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
@Table(name = "tb_employee_attendance")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TbEmployeeAttendance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "attendance_id")
    private BigInteger attendanceId;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "employee_id", referencedColumnName = "employee_id")
    private TbEmployee employee;

    @Column(name = "date_and_time")
    @Convert(converter = TimeZoneConverter.class)
    private LocalDateTime dateAndTime;

    @Column(name = "status")
    private String status;
}
