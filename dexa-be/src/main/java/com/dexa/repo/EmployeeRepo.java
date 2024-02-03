package com.dexa.repo;

import com.dexa.entities.TbEmployee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepo extends JpaRepository<TbEmployee, BigInteger> {
    Optional<TbEmployee> findByEmployeeEmail(String employeeEmail);
}
