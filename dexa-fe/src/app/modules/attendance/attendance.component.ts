import {Component, OnInit} from '@angular/core';
import {AttendanceService} from '../../core/services/AttendanceService';
import {AuthModel} from '../../model/AuthModel';
import {TbEmployeeAttendance} from '../../model/TbEmployeeAttendance';
import {BehaviorSubject} from 'rxjs';
import {Utils} from '../../utils/Utils';

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
    const sessStorage: AuthModel = Utils.getSessionStorageByKey<AuthModel>('EMPLOYEE_CREDS');
    const employeeId: bigint = sessStorage.userInfo.employeeId;

    await this.attendanceService.getAttendanceHit(employeeId, masukOrPulang).then(d => {
      if (masukOrPulang === 'MASUK') {
        alert(`MASUK --( ${d.data.dateAndTime} )`);
        this.loadAttendanceDataPerDayOne();
      } else {
        alert(`PULANG -- ( ${d.data.dateAndTime} )`);
        this.loadAttendanceDataPerDayOne();
      }
    }, (e: any) => {
      alert(e.error.responseMessage);
    });
  }

  async loadAttendanceDataPerDayOne(): Promise<void> {
    const sessStorage: AuthModel = Utils.getSessionStorageByKey<AuthModel>('EMPLOYEE_CREDS');
    const employeeId: bigint = sessStorage.userInfo.employeeId;

    await this.attendanceService.getAttendanceSummaryPerDayOne(employeeId, 0, 1000).then(d => {
      this.dataSubject.next(d.data);
    }, (e: any) => {
      alert(e.error.responseMessage);
    });
  }
}
