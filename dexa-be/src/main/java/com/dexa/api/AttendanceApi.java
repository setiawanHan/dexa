package com.dexa.api;

import com.dexa.entities.TbEmployeeAttendance;
import com.dexa.enums.AttendanceStatusEnum;
import com.dexa.models.RestWrapper;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigInteger;
import java.util.List;

@Tag(name = "ATTENDANCE", description = "AttendanceApi.class")
public interface AttendanceApi {

    @GetMapping(
            value = "/api/attendance/hit",
            produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<RestWrapper<TbEmployeeAttendance>> getAttendanceHit(@RequestParam(name = "employeeId") BigInteger employeeId,
                                                                       @RequestParam(name = "attendanceStatus") String attendanceStatus);

    @GetMapping(
            value = "/api/attendance/summary/perDayOne",
            produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<RestWrapper<List<TbEmployeeAttendance>>> getAttendanceSummaryPerDayOne(@RequestParam(name = "employeeId") BigInteger employeeId,
                                                                                          @RequestParam(name = "pageNo") int pageNo,
                                                                                          @RequestParam(name = "pageSize") int pageSize);

    @GetMapping(
            value = "/api/attendance/summary/perRangeDay",
            produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<RestWrapper<List<TbEmployeeAttendance>>> getAttendanceSummaryPerRangeDay(@RequestParam(name = "employeeId") BigInteger employeeId,
                                                                                            @RequestParam(name = "dateFrom") String dateFrom,
                                                                                            @RequestParam(name = "dateTo") String dateTo,
                                                                                            @RequestParam(name = "pageNo") int pageNo,
                                                                                            @RequestParam(name = "pageSize") int pageSize);
}
