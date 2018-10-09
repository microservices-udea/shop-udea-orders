package com.udea.orders.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class UtilDates {
	
    private static final String formatDate = "dd/MM/yyyy";
    
	  public static String convertDateToString(Date dt) {
	        DateFormat df = new SimpleDateFormat(formatDate);
	        return df.format(dt);
	    }
}
