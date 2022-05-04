package com.goaudits.business.entity;

import java.io.Serializable;

import javax.validation.constraints.Pattern;

import org.springframework.stereotype.Repository;

import com.goaudits.business.util.Utils;

@Repository
public class SectionGroupClone implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Pattern(regexp = Utils.VALIDATION_REGEX, message =Utils.Validation_Message )
	private String guid;
	@Pattern(regexp = Utils.VALIDATION_REGEX, message =Utils.Validation_Message )
	private String uid;
	private int client_id;
	private int audit_group_id;
	private int audit_type_id;
	private int section_id;
	@Pattern(regexp = Utils.VALIDATION_REGEX, message =Utils.Validation_Message )
	private String section_help;
	@Pattern(regexp = Utils.VALIDATION_REGEX, message =Utils.Validation_Message )
	private String help_color;
	private boolean is_help_bold;
	private boolean is_help_italic;
	private boolean help_text_position;
	@Pattern(regexp = Utils.VALIDATION_REGEX, message =Utils.Validation_Message )
	private String new_section_name;
	private int group_id;
	@Pattern(regexp = Utils.VALIDATION_REGEX, message =Utils.Validation_Message )
	private String new_group_name;
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
	 * @return the new_section_name
	 */
	public String getNew_section_name() {
		return new_section_name;
	}
	/**
	 * @param new_section_name the new_section_name to set
	 */
	public void setNew_section_name(String new_section_name) {
		this.new_section_name = new_section_name;
	}
	/**
	 * @return the group_id
	 */
	public int getGroup_id() {
		return group_id;
	}
	/**
	 * @param group_id the group_id to set
	 */
	public void setGroup_id(int group_id) {
		this.group_id = group_id;
	}
	/**
	 * @return the new_group_name
	 */
	public String getNew_group_name() {
		return new_group_name;
	}
	/**
	 * @param new_group_name the new_group_name to set
	 */
	public void setNew_group_name(String new_group_name) {
		this.new_group_name = new_group_name;
	}
	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	
}
