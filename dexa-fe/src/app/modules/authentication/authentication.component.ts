import { Component, OnInit } from '@angular/core';
import {AuthenticationService} from '../../core/services/AuthenticationService';
import {RestWrapper} from '../../model/rest/RestWrapper';
import {AuthModel} from '../../model/AuthModel';

@Component({
  selector: 'app-authentication',
  templateUrl: './authentication.component.html',
  styleUrls: []
})
export class AuthenticationComponent implements OnInit {
  fd = { employeeEmail: '', employeeRawPassword: '' };

  constructor(public authenticationService: AuthenticationService) { }
  ngOnInit(): void {}

  async onLogin(): Promise<void> {
    await this.authenticationService.login(this.fd.employeeEmail, this.fd.employeeRawPassword)
      .then((d: RestWrapper<AuthModel>) => {
        this.assignToSessionStorage('EMPLOYEE_CREDS', JSON.stringify(d.data));
        alert('--- sukses ---');
      }).catch((e: any) => {
        this.clearSessionStorage();
        alert(e.error.responseMessage);
      });
  }

  private assignToSessionStorage(key: any, value: any): void {
    sessionStorage.clear();
    sessionStorage.setItem(key.toString(), value);
  }
  private clearSessionStorage(): void {
    sessionStorage.clear();
  }
}
