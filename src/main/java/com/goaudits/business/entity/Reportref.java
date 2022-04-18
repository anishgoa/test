package com.goaudits.business.entity;

import javax.validation.constraints.Pattern;

import org.springframework.stereotype.Repository;

import com.goaudits.business.util.Utils;

@Repository
public class Reportref {
	String client_id;
	String audit_id;
	@Pattern(regexp = Utils.VALIDATION_REGEX, message = "Special character = is not allowed")
	String guid;
	int min;
	int max;
	@Pattern(regexp = Utils.VALIDATION_REGEX, message = "Special character = is not allowed")
	String search;
	public String getClient_id() {
		return client_id;
	}
	public void setClient_id(String client_id) {
		this.client_id = client_id;
	}
	public String getAudit_id() {
		return audit_id;
	}
	public void setAudit_id(String audit_id) {
		this.audit_id = audit_id;
	}
	public String getGuid() {
		return guid;
	}
	public void setGuid(String guid) {
		this.guid = guid;
	}
	public int getMin() {
		return min;
	}
	public void setMin(int min) {
		this.min = min;
	}
	public int getMax() {
		return max;
	}
	public void setMax(int max) {
		this.max = max;
	}
	public String getSearch() {
		return search;
	}
	public void setSearch(String search) {
		this.search = search;
	}
	

}
