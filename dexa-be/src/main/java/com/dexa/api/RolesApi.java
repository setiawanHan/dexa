package com.dexa.api;

import com.dexa.entities.TbRoles;
import com.dexa.models.RoleModel;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigInteger;
import java.util.List;

@Tag(name = "ROLES", description = "RolesApi.class")
public interface RolesApi {
    @GetMapping("/api/roles/all")
    ResponseEntity<List<TbRoles>> getAllRoles();
    @GetMapping("/api/roles/byId")
    ResponseEntity<TbRoles> getRoleById(@RequestParam(name = "roleId") BigInteger roleId);
    @GetMapping("/api/roles/byName")
    ResponseEntity<TbRoles> getRoleByName(@RequestParam(name = "roleName") String roleName);
    @PostMapping(
            value = "/api/roles/add",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<TbRoles> addRole(@RequestBody RoleModel request);

}
