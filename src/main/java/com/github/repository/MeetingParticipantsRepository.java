package com.github.repository;

import org.springframework.data.repository.CrudRepository;

import com.github.entity.MeetingParticipants;

/**
 * The Interface MeetingParticipantsRepository.
 */
public interface MeetingParticipantsRepository extends CrudRepository<MeetingParticipants, Integer> {

}
