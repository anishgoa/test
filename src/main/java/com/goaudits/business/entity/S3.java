package com.goaudits.business.entity;

import org.springframework.stereotype.Repository;

@Repository
public class S3 {
	String file_name;
	String timestamp;
	String signature;
	String name;
	String client_id;
	String audit_type_id;
	String question_no;
	String client_name;
	String page;
	String folderpath;
	String audit_type_name;
	String uid;
	String guid;

	public String getFolderpath() {
		return folderpath;
	}

	public void setFolderpath(String folderpath) {
		this.folderpath = folderpath;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}

	public String getFile_name() {
		return file_name;
	}

	public void setFile_name(String file_name) {
		this.file_name = file_name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getClient_id() {
		return client_id;
	}

	public void setClient_id(String client_id) {
		this.client_id = client_id;
	}

	public String getAudit_type_id() {
		return audit_type_id;
	}

	public void setAudit_type_id(String audit_type_id) {
		this.audit_type_id = audit_type_id;
	}

	public String getQuestion_no() {
		return question_no;
	}

	public void setQuestion_no(String question_no) {
		this.question_no = question_no;
	}

	public String getClient_name() {
		return client_name;
	}

	public void setClient_name(String client_name) {
		this.client_name = client_name;
	}

	public String getPage() {
		return page;
	}

	public void setPage(String page) {
		this.page = page;
	}

	public String getAudit_type_name() {
		return audit_type_name;
	}

	public void setAudit_type_name(String audit_type_name) {
		this.audit_type_name = audit_type_name;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getGuid() {
		return guid;
	}

	public void setGuid(String guid) {
		this.guid = guid;
	}

}
