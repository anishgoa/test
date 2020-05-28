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

import com.goaudits.business.entity.AuditWorkFlow;
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

		return new ResponseEntity<Integer>(addTag, HttpStatus.OK);
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

		int workflow = advancedservice.addWorkFlow(AuditWorkFlow);

		List<AuditWorkFlow> wrkFlwList = new ArrayList<AuditWorkFlow>();
		wrkFlwList.add(AuditWorkFlow);
		return new ResponseEntity<List<AuditWorkFlow>>(wrkFlwList, HttpStatus.OK);
	}

	@RequestMapping(value = "/workflow/edit", method = RequestMethod.POST)
	public ResponseEntity<?> editScheduleaudit(@RequestBody AuditWorkFlow AuditWorkFlow) {

		int workflow = advancedservice.editWorkFlow(AuditWorkFlow);

		List<AuditWorkFlow> wrkFlwList = new ArrayList<AuditWorkFlow>();
		wrkFlwList.add(AuditWorkFlow);
		return new ResponseEntity<List<AuditWorkFlow>>(wrkFlwList, HttpStatus.OK);
	}

	@RequestMapping(value = "/workflow/delete", method = RequestMethod.POST)
	public ResponseEntity<?> deleteScheduleaudit(@RequestBody AuditWorkFlow AuditWorkFlow) {

		int workflow = advancedservice.deleteWorkFlow(AuditWorkFlow);

		List<AuditWorkFlow> wrkFlwList = new ArrayList<AuditWorkFlow>();
		wrkFlwList.add(AuditWorkFlow);
		return new ResponseEntity<List<AuditWorkFlow>>(wrkFlwList, HttpStatus.OK);
	}

}
