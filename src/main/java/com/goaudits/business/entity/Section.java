package com.goaudits.business.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public class Section implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String guid;
	private String uid;
	private int client_id;
	private String client_name;
	private int audit_group_id;
	private String audit_group_name;
	private int audit_type_id;
	private String audit_type_name;	
	private int section_id;
	private String section_name;
	private boolean active;
	private String store_name;
	private String store_id;
	private String section_help;
	private String help_color;
	private boolean is_help_bold;
	private boolean is_help_italic;
	private boolean help_text_position;
	
	private List<Group> GroupList = new ArrayList<Group>();
	
	
	
	public boolean isHelp_text_position() {
		return help_text_position;
	}
	public void setHelp_text_position(boolean help_text_position) {
		this.help_text_position = help_text_position;
	}
	public String getStore_name() {
		return store_name;
	}
	public void setStore_name(String store_name) {
		this.store_name = store_name;
	}
	public String getStore_id() {
		return store_id;
	}
	public void setStore_id(String store_id) {
		this.store_id = store_id;
	}
	public List<Group> getGroupList() {
		return GroupList;
	}
	public void setGroupList(List<Group> groupList) {
		GroupList = groupList;
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

	public String getSection_name() {
		return section_name;
	}
	public void setSection_name(String section_name) {
		this.section_name = section_name;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	public String getSection_help() {
		return section_help;
	}
	public void setSection_help(String section_help) {
		this.section_help = section_help;
	}
	public String getHelp_color() {
		return help_color;
	}
	public void setHelp_color(String help_color) {
		this.help_color = help_color;
	}
	public boolean isIs_help_bold() {
		return is_help_bold;
	}
	public void setIs_help_bold(boolean is_help_bold) {
		this.is_help_bold = is_help_bold;
	}
	public boolean isIs_help_italic() {
		return is_help_italic;
	}
	public void setIs_help_italic(boolean is_help_italic) {
		this.is_help_italic = is_help_italic;
	}
	
}
