package com.github.service;

import static org.junit.jupiter.api.Assertions.*;
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
import com.github.dto.TimeSlot;
import com.github.utility.MeetingUtility;

/**
 * The Class AvailabilityServiceTest.
 */
@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class AvailabilityServiceTest {

	/** The meeting utility. */
	@Mock
	private MeetingUtility meetingUtility;

	/** The availability service. */
	@InjectMocks
	private AvailabilityService availabilityService;

	/**
	 * Test check available slots.
	 */
	@Test
	void testCheckAvailableSlots() {
		// Employee 1 - Calendar Events
		List<CalendarEvent> calendarEventEmployee1 = new ArrayList<>();

		TimeSlot startTime1 = new TimeSlot(9, 0);

		int startTime1InMinutes = 540;
		doReturn(startTime1InMinutes).when(meetingUtility).getTimeSlotInMinutes(startTime1);

		TimeSlot endTime1 = new TimeSlot(9, 30);

		int endTime1InMinutes = 570;
		doReturn(endTime1InMinutes).when(meetingUtility).getTimeSlotInMinutes(endTime1);

		CalendarEvent event1 = new CalendarEvent(startTime1, endTime1);

		TimeSlot startTime2 = new TimeSlot(10, 0);

		int startTime2InMinutes = 600;
		doReturn(startTime2InMinutes).when(meetingUtility).getTimeSlotInMinutes(startTime2);

		TimeSlot endTime2 = new TimeSlot(10, 30);

		int endTime2InMinutes = 630;
		doReturn(endTime2InMinutes).when(meetingUtility).getTimeSlotInMinutes(endTime2);

		CalendarEvent event2 = new CalendarEvent(startTime2, endTime2);

		calendarEventEmployee1.add(event1);
		calendarEventEmployee1.add(event2);

		// Employee 2 - Calendar Events
		List<CalendarEvent> calendarEventEmployee2 = new ArrayList<>();

		TimeSlot startTime3 = new TimeSlot(9, 0);

		int startTime3InMinutes = 540;
		doReturn(startTime3InMinutes).when(meetingUtility).getTimeSlotInMinutes(startTime3);

		TimeSlot endTime3 = new TimeSlot(9, 45);

		int endTime3InMinutes = 585;
		doReturn(endTime3InMinutes).when(meetingUtility).getTimeSlotInMinutes(endTime3);

		CalendarEvent event3 = new CalendarEvent(startTime3, endTime3);

		TimeSlot startTime4 = new TimeSlot(10, 15);

		int startTime4InMinutes = 615;
		doReturn(startTime4InMinutes).when(meetingUtility).getTimeSlotInMinutes(startTime4);

		TimeSlot endTime4 = new TimeSlot(11, 10);

		int endTime4InMinutes = 670;
		doReturn(endTime4InMinutes).when(meetingUtility).getTimeSlotInMinutes(endTime4);

		CalendarEvent event4 = new CalendarEvent(startTime4, endTime4);

		calendarEventEmployee2.add(event3);
		calendarEventEmployee2.add(event4);

		// All Employee Calendars
		List<List<CalendarEvent>> allEmployeeCalendarEvents = new ArrayList<>();
		allEmployeeCalendarEvents.add(calendarEventEmployee1);
		allEmployeeCalendarEvents.add(calendarEventEmployee2);

		// Meeting Duration
		int meetingDuration = 45;

		// Expected Response
		List<CalendarEvent> expectedResponse = new ArrayList<>();

		TimeSlot startTime5 = new TimeSlot(0, 0);

		int startTime5InMinutes = 0;
		doReturn(startTime5InMinutes).when(meetingUtility).getTimeSlotInMinutes(startTime5);

		TimeSlot endTime5 = new TimeSlot(9, 0);
		CalendarEvent event5 = new CalendarEvent(startTime5, endTime5);

		TimeSlot startTime6 = new TimeSlot(11, 10);

		TimeSlot endTime6 = new TimeSlot(23, 59);

		int endTime6InMinutes = 1439;
		doReturn(endTime6InMinutes).when(meetingUtility).getTimeSlotInMinutes(endTime6);

		CalendarEvent event6 = new CalendarEvent(startTime6, endTime6);

		expectedResponse.add(event5);
		expectedResponse.add(event6);

		assertEquals(expectedResponse,
				availabilityService.checkAvailableSlots(allEmployeeCalendarEvents, meetingDuration));

	}

}
