package com.goaudits.business.entity;

import org.springframework.stereotype.Repository;

@Repository
public class ReportImage {

String imageurl;

/**
 * @return the imageurl
 */
public String getImageurl() {
	return imageurl;
}

/**
 * @param imageurl the imageurl to set
 */
public void setImageurl(String imageurl) {
	this.imageurl = imageurl;
}



}
