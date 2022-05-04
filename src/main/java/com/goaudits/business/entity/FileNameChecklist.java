package com.goaudits.business.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.Pattern;

import org.springframework.stereotype.Repository;

import com.goaudits.business.util.Utils;

@Repository
public class FileNameChecklist implements Serializable{
	
	private static final long serialVersionUID = 1L;
	@Pattern(regexp = Utils.VALIDATION_REGEX, message =Utils.Validation_Message )
	private String guid;
	@Pattern(regexp = Utils.VALIDATION_REGEX, message =Utils.Validation_Message )
	private String uid;
	private int client_id;
	private int audit_type_id;
	private int report_id_enabled;
	private int client_name_enabled;
	private int checklist_name_enabled;
	private int store_name_enabled;
	private int audit_date_enabled;
	private int location_code_enabled;
	private int custom_field_enabled;
	@Pattern(regexp = Utils.VALIDATION_REGEX, message =Utils.Validation_Message )
	private String custom_field_name;
	
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
	public int getAudit_type_id() {
		return audit_type_id;
	}
	public void setAudit_type_id(int audit_type_id) {
		this.audit_type_id = audit_type_id;
	}
	public int getReport_id_enabled() {
		return report_id_enabled;
	}
	public void setReport_id_enabled(int report_id_enabled) {
		this.report_id_enabled = report_id_enabled;
	}
	public int getClient_name_enabled() {
		return client_name_enabled;
	}
	public void setClient_name_enabled(int client_name_enabled) {
		this.client_name_enabled = client_name_enabled;
	}
	public int getChecklist_name_enabled() {
		return checklist_name_enabled;
	}
	public void setChecklist_name_enabled(int checklist_name_enabled) {
		this.checklist_name_enabled = checklist_name_enabled;
	}
	public int getStore_name_enabled() {
		return store_name_enabled;
	}
	public void setStore_name_enabled(int store_name_enabled) {
		this.store_name_enabled = store_name_enabled;
	}
	public int getAudit_date_enabled() {
		return audit_date_enabled;
	}
	public void setAudit_date_enabled(int audit_date_enabled) {
		this.audit_date_enabled = audit_date_enabled;
	}
	public int getLocation_code_enabled() {
		return location_code_enabled;
	}
	public void setLocation_code_enabled(int location_code_enabled) {
		this.location_code_enabled = location_code_enabled;
	}
	public int getCustom_field_enabled() {
		return custom_field_enabled;
	}
	public void setCustom_field_enabled(int custom_field_enabled) {
		this.custom_field_enabled = custom_field_enabled;
	}
	public String getCustom_field_name() {
		return custom_field_name;
	}
	public void setCustom_field_name(String custom_field_name) {
		this.custom_field_name = custom_field_name;
	}
	
	

	
}
