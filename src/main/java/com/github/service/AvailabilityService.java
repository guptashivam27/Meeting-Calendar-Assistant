package com.github.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.PriorityQueue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.dto.CalendarEvent;
import com.github.dto.TimeSlot;
import com.github.utility.CalendarEventComparator;
import com.github.utility.MeetingUtility;

/**
 * The Class AvailabilityService.
 */
@Service
public class AvailabilityService {

	@Autowired
	private MeetingUtility meetingUtility;

	/**
	 * Check available slots.
	 *
	 * @param allEmployeeCalendarEvents the all employee calendar events assuming
	 *                                  common time zones
	 * @param meetingDuration           the meeting duration in minutes
	 * @return the list of all the available slots between 00:00 to 23:59
	 */
	public List<CalendarEvent> checkAvailableSlots(List<List<CalendarEvent>> allEmployeeCalendarEvents,
			int meetingDuration) {

		PriorityQueue<CalendarEvent> minHeap = new PriorityQueue<>(new CalendarEventComparator());

		TimeSlot minTimeSlot = new TimeSlot(0, 0), maxTimeSlot = new TimeSlot(23, 59);

		CalendarEvent firstCalendarEvent = new CalendarEvent(minTimeSlot, minTimeSlot),
				lastCalendarEvent = new CalendarEvent(maxTimeSlot, maxTimeSlot);

		minHeap.add(firstCalendarEvent);
		minHeap.add(lastCalendarEvent);

		for (List<CalendarEvent> employeeCalendarEvents : allEmployeeCalendarEvents) {

			for (CalendarEvent event : employeeCalendarEvents) {
				minHeap.add(event);
			}
		}

		List<CalendarEvent> result = new ArrayList<>();

		CalendarEvent previousEvent = minHeap.poll();

		while (!minHeap.isEmpty()) {

			CalendarEvent currentEvent = minHeap.poll();

			Optional<CalendarEvent> commonSlot = getCommonSlot(previousEvent.getEndTime(), currentEvent.getStartTime());

			if (commonSlot.isPresent() && isSlotDurationValid(commonSlot.get(), meetingDuration)) {
				result.add(commonSlot.get());
			}

			previousEvent = currentEvent;
		}

		return result;
	}

	/**
	 * Checks if is slot duration valid.
	 *
	 * @param slot            the slot
	 * @param meetingDuration the meeting duration in minutes
	 * @return true, if is slot duration valid
	 */
	private boolean isSlotDurationValid(CalendarEvent slot, int meetingDuration) {

		int startTimeInMinutes = meetingUtility.getTimeSlotInMinutes(slot.getStartTime());

		int endTimeInMinutes = meetingUtility.getTimeSlotInMinutes(slot.getEndTime());

		int slotDuration = endTimeInMinutes - startTimeInMinutes;

		return ((slotDuration >= meetingDuration) ? true : false);
	}

	/**
	 * Compare time slots.
	 *
	 * @param slotA the slot A
	 * @param slotB the slot B
	 * @return true, if and only if slot B is strictly greater than slot A
	 */
	private boolean compareTimeSlots(TimeSlot slotA, TimeSlot slotB) {

		if ((slotB.getHour() > slotA.getHour())
				|| ((slotB.getHour() == slotA.getHour()) && slotB.getMinute() > slotA.getMinute())) {
			return true;
		}

		return false;
	}

	/**
	 * Gets the common slot.
	 *
	 * @param slotA the slot A
	 * @param slotB the slot B
	 * @return the common slot
	 */
	private Optional<CalendarEvent> getCommonSlot(TimeSlot slotA, TimeSlot slotB) {

		CalendarEvent result = null;

		if (compareTimeSlots(slotA, slotB)) {
			result = new CalendarEvent();

			result.setStartTime(slotA);

			result.setEndTime(slotB);
		}

		return Optional.ofNullable(result);
	}
}
