package com.goaudits.business.entity;

import org.springframework.stereotype.Repository;

@Repository
public class Notification {
	int notification_id;
	String guid;
	String client_id;
	String audit_group_id;
	String audit_type_id;
	int notification_type;
	int frequency;
	String trigger_day;
	String trigger_date_time;
	String time_zone;
	String email_list;
	String uid;
	String trigger_date;
	String trigger_time;
	String client_name;
	String audit_type_name;
	String frequencyname;

	public int getNotification_id() {
		return notification_id;
	}

	public void setNotification_id(int notification_id) {
		this.notification_id = notification_id;
	}

	public String getGuid() {
		return guid;
	}

	public void setGuid(String guid) {
		this.guid = guid;
	}

	public String getClient_id() {
		return client_id;
	}

	public void setClient_id(String client_id) {
		this.client_id = client_id;
	}

	public String getAudit_group_id() {
		return audit_group_id;
	}

	public void setAudit_group_id(String audit_group_id) {
		this.audit_group_id = audit_group_id;
	}

	public String getAudit_type_id() {
		return audit_type_id;
	}

	public void setAudit_type_id(String audit_type_id) {
		this.audit_type_id = audit_type_id;
	}

	public int getNotification_type() {
		return notification_type;
	}

	public void setNotification_type(int notification_type) {
		this.notification_type = notification_type;
	}

	public int getFrequency() {
		return frequency;
	}

	public void setFrequency(int frequency) {
		this.frequency = frequency;
	}

	public String getTrigger_day() {
		return trigger_day;
	}

	public void setTrigger_day(String trigger_day) {
		this.trigger_day = trigger_day;
	}

	public String getTrigger_date_time() {
		return trigger_date_time;
	}

	public void setTrigger_date_time(String trigger_date_time) {
		this.trigger_date_time = trigger_date_time;
	}

	public String getTime_zone() {
		return time_zone;
	}

	public void setTime_zone(String time_zone) {
		this.time_zone = time_zone;
	}

	public String getEmail_list() {
		return email_list;
	}

	public void setEmail_list(String email_list) {
		this.email_list = email_list;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getTrigger_date() {
		return trigger_date;
	}

	public void setTrigger_date(String trigger_date) {
		this.trigger_date = trigger_date;
	}

	public String getTrigger_time() {
		return trigger_time;
	}

	public void setTrigger_time(String trigger_time) {
		this.trigger_time = trigger_time;
	}

	public String getClient_name() {
		return client_name;
	}

	public void setClient_name(String client_name) {
		this.client_name = client_name;
	}

	public String getAudit_type_name() {
		return audit_type_name;
	}

	public void setAudit_type_name(String audit_type_name) {
		this.audit_type_name = audit_type_name;
	}

	public String getFrequencyname() {
		return frequencyname;
	}

	public void setFrequencyname(String frequencyname) {
		this.frequencyname = frequencyname;
	}

	

}
