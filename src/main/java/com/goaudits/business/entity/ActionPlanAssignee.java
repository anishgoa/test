package com.goaudits.business.entity;

import org.springframework.stereotype.Repository;

@Repository
public class ActionPlanAssignee {
	private String guid;
	private String client_id;
	private String audit_type_id;
	private String store_id;
	private String person_number;
	private String person_name;
	private String person_email;
	private String department;
	private boolean isapproval_required;
	private boolean make_default;
    private String common_action_email;
    
    
	public String getCommon_action_email() {
		return common_action_email;
	}

	public void setCommon_action_email(String common_action_email) {
		this.common_action_email = common_action_email;
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

	public String getAudit_type_id() {
		return audit_type_id;
	}

	public void setAudit_type_id(String audit_type_id) {
		this.audit_type_id = audit_type_id;
	}

	public String getStore_id() {
		return store_id;
	}

	public void setStore_id(String store_id) {
		this.store_id = store_id;
	}

	public String getPerson_number() {
		return person_number;
	}

	public void setPerson_number(String person_number) {
		this.person_number = person_number;
	}

	public String getPerson_name() {
		return person_name;
	}

	public void setPerson_name(String person_name) {
		this.person_name = person_name;
	}

	public String getPerson_email() {
		return person_email;
	}

	public void setPerson_email(String person_email) {
		this.person_email = person_email;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public boolean isIsapproval_required() {
		return isapproval_required;
	}

	public void setIsapproval_required(boolean isapproval_required) {
		this.isapproval_required = isapproval_required;
	}

	public boolean isMake_default() {
		return make_default;
	}

	public void setMake_default(boolean make_default) {
		this.make_default = make_default;
	}

}
