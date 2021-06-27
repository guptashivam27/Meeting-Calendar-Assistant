package com.github.utility;

import org.springframework.stereotype.Component;

import com.github.dto.TimeSlot;

/**
 * The Class MeetingUtility.
 */
@Component
public class MeetingUtility {

	/**
	 * Gets the time slot in minutes.
	 *
	 * @param A the a
	 * @return the time slot in minutes
	 */
	public int getTimeSlotInMinutes(TimeSlot A) {

		int minutes = A.getHour() * 60 + A.getMinute();

		return minutes;
	}
}
