package com.goaudits.business.entity;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;

import com.goaudits.business.util.Utils;


public class SectionItem {

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
	private int section_id;
	@Pattern(regexp = Utils.VALIDATION_REGEX, message =Utils.Validation_Message )
	private String section_name;
	private boolean active;
	@Pattern(regexp = Utils.VALIDATION_REGEX, message =Utils.Validation_Message )
	private String store_name;
	@Pattern(regexp = Utils.VALIDATION_REGEX, message =Utils.Validation_Message )
	private String store_id;
	@Pattern(regexp = Utils.VALIDATION_REGEX, message =Utils.Validation_Message )
	private String section_help;
	@Pattern(regexp = Utils.VALIDATION_REGEX, message =Utils.Validation_Message )
	private String help_color;
	private boolean is_help_bold;
	private boolean is_help_italic;
	private boolean help_text_position;
	
	@Valid
	private List<GroupItem> GroupList = new ArrayList<GroupItem>();

	public SectionItem(QuestionVo n) {
		this.section_id = n.getSection_id();
		this.section_name = n.getSection_name();
		this.client_name = n.getClient_name();
		this.audit_type_name = n.getAudit_type_name();
		this.store_name = n.getStore_name();
		this.section_help = n.getSection_help();
		this.help_color = n.getHelp_color();
		this.is_help_bold = n.isIs_help_bold();
		this.is_help_italic = n.isIs_help_italic();
		this.help_text_position = n.isHelp_text_position();
		this.active=n.isSecactive();
		// add active column here - Todo
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof SectionItem) {
			return ((SectionItem) obj).section_id == section_id;
		}
		return false;
	}

	@Override
	public int hashCode() {
		return this.section_id;
	}

	/**
	 * @return the guid
	 */
	public String getGuid() {
		return guid;
	}

	/**
	 * @param guid the guid to set
	 */
	public void setGuid(String guid) {
		this.guid = guid;
	}

	/**
	 * @return the uid
	 */
	public String getUid() {
		return uid;
	}

	/**
	 * @param uid the uid to set
	 */
	public void setUid(String uid) {
		this.uid = uid;
	}

	/**
	 * @return the client_id
	 */
	public int getClient_id() {
		return client_id;
	}

	/**
	 * @param client_id the client_id to set
	 */
	public void setClient_id(int client_id) {
		this.client_id = client_id;
	}

	/**
	 * @return the client_name
	 */
	public String getClient_name() {
		return client_name;
	}

	/**
	 * @param client_name the client_name to set
	 */
	public void setClient_name(String client_name) {
		this.client_name = client_name;
	}

	/**
	 * @return the audit_group_id
	 */
	public int getAudit_group_id() {
		return audit_group_id;
	}

	/**
	 * @param audit_group_id the audit_group_id to set
	 */
	public void setAudit_group_id(int audit_group_id) {
		this.audit_group_id = audit_group_id;
	}

	/**
	 * @return the audit_group_name
	 */
	public String getAudit_group_name() {
		return audit_group_name;
	}

	/**
	 * @param audit_group_name the audit_group_name to set
	 */
	public void setAudit_group_name(String audit_group_name) {
		this.audit_group_name = audit_group_name;
	}

	/**
	 * @return the audit_type_id
	 */
	public int getAudit_type_id() {
		return audit_type_id;
	}

	/**
	 * @param audit_type_id the audit_type_id to set
	 */
	public void setAudit_type_id(int audit_type_id) {
		this.audit_type_id = audit_type_id;
	}

	/**
	 * @return the audit_type_name
	 */
	public String getAudit_type_name() {
		return audit_type_name;
	}

	/**
	 * @param audit_type_name the audit_type_name to set
	 */
	public void setAudit_type_name(String audit_type_name) {
		this.audit_type_name = audit_type_name;
	}

	/**
	 * @return the section_id
	 */
	public int getSection_id() {
		return section_id;
	}

	/**
	 * @param section_id the section_id to set
	 */
	public void setSection_id(int section_id) {
		this.section_id = section_id;
	}

	/**
	 * @return the section_name
	 */
	public String getSection_name() {
		return section_name;
	}

	/**
	 * @param section_name the section_name to set
	 */
	public void setSection_name(String section_name) {
		this.section_name = section_name;
	}

	/**
	 * @return the active
	 */
	public boolean isActive() {
		return active;
	}

	/**
	 * @param active the active to set
	 */
	public void setActive(boolean active) {
		this.active = active;
	}

	/**
	 * @return the store_name
	 */
	public String getStore_name() {
		return store_name;
	}

	/**
	 * @param store_name the store_name to set
	 */
	public void setStore_name(String store_name) {
		this.store_name = store_name;
	}

	/**
	 * @return the store_id
	 */
	public String getStore_id() {
		return store_id;
	}

	/**
	 * @param store_id the store_id to set
	 */
	public void setStore_id(String store_id) {
		this.store_id = store_id;
	}

	/**
	 * @return the section_help
	 */
	public String getSection_help() {
		return section_help;
	}

	/**
	 * @param section_help the section_help to set
	 */
	public void setSection_help(String section_help) {
		this.section_help = section_help;
	}

	/**
	 * @return the help_color
	 */
	public String getHelp_color() {
		return help_color;
	}

	/**
	 * @param help_color the help_color to set
	 */
	public void setHelp_color(String help_color) {
		this.help_color = help_color;
	}

	/**
	 * @return the is_help_bold
	 */
	public boolean isIs_help_bold() {
		return is_help_bold;
	}

	/**
	 * @param is_help_bold the is_help_bold to set
	 */
	public void setIs_help_bold(boolean is_help_bold) {
		this.is_help_bold = is_help_bold;
	}

	/**
	 * @return the is_help_italic
	 */
	public boolean isIs_help_italic() {
		return is_help_italic;
	}

	/**
	 * @param is_help_italic the is_help_italic to set
	 */
	public void setIs_help_italic(boolean is_help_italic) {
		this.is_help_italic = is_help_italic;
	}

	/**
	 * @return the help_text_position
	 */
	public boolean isHelp_text_position() {
		return help_text_position;
	}

	/**
	 * @param help_text_position the help_text_position to set
	 */
	public void setHelp_text_position(boolean help_text_position) {
		this.help_text_position = help_text_position;
	}

	/**
	 * @return the groupList
	 */
	public List<GroupItem> getGroupList() {
		return GroupList;
	}

	/**
	 * @param groupList the groupList to set
	 */
	public void setGroupList(List<GroupItem> groupList) {
		GroupList = groupList;
	}

	
}
