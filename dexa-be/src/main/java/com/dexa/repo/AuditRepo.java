package com.dexa.repo;

import com.dexa.entities.TbAuditLogin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.Optional;

@Repository
public interface AuditRepo extends JpaRepository<TbAuditLogin, BigInteger> {
    Optional<TbAuditLogin> findByEmployeeEmail(String employeeEmail);
    void deleteByEmployeeEmail(String employeeEmail);
}
