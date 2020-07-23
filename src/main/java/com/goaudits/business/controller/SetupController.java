package com.goaudits.business.controller;

import java.util.ArrayList;
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
import com.goaudits.business.service.SetupService;
import com.goaudits.business.util.GoAuditsException;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/setup")
public class SetupController {

	@Autowired
	SetupService setupservice;

	@RequestMapping(value = "/company/list", method = RequestMethod.POST)
	public ResponseEntity<?> getAllCompanies(@RequestBody Company company) {
		List<Company> companyList = setupservice.getCompanyList(company);
		return new ResponseEntity<List<Company>>(companyList, HttpStatus.OK);
	}

	@RequestMapping(value = "/location/list", method = RequestMethod.POST)
	public ResponseEntity<List<Location>> getlocationsBasedeOnCompany(@RequestBody Location location) {
		List<Location> locationList = setupservice.getLocationsBasedonCompany(location);
		return new ResponseEntity<List<Location>>(locationList, HttpStatus.OK);
	}

	@RequestMapping(value = "/auditname/list", method = RequestMethod.POST)
	public ResponseEntity<List<AuditName>> getAuditNamesByCompany(@RequestBody AuditName auditname) {
		List<AuditName> auditNameList = setupservice.getAuditNamesByCompany(auditname);
		return new ResponseEntity<List<AuditName>>(auditNameList, HttpStatus.OK);
	}

	@RequestMapping(value = "/company/add", method = RequestMethod.POST)
	public ResponseEntity<?> addCompany(@RequestBody Company company) {

		if (setupservice.isCompanyExists(company)) {
			return new ResponseEntity<>(new GoAuditsException("Company cannot be added, already exists"),
					HttpStatus.CONFLICT);
		} else {
			try {
				Company comp = setupservice.addCompany(company);
				List<Company> companyList = new ArrayList<Company>();
				company.setClient_id(comp.getClient_id());
				company.setLogo(comp.getLogo());
				companyList.add(company);
				return new ResponseEntity<List<Company>>(companyList, HttpStatus.CREATED);
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println(e.getMessage());
				return new ResponseEntity<>(new GoAuditsException(e.getMessage()), HttpStatus.EXPECTATION_FAILED);
			}
		}
	}

	@RequestMapping(value = "/company/update", method = RequestMethod.PUT)
	public ResponseEntity<?> updateCompany(@RequestBody Company company) {

		if (!setupservice.isCompanyExistInDB(company)) {
			try {
				setupservice.updateCompany(company);
				List<Company> companyList = new ArrayList<Company>();
				companyList.add(company);
				return new ResponseEntity<List<Company>>(companyList, HttpStatus.OK);
			} catch (Exception e) {
				return new ResponseEntity<>(new GoAuditsException(e.getMessage()), HttpStatus.EXPECTATION_FAILED);
			}
		}
		return new ResponseEntity<>(new GoAuditsException("Company cannot be updated, already exists"),
				HttpStatus.CONFLICT);
	}

	@RequestMapping(value = "/getcompanycloneflag/{guid}", method = RequestMethod.GET)
	public ResponseEntity<?> getCompanycloneFlag(@PathVariable("guid") String guid) {
		String flag = setupservice.getCompanyCloneFlag(guid);
		Company cmp = new Company();
		cmp.setCompany_clone(Boolean.parseBoolean(flag));
		List<Company> companyList = new ArrayList<Company>();
		companyList.add(cmp);
		return new ResponseEntity<List<Company>>(companyList, HttpStatus.OK);
	}

	@RequestMapping(value = "/clonecompany", method = RequestMethod.POST)
	public ResponseEntity<?> cloneCompanies(@RequestBody PreTemplates PreTemplates) {

		String valid[] = setupservice.checkCompanydata(PreTemplates).split("---@%");
		if (Integer.parseInt(valid[0]) > 0) {
			return new ResponseEntity<>(new GoAuditsException(valid[1]), HttpStatus.CONFLICT);
		} else {
			setupservice.cloneCompanies(PreTemplates);
			List<PreTemplates> preList = new ArrayList<PreTemplates>();

			preList.add(PreTemplates);
			return new ResponseEntity<List<PreTemplates>>(preList, HttpStatus.CREATED);
		}

	}

	@RequestMapping(value = "/company/assignees", method = RequestMethod.POST)
	public ResponseEntity<?> getCompanyAssignees(@RequestBody Company company) {

		List<ActionPlanAssignee> assigneeList = setupservice.getCompanyAssigneeList(company);
		return new ResponseEntity<List<ActionPlanAssignee>>(assigneeList, HttpStatus.OK);
	}

	@RequestMapping(value = "/location/assignees", method = RequestMethod.POST)
	public ResponseEntity<?> getLocationAssignees(@RequestBody Location location) {

		List<ActionPlanAssignee> assigneeList = setupservice.getLocationAssigneeList(location);
		return new ResponseEntity<List<ActionPlanAssignee>>(assigneeList, HttpStatus.OK);
	}

	@RequestMapping(value = "/auditname/assignees", method = RequestMethod.POST)
	public ResponseEntity<?> getAuditnameAssignees(@RequestBody AuditName auditname) {

		List<ActionPlanAssignee> assigneeList = setupservice.getAuditNameAssigneeList(auditname);
		return new ResponseEntity<List<ActionPlanAssignee>>(assigneeList, HttpStatus.OK);
	}

//	@RequestMapping(value = "/company/updateassignees", method = RequestMethod.POST)
//	public ResponseEntity<?> getCompanyUpdateAssignees(@RequestBody List<ActionPlanAssignee> assignees) {
//
//		int assigneeUpdated = setupservice.updateCompanyAssignee(assignees);
//		return new ResponseEntity<Integer>(assigneeUpdated, HttpStatus.OK);
//	}
//
//	@RequestMapping(value = "/location/updateassignees", method = RequestMethod.POST)
//	public ResponseEntity<?> getLocationUpdateAssignees(@RequestBody List<ActionPlanAssignee> assignees) {
//
//		int a2ssigneeUpdated = setupservice.updateLocationAssignee(assignees);
//		return new ResponseEntity<Integer>(assigneeUpdated, HttpStatus.OK);
//	}
//
//	@RequestMapping(value = "/auditname/updateassignees", method = RequestMethod.POST)
//	public ResponseEntity<?> getAuditnameUpdateAssignees(@RequestBody List<ActionPlanAssignee> assignees) {
//
//		int assigneeUpdated = setupservice.updateAuditNameUpdateAssignee(assignees);
//		return new ResponseEntity<Integer>(assigneeUpdated, HttpStatus.OK);
//	}

	@RequestMapping(value = "/company/order", method = RequestMethod.POST)
	public ResponseEntity<?> addAuditType(@RequestBody Company company) {
		setupservice.companyReOrder(company);
		List<Company> companyList = new ArrayList<Company>();
		companyList.add(company);
		return new ResponseEntity<List<Company>>(companyList, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/location/add", method = RequestMethod.POST)
	public ResponseEntity<?> addLocation(@RequestBody Location location) {

		if (setupservice.isLocationExist(location)) {
			return new ResponseEntity<>(new GoAuditsException("Location cannot be added, already exists"),
					HttpStatus.CONFLICT);
		} else {
			try {
				String store_id = setupservice.addLocation(location);
				List<Location> locationList = new ArrayList<Location>();
				location.setStore_id(store_id);
				locationList.add(location);
				return new ResponseEntity<List<Location>>(locationList, HttpStatus.CREATED);
			} catch (Exception e) {
				return new ResponseEntity<>(new GoAuditsException(e.getMessage()), HttpStatus.EXPECTATION_FAILED);
			}
		}
	}

	@RequestMapping(value = "/location/update", method = RequestMethod.PUT)
	public ResponseEntity<?> updateLocation(@RequestBody Location location) {

		if (!setupservice.isLocationExistInDB(location)) {
			try {
				String store_id = setupservice.updateLocation(location);
				List<Location> locationList = new ArrayList<Location>();
				location.setStore_id(store_id);
				locationList.add(location);
				return new ResponseEntity<List<Location>>(locationList, HttpStatus.OK);
			} catch (Exception e) {
				return new ResponseEntity<>(new GoAuditsException(e.getMessage()), HttpStatus.EXPECTATION_FAILED);
			}
		}
		return new ResponseEntity<>(new GoAuditsException("Location cannot be updated, already exists"),
				HttpStatus.CONFLICT);
	}

	@RequestMapping(value = "getgpsflag/{guid}", method = RequestMethod.GET)
	public ResponseEntity<?> Locflag(@PathVariable("guid") String guid, Location location) {

		boolean gpsFilterFlag = setupservice.getGpsFlag(guid);

		List<Location> locationList = new ArrayList<Location>();
		location.setGps_location_filter_enabled(gpsFilterFlag);
		locationList.add(location);
		return new ResponseEntity<List<Location>>(locationList, HttpStatus.OK);
	}

	@RequestMapping(value = "/location/tags/{guid}/{uid}/{clientid}/{store_id}", method = RequestMethod.GET)
	public ResponseEntity<List<LocationTags>> getTagsBasedonLocation(@PathVariable("guid") String guid,
			@PathVariable("uid") String uid, @PathVariable("clientid") String clientid,
			@PathVariable("store_id") String store_id) {
		List<LocationTags> actionlist = setupservice.getTagsBasedonLocation(guid, uid, clientid, store_id);
		return new ResponseEntity<List<LocationTags>>(actionlist, HttpStatus.OK);
	}

	@RequestMapping(value = "gettagslist/{guid}/{type}", method = RequestMethod.GET)
	public ResponseEntity<?> getagList(@PathVariable("guid") String guid, @PathVariable("type") int type) {
		List<LocationTags> tagsList = setupservice.getTagsList(guid, type);
		return new ResponseEntity<List<LocationTags>>(tagsList, HttpStatus.OK);
	}

	@RequestMapping(value = "/auditname/add", method = RequestMethod.POST)
	public ResponseEntity<?> addAuditType(@RequestBody AuditName auditname) {
		if (setupservice.isAuditNameExist(auditname)) {
			return new ResponseEntity<>(new GoAuditsException("Auditname cannot be added, already exists"),
					HttpStatus.CONFLICT);
		}
		try {
			AuditName AudName = setupservice.addAuditName(auditname);
			auditname.setAudit_type_id(AudName.getAudit_type_id());
			auditname.setLogo(AudName.getLogo());
			List<AuditName> auditNameList = new ArrayList<AuditName>();
			auditNameList.add(auditname);
			return new ResponseEntity<List<AuditName>>(auditNameList, HttpStatus.CREATED);
		} catch (Exception e) {
			e.printStackTrace();

			return new ResponseEntity<>(new GoAuditsException("Something went wrong"), HttpStatus.EXPECTATION_FAILED);
		}
	}

	@RequestMapping(value = "/auditname/order", method = RequestMethod.POST)
	public ResponseEntity<?> reOrderAuditName(@RequestBody AuditName auditname) {
		setupservice.addAuditNameOrder(auditname);
		List<AuditName> auditNameList = new ArrayList<AuditName>();
		auditNameList.add(auditname);
		return new ResponseEntity<List<AuditName>>(auditNameList, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/auditname/update", method = RequestMethod.PUT)
	public ResponseEntity<?> updateAuditName(@RequestBody AuditName auditname) {
		if (!setupservice.isAuditNameExistInDB(auditname)) {
			try {
				AuditName AudName = setupservice.updateAuditName(auditname);
				auditname.setAudit_type_id(AudName.getAudit_type_id());
				List<AuditName> auditNameList = new ArrayList<AuditName>();
				auditNameList.add(auditname);
				return new ResponseEntity<List<AuditName>>(auditNameList, HttpStatus.OK);
			} catch (Exception e) {
				System.out.println(e.getMessage());
				return new ResponseEntity<>(new GoAuditsException("Something went wrong"),
						HttpStatus.EXPECTATION_FAILED);
			}
		}
		return new ResponseEntity<>(new GoAuditsException("Auditname cannot be updated, already exists"),
				HttpStatus.NOT_FOUND);
	}

	@RequestMapping(value = "/cloneauditname", method = RequestMethod.POST)
	public ResponseEntity<?> cloneAuditname(@RequestBody PreTemplates PreTemplates) {

		String valid[] = setupservice.PreTemplates(PreTemplates).split("---@%");
		if (PreTemplates.isValidate() && Integer.parseInt(valid[0]) > 0) {

			return new ResponseEntity(new GoAuditsException(valid[1]), HttpStatus.CONFLICT);
		} else {
			try {
				setupservice.cloneAuditName(PreTemplates);
				List<PreTemplates> preList = new ArrayList<PreTemplates>();

				preList.add(PreTemplates);
				return new ResponseEntity<List<PreTemplates>>(preList, HttpStatus.CREATED);
			} catch (Exception e) {
				return new ResponseEntity(new GoAuditsException(e.getMessage()), HttpStatus.EXPECTATION_FAILED);
			}
		}
	}
	
	

	@RequestMapping(value = "/getscorerange/{guid}/{client_id}/{audit_type_id}", method = RequestMethod.GET)
	public ResponseEntity<?> getScoreRange(@PathVariable String guid, @PathVariable String client_id,
			@PathVariable String audit_type_id) {
		List<ScoreRange> scorerangeList = setupservice.getScoreRange(guid, client_id, audit_type_id);
		return new ResponseEntity<List<ScoreRange>>(scorerangeList, HttpStatus.OK);
	}

	@RequestMapping(value = "/addscorerange", method = RequestMethod.POST)
	public ResponseEntity<?> getScoreRange(@RequestBody List<ScoreRange> scorerange) {
		setupservice.addScoreRange(scorerange);
		return new ResponseEntity<List<ScoreRange>>(scorerange, HttpStatus.OK);
	}

	@RequestMapping(value = "/emailtemplate/list", method = RequestMethod.POST)
	public ResponseEntity<?> getEmailTemplates(@RequestBody EmailTemplate emailtemplate) {

		List<EmailTemplate> emailTemplateList = setupservice.getEmailTemplates(emailtemplate);
	
		return new ResponseEntity<List<EmailTemplate>>(emailTemplateList, HttpStatus.OK);
	}

	@RequestMapping(value = "/emailtemplate/update", method = RequestMethod.POST)
	public ResponseEntity<?> updateEmailTemplate(@RequestBody EmailTemplate emailTemplate) {
		try {
			setupservice.updateEmailTemplate(emailTemplate);
			List<EmailTemplate> emailList = new ArrayList<EmailTemplate>();
			emailList.add(emailTemplate);
			return new ResponseEntity<List<EmailTemplate>>(emailList, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(new GoAuditsException("Something went wrong"), HttpStatus.EXPECTATION_FAILED);

		}
	}

	@RequestMapping(value = "/report/list", method = RequestMethod.POST)
	public ResponseEntity<?> getAllReports(@RequestBody Report report) {
		List<Report> reportList = setupservice.getReports(report);
		return new ResponseEntity<List<Report>>(reportList, HttpStatus.OK);
	}

	@RequestMapping(value = "/report/update", method = RequestMethod.PUT)
	public ResponseEntity<?> updateReport(@RequestBody Report report) {
		try {
			setupservice.updateReport(report);
			List<Report> reportList = new ArrayList<Report>();
			reportList.add(report);
			return new ResponseEntity<List<Report>>(reportList, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(new GoAuditsException(e.getMessage()), HttpStatus.EXPECTATION_FAILED);
		}
	}

	@RequestMapping(value = "/reporttemplate/list/{guid}", method = RequestMethod.GET)
	public ResponseEntity<List<Report>> getReporttemplates(@PathVariable("guid") String guid) {
		List<Report> reportList = setupservice.getReporttemplates(guid);
		return new ResponseEntity<List<Report>>(reportList, HttpStatus.OK);
	}

	@RequestMapping(value = "/templateslist", method = RequestMethod.GET)
	public ResponseEntity<List<Company>> getPreexistingtemplates() {
		List<Company> templateslist = setupservice.getPreexistingTemplates();
		return new ResponseEntity<List<Company>>(templateslist, HttpStatus.OK);
	}

	@RequestMapping(value = "/preclient/{client_id}", method = RequestMethod.GET)
	public ResponseEntity<List<AuditName>> getPreAuditnames(@PathVariable int client_id) {
		List<AuditName> auditnamelist = setupservice.getPreAuditnames(client_id);
		return new ResponseEntity<List<AuditName>>(auditnamelist, HttpStatus.OK);
	}

	@RequestMapping(value = "/preclient/all", method = RequestMethod.GET)
	public ResponseEntity<List<AuditName>> getPreAuditnamesAll() {
		List<AuditName> auditnamelist = setupservice.getAllPreAuditnames();
		return new ResponseEntity<List<AuditName>>(auditnamelist, HttpStatus.OK);
	}

	@RequestMapping(value = "/prequestion/count/{client_id}/{audit_type_id}", method = RequestMethod.GET)
	public ResponseEntity<?> getPreQuestionscount(@PathVariable int client_id, @PathVariable int audit_type_id) {

		int count = setupservice.getPretempletQuestionscount(client_id, audit_type_id);
		Company cmp = new Company();
		cmp.setCount(count);
		List<Company> companyList = new ArrayList<Company>();
		companyList.add(cmp);
		return new ResponseEntity<List<Company>>(companyList, HttpStatus.OK);
	}

	@RequestMapping(value = "/prequestion/{client_id}/{audit_type_id}", method = RequestMethod.GET)
	public ResponseEntity<List<Section>> getPreQuestions(@PathVariable int client_id, @PathVariable int audit_type_id) {
		Section section = new Section();
		section.setClient_id(client_id);
		section.setAudit_type_id(audit_type_id);
		List<Section> sectionlist = setupservice.getPretempletQuestions(section);
		return new ResponseEntity<List<Section>>(sectionlist, HttpStatus.OK);
	}

	@RequestMapping(value = "/pretemplate/add", method = RequestMethod.POST)
	public ResponseEntity<?> addpreTemplate(@RequestBody PreTemplates PreTemplates) {

		String valid[] = setupservice.PreTemplates(PreTemplates).split("---@%");
		if (Integer.parseInt(valid[0]) > 0) {
			return new ResponseEntity<>(new GoAuditsException("Template cannot be created, already exists"),
					HttpStatus.CONFLICT);
		}
		try {
			setupservice.createPreTemplate(PreTemplates);
			List<PreTemplates> templatesList = new ArrayList<PreTemplates>();
			templatesList.add(PreTemplates);
			return new ResponseEntity<List<PreTemplates>>(templatesList, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(new GoAuditsException(e.getMessage()), HttpStatus.EXPECTATION_FAILED);
		}
	}
	
	@RequestMapping(value = "/guideddata", method = RequestMethod.GET)
	public ResponseEntity<?> guidedSetupData() {
		List<GuidedSetup> dataList=setupservice.getGuidedSetupdata();
		return new ResponseEntity<List<GuidedSetup>>(dataList, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/cmpcount/{guid}", method = RequestMethod.GET)
	public ResponseEntity<?> CompanyCount(@PathVariable("guid") String guid) {
		int count=setupservice.getCompanyCount(guid);
		List<Integer> list=new ArrayList<Integer>();
		list.add(count);
		return new ResponseEntity<List<Integer>>(list, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/createdguided", method = RequestMethod.POST)
	public ResponseEntity<?> createguidedSetup(@RequestBody GuidedSetup gudsetp,Company cmp) {
		cmp.setGuid(gudsetp.getGuid());
		cmp.setUid(gudsetp.getUid());
		cmp.setClient_name(gudsetp.getCompany_name());
		cmp.setLogo(gudsetp.getLogo());
		cmp.setActive(true);
		setupservice.addCompany(cmp);
		setupservice.createGuided(gudsetp);
		List<GuidedSetup> arr=new ArrayList<GuidedSetup>();
		arr.add(gudsetp);
		return new ResponseEntity<List<GuidedSetup>>(arr, HttpStatus.OK);
	}
	

}