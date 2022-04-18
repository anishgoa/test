package com.goaudits.business.entity;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.goaudits.business.util.Utils;

public class GroupItem {
	
	@Pattern(regexp = Utils.VALIDATION_REGEX, message = "Special character = is not allowed")
	private String guid;
	@Pattern(regexp = Utils.VALIDATION_REGEX, message = "Special character = is not allowed")
	private String uid;
	private int client_id;
	private int audit_group_id;
	private int audit_type_id;
	private int section_id;
	private int group_id;
	@Pattern(regexp = Utils.VALIDATION_REGEX, message = "Special character = is not allowed")
	private String group_name = null;
	private boolean active;
	@Pattern(regexp = Utils.VALIDATION_REGEX, message = "Special character = is not allowed")
	private String audit_token;
	private int group_order;
	
	@JsonIgnore
	private boolean inactiveq=true;
	private List<QuestionItem> QuestionList = new ArrayList<QuestionItem>();
	
	public GroupItem(QuestionVo p) {
		this.section_id = p.getSection_id();
		this.group_id = p.getGroup_id();
		this.group_name = p.getGroup_name();
		this.active = p.isGrpacive();
		this.group_order=p.getGroup_order();
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof GroupItem) {
			return ((GroupItem) obj).group_id == group_id;
		}
		return false;
	}

	@Override
	public int hashCode() {
		return this.group_id;
	}
	
	public String getAudit_token() {
		return audit_token;
	}
	public void setAudit_token(String audit_token) {
		this.audit_token = audit_token;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
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
	public int getSection_id() {
		return section_id;
	}
	public void setSection_id(int section_id) {
		this.section_id = section_id;
	}
	public int getGroup_id() {
		return group_id;
	}
	public void setGroup_id(int group_id) {
		this.group_id = group_id;
	}
	public String getGroup_name() {
		return group_name;
	}
	public void setGroup_name(String group_name) {
		this.group_name = group_name;
	}
	/**
	 * @return the inactiveq
	 */
	public boolean isInactiveq() {
		return inactiveq;
	}
	/**
	 * @param inactiveq the inactiveq to set
	 */
	public void setInactiveq(boolean inactiveq) {
		this.inactiveq = inactiveq;
	}

	/**
	 * @return the questionList
	 */
	public List<QuestionItem> getQuestionList() {
		return QuestionList;
	}

	/**
	 * @param questionList the questionList to set
	 */
	public void setQuestionList(List<QuestionItem> questionList) {
		QuestionList = questionList;
	}

	/**
	 * @return the group_order
	 */
	public int getGroup_order() {
		return group_order;
	}

	/**
	 * @param group_order the group_order to set
	 */
	public void setGroup_order(int group_order) {
		this.group_order = group_order;
	}

	
	
}
