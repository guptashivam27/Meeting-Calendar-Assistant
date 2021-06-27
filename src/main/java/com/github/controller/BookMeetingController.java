package com.github.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.dto.BookMeetingRequestDTO;
import com.github.dto.BookMeetingResponseDTO;
import com.github.service.BookMeetingService;

@RestController
@CrossOrigin
@RequestMapping(value = "/api/v1/meeting/book")
public class BookMeetingController {

	@Autowired
	private BookMeetingService bookMeetingService;

	@PostMapping(consumes = { "application/json" }, produces = { "application/json" })
	public ResponseEntity<BookMeetingResponseDTO> bookMeeting(@RequestBody @NonNull BookMeetingRequestDTO request) {

		BookMeetingResponseDTO response = bookMeetingService.bookMeeting(request);

		if (response.getMeetingId() != null) {
			return new ResponseEntity<BookMeetingResponseDTO>(response, HttpStatus.CREATED);

		} else {
			return new ResponseEntity<BookMeetingResponseDTO>(HttpStatus.BAD_REQUEST);
		}
	}

}
