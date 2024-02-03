package com.dexa.repo;

import com.dexa.entities.TbEmployeeProfiles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.Optional;

@Repository
public interface EmployeeProfilesRepo extends JpaRepository<TbEmployeeProfiles, BigInteger> { }
