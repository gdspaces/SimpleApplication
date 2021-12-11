package com.simple.application.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.simple.application.model.EmployeeInfo;

public interface EmployeeInfoRepository extends JpaRepository<EmployeeInfo, Integer> {
}