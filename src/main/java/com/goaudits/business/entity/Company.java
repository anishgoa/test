package com.goaudits.business.entity;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public class Company {
	private String guid;
	private String client_id;
	private String client_name;
	private String submit_button_text;
	private String inputLogo;
	private boolean active;
	private String logo;
	private byte[] logo_binary;
	private String uid;
	private String short_name;
	private int sort_order;
	private int drag_index;
	private int drop_index;
	private boolean image_required=true;
	private boolean company_clone;
	private List<ActionPlanAssignee> actionlist = new ArrayList<ActionPlanAssignee>();

	public List<ActionPlanAssignee> getActionlist() {
		return actionlist;
	}

	public void setActionlist(List<ActionPlanAssignee> actionlist) {
		this.actionlist = actionlist;
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

	public String getClient_name() {
		return client_name;
	}

	public void setClient_name(String client_name) {
		this.client_name = client_name;
	}

	public String getSubmit_button_text() {
		return submit_button_text;
	}

	public void setSubmit_button_text(String submit_button_text) {
		this.submit_button_text = submit_button_text;
	}

	public String getInputLogo() {
		return inputLogo;
	}

	public void setInputLogo(String inputLogo) {
		this.inputLogo = inputLogo;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public byte[] getLogo_binary() {
		return logo_binary;
	}

	public void setLogo_binary(byte[] logo_binary) {
		this.logo_binary = logo_binary;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getShort_name() {
		return short_name;
	}

	public void setShort_name(String short_name) {
		this.short_name = short_name;
	}

	public int getSort_order() {
		return sort_order;
	}

	public void setSort_order(int sort_order) {
		this.sort_order = sort_order;
	}

	public int getDrag_index() {
		return drag_index;
	}

	public void setDrag_index(int drag_index) {
		this.drag_index = drag_index;
	}

	public int getDrop_index() {
		return drop_index;
	}

	public void setDrop_index(int drop_index) {
		this.drop_index = drop_index;
	}

	public boolean isImage_required() {
		return image_required;
	}

	public void setImage_required(boolean image_required) {
		this.image_required = image_required;
	}

	public boolean isCompany_clone() {
		return company_clone;
	}

	public void setCompany_clone(boolean company_clone) {
		this.company_clone = company_clone;
	}


	

}
