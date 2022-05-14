package com.goaudits.business.entity;

import org.springframework.stereotype.Repository;

@Repository
public class CustomFieldList {
	private String guid;
	private String uid;
	private String client_id;
	private String client_name;
	private int audit_type_id;
	private String audit_type_name;
	private String field_name;
	private String field_label;
	private String field_type;
	private String active;
	private String field_value;
	private boolean show_app_list;

	/**
	 * @return the guid
	 */
	public String getGuid() {
		return guid;
	}
	/**
	 * @param guid the guid to set
	 */
	public void setGuid(String guid) {
		this.guid = guid;
	}
	/**
	 * @return the uid
	 */
	public String getUid() {
		return uid;
	}
	/**
	 * @param uid the uid to set
	 */
	public void setUid(String uid) {
		this.uid = uid;
	}
	/**
	 * @return the client_id
	 */
	public String getClient_id() {
		return client_id;
	}
	/**
	 * @param client_id the client_id to set
	 */
	public void setClient_id(String client_id) {
		this.client_id = client_id;
	}
	/**
	 * @return the client_name
	 */
	public String getClient_name() {
		return client_name;
	}
	/**
	 * @param client_name the client_name to set
	 */
	public void setClient_name(String client_name) {
		this.client_name = client_name;
	}
	/**
	 * @return the audit_type_id
	 */
	public int getAudit_type_id() {
		return audit_type_id;
	}
	/**
	 * @param audit_type_id the audit_type_id to set
	 */
	public void setAudit_type_id(int audit_type_id) {
		this.audit_type_id = audit_type_id;
	}
	/**
	 * @return the audit_type_name
	 */
	public String getAudit_type_name() {
		return audit_type_name;
	}
	/**
	 * @param audit_type_name the audit_type_name to set
	 */
	public void setAudit_type_name(String audit_type_name) {
		this.audit_type_name = audit_type_name;
	}
	/**
	 * @return the field_name
	 */
	public String getField_name() {
		return field_name;
	}
	/**
	 * @param field_name the field_name to set
	 */
	public void setField_name(String field_name) {
		this.field_name = field_name;
	}
	/**
	 * @return the field_label
	 */
	public String getField_label() {
		return field_label;
	}
	/**
	 * @param field_label the field_label to set
	 */
	public void setField_label(String field_label) {
		this.field_label = field_label;
	}
	/**
	 * @return the field_type
	 */
	public String getField_type() {
		return field_type;
	}
	/**
	 * @param field_type the field_type to set
	 */
	public void setField_type(String field_type) {
		this.field_type = field_type;
	}
	/**
	 * @return the active
	 */
	public String getActive() {
		return active;
	}
	/**
	 * @param active the active to set
	 */
	public void setActive(String active) {
		this.active = active;
	}
	public String getField_value() {
		return field_value;
	}
	public void setField_value(String field_value) {
		this.field_value = field_value;
	}
	public boolean isShow_app_list() {
		return show_app_list;
	}
	public void setShow_app_list(boolean show_app_list) {
		this.show_app_list = show_app_list;
	}
	
	
}
