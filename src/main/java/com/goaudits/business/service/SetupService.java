package com.goaudits.business.service;

import java.util.List;

import com.goaudits.business.entity.ActionPlanAssignee;
import com.goaudits.business.entity.AuditName;
import com.goaudits.business.entity.Company;
import com.goaudits.business.entity.EmailTemplate;
import com.goaudits.business.entity.GuidedSetup;
import com.goaudits.business.entity.Location;
import com.goaudits.business.entity.LocationTags;
import com.goaudits.business.entity.PreTemplates;
import com.goaudits.business.entity.Report;
import com.goaudits.business.entity.ScoreRange;
import com.goaudits.business.entity.Section;

public interface SetupService {

	public List<Company> getCompanyList(Company company);

	public List<Location> getLocationsBasedonCompany(Location location);

	public List<AuditName> getAuditNamesByCompany(AuditName auditname);

	public boolean isCompanyExists(Company company);

	public Company addCompany(Company company);

	public boolean isCompanyExistInDB(Company company);

	public Company updateCompany(Company company);

	public void companyReOrder(Company company);

	public boolean isLocationExistInDB(Location location);

	public String updateLocation(Location location);

	public boolean isLocationExist(Location location);

	public String addLocation(Location location);

	public List<LocationTags> getTagsBasedonLocation(String guid, String uid, String clientid, String store_id);

	public boolean isAuditNameExist(AuditName auditname);

	public AuditName addAuditName(AuditName auditname);

	public void addAuditNameOrder(AuditName auditname);

	public boolean isAuditNameExistInDB(AuditName auditname);

	public AuditName updateAuditName(AuditName auditname);

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

	public String getCompanyCloneFlag(String guid);

	public String checkCompanydata(PreTemplates preTemplates);

	public int cloneCompanies(PreTemplates preTemplates);

	public boolean getGpsFlag(String guid);

	public List<Report> getReports(Report report);

	public int updateReport(Report report);

	public List<Report> getReporttemplates(String guid);

	public List<Company> getPreexistingTemplates();

	public List<AuditName> getPreAuditnames(int client_id);

	public List<AuditName> getAllPreAuditnames();

	public int getPretempletQuestionscount(int client_id, int audit_type_id);

	public List<Section> getPretempletQuestions(Section section);

	public String PreTemplates(PreTemplates preTemplates);

	public int createPreTemplate(PreTemplates preTemplates);

	public int cloneAuditName(PreTemplates preTemplates);

	public List<GuidedSetup> getGuidedSetupdata();

	public int getCompanyCount(String guid);

	public int createGuided(GuidedSetup gudsetp);

	public List<Location> getLocationsBasedonCompanyv2(Location location);

	public boolean getEditFlag(String guid);

	public boolean getCompletedFlag(String guid);

	
}
