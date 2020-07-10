package com.goaudits.business.controller;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
import com.goaudits.business.service.AdvancedService;
import com.goaudits.business.util.GoAuditsException;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/setup")
public class AdvancedController {

	@Autowired
	AdvancedService advancedservice;

	@RequestMapping(value = "/getpersonseen/{guid}", method = RequestMethod.GET)
	public ResponseEntity<?> getPersonSenn(@PathVariable("guid") String guid) {
		List<Personseen> psenlist = advancedservice.getPersonseen(guid);
		return new ResponseEntity<List<Personseen>>(psenlist, HttpStatus.OK);
	}

	@RequestMapping(value = "/addpersonseen", method = RequestMethod.POST)
	public ResponseEntity<?> addoreditPersonSenn(@RequestBody Personseen pseen) {

		List<LinkedHashMap> validatelist = advancedservice.validateaddPersonSeen(pseen);

		if ((boolean) validatelist.get(0).get("company")) {
			return new ResponseEntity<>(new GoAuditsException("Person already exists"), HttpStatus.CONFLICT);
		} else if ((boolean) validatelist.get(0).get("auditname")) {
			return new ResponseEntity<>(new GoAuditsException("Audit already exists"), HttpStatus.CONFLICT);
		} else if ((boolean) validatelist.get(0).get("location")) {
			return new ResponseEntity<>(new GoAuditsException("Location already exists"), HttpStatus.CONFLICT);
		}

		advancedservice.addoredPersonseen(pseen);
		List<Personseen> pseenList = new ArrayList<Personseen>();
		pseenList.add(pseen);
		return new ResponseEntity<List<Personseen>>(pseenList, HttpStatus.OK);
	}

	@RequestMapping(value = "/editpersonseen", method = RequestMethod.POST)
	public ResponseEntity<?> editPersonSenn(@RequestBody Personseen pseen) {

		List<LinkedHashMap> validatelist = advancedservice.validateeditPersonSeen(pseen);

		if ((boolean) validatelist.get(0).get("company")) {
			return new ResponseEntity<>(new GoAuditsException("Person already exists"), HttpStatus.CONFLICT);
		} else if ((boolean) validatelist.get(0).get("auditname")) {
			return new ResponseEntity<>(new GoAuditsException("Audit already exists"), HttpStatus.CONFLICT);
		} else if ((boolean) validatelist.get(0).get("location")) {
			return new ResponseEntity<>(new GoAuditsException("Location already exists"), HttpStatus.CONFLICT);
		}
		advancedservice.editPersonseen(pseen);
		List<Personseen> pseenList = new ArrayList<Personseen>();
		pseenList.add(pseen);
		return new ResponseEntity<List<Personseen>>(pseenList, HttpStatus.OK);
	}

	@RequestMapping(value = "/reporttag/list", method = RequestMethod.POST)
	public ResponseEntity<?> getReportTag(@RequestBody LocationTags tag) {

		List<LocationTags> tagsList = advancedservice.getReportTag(tag);

		return new ResponseEntity<List<LocationTags>>(tagsList, HttpStatus.OK);
	}

	@RequestMapping(value = "addtagcategory", method = RequestMethod.POST)
	public ResponseEntity<?> addCategory(@RequestBody LocationTags loct) {

		if (loct.getCategory_id() == 0) {

			if (advancedservice.validateCategory(loct)) {
				return new ResponseEntity(new GoAuditsException("Category name already exists"), HttpStatus.CONFLICT);
			}

		} else {

			if (advancedservice.validateEdCategory(loct)) {
				return new ResponseEntity(new GoAuditsException("Category name already exists"), HttpStatus.CONFLICT);
			}
		}

		int category_id = advancedservice.addTagCategory(loct);
		List<LocationTags> TagsList = new ArrayList<LocationTags>();
		loct.setCategory_id(category_id);
		TagsList.add(loct);
		return new ResponseEntity<List<LocationTags>>(TagsList, HttpStatus.OK);
	}

	@RequestMapping(value = "addtag", method = RequestMethod.POST)
	public ResponseEntity<?> addTag(@RequestBody LocationTags loct) {
		if (loct.getTag_id() == 0) {

			if (advancedservice.validateTag(loct)) {
				return new ResponseEntity(new GoAuditsException("Tag name already exists"), HttpStatus.CONFLICT);
			}

		} else {

			if (advancedservice.validateEdTag(loct)) {
				return new ResponseEntity(new GoAuditsException("Tag name already exists"), HttpStatus.CONFLICT);
			}
		}
		int addTag = advancedservice.addTag(loct);

		List<LocationTags> TagsList = new ArrayList<LocationTags>();
		loct.setTag_id(addTag);
		TagsList.add(loct);
		return new ResponseEntity<List<LocationTags>>(TagsList, HttpStatus.OK);
	}

	@RequestMapping(value = "deletetag", method = RequestMethod.POST)
	public ResponseEntity<?> deleteTag(@RequestBody LocationTags loct) {

		if (advancedservice.CheckTagsAssigned(loct)) {
			return new ResponseEntity(new GoAuditsException("Tag already configured to location"), HttpStatus.CONFLICT);
		}

		int deletetag = advancedservice.deleteTag(loct);

		return new ResponseEntity<Integer>(deletetag, HttpStatus.OK);
	}

	@RequestMapping(value = "deletetagcategory", method = RequestMethod.POST)
	public ResponseEntity<?> deleteTagCategory(@RequestBody LocationTags loct) {

		if (advancedservice.CheckTagsCategoryAssigned(loct)) {
			return new ResponseEntity(new GoAuditsException("Tag already configured to location"), HttpStatus.CONFLICT);
		}
		int deletetag = advancedservice.deleteTagCategory(loct);

		return new ResponseEntity<Integer>(deletetag, HttpStatus.OK);
	}

	@RequestMapping(value = "/workflow", method = RequestMethod.POST)
	public ResponseEntity<List<AuditWorkFlow>> getWorkFlowList(@RequestBody AuditWorkFlow workflow) {
		List<AuditWorkFlow> workflowlist = advancedservice.getAuditWorkflowList(workflow);
		return new ResponseEntity<List<AuditWorkFlow>>(workflowlist, HttpStatus.OK);
	}

	@RequestMapping(value = "/getadmins", method = RequestMethod.POST)
	public ResponseEntity<List<User>> geAdminList(@RequestBody AuditWorkFlow AuditWorkFlow) {
		if (AuditWorkFlow.getStore_id() == "" || AuditWorkFlow.getStore_id().isEmpty()) {
			AuditWorkFlow.setStore_id("0");
		}

		if (AuditWorkFlow.getAudit_type_id() == "" || AuditWorkFlow.getAudit_type_id().isEmpty()) {
			AuditWorkFlow.setAudit_type_id("0");
		}
		List<User> adminlist = advancedservice.getAdminlist(AuditWorkFlow);
		return new ResponseEntity<List<User>>(adminlist, HttpStatus.OK);
	}

	@RequestMapping(value = "/workflow/add", method = RequestMethod.POST)
	public ResponseEntity<?> addScheduleaudit(@RequestBody AuditWorkFlow AuditWorkFlow) {

		 advancedservice.addWorkFlow(AuditWorkFlow);

		List<AuditWorkFlow> wrkFlwList = new ArrayList<AuditWorkFlow>();
		wrkFlwList.add(AuditWorkFlow);
		return new ResponseEntity<List<AuditWorkFlow>>(wrkFlwList, HttpStatus.OK);
	}

	@RequestMapping(value = "/workflow/edit", method = RequestMethod.POST)
	public ResponseEntity<?> editScheduleaudit(@RequestBody AuditWorkFlow AuditWorkFlow) {

		 advancedservice.editWorkFlow(AuditWorkFlow);

		List<AuditWorkFlow> wrkFlwList = new ArrayList<AuditWorkFlow>();
		wrkFlwList.add(AuditWorkFlow);
		return new ResponseEntity<List<AuditWorkFlow>>(wrkFlwList, HttpStatus.OK);
	}

	@RequestMapping(value = "/workflow/delete", method = RequestMethod.POST)
	public ResponseEntity<?> deleteScheduleaudit(@RequestBody AuditWorkFlow AuditWorkFlow) {

		 advancedservice.deleteWorkFlow(AuditWorkFlow);

		List<AuditWorkFlow> wrkFlwList = new ArrayList<AuditWorkFlow>();
		wrkFlwList.add(AuditWorkFlow);
		return new ResponseEntity<List<AuditWorkFlow>>(wrkFlwList, HttpStatus.OK);
	}

	@RequestMapping(value = "/customfields/list2", method = RequestMethod.POST)
	public ResponseEntity<List<Customfields>> getAllCustomFields2(@RequestBody AuditName audit) {
		
		List<Customfields> customFieldsList = advancedservice.getAllCustomfields(audit.getGuid(), audit.getUid(),audit.getClient_id());
		if (customFieldsList.isEmpty()) {
			return new ResponseEntity(new GoAuditsException("No custom fields are found"), HttpStatus.NOT_FOUND);
		} 
		return new ResponseEntity<List<Customfields>>(customFieldsList, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/customfields/list", method = RequestMethod.POST)
	public ResponseEntity<List<CustomFieldList>> getAllCustomFields(@RequestBody AuditName audit) {
		
		List<CustomFieldList> customFieldsList = advancedservice.getCustomFieldsList(audit);
	
		return new ResponseEntity<List<CustomFieldList>>(customFieldsList, HttpStatus.OK);
	}
	
	

	@RequestMapping(value = "/customfields/add", method = RequestMethod.POST)
	public ResponseEntity<?> addCustomFields(@RequestBody Customfields customfields) {
		
		if (advancedservice.isCustomfieldsExist(customfields)) {
			return new ResponseEntity(new GoAuditsException("Custom fields cannot be added, already exists"),
					HttpStatus.CONFLICT);
		}
		try {
			advancedservice.addCustomfields(customfields);
			List<Customfields> customFieldsList=new ArrayList<Customfields>();
			customFieldsList.add(customfields);
			return new ResponseEntity<List<Customfields>>(customFieldsList, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity(new GoAuditsException(e.getMessage()), HttpStatus.EXPECTATION_FAILED);
		}
	}

	@RequestMapping(value = "/customfields/update", method = RequestMethod.PUT)
	public ResponseEntity<?> updateCustomfields(@RequestBody Customfields customfields) {

		if (advancedservice.isCustomfieldsExist(customfields)) {
			try {
				advancedservice.updateCustomfields(customfields);

				List<Customfields> customFieldsList=new ArrayList<Customfields>();
				customFieldsList.add(customfields);
				return new ResponseEntity<List<Customfields>>(customFieldsList, HttpStatus.CREATED);
				
			} catch (Exception e) {
				return new ResponseEntity(new GoAuditsException(e.getMessage()), HttpStatus.EXPECTATION_FAILED);
			}
		}
		return new ResponseEntity(new GoAuditsException("Custom field cannot be updated, record not found"),
				HttpStatus.CONFLICT);
	}

	

	
	@RequestMapping(value = "/actionplansettings", method = RequestMethod.POST)
	public ResponseEntity<?> getActionPlanSetngs(@RequestBody ActionPlanSettings ActionPlanSettings) {

		List<ActionPlanSettings> actlist = advancedservice.getActionPlanSettngs(ActionPlanSettings);

		return new ResponseEntity<List<ActionPlanSettings>>(actlist, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/addactionplansetngs", method = RequestMethod.POST)
	public ResponseEntity<?> addActionPlanSetngs(@RequestBody ActionPlanSettings ActionPlanSettings) {

		if (ActionPlanSettings.getPriority_id() == 0) {

			if (advancedservice.validateaddActionSts(ActionPlanSettings)) {
				return new ResponseEntity<>(new GoAuditsException("Priority already exists"), HttpStatus.CONFLICT);
			} else {
				int actplnadd = advancedservice.addOrEditActionPlanSettngs(ActionPlanSettings);

				List<ActionPlanSettings> actionPlansetngs=new ArrayList<ActionPlanSettings>();
				ActionPlanSettings.setPriority_id(actplnadd);
				actionPlansetngs.add(ActionPlanSettings);
				return new ResponseEntity<List<ActionPlanSettings>>(actionPlansetngs, HttpStatus.OK);
			}

		} else if (advancedservice.validateeditActionSts(ActionPlanSettings)) {
			return new ResponseEntity<>(new GoAuditsException("Priority already exists"), HttpStatus.CONFLICT);

		} else {
			int actplnadd = advancedservice.addOrEditActionPlanSettngs(ActionPlanSettings);
			
			
			List<ActionPlanSettings> actionPlansetngs=new ArrayList<ActionPlanSettings>();
			ActionPlanSettings.setPriority_id(actplnadd);
			actionPlansetngs.add(ActionPlanSettings);
			return new ResponseEntity<List<ActionPlanSettings>>(actionPlansetngs, HttpStatus.OK);
		}

	}
	
	/**
	 * List report tags of a company
	 * @param guid
	 * @param uid
	 * @param client_id
	 * @return
	 */
	@RequestMapping(value = "/report/tag/list", method = RequestMethod.POST)
	public ResponseEntity<?> getReportTag(@RequestBody Tag tag) {

		List<Tag> reportTagList = advancedservice.getReportTag(tag.getGuid(),tag.getUid(), tag.getClient_id());

		return new ResponseEntity<List<Tag>>(reportTagList, HttpStatus.OK);
	}
	
	/**
	 * Add / create a new report tag
	 * @param tag
	 * @return
	 */
	@RequestMapping(value = "/reporttag/add", method = RequestMethod.POST)
	public ResponseEntity<?> addReportTag(@RequestBody Tag tag) {

		
		if (advancedservice.validateaddTag(tag)) {

			return new ResponseEntity(new GoAuditsException("Tag cannot be added,tag code already exists"),
					HttpStatus.CONFLICT);

		} else {
			int reportTag = advancedservice.addReportTag(tag);
			return new ResponseEntity<Integer>(reportTag, HttpStatus.OK);

		}

	}
	
	/**
	 * Update or edit the report tag
	 * @param tag
	 * @return
	 */
	@RequestMapping(value = "/reporttag/update", method = RequestMethod.PUT)
	public ResponseEntity<?> updateReportTag(@RequestBody Tag tag) {

		
		if (advancedservice.validateeditTag(tag)) {

			return new ResponseEntity(new GoAuditsException("Tag cannot be saved,tag code already exists"),
					HttpStatus.CONFLICT);

		} else {
			int reportTag = advancedservice.addReportTag(tag);
			return new ResponseEntity<Integer>(reportTag, HttpStatus.OK);
		}
	}
	
	@RequestMapping(value = "/groupaudit/list/{guid}/{active}", method = RequestMethod.GET)
	public ResponseEntity<?> getGroupAudit(@PathVariable("guid")String guid,@PathVariable("active") boolean active) {

		List<GroupAudit> auditTypeList = advancedservice.getGroupAudit(guid, active);

		return new ResponseEntity<List<GroupAudit>>(auditTypeList, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/groupaudit/auditnames/{guid}/{uid}/{client_id}/{parent_audit_id}", method = RequestMethod.GET)
	public ResponseEntity<List<AuditName>> getAllClients(@PathVariable("guid") String guid,@PathVariable("uid") String uid,@PathVariable("client_id") int client_id,
			@PathVariable("parent_audit_id") int parent_audit_id) {

		List<AuditName> auditTypeList = advancedservice.getAuditTypeList(guid,
				uid, client_id, parent_audit_id);

		return new ResponseEntity<List<AuditName>>(auditTypeList, HttpStatus.OK);
	}

	@RequestMapping(value = "/groupaudit/add", method = RequestMethod.POST)
	public ResponseEntity<?> addGroupAudit(@RequestBody GroupAudit GroupAudit) {

		if (GroupAudit.getParent_audit_id() == 0) {

			if (advancedservice.validateGroupAudit(GroupAudit)) {
				return new ResponseEntity(new GoAuditsException("Name cannot be added, already exists"),
						HttpStatus.CONFLICT);

			}

		} else {

			if (advancedservice.validateGroupAudit1(GroupAudit)) {
				return new ResponseEntity(new GoAuditsException("Name cannot be edited, already exists"),
						HttpStatus.CONFLICT);

			}

		}

		int addAuditGroup = advancedservice.addAuditGroup(GroupAudit);
		List<GroupAudit> gList=new ArrayList<GroupAudit>();

		return new ResponseEntity<List<GroupAudit>>(gList, HttpStatus.OK);
	}

	
	
	

	
	
	

}
