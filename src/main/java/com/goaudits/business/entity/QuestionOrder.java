package com.goaudits.business.entity;

import java.io.Serializable;

import javax.validation.constraints.Pattern;

import org.springframework.stereotype.Repository;

import com.goaudits.business.util.Utils;

@Repository
public class QuestionOrder implements Serializable{

	private static final long serialVersionUID = 1L;
	@Pattern(regexp = Utils.VALIDATION_REGEX, message =Utils.Validation_Message,flags = Pattern.Flag.DOTALL)
	private String guid;
	@Pattern(regexp = Utils.VALIDATION_REGEX, message =Utils.Validation_Message,flags = Pattern.Flag.DOTALL)
	private String uid;
	private int client_id;
	private int audit_group_id;
	private int audit_type_id;
	private int section_id; 
	private int  draggroup_id;
	private int  dropgroup_id;
	private int dragquestion_order;
	private int dropquestion_order;
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
	public int getDraggroup_id() {
		return draggroup_id;
	}
	public void setDraggroup_id(int draggroup_id) {
		this.draggroup_id = draggroup_id;
	}
	public int getDropgroup_id() {
		return dropgroup_id;
	}
	public void setDropgroup_id(int dropgroup_id) {
		this.dropgroup_id = dropgroup_id;
	}
	public int getDragquestion_order() {
		return dragquestion_order;
	}
	public void setDragquestion_order(int dragquestion_order) {
		this.dragquestion_order = dragquestion_order;
	}
	public int getDropquestion_order() {
		return dropquestion_order;
	}
	public void setDropquestion_order(int dropquestion_order) {
		this.dropquestion_order = dropquestion_order;
	}
	
}
