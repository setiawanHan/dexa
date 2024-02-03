package com.dexa.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigInteger;
import java.sql.Timestamp;

@Entity
@Table(name = "tb_employee_attendance")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TbEmployeeAttendance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private BigInteger attendanceId;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "employee_id", referencedColumnName = "employee_id")
    private TbEmployee employee;

    @Column(name = "date_and_time")
    private Timestamp dateAndTime;

    @Column(name = "status")
    private String status;
}
