import {NgModule} from '@angular/core';
import {HeaderComponent} from './components/header/header.component';
import {CommonModule} from '@angular/common';
import {SidebarComponent} from './components/sidebar/sidebar.component';
import {AuthenticationService} from '../core/services/AuthenticationService';
import {AttendanceModule} from '../modules/attendance/attendance.module';
import {AppRoutingModule} from '../app-routing.module';
import {RouterModule} from '@angular/router';

@NgModule({
  declarations: [
    HeaderComponent,
    SidebarComponent
  ],
  exports: [
    HeaderComponent,
    SidebarComponent
  ],
  imports: [CommonModule],
  providers: [AuthenticationService]
})
export class SharedModule {}
