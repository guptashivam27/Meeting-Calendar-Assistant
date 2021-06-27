package com.github.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "MEETING_DETAILS")
public class MeetingDetails {
	@Id
    @GeneratedValue
    @Column(name = "MEETING_ID", nullable = false)
	Integer meetingId;
	
	@Column(name = "MEETING_TITLE", nullable = true)
	String meetingTitle;
	
	@Column(name = "DATE", nullable = false)
	@Temporal(TemporalType.DATE)
	Date date;
	
	@Column(name = "START_TIME", nullable = false)
	Integer startTime;
	
	@Column(name = "END_TIME", nullable = false)
	Integer endTime;
	 

}
