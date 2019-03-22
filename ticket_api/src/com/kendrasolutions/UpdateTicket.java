package com.kendrasolutions;

import java.sql.Timestamp;
import com.kendrasolutions.dao.DbHelper;
import com.kendrasolutions.dto.TicketDTO;

public class UpdateTicket {
	public boolean doUpdateTicket(int ticketID,
			int userID, 
			String ticketNo,
			String ticketType,
			String pgRelated,
			String notes,
			Timestamp assignTime,
			Timestamp responseTime,
			Timestamp fulfillmentTime,
			long responseTimeSLA,
			long fulfillmentTimeSLA){
		
		DbHelper db = new DbHelper();
		
		TicketDTO ticket = new TicketDTO(ticketID, 
				userID, 
				ticketNo, 
				ticketType,
				pgRelated, 
				notes,
				assignTime,
				responseTime,
				fulfillmentTime,
				responseTimeSLA,
				fulfillmentTimeSLA);

		// call db.updateTicket here
		return db.addTicket(db.getConn(), ticket);
	}
}