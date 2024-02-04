import {RouterModule, Routes} from '@angular/router';
import {AttendanceComponent} from './attendance.component';
import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {SharedModule} from '../../shared/shared-module';
import {AttendanceService} from '../../core/services/AttendanceService';

const attendanceRoutes: Routes = [
  {path: '', component: AttendanceComponent}
];
@NgModule({
  declarations: [
    AttendanceComponent
  ],
  imports: [
    CommonModule,
    RouterModule.forChild(attendanceRoutes),
    SharedModule
  ],
  providers: [
    AttendanceService
  ],
  exports: [
    RouterModule,
    AttendanceComponent
  ]
})
export class AttendanceModule {}
