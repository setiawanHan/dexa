import { Component, OnInit } from '@angular/core';
import {Router} from '@angular/router';

@Component({
  selector: 'app-sidebar',
  templateUrl: './sidebar.component.html',
  styleUrls: []
})
export class SidebarComponent implements OnInit {
  constructor() {}

  public hideSideBar = true;
  public menuId = '';

  ngOnInit(): void {
    this.checkSessionToShowOrHideNavBar();
    this.menuId = sessionStorage.getItem('ACTIVE_MENU') == null ? '/attendance' : sessionStorage.getItem('ACTIVE_MENU') ;
  }

  private checkSessionToShowOrHideNavBar(): void {
    if (sessionStorage.getItem('EMPLOYEE_CREDS') == null) {
      this.hideSideBar = true;
    } else {
      this.hideSideBar = false;
    }
  }

  public menuIdef(s: string): void {
    sessionStorage.removeItem('ACTIVE_MENU');
    sessionStorage.setItem('ACTIVE_MENU', s);
    this.menuId = sessionStorage.getItem('ACTIVE_MENU');
  }
}
