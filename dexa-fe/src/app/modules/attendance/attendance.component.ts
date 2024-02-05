import {ChangeDetectorRef, Component, OnInit} from '@angular/core';
import {AttendanceService} from '../../core/services/AttendanceService';
import {AuthModel} from '../../model/AuthModel';
import {error, promise} from 'protractor';
import {TbEmployeeAttendance} from '../../model/TbEmployeeAttendance';
import {BehaviorSubject, Observable, Subject} from 'rxjs';

@Component({
  selector: 'app-attendance',
  templateUrl: './attendance.component.html',
  styleUrls: []
})
export class AttendanceComponent implements OnInit {
  constructor(private attendanceService: AttendanceService) { }

  private dataSubject = new BehaviorSubject<TbEmployeeAttendance[]>([]);
  public tableAttendance$ = this.dataSubject.asObservable();

  async ngOnInit(): Promise<void> {
    await this.loadAttendanceDataPerDayOne();
  }

  async hit(masukOrPulang: string): Promise<void> {
    const sessStorage: AuthModel = JSON.parse(sessionStorage.getItem('EMPLOYEE_CREDS'));
    const employeeId: bigint = sessStorage.userInfo.employeeId;

    await this.attendanceService.getAttendanceHit(employeeId, masukOrPulang).then(d => {
      if (masukOrPulang === 'MASUK') {
        alert(`MASUK pada ( ${d.data.dateAndTime} )`);
        this.loadAttendanceDataPerDayOne();
      } else {
        alert(`PULANG pada ( ${d.data.dateAndTime} )`);
        this.loadAttendanceDataPerDayOne();
      }
    }, (e: any) => {
      alert(e.error.responseMessage);
    });
  }

  async loadAttendanceDataPerDayOne(): Promise<void> {
    const sessStorage: AuthModel = JSON.parse(sessionStorage.getItem('EMPLOYEE_CREDS'));
    const employeeId: bigint = sessStorage.userInfo.employeeId;

    await this.attendanceService.getAttendanceSummaryPerDayOne(employeeId, 0, 1000).then(d => {
      this.dataSubject.next(d.data);
    }, (e: any) => {
      alert(e.error.responseMessage);
    });
  }
}
