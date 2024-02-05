import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {AuthGuard} from './shared/guard/AuthGuard';
import {LoginGuard} from './shared/guard/LoginGuard';

const routes: Routes = [
  { path: '', redirectTo: 'authentication', pathMatch: 'full' },
  { path: 'authentication', loadChildren: () => import('./modules/authentication/authentication.module').then(m => m.AuthenticationModule), canActivate: [LoginGuard] },
  { path: 'attendance', loadChildren: () => import('./modules/attendance/attendance.module').then(m => m.AttendanceModule), canActivate: [AuthGuard] },
  { path: 'profilez', loadChildren: () => import('./modules/profilez/profilez.module').then(m => m.ProfilezModule), canActivate: [AuthGuard] },
  { path: 'summaries', loadChildren: () => import('./modules/summaries/summaries.module').then(m => m.SummariesModule ), canActivate: [AuthGuard] }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {}
