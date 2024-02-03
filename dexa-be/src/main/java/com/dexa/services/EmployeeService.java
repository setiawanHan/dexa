package com.dexa.services;

import com.dexa.entities.TbEmployee;
import com.dexa.entities.TbEmployeeProfiles;
import com.dexa.exception.DexaException;
import com.dexa.models.EmployeeModel;
import com.dexa.repo.EmployeeProfilesRepo;
import com.dexa.repo.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Objects;
import java.util.Optional;

@Service
public class EmployeeService {

    // update employee to inactive (x)
    // get employees by role_id (x)
    // get employee active / inactive (x)

    private final EmployeeRepo employeeRepo;
    private final EmployeeProfilesRepo employeeProfilesRepo;

    private final PasswordService passwordService;
    private final RolesService rolesService;

    @Autowired
    public EmployeeService(EmployeeRepo employeeRepo,
                           EmployeeProfilesRepo employeeProfilesRepo,
                           PasswordService passwordService,
                           RolesService rolesService) {
        this.employeeRepo = employeeRepo;
        this.employeeProfilesRepo = employeeProfilesRepo;
        this.rolesService = rolesService;
        this.passwordService = passwordService;
    }

    /* ---------- EMPLOYEE ---------- */
    public TbEmployee addNewEmployee(EmployeeModel request) {

        if (Objects.isNull(request.getEmployeeProfileModel()))
            throw new DexaException("Employee Profile couldn't be null.");
        if (Objects.isNull(request.getEmployeeProfileModel().getEmployeeName()))
            throw new DexaException("Employee Name couldn't be null.");
        if (Objects.isNull(request.getEmployeeProfileModel().getEmployeePosition()))
            throw new DexaException("Employee Position couldn't be null.");

        TbEmployee dataPreparation = TbEmployee.builder()
                .employeeIsActive(true)
                .employeeEmail(request.getEmployeeEmail())
                .employeePassword(passwordService.encryptPassword(request.getEmployeePassword()))
                .employeeProfiles(
                        TbEmployeeProfiles.builder()
                                .employeeName(request.getEmployeeEmail())
                                .employeePosition(request.getEmployeeProfileModel().getEmployeePosition())
                                .employeePhone(request.getEmployeeProfileModel().getEmployeePhone())
                                .updateDate(LocalDateTime.now(ZoneId.of("Asia/Jakarta")))
                                .build()
                )
                .role(rolesService.getRoleById(request.getRoleModel().getRoleId()))
                .build();

        employeeRepo.save(dataPreparation);
        return dataPreparation;
    }

    public TbEmployee getEmployeeById(BigInteger employeeId) {
        Optional<TbEmployee> employee = employeeRepo.findById(employeeId);
        if (!employee.isPresent())
            throw new DexaException("Employee not found. (employee_id = " + employeeId + ")");
        return employee.get();
    }

    public TbEmployee getEmployeeByEmail(String employeeEmail) {
        Optional<TbEmployee> employee = employeeRepo.findByEmployeeEmail(employeeEmail);
        if (!employee.isPresent())
            throw new DexaException("Employee not found. (employee_email = " + employeeEmail + ")");
        return employee.get();
    }

    public String updateEmployeePassword(BigInteger employeeId, String newEmployeePassword) {
        TbEmployee employee = this.getEmployeeById(employeeId);
        employee.setEmployeePassword(passwordService.encryptPassword(newEmployeePassword));
        employeeRepo.save(employee);
        return employee.getEmployeeEmail();
    }

    /* ---------- EMPLOYEE PROFILES ---------- */
    public TbEmployeeProfiles getEmployeeProfileByEmployeeId(BigInteger employeeId) {
        return this.getEmployeeById(employeeId).getEmployeeProfiles();
    }

    public String updateEmployeePhone(BigInteger employeeId, String newPhoneNumber) {
        TbEmployeeProfiles employeeProfiles = this.getEmployeeProfileByEmployeeId(employeeId);
        employeeProfiles.setEmployeePhone(newPhoneNumber);
        employeeProfilesRepo.save(employeeProfiles);
        return employeeProfiles.getEmployeePhone();
    }

    /* ---------- MIX EMPLOYEE & EMPLOYEE PROFILES */
//    public String updateEmployeePhoneAndPasswordByEmployeeId(BigInteger employeeId,
//                                                             String )

}
