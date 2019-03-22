package com.kendrasolutions;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import com.kendrasolutions.dao.DbHelper;
import com.kendrasolutions.util.Parser;
import com.kendrasolutions.util.ReturnCode;
/**
 * Servlet implementation class Register
 */
@WebServlet(urlPatterns = { "/Register","/register" })
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Parser parser = new Parser();
       
    public Register() {
        super();
    }

	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DbHelper db = new DbHelper();
		Connection conn = db.getConn();
		if (conn != null) {
			try {
				URL url = new URL("http://"  // no trailing slash
	                    + request.getRequestURI()
	                    + (request.getQueryString() != null ? "?" + request.getQueryString() : ""));
				
				Map<String, List<String>> param = parser.splitQuery(url);
				List<String> username = param.get("username");
				List<String> email = param.get("email");
				List<String> password = param.get("password");
				
				JSONObject resp = new JSONObject();
				
				// check missing param
				if (param.get("username") == null
					|| param.get("email") == null
					|| param.get("password") == null) {
					resp.put("ret_code", ReturnCode.ERROR);
					resp.put("desc", ReturnCode.Errors.MISSING_PARAM.name());
					response.getWriter().append(resp.toJSONString());
					return;
				}
				
				// param is OK, do registration
				int result = db.register(conn, username.get(0), email.get(0), password.get(0));
				
				if (result == 0){
					resp.put("ret_code", ReturnCode.SUCCESS);
					resp.put("desc", ReturnCode.Success.OK.name());
					response.getWriter().append(resp.toJSONString());
				} else {
					resp.put("ret_code", ReturnCode.ERROR);
					resp.put("desc", ReturnCode.Errors.DATABASE.name());
					response.getWriter().append(resp.toJSONString());
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			response.getWriter().append("Database connection : failed");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
