package com.dexa.api;

import com.dexa.entities.TbAuditLogin;
import com.dexa.models.AuthModel;
import com.dexa.models.RestWrapper;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Tag(name = "AUTHENTICATION", description = "AuthApi.class")
public interface AuthApi {

    @GetMapping(
            value = "/api/auth/login",
            produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<RestWrapper<AuthModel>> login(@RequestParam(name = "employeeEmail") String employeeEmail,
                                                 @RequestParam(name = "employeePassword") String employeePassword,
                                                 HttpServletRequest servletRequest,
                                                 HttpServletResponse servletResponse);

    @GetMapping(value = "/api/auth/logout")
    ResponseEntity<RestWrapper<String>> logout(@RequestParam(name = "employeeEmail") String employeeEmail);

    @GetMapping(
            value = "/api/auth/audit/byEmployeeEmail",
            produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<RestWrapper<TbAuditLogin>> getAuditLoginByEmployeeEmail(@RequestParam(name = "employeeEmail") String employeeEmail);

    @GetMapping(value = "/api/auth/audit/employeeIsActive")
    ResponseEntity<RestWrapper<Boolean>> employeeIsActive(@RequestParam(name = "employeeEmail") String employeeEmail);

}
