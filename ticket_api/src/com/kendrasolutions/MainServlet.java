package com.kendrasolutions;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.kendrasolutions.dao.DbHelper;
import com.kendrasolutions.dto.TicketDTO;
import com.kendrasolutions.util.Parser;


@WebServlet(asyncSupported = true, urlPatterns = { "/" })
public class MainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Parser parser = new Parser();
	Map<String, List<String>> param = null;
	List<String> mandatoryParam = null;
	List<String> missingParam = null;

    public MainServlet() {
        // TODO Auto-generated constructor stub
    }
    
    // format params into String
    public String formatParams(Object o) {
    	return o.toString().replaceAll("\\[", "").replaceAll("\\]","");
    }
    
    public List<String> checkParamExist(List<String> paramList){
		List<String> missingParam = new ArrayList<String>();
    	for (String eachParam : paramList) {
    		if (param.get(eachParam) == null) {
    			missingParam.add(eachParam);
    		}
    	}
    	return missingParam;
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DbHelper db = new DbHelper();
		Connection conn = db.getConn();
		if (conn != null) {
			try {
        		// Logger.getLogger(MainServlet.class.getName()).log(Level.INFO, "request URL : " + request.getRequestURL());
        		// Logger.getLogger(MainServlet.class.getName()).log(Level.INFO, "request query: " + request.getQueryString());
        		
				URL url = new URL("http://"  // no trailing slash
	                    + request.getRequestURI()
	                    + (request.getQueryString() != null ? "?" + request.getQueryString() : ""));
				
				param = parser.splitQuery(url);
				String api = request.getRequestURI().toString();
				
				
	        	System.out.println(api.toLowerCase());

				switch (api.toLowerCase()) {
		            case "/ticket/baseapi/login":
		            	mandatoryParam = Arrays.asList("username", "password");
		            	missingParam = checkParamExist(mandatoryParam);

		            	if (!missingParam.isEmpty()) {
		            		String resp = "Missing params :";
		                	for (String eachParam : missingParam) {
		                		resp = resp + " " + eachParam;
		                	}
		            		response.getWriter().append(resp);
		            		break;
		            	} else {
		    	        	System.out.println("param is OK");

		            		String username = formatParams(param.get("username"));
		            		String password = formatParams(param.get("password"));
			            	Login login = new Login();
			            	if (login.doLogin(username, password))
			            	{	
			            		System.out.println("should be success");
			            		response.getWriter().append("success");
			            	} else {
			            		System.out.println("should be failed");
			            		response.getWriter().append("failed");
			            	}
		            	}
		            	break;
		            case "/ticket/baseapi/register":		     
		            	// register user
		            	break;
		            case "/ticket/baseapi/addticket":
		            	// http://localhost:8080/baseAPI/addticket?ticket_no=11111&user_id=111&ticket_type=asdasd&pg_related=1&notes=yes%20not%20really&assign_time=2017-02-25%2000:00:01&response_time=2017-02-25%2000:00:10&fulfillment_time=2017-02-25%2000:00:34
		            	mandatoryParam = Arrays.asList("user_id", "ticket_no","ticket_type","pg_related","notes","assign_time","response_time","fulfillment_time");
		            	missingParam = checkParamExist(mandatoryParam);

		            	if (!missingParam.isEmpty()) {
		            		String resp = "Missing params :";
		                	for (String eachParam : missingParam) {
		                		resp = resp + " " + eachParam;
		                	}
		            		response.getWriter().append(resp);
		            		break;
		            	} else {
		            		Integer userID = Integer.parseInt(formatParams(param.get("user_id").get(0)));
		            		String ticketNo = formatParams(param.get("ticket_no"));
		            		String ticketType = formatParams(param.get("ticket_type"));
				        	String pgRelated = formatParams(param.get("pg_related"));
				         	String notes = formatParams(param.get("notes"));
				         	Timestamp assignTime = Timestamp.valueOf(formatParams(param.get("assign_time")));
				         	Timestamp responseTime = Timestamp.valueOf(formatParams(param.get("response_time")));
				         	Timestamp fulfillmentTime = Timestamp.valueOf(formatParams(param.get("fulfillment_time")));
				         	
		            		AddTicket addTicket = new AddTicket();
				         	addTicket.doAddTicket(userID, ticketNo, ticketType, pgRelated, notes, assignTime, responseTime, fulfillmentTime,0,0);
		            	}
		            	break;

		            case "/ticket/baseapi/updateticket":
		            	mandatoryParam = Arrays.asList("ticket_id", "user_id", "ticket_no","ticket_type","pg_related","notes","assign_time","response_time","fulfillment_time");
		            	missingParam = checkParamExist(mandatoryParam);

		            	if (!missingParam.isEmpty()) {
		            		String resp = "Missing params :";
		                	for (String eachParam : missingParam) {
		                		resp = resp + " " + eachParam;
		                	}
		            		response.getWriter().append(resp);
		            		break;
		            	} else {
		            		Integer ticketID = Integer.parseInt(formatParams(param.get("ticket_id").get(0)));
		            		Integer userID = Integer.parseInt(formatParams(param.get("user_id").get(0)));
		            		String ticketNo = formatParams(param.get("ticket_no"));
		            		String ticketType = formatParams(param.get("ticket_type"));
				        	String pgRelated = formatParams(param.get("pg_related"));
				         	String notes = formatParams(param.get("notes"));
				         	Timestamp assignTime = Timestamp.valueOf(formatParams(param.get("assign_time")));
				         	Timestamp responseTime = Timestamp.valueOf(formatParams(param.get("response_time")));
				         	Timestamp fulfillmentTime = Timestamp.valueOf(formatParams(param.get("fulfillment_time")));
				         	
		            		UpdateTicket updateTicket = new UpdateTicket();
				         	updateTicket.doUpdateTicket(ticketID, userID, ticketNo, ticketType, pgRelated, notes, assignTime, responseTime, fulfillmentTime,0,0);
		            	}
		            	break;
		            
		            // get list of ticket. Time filter is for fulfillment_time
		            case "/ticket/baseapi/getlistoftickets":	
		            	mandatoryParam = Arrays.asList("from","to");
		            	missingParam = checkParamExist(mandatoryParam);

		            	
		            	if (!missingParam.isEmpty()) {
		            		String resp = "Missing params :";
		                	for (String eachParam : missingParam) {
		                		resp = resp + " " + eachParam;
		                	}
		            		response.getWriter().append(resp);
		            		break;
		            	} else {
				         	Timestamp from = Timestamp.valueOf(formatParams(param.get("from")));
				         	Timestamp to = Timestamp.valueOf(formatParams(param.get("to")));
				         	
		            		GetTicketList getTicketList = new GetTicketList();
		            		
		            		List<TicketDTO> list = getTicketList.doGetTicketList(from, to);
		            		Gson gson = new Gson();
		            		response.getWriter().append(gson.toJson(list));
		            	}
		            	break;
				}
				
				// get value using param.
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			response.getWriter().append("Database connection : failed");
			// .append(request.getContextPath());
		}
	}
	


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
