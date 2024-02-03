package com.dexa.api;

import com.dexa.entities.TbEmployee;
import com.dexa.entities.TbEmployeeProfiles;
import com.dexa.models.EmployeeModel;
import com.dexa.models.RestWrapper;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;

@Tag(name = "EMPLOYEE", description = "EmployeeApi.class")
public interface EmployeeApi {

    @PostMapping(
            value = "/api/employee/add",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<RestWrapper<TbEmployee>> addNewEmployee(@RequestBody EmployeeModel request);

    @GetMapping(
            value = "/api/employee/byEmail",
            produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<RestWrapper<TbEmployee>> getEmployeeByEmail(@RequestParam(name = "employeeEmail") String employeeEmail);

    @GetMapping(value = "/api/employee/profile")
    ResponseEntity<RestWrapper<TbEmployeeProfiles>> getEmployeeProfile(@RequestParam(name = "employeeId") BigInteger employeeId);

    @PutMapping(value = "/api/employee/passwordAndPhone/update")
    ResponseEntity<RestWrapper<String>> updatePasswordAndPhone(@RequestParam(name = "employeeId") BigInteger employeeId,
                                                               @RequestParam(name = "employeeNewPassword") String employeeNewPassword,
                                                               @RequestParam(name = "employeeNewPhone") String employeeNewPhone);
}
