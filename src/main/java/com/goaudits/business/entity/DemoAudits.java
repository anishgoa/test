package com.goaudits.business.entity;

import org.springframework.stereotype.Repository;

@Repository
public class DemoAudits {
	private String audit_id;
	private String guid;
	private String uid;
	private String client_id;
	private String audit_type_id;
	private String section_id;
	private String group_id;
	private String store_id;
	private String audit_date;
	private String display_audit_date;
	private String seq_no;
	private String comments;
	private String client_name = null;
	private String audit_type_name = null;
	private String section_name = null;
	private String group_name = null;
	private String auditor_name;
	private String status;
	private String store_name;
	private String audit_group_id;
	private String start_date;
	private String end_date;
	private String person_seen;
	private String person_seen_label;
	private String person_seen_mandatory;
	private String showif_optional;
	private int actioncount;
	private int status_id;
	private int tab;
	private int wkcount;
	private String score;
	private String status_name;
	private int min;
	private int max;
	private int count;
	String search_item;
	String sort;
	String order;
	int type_of_audit;
	boolean perdelete;
//	private List<Customfields> Customfieldslist = new ArrayList<Customfields>();
//	private List<Location> strarry = new ArrayList<Location>();
	
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
	public String getSection_id() {
		return section_id;
	}
	public void setSection_id(String section_id) {
		this.section_id = section_id;
	}
	public String getGroup_id() {
		return group_id;
	}
	public void setGroup_id(String group_id) {
		this.group_id = group_id;
	}
	public String getStore_id() {
		return store_id;
	}
	public void setStore_id(String store_id) {
		this.store_id = store_id;
	}
	public String getAudit_date() {
		return audit_date;
	}
	public void setAudit_date(String audit_date) {
		this.audit_date = audit_date;
	}
	public String getDisplay_audit_date() {
		return display_audit_date;
	}
	public void setDisplay_audit_date(String display_audit_date) {
		this.display_audit_date = display_audit_date;
	}
	public String getSeq_no() {
		return seq_no;
	}
	public void setSeq_no(String seq_no) {
		this.seq_no = seq_no;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
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
	public String getSection_name() {
		return section_name;
	}
	public void setSection_name(String section_name) {
		this.section_name = section_name;
	}
	public String getGroup_name() {
		return group_name;
	}
	public void setGroup_name(String group_name) {
		this.group_name = group_name;
	}
	public String getAuditor_name() {
		return auditor_name;
	}
	public void setAuditor_name(String auditor_name) {
		this.auditor_name = auditor_name;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getStore_name() {
		return store_name;
	}
	public void setStore_name(String store_name) {
		this.store_name = store_name;
	}
	public String getAudit_group_id() {
		return audit_group_id;
	}
	public void setAudit_group_id(String audit_group_id) {
		this.audit_group_id = audit_group_id;
	}
	public String getStart_date() {
		return start_date;
	}
	public void setStart_date(String start_date) {
		this.start_date = start_date;
	}
	public String getEnd_date() {
		return end_date;
	}
	public void setEnd_date(String end_date) {
		this.end_date = end_date;
	}
	public String getPerson_seen() {
		return person_seen;
	}
	public void setPerson_seen(String person_seen) {
		this.person_seen = person_seen;
	}
	public String getPerson_seen_label() {
		return person_seen_label;
	}
	public void setPerson_seen_label(String person_seen_label) {
		this.person_seen_label = person_seen_label;
	}
	public String getPerson_seen_mandatory() {
		return person_seen_mandatory;
	}
	public void setPerson_seen_mandatory(String person_seen_mandatory) {
		this.person_seen_mandatory = person_seen_mandatory;
	}
	public String getShowif_optional() {
		return showif_optional;
	}
	public void setShowif_optional(String showif_optional) {
		this.showif_optional = showif_optional;
	}
	public int getActioncount() {
		return actioncount;
	}
	public void setActioncount(int actioncount) {
		this.actioncount = actioncount;
	}
	public int getStatus_id() {
		return status_id;
	}
	public void setStatus_id(int status_id) {
		this.status_id = status_id;
	}
	public int getTab() {
		return tab;
	}
	public void setTab(int tab) {
		this.tab = tab;
	}
	public int getWkcount() {
		return wkcount;
	}
	public void setWkcount(int wkcount) {
		this.wkcount = wkcount;
	}
	public String getScore() {
		return score;
	}
	public void setScore(String score) {
		this.score = score;
	}
	public String getStatus_name() {
		return status_name;
	}
	public void setStatus_name(String status_name) {
		this.status_name = status_name;
	}
	/**
	 * @return the min
	 */
	public int getMin() {
		return min;
	}
	/**
	 * @param min the min to set
	 */
	public void setMin(int min) {
		this.min = min;
	}
	/**
	 * @return the max
	 */
	public int getMax() {
		return max;
	}
	/**
	 * @param max the max to set
	 */
	public void setMax(int max) {
		this.max = max;
	}
	/**
	 * @return the count
	 */
	public int getCount() {
		return count;
	}
	/**
	 * @param count the count to set
	 */
	public void setCount(int count) {
		this.count = count;
	}
	/**
	 * @return the search_item
	 */
	public String getSearch_item() {
		return search_item;
	}
	/**
	 * @param search_item the search_item to set
	 */
	public void setSearch_item(String search_item) {
		this.search_item = search_item;
	}
	/**
	 * @return the sort
	 */
	public String getSort() {
		return sort;
	}
	/**
	 * @param sort the sort to set
	 */
	public void setSort(String sort) {
		this.sort = sort;
	}
	/**
	 * @return the order
	 */
	public String getOrder() {
		return order;
	}
	/**
	 * @param order the order to set
	 */
	public void setOrder(String order) {
		this.order = order;
	}
	/**
	 * @return the audit_id
	 */
	public String getAudit_id() {
		return audit_id;
	}
	/**
	 * @param audit_id the audit_id to set
	 */
	public void setAudit_id(String audit_id) {
		this.audit_id = audit_id;
	}
	/**
	 * @return the type_of_audit
	 */
	public int getType_of_audit() {
		return type_of_audit;
	}
	/**
	 * @param type_of_audit the type_of_audit to set
	 */
	public void setType_of_audit(int type_of_audit) {
		this.type_of_audit = type_of_audit;
	}
	public boolean isPerdelete() {
		return perdelete;
	}
	public void setPerdelete(boolean perdelete) {
		this.perdelete = perdelete;
	}
	
	

	
}
