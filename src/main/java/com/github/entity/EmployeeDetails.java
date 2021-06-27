package com.github.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "EMPLOYEE_DETAILS")
public class EmployeeDetails {
	
	@Id
    @GeneratedValue
    @Column(name = "EMPLOYEE_ID", nullable = false)
	Integer employeeId;
	
	@Column(name = "EMPLOYEE_NAME", nullable = false)
	String employeeName;

}