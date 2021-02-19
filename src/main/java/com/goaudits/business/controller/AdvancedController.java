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
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.goaudits.business.entity.ActionPlanSettings;
import com.goaudits.business.entity.AuditName;
import com.goaudits.business.entity.AuditWorkFlow;
import com.goaudits.business.entity.Broadcast;
import com.goaudits.business.entity.CustomFieldList;
import com.goaudits.business.entity.Customfields;
import com.goaudits.business.entity.GroupAudit;
import com.goaudits.business.entity.LocationTags;
import com.goaudits.business.entity.Personseen;
import com.goaudits.business.entity.Tag;
import com.goaudits.business.entity.User;
import com.goaudits.business.service.AdvancedService;
import com.goaudits.business.util.GoAuditsException;
import com.goaudits.business.util.Utils;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/setup")
public class AdvancedController {

	@Autowired
	AdvancedService advancedservice;

	@RequestMapping(value = "/getpersonseen/{guid}", method = RequestMethod.GET)
	public ResponseEntity<?> getPersonSenn(@PathVariable("guid") String guid,
			@RequestHeader(name = "Authorization") String token) {
		try {
			if (token != null && token != "" && !token.isEmpty()) {
				token = token.replace("Bearer ", "");
				String userguid = Utils.getGuid(token);
				guid = userguid;
			}

			List<Personseen> psenlist = advancedservice.getPersonseen(guid);
			return new ResponseEntity<List<Personseen>>(psenlist, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(new GoAuditsException("Something went wrong"), HttpStatus.EXPECTATION_FAILED);
		}
	}

	@RequestMapping(value = "/addpersonseen", method = RequestMethod.POST)
	public ResponseEntity<?> addoreditPersonSenn(@RequestBody Personseen pseen,
			@RequestHeader(name = "Authorization") String token) {

		List<LinkedHashMap> validatelist = advancedservice.validateaddPersonSeen(pseen);

		try {
			if (token != null && token != "" && !token.isEmpty()) {
				token = token.replace("Bearer ", "");
				String guid = Utils.getGuid(token);
				String uid = Utils.getUid(token);
				pseen.setGuid(guid);
				pseen.setUid(uid);
			}
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
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(new GoAuditsException("Something went wrong"), HttpStatus.EXPECTATION_FAILED);
		}
	}

	@RequestMapping(value = "/editpersonseen", method = RequestMethod.POST)
	public ResponseEntity<?> editPersonSenn(@RequestBody Personseen pseen,
			@RequestHeader(name = "Authorization") String token) {

		List<LinkedHashMap> validatelist = advancedservice.validateeditPersonSeen(pseen);

		try {
			if (token != null && token != "" && !token.isEmpty()) {
				token = token.replace("Bearer ", "");
				String guid = Utils.getGuid(token);
				String uid = Utils.getUid(token);
				pseen.setGuid(guid);
				pseen.setUid(uid);
			}
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
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(new GoAuditsException("Something went wrong"), HttpStatus.EXPECTATION_FAILED);
		}
	}

	@RequestMapping(value = "/reporttag/list", method = RequestMethod.POST)
	public ResponseEntity<?> getReportTag(@RequestBody LocationTags tag,
			@RequestHeader(name = "Authorization") String token) {
		try {
			if (token != null && token != "" && !token.isEmpty()) {
				token = token.replace("Bearer ", "");
				String guid = Utils.getGuid(token);
				String uid = Utils.getUid(token);
				tag.setGuid(guid);
				tag.setUid(uid);
			}
			List<LocationTags> tagsList = advancedservice.getReportTag(tag);

			return new ResponseEntity<List<LocationTags>>(tagsList, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(new GoAuditsException("Something went wrong"), HttpStatus.EXPECTATION_FAILED);
		}
	}

	@RequestMapping(value = "addtagcategory", method = RequestMethod.POST)
	public ResponseEntity<?> addCategory(@RequestBody LocationTags loct,
			@RequestHeader(name = "Authorization") String token) {

		try {
			if (token != null && token != "" && !token.isEmpty()) {
				token = token.replace("Bearer ", "");
				String guid = Utils.getGuid(token);
				String uid = Utils.getUid(token);
				loct.setGuid(guid);
				loct.setUid(uid);
			}
			if (loct.getCategory_id() == 0) {

				if (advancedservice.validateCategory(loct)) {
					return new ResponseEntity(new GoAuditsException("Category name already exists"),
							HttpStatus.CONFLICT);
				}

			} else {

				if (advancedservice.validateEdCategory(loct)) {
					return new ResponseEntity(new GoAuditsException("Category name already exists"),
							HttpStatus.CONFLICT);
				}
			}

			int category_id = advancedservice.addTagCategory(loct);
			List<LocationTags> TagsList = new ArrayList<LocationTags>();
			loct.setCategory_id(category_id);
			TagsList.add(loct);
			return new ResponseEntity<List<LocationTags>>(TagsList, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(new GoAuditsException("Something went wrong"), HttpStatus.EXPECTATION_FAILED);
		}
	}

	@RequestMapping(value = "addtag", method = RequestMethod.POST)
	public ResponseEntity<?> addTag(@RequestBody LocationTags loct,
			@RequestHeader(name = "Authorization") String token) {

		try {
			if (token != null && token != "" && !token.isEmpty()) {
				token = token.replace("Bearer ", "");
				String guid = Utils.getGuid(token);
				String uid = Utils.getUid(token);
				loct.setGuid(guid);
				loct.setUid(uid);
			}
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
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(new GoAuditsException("Something went wrong"), HttpStatus.EXPECTATION_FAILED);
		}
	}

	@RequestMapping(value = "deletetag", method = RequestMethod.POST)
	public ResponseEntity<?> deleteTag(@RequestBody LocationTags loct,
			@RequestHeader(name = "Authorization") String token) {

		try {
			if (token != null && token != "" && !token.isEmpty()) {
				token = token.replace("Bearer ", "");
				String guid = Utils.getGuid(token);
				String uid = Utils.getUid(token);
				loct.setGuid(guid);
				loct.setUid(uid);
			}
			if (advancedservice.CheckTagsAssigned(loct)) {
				return new ResponseEntity(new GoAuditsException("Tag already configured to location"),
						HttpStatus.CONFLICT);
			}

			int deletetag = advancedservice.deleteTag(loct);

			return new ResponseEntity<Integer>(deletetag, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(new GoAuditsException("Something went wrong"), HttpStatus.EXPECTATION_FAILED);
		}
	}

	@RequestMapping(value = "deletetagcategory", method = RequestMethod.POST)
	public ResponseEntity<?> deleteTagCategory(@RequestBody LocationTags loct,
			@RequestHeader(name = "Authorization") String token) {

		try {
			if (token != null && token != "" && !token.isEmpty()) {
				token = token.replace("Bearer ", "");
				String guid = Utils.getGuid(token);
				String uid = Utils.getUid(token);
				loct.setGuid(guid);
				loct.setUid(uid);
			}
			if (advancedservice.CheckTagsCategoryAssigned(loct)) {
				return new ResponseEntity(new GoAuditsException("Tag already configured to location"),
						HttpStatus.CONFLICT);
			}
			int deletetag = advancedservice.deleteTagCategory(loct);

			return new ResponseEntity<Integer>(deletetag, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(new GoAuditsException("Something went wrong"), HttpStatus.EXPECTATION_FAILED);
		}
	}

	@RequestMapping(value = "/workflow", method = RequestMethod.POST)
	public ResponseEntity<?> getWorkFlowList(@RequestBody AuditWorkFlow workflow,
			@RequestHeader(name = "Authorization") String token) {

		try {
			if (token != null && token != "" && !token.isEmpty()) {
				token = token.replace("Bearer ", "");
				String guid = Utils.getGuid(token);
				String uid = Utils.getUid(token);
				workflow.setGuid(guid);
				workflow.setUid(uid);
			}
			List<AuditWorkFlow> workflowlist = advancedservice.getAuditWorkflowList(workflow);
			return new ResponseEntity<List<AuditWorkFlow>>(workflowlist, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(new GoAuditsException("Something went wrong"), HttpStatus.EXPECTATION_FAILED);
		}
	}

	@RequestMapping(value = "/getadmins", method = RequestMethod.POST)
	public ResponseEntity<?> geAdminList(@RequestBody AuditWorkFlow AuditWorkFlow,
			@RequestHeader(name = "Authorization") String token) {

		try {
			if (token != null && token != "" && !token.isEmpty()) {
				token = token.replace("Bearer ", "");
				String guid = Utils.getGuid(token);
				String uid = Utils.getUid(token);
				AuditWorkFlow.setGuid(guid);
				AuditWorkFlow.setUid(uid);
			}
			if (AuditWorkFlow.getStore_id() == "" || AuditWorkFlow.getStore_id().isEmpty()) {
				AuditWorkFlow.setStore_id("0");
			}

			if (AuditWorkFlow.getAudit_type_id() == "" || AuditWorkFlow.getAudit_type_id().isEmpty()) {
				AuditWorkFlow.setAudit_type_id("0");
			}
			List<User> adminlist = advancedservice.getAdminlist(AuditWorkFlow);
			return new ResponseEntity<List<User>>(adminlist, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(new GoAuditsException("Something went wrong"), HttpStatus.EXPECTATION_FAILED);
		}
	}

	@RequestMapping(value = "/workflow/add", method = RequestMethod.POST)
	public ResponseEntity<?> addScheduleaudit(@RequestBody AuditWorkFlow AuditWorkFlow,
			@RequestHeader(name = "Authorization") String token) {

		try {
			if (token != null && token != "" && !token.isEmpty()) {
				token = token.replace("Bearer ", "");
				String guid = Utils.getGuid(token);
				String uid = Utils.getUid(token);
				AuditWorkFlow.setGuid(guid);
				AuditWorkFlow.setUid(uid);
			}
			advancedservice.addWorkFlow(AuditWorkFlow);

			List<AuditWorkFlow> wrkFlwList = new ArrayList<AuditWorkFlow>();
			wrkFlwList.add(AuditWorkFlow);
			return new ResponseEntity<List<AuditWorkFlow>>(wrkFlwList, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(new GoAuditsException("Something went wrong"), HttpStatus.EXPECTATION_FAILED);
		}
	}

	@RequestMapping(value = "/workflow/edit", method = RequestMethod.POST)
	public ResponseEntity<?> editScheduleaudit(@RequestBody AuditWorkFlow AuditWorkFlow,
			@RequestHeader(name = "Authorization") String token) {
		try {
			if (token != null && token != "" && !token.isEmpty()) {
				token = token.replace("Bearer ", "");
				String guid = Utils.getGuid(token);
				String uid = Utils.getUid(token);
				AuditWorkFlow.setGuid(guid);
				AuditWorkFlow.setUid(uid);
			}
			advancedservice.editWorkFlow(AuditWorkFlow);

			List<AuditWorkFlow> wrkFlwList = new ArrayList<AuditWorkFlow>();
			wrkFlwList.add(AuditWorkFlow);
			return new ResponseEntity<List<AuditWorkFlow>>(wrkFlwList, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(new GoAuditsException("Something went wrong"), HttpStatus.EXPECTATION_FAILED);
		}
	}

	@RequestMapping(value = "/workflow/delete", method = RequestMethod.POST)
	public ResponseEntity<?> deleteScheduleaudit(@RequestBody AuditWorkFlow AuditWorkFlow,
			@RequestHeader(name = "Authorization") String token) {

		try {
			if (token != null && token != "" && !token.isEmpty()) {
				token = token.replace("Bearer ", "");
				String guid = Utils.getGuid(token);
				String uid = Utils.getUid(token);
				AuditWorkFlow.setGuid(guid);
				AuditWorkFlow.setUid(uid);
			}
			advancedservice.deleteWorkFlow(AuditWorkFlow);

			List<AuditWorkFlow> wrkFlwList = new ArrayList<AuditWorkFlow>();
			wrkFlwList.add(AuditWorkFlow);
			return new ResponseEntity<List<AuditWorkFlow>>(wrkFlwList, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(new GoAuditsException("Something went wrong"), HttpStatus.EXPECTATION_FAILED);
		}
	}

	@RequestMapping(value = "/customfields/list2", method = RequestMethod.POST)
	public ResponseEntity<?> getAllCustomFields2(@RequestBody AuditName audit,
			@RequestHeader(name = "Authorization") String token) {
		try {
			if (token != null && token != "" && !token.isEmpty()) {
				token = token.replace("Bearer ", "");
				String guid = Utils.getGuid(token);
				String uid = Utils.getUid(token);
				audit.setGuid(guid);
				audit.setUid(uid);
			}
			List<Customfields> customFieldsList = advancedservice.getAllCustomfields(audit.getGuid(), audit.getUid(),
					audit.getClient_id());

//			if (customFieldsList.isEmpty()) {
//				return new ResponseEntity(new GoAuditsException("No custom fields are found"), HttpStatus.NOT_FOUND);
//			}
			return new ResponseEntity<List<Customfields>>(customFieldsList, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(new GoAuditsException("Something went wrong"), HttpStatus.EXPECTATION_FAILED);
		}
	}

	@RequestMapping(value = "/customfields/list", method = RequestMethod.POST)
	public ResponseEntity<?> getAllCustomFields(@RequestBody AuditName audit,
			@RequestHeader(name = "Authorization") String token) {

		try {
			if (token != null && token != "" && !token.isEmpty()) {
				token = token.replace("Bearer ", "");
				String guid = Utils.getGuid(token);
				String uid = Utils.getUid(token);
				audit.setGuid(guid);
				audit.setUid(uid);
			}
			List<CustomFieldList> customFieldsList = advancedservice.getCustomFieldsList(audit);

			return new ResponseEntity<List<CustomFieldList>>(customFieldsList, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(new GoAuditsException("Something went wrong"), HttpStatus.EXPECTATION_FAILED);
		}
	}

	@RequestMapping(value = "/customfields/add", method = RequestMethod.POST)
	public ResponseEntity<?> addCustomFields(@RequestBody Customfields customfields,
			@RequestHeader(name = "Authorization") String token) {

		try {
			if (token != null && token != "" && !token.isEmpty()) {
				token = token.replace("Bearer ", "");
				String guid = Utils.getGuid(token);
				String uid = Utils.getUid(token);
				customfields.setGuid(guid);
				customfields.setUid(uid);
			}
			if (advancedservice.isCustomfieldsExist(customfields)) {
				return new ResponseEntity(new GoAuditsException("Custom fields cannot be added, already exists"),
						HttpStatus.CONFLICT);
			}

			advancedservice.addCustomfields(customfields);
			List<Customfields> customFieldsList = new ArrayList<Customfields>();
			customFieldsList.add(customfields);
			return new ResponseEntity<List<Customfields>>(customFieldsList, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity(new GoAuditsException("Something went wrong"), HttpStatus.EXPECTATION_FAILED);
		}
	}

	@RequestMapping(value = "/customfields/update", method = RequestMethod.PUT)
	public ResponseEntity<?> updateCustomfields(@RequestBody Customfields customfields,
			@RequestHeader(name = "Authorization") String token) {

		try {
			if (token != null && token != "" && !token.isEmpty()) {
				token = token.replace("Bearer ", "");
				String guid = Utils.getGuid(token);
				String uid = Utils.getUid(token);
				customfields.setGuid(guid);
				customfields.setUid(uid);
			}
			if (advancedservice.isCustomfieldsExist(customfields)) {

				advancedservice.updateCustomfields(customfields);

				List<Customfields> customFieldsList = new ArrayList<Customfields>();
				customFieldsList.add(customfields);
				return new ResponseEntity<List<Customfields>>(customFieldsList, HttpStatus.CREATED);

			}
			return new ResponseEntity<>(new GoAuditsException("Custom field cannot be updated, record not found"),
					HttpStatus.CONFLICT);
		} catch (Exception e) {
			return new ResponseEntity(new GoAuditsException("Something went wrong"), HttpStatus.EXPECTATION_FAILED);
		}
	}

	@RequestMapping(value = "/actionplansettings", method = RequestMethod.POST)
	public ResponseEntity<?> getActionPlanSetngs(@RequestBody ActionPlanSettings ActionPlanSettings,
			@RequestHeader(name = "Authorization") String token) {

		try {
			if (token != null && token != "" && !token.isEmpty()) {
				token = token.replace("Bearer ", "");
				String guid = Utils.getGuid(token);
				String uid = Utils.getUid(token);
				ActionPlanSettings.setGuid(guid);
			}
			List<ActionPlanSettings> actlist = advancedservice.getActionPlanSettngs(ActionPlanSettings);

			return new ResponseEntity<List<ActionPlanSettings>>(actlist, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(new GoAuditsException("Something went wrong"), HttpStatus.EXPECTATION_FAILED);
		}
	}

	@RequestMapping(value = "/addactionplansetngs", method = RequestMethod.POST)
	public ResponseEntity<?> addActionPlanSetngs(@RequestBody ActionPlanSettings ActionPlanSettings,
			@RequestHeader(name = "Authorization") String token) {

		try {
			if (token != null && token != "" && !token.isEmpty()) {
				token = token.replace("Bearer ", "");
				String guid = Utils.getGuid(token);
				String uid = Utils.getUid(token);
				ActionPlanSettings.setGuid(guid);
			}
			if (ActionPlanSettings.getPriority_id() == 0) {

				if (advancedservice.validateaddActionSts(ActionPlanSettings)) {
					return new ResponseEntity<>(new GoAuditsException("Priority already exists"), HttpStatus.CONFLICT);
				} else {
					int actplnadd = advancedservice.addOrEditActionPlanSettngs(ActionPlanSettings);

					List<ActionPlanSettings> actionPlansetngs = new ArrayList<ActionPlanSettings>();
					ActionPlanSettings.setPriority_id(actplnadd);
					actionPlansetngs.add(ActionPlanSettings);
					return new ResponseEntity<List<ActionPlanSettings>>(actionPlansetngs, HttpStatus.OK);
				}

			} else if (advancedservice.validateeditActionSts(ActionPlanSettings)) {
				return new ResponseEntity<>(new GoAuditsException("Priority already exists"), HttpStatus.CONFLICT);

			} else {
				int actplnadd = advancedservice.addOrEditActionPlanSettngs(ActionPlanSettings);

				List<ActionPlanSettings> actionPlansetngs = new ArrayList<ActionPlanSettings>();
				ActionPlanSettings.setPriority_id(actplnadd);
				actionPlansetngs.add(ActionPlanSettings);
				return new ResponseEntity<List<ActionPlanSettings>>(actionPlansetngs, HttpStatus.OK);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(new GoAuditsException("Something went wrong"), HttpStatus.EXPECTATION_FAILED);
		}

	}

	/**
	 * List report tags of a company
	 * 
	 * @param guid
	 * @param uid
	 * @param client_id
	 * @return
	 */
	@RequestMapping(value = "/report/tag/list", method = RequestMethod.POST)
	public ResponseEntity<?> getReportTag(@RequestBody Tag tag, @RequestHeader(name = "Authorization") String token) {

		try {
			if (token != null && token != "" && !token.isEmpty()) {
				token = token.replace("Bearer ", "");
				String guid = Utils.getGuid(token);
				String uid = Utils.getUid(token);
				tag.setGuid(guid);
				tag.setUid(uid);
			}

			List<Tag> reportTagList = advancedservice.getReportTag(tag.getGuid(), tag.getUid(), tag.getClient_id());

			return new ResponseEntity<List<Tag>>(reportTagList, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(new GoAuditsException("Something went wrong"), HttpStatus.EXPECTATION_FAILED);
		}
	}

	/**
	 * Add / create a new report tag
	 * 
	 * @param tag
	 * @return
	 */
	@RequestMapping(value = "/reporttag/add", method = RequestMethod.POST)
	public ResponseEntity<?> addReportTag(@RequestBody Tag tag, @RequestHeader(name = "Authorization") String token) {

		try {
			if (token != null && token != "" && !token.isEmpty()) {
				token = token.replace("Bearer ", "");
				String guid = Utils.getGuid(token);
				String uid = Utils.getUid(token);
				tag.setGuid(guid);
				tag.setUid(uid);
			}
			if (advancedservice.validateaddTag(tag)) {

				return new ResponseEntity(new GoAuditsException("Tag cannot be added,tag code already exists"),
						HttpStatus.CONFLICT);

			} else {
				int reportTag = advancedservice.addReportTag(tag);
				return new ResponseEntity<Integer>(reportTag, HttpStatus.OK);

			}
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(new GoAuditsException("Something went wrong"), HttpStatus.EXPECTATION_FAILED);
		}

	}

	/**
	 * Update or edit the report tag
	 * 
	 * @param tag
	 * @return
	 */
	@RequestMapping(value = "/reporttag/update", method = RequestMethod.PUT)
	public ResponseEntity<?> updateReportTag(@RequestBody Tag tag,
			@RequestHeader(name = "Authorization") String token) {

		try {
			if (token != null && token != "" && !token.isEmpty()) {
				token = token.replace("Bearer ", "");
				String guid = Utils.getGuid(token);
				String uid = Utils.getUid(token);
				tag.setGuid(guid);
				tag.setUid(uid);
			}
			if (advancedservice.validateeditTag(tag)) {

				return new ResponseEntity(new GoAuditsException("Tag cannot be saved,tag code already exists"),
						HttpStatus.CONFLICT);

			} else {
				int reportTag = advancedservice.addReportTag(tag);
				return new ResponseEntity<Integer>(reportTag, HttpStatus.OK);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(new GoAuditsException("Something went wrong"), HttpStatus.EXPECTATION_FAILED);
		}

	}

	@RequestMapping(value = "/groupaudit/list/{guid}/{active}", method = RequestMethod.GET)
	public ResponseEntity<?> getGroupAudit(@PathVariable("guid") String guid, @PathVariable("active") boolean active,
			@RequestHeader(name = "Authorization") String token) {

		try {
			if (token != null && token != "" && !token.isEmpty()) {
				token = token.replace("Bearer ", "");
				String userguid = Utils.getGuid(token);
				guid = userguid;
			}

			List<GroupAudit> auditTypeList = advancedservice.getGroupAudit(guid, active);
			return new ResponseEntity<List<GroupAudit>>(auditTypeList, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(new GoAuditsException("Something went wrong"), HttpStatus.EXPECTATION_FAILED);
		}
	}

	@RequestMapping(value = "/groupaudit/auditnames/{guid}/{uid}/{client_id}/{parent_audit_id}", method = RequestMethod.GET)
	public ResponseEntity<?> getAllClients(@PathVariable("guid") String guid,
			@PathVariable("uid") String uid, @PathVariable("client_id") int client_id,
			@PathVariable("parent_audit_id") int parent_audit_id, @RequestHeader(name = "Authorization") String token) {

		try {
			if (token != null && token != "" && !token.isEmpty()) {
				token = token.replace("Bearer ", "");
				String userguid = Utils.getGuid(token);
				guid = userguid;
			}

			List<AuditName> auditTypeList = advancedservice.getAuditTypeList(guid, uid, client_id, parent_audit_id);

			return new ResponseEntity<List<AuditName>>(auditTypeList, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(new GoAuditsException("Something went wrong"), HttpStatus.EXPECTATION_FAILED);
		}
	}

	@RequestMapping(value = "/groupaudit/add", method = RequestMethod.POST)
	public ResponseEntity<?> addGroupAudit(@RequestBody GroupAudit GroupAudit,
			@RequestHeader(name = "Authorization") String token) {

		try {
			if (token != null && token != "" && !token.isEmpty()) {
				token = token.replace("Bearer ", "");
				String guid = Utils.getGuid(token);
				String uid = Utils.getUid(token);
				GroupAudit.setGuid(guid);
			}
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
			List<GroupAudit> gList = new ArrayList<GroupAudit>();

			return new ResponseEntity<List<GroupAudit>>(gList, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(new GoAuditsException("Something went wrong"), HttpStatus.EXPECTATION_FAILED);
		}
		
		
	}
	
	@RequestMapping(value = "/broadcast/list", method = RequestMethod.POST)
	public ResponseEntity<?> publishList(Broadcast broadcast,
			@RequestHeader(name = "Authorization") String token) {
		try {
			if (token != null && token != "" && !token.isEmpty()) {
				token = token.replace("Bearer ", "");
				String guid = Utils.getGuid(token);
				String uid = Utils.getUid(token);
				broadcast.setGuid(guid);
			}
		List<Broadcast> userlist = advancedservice.getBroadcastList(broadcast);
		return new ResponseEntity<List<Broadcast>>(userlist, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(new GoAuditsException("Something went wrong"), HttpStatus.EXPECTATION_FAILED);
		}
	}

	@RequestMapping(value = "/broadcast/details", method = RequestMethod.POST)
	public ResponseEntity<?> publishListDetails(@RequestBody Broadcast broadcast,
			@RequestHeader(name = "Authorization") String token) {
		try {
			if (token != null && token != "" && !token.isEmpty()) {
				token = token.replace("Bearer ", "");
				String guid = Utils.getGuid(token);
				String uid = Utils.getUid(token);
				broadcast.setGuid(guid);
			}
			List<Broadcast> publishlist = advancedservice.getBroadcastdetails(broadcast);
			return new ResponseEntity<List<Broadcast>>(publishlist, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(new GoAuditsException("Something went wrong"), HttpStatus.EXPECTATION_FAILED);
		}
	}
	
	
}
