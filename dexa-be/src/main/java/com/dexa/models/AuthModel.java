package com.dexa.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthModel {
    private boolean employeeIsAuthenticated;
    private String employeeAuthenticationSession;
    private AuthUserDetailsModel userInfo;
}
