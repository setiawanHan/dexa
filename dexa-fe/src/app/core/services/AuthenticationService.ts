import {Injectable} from '@angular/core';
import { Observable } from 'rxjs';
import { AuthModel } from 'src/app/model/AuthModel';
import { RestWrapper } from 'src/app/model/rest/RestWrapper';
import { TbAuditLogin } from 'src/app/model/TbAuditLogin';
import {IAuthentication} from '../interfaces/IAuthentication';
import {HttpClient} from '@angular/common/http';

@Injectable()
export class AuthenticationService implements IAuthentication {
  constructor(public http: HttpClient) { }
  login(employeeEmail: string, employeePassword: string): Promise<RestWrapper<AuthModel>> {
    const url = `/dexa/api/auth/login?employeeEmail=${employeeEmail}&employeePassword=${employeePassword}`;
    return this.http.get<RestWrapper<AuthModel>>(url).toPromise();
  }
  logout(employeeEmail: string): Observable<RestWrapper<string>> {
    throw new Error('Method not implemented.');
  }
  getAuditLoginByEmployeeEmail(employeeEmail: string): Observable<RestWrapper<TbAuditLogin>> {
    throw new Error('Method not implemented.');
  }
  employeeIsActive(employeeEmail: string): Observable<RestWrapper<boolean>> {
    throw new Error('Method not implemented.');
  }
}
