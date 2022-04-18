package com.goaudits.business.entity;

import java.io.Serializable;

import javax.validation.constraints.Pattern;

import org.springframework.stereotype.Repository;

import com.goaudits.business.util.Utils;

@Repository
public class Quesactionfield implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Pattern(regexp = Utils.VALIDATION_REGEX, message = "Special character = is not allowed")
	private String guid;
	@Pattern(regexp = Utils.VALIDATION_REGEX, message = "Special character = is not allowed")
	private String uid;
	private int client_id;
	private int store_id;
	private int audit_group_id;
	private int audit_type_id;
	private int  question_no;
	@Pattern(regexp = Utils.VALIDATION_REGEX, message = "Special character = is not allowed")
	private String audit_date;
	private int seq_no;
	int field_id;
	@Pattern(regexp = Utils.VALIDATION_REGEX, message = "Special character = is not allowed")
	String field_name;
	@Pattern(regexp = Utils.VALIDATION_REGEX, message = "Special character = is not allowed")
	String field_type;
	boolean active;
	@Pattern(regexp = Utils.VALIDATION_REGEX, message = "Special character = is not allowed")
	String field_value;
	boolean is_mandatory;
	
	
	public boolean isIs_mandatory() {
		return is_mandatory;
	}
	public void setIs_mandatory(boolean is_mandatory) {
		this.is_mandatory = is_mandatory;
	}
	public String getGuid() {
		return guid;
	}
	public void setGuid(String guid) {
		this.guid = guid;
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public int getClient_id() {
		return client_id;
	}
	public void setClient_id(int client_id) {
		this.client_id = client_id;
	}
	public int getStore_id() {
		return store_id;
	}
	public void setStore_id(int store_id) {
		this.store_id = store_id;
	}
	public int getAudit_group_id() {
		return audit_group_id;
	}
	public void setAudit_group_id(int audit_group_id) {
		this.audit_group_id = audit_group_id;
	}
	public int getAudit_type_id() {
		return audit_type_id;
	}
	public void setAudit_type_id(int audit_type_id) {
		this.audit_type_id = audit_type_id;
	}
	public int getQuestion_no() {
		return question_no;
	}
	public void setQuestion_no(int question_no) {
		this.question_no = question_no;
	}
	public String getAudit_date() {
		return audit_date;
	}
	public void setAudit_date(String audit_date) {
		this.audit_date = audit_date;
	}
	public int getSeq_no() {
		return seq_no;
	}
	public void setSeq_no(int seq_no) {
		this.seq_no = seq_no;
	}
	public int getField_id() {
		return field_id;
	}
	public void setField_id(int field_id) {
		this.field_id = field_id;
	}
	public String getField_name() {
		return field_name;
	}
	public void setField_name(String field_name) {
		this.field_name = field_name;
	}
	public String getField_type() {
		return field_type;
	}
	public void setField_type(String field_type) {
		this.field_type = field_type;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	public String getField_value() {
		return field_value;
	}
	public void setField_value(String field_value) {
		this.field_value = field_value;
	}
	
	
	

}
