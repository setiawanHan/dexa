package com.dexa.api;

import com.dexa.entities.TbEmployee;
import com.dexa.models.EmployeeModel;
import com.dexa.models.RestWrapper;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PutMapping(value = "/api/employee/password/update")
    ResponseEntity<RestWrapper<String>> updateEmployeePassword(@RequestParam(name = "employeeEmail") String employeeEmail,
                                                               @RequestParam(name = "employeeRawPassword") String employeeRawPassword);
}
