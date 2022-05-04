package com.goaudits.business.entity;

import javax.validation.constraints.Pattern;

import org.springframework.stereotype.Repository;

import com.goaudits.business.util.Utils;

@Repository
public class GroupAudit {
	@Pattern(regexp = Utils.VALIDATION_REGEX, message =Utils.Validation_Message )
	String guid;
	@Pattern(regexp = Utils.VALIDATION_REGEX, message =Utils.Validation_Message )
	String parent_audit_type_name;
	@Pattern(regexp = Utils.VALIDATION_REGEX, message =Utils.Validation_Message )
	String sub_title;
	int client_id;
	int parent_audit_id;
	String logo;
	boolean active;
	@Pattern(regexp = Utils.VALIDATION_REGEX, message =Utils.Validation_Message )
	String audit_type_ids;
	@Pattern(regexp = Utils.VALIDATION_REGEX, message =Utils.Validation_Message )
	String client_name;
	@Pattern(regexp = Utils.VALIDATION_REGEX, message =Utils.Validation_Message )
	String audit_type_names;
	@Pattern(regexp = Utils.VALIDATION_REGEX, message =Utils.Validation_Message )
	String toemail;
	@Pattern(regexp = Utils.VALIDATION_REGEX, message =Utils.Validation_Message )
	String ccemail;

	public String getGuid() {
		return guid;
	}

	public void setGuid(String guid) {
		this.guid = guid;
	}

	public String getParent_audit_type_name() {
		return parent_audit_type_name;
	}

	public void setParent_audit_type_name(String parent_audit_type_name) {
		this.parent_audit_type_name = parent_audit_type_name;
	}

	public String getSub_title() {
		return sub_title;
	}

	public void setSub_title(String sub_title) {
		this.sub_title = sub_title;
	}

	public int getClient_id() {
		return client_id;
	}

	public void setClient_id(int client_id) {
		this.client_id = client_id;
	}

	public int getParent_audit_id() {
		return parent_audit_id;
	}

	public void setParent_audit_id(int parent_audit_id) {
		this.parent_audit_id = parent_audit_id;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public String getAudit_type_ids() {
		return audit_type_ids;
	}

	public void setAudit_type_ids(String audit_type_ids) {
		this.audit_type_ids = audit_type_ids;
	}

	public String getClient_name() {
		return client_name;
	}

	public void setClient_name(String client_name) {
		this.client_name = client_name;
	}

	public String getAudit_type_names() {
		return audit_type_names;
	}

	public void setAudit_type_names(String audit_type_names) {
		this.audit_type_names = audit_type_names;
	}

	public String getToemail() {
		return toemail;
	}

	public void setToemail(String toemail) {
		this.toemail = toemail;
	}

	public String getCcemail() {
		return ccemail;
	}

	public void setCcemail(String ccemail) {
		this.ccemail = ccemail;
	}
	

}
