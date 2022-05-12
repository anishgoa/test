package com.goaudits.business.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;

import org.springframework.stereotype.Repository;

import com.goaudits.business.util.Utils;

@Repository
public class EmailTemplate implements Serializable {

	private static final long serialVersionUID = 1L;
	@Pattern(regexp = Utils.VALIDATION_REGEX, message =Utils.Validation_Message )
	private String guid;
	@Pattern(regexp = Utils.VALIDATION_REGEX, message =Utils.Validation_Message )
	private String uid;
	private int client_id;
	@Pattern(regexp = Utils.VALIDATION_REGEX, message =Utils.Validation_Message )
	private String client_name;
	private int audit_group_id;
	@Pattern(regexp = Utils.VALIDATION_REGEX, message =Utils.Validation_Message )
	private String audit_group_name;
	private int audit_type_id;
	@Pattern(regexp = Utils.VALIDATION_REGEX, message =Utils.Validation_Message )
	private String audit_type_name;
	@Pattern(regexp = Utils.VALIDATION_REGEX, message =Utils.Validation_Message )
	private String to_email;
	@Pattern(regexp = Utils.VALIDATION_REGEX, message =Utils.Validation_Message )
	private String cc_email;
	@Pattern(regexp = Utils.VALIDATION_REGEX, message =Utils.Validation_Message )
	private String email_sub;
	@Pattern(regexp = Utils.VALIDATION_REGEX, message =Utils.Validation_Message )
	private String email_body;
	@Valid
	List<EmailSubject> emailSubjectList = new ArrayList<EmailSubject>();
	@Valid
	List<EmailMessage> emailMessageList = new ArrayList<EmailMessage>();
	

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

	public String getGuid() {
		return guid;
	}

	public void setGuid(String guid) {
		this.guid = guid;
	}

	public String getTo_email() {
		return to_email;
	}

	public void setTo_email(String to_email) {
		this.to_email = to_email;
	}

	public String getCc_email() {
		return cc_email;
	}

	public void setCc_email(String cc_email) {
		this.cc_email = cc_email;
	}

	public String getEmail_sub() {
		return email_sub;
	}

	public void setEmail_sub(String email_sub) {
		this.email_sub = email_sub;
	}

	public String getEmail_body() {
		return email_body;
	}

	public void setEmail_body(String email_body) {
		this.email_body = email_body;
	}

	public List<EmailSubject> getEmailSubjectList() {
		return emailSubjectList;
	}

	public void setEmailSubjectList(List<EmailSubject> emailSubjectList) {
		this.emailSubjectList = emailSubjectList;
	}

	public List<EmailMessage> getEmailMessageList() {
		return emailMessageList;
	}

	public void setEmailMessageList(List<EmailMessage> emailMessageList) {
		this.emailMessageList = emailMessageList;
	}
	
	

}