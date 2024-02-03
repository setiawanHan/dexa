package com.dexa.services;

import com.dexa.entities.TbRoles;
import com.dexa.exception.DexaException;
import com.dexa.models.RoleModel;
import com.dexa.repo.RolesRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

@Service
public class RolesService {

    private final RolesRepo rolesRepo;
    @Autowired
    public RolesService(RolesRepo rolesRepo) {
        this.rolesRepo = rolesRepo;
    }

    public List<TbRoles> getAllRoles() {
        return rolesRepo.findAll();
    }

    public TbRoles getRoleById(BigInteger roleId) {
        Optional<TbRoles> roleData = rolesRepo.findById(roleId);
        if (!roleData.isPresent())
            throw new DexaException("Role not found. (role_id = " + roleId + ")");
        return roleData.get();
    }

    public TbRoles getRoleByName(String roleName) {
        Optional<TbRoles> roleData = rolesRepo.findByRoleName(roleName);
        if (!roleData.isPresent())
            throw new DexaException("Role not found. (role_name = " + roleName + ")");
        return roleData.get();
    }

    public TbRoles addRole(RoleModel roleModel) {
        TbRoles roles = TbRoles.builder()
                .roleName(roleModel.getRoleName())
                .build();
        rolesRepo.save(roles);
        return roles;
    }

}
