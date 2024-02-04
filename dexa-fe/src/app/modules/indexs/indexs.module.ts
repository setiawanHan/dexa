import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {RouterModule} from '@angular/router';
import {IndexsComponent} from './indexs.component';
import {SharedModule} from '../../shared/shared-module';

@NgModule({
  declarations: [
    IndexsComponent
  ],
  imports: [
    CommonModule,
    RouterModule.forChild([
      {path: '', component: IndexsComponent}
    ]),
    SharedModule
  ],
  exports: [
    RouterModule,
    IndexsComponent
  ]
})
export class IndexsModule { }
