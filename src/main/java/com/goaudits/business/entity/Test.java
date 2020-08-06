package com.goaudits.business.entity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Test {
	public static void main(String[] args) throws ParseException {
		
		Date d=new Date("2-Feb-2021");
		SimpleDateFormat s=new SimpleDateFormat("yyyy-MM-dd");
	    String strDate= s.format(d);  
	    System.out.println(strDate);

	}

}
