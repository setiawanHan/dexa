package com.dexa.repo;

import com.dexa.entities.TbRoles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.Optional;

@Repository
public interface RolesRepo extends JpaRepository<TbRoles, BigInteger> {
    Optional<TbRoles> findByRoleName(String roleName);
}
