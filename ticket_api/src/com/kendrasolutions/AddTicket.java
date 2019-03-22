package com.kendrasolutions;

import java.sql.Timestamp;
import com.kendrasolutions.dao.DbHelper;
import com.kendrasolutions.dto.TicketDTO;

public class AddTicket {
	public boolean doAddTicket(int userID, 
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
		
		TicketDTO ticket = new TicketDTO(0, userID, 
				ticketNo, 
				ticketType,
				pgRelated, 
				notes,
				assignTime,
				responseTime,
				fulfillmentTime, 0,0);
		
		// adjust responseTimeSLA and fulfillmentTime
		ticket.getSLA();
		
		return db.addTicket(db.getConn(), ticket);
	}
}