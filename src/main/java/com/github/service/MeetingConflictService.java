package com.github.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.dto.CalendarEvent;
import com.github.dto.Employee;
import com.github.utility.MeetingUtility;

/**
 * The Class MeetingConflictService.
 */
@Service
public class MeetingConflictService {

	/** The meeting utility. */
	@Autowired
	private MeetingUtility meetingUtility;

	/**
	 * Find meeting conflicts.
	 *
	 * @param employees      the employees
	 * @param meetingRequest the meeting request
	 * @return the list of Employee IDs having meeting conflict
	 */
	public List<Integer> findMeetingConflicts(List<Employee> employees, CalendarEvent meetingRequest) {

		List<Integer> result = new ArrayList<>();

		for (Employee employee : employees) {

			List<CalendarEvent> calendarEvents = employee.getCalendar();

			for (CalendarEvent event : calendarEvents) {
				if (isOverlappingInterval(event, meetingRequest)) {
					result.add(employee.getEmployeeId());
					break;
				}
			}
		}

		return result;

	}

	/**
	 * Checks if the interval of event and meeting request is overlapping interval.
	 *
	 * @param event          the event
	 * @param meetingRequest the meeting request
	 * @return true, if is overlapping interval
	 */
	private boolean isOverlappingInterval(CalendarEvent event, CalendarEvent meetingRequest) {

		int meetingStartTime = meetingUtility.getTimeSlotInMinutes(meetingRequest.getStartTime()),
				meetingEndTime = meetingUtility.getTimeSlotInMinutes(meetingRequest.getEndTime());

		int eventStartTime = meetingUtility.getTimeSlotInMinutes(event.getStartTime()),
				eventEndTime = meetingUtility.getTimeSlotInMinutes(event.getEndTime());

		if ((meetingStartTime <= meetingEndTime) && (eventStartTime <= eventEndTime)
				&& (meetingStartTime >= eventEndTime || meetingEndTime <= eventStartTime)) {
			return false;

		} else {
			return true;
		}
	}

}
