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

  onDataEditSave(): void {
    if (this.isOnEditPhone) {
      this.onEditClose();
    } else if (this.isOnEditPassword) {
      this.onEditClose();
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
}
