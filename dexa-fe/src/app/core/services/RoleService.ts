import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {IRoles} from '../interfaces/IRoles';

@Injectable()
export class RoleService implements IRoles {
  constructor(private http: HttpClient) {}
  public getRoleById(roleId: number): Promise<any> {
    const url = `/dexa/api/roles/byId?roleId=${roleId}`;
    return this.http.get(url).toPromise();
  }
}
