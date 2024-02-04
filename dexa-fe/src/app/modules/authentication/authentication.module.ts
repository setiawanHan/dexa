import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AuthenticationComponent } from './authentication.component';
import {RouterModule, Routes} from '@angular/router';
import {FormsModule} from '@angular/forms';
import {AuthenticationService} from '../../core/services/AuthenticationService';
import {RoleService} from '../../core/services/RoleService';

const authRoute: Routes = [
  { path: '', component: AuthenticationComponent }
];

@NgModule({
  declarations: [
    AuthenticationComponent
  ],
  imports: [
    CommonModule,
    FormsModule,
    RouterModule.forChild(authRoute)
  ],
  providers: [
    AuthenticationService
  ],
  exports: [RouterModule, AuthenticationComponent]
})
export class AuthenticationModule {}
