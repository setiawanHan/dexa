package com.dexa.repo;

import com.dexa.entities.TbEmployeeAttendance;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;

@Repository
public interface AttendanceRepo extends JpaRepository<TbEmployeeAttendance, BigInteger> {
    @Query(nativeQuery = true,
            value = "SELECT COUNT(1) FROM tb_employee_attendance tea WHERE tea.employee_id = ?1 AND DATE(tea.date_and_time) = DATE(NOW())")
    int dexaCountAttendancePerDay(BigInteger employeeId);

    @Query(nativeQuery = true,
            value = "SELECT COUNT(1) FROM tb_employee_attendance tea WHERE tea.employee_id = ?1 AND DATE(tea.date_and_time) = DATE(NOW()) AND tea.status = ?2")
    int dexaCountAttendancePerDayByStatus(BigInteger employeeId, String status);

    @Query(nativeQuery = true,
            value = "SELECT * FROM tb_employee_attendance WHERE employee_id = ?1 AND date_and_time >= DATE_FORMAT(NOW(), '%Y-%m-01')")
    List<TbEmployeeAttendance> dexaGetSummaryAttendance(BigInteger employeeId, Pageable pageable);

    @Query(nativeQuery = true,
            value = "SELECT * FROM tb_employee_attendance tea WHERE tea.employee_id = ?1 AND tea.date_and_time >= DATE_FORMAT(?2, '%Y-%m-%d %H:%i:%s') AND tea.date_and_time <= DATE_FORMAT(?3, '%Y-%m-%d %H:%i:%s')")
    List<TbEmployeeAttendance> dexaGetSummaryAttendanceByRange(BigInteger employeeId, String dateFrom, String dateTo, Pageable pageable);

}
