package com.goaudits.business.entity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class Test {
	public static void main(String[] args) throws ParseException {
		
//		for(int i=0;i<10;i++) {
		    Random r = new Random( System.currentTimeMillis() );
		    System.out.println( ((1 + r.nextInt(2)) * 10000 + r.nextInt(10000)));
//		}

	}

}
