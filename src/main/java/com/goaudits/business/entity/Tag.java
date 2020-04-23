package com.goaudits.business.entity;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public class Tag implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private String guid;
	private String uid;
	private int client_id;
	private int audit_group_id;
	private int audit_type_id;
	private int id;
	private String tag_code;
	private String tag_description;
	private String passing_level;
	private String sort_order;
	private String tagids;
	private String audit_type_name;
	private String client_name;
	private List<Tag> reportTagList = new ArrayList<Tag>();
	
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
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTag_code() {
		return tag_code;
	}
	public void setTag_code(String tag_code) {
		this.tag_code = tag_code;
	}
	public String getTag_description() {
		return tag_description;
	}
	public void setTag_description(String tag_description) {
		this.tag_description = tag_description;
	}
	public String getPassing_level() {
		return passing_level;
	}
	public void setPassing_level(String passing_level) {
		this.passing_level = passing_level;
	}
	public String getSort_order() {
		return sort_order;
	}
	public void setSort_order(String sort_order) {
		this.sort_order = sort_order;
	}
	public List<Tag> getReportTagList() {
		return reportTagList;
	}
	public void setReportTagList(List<Tag> reportTagList) {
		this.reportTagList = reportTagList;
	}
	public String getTagids() {
		return tagids;
	}
	public void setTagids(String tagids) {
		this.tagids = tagids;
	}
	public String getAudit_type_name() {
		return audit_type_name;
	}
	public void setAudit_type_name(String audit_type_name) {
		this.audit_type_name = audit_type_name;
	}
	public String getClient_name() {
		return client_name;
	}
	public void setClient_name(String client_name) {
		this.client_name = client_name;
	}
	

}
