package com.goaudits.business.entity;

import javax.validation.constraints.Pattern;

import org.springframework.stereotype.Repository;

import com.goaudits.business.util.Utils;

@Repository
public class GroupOrder {
	@Pattern(regexp = Utils.VALIDATION_REGEX, message =Utils.Validation_Message,flags = Pattern.Flag.DOTALL)
	String guid;
	@Pattern(regexp = Utils.VALIDATION_REGEX, message =Utils.Validation_Message,flags = Pattern.Flag.DOTALL)
	String uid;
	int client_id;
	int audit_group_id;
	int audit_type_id;
	int dropsection_id;
	int dragedsectionid;
	int draggroup_order;
	int dropgroup_order;
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
	 * @return the dropsection_id
	 */
	public int getDropsection_id() {
		return dropsection_id;
	}
	/**
	 * @param dropsection_id the dropsection_id to set
	 */
	public void setDropsection_id(int dropsection_id) {
		this.dropsection_id = dropsection_id;
	}
	/**
	 * @return the dragedsectionid
	 */
	public int getDragedsectionid() {
		return dragedsectionid;
	}
	/**
	 * @param dragedsectionid the dragedsectionid to set
	 */
	public void setDragedsectionid(int dragedsectionid) {
		this.dragedsectionid = dragedsectionid;
	}
	/**
	 * @return the draggroup_order
	 */
	public int getDraggroup_order() {
		return draggroup_order;
	}
	/**
	 * @param draggroup_order the draggroup_order to set
	 */
	public void setDraggroup_order(int draggroup_order) {
		this.draggroup_order = draggroup_order;
	}
	/**
	 * @return the dropgroup_order
	 */
	public int getDropgroup_order() {
		return dropgroup_order;
	}
	/**
	 * @param dropgroup_order the dropgroup_order to set
	 */
	public void setDropgroup_order(int dropgroup_order) {
		this.dropgroup_order = dropgroup_order;
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
	
	
	

}
