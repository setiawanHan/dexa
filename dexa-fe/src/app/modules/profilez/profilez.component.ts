import { Component, OnInit } from '@angular/core';
import {AuthModel} from '../../model/AuthModel';
import {EmployeeService} from '../../core/services/EmployeeService';
import {TbEmployeeProfiles} from '../../model/TbEmployeeProfiles';

@Component({
  selector: 'app-profilez',
  templateUrl: './profilez.component.html',
  styleUrls: []
})
export class ProfilezComponent implements OnInit {
  constructor(private employeeService: EmployeeService) { }

  formDataEmployee = { employeePhone: '', employeeRawPassword: '' };
  employeeCredentials: AuthModel;
  employeeProfiles: TbEmployeeProfiles;

  public isEditCardHide = true;
  public isOnEditPhone = false;
  public isOnEditPassword = false;

  public isOnKeyDown = false;

  ngOnInit(): void {
    this.employeeCredentials = JSON.parse(sessionStorage.getItem('EMPLOYEE_CREDS'));
    this.getEmployeeProfileData(this.employeeCredentials.userInfo.employeeId);
  }

  onEditPhone(): void {
    this.isEditCardHide = false;
    this.isOnEditPhone = true;
    this.isOnEditPassword = false;
  }

  onEditPassword(): void {
    this.isEditCardHide = false;
    this.isOnEditPhone = false;
    this.isOnEditPassword = true;
  }

  onEditClose(): void {
    this.isEditCardHide = true;
    this.isOnEditPhone = false;
    this.isOnEditPassword = false;
  }

  async onDataEditSave(): Promise<void> {
    if (this.isOnEditPhone) {
      if (this.formDataEmployee.employeePhone == null || this.formDataEmployee.employeePhone === '' || this.formDataEmployee.employeePhone === undefined) {
        alert(`Tidak dapat update dengan value Phone Number kosong.`);
      } else {
        const employeeCreds: AuthModel = JSON.parse(sessionStorage.getItem('EMPLOYEE_CREDS'));
        await this.employeeService.updatePasswordAndPhone(employeeCreds.userInfo.employeeId, '', this.formDataEmployee.employeePhone)
          .then(d => {
            this.onEditClose();
            alert('Update Phone Number Sukses.');
            this.removeSessionByKey('EMPLOYEE_PROFILES');
            this.getEmployeeProfileData(employeeCreds.userInfo.employeeId);
            }, (e: any) => {
            alert(e.error.responseMessage);
          });
      }
    } else if (this.isOnEditPassword) {
      if (this.formDataEmployee.employeeRawPassword == null || this.formDataEmployee.employeeRawPassword === '' || this.formDataEmployee.employeeRawPassword === undefined) {
        alert(`Tidak dapat update dengan value Password kosong.`);
      } else {
        const employeeCreds: AuthModel = JSON.parse(sessionStorage.getItem('EMPLOYEE_CREDS'));
        await this.employeeService.updatePasswordAndPhone(employeeCreds.userInfo.employeeId, this.formDataEmployee.employeeRawPassword, '')
          .then(d => {
            this.onEditClose();
            alert(`Update Password Sukses. Silahkan Logout dan Login kembali untuk mencoba.`);
          }, (e: any) => {
            alert(e.error.responseMessage);
          });
      }
    }
  }

  private async getEmployeeProfileData(employeeId: bigint): Promise<void> {
    const employeeProfilesSession = JSON.parse(sessionStorage.getItem('EMPLOYEE_PROFILES'));
    if (employeeProfilesSession == null) {
      await this.employeeService.getEmployeeProfile(employeeId).then(d => {
        this.employeeProfiles = d.data;
        sessionStorage.setItem('EMPLOYEE_PROFILES', JSON.stringify(d.data));
      }, (e: any) => {
        alert(e.error.responseMessage);
      });
    } else {
      this.employeeProfiles = JSON.parse(sessionStorage.getItem('EMPLOYEE_PROFILES'));
    }
  }

  private removeSessionByKey(sessionKey: string): void {
    sessionStorage.removeItem('EMPLOYEE_PROFILES');
  }
}
