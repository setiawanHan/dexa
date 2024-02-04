import {RestWrapper} from '../../../model/rest/RestWrapper';
import {TbRoles} from '../../../model/TbRoles';
import {Observable} from 'rxjs';

export interface IRoles {
  getRoleById(roleId: number): Observable<RestWrapper<TbRoles>>;
}
