import {NgModule} from '@angular/core';
import {HeaderComponent} from './components/header/header.component';
import {CommonModule} from '@angular/common';
import {SidebarComponent} from './components/sidebar/sidebar.component';

@NgModule({
  declarations: [
    HeaderComponent,
    SidebarComponent
  ],
  exports: [
    HeaderComponent,
    SidebarComponent
  ],
  imports: [CommonModule]
})
export class SharedModule { }
