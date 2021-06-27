package com.github.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doReturn;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import com.github.dto.CalendarEvent;
import com.github.dto.Employee;
import com.github.dto.TimeSlot;
import com.github.utility.MeetingUtility;

/**
 * The Class MeetingConflictServiceTest.
 */
@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class MeetingConflictServiceTest {

	/** The meeting utility. */
	@Mock
	private MeetingUtility meetingUtility;

	/** The meeting conflict service. */
	@InjectMocks
	private MeetingConflictService meetingConflictService;

	/**
	 * Test find meeting conflicts.
	 */
	@Test
	void testFindMeetingConflicts() {
		// Employee 1
		Employee employee1 = new Employee();
		employee1.setEmployeeId(1);
		employee1.setEmployeeName("Shivam Gupta");

		List<CalendarEvent> calendarEventsEmployee1 = new ArrayList<>();

		TimeSlot slot1 = new TimeSlot(9, 0);
		doReturn(540).when(meetingUtility).getTimeSlotInMinutes(slot1);

		TimeSlot slot2 = new TimeSlot(9, 30);
		doReturn(570).when(meetingUtility).getTimeSlotInMinutes(slot2);

		CalendarEvent calendarEvent1 = new CalendarEvent(slot1, slot2);

		TimeSlot slot3 = new TimeSlot(10, 0);
		doReturn(600).when(meetingUtility).getTimeSlotInMinutes(slot3);

		TimeSlot slot4 = new TimeSlot(11, 45);
		doReturn(705).when(meetingUtility).getTimeSlotInMinutes(slot4);

		CalendarEvent calendarEvent2 = new CalendarEvent(slot3, slot4);

		calendarEventsEmployee1.add(calendarEvent1);
		calendarEventsEmployee1.add(calendarEvent2);

		employee1.setCalendar(calendarEventsEmployee1);

		// Employee 2
		Employee employee2 = new Employee();
		employee2.setEmployeeId(2);
		employee2.setEmployeeName("Rahul Saini");

		List<CalendarEvent> calendarEventsEmployee2 = new ArrayList<>();

		TimeSlot slot5 = new TimeSlot(9, 0);
		doReturn(540).when(meetingUtility).getTimeSlotInMinutes(slot5);

		TimeSlot slot6 = new TimeSlot(10, 0);
		doReturn(600).when(meetingUtility).getTimeSlotInMinutes(slot6);

		CalendarEvent calendarEvent3 = new CalendarEvent(slot5, slot6);

		TimeSlot slot7 = new TimeSlot(11, 30);
		doReturn(690).when(meetingUtility).getTimeSlotInMinutes(slot7);

		TimeSlot slot8 = new TimeSlot(12, 0);
		doReturn(720).when(meetingUtility).getTimeSlotInMinutes(slot8);

		CalendarEvent calendarEvent4 = new CalendarEvent(slot7, slot8);

		calendarEventsEmployee2.add(calendarEvent3);
		calendarEventsEmployee2.add(calendarEvent4);

		employee2.setCalendar(calendarEventsEmployee2);

		// Employee 3
		Employee employee3 = new Employee();
		employee3.setEmployeeId(3);
		employee3.setEmployeeName("Ramesh Singh");

		List<CalendarEvent> calendarEventsEmployee3 = new ArrayList<>();

		TimeSlot slot9 = new TimeSlot(8, 30);
		doReturn(510).when(meetingUtility).getTimeSlotInMinutes(slot9);

		TimeSlot slot10 = new TimeSlot(9, 0);
		doReturn(540).when(meetingUtility).getTimeSlotInMinutes(slot10);

		CalendarEvent calendarEvent5 = new CalendarEvent(slot9, slot10);

		TimeSlot slot11 = new TimeSlot(9, 0);
		doReturn(540).when(meetingUtility).getTimeSlotInMinutes(slot11);

		TimeSlot slot12 = new TimeSlot(12, 30);
		doReturn(750).when(meetingUtility).getTimeSlotInMinutes(slot12);

		CalendarEvent calendarEvent6 = new CalendarEvent(slot11, slot12);

		calendarEventsEmployee3.add(calendarEvent5);
		calendarEventsEmployee3.add(calendarEvent6);

		employee3.setCalendar(calendarEventsEmployee3);

		// Employee List
		List<Employee> allEmployees = new ArrayList<>();
		allEmployees.add(employee1);
		allEmployees.add(employee2);
		allEmployees.add(employee3);

		// Meeting Request Details
		TimeSlot slot13 = new TimeSlot(10, 30);
		doReturn(630).when(meetingUtility).getTimeSlotInMinutes(slot13);

		TimeSlot slot14 = new TimeSlot(11, 0);
		doReturn(660).when(meetingUtility).getTimeSlotInMinutes(slot14);

		CalendarEvent meetingRequest = new CalendarEvent(slot13, slot14);

		// Expected Response
		List<Integer> expectedResponse = new ArrayList<>();
		expectedResponse.add(1);
		expectedResponse.add(3);

		assertEquals(expectedResponse, meetingConflictService.findMeetingConflicts(allEmployees, meetingRequest));
	}

}
