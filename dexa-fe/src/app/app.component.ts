import {Component, OnInit} from '@angular/core';
import {RoleService} from './core/services/RoleService';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit{
  title = 'dexa-fe';

  roleName: string;

  constructor(public roleService: RoleService) {}



  ngOnInit(): void {
    const result = this.roleService.getRoleById(1);
    result.subscribe(data => {
      console.log(JSON.stringify(data));
      this.roleName = data.data.roleName;
    });
  }
}
