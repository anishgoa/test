package com.goaudits.business.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public class Report implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String guid;
	private String uid;
	private int client_id;
	private String client_name;
	private int audit_group_id=1;
	private String audit_group_name;
	private int audit_type_id;
	private String audit_type_name;	
	private int template_id;
	private String template_name;
	private boolean active;	
    private String report_path;
    private String description=null;
    private String  exe_summary=null;
    private byte[] report_logo=null;
    private String reportlogo=null;
    private String  confidentiality;
    private String  client_displayname;
    private String template_desc;
    private int   is_customizable;
    private String template_params_id;
    private String leftlogo;
    private byte[] leftlogobi;
    private String template_descrition;
    private String show_edit;
    
    List<ReportImage> reportImageList=new ArrayList<ReportImage>();
	public String getTemplate_desc() {
		return template_desc;
	}
	public void setTemplate_desc(String template_desc) {
		this.template_desc = template_desc;
	}
	public int getIs_customizable() {
		return is_customizable;
	}
	public void setIs_customizable(int is_customizable) {
		this.is_customizable = is_customizable;
	}
	public String getTemplate_params_id() {
		return template_params_id;
	}
	public void setTemplate_params_id(String template_params_id) {
		this.template_params_id = template_params_id;
	}
	public String getReport_path() {
		return report_path;
	}
	public void setReport_path(String report_path) {
		this.report_path = report_path;
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
	public String getClient_displayname() {
		return client_displayname;
	}
	public void setClient_displayname(String client_displayname) {
		this.client_displayname = client_displayname;
	}
	public int getTemplate_id() {
		return template_id;
	}
	public void setTemplate_id(int template_id) {
		this.template_id = template_id;
	}
	public String getExe_summary() {
		return exe_summary;
	}
	public void setExe_summary(String exe_summary) {
		this.exe_summary = exe_summary;
	}
	public String getConfidentiality() {
		return confidentiality;
	}
	public void setConfidentiality(String confidentiality) {
		this.confidentiality = confidentiality;
	}
	public byte[] getReport_logo() {
		return report_logo;
	}
	public void setReport_logo(byte[] report_logo) {
		this.report_logo = report_logo;
	}
	public String getReportlogo() {
		return reportlogo;
	}
	public void setReportlogo(String reportlogo) {
		this.reportlogo = reportlogo;
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
	
	public String getTemplate_name() {
		return template_name;
	}
	public void setTemplate_name(String template_name) {
		this.template_name = template_name;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getLeftlogo() {
		return leftlogo;
	}
	public void setLeftlogo(String leftlogo) {
		this.leftlogo = leftlogo;
	}
	public byte[] getLeftlogobi() {
		return leftlogobi;
	}
	public void setLeftlogobi(byte[] leftlogobi) {
		this.leftlogobi = leftlogobi;
	}
	/**
	 * @return the template_descrition
	 */
	public String getTemplate_descrition() {
		return template_descrition;
	}
	/**
	 * @param template_descrition the template_descrition to set
	 */
	public void setTemplate_descrition(String template_descrition) {
		this.template_descrition = template_descrition;
	}
	/**
	 * @return the reportImageList
	 */
	public List<ReportImage> getReportImageList() {
		return reportImageList;
	}
	/**
	 * @param reportImageList the reportImageList to set
	 */
	public void setReportImageList(List<ReportImage> reportImageList) {
		this.reportImageList = reportImageList;
	}
	public String getShow_edit() {
		return show_edit;
	}
	public void setShow_edit(String show_edit) {
		this.show_edit = show_edit;
	}


	

    
}

