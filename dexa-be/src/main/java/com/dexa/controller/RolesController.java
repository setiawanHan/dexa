package com.dexa.controller;

import com.dexa.api.RolesApi;
import com.dexa.entities.TbRoles;
import com.dexa.models.RestWrapper;
import com.dexa.models.RoleModel;
import com.dexa.services.RolesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigInteger;
import java.util.List;

@RestController
public class RolesController implements RolesApi {

    private final RolesService rolesService;
    @Autowired
    public RolesController(RolesService rolesService) {
        this.rolesService = rolesService;
    }

    @Override
    public ResponseEntity<RestWrapper<List<TbRoles>>> getAllRoles(HttpServletRequest servletRequest, HttpServletResponse servletResponse) {
        return new RestWrapper<List<TbRoles>>().responseWrapper(
                HttpStatus.OK.value(),
                HttpStatus.OK,
                "OK",
                rolesService.getAllRoles());
    }

    @Override
    public ResponseEntity<RestWrapper<TbRoles>> getRoleById(BigInteger roleId, HttpServletRequest servletRequest, HttpServletResponse servletResponse) {
        return new RestWrapper<TbRoles>().responseWrapper(
                HttpStatus.OK.value(),
                HttpStatus.OK,
                "OK",
                rolesService.getRoleById(roleId));
    }

    @Override
    public ResponseEntity<RestWrapper<TbRoles>> getRoleByName(String roleName, HttpServletRequest servletRequest, HttpServletResponse servletResponse) {
        return new RestWrapper<TbRoles>().responseWrapper(
                HttpStatus.OK.value(),
                HttpStatus.OK,
                "OK",
                rolesService.getRoleByName(roleName));
    }

    @Override
    public ResponseEntity<RestWrapper<TbRoles>> addRole(RoleModel request, HttpServletRequest servletRequest, HttpServletResponse servletResponse) {
        return new RestWrapper<TbRoles>().responseWrapper(
                HttpStatus.CREATED.value(),
                HttpStatus.CREATED,
                "OK",
                rolesService.addRole(request)
        );
    }
}
