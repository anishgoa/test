package com.goaudits.business.service;

import java.util.LinkedHashMap;
import java.util.List;

import com.goaudits.business.entity.ActionPlanSettings;
import com.goaudits.business.entity.AuditName;
import com.goaudits.business.entity.AuditWorkFlow;
import com.goaudits.business.entity.CustomFieldList;
import com.goaudits.business.entity.Customfields;
import com.goaudits.business.entity.GroupAudit;
import com.goaudits.business.entity.LocationTags;
import com.goaudits.business.entity.Personseen;
import com.goaudits.business.entity.Tag;
import com.goaudits.business.entity.User;

public interface AdvancedService {

	List<Personseen> getPersonseen(String guid);

	List<LinkedHashMap> validateaddPersonSeen(Personseen pseen);

	int addoredPersonseen(Personseen pseen);

	List<LinkedHashMap> validateeditPersonSeen(Personseen pseen);

	int editPersonseen(Personseen pseen);

	boolean validateTag(LocationTags loct);

	boolean validateEdTag(LocationTags loct);

	int addTag(LocationTags loct);

	boolean validateCategory(LocationTags loct);

	boolean validateEdCategory(LocationTags loct);

	int addTagCategory(LocationTags loct);

	List<LocationTags> getReportTag(LocationTags tag);


	List<AuditWorkFlow> getAuditWorkflowList(AuditWorkFlow workflow);

	List<User> getAdminlist(AuditWorkFlow auditWorkFlow);

	int addWorkFlow(AuditWorkFlow auditWorkFlow);

	int editWorkFlow(AuditWorkFlow auditWorkFlow);

	int deleteWorkFlow(AuditWorkFlow auditWorkFlow);

	boolean CheckTagsAssigned(LocationTags loct);

	int deleteTag(LocationTags loct);

	boolean CheckTagsCategoryAssigned(LocationTags loct);

	int deleteTagCategory(LocationTags loct);

	List<Customfields> getAllCustomfields(String guid, String uid, String client_id);

	List<CustomFieldList> getCustomFieldsList(AuditName audit);

	List<ActionPlanSettings> getActionPlanSettngs(ActionPlanSettings actionPlanSettings);

	boolean validateaddActionSts(ActionPlanSettings actionPlanSettings);

	int addOrEditActionPlanSettngs(ActionPlanSettings actionPlanSettings);

	boolean validateeditActionSts(ActionPlanSettings actionPlanSettings);

	boolean isCustomfieldsExist(Customfields customfields);

	int addCustomfields(Customfields customfields);

	int updateCustomfields(Customfields customfields);

	List<Tag> getReportTag(String guid, String uid, int client_id);

	boolean validateaddTag(Tag tag);

	int addReportTag(Tag tag);

	boolean validateeditTag(Tag tag);

	List<GroupAudit> getGroupAudit(String guid, boolean active);

	List<AuditName> getAuditTypeList(String guid, String uid, int client_id, int parent_audit_id);

	boolean validateGroupAudit(GroupAudit groupAudit);

	boolean validateGroupAudit1(GroupAudit groupAudit);

	int addAuditGroup(GroupAudit groupAudit);

}
