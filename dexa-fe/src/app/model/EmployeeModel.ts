import {EmployeeProfileModel} from './EmployeeProfileModel';
import {RoleModel} from './RoleModel';

export interface EmployeeModel {
  employeeEmail: string;
  employeePassword: string;
  employeeProfileModel: EmployeeProfileModel;
  roleModel: RoleModel;
}
