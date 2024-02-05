import {RouterModule, Routes} from '@angular/router';
import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {FormsModule} from '@angular/forms';
import {SharedModule} from '../../shared/shared-module';
import {SummariesComponent} from './summaries.component';

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
  providers: [],
  exports: [
    RouterModule,
    SummariesComponent
  ]
})
export class SummariesModule {}
