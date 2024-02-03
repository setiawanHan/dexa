package com.dexa.controller;

import com.dexa.api.RolesApi;
import com.dexa.entities.TbRoles;
import com.dexa.models.RoleModel;
import com.dexa.services.RolesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

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
    public ResponseEntity<List<TbRoles>> getAllRoles() {
        return new ResponseEntity<>(rolesService.getAllRoles(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<TbRoles> getRoleById(BigInteger roleId) {
        return new ResponseEntity<>(rolesService.getRoleById(roleId), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<TbRoles> getRoleByName(String roleName) {
        return new ResponseEntity<>(rolesService.getRoleByName(roleName), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<TbRoles> addRole(RoleModel request) {
        return new ResponseEntity<>(rolesService.addRole(request), HttpStatus.CREATED);
    }
}
