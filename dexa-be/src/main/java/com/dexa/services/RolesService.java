package com.dexa.services;

import com.dexa.entities.TbRoles;
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
        return roleData.orElse(null);
    }

    public TbRoles getRoleByName(String roleName) {
        Optional<TbRoles> roleData = rolesRepo.findByRoleName(roleName);
        return roleData.orElse(null);
    }

    public TbRoles addRole(RoleModel roleModel) {
        TbRoles roles = TbRoles.builder()
                .roleName(roleModel.getRoleName())
                .build();
        rolesRepo.save(roles);
        return roles;
    }

}
