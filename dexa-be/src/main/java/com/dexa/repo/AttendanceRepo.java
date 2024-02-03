package com.dexa.repo;

import com.dexa.entities.TbEmployeeAttendance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;

@Repository
public interface AttendanceRepo extends JpaRepository<TbEmployeeAttendance, BigInteger> { }
