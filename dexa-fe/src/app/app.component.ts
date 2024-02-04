import {Component, OnInit} from '@angular/core';
import {Router} from '@angular/router';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: []
})
export class AppComponent implements OnInit {

  constructor(private router: Router) {}

  ngOnInit(): void {
    this.checkSessionToForceRedirect();
  }

  private checkSessionToForceRedirect(): void {
    if (sessionStorage.getItem('EMPLOYEE_CREDS') == null) {
      this.router.navigate(['/authentication']);
    }
  }
}
