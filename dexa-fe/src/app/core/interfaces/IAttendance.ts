import {RestWrapper} from '../../model/rest/RestWrapper';
import {TbEmployeeAttendance} from '../../model/TbEmployeeAttendance';

export interface IAttendance {
  getAttendanceHit(employeeId: bigint, attendanceStatus: string): Promise<RestWrapper<TbEmployeeAttendance>>;
  getAttendanceSummaryPerDayOne(employeeId: bigint, pageNo: number, pageSize: number): Promise<RestWrapper<TbEmployeeAttendance[]>>;
  getAttendanceSummaryPerRangeDay(employeeId: bigint, dateFrom: string, dateTo: string, pageNo: number, pageSize: number): Promise<RestWrapper<TbEmployeeAttendance[]>>;
}
