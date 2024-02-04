import {RouterModule, Routes} from '@angular/router';
import {AttendanceComponent} from './attendance.component';
import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {SharedModule} from '../../shared/shared-module';

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
  providers: [],
  exports: [
    RouterModule,
    AttendanceComponent
  ]
})
export class AttendanceModule {}
