import {RoleModel} from './RoleModel';

export interface AuthUserDetailsModel {
  employeeId: bigint;
  employeeEmail: string;
  role: RoleModel;
}
