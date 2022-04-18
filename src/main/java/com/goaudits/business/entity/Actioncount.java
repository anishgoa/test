package com.goaudits.business.entity;

import java.io.Serializable;

import javax.validation.constraints.Pattern;

import org.springframework.stereotype.Repository;

import com.goaudits.business.util.Utils;

@Repository
public class Actioncount  implements Serializable{
	private static final long serialVersionUID = 1L;
	
    @Pattern(regexp = Utils.VALIDATION_REGEX, message = "Special character = is not allowed")
	String opencount;
    
    @Pattern(regexp = Utils.VALIDATION_REGEX, message = "Special character = is not allowed")
	String inprogresscount;
    
    @Pattern(regexp = Utils.VALIDATION_REGEX, message = "Special character = is not allowed")
	String submitforapprovalcount;
    
    @Pattern(regexp = Utils.VALIDATION_REGEX, message = "Special character = is not allowed")
	String closedcount;
    
    @Pattern(regexp = Utils.VALIDATION_REGEX, message = "Special character = is not allowed")
	String rejectedcount;
    
    @Pattern(regexp = Utils.VALIDATION_REGEX, message = "Special character = is not allowed")
	String opentext;
    
    @Pattern(regexp = Utils.VALIDATION_REGEX, message = "Special character = is not allowed")
	String inprogresstext;
    
    @Pattern(regexp = Utils.VALIDATION_REGEX, message = "Special character = is not allowed")
	String submitforapprovaltext;
    
    @Pattern(regexp = Utils.VALIDATION_REGEX, message = "Special character = is not allowed")
	String closedtext;
    
    @Pattern(regexp = Utils.VALIDATION_REGEX, message = "Special character = is not allowed")
	String rejectedtext;
    
    @Pattern(regexp = Utils.VALIDATION_REGEX, message = "Special character = is not allowed")
	String duedatecount;
    
    @Pattern(regexp = Utils.VALIDATION_REGEX, message = "Special character = is not allowed")
	String duedatetext;
	
	
	public String getDuedatecount() {
		return duedatecount;
	}
	public void setDuedatecount(String duedatecount) {
		this.duedatecount = duedatecount;
	}
	public String getDuedatetext() {
		return duedatetext;
	}
	public void setDuedatetext(String duedatetext) {
		this.duedatetext = duedatetext;
	}
	public String getOpentext() {
		return opentext;
	}
	public void setOpentext(String opentext) {
		this.opentext = opentext;
	}
	public String getInprogresstext() {
		return inprogresstext;
	}
	public void setInprogresstext(String inprogresstext) {
		this.inprogresstext = inprogresstext;
	}
	public String getSubmitforapprovaltext() {
		return submitforapprovaltext;
	}
	public void setSubmitforapprovaltext(String submitforapprovaltext) {
		this.submitforapprovaltext = submitforapprovaltext;
	}
	public String getClosedtext() {
		return closedtext;
	}
	public void setClosedtext(String closedtext) {
		this.closedtext = closedtext;
	}
	public String getRejectedtext() {
		return rejectedtext;
	}
	public void setRejectedtext(String rejectedtext) {
		this.rejectedtext = rejectedtext;
	}
	public String getOpencount() {
		return opencount;
	}
	public void setOpencount(String opencount) {
		this.opencount = opencount;
	}
	public String getInprogresscount() {
		return inprogresscount;
	}
	public void setInprogresscount(String inprogresscount) {
		this.inprogresscount = inprogresscount;
	}
	public String getSubmitforapprovalcount() {
		return submitforapprovalcount;
	}
	public void setSubmitforapprovalcount(String submitforapprovalcount) {
		this.submitforapprovalcount = submitforapprovalcount;
	}
	public String getClosedcount() {
		return closedcount;
	}
	public void setClosedcount(String closedcount) {
		this.closedcount = closedcount;
	}
	public String getRejectedcount() {
		return rejectedcount;
	}
	public void setRejectedcount(String rejectedcount) {
		this.rejectedcount = rejectedcount;
	}
	

}
