package com.goaudits.business.service;

import java.util.List;

import com.goaudits.business.entity.ActionPlanAssignee;
import com.goaudits.business.entity.AuditName;
import com.goaudits.business.entity.Company;
import com.goaudits.business.entity.EmailTemplate;
import com.goaudits.business.entity.Location;
import com.goaudits.business.entity.LocationTags;
import com.goaudits.business.entity.ScoreRange;

public interface SetupService {

	public List<Company> getCompanyList(Company company);

	public List<Location> getLocationsBasedonCompany(Location location);

	public List<AuditName> getAuditNamesByCompany(AuditName auditname);

	public boolean isCompanyExists(Company company);

	public String addCompany(Company company);

	public boolean isCompanyExistInDB(Company company);

	public String updateCompany(Company company);

	public void companyReOrder(Company company);

	public boolean isLocationExistInDB(Location location);

	public String updateLocation(Location location);

	public boolean isLocationExist(Location location);

	public String addLocation(Location location);

	public List<LocationTags> getTagsBasedonLocation(String guid, String uid, String clientid, String store_id);

	public boolean isAuditNameExist(AuditName auditname);

	public String addAuditName(AuditName auditname);

	public void addAuditNameOrder(AuditName auditname);

	public boolean isAuditNameExistInDB(AuditName auditname);

	public String updateAuditName(AuditName auditname);

	public List<ActionPlanAssignee> getCompanyAssigneeList(Company company);

	public List<ActionPlanAssignee> getLocationAssigneeList(Location location);

	public List<ActionPlanAssignee> getAuditNameAssigneeList(AuditName auditname);

//	public int updateCompanyAssignee(List<ActionPlanAssignee> assignees);
//
//	public int updateLocationAssignee(List<ActionPlanAssignee> assignees);
//
//	public int updateAuditNameUpdateAssignee(List<ActionPlanAssignee> assignees);

	public List<LocationTags> getTagsList(String guid, int type);

	public List<ScoreRange> getScoreRange(String guid, String client_id, String audit_type_id);

	public int addScoreRange(List<ScoreRange> scorerange);

	public List<EmailTemplate> getEmailTemplates(EmailTemplate emailtemplate);

	public void updateEmailTemplate(EmailTemplate emailTemplate);
	
}
