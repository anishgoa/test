package com.goaudits.business.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.Pattern;

import org.springframework.stereotype.Repository;

import com.goaudits.business.util.Utils;

@Repository
public class Customfields implements Serializable {

	private static final long serialVersionUID = 1L;

	@Pattern(regexp = Utils.VALIDATION_REGEX, message =Utils.Validation_Message )
	private String guid;
	@Pattern(regexp = Utils.VALIDATION_REGEX, message =Utils.Validation_Message )
	private String uid;
	@Pattern(regexp = Utils.VALIDATION_REGEX, message =Utils.Validation_Message )
	private String client_id;
	@Pattern(regexp = Utils.VALIDATION_REGEX, message =Utils.Validation_Message )
	private String client_name;
	private int audit_group_id = 1;
	@Pattern(regexp = Utils.VALIDATION_REGEX, message =Utils.Validation_Message )
	private String audit_group_name;
	private int audit_type_id;
	@Pattern(regexp = Utils.VALIDATION_REGEX, message =Utils.Validation_Message )
	private String audit_type_name;
	@Pattern(regexp = Utils.VALIDATION_REGEX, message =Utils.Validation_Message )
	private String field_name;
	@Pattern(regexp = Utils.VALIDATION_REGEX, message =Utils.Validation_Message )
	private String field_label;
	@Pattern(regexp = Utils.VALIDATION_REGEX, message =Utils.Validation_Message )
	private String field_type;
	@Pattern(regexp = Utils.VALIDATION_REGEX, message =Utils.Validation_Message )
	private String active;
	@Pattern(regexp = Utils.VALIDATION_REGEX, message =Utils.Validation_Message )
	private String field_value;
	private boolean pactive;
	private boolean isdelete;
	private boolean show_app_list;
	private List<Customfields> customfieldslist = new ArrayList<Customfields>();

	public String getField_value() {
		return field_value;
	}

	public void setField_value(String field_value) {
		this.field_value = field_value;
	}

	public boolean isPactive() {
		return pactive;
	}

	public void setPactive(boolean pactive) {
		this.pactive = pactive;
	}

	public String getActive() {
		return active;
	}

	public void setActive(String active) {
		this.active = active;
	}



	public List<Customfields> getCustomfieldslist() {
		return customfieldslist;
	}

	public void setCustomfieldslist(List<Customfields> customfieldslist) {
		this.customfieldslist = customfieldslist;
	}

	public String getClient_id() {
		return client_id;
	}

	public void setClient_id(String client_id) {
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

	public String getField_name() {
		return field_name;
	}

	public void setField_name(String field_name) {
		this.field_name = field_name;
	}

	public String getField_label() {
		return field_label;
	}

	public void setField_label(String field_label) {
		this.field_label = field_label;
	}

	public String getField_type() {
		return field_type;
	}

	public void setField_type(String field_type) {
		this.field_type = field_type;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getGuid() {
		return guid;
	}

	public void setGuid(String guid) {
		this.guid = guid;
	}

	public String getClient_name() {
		return client_name;
	}

	public void setClient_name(String client_name) {
		this.client_name = client_name;
	}

	public String getAudit_group_name() {
		return audit_group_name;
	}

	public void setAudit_group_name(String audit_group_name) {
		this.audit_group_name = audit_group_name;
	}

	public String getAudit_type_name() {
		return audit_type_name;
	}

	public void setAudit_type_name(String audit_type_name) {
		this.audit_type_name = audit_type_name;
	}

	public boolean isIsdelete() {
		return isdelete;
	}

	public void setIsdelete(boolean isdelete) {
		this.isdelete = isdelete;
	}

	public boolean isShow_app_list() {
		return show_app_list;
	}

	public void setShow_app_list(boolean show_app_list) {
		this.show_app_list = show_app_list;
	}

}
