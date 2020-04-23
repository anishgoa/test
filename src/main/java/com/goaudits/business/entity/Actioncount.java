package com.goaudits.business.entity;

import java.io.Serializable;

import org.springframework.stereotype.Repository;

@Repository
public class Actioncount  implements Serializable{
	private static final long serialVersionUID = 1L;
	String opencount;
	String inprogresscount;
	String submitforapprovalcount;
	String closedcount;
	String rejectedcount;
	String opentext;
	String inprogresstext;
	String submitforapprovaltext;
	String closedtext;
	String rejectedtext;
	String duedatecount;
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
