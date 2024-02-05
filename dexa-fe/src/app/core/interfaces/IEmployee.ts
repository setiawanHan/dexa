import {EmployeeModel} from '../../model/EmployeeModel';
import {RestWrapper} from '../../model/rest/RestWrapper';
import {TbEmployee} from '../../model/TbEmployee';
import {TbEmployeeProfiles} from '../../model/TbEmployeeProfiles';

export interface IEmployee {
  addNewEmployee(request: EmployeeModel): Promise<RestWrapper<TbEmployee>>;
  getEmployeeByEmail(employeeEmail: string): Promise<RestWrapper<TbEmployee>>;
  getEmployeeProfile(employeeId: bigint): Promise<RestWrapper<TbEmployeeProfiles>>;
  updatePasswordAndPhone(employeeId: bigint, employeeNewPassword: string, employeeNewPhone: string): Promise<RestWrapper<string>>;
}
