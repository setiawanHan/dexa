import {TbEmployee} from './TbEmployee';

export class TbEmployeeAttendance {
  attendanceId: bigint;
  employee: TbEmployee;
  dateAndTime: string;
  status: string;
}
