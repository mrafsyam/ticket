package com.kendrasolutions;

import java.sql.Timestamp;
import java.util.List;
import com.kendrasolutions.dao.DbHelper;
import com.kendrasolutions.dto.TicketDTO;

public class GetTicketList {
	public List<TicketDTO> doGetTicketList(Timestamp from, Timestamp to) {
		DbHelper db = new DbHelper();
		return db.getTicketList(db.getConn(), from, to);
	}
}