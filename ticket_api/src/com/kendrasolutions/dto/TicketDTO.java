package com.kendrasolutions.dto;

import java.sql.Timestamp;

import com.kendrasolutions.util.Parser;

public class TicketDTO {
	int ticketID;
	public int getTicketID() {
		return ticketID;
	}

	public void setTicketID(int ticketID) {
		this.ticketID = ticketID;
	}

	int userID; 
	String ticketNo;
	String ticketType;
	String pgRelated;
	String notes;
	Timestamp assignTime;
	Timestamp responseTime;
	Timestamp fulfillmentTime;
	long responseTimeSLA;
	long fulfillmentTimeSLA;
	
	// existing ticket
	public TicketDTO(int ticketID,
			int userID, 
			String ticketNo, 
			String ticketType, 
			String pgRelated, 
			String notes,
			Timestamp assignTime, 
			Timestamp responseTime, 
			Timestamp fulfillmentTime,
			long responseTimeSLA,
			long fulfillmentTimeSLA
			) {
		
		this.ticketID = ticketID;
		this.userID = userID;
		this.ticketNo = ticketNo;
		this.ticketType = ticketType;
		this.pgRelated = pgRelated;
		this.notes = notes;
		this.assignTime = assignTime;
		this.responseTime = responseTime;
		this.fulfillmentTime = fulfillmentTime;
		this.responseTimeSLA = responseTimeSLA;
		this.fulfillmentTimeSLA = fulfillmentTimeSLA;
	}

	public String getResponseTimeSLA() {
		Integer i = new Long(responseTimeSLA) != null ? new Long(responseTimeSLA).intValue() : null;

		return String.valueOf(responseTimeSLA);
	}

	public void setResponseTimeSLA(long responseTimeSLA) {
		this.responseTimeSLA = responseTimeSLA;
	}

	public String getFulfillmentTimeSLA() {
		Integer i = new Long(fulfillmentTimeSLA) != null ? new Long(fulfillmentTimeSLA).intValue() : null;
		return String.valueOf(fulfillmentTimeSLA);
	}

	public void setFulfillmentTimeSLA(long fulfillmentTimeSLA) {
		this.fulfillmentTimeSLA = fulfillmentTimeSLA;
	}

	public void getSLA() {
		this.responseTimeSLA = Parser.getTimestampDiffMin(responseTime,assignTime);
		this.fulfillmentTimeSLA = Parser.getTimestampDiffDay(fulfillmentTime,assignTime);
	}

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public String getTicketNo() {
		return ticketNo;
	}

	public void setTicketNo(String ticketNo) {
		this.ticketNo = ticketNo;
	}

	public String getTicketType() {
		return ticketType;
	}

	public void setTicketType(String ticketType) {
		this.ticketType = ticketType;
	}

	public String getPgRelated() {
		return pgRelated;
	}

	public void setPgRelated(String pgRelated) {
		this.pgRelated = pgRelated;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public Timestamp getAssignTime() {
		return assignTime;
	}

	public void setAssignTime(Timestamp assignTime) {
		this.assignTime = assignTime;
	}

	public Timestamp getResponseTime() {
		return responseTime;
	}

	public void setResponseTime(Timestamp responseTime) {
		this.responseTime = responseTime;
	}

	public Timestamp getFulfillmentTime() {
		return fulfillmentTime;
	}

	public void setFulfillmentTime(Timestamp fulfillmentTime) {
		this.fulfillmentTime = fulfillmentTime;
	}

}
