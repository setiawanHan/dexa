import {Injectable} from '@angular/core';
import { RestWrapper } from 'src/app/model/rest/RestWrapper';
import { TbEmployeeAttendance } from 'src/app/model/TbEmployeeAttendance';
import {IAttendance} from '../interfaces/IAttendance';
import {HttpClient} from '@angular/common/http';

@Injectable()
export class AttendanceService implements IAttendance {

  constructor(private http: HttpClient) {}
  getAttendanceHit(employeeId: bigint, attendanceStatus: string): Promise<RestWrapper<TbEmployeeAttendance>> {
    const url = `/dexa/api/attendance/hit?employeeId=${employeeId}&attendanceStatus=${attendanceStatus}`;
    return this.http.get<RestWrapper<TbEmployeeAttendance>>(url).toPromise();
  }
  getAttendanceSummaryPerDayOne(employeeId: bigint, pageNo: number, pageSize: number): Promise<RestWrapper<TbEmployeeAttendance[]>> {
    const url = `/dexa/api/attendance/summary/perDayOne?employeeId=${employeeId}&pageNo=${pageNo}&pageSize=${pageSize}`;
    return this.http.get<RestWrapper<TbEmployeeAttendance[]>>(url).toPromise();
  }
  getAttendanceSummaryPerRangeDay(employeeId: bigint, dateFrom: string, dateTo: string, pageNo: number, pageSize: number): RestWrapper<TbEmployeeAttendance[]> {
    throw new Error('Method not implemented.');
  }
}
