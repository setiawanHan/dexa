package com.dexa.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthUserDetailsModel {
    private BigInteger employeeId;
    private String employeeEmail;
    private RoleModel role;
}
