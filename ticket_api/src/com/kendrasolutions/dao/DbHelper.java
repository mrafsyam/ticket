package com.kendrasolutions.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.kendrasolutions.dto.LoginDTO;
import com.kendrasolutions.dto.TicketDTO;
import com.kendrasolutions.util.Config;
import com.mysql.cj.protocol.Resultset;

public class DbHelper {
	    
    //  database credentials
    private String dBHost = "";
    private String dBName = "";
    private String dBUser = "";
    private	String dBPass = "";
    
    private Connection conn = null;    
    private String DB_URL = "";

    public DbHelper() {
        try {
        	Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            
            List<String> dBConfig = Config.getDbConfig();
            
            dBHost = dBConfig.get(0);
            dBName = dBConfig.get(1);
            dBUser = dBConfig.get(2);
            dBPass = dBConfig.get(3);
            
            DB_URL = "jdbc:mysql://" + dBHost + "/" + dBName;
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    
    public Connection getConn() {
        try {
        	if (conn == null) {
                System.out.println("Connecting to database...");
                conn = DriverManager.getConnection(DB_URL, dBUser, dBPass);
                
                if (conn !=null) {
                	System.out.println("Connected to database.");
                }
        	}
        } catch (SQLException ex) {
            Logger.getLogger(DbHelper.class.getName()).log(Level.SEVERE, null, ex);
        }

		return conn;
    }
    
    public int register(Connection conn, String username, String email, String password) {
        try {
        	PreparedStatement stmt = 
        			conn.prepareStatement("INSERT INTO ACCESS (userType, username, email, password) VALUES(?,?,?,?)");  
        	stmt.setString(1,"1");
        	stmt.setString(2,username); 
        	stmt.setString(3,email);  
        	stmt.setString(4,password); 
        	stmt.executeUpdate();  
        } catch (SQLException ex) {
            Logger.getLogger(DbHelper.class.getName()).log(Level.SEVERE, null, ex);
            return 1;
        }
		return 0;
    }
    
    public boolean login(Connection conn, LoginDTO login) {
        try {
        	PreparedStatement stmt = 
        			conn.prepareStatement("select ID from USERS where EMAIL=? and password=?");  
        	stmt.setString(1,login.getUsername()); 
        	stmt.setString(2,login.getPassword()); 
        	ResultSet rs = stmt.executeQuery();
        	
        	System.out.println(stmt.toString());
        	System.out.println(rs.toString());
 
        	
        	if (rs.next()) { 
        		System.out.println( "login OK");
        		return true;
        	} else {
        		System.out.println( "login failed");
        		return false;
        	}
        } catch (SQLException ex) {
            Logger.getLogger(DbHelper.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
            return false;
        }
    }
    
    public int updateTicket(Connection conn, String username, String password) {
        try {
        	PreparedStatement stmt = 
        			conn.prepareStatement("select ID from ACCESS where username=? and password=?");  
        	stmt.setString(1,username); 
        	stmt.setString(2,password); 
        	stmt.executeUpdate();  
        } catch (SQLException ex) {
            Logger.getLogger(DbHelper.class.getName()).log(Level.SEVERE, null, ex);
            return 1;
        }
		return 0;
    }
    
    public int getListTicket(Connection conn, String username, String password) {
        try {
        	PreparedStatement stmt = 
        			conn.prepareStatement("select ID from ACCESS where username=? and password=?");  
        	stmt.setString(1,username); 
        	stmt.setString(2,password); 
        	stmt.executeUpdate();  
        } catch (SQLException ex) {
            Logger.getLogger(DbHelper.class.getName()).log(Level.SEVERE, null, ex);
            return 1;
        }
		return 0;
    }

	public boolean addTicket(Connection con, TicketDTO ticket) {
        try {
        	PreparedStatement stmt = conn.prepareStatement("INSERT INTO TICKET "
        					+ "(USER_ID, "
        					+ "TICKET_NO, "
        					+ "TICKET_TYPE, "
        					+ "PG_RELATED, "
        					+ "NOTES, "
        					+ "ASSIGNED_TIME, "
        					+ "RESPONSE_TIME, "
        					+ "FULFILLMENT_TIME, "
        					+ "RESPONSE_SLA, "
        					+ "FULFILLMENT_SLA) "
        					+ "VALUES "
        					+ "(?,?,?,?,?,?,?,?,?,?)");
        	
        	stmt.setInt(1,ticket.getUserID()); 
        	stmt.setString(2,ticket.getTicketNo()); 
        	stmt.setString(3,ticket.getTicketType()); 
        	stmt.setString(4,ticket.getPgRelated()); 
        	stmt.setString(5,ticket.getNotes()); 
        	stmt.setTimestamp(6,ticket.getAssignTime()); 
        	stmt.setTimestamp(7,ticket.getResponseTime()); 
        	stmt.setTimestamp(8,ticket.getFulfillmentTime()); 
        	stmt.setString(9,ticket.getResponseTimeSLA()); 
        	stmt.setString(10,ticket.getFulfillmentTimeSLA()); 
        	
        	stmt.executeUpdate();  
        	
    		Logger.getLogger(DbHelper.class.getName()).log(Level.INFO, stmt.toString());

        	
        } catch (SQLException ex) {
            Logger.getLogger(DbHelper.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
		return true;
	}

	public List<TicketDTO> getTicketList(Connection conn, Timestamp from, Timestamp to) {
    	List<TicketDTO> list = new ArrayList<TicketDTO>();

		 try {
	        	PreparedStatement stmt = conn.prepareStatement("SELECT ID, USER_ID, TICKET_NO, TICKET_TYPE, PG_RELATED, "
	        			+ " NOTES, ASSIGNED_TIME, RESPONSE_TIME, FULFILLMENT_TIME, RESPONSE_SLA, FULFIlLMENT_SLA "
	        			+ " FROM TICKET " 
	        			+ " WHERE FULFILLMENT_TIME > ? "
	        			+ " AND FULFILLMENT_TIME < ? ");
	        	
	        	stmt.setTimestamp(1, from); 
	        	stmt.setTimestamp(2, to); 
	        	
	        	ResultSet rs = stmt.executeQuery();
	        	
	        	Logger.getLogger(DbHelper.class.getName()).log(Level.INFO, stmt.toString());
	        	Logger.getLogger(DbHelper.class.getName()).log(Level.INFO, rs.toString());

	        	TicketDTO ticket = null;
	        	while (rs.next()) {
		        	  int ticketID = rs.getInt("ID");
		        	  int userID = rs.getInt("USER_ID");
		        	  String ticketNo = rs.getString("TICKET_NO");
		        	  String ticketType = rs.getString("TICKET_TYPE");
		        	  String pgRelated = rs.getString("PG_RELATED");
		        	  String notes = rs.getString("NOTES");
		        	  Timestamp assignTime = rs.getTimestamp("ASSIGNED_TIME");
		        	  Timestamp responseTime = rs.getTimestamp("ASSIGNED_TIME");
		        	  Timestamp fulfillmentTime = rs.getTimestamp("ASSIGNED_TIME");
		        	  Long responseTimeSLA = rs.getLong("RESPONSE_SLA");
		        	  Long fulfillmentTimeSLA = rs.getLong("FULFIlLMENT_SLA");
		        	  
		        	  ticket = new TicketDTO(ticketID, 
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

		        	  list.add(ticket);
		        }
		 } catch (SQLException ex) {
			Logger.getLogger(DbHelper.class.getName()).log(Level.SEVERE, null, ex);
	        return null;
		 }
		return list;
	}
}