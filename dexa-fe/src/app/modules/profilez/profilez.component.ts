import { Component, OnInit } from '@angular/core';
import {AuthModel} from '../../model/AuthModel';

@Component({
  selector: 'app-profilez',
  templateUrl: './profilez.component.html',
  styleUrls: []
})
export class ProfilezComponent implements OnInit {
  constructor() { }

  formDataEmployee = { employeePhone: '', employeeRawPassword: '' };
  employeeCredentials: AuthModel;

  public isEditCardHide = true;
  public isOnEditPhone = false;
  public isOnEditPassword = false;

  ngOnInit(): void {
    this.employeeCredentials = JSON.parse(sessionStorage.getItem('EMPLOYEE_CREDS'));
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
}
