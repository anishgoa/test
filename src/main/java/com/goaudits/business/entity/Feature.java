package com.goaudits.business.entity;

import java.io.Serializable;

import org.springframework.stereotype.Repository;

@Repository
public class Feature implements Serializable{
	
	private static final long serialVersionUID = 1L;
	

	double feature_id;
	String feature_name;
	String feature_description1,feature_description2,feature_description3;
	String feature_image,feature_button_text, learn_more_link;
	String feature_type;
	String page_code;
	String page_name;
	
	
	
	public String getFeature_type() {
		return feature_type;
	}
	public void setFeature_type(String feature_type) {
		this.feature_type = feature_type;
	}
	public String getPage_code() {
		return page_code;
	}
	public void setPage_code(String page_code) {
		this.page_code = page_code;
	}
	public String getPage_name() {
		return page_name;
	}
	public void setPage_name(String page_name) {
		this.page_name = page_name;
	}
	public double getFeature_id() {
		return feature_id;
	}
	public void setFeature_id(double feature_id) {
		this.feature_id = feature_id;
	}
	public String getFeature_name() {
		return feature_name;
	}
	public void setFeature_name(String feature_name) {
		this.feature_name = feature_name;
	}

	public String getFeature_description1() {
		return feature_description1;
	}
	public void setFeature_description1(String feature_description1) {
		this.feature_description1 = feature_description1;
	}
	public String getFeature_description2() {
		return feature_description2;
	}
	public void setFeature_description2(String feature_description2) {
		this.feature_description2 = feature_description2;
	}
	public String getFeature_description3() {
		return feature_description3;
	}
	public void setFeature_description3(String feature_description3) {
		this.feature_description3 = feature_description3;
	}
	public String getFeature_image() {
		return feature_image;
	}
	public void setFeature_image(String feature_image) {
		this.feature_image = feature_image;
	}
	public String getFeature_button_text() {
		return feature_button_text;
	}
	public void setFeature_button_text(String feature_button_text) {
		this.feature_button_text = feature_button_text;
	}
	public String getLearn_more_link() {
		return learn_more_link;
	}
	public void setLearn_more_link(String learn_more_link) {
		this.learn_more_link = learn_more_link;
	}
	
	

}
