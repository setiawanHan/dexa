package com.dexa.services;

import com.dexa.entities.TbEmployee;
import com.dexa.entities.TbRoles;
import com.dexa.exception.DexaException;
import com.dexa.models.EmployeeModel;
import com.dexa.repo.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
public class EmployeeService {
    // add new employee
    // get employee by email
    // update employee password

    // update employee to inactive (x)
    // get employees by role_id (x)
    // get employee active / inactive (x)

    /* ----- employee profiles ----- */
    // get employee profiles
    // update employee profile phone number

    private final EmployeeRepo employeeRepo;

    private final PasswordService passwordService;
    private final RolesService rolesService;

    @Autowired
    public EmployeeService(EmployeeRepo employeeRepo,
                           PasswordService passwordService,
                           RolesService rolesService) {
        this.employeeRepo = employeeRepo;
        this.rolesService = rolesService;
        this.passwordService = passwordService;
    }


    /* ---------- EMPLOYEE ---------- */
    public TbEmployee addNewEmployee(EmployeeModel request) {

        TbRoles roles = rolesService.getRoleById(request.getRoleModel().getRoleId());
        if (Objects.isNull(roles))
            throw new DexaException("Role not found. (role_id = " + request.getRoleModel().getRoleId() + ")");

        TbEmployee dataPreparation = TbEmployee.builder()
                .employeeIsActive(true)
                .employeeEmail(request.getEmployeeEmail())
                .employeePassword(passwordService.encryptPassword(request.getEmployeePassword()))
                .role(roles)
                .build();

        employeeRepo.save(dataPreparation);
        return dataPreparation;
    }

    public TbEmployee getEmployeeByEmail(String employeeEmail) {
        Optional<TbEmployee> employee = employeeRepo.findByEmployeeEmail(employeeEmail);
        if (!employee.isPresent())
            throw new DexaException("Employee not found. (employee_email = " + employeeEmail + ")");
        return employee.get();
    }

    public String updateEmployeePassword(String employeeEmail, String newEmployeePassword) {
        TbEmployee employee = this.getEmployeeByEmail(employeeEmail);
        employee.setEmployeePassword(passwordService.encryptPassword(newEmployeePassword));
        employeeRepo.save(employee);
        return employee.getEmployeeEmail();
    }

    /* ---------- EMPLOYEE PROFILES ---------- */
}
