import {RouterModule, Routes} from '@angular/router';
import {NgModule} from '@angular/core';
import {CommonModule, DatePipe} from '@angular/common';
import {FormsModule} from '@angular/forms';
import {SharedModule} from '../../shared/shared-module';
import {SummariesComponent} from './summaries.component';
import {AttendanceService} from '../../core/services/AttendanceService';

const summariesRoute: Routes = [
  { path: '', component: SummariesComponent }
];

@NgModule({
  declarations: [
    SummariesComponent
  ],
  imports: [
    CommonModule,
    FormsModule,
    RouterModule.forChild(summariesRoute),
    SharedModule
  ],
  providers: [
    AttendanceService,
    DatePipe
  ],
  exports: [
    RouterModule,
    SummariesComponent
  ]
})
export class SummariesModule {}
