import {Injectable} from '@angular/core';
import {IRoles} from './interfaces/IRoles';
import {RestWrapper} from '../../model/rest/RestWrapper';
import {TbRoles} from '../../model/TbRoles';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';

@Injectable()
export class RoleService implements IRoles {
  constructor(private http: HttpClient) {}
  public getRoleById(roleId: number): Observable<RestWrapper<TbRoles>> {
    const url = `/dexa/api/roles/byId?roleId=${roleId}`;
    return this.http.get<RestWrapper<TbRoles>>(url);
  }
}
