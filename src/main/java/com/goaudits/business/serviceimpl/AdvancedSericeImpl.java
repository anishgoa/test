package com.goaudits.business.serviceimpl;

import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.goaudits.business.entity.ActionPlanSettings;
import com.goaudits.business.entity.AuditName;
import com.goaudits.business.entity.AuditWorkFlow;
import com.goaudits.business.entity.Company;
import com.goaudits.business.entity.CustomFieldList;
import com.goaudits.business.entity.Customfields;
import com.goaudits.business.entity.Location;
import com.goaudits.business.entity.LocationTags;
import com.goaudits.business.entity.Personseen;
import com.goaudits.business.entity.Tag;
import com.goaudits.business.entity.User;
import com.goaudits.business.mapper.AdvancedMapper;
import com.goaudits.business.service.AdvancedService;
import com.goaudits.business.util.Utils;

@Service
public class AdvancedSericeImpl implements AdvancedService {

	@Autowired
	AdvancedMapper advancedmapper;

	public List<Personseen> getPersonseen(String guid) {

		List<Personseen> pseenlist = advancedmapper.getPersonseenList(guid);

		for (Personseen psen : pseenlist) {

			List<Company> cllist = advancedmapper.getpersonseenClients(guid, psen.getPerson_email());
			psen.getClientarr().addAll(cllist);

			for (Company c : cllist) {

				List<AuditName> atlist = advancedmapper.getPersonseenAudits(guid, psen.getPerson_email(),
						c.getClient_id());
				c.getAudarry().addAll(atlist);

				for (AuditName a : atlist) {
					List<Location> starr = advancedmapper.getPersonseenLocations(guid, psen.getPerson_email(),
							c.getClient_id(), a.getAudit_type_id());
					a.getStrarry().addAll(starr);

				}

			}

		}

		return pseenlist;
	}

	@Override
	public List<LinkedHashMap> validateaddPersonSeen(Personseen pseen) {
		return advancedmapper.validateaddPersonSeen(pseen);
	}

	@Override
	public int addoredPersonseen(Personseen pseen) {
		int storelength = 0;
		int auditlength = 0;
		String storeids[] = pseen.getStore_ids().split(",");
		String auditids[] = pseen.getAudit_type_ids().split(",");
		if (pseen.getStore_ids().equals("")) {
			storelength = 0;
		} else {
			storelength = storeids.length;
		}

		if (pseen.getAudit_type_ids().equals("")) {
			auditlength = 0;

		} else {
			auditlength = auditids.length;

		}

		if (storelength > 0) {

			for (int i = 0; i < storelength; i++) {

				if (auditlength > 0) {

					for (int j = 0; j < auditlength; j++) {

						pseen.setAudit_type_id(Integer.parseInt(auditids[j]));
						pseen.setStore_id(Integer.parseInt(storeids[i]));
						advancedmapper.addorEditPersonSeen(pseen);

					}

				} else {
					pseen.setAudit_type_id(0);
					pseen.setStore_id(Integer.parseInt(storeids[i]));
					advancedmapper.addorEditPersonSeen(pseen);

				}

			}

		} else if (auditlength > 0) {

			for (int i = 0; i < auditlength; i++) {

				if (storelength > 0) {

					for (int j = 0; j < storelength; j++) {

						pseen.setAudit_type_id(Integer.parseInt(auditids[i]));
						pseen.setStore_id(Integer.parseInt(storeids[j]));
						advancedmapper.addorEditPersonSeen(pseen);

					}

				} else {
					pseen.setAudit_type_id(Integer.parseInt(auditids[i]));
					pseen.setStore_id(0);
					advancedmapper.addorEditPersonSeen(pseen);

				}

			}

		} else {
			pseen.setAudit_type_id(0);
			pseen.setStore_id(0);
			advancedmapper.addorEditPersonSeen(pseen);
		}

		return 0;
	}

	@Override
	public List<LinkedHashMap> validateeditPersonSeen(Personseen pseen) {
		return advancedmapper.validateeditPersonSeen(pseen);
	}

	@Override
	public int editPersonseen(Personseen pseen) {
		advancedmapper.deletePersonSeen(pseen);

		int storelength = 0;
		int auditlength = 0;
		String storeids[] = pseen.getStore_ids().split(",");
		String auditids[] = pseen.getAudit_type_ids().split(",");
		if (pseen.getStore_ids().equals("")) {
			storelength = 0;
		} else {
			storelength = storeids.length;
		}

		if (pseen.getAudit_type_ids().equals("")) {
			auditlength = 0;

		} else {
			auditlength = auditids.length;

		}

		if (storelength > 0) {

			for (int i = 0; i < storelength; i++) {

				if (auditlength > 0) {

					for (int j = 0; j < auditlength; j++) {

						pseen.setAudit_type_id(Integer.parseInt(auditids[j]));
						pseen.setStore_id(Integer.parseInt(storeids[i]));
						advancedmapper.addorEditPersonSeen(pseen);

					}

				} else {
					pseen.setAudit_type_id(0);
					pseen.setStore_id(Integer.parseInt(storeids[i]));
					advancedmapper.addorEditPersonSeen(pseen);

				}

			}

		} else if (auditlength > 0) {

			for (int i = 0; i < auditlength; i++) {

				if (storelength > 0) {

					for (int j = 0; j < storelength; j++) {

						pseen.setAudit_type_id(Integer.parseInt(auditids[i]));
						pseen.setStore_id(Integer.parseInt(storeids[j]));
						advancedmapper.addorEditPersonSeen(pseen);

					}

				} else {
					pseen.setAudit_type_id(Integer.parseInt(auditids[i]));
					pseen.setStore_id(0);
					advancedmapper.addorEditPersonSeen(pseen);

				}

			}

		} else {
			pseen.setAudit_type_id(0);
			pseen.setStore_id(0);
			advancedmapper.addorEditPersonSeen(pseen);

		}
		return 0;
	}

	@Override
	public boolean validateTag(LocationTags loct) {
		return advancedmapper.validateTags(loct) > 0 ? true : false;
	}

	@Override
	public boolean validateEdTag(LocationTags loct) {
		return advancedmapper.validateedTags(loct) > 0 ? true : false;
	}

	@Override
	public int addTag(LocationTags loct) {
		return advancedmapper.addTags(loct);
	}

	@Override
	public boolean validateCategory(LocationTags tgs) {

		return advancedmapper.validateCategory(tgs) > 0 ? true : false;
	}

	@Override
	public boolean validateEdCategory(LocationTags tgs) {
		return advancedmapper.validateEdCategory(tgs) > 0 ? true : false;
	}

	@Override
	public int addTagCategory(LocationTags tgs) {
		return advancedmapper.addTagCategory(tgs);
	}

	@Override
	public List<LocationTags> getReportTag(LocationTags tag) {
		List<LocationTags> tgsCaList = advancedmapper.getTagCategories(tag.getGuid());

		for (LocationTags lt : tgsCaList) {

			List<LocationTags> tgsList = advancedmapper.getTagList(tag.getGuid(), lt.getCategory_id());

			lt.getTagsList().addAll(tgsList);

		}

		return tgsCaList;

	}

	@Override
	public List<AuditWorkFlow> getAuditWorkflowList(AuditWorkFlow workflow) {
		return advancedmapper.getAuditWorkflowList(workflow);
	}

	@Override
	public List<User> getAdminlist(AuditWorkFlow auditWorkFlow) {
		return advancedmapper.getAdminlist(auditWorkFlow);
	}

	@Override
	public int addWorkFlow(AuditWorkFlow auditWorkFlow) {

		String locations[]=auditWorkFlow.getStore_id().split(",");
		String auditnames[]=auditWorkFlow.getAudit_type_id().split(",");
		String assignees[]=auditWorkFlow.getAssignee().split(",");
		for(int i=0;i<locations.length;i++){
			for(int j=0;j<auditnames.length;j++){
				for(int k=0;k<assignees.length;k++){
					String uuid = String.valueOf(Utils.generateUID());
					auditWorkFlow.setUuid(uuid);	
					advancedmapper.addAuditWorkFlow(auditWorkFlow.getGuid(),auditWorkFlow.getClient_id(),locations[i],auditnames[j],assignees[k],auditWorkFlow.isWorkflow_type(),auditWorkFlow.getUuid(),auditWorkFlow.isSignature_required());
				}	
			}	
		}
		return 1;

	}

	@Override
	public int editWorkFlow(AuditWorkFlow auditWorkFlow) {
		advancedmapper.deleteAuditWorkFlow(auditWorkFlow.getEdituuid());
		String locations[]=auditWorkFlow.getStore_id().split(",");
		String auditnames[]=auditWorkFlow.getAudit_type_id().split(",");
		String assignees[]=auditWorkFlow.getAssignee().split(",");
		
		for(int i=0;i<locations.length;i++){
			
			for(int j=0;j<auditnames.length;j++){
				
				for(int k=0;k<assignees.length;k++){
					String uuid = String.valueOf(Utils.generateUID());
					auditWorkFlow.setUuid(uuid);
					advancedmapper.addAuditWorkFlow(auditWorkFlow.getGuid(),auditWorkFlow.getClient_id(),locations[i],auditnames[j],assignees[k],auditWorkFlow.isWorkflow_type(),auditWorkFlow.getUuid(),auditWorkFlow.isSignature_required());
				}
								
			}
			
		}
		
		return 1;
	}

	@Override
	public int deleteWorkFlow(AuditWorkFlow auditWorkFlow) {
		return advancedmapper.deleteAuditWorkFlow(auditWorkFlow.getUuid());
	}

	@Override
	public boolean CheckTagsAssigned(LocationTags loct) {
		// TODO Auto-generated method stub
		return advancedmapper.checkTagsAssigned(loct) > 0 ? true : false;
	}

	@Override
	public int deleteTag(LocationTags tgs) {
		// TODO Auto-generated method stub
		return advancedmapper.deleteTag(tgs);
	}

	@Override
	public boolean CheckTagsCategoryAssigned(LocationTags loct) {
		// TODO Auto-generated method stub
		return advancedmapper.checkTagsCategoryAssigned(loct) > 0 ? true : false;
	}

	@Override
	public int deleteTagCategory(LocationTags tgs) {
		// TODO Auto-generated method stub
		return advancedmapper.deleteTagCategory(tgs);
	}

	@Override
	public List<Customfields> getAllCustomfields(String guid, String uid, String client_id) {
		List<Customfields> Customfieldslist = advancedmapper.getAllCustomfields(guid, uid, client_id);
		for (Customfields cf : Customfieldslist) {
			String label[] = cf.getField_label().split("---");
			String name[] = cf.getField_name().split("---");
			String act[] = cf.getActive().split("---");
			String typ[] = cf.getField_type().split("---");
			for (int i = 0; i < label.length; i++) {
				Customfields cfs = new Customfields();
				cfs.setField_label(label[i]);
				cfs.setField_name(name[i]);
				cfs.setField_type(typ[i]);
				cfs.setActive(act[i]);
				cf.getCustomfieldslist().add(cfs);

			}

		}

		return Customfieldslist;
	}

	@Override
	public List<CustomFieldList> getCustomFieldsList(AuditName audit) {
		
		return advancedmapper.getCustomFieldsList(audit);
	}

	@Override
	public List<ActionPlanSettings> getActionPlanSettngs(ActionPlanSettings actionPlanSettings) {
	
		return advancedmapper.getActionPlanSettngs(actionPlanSettings);
	}

	@Override
	public boolean validateaddActionSts(ActionPlanSettings actionPlanSettings) {
		return (advancedmapper.validateaddActionSts(actionPlanSettings)) > 0 ? true : false;
	}

	@Override
	public int addOrEditActionPlanSettngs(ActionPlanSettings actionPlanSettings) {
		return advancedmapper.addOrEditActionPlanSettngs(actionPlanSettings);
	}

	@Override
	public boolean validateeditActionSts(ActionPlanSettings actionPlanSettings) {
		
		return (advancedmapper.validateeditActionSts(actionPlanSettings)) > 0 ? true : false;
	}

	@Override
	public boolean isCustomfieldsExist(Customfields customfields) {
		return advancedmapper.isCustomfieldsExist(customfields) == 1 ? true : false;
	}

	@Override
	public int addCustomfields(Customfields customfields) {
		
		return advancedmapper.addCustomfields(customfields);
	}

	@Override
	public int updateCustomfields(Customfields customfields) {
	
		return advancedmapper.addCustomfields(customfields);
	}





}
