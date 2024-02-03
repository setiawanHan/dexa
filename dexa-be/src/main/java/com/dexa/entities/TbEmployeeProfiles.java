package com.dexa.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigInteger;
import java.sql.Timestamp;

@Entity
@Table(name = "tb_employee_profiles")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TbEmployeeProfiles {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private BigInteger employeeProfileId;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "employee_id", referencedColumnName = "employee_id")
    private TbEmployee employee;

    @Column(name = "employee_name")
    private String employeeName;

    @Column(name = "employee_position")
    private String employeePosition;

    @Column(name = "employee_phone")
    private String employeePhone;

    @Column(name = "update_date")
    private Timestamp updateDate;
}
