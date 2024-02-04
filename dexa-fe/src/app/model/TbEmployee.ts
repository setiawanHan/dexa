import {TbEmployeeProfiles} from './TbEmployeeProfiles';
import {TbRoles} from './TbRoles';

export interface TbEmployee {
  employeeId: bigint;
  employeeEmail: string;
  employeePassword: string;
  employeeIsActive: boolean;
  employeeProfiles: TbEmployeeProfiles;
  role: TbRoles;
}
