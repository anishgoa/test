package com.goaudits.business.entity;

import java.io.Serializable;

import javax.validation.constraints.Pattern;

import org.springframework.stereotype.Repository;

import com.goaudits.business.util.Utils;

@Repository
public class Questactimage implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Pattern(regexp = Utils.VALIDATION_REGEX, message =Utils.Validation_Message,flags = Pattern.Flag.DOTALL)
	private String guid;
	@Pattern(regexp = Utils.VALIDATION_REGEX, message =Utils.Validation_Message,flags = Pattern.Flag.DOTALL)
	private String uid;
	private int client_id;
	private int store_id;
	private int audit_group_id;
	private int audit_type_id;
	private int  question_no;
	@Pattern(regexp = Utils.VALIDATION_REGEX, message =Utils.Validation_Message,flags = Pattern.Flag.DOTALL)
	private String audit_date;
	private int seq_no;
	private byte[] action_thumbnail;
	private byte[] action_imagebi=null;
	int image_id;
	String action_image;
	@Pattern(regexp = Utils.VALIDATION_REGEX, message =Utils.Validation_Message,flags = Pattern.Flag.DOTALL)
	String  capture_time;
	String cloud_image_path;
	@Pattern(regexp = Utils.VALIDATION_REGEX, message =Utils.Validation_Message,flags = Pattern.Flag.DOTALL)
	String cloud_image_public_id;
	@Pattern(regexp = Utils.VALIDATION_REGEX, message =Utils.Validation_Message,flags = Pattern.Flag.DOTALL)
	String cloud_image_thumbnail;
		
	public byte[] getAction_thumbnail() {
		return action_thumbnail;
	}
	public void setAction_thumbnail(byte[] action_thumbnail) {
		this.action_thumbnail = action_thumbnail;
	}
	public byte[] getAction_imagebi() {
		return action_imagebi;
	}
	public void setAction_imagebi(byte[] action_imagebi) {
		this.action_imagebi = action_imagebi;
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
	public int getStore_id() {
		return store_id;
	}
	public void setStore_id(int store_id) {
		this.store_id = store_id;
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
	public int getQuestion_no() {
		return question_no;
	}
	public void setQuestion_no(int question_no) {
		this.question_no = question_no;
	}
	public String getAudit_date() {
		return audit_date;
	}
	public void setAudit_date(String audit_date) {
		this.audit_date = audit_date;
	}
	public int getSeq_no() {
		return seq_no;
	}
	public void setSeq_no(int seq_no) {
		this.seq_no = seq_no;
	}
	public int getImage_id() {
		return image_id;
	}
	public void setImage_id(int image_id) {
		this.image_id = image_id;
	}
	public String getAction_image() {
		return action_image;
	}
	public void setAction_image(String action_image) {
		this.action_image = action_image;
	}
	public String getCapture_time() {
		return capture_time;
	}
	public void setCapture_time(String capture_time) {
		this.capture_time = capture_time;
	}
	public String getCloud_image_path() {
		return cloud_image_path;
	}
	public void setCloud_image_path(String cloud_image_path) {
		this.cloud_image_path = cloud_image_path;
	}
	public String getCloud_image_public_id() {
		return cloud_image_public_id;
	}
	public void setCloud_image_public_id(String cloud_image_public_id) {
		this.cloud_image_public_id = cloud_image_public_id;
	}
	public String getCloud_image_thumbnail() {
		return cloud_image_thumbnail;
	}
	public void setCloud_image_thumbnail(String cloud_image_thumbnail) {
		this.cloud_image_thumbnail = cloud_image_thumbnail;
	}
	
	

}
