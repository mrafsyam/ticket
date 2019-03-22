package com.kendrasolutions.util;

public class ReturnCode {
	public final static String ERROR = "1";
	public final static String SUCCESS = "0";
	
	
	public enum Errors {
		  DATABASE {
		    @Override
		    public String toString() {
		      return "A database error has occured.";
		    }
		  },

		  DUPLICATE_USER {
		    @Override
		    public String toString() {
		      return "This user already exists.";
		    }
		  },

		  MISSING_PARAM {
			    @Override
			    public String toString() {
			      return "This user already exists.";
			    }
			  },
		}
	
	public enum Success {
		  OK {
		    @Override
		    public String toString() {
		      return "Success";
		    }
		  },

		}
}
