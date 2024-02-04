import {AuthUserDetailsModel} from './AuthUserDetailsModel';

export interface AuthModel {
  employeeIsAuthenticated: boolean;
  employeeAuthenticationSession: string;
  userInfo: AuthUserDetailsModel;
}
