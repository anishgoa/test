package com.goaudits.business.serviceimpl;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.goaudits.business.entity.ActionPlanAssignee;
import com.goaudits.business.entity.AuditName;
import com.goaudits.business.entity.Company;
import com.goaudits.business.entity.EmailTemplate;
import com.goaudits.business.entity.Location;
import com.goaudits.business.entity.LocationTags;
import com.goaudits.business.entity.PreTemplates;
import com.goaudits.business.entity.ScoreRange;
import com.goaudits.business.mapper.SetupMapper;
import com.goaudits.business.service.SetupService;
import com.goaudits.business.util.Utils;

@Service
public class SetupServiceImpl implements SetupService {

	@Autowired
	SetupMapper setupmapper;

	@Override
	public List<Company> getCompanyList(Company company) {

		List<Company> CompanyList = setupmapper.getCompanyList(company);

		return CompanyList;

	}

	@Override
	public List<Location> getLocationsBasedonCompany(Location location) {

		return setupmapper.getLocationsBasedonCompany(location);
	}

	@Override
	public List<AuditName> getAuditNamesByCompany(AuditName auditname) {

		return setupmapper.getAuditNamesByCompany(auditname);
	}

	@Override
	public boolean isCompanyExists(Company company) {

		return (setupmapper.getAddCompanyCount(company)) == 0 ? false : true;
	}

	@Override
	public String addCompany(Company company) {

		byte[] clientimage = null;
		String[] imagesrc = null;

		if (company.getLogo() != null && !(company.getLogo().trim().isEmpty())) {
			imagesrc = company.getLogo().split(",");
			try {
				clientimage = Utils.Base64ToBytes(imagesrc[1]);
				company.setLogo_binary(clientimage);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		String client_id = setupmapper.addOrUpdateCompany(company);
		List<ActionPlanAssignee> actionlist = company.getActionlist();

		for (ActionPlanAssignee act : actionlist) {

			act.setGuid(company.getGuid());
			act.setClient_id(client_id);
			setupmapper.insertActionemailforCompany(act);
		}
		return client_id;
	}

	@Override
	public boolean isCompanyExistInDB(Company company) {

		return (setupmapper.getEditCompanyCount(company)) == 0 ? false : true;

	}

	@Override
	public String updateCompany(Company company) {

		byte[] clientimage = null;
		String[] imagesrc = null;

		if (company.getLogo() != null && !(company.getLogo().trim().isEmpty())) {
			imagesrc = company.getLogo().split(",");
			try {
				clientimage = Utils.Base64ToBytes(imagesrc[1]);
				company.setLogo_binary(clientimage);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		String client_id = setupmapper.addOrUpdateCompany(company);

		List<ActionPlanAssignee> actionlist = company.getActionlist();
		setupmapper.deleteActionemailCompany(company.getGuid(), company.getClient_id());
		for (ActionPlanAssignee act : actionlist) {

			act.setGuid(company.getGuid());
			act.setClient_id(client_id);
			setupmapper.insertActionemailforCompany(act);
		}
		return client_id;
	}

	@Override
	public void companyReOrder(Company company) {
		setupmapper.companyReorder(company);
	}

	@Override
	public boolean isLocationExist(Location location) {

		return (setupmapper.getLocationExits(location) > 0) ? true : false;
	}

	@Override
	public String addLocation(Location location) {

		String store_id = setupmapper.addOrUpdateLocation(location);

		List<ActionPlanAssignee> actionlist = location.getActionlist();
		for (ActionPlanAssignee act : actionlist) {

			act.setGuid(location.getGuid());
			act.setClient_id(location.getClient_id());
			act.setStore_id(store_id);
			setupmapper.insertLoationActionemail(act);
		}

		List<LocationTags> tagslist = location.getTagslist();

		for (LocationTags t : tagslist) {

			t.setGuid(location.getGuid());
			t.setClient_id(location.getClient_id());
			t.setStore_id(store_id);
			setupmapper.addLocationTags(t);

		}
		return store_id;
	}

	@Override
	public boolean isLocationExistInDB(Location location) {

		return (setupmapper.getLocationExitsInDb(location) > 0) ? true : false;
	}

	@Override
	public String updateLocation(Location location) {
		String store_id = setupmapper.addOrUpdateLocation(location);

		List<LocationTags> tagslist = location.getTagslist();
		setupmapper.deleteLocationTags(location.getGuid(), location.getClient_id(), store_id);
		for (LocationTags t : tagslist) {
			t.setGuid(location.getGuid());
			t.setClient_id(location.getClient_id());
			t.setStore_id(store_id);
			setupmapper.addLocationTags(t);

		}
		List<ActionPlanAssignee> actionlist = location.getActionlist();
		setupmapper.deleteLocationActionemail(location.getGuid(), location.getClient_id(), store_id);
		for (ActionPlanAssignee act : actionlist) {

			act.setGuid(location.getGuid());
			act.setClient_id(location.getClient_id());
			act.setStore_id(store_id);
			setupmapper.insertLoationActionemail(act);
		}
		return store_id;
	}

	@Override
	public List<LocationTags> getTagsBasedonLocation(String guid, String uid, String clientid, String store_id) {
		return setupmapper.getTagsBasedonLocation(guid, uid, store_id);
	}

	@Override
	public boolean isAuditNameExist(AuditName auditname) {
		return (setupmapper.getAuditnameDetailsByCompany(auditname.getGuid(), auditname.getClient_id(),
				auditname.getAudit_type_name())) != null ? true : false;
	}

	@Override
	public String addAuditName(AuditName auditname) {
		String audit_type_id = setupmapper.insertUpdateAuditName(auditname);

		List<ActionPlanAssignee> actionlist = auditname.getActionlist();
		for (ActionPlanAssignee act : actionlist) {

			act.setGuid(auditname.getGuid());
			act.setClient_id(auditname.getClient_id());
			act.setAudit_type_id(audit_type_id);
			setupmapper.insertActionemailAuditName(act);
		}
		return audit_type_id;
	}

	@Override
	public void addAuditNameOrder(AuditName auditname) {
		setupmapper.AuditNameReorder(auditname);
	}

	@Override
	public boolean isAuditNameExistInDB(AuditName auditname) {
		return (setupmapper.getAuditnameDetailsByCompanyEdit(auditname)) != null ? true : false;
	}

	@Override
	public String updateAuditName(AuditName auditname) {

		String audit_type_id = setupmapper.insertUpdateAuditName(auditname);
		List<ActionPlanAssignee> actionlist = auditname.getActionlist();
		setupmapper.deleteActionemailAuditName(auditname);
		for (ActionPlanAssignee act : actionlist) {
			act.setGuid(auditname.getGuid());
			act.setClient_id(auditname.getClient_id());
			act.setAudit_type_id(audit_type_id);
			setupmapper.insertActionemailAuditName(act);
		}
		return audit_type_id;
	}

	@Override
	public List<ActionPlanAssignee> getCompanyAssigneeList(Company company) {
		return setupmapper.getActionPlanAssigneeByCompany(company);

	}

	@Override
	public List<ActionPlanAssignee> getLocationAssigneeList(Location location) {
		return setupmapper.getActionemailByLocation(location);
	}

	@Override
	public List<ActionPlanAssignee> getAuditNameAssigneeList(AuditName auditname) {
		return setupmapper.getActionemailByAuditname(auditname);
	}

//	@Override
//	public int updateCompanyAssignee(List<ActionPlanAssignee> assignees) {
//		setupmapper.deleteActionemailCompany(assignees.get(0).getGuid(), assignees.get(0).getClient_id());
//
//		for (ActionPlanAssignee act : assignees) {
//
//			setupmapper.insertActionemailforCompany(act);
//		}
//		return 1;
//	}

//	@Override
//	public int updateLocationAssignee(List<ActionPlanAssignee> assignees) {
//		setupmapper.deleteLocationActionemail(assignees.get(0).getGuid(), assignees.get(0).getClient_id(),
//				assignees.get(0).getStore_id());
//
//		for (ActionPlanAssignee act : assignees) {
//			setupmapper.insertLoationActionemail(act);
//		}
//		return 1;
//	}

//	@Override
//	public int updateAuditNameUpdateAssignee(List<ActionPlanAssignee> assignees) {
//
//		AuditName adn = new AuditName();
//		adn.setGuid(assignees.get(0).getGuid());
//		adn.setClient_id(assignees.get(0).getClient_id());
//		adn.setAudit_type_id(assignees.get(0).getAudit_type_id());
//		setupmapper.deleteActionemailAuditName(adn);
//		for (ActionPlanAssignee act : assignees) {
//			setupmapper.insertActionemailAuditName(act);
//		}
//
//		return 1;
//	}

	@Override
	public List<LocationTags> getTagsList(String guid, int type) {
		List<LocationTags> tgsCaList = setupmapper.getTagCategories(guid, type);

		for (LocationTags lt : tgsCaList) {

			List<LocationTags> tgsList = setupmapper.getTagList(guid, lt.getCategory_id());

			lt.getTagsList().addAll(tgsList);

		}

		return tgsCaList;
	}

	@Override
	public List<ScoreRange> getScoreRange(String guid, String client_id, String audit_type_id) {
		return setupmapper.getScoreRange(guid, client_id, audit_type_id);
	}

	@Override
	public int addScoreRange(List<ScoreRange> scorerange) {
		setupmapper.deleteScoreRange(scorerange.get(0));

		for (ScoreRange sc : scorerange) {
			if (sc.getMax_value() != 0) {
				setupmapper.addScoreRange(sc);
			}

		}

		return 1;
	}

	@Override
	public List<EmailTemplate> getEmailTemplates(EmailTemplate emailtemplate) {

		return setupmapper.getEmailTemplates(emailtemplate);
	}

	@Override
	public void updateEmailTemplate(EmailTemplate emailTemplate) {

		setupmapper.updateEmailTemplate(emailTemplate);

	}

	@Override
	public String getCompanyCloneFlag(String guid) {
		
		return setupmapper.getCompanyCloneFlag(guid);
	}

	@Override
	public String checkCompanydata(PreTemplates preTemplates) {
		String validate = null;

		validate = setupmapper.ispreTemplateExistCompany(preTemplates) + "";
		String clients = setupmapper.getClientnamesExistingCompany(preTemplates);
		validate = validate + "---@%" + clients;
		return validate;
	}

	@Override
	public int cloneCompanies(PreTemplates preTemplates) {
		String clientIds[] = preTemplates.getClient_id().split(",");

		for (int i = 0; i < clientIds.length; i++) {

			preTemplates.setClient_id(clientIds[i]);
			setupmapper.cloneCompanies(preTemplates);

		}

		return 1;
	}

	@Override
	public boolean getGpsFlag(String guid) {
		return setupmapper.getGpsFlag(guid);
	}

}
