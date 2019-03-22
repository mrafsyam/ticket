package com.kendrasolutions.util;

import java.util.ArrayList;
import java.util.List;

public class Config {

	//  database credentials
    private static final String dBHost = "145.239.86.102";
    //private static final String dBHost = "localhost";
    private static final String dBName = "MS";
    private static final String dBUser = "root";
    private static final String dBPass = "Enter_123";
    
    public static List<String> getDbConfig(){
    	List<String> list = new ArrayList<String>();
    	list.add(dBHost);
    	list.add(dBName);
    	list.add(dBUser);
    	list.add(dBPass);
		return list;
    }
}
