import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';

const routes: Routes = [
  { path: '', redirectTo: 'authentication', pathMatch: 'full' },
  { path: 'authentication', loadChildren: () => import('./modules/authentication/authentication.module').then(m => m.AuthenticationModule) },
  { path: 'attendance', loadChildren: () => import('./modules/attendance/attendance.module').then(m => m.AttendanceModule) }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {}
