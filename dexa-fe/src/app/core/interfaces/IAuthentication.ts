import {Observable} from 'rxjs';
import {RestWrapper} from '../../model/rest/RestWrapper';
import {AuthModel} from '../../model/AuthModel';
import {TbAuditLogin} from '../../model/TbAuditLogin';

export interface IAuthentication {
  login(employeeEmail: string, employeePassword: string): Promise<RestWrapper<AuthModel>>;
  logout(employeeEmail: string): Observable<RestWrapper<string>>;
  getAuditLoginByEmployeeEmail(employeeEmail: string): Observable<RestWrapper<TbAuditLogin>>;
  employeeIsActive(employeeEmail: string): Observable<RestWrapper<boolean>>;
}
