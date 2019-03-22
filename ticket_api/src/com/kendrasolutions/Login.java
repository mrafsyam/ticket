package com.kendrasolutions;

import com.kendrasolutions.dao.DbHelper;
import com.kendrasolutions.dto.LoginDTO;

public class Login {
	public boolean doLogin(String username, String password){
		DbHelper db = new DbHelper();
		LoginDTO login = new LoginDTO(username, password);
		return db.login(db.getConn(), login);
	}
}