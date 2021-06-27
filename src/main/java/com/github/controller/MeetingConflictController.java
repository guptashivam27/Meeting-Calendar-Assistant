package com.github.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.dto.MeetingConflictRequestDTO;
import com.github.dto.MeetingConflictResponseDTO;
import com.github.service.MeetingConflictService;

/**
 * The Class MeetingConflictController.
 */
@RestController
@CrossOrigin
@RequestMapping(value = "/api/v1/meeting/conflict")
public class MeetingConflictController {

	/** The meeting conflict service. */
	@Autowired
	private MeetingConflictService meetingConflictService;

	/**
	 * Find meeting conflicts.
	 *
	 * @param request the request
	 * @return the response entity
	 */
	@GetMapping(produces = { "application/json" })
	public ResponseEntity<MeetingConflictResponseDTO> findMeetingConflicts(
			@RequestBody @NonNull MeetingConflictRequestDTO request) {

		List<Integer> conflicts = new ArrayList<>();
		
		conflicts = meetingConflictService.findMeetingConflicts(request.getEmployees(), request.getMeetingRequestDetail());
		
		MeetingConflictResponseDTO response = new MeetingConflictResponseDTO(conflicts);

		return new ResponseEntity<MeetingConflictResponseDTO>(response, HttpStatus.OK);
	}
}
