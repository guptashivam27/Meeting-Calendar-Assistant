package com.github.utility;

import java.util.Comparator;

import com.github.dto.CalendarEvent;
import com.github.dto.TimeSlot;

/**
 * The Class CalendarEventComparator.
 */
public class CalendarEventComparator implements Comparator<CalendarEvent> {

	/**
	 * Compare.
	 *
	 * @param eventA the event A
	 * @param eventB the event B
	 * @return the integer value
	 */
	@Override
	public int compare(CalendarEvent eventA, CalendarEvent eventB) {

		TimeSlot startTimeEventA = eventA.getStartTime(), startTimeEventB = eventB.getStartTime(),
				endTimeEventA = eventA.getEndTime(), endTimeEventB = eventB.getEndTime();

		if (eventA.equals(eventB)) {
			/* Case 1: When both the events starts and end at the same time */
			return 0;

		} else if ((startTimeEventA.equals(startTimeEventB)) && ((endTimeEventA.getHour() < endTimeEventB.getHour())
				|| (endTimeEventA.getHour() == endTimeEventB.getHour()
						&& endTimeEventA.getMinute() < endTimeEventB.getMinute()))) {
			/*
			 * Case 2: When both the events starts at same time, but Event A ends before
			 * Event B
			 */

			return -1;

		} else if ((startTimeEventA.getHour() < startTimeEventB.getHour())
				|| (startTimeEventA.getHour() == startTimeEventB.getHour()
						&& startTimeEventA.getMinute() < startTimeEventB.getMinute())) {
			/*
			 * Case 3: When Event A starts before Event B
			 */

			return -1;

		} else {
			/*
			 * Case 4: When Event B starts before Event A or when both events starts at same
			 * time but Event B ends before Event A
			 */

			return 1;
		}
	}

}
