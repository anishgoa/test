package com.goaudits.business.entity;

import javax.validation.constraints.Pattern;

import org.springframework.stereotype.Repository;

import com.goaudits.business.util.Utils;

@Repository
public class ActionPlanSettings {
	
	@Pattern(regexp = Utils.VALIDATION_REGEX, message =Utils.Validation_Message,flags = Pattern.Flag.DOTALL)
	String guid;
	int client_id;
	int audit_group_id;
	int audit_type_id;
	int priority_id=0;
	@Pattern(regexp = Utils.VALIDATION_REGEX, message =Utils.Validation_Message,flags = Pattern.Flag.DOTALL)
	String priority_name;
	@Pattern(regexp = Utils.VALIDATION_REGEX, message =Utils.Validation_Message,flags = Pattern.Flag.DOTALL)
	String priority_color;
	int default_due_days;
	boolean active;
	boolean make_default;
	
	public String getGuid() {
		return guid;
	}
	public void setGuid(String guid) {
		this.guid = guid;
	}
	public int getClient_id() {
		return client_id;
	}
	public void setClient_id(int client_id) {
		this.client_id = client_id;
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
	public int getPriority_id() {
		return priority_id;
	}
	public void setPriority_id(int priority_id) {
		this.priority_id = priority_id;
	}
	public String getPriority_name() {
		return priority_name;
	}
	public void setPriority_name(String priority_name) {
		this.priority_name = priority_name;
	}
	public String getPriority_color() {
		return priority_color;
	}
	public void setPriority_color(String priority_color) {
		this.priority_color = priority_color;
	}
	public int getDefault_due_days() {
		return default_due_days;
	}
	public void setDefault_due_days(int default_due_days) {
		this.default_due_days = default_due_days;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	public boolean isMake_default() {
		return make_default;
	}
	public void setMake_default(boolean make_default) {
		this.make_default = make_default;
	}
	
	
	

}
