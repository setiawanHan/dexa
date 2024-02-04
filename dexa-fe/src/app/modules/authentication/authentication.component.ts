import { Component, OnInit } from '@angular/core';
import {AuthenticationService} from '../../core/services/AuthenticationService';
import {RestWrapper} from '../../model/rest/RestWrapper';
import {AuthModel} from '../../model/AuthModel';
import {Router} from '@angular/router';

@Component({
  selector: 'app-authentication',
  templateUrl: './authentication.component.html',
  styleUrls: []
})
export class AuthenticationComponent implements OnInit {
  fd = { employeeEmail: '', employeeRawPassword: '' };

  constructor(private authenticationService: AuthenticationService,
              private router: Router) { }

  ngOnInit(): void {}

  async onLogin(): Promise<void> {
    await this.authenticationService.login(this.fd.employeeEmail, this.fd.employeeRawPassword)
      .then((d: RestWrapper<AuthModel>) => {
        this.assignToSessionStorage('EMPLOYEE_CREDS', JSON.stringify(d.data));
        this.routeAndReload(['/attendance']);
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

  private routeAndReload(commands: any[]): void {
    this.router.navigate(commands).then(r => window.location.reload());
  }
}
