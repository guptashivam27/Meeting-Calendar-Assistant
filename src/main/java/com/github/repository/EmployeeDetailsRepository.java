package com.github.repository;

import org.springframework.data.repository.CrudRepository;

import com.github.entity.EmployeeDetails;

/**
 * The Interface EmployeeDetailsRepository.
 */
public interface EmployeeDetailsRepository extends CrudRepository<EmployeeDetails, Integer> {

}