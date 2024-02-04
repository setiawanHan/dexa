package com.dexa.services;

import com.dexa.entities.TbEmployeeAttendance;
import com.dexa.enums.AttendanceStatusEnum;
import com.dexa.exception.DexaException;
import com.dexa.repo.AttendanceRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Service
public class AttendanceService {

    private final AttendanceRepo attendanceRepo;
    private final EmployeeService employeeService;
    @Autowired
    public AttendanceService(AttendanceRepo attendanceRepo,
                             EmployeeService employeeService) {
        this.attendanceRepo = attendanceRepo;
        this.employeeService = employeeService;
    }

    public TbEmployeeAttendance attendanceAction(BigInteger employeeId,
                                                 String attendanceStatus) {

        int countAttendancePerDayMasukPulang =
                attendanceRepo.dexaCountAttendancePerDayByStatus(employeeId, attendanceStatus);
        if (attendanceStatus.equals("PULANG")) {
            int countAttendanceMasuk =
                    attendanceRepo.dexaCountAttendancePerDayByStatus(employeeId, "MASUK");
            if (countAttendanceMasuk == 0)
                throw new DexaException("Silahkan Pilih MASUK terlebih dahulu sebelum memilih PULANG.");
        }

        if (countAttendancePerDayMasukPulang >= 1)
            throw new DexaException("Anda sudah melakukan Hit Attendance " + attendanceStatus + ".");

        int countAttendancePerDay = attendanceRepo.dexaCountAttendancePerDay(employeeId);
        if (countAttendancePerDay >= 2)
            throw new DexaException("Cukup untuk hari ini. Silahkan lakukan untuk besok. Terima Kasih.");

        TbEmployeeAttendance dataPreparation = TbEmployeeAttendance.builder()
                .dateAndTime(LocalDateTime.now(ZoneId.of("Asia/Jakarta")))
                .status(attendanceStatus)
                .employee(employeeService.getEmployeeById(employeeId))
                .build();
        attendanceRepo.save(dataPreparation);
        return dataPreparation;
    }

    public List<TbEmployeeAttendance> getAttendanceSummaryPerDayOne(BigInteger employeeId, int pageNo, int pageSize) {
        return attendanceRepo.dexaGetSummaryAttendance(
                employeeId,
                PageRequest.of(pageNo, pageSize));
    }

    public List<TbEmployeeAttendance> getAttendanceSummaryByRange(BigInteger employeeId,
                                                                  String dateFrom,
                                                                  String dateTo,
                                                                  int pageNo,
                                                                  int pageSize) {
        return attendanceRepo.dexaGetSummaryAttendanceByRange(
                employeeId,
                dateFrom + " 00:00:00",
                dateTo + " 23:59:59",
                PageRequest.of(pageNo, pageSize));
    }

}
