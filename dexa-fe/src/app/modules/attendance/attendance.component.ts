import {ChangeDetectorRef, Component, OnInit} from '@angular/core';
import {AttendanceService} from '../../core/services/AttendanceService';
import {AuthModel} from '../../model/AuthModel';
import {error} from 'protractor';

@Component({
  selector: 'app-attendance',
  templateUrl: './attendance.component.html',
  styleUrls: []
})
export class AttendanceComponent implements OnInit {
  constructor(private attendanceService: AttendanceService) { }
  ngOnInit(): void {}

  async hit(masukOrPulang: string): Promise<void> {
    const sessStorage: AuthModel = JSON.parse(sessionStorage.getItem('EMPLOYEE_CREDS'));
    const employeeId: bigint = sessStorage.userInfo.employeeId;

    await this.attendanceService.getAttendanceHit(employeeId, masukOrPulang).then(d => {
      if (masukOrPulang === 'MASUK') {
        alert(`MASUK pada ( ${d.data.dateAndTime} )`);
      } else {
        alert(`PULANG pada ( ${d.data.dateAndTime} )`);
      }
    }, (e: any) => {
      alert(e.error.responseMessage);
    });

  }
}
