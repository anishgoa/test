package com.goaudits.business.entity;

import java.io.Serializable;

import javax.validation.constraints.Pattern;

import org.springframework.stereotype.Repository;

import com.goaudits.business.util.Utils;

@Repository
public class Questionimage implements Serializable{

	private static final long serialVersionUID = 1L;
	@Pattern(regexp = Utils.VALIDATION_REGEX, message = "Special character = is not allowed")
	String image;
    byte[] binaryimage;
    String image_path;
    @Pattern(regexp = Utils.VALIDATION_REGEX, message = "Special character = is not allowed")
    String image_thumbnail;
    int isdeleted;
    int client_id;
    @Pattern(regexp = Utils.VALIDATION_REGEX, message = "Special character = is not allowed")
    String image_public_id;
    int image_id;
    int audit_type_id;
    
	public String getImage_thumbnail() {
		return image_thumbnail;
	}
	public void setImage_thumbnail(String image_thumbnail) {
		this.image_thumbnail = image_thumbnail;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public byte[] getBinaryimage() {
		return binaryimage;
	}
	public void setBinaryimage(byte[] binaryimage) {
		this.binaryimage = binaryimage;
	}
	public String getImage_path() {
		return image_path;
	}
	public void setImage_path(String image_path) {
		this.image_path = image_path;
	}
	
	
	public int getIsdeleted() {
		return isdeleted;
	}
	public void setIsdeleted(int isdeleted) {
		this.isdeleted = isdeleted;
	}
	public int getClient_id() {
		return client_id;
	}
	public void setClient_id(int client_id) {
		this.client_id = client_id;
	}
	public String getImage_public_id() {
		return image_public_id;
	}
	public void setImage_public_id(String image_public_id) {
		this.image_public_id = image_public_id;
	}
	public int getImage_id() {
		return image_id;
	}
	public void setImage_id(int image_id) {
		this.image_id = image_id;
	}
	public int getAudit_type_id() {
		return audit_type_id;
	}
	public void setAudit_type_id(int audit_type_id) {
		this.audit_type_id = audit_type_id;
	}
	
	
}
