package com.dexa.controller;

import com.dexa.api.AttendanceApi;
import com.dexa.entities.TbEmployeeAttendance;
import com.dexa.enums.AttendanceStatusEnum;
import com.dexa.models.RestWrapper;
import com.dexa.services.AttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.List;

@RestController
public class AttendanceController implements AttendanceApi {

    private final AttendanceService attendanceService;
    @Autowired
    public AttendanceController(AttendanceService attendanceService) {
        this.attendanceService = attendanceService;
    }

    @Override
    public ResponseEntity<RestWrapper<TbEmployeeAttendance>> getAttendanceHit(BigInteger employeeId,
                                                                              AttendanceStatusEnum attendanceStatus) {
        return new RestWrapper<TbEmployeeAttendance>().responseWrapper(
                HttpStatus.CREATED.value(),
                HttpStatus.CREATED,
                "OK",
                attendanceService.attendanceAction(employeeId, attendanceStatus));
    }

    @Override
    public ResponseEntity<RestWrapper<List<TbEmployeeAttendance>>> getAttendanceSummaryPerDayOne(BigInteger employeeId, int pageNo, int pageSize) {
        return new RestWrapper<List<TbEmployeeAttendance>>().responseWrapper(
                HttpStatus.OK.value(),
                HttpStatus.OK,
                "OK",
                attendanceService.getAttendanceSummaryPerDayOne(employeeId, pageNo, pageSize));
    }

    @Override
    public ResponseEntity<RestWrapper<List<TbEmployeeAttendance>>> getAttendanceSummaryPerRangeDay(BigInteger employeeId,
                                                                                                   String dateFrom,
                                                                                                   String dateTo,
                                                                                                   int pageNo,
                                                                                                   int pageSize) {
        return new RestWrapper<List<TbEmployeeAttendance>>().responseWrapper(
                HttpStatus.OK.value(),
                HttpStatus.OK,
                "OK",
                attendanceService.getAttendanceSummaryByRange(employeeId, dateFrom, dateTo, pageNo, pageSize)
        );
    }
}
