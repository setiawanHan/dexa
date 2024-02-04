import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {RouterModule, Routes} from '@angular/router';
import {FormsModule} from '@angular/forms';
import {SharedModule} from '../../shared/shared-module';
import {ProfilezComponent} from './profilez.component';

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
  providers: [],
  exports: [
    RouterModule,
    ProfilezComponent
  ]
})
export class ProfilezModule {}
