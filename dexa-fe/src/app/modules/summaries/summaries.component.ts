import { Component, OnInit } from '@angular/core';
import {AuthModel} from '../../model/AuthModel';
import {Utils} from '../../utils/Utils';
import {AttendanceService} from '../../core/services/AttendanceService';
import {BehaviorSubject} from 'rxjs';
import {TbEmployeeAttendance} from '../../model/TbEmployeeAttendance';
import {DatePipe} from '@angular/common';

@Component({
  selector: 'app-summaries',
  templateUrl: './summaries.component.html',
  styleUrls: []
})
export class SummariesComponent implements OnInit {
  constructor(private attendanceService: AttendanceService,
              private datePipe: DatePipe) {}

  private dataSubject = new BehaviorSubject<TbEmployeeAttendance[]>([]);
  public tableAttendance$ = this.dataSubject.asObservable();
  public selectedDateFrom: Date;
  public selectedDateTo: Date;

  async ngOnInit(): Promise<void> {
    await this.loadAttendancePerDayOne();
  }

  async onRefreshSummary(): Promise<void> {
    await this.loadAttendancePerDayOne()
      .then(() => alert('Reset Sukses.'));
  }

  onDateFromChange($event: any): void {}
  onDateToChange($event: any): void {}
  onCl(): void {
    console.log('Button Clicked with Date:', this.datePipe.transform(this.selectedDateFrom, 'yyyy-MM-dd') || '');
  }

  private async loadAttendancePerDayOne(): Promise<void> {
    const employeeCreds: AuthModel = Utils.getSessionStorageByKey<AuthModel>('EMPLOYEE_CREDS');
    await this.attendanceService.getAttendanceSummaryPerDayOne(employeeCreds.userInfo.employeeId, 0, 1000).then(d => {
      this.dataSubject.next(d.data);
    }, (e: any) => {
      alert(e.error.responseMessage);
    });
  }
}
