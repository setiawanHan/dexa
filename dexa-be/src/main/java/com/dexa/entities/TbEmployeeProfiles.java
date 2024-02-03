package com.dexa.entities;

import com.dexa.utils.TimeZoneConverter;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigInteger;
import java.time.LocalDateTime;

@Entity
@Table(name = "tb_employee_profiles")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TbEmployeeProfiles {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employee_profile_id")
    private BigInteger employeeProfileId;

    @Column(name = "employee_name")
    private String employeeName;

    @Column(name = "employee_position")
    private String employeePosition;

    @Column(name = "employee_phone")
    private String employeePhone;

    @Column(name = "update_date")
    @Convert(converter = TimeZoneConverter.class)
    private LocalDateTime updateDate;
}
