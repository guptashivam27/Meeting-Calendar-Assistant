package com.github.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.dto.Employee;
import com.github.service.EmployeeRegistrationService;

/**
 * The Class EmployeeRegistrationController.
 */
@RestController
@CrossOrigin
@RequestMapping(value = "/api/v1/employee/register")
public class EmployeeRegistrationController {

	/** The employee registration service. */
	@Autowired
	private EmployeeRegistrationService employeeRegistrationService;

	/**
	 * Register employee.
	 *
	 * @param employeeName the employee name
	 * @return the response entity
	 */
	@PostMapping()
	public ResponseEntity<?> registerEmployee(@RequestParam(name = "employeeName") String employeeName) {

		try {
			Integer employeeId = employeeRegistrationService.registerEmployee(employeeName);
			
			Employee response = new Employee();
			response.setEmployeeId(employeeId);
			
			return new ResponseEntity<>(response, HttpStatus.CREATED);

		} catch (Exception e) {

			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
}