package com.github.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.dto.BookMeetingRequestDTO;
import com.github.dto.BookMeetingResponseDTO;
import com.github.dto.CalendarEvent;
import com.github.entity.MeetingDetails;
import com.github.entity.MeetingParticipants;
import com.github.repository.EmployeeDetailsRepository;
import com.github.repository.MeetingDetailsRepository;
import com.github.repository.MeetingParticipantsRepository;
import com.github.utility.MeetingUtility;

/**
 * The Class BookMeetingService.
 */
@Service
public class BookMeetingService {

	/** The meeting utility. */
	@Autowired
	private MeetingUtility meetingUtility;

	/** The meeting details repository. */
	@Autowired
	private MeetingDetailsRepository meetingDetailsRepository;

	/** The meeting participants repository. */
	@Autowired
	private MeetingParticipantsRepository meetingParticipantsRepository;

	/** The employee details repository. */
	@Autowired
	private EmployeeDetailsRepository employeeDetailsRepository;

	/**
	 * Book meeting.
	 *
	 * @param meetingInfo the meeting info
	 * @return the book meeting response DTO
	 */
	public BookMeetingResponseDTO bookMeeting(BookMeetingRequestDTO meetingInfo) {

		BookMeetingResponseDTO response = new BookMeetingResponseDTO();

		try {
			CalendarEvent meetingSchedule = meetingInfo.getSchedule();

			int startTime = meetingUtility.getTimeSlotInMinutes(meetingSchedule.getStartTime()),
					endTime = meetingUtility.getTimeSlotInMinutes(meetingSchedule.getEndTime());

			MeetingDetails meetingDetails = new MeetingDetails();

			meetingDetails.setMeetingTitle(meetingInfo.getTitle());

			meetingDetails.setDate(meetingInfo.getDate());

			meetingDetails.setStartTime(startTime);

			meetingDetails.setEndTime(endTime);

			Integer meetingId = meetingDetailsRepository.save(meetingDetails).getMeetingId();

			List<Integer> meetingParticipants = meetingInfo.getEmployeeId();

			for (Integer employeeId : meetingParticipants) {
				if (employeeDetailsRepository.findById(employeeId).isPresent()) {

					MeetingParticipants meetingParticipant = new MeetingParticipants();

					meetingParticipant.setMeetingId(meetingId);

					meetingParticipant.setEmployeeId(employeeId);

					meetingParticipantsRepository.save(meetingParticipant);
				}
			}

			response.setMeetingId(meetingId);
			return response;

		} catch (Exception e) {
			return response;
		}

	}

}
