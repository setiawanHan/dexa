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
@Table(name = "tb_audit_login")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TbAuditLogin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "audit_id")
    private BigInteger auditId;

    @Column(name = "session_id")
    private String sessionId;

    @Column(name = "employee_email")
    private String employeeEmail;

    @Column(name = "employee_role_name")
    private String employeeRoleName;

    @Column(name = "audit_expired")
    @Convert(converter = TimeZoneConverter.class)
    private LocalDateTime auditExpired;
}
