package com.github.utility;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import com.github.dto.TimeSlot;

/**
 * The Class MeetingUtilityTest.
 */
@ExtendWith(MockitoExtension.class)
class MeetingUtilityTest {

	/** The meeting utility. */
	@InjectMocks
	private MeetingUtility meetingUtility;

	/**
	 * Test get time slot in minutes.
	 */
	@Test
	void testGetTimeSlotInMinutes() {

		TimeSlot slot = new TimeSlot();
		slot.setHour(15);
		slot.setMinute(45);

		int expectedResult = 945;

		assertEquals(expectedResult, meetingUtility.getTimeSlotInMinutes(slot));
	}

}
