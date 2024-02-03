package com.dexa.controller;

import com.dexa.api.EmployeeApi;
import com.dexa.entities.TbEmployee;
import com.dexa.entities.TbEmployeeProfiles;
import com.dexa.models.EmployeeModel;
import com.dexa.models.RestWrapper;
import com.dexa.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigInteger;

@RestController
public class EmployeeController implements EmployeeApi {

    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Override
    public ResponseEntity<RestWrapper<TbEmployee>> addNewEmployee(EmployeeModel request) {
        return new RestWrapper<TbEmployee>()
                .responseWrapper(
                        HttpStatus.CREATED.value(),
                        HttpStatus.CREATED,
                        "OK",
                        employeeService.addNewEmployee(request));
    }

    @Override
    public ResponseEntity<RestWrapper<TbEmployee>> getEmployeeByEmail(String employeeEmail) {
        return new RestWrapper<TbEmployee>()
                .responseWrapper(
                        HttpStatus.OK.value(),
                        HttpStatus.OK,
                        "OK",
                        employeeService.getEmployeeByEmail(employeeEmail));
    }

    @Override
    public ResponseEntity<RestWrapper<String>> updateEmployeePassword(String employeeEmail,
                                                                      String employeeRawPassword) {
        return new RestWrapper<String>()
                .responseWrapper(
                        HttpStatus.OK.value(),
                        HttpStatus.OK,
                        "OK",
                        employeeService.updateEmployeePassword(employeeEmail, employeeRawPassword));
    }

    @Override
    public ResponseEntity<RestWrapper<TbEmployeeProfiles>> getEmployeeProfile(BigInteger employeeId) {
        return new RestWrapper<TbEmployeeProfiles>().responseWrapper(
                HttpStatus.OK.value(),
                HttpStatus.OK,
                "OK",
                employeeService.getEmployeeProfileByEmployeeId(employeeId)
        );
    }
}
