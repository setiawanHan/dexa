package com.dexa.api;

import com.dexa.entities.TbRoles;
import com.dexa.models.RestWrapper;
import com.dexa.models.RoleModel;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigInteger;
import java.util.List;

@Tag(name = "ROLES", description = "RolesApi.class")
public interface RolesApi {

    @GetMapping("/api/roles/all")
    ResponseEntity<RestWrapper<List<TbRoles>>> getAllRoles(HttpServletRequest servletRequest, HttpServletResponse servletResponse);

    @GetMapping("/api/roles/byId")
    ResponseEntity<RestWrapper<TbRoles>> getRoleById(@RequestParam(name = "roleId") BigInteger roleId, HttpServletRequest servletRequest, HttpServletResponse servletResponse);

    @GetMapping("/api/roles/byName")
    ResponseEntity<RestWrapper<TbRoles>> getRoleByName(@RequestParam(name = "roleName") String roleName, HttpServletRequest servletRequest, HttpServletResponse servletResponse);

    @PostMapping(
            value = "/api/roles/add",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<RestWrapper<TbRoles>> addRole(@RequestBody RoleModel request, HttpServletRequest servletRequest, HttpServletResponse servletResponse);

}
