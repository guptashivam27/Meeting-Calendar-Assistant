package com.github.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.entity.EmployeeDetails;
import com.github.repository.EmployeeDetailsRepository;

/**
 * The Class EmployeeRegistrationService.
 */
@Service
public class EmployeeRegistrationService {

	/** The employee details repository. */
	@Autowired
	private EmployeeDetailsRepository employeeDetailsRepository;

	/**
	 * Register employee.
	 *
	 * @param name the name
	 * @return the integer
	 */
	public Integer registerEmployee(String name) {

		EmployeeDetails employeeDetails = new EmployeeDetails();

		employeeDetails.setEmployeeName(name);

		Integer employeeId = employeeDetailsRepository.save(employeeDetails).getEmployeeId();

		return employeeId;
	}
}
