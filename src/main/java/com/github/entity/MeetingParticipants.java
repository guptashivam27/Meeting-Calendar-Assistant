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
@Table(name = "MEETING_PARTICIPANTS")
public class MeetingParticipants {

	@Id
	@GeneratedValue
	@Column(name = "SERIAL_NUMBER", nullable = false)
	Integer serialNumber;

	@Column(name = "MEETING_ID", nullable = false)
	Integer meetingId;

	@Column(name = "EMPLOYEE_ID", nullable = false)
	int employeeId;
}
