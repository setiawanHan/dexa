import {Component, Input, OnInit} from '@angular/core';
import {AuthenticationService} from '../../../core/services/AuthenticationService';
import {AuthModel} from '../../../model/AuthModel';
import {Router} from '@angular/router';
@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: []
})
export class HeaderComponent implements OnInit {
  constructor(private authenticationService: AuthenticationService,
              private router: Router) { }

  public hideNavBar = true;
  ngOnInit(): void {
    this.checkSessionToShowOrHideNavBar();
  }

  async logout(): Promise<void> {
    const employeeCreds: AuthModel = JSON.parse(sessionStorage.getItem('EMPLOYEE_CREDS'));
    await this.authenticationService.logout(employeeCreds.userInfo.employeeEmail)
      .then(d => {
        sessionStorage.clear();
        this.routeAndReload(['/authentication']);
      })
      .catch((e: any) => {
        alert(e.error.responseMessage);
      });
  }

  private checkSessionToShowOrHideNavBar(): void {
    if (sessionStorage.getItem('EMPLOYEE_CREDS') == null) {
      this.hideNavBar = true;
    } else {
      this.hideNavBar = false;
    }
  }

  private routeAndReload(commands: any[]): void {
    this.router.navigate(commands)
      .then(() => window.location.reload());
  }
}
