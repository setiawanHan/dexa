package com.dexa.models;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmployeeModel {
    private String employeeEmail;
    private String employeePassword;
    private EmployeeProfileModel employeeProfileModel;
    private RoleModel roleModel;
}
