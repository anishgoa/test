package com.goaudits.business.entity;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public class Personseen {

	String guid;
	String uid;
	String client_id;
	String audit_type_ids;
	String store_ids;
	String person_name, person_email;
	boolean active;
	int audit_type_id, store_id;
	int id = 0;
	String client_name, audit_type_name, store_name;
	String fromemail, fromclient_id;

	private List<Company> clientarr = new ArrayList<Company>();
	private List<AuditName> audarry = new ArrayList<AuditName>();

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

	public String getPerson_name() {
		return person_name;
	}

	public void setPerson_name(String person_name) {
		this.person_name = person_name;
	}

	public String getPerson_email() {
		return person_email;
	}

	public void setPerson_email(String person_email) {
		this.person_email = person_email;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public String getClient_id() {
		return client_id;
	}

	public void setClient_id(String client_id) {
		this.client_id = client_id;
	}

	public String getAudit_type_ids() {
		return audit_type_ids;
	}

	public void setAudit_type_ids(String audit_type_ids) {
		this.audit_type_ids = audit_type_ids;
	}

	public String getStore_ids() {
		return store_ids;
	}

	public void setStore_ids(String store_ids) {
		this.store_ids = store_ids;
	}

	public int getAudit_type_id() {
		return audit_type_id;
	}

	public void setAudit_type_id(int audit_type_id) {
		this.audit_type_id = audit_type_id;
	}

	public int getStore_id() {
		return store_id;
	}

	public void setStore_id(int store_id) {
		this.store_id = store_id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getClient_name() {
		return client_name;
	}

	public void setClient_name(String client_name) {
		this.client_name = client_name;
	}

	public String getAudit_type_name() {
		return audit_type_name;
	}

	public void setAudit_type_name(String audit_type_name) {
		this.audit_type_name = audit_type_name;
	}

	public String getStore_name() {
		return store_name;
	}

	public void setStore_name(String store_name) {
		this.store_name = store_name;
	}

	public String getFromemail() {
		return fromemail;
	}

	public void setFromemail(String fromemail) {
		this.fromemail = fromemail;
	}

	public String getFromclient_id() {
		return fromclient_id;
	}

	public void setFromclient_id(String fromclient_id) {
		this.fromclient_id = fromclient_id;
	}

	public List<Company> getClientarr() {
		return clientarr;
	}

	public void setClientarr(List<Company> clientarr) {
		this.clientarr = clientarr;
	}

	public List<AuditName> getAudarry() {
		return audarry;
	}

	public void setAudarry(List<AuditName> audarry) {
		this.audarry = audarry;
	}

}
