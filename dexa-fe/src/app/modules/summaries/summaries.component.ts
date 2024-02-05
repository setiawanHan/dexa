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
      .then(() => {
        alert('Reset Sukses.');
        this.onClearDatePicker();
      });
  }

  onDateFromChange($event: any): void {}
  onDateToChange($event: any): void {}
  onClearDatePicker(): void {
    this.selectedDateFrom = null;
    this.selectedDateTo = null;
  }

  async onSearch(): Promise<void> {
    const dFrom = this.datePipe.transform(this.selectedDateFrom, 'yyyy-MM-dd') || '';
    const dTo = this.datePipe.transform(this.selectedDateTo, 'yyyy-MM-dd') || '';

    if (dFrom === '' || dTo === '') {
      alert('Mohon isi (Date From) dan (Date To) Secara Lengkap.');
    } else {
      await this.loadAttendanceByRange();
    }
  }

  private async loadAttendancePerDayOne(): Promise<void> {
    const employeeCreds: AuthModel = Utils.getSessionStorageByKey<AuthModel>('EMPLOYEE_CREDS');
    await this.attendanceService.getAttendanceSummaryPerDayOne(employeeCreds.userInfo.employeeId, 0, 1000).then(d => {
      this.dataSubject.next(d.data);
    }, (e: any) => {
      alert(e.error.responseMessage);
    });
  }

  private async loadAttendanceByRange(): Promise<void> {
    const employeeCreds: AuthModel = Utils.getSessionStorageByKey<AuthModel>('EMPLOYEE_CREDS');
    await this.attendanceService.getAttendanceSummaryPerRangeDay(
      employeeCreds.userInfo.employeeId,
      this.datePipe.transform(this.selectedDateFrom, 'yyyy-MM-dd'),
      this.datePipe.transform(this.selectedDateTo, 'yyyy-MM-dd'),
      0,
      1000).then(d => {
        this.dataSubject.next(d.data);
    }, (e: any) => {
      alert(e.error.responseMessage);
    });
  }
}
