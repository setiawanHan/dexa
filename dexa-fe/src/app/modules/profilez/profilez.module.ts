import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {RouterModule, Routes} from '@angular/router';
import {FormsModule} from '@angular/forms';
import {SharedModule} from '../../shared/shared-module';
import {ProfilezComponent} from './profilez.component';
import {EmployeeService} from '../../core/services/EmployeeService';

const profilezRoute: Routes = [
  { path: '', component: ProfilezComponent }
];

@NgModule({
  declarations: [
    ProfilezComponent
  ],
  imports: [
    CommonModule,
    FormsModule,
    RouterModule.forChild(profilezRoute),
    SharedModule
  ],
  providers: [
    EmployeeService
  ],
  exports: [
    RouterModule,
    ProfilezComponent
  ]
})
export class ProfilezModule {}
