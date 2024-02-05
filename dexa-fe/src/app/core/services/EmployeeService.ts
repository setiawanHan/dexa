import { EmployeeModel } from 'src/app/model/EmployeeModel';
import { RestWrapper } from 'src/app/model/rest/RestWrapper';
import { TbEmployee } from 'src/app/model/TbEmployee';
import { TbEmployeeProfiles } from 'src/app/model/TbEmployeeProfiles';
import {IEmployee} from '../interfaces/IEmployee';
import {HttpClient} from '@angular/common/http';
import {Injectable} from '@angular/core';

@Injectable()
export class EmployeeService implements IEmployee {

  constructor(private http: HttpClient) {}

  addNewEmployee(request: EmployeeModel): Promise<RestWrapper<TbEmployee>> {
    throw new Error('Method not implemented.');
  }
  getEmployeeByEmail(employeeEmail: string): Promise<RestWrapper<TbEmployee>> {
    throw new Error('Method not implemented.');
  }
  getEmployeeProfile(employeeId: bigint): Promise<RestWrapper<TbEmployeeProfiles>> {
    const url = `/dexa/api/employee/profile?employeeId=${employeeId}`;
    return this.http.get<RestWrapper<TbEmployeeProfiles>>(url).toPromise();
  }
  updatePasswordAndPhone(employeeId: bigint, employeeNewPassword: string, employeeNewPhone: string): Promise<RestWrapper<string>> {
    throw new Error('Method not implemented.');
  }
}
