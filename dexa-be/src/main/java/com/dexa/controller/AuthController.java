package com.dexa.controller;

import com.dexa.api.AuthApi;
import com.dexa.entities.TbAuditLogin;
import com.dexa.models.AuthLoginModel;
import com.dexa.models.AuthModel;
import com.dexa.models.RestWrapper;
import com.dexa.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
public class AuthController implements AuthApi {

    private final AuthService authService;
    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @Override
    public ResponseEntity<RestWrapper<AuthModel>> login(String employeeEmail,
                                                        String employeePassword,
                                                        HttpServletRequest servletRequest,
                                                        HttpServletResponse servletResponse) {
        return new RestWrapper<AuthModel>().responseWrapper(
                HttpStatus.OK.value(),
                HttpStatus.OK,
                "OK",
                authService.authenticate(
                        AuthLoginModel.builder()
                                .employeeEmail(employeeEmail)
                                .rawPassword(employeePassword)
                                .build(),
                        servletRequest,
                        servletResponse));
    }

    @Override
    public ResponseEntity<RestWrapper<String>> logout(String employeeEmail) {
        return new RestWrapper<String>().responseWrapper(
                HttpStatus.NO_CONTENT.value(),
                HttpStatus.NO_CONTENT,
                "OK",
                authService.logout(employeeEmail));
    }

    @Override
    public ResponseEntity<RestWrapper<TbAuditLogin>> getAuditLoginByEmployeeEmail(String employeeEmail) {
        return new RestWrapper<TbAuditLogin>().responseWrapper(
                HttpStatus.OK.value(),
                HttpStatus.OK,
                "OK",
                authService.getAuditLoginByEmployeeEmail(employeeEmail));
    }

    @Override
    public ResponseEntity<RestWrapper<Boolean>> employeeIsActive(String employeeEmail) {
        return new RestWrapper<Boolean>().responseWrapper(
                HttpStatus.OK.value(),
                HttpStatus.OK,
                "OK",
                authService.employeeSessionIsActive(employeeEmail));
    }
}
