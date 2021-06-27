package com.github.dto;

import java.util.List;

import org.hibernate.validator.constraints.Range;

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
public class AvailabilityRequestDTO {

	List<List<CalendarEvent>> employeeCalendar;
	
	@Range(min = 1L, max = 1439L, message = "Meeting duration should be in the range 1 to 1439 minutes")
	int meetingDuration;
}