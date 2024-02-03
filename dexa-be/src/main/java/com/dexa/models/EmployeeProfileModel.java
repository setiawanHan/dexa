package com.dexa.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;
import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmployeeProfileModel {
    private BigInteger employeeProfileId;
    private String employeeName;
    private String employeePosition;
    private String employeePhone;
    private Timestamp updateDate;
}
