package com.dexa.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigInteger;

@Entity
@Table(name = "tb_employee")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TbEmployee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employee_id")
    private BigInteger employeeId;

    @Column(name = "employee_email")
    private String employeeEmail;

    @Column(name = "employee_password")
    private String employeePassword;

    @Column(name = "is_active")
    private boolean employeeIsActive;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "role_id", referencedColumnName = "role_id")
    private TbRoles role;

}
