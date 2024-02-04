import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-sidebar',
  templateUrl: './sidebar.component.html',
  styleUrls: []
})
export class SidebarComponent implements OnInit {
  constructor() { }

  public hideSideBar = true;

  ngOnInit(): void {
    this.checkSessionToShowOrHideNavBar();
  }

  private checkSessionToShowOrHideNavBar(): void {
    if (sessionStorage.getItem('EMPLOYEE_CREDS') == null) {
      this.hideSideBar = true;
    } else {
      this.hideSideBar = false;
    }
  }
}
