package com.github.controller;

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

import com.github.dto.AvailabilityRequestDTO;
import com.github.dto.AvailabilityResponseDTO;
import com.github.dto.CalendarEvent;
import com.github.service.AvailabilityService;

/**
 * The Class AvailabilityController.
 */
@RestController
@CrossOrigin
@RequestMapping(value = "/api/v1/meeting/available")
public class AvailabilityController {

	/** The availability service. */
	@Autowired
	private AvailabilityService availabilityService;

	/**
	 * Find availability.
	 *
	 * @param request the request
	 * @return the response entity
	 */
	@GetMapping(produces = { "application/json" })
	public ResponseEntity<AvailabilityResponseDTO> findAvailability(
			@RequestBody @NonNull AvailabilityRequestDTO request) {

		AvailabilityResponseDTO response = new AvailabilityResponseDTO();

		List<CalendarEvent> availableSlots = availabilityService.checkAvailableSlots(request.getEmployeeCalendar(),
				request.getMeetingDuration());

		response.setAvailableSlots(availableSlots);

		return new ResponseEntity<AvailabilityResponseDTO>(response, HttpStatus.OK);
	}

}
