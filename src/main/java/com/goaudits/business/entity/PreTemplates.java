package com.goaudits.business.entity;

import java.io.Serializable;

import javax.validation.constraints.Pattern;

import org.springframework.stereotype.Repository;

import com.goaudits.business.util.Utils;


@Repository
public class PreTemplates implements Serializable{
	 private static final long serialVersionUID = 1L;
	 
	@Pattern(regexp = Utils.VALIDATION_REGEX, message =Utils.Validation_Message )
	private String client_id;
	private int audit_group_id;
	private int audit_type_id;
	private int pre_client_id;
	private int pre_audit_type_id;
	@Pattern(regexp = Utils.VALIDATION_REGEX, message =Utils.Validation_Message )
	 private String pre_audit_type_name;
	@Pattern(regexp = Utils.VALIDATION_REGEX, message =Utils.Validation_Message )
	private String guid;
	@Pattern(regexp = Utils.VALIDATION_REGEX, message =Utils.Validation_Message )
	private String uid;
	private boolean validate;

	public boolean isValidate() {
		return validate;
	}
	public void setValidate(boolean validate) {
		this.validate = validate;
	}
	public String getPre_audit_type_name() {
		return pre_audit_type_name;
	}
	public void setPre_audit_type_name(String pre_audit_type_name) {
		this.pre_audit_type_name = pre_audit_type_name;
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
	public int getPre_client_id() {
		return pre_client_id;
	}
	public void setPre_client_id(int pre_client_id) {
		this.pre_client_id = pre_client_id;
	}
	public int getPre_audit_type_id() {
		return pre_audit_type_id;
	}
	public void setPre_audit_type_id(int pre_audit_type_id) {
		this.pre_audit_type_id = pre_audit_type_id;
	}
	
	
	

}
