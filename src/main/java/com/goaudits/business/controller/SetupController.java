package com.goaudits.business.controller;

import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import com.goaudits.business.entity.ActionPlanAssignee;
import com.goaudits.business.entity.AuditName;
import com.goaudits.business.entity.Company;
import com.goaudits.business.entity.EmailTemplate;
import com.goaudits.business.entity.GuidedSetup;
import com.goaudits.business.entity.Help;
import com.goaudits.business.entity.Location;
import com.goaudits.business.entity.LocationTags;
import com.goaudits.business.entity.Menu;
import com.goaudits.business.entity.PreTemplates;
import com.goaudits.business.entity.Report;
import com.goaudits.business.entity.Reportref;
import com.goaudits.business.entity.ScoreRange;
import com.goaudits.business.entity.Section;
import com.goaudits.business.service.SetupService;
import com.goaudits.business.util.GoAuditsException;
import com.goaudits.business.util.Utils;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/setup")
public class SetupController {

	private final Logger log = LogManager.getLogger(getClass().getName());
	
	@Autowired
	SetupService setupservice;

	@RequestMapping(value = "/company/list", method = RequestMethod.POST)
	public ResponseEntity<?> getAllCompanies(@RequestBody Company company,
			@RequestHeader(name = "Authorization") String token) {
		try {

			if (token != null && token != "" && !token.isEmpty()) {
				token = token.replace("Bearer ", "");
				String guid = Utils.getGuid(token);
				String uid = Utils.getUid(token);
				company.setGuid(guid);
				company.setUid(uid);
			}
			List<Company> companyList = setupservice.getCompanyList(company);
			return new ResponseEntity<List<Company>>(companyList, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Error catched", e);
			return new ResponseEntity<>(new GoAuditsException("Something went wrong"), HttpStatus.EXPECTATION_FAILED);
		}
	}

	@RequestMapping(value = "/location/list", method = RequestMethod.POST)
	public ResponseEntity<?> getlocationsBasedeOnCompany(@RequestBody Location location,
			@RequestHeader(name = "Authorization") String token) {
		try {
			if (token != null && token != "" && !token.isEmpty()) {
				token = token.replace("Bearer ", "");
				String guid = Utils.getGuid(token);
				String uid = Utils.getUid(token);
				location.setGuid(guid);
				location.setUid(uid);
			}
			List<Location> locationList = setupservice.getLocationsBasedonCompany(location);
			return new ResponseEntity<List<Location>>(locationList, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Error catched", e);
			return new ResponseEntity<>(new GoAuditsException("Something went wrong"), HttpStatus.EXPECTATION_FAILED);
		}

	}

	@RequestMapping(value = "/location/listv2", method = RequestMethod.POST)
	public ResponseEntity<?> getlocationsBasedeOnCompanyV2(@RequestBody Location location,
			@RequestHeader(name = "Authorization") String token) {
		try {
			if (token != null && token != "" && !token.isEmpty()) {
				token = token.replace("Bearer ", "");
				String guid = Utils.getGuid(token);
				String uid = Utils.getUid(token);
				location.setGuid(guid);
				location.setUid(uid);
			}
			List<Location> locationList = setupservice.getLocationsBasedonCompanyv2(location);
			return new ResponseEntity<List<Location>>(locationList, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Error catched", e);
			return new ResponseEntity<>(new GoAuditsException("Something went wrong"), HttpStatus.EXPECTATION_FAILED);
		}
	}

	@RequestMapping(value = "/auditname/list", method = RequestMethod.POST)
	public ResponseEntity<?> getAuditNamesByCompany(@RequestBody AuditName auditname,
			@RequestHeader(name = "Authorization") String token) {
		try {
			if (token != null && token != "" && !token.isEmpty()) {
				token = token.replace("Bearer ", "");
				String guid = Utils.getGuid(token);
				String uid = Utils.getUid(token);
				auditname.setGuid(guid);
				auditname.setUid(uid);
			}
			List<AuditName> auditNameList = setupservice.getAuditNamesByCompany(auditname);
			return new ResponseEntity<List<AuditName>>(auditNameList, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Error catched", e);
			return new ResponseEntity<>(new GoAuditsException("Something went wrong"), HttpStatus.EXPECTATION_FAILED);
		}
	}

	@RequestMapping(value = "/company/add", method = RequestMethod.POST)
	public ResponseEntity<?> addCompany(@RequestBody Company company,
			@RequestHeader(name = "Authorization") String token) {
		try {
			if (token != null && token != "" && !token.isEmpty()) {
				token = token.replace("Bearer ", "");
				String guid = Utils.getGuid(token);
				String uid = Utils.getUid(token);
				company.setGuid(guid);
				company.setUid(uid);
			}

			if (setupservice.isCompanyExists(company)) {
				return new ResponseEntity<>(
						new GoAuditsException(
								"This company name is already in use, Please enter a different company name"),
						HttpStatus.CONFLICT);
			} else {

				Company comp = setupservice.addCompany(company);
				Location location = new Location();
				location.setGuid(company.getGuid());
				location.setUid(company.getUid());
				location.setClient_id(comp.getClient_id());
				location.setActive(true);
				location.setStore_name("My Site");
				setupservice.addLocation(location);
				List<Company> companyList = new ArrayList<Company>();
				company.setClient_id(comp.getClient_id());
				company.setLogo(comp.getLogo());
				company.setLast_modified(comp.getLast_modified());
				companyList.add(company);
				return new ResponseEntity<List<Company>>(companyList, HttpStatus.CREATED);

			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Error catched", e);
//			System.out.println("Something went wrong" );
			return new ResponseEntity<>(new GoAuditsException("Something went wrong"), HttpStatus.EXPECTATION_FAILED);
		}
	}

	@RequestMapping(value = "/company/update", method = RequestMethod.PUT)
	public ResponseEntity<?> updateCompany(@RequestBody Company company,
			@RequestHeader(name = "Authorization") String token) {

		try {
			if (token != null && token != "" && !token.isEmpty()) {
				token = token.replace("Bearer ", "");
				String guid = Utils.getGuid(token);
				String uid = Utils.getUid(token);
				company.setGuid(guid);
				company.setUid(uid);
			}
			if (!setupservice.isCompanyExistInDB(company)) {
				try {
					Company cmpy = setupservice.updateCompany(company);
					List<Company> companyList = new ArrayList<Company>();
					company.setLast_modified(cmpy.getLast_modified());
					companyList.add(company);
					return new ResponseEntity<List<Company>>(companyList, HttpStatus.OK);
				} catch (Exception e) {
					return new ResponseEntity<>(new GoAuditsException("Something went wrong"),
							HttpStatus.EXPECTATION_FAILED);
				}
			}
			return new ResponseEntity<>(
					new GoAuditsException("This company name is already in use, Please enter a different company name"),
					HttpStatus.CONFLICT);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Error catched", e);
			return new ResponseEntity<>(new GoAuditsException("Something went wrong"), HttpStatus.EXPECTATION_FAILED);
		}
	}

	@RequestMapping(value = "/getcompanycloneflag/{guid}", method = RequestMethod.GET)
	public ResponseEntity<?> getCompanycloneFlag(@PathVariable("guid") String guid,
			@RequestHeader(name = "Authorization") String token) {
		try {
			if (token != null && token != "" && !token.isEmpty()) {
				token = token.replace("Bearer ", "");
				String userguid = Utils.getGuid(token);
				guid = userguid;
			}
			String flag = setupservice.getCompanyCloneFlag(guid);
			Company cmp = new Company();
			cmp.setCompany_clone(Boolean.parseBoolean(flag));
			List<Company> companyList = new ArrayList<Company>();
			companyList.add(cmp);
			return new ResponseEntity<List<Company>>(companyList, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Error catched", e);
			return new ResponseEntity<>(new GoAuditsException("Something went wrong"), HttpStatus.EXPECTATION_FAILED);
		}
	}

	@RequestMapping(value = "/clonecompany", method = RequestMethod.POST)
	public ResponseEntity<?> cloneCompanies(@RequestBody PreTemplates PreTemplates,
			@RequestHeader(name = "Authorization") String token) {

		try {
			if (token != null && token != "" && !token.isEmpty()) {
				token = token.replace("Bearer ", "");
				String guid = Utils.getGuid(token);
				String uid = Utils.getUid(token);
				PreTemplates.setGuid(guid);
				PreTemplates.setUid(uid);
			}

			String valid[] = setupservice.checkCompanydata(PreTemplates).split("---@%");
			if (Integer.parseInt(valid[0]) > 0) {
				return new ResponseEntity<>(new GoAuditsException(valid[1]), HttpStatus.CONFLICT);
			} else {
				setupservice.cloneCompanies(PreTemplates);
				List<PreTemplates> preList = new ArrayList<PreTemplates>();

				preList.add(PreTemplates);
				return new ResponseEntity<List<PreTemplates>>(preList, HttpStatus.CREATED);
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Error catched", e);
			return new ResponseEntity<>(new GoAuditsException("Something went wrong"), HttpStatus.EXPECTATION_FAILED);
		}
	}

	@RequestMapping(value = "/company/assignees", method = RequestMethod.POST)
	public ResponseEntity<?> getCompanyAssignees(@RequestBody Company company,
			@RequestHeader(name = "Authorization") String token) {

		try {
			if (token != null && token != "" && !token.isEmpty()) {
				token = token.replace("Bearer ", "");
				String guid = Utils.getGuid(token);
				String uid = Utils.getUid(token);
				company.setGuid(guid);
				company.setUid(uid);
			}
			List<ActionPlanAssignee> assigneeList = setupservice.getCompanyAssigneeList(company);
			return new ResponseEntity<List<ActionPlanAssignee>>(assigneeList, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Error catched", e);
			return new ResponseEntity<>(new GoAuditsException("Something went wrong"), HttpStatus.EXPECTATION_FAILED);
		}
	}

	@RequestMapping(value = "/location/assignees", method = RequestMethod.POST)
	public ResponseEntity<?> getLocationAssignees(@RequestBody Location location,
			@RequestHeader(name = "Authorization") String token) {

		try {
			if (token != null && token != "" && !token.isEmpty()) {
				token = token.replace("Bearer ", "");
				String guid = Utils.getGuid(token);
				String uid = Utils.getUid(token);
				location.setGuid(guid);
				location.setUid(uid);
			}
			List<ActionPlanAssignee> assigneeList = setupservice.getLocationAssigneeList(location);
			return new ResponseEntity<List<ActionPlanAssignee>>(assigneeList, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Error catched", e);
			return new ResponseEntity<>(new GoAuditsException("Something went wrong"), HttpStatus.EXPECTATION_FAILED);
		}
	}

	@RequestMapping(value = "/auditname/assignees", method = RequestMethod.POST)
	public ResponseEntity<?> getAuditnameAssignees(@RequestBody AuditName auditname,
			@RequestHeader(name = "Authorization") String token) {
		try {
			if (token != null && token != "" && !token.isEmpty()) {
				token = token.replace("Bearer ", "");
				String guid = Utils.getGuid(token);
				String uid = Utils.getUid(token);
				auditname.setGuid(guid);
				auditname.setUid(uid);
			}
			List<ActionPlanAssignee> assigneeList = setupservice.getAuditNameAssigneeList(auditname);
			return new ResponseEntity<List<ActionPlanAssignee>>(assigneeList, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Error catched", e);
			return new ResponseEntity<>(new GoAuditsException("Something went wrong"), HttpStatus.EXPECTATION_FAILED);
		}
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
	public ResponseEntity<?> addAuditType(@RequestBody Company company,
			@RequestHeader(name = "Authorization") String token) {
		try {
			if (token != null && token != "" && !token.isEmpty()) {
				token = token.replace("Bearer ", "");
				String guid = Utils.getGuid(token);
				String uid = Utils.getUid(token);
				company.setGuid(guid);
				company.setUid(uid);
			}
			setupservice.companyReOrder(company);
			List<Company> companyList = new ArrayList<Company>();
			companyList.add(company);
			return new ResponseEntity<List<Company>>(companyList, HttpStatus.CREATED);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Error catched", e);
			return new ResponseEntity<>(new GoAuditsException("Something went wrong"), HttpStatus.EXPECTATION_FAILED);
		}
	}

	@RequestMapping(value = "/location/add", method = RequestMethod.POST)
	public ResponseEntity<?> addLocation(@RequestBody Location location,
			@RequestHeader(name = "Authorization") String token) {
		try {
			if (token != null && token != "" && !token.isEmpty()) {
				token = token.replace("Bearer ", "");
				String guid = Utils.getGuid(token);
				String uid = Utils.getUid(token);
				location.setGuid(guid);
				location.setUid(uid);
			}
			if (setupservice.isLocationExist(location)) {
				return new ResponseEntity<>(
						new GoAuditsException(
								"This location name is already in use, Please enter a different location name"),
						HttpStatus.CONFLICT);
			} else {
				try {
					String store_id = setupservice.addLocation(location);
					List<Location> locationList = new ArrayList<Location>();
					location.setStore_id(store_id);
					locationList.add(location);
					return new ResponseEntity<List<Location>>(locationList, HttpStatus.CREATED);
				} catch (Exception e) {
					log.error("Error catched", e);
					return new ResponseEntity<>(new GoAuditsException("Something went wrong"),
							HttpStatus.EXPECTATION_FAILED);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Error catched", e);
			return new ResponseEntity<>(new GoAuditsException("Something went wrong"), HttpStatus.EXPECTATION_FAILED);
		}
	}

	@RequestMapping(value = "/location/update", method = RequestMethod.PUT)
	public ResponseEntity<?> updateLocation(@RequestBody Location location,
			@RequestHeader(name = "Authorization") String token) {

		try {
			if (token != null && token != "" && !token.isEmpty()) {
				token = token.replace("Bearer ", "");
				String guid = Utils.getGuid(token);
				String uid = Utils.getUid(token);
				location.setGuid(guid);
				location.setUid(uid);
			}
			if (!setupservice.isLocationExistInDB(location)) {
				try {
					String store_id = setupservice.updateLocation(location);
					List<Location> locationList = new ArrayList<Location>();
					location.setStore_id(store_id);
					locationList.add(location);
					return new ResponseEntity<List<Location>>(locationList, HttpStatus.OK);
				} catch (Exception e) {
					log.error("Error catched", e);
					return new ResponseEntity<>(new GoAuditsException("Something went wrong"),
							HttpStatus.EXPECTATION_FAILED);
				}
			}
			return new ResponseEntity<>(
					new GoAuditsException(
							"This location name is already in use, Please enter a different location name"),
					HttpStatus.CONFLICT);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Error catched", e);
			return new ResponseEntity<>(new GoAuditsException("Something went wrong"), HttpStatus.EXPECTATION_FAILED);
		}
	}

	@RequestMapping(value = "getgpsflag/{guid}", method = RequestMethod.GET)
	public ResponseEntity<?> Locflag(@PathVariable("guid") String guid, Location location,
			@RequestHeader(name = "Authorization") String token) {

		try {
			if (token != null && token != "" && !token.isEmpty()) {
				token = token.replace("Bearer ", "");
				String userguid = Utils.getGuid(token);
				guid = userguid;
			}
			boolean gpsFilterFlag = setupservice.getGpsFlag(guid);

			List<Location> locationList = new ArrayList<Location>();
			location.setGps_location_filter_enabled(gpsFilterFlag);
			locationList.add(location);
			return new ResponseEntity<List<Location>>(locationList, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Error catched", e);
			return new ResponseEntity<>(new GoAuditsException("Something went wrong"), HttpStatus.EXPECTATION_FAILED);
		}
	}

	@RequestMapping(value = "/location/tags/{guid}/{uid}/{clientid}/{store_id}", method = RequestMethod.GET)
	public ResponseEntity<?> getTagsBasedonLocation(@PathVariable("guid") String guid, @PathVariable("uid") String uid,
			@PathVariable("clientid") String clientid, @PathVariable("store_id") String store_id,
			@RequestHeader(name = "Authorization") String token) {
		try {
			if (token != null && token != "" && !token.isEmpty()) {
				token = token.replace("Bearer ", "");
				String userguid = Utils.getGuid(token);
				String useruid = Utils.getUid(token);
				uid = useruid;
				guid = userguid;

			}
			List<LocationTags> actionlist = setupservice.getTagsBasedonLocation(guid, uid, clientid, store_id);
			return new ResponseEntity<List<LocationTags>>(actionlist, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Error catched", e);
			return new ResponseEntity<>(new GoAuditsException("Something went wrong"), HttpStatus.EXPECTATION_FAILED);
		}
	}

	@RequestMapping(value = "gettagslist/{guid}/{type}", method = RequestMethod.GET)
	public ResponseEntity<?> getagList(@PathVariable("guid") String guid, @PathVariable("type") int type,
			@RequestHeader(name = "Authorization") String token) {

		try {
			if (token != null && token != "" && !token.isEmpty()) {
				token = token.replace("Bearer ", "");
				String userguid = Utils.getGuid(token);
				guid = userguid;

			}
			List<LocationTags> tagsList = setupservice.getTagsList(guid, type);
			return new ResponseEntity<List<LocationTags>>(tagsList, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Error catched", e);
			return new ResponseEntity<>(new GoAuditsException("Something went wrong"), HttpStatus.EXPECTATION_FAILED);
		}
	}

	@RequestMapping(value = "/auditname/add", method = RequestMethod.POST)
	public ResponseEntity<?> addAuditType(@RequestBody AuditName auditname,
			@RequestHeader(name = "Authorization") String token) {
		try {
			if (token != null && token != "" && !token.isEmpty()) {
				token = token.replace("Bearer ", "");
				String guid = Utils.getGuid(token);
				String uid = Utils.getUid(token);
				auditname.setGuid(guid);
				auditname.setUid(uid);
			}
			if (setupservice.isAuditNameExist(auditname)) {
				return new ResponseEntity<>(
						new GoAuditsException(
								"This checklist name is already in use, Please enter a different checklist name "),
						HttpStatus.CONFLICT);
			}
			AuditName AudName = setupservice.addAuditName(auditname);
			auditname.setAudit_type_id(AudName.getAudit_type_id());
			auditname.setLogo(AudName.getLogo());
			auditname.setLast_modified(AudName.getLast_modified());
			List<AuditName> auditNameList = new ArrayList<AuditName>();
			auditNameList.add(auditname);
			return new ResponseEntity<List<AuditName>>(auditNameList, HttpStatus.CREATED);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Error catched", e);
			return new ResponseEntity<>(new GoAuditsException("Something went wrong"), HttpStatus.EXPECTATION_FAILED);
		}
	}

	@RequestMapping(value = "/auditname/order", method = RequestMethod.POST)
	public ResponseEntity<?> reOrderAuditName(@RequestBody AuditName auditname,
			@RequestHeader(name = "Authorization") String token) {
		try {
			if (token != null && token != "" && !token.isEmpty()) {
				token = token.replace("Bearer ", "");
				String guid = Utils.getGuid(token);
				String uid = Utils.getUid(token);
				auditname.setGuid(guid);
				auditname.setUid(uid);
			}
			setupservice.addAuditNameOrder(auditname);
			List<AuditName> auditNameList = new ArrayList<AuditName>();
			auditNameList.add(auditname);
			return new ResponseEntity<List<AuditName>>(auditNameList, HttpStatus.CREATED);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Error catched", e);
			return new ResponseEntity<>(new GoAuditsException("Something went wrong"), HttpStatus.EXPECTATION_FAILED);
		}
	}

	@RequestMapping(value = "/auditname/update", method = RequestMethod.PUT)
	public ResponseEntity<?> updateAuditName(@RequestBody AuditName auditname,
			@RequestHeader(name = "Authorization") String token) {
		try {
			if (token != null && token != "" && !token.isEmpty()) {
				token = token.replace("Bearer ", "");
				String guid = Utils.getGuid(token);
				String uid = Utils.getUid(token);
				auditname.setGuid(guid);
				auditname.setUid(uid);
			}
			if (!setupservice.isAuditNameExistInDB(auditname)) {
				try {
					AuditName AudName = setupservice.updateAuditName(auditname);
					auditname.setAudit_type_id(AudName.getAudit_type_id());
					auditname.setLast_modified(AudName.getLast_modified());
					List<AuditName> auditNameList = new ArrayList<AuditName>();
					auditNameList.add(auditname);
					return new ResponseEntity<List<AuditName>>(auditNameList, HttpStatus.OK);
				} catch (Exception e) {
//				System.out.println("Something went wrong" );
					return new ResponseEntity<>(new GoAuditsException("Something went wrong"),
							HttpStatus.EXPECTATION_FAILED);
				}
			}
			return new ResponseEntity<>(
					new GoAuditsException(
							"This checklist name is already in use, Please enter a different checklist name"),
					HttpStatus.CONFLICT);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Error catched", e);
			return new ResponseEntity<>(new GoAuditsException("Something went wrong"), HttpStatus.EXPECTATION_FAILED);
		}
	}

	@RequestMapping(value = "/cloneauditname", method = RequestMethod.POST)
	public ResponseEntity<?> cloneAuditname(@RequestBody PreTemplates PreTemplates,
			@RequestHeader(name = "Authorization") String token) {
		try {
			if (token != null && token != "" && !token.isEmpty()) {
				token = token.replace("Bearer ", "");
				String guid = Utils.getGuid(token);
				String uid = Utils.getUid(token);
				PreTemplates.setGuid(guid);
				PreTemplates.setUid(uid);
			}
			String valid[] = setupservice.PreTemplates(PreTemplates).split("---@%");
			if (PreTemplates.isValidate() && Integer.parseInt(valid[0]) > 0) {

				return new ResponseEntity<>(new GoAuditsException(valid[1]), HttpStatus.CONFLICT);
			} else {
				setupservice.cloneAuditName(PreTemplates);
				List<PreTemplates> preList = new ArrayList<PreTemplates>();

				preList.add(PreTemplates);
				return new ResponseEntity<List<PreTemplates>>(preList, HttpStatus.CREATED);
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Error catched", e);
			return new ResponseEntity<>(new GoAuditsException("Something went wrong"), HttpStatus.EXPECTATION_FAILED);
		}
	}

	@RequestMapping(value = "/getscorerange/{guid}/{client_id}/{audit_type_id}", method = RequestMethod.GET)
	public ResponseEntity<?> getScoreRange(@PathVariable String guid, @PathVariable String client_id,
			@PathVariable String audit_type_id, @RequestHeader(name = "Authorization") String token) {
		try {
			if (token != null && token != "" && !token.isEmpty()) {
				token = token.replace("Bearer ", "");
				String userguid = Utils.getGuid(token);
				guid = userguid;

			}
			List<ScoreRange> scorerangeList = setupservice.getScoreRange(guid, client_id, audit_type_id);
			return new ResponseEntity<List<ScoreRange>>(scorerangeList, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Error catched", e);
			return new ResponseEntity<>(new GoAuditsException("Something went wrong"), HttpStatus.EXPECTATION_FAILED);
		}
	}

	@RequestMapping(value = "/addscorerange", method = RequestMethod.POST)
	public ResponseEntity<?> getScoreRange(@RequestBody List<ScoreRange> scorerange,
			@RequestHeader(name = "Authorization") String token) {
		try {
			if (token != null && token != "" && !token.isEmpty()) {
				token = token.replace("Bearer ", "");
				String guid = Utils.getGuid(token);
				String uid = Utils.getUid(token);
				scorerange.get(0).setGuid(guid);
				scorerange.get(0).setUid(uid);
			}
			setupservice.addScoreRange(scorerange);
			return new ResponseEntity<List<ScoreRange>>(scorerange, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Error catched", e);
			return new ResponseEntity<>(new GoAuditsException("Something went wrong"), HttpStatus.EXPECTATION_FAILED);
		}
	}

	@RequestMapping(value = "/emailtemplate/list", method = RequestMethod.POST)
	public ResponseEntity<?> getEmailTemplates(@RequestBody EmailTemplate emailtemplate,
			@RequestHeader(name = "Authorization") String token) {
		try {
			if (token != null && token != "" && !token.isEmpty()) {
				token = token.replace("Bearer ", "");
				String guid = Utils.getGuid(token);
				String uid = Utils.getUid(token);
				emailtemplate.setGuid(guid);
				emailtemplate.setUid(uid);
			}
			List<EmailTemplate> emailTemplateList = setupservice.getEmailTemplates(emailtemplate);

			return new ResponseEntity<List<EmailTemplate>>(emailTemplateList, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Error catched", e);
			return new ResponseEntity<>(new GoAuditsException("Something went wrong"), HttpStatus.EXPECTATION_FAILED);
		}
	}

	@RequestMapping(value = "/emailtemplate/update", method = RequestMethod.POST)
	public ResponseEntity<?> updateEmailTemplate(@RequestBody EmailTemplate emailTemplate,
			@RequestHeader(name = "Authorization") String token) {
		try {
			if (token != null && token != "" && !token.isEmpty()) {
				token = token.replace("Bearer ", "");
				String guid = Utils.getGuid(token);
				String uid = Utils.getUid(token);
				emailTemplate.setGuid(guid);
				emailTemplate.setUid(uid);
			}
			setupservice.updateEmailTemplate(emailTemplate);
			List<EmailTemplate> emailList = new ArrayList<EmailTemplate>();
			emailList.add(emailTemplate);
			return new ResponseEntity<List<EmailTemplate>>(emailList, HttpStatus.CREATED);
		} catch (Exception e) {
			log.error("Error catched", e);
			return new ResponseEntity<>(new GoAuditsException("Something went wrong"), HttpStatus.EXPECTATION_FAILED);

		}
	}

	@RequestMapping(value = "/report/list", method = RequestMethod.POST)
	public ResponseEntity<?> getAllReports(@RequestBody Report report,
			@RequestHeader(name = "Authorization") String token) {
		try {
			if (token != null && token != "" && !token.isEmpty()) {
				token = token.replace("Bearer ", "");
				String guid = Utils.getGuid(token);
				String uid = Utils.getUid(token);
				report.setGuid(guid);
				report.setUid(uid);
			}
			List<Report> reportList = setupservice.getReports(report);
			return new ResponseEntity<List<Report>>(reportList, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Error catched", e);
			return new ResponseEntity<>(new GoAuditsException("Something went wrong"), HttpStatus.EXPECTATION_FAILED);
		}
	}

	@RequestMapping(value = "/report/update", method = RequestMethod.PUT)
	public ResponseEntity<?> updateReport(@RequestBody Report report,
			@RequestHeader(name = "Authorization") String token) {
		try {
			if (token != null && token != "" && !token.isEmpty()) {
				token = token.replace("Bearer ", "");
				String guid = Utils.getGuid(token);
				String uid = Utils.getUid(token);
				report.setGuid(guid);
				report.setUid(uid);
			}
			setupservice.updateReport(report);
			List<Report> reportList = new ArrayList<Report>();
			reportList.add(report);
			return new ResponseEntity<List<Report>>(reportList, HttpStatus.CREATED);
		} catch (Exception e) {
			log.error("Error catched", e);
			return new ResponseEntity<>(new GoAuditsException("Something went wrong"), HttpStatus.EXPECTATION_FAILED);
		}
	}

	@RequestMapping(value = "/reporttemplate/list/{guid}", method = RequestMethod.GET)
	public ResponseEntity<?> getReporttemplates(@PathVariable("guid") String guid,
			@RequestHeader(name = "Authorization") String token) {
		try {
			if (token != null && token != "" && !token.isEmpty()) {
				token = token.replace("Bearer ", "");
				guid = Utils.getGuid(token);

			}
			List<Report> reportList = setupservice.getReporttemplates(guid);
			return new ResponseEntity<List<Report>>(reportList, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Error catched", e);
			return new ResponseEntity<>(new GoAuditsException("Something went wrong"), HttpStatus.EXPECTATION_FAILED);
		}
	}

	@RequestMapping(value = "/templateslist", method = RequestMethod.GET)
	public ResponseEntity<?> getPreexistingtemplates() {
		try {

			List<Company> templateslist = setupservice.getPreexistingTemplates();
			return new ResponseEntity<List<Company>>(templateslist, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Error catched", e);
			return new ResponseEntity<>(new GoAuditsException("Something went wrong"), HttpStatus.EXPECTATION_FAILED);
		}
	}

	@RequestMapping(value = "/preclient/{client_id}", method = RequestMethod.GET)
	public ResponseEntity<?> getPreAuditnames(@PathVariable int client_id) {
		try {

			List<AuditName> auditnamelist = setupservice.getPreAuditnames(client_id);
			return new ResponseEntity<List<AuditName>>(auditnamelist, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Error catched", e);
			return new ResponseEntity<>(new GoAuditsException("Something went wrong"), HttpStatus.EXPECTATION_FAILED);
		}
	}

	@RequestMapping(value = "/preclient/all", method = RequestMethod.GET)
	public ResponseEntity<?> getPreAuditnamesAll() {
		try {
			List<AuditName> auditnamelist = setupservice.getAllPreAuditnames();
			return new ResponseEntity<List<AuditName>>(auditnamelist, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Error catched", e);
			return new ResponseEntity<>(new GoAuditsException("Something went wrong"), HttpStatus.EXPECTATION_FAILED);
		}
	}

	@RequestMapping(value = "/prequestion/count/{client_id}/{audit_type_id}", method = RequestMethod.GET)
	public ResponseEntity<?> getPreQuestionscount(@PathVariable int client_id, @PathVariable int audit_type_id) {
		try {
			int count = setupservice.getPretempletQuestionscount(client_id, audit_type_id);
			Company cmp = new Company();
			cmp.setCount(count);
			List<Company> companyList = new ArrayList<Company>();
			companyList.add(cmp);
			return new ResponseEntity<List<Company>>(companyList, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Error catched", e);
			return new ResponseEntity<>(new GoAuditsException("Something went wrong"), HttpStatus.EXPECTATION_FAILED);
		}
	}

	@RequestMapping(value = "/prequestion/{client_id}/{audit_type_id}", method = RequestMethod.GET)
	public ResponseEntity<?> getPreQuestions(@PathVariable int client_id, @PathVariable int audit_type_id) {
		try {
			Section section = new Section();
			section.setClient_id(client_id);
			section.setAudit_type_id(audit_type_id);
			List<Section> sectionlist = setupservice.getPretempletQuestions(section);
			return new ResponseEntity<List<Section>>(sectionlist, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Error catched", e);
			return new ResponseEntity<>(new GoAuditsException("Something went wrong"), HttpStatus.EXPECTATION_FAILED);
		}
	}

	@RequestMapping(value = "/pretemplate/add", method = RequestMethod.POST)
	public ResponseEntity<?> addpreTemplate(@RequestBody PreTemplates PreTemplates,
			@RequestHeader(name = "Authorization") String token) {
		try {
			if (token != null && token != "" && !token.isEmpty()) {
				token = token.replace("Bearer ", "");
				String guid = Utils.getGuid(token);
				String uid = Utils.getUid(token);
				PreTemplates.setGuid(guid);
				PreTemplates.setUid(uid);
			}

			String valid[] = setupservice.PreTemplates(PreTemplates).split("---@%");
			if (Integer.parseInt(valid[0]) > 0) {
				return new ResponseEntity<>(
						new GoAuditsException(
								"This template name is already in use, Please enter a different template name "),
						HttpStatus.CONFLICT);
			}

			setupservice.createPreTemplate(PreTemplates);
			List<PreTemplates> templatesList = new ArrayList<PreTemplates>();
			templatesList.add(PreTemplates);
			return new ResponseEntity<List<PreTemplates>>(templatesList, HttpStatus.CREATED);
		} catch (Exception e) {
			log.error("Error catched", e);
			return new ResponseEntity<>(new GoAuditsException("Something went wrong"), HttpStatus.EXPECTATION_FAILED);
		}
	}

	@RequestMapping(value = "/guideddata", method = RequestMethod.GET)
	public ResponseEntity<?> guidedSetupData() {
		try {
			List<GuidedSetup> dataList = setupservice.getGuidedSetupdata();
			return new ResponseEntity<List<GuidedSetup>>(dataList, HttpStatus.OK);
		} catch (Exception e) {
			log.error("Error catched", e);
			return new ResponseEntity<>(new GoAuditsException("Something went wrong"), HttpStatus.EXPECTATION_FAILED);
		}
	}

	@RequestMapping(value = "/cmpcount/{guid}", method = RequestMethod.GET)
	public ResponseEntity<?> CompanyCount(@PathVariable("guid") String guid,
			@RequestHeader(name = "Authorization") String token) {
		try {
			if (token != null && token != "" && !token.isEmpty()) {
				token = token.replace("Bearer ", "");
				guid = Utils.getGuid(token);

			}
			int count = setupservice.getCompanyCount(guid);
			List<Integer> list = new ArrayList<Integer>();
			list.add(count);
			return new ResponseEntity<List<Integer>>(list, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Error catched", e);
			return new ResponseEntity<>(new GoAuditsException("Something went wrong"), HttpStatus.EXPECTATION_FAILED);
		}
	}

	@RequestMapping(value = "/createdguided", method = RequestMethod.POST)
	public ResponseEntity<?> createguidedSetup(@RequestBody GuidedSetup gudsetp, Company cmp,
			@RequestHeader(name = "Authorization") String token) {
		try {
			if (token != null && token != "" && !token.isEmpty()) {
				token = token.replace("Bearer ", "");
				String guid = Utils.getGuid(token);
				String uid = Utils.getUid(token);
				gudsetp.setGuid(guid);
				gudsetp.setUid(uid);
			}

			cmp.setGuid(gudsetp.getGuid());
			cmp.setUid(gudsetp.getUid());
			cmp.setClient_name(gudsetp.getCompany_name());
			cmp.setLogo(gudsetp.getLogo());
			cmp.setActive(true);
			Company newcmp = setupservice.addCompany(cmp);
			Location location = new Location();
			location.setGuid(gudsetp.getGuid());
			location.setUid(gudsetp.getUid());
			location.setClient_id(newcmp.getClient_id());
			location.setActive(true);
			location.setStore_name("My Site");
			setupservice.addLocation(location);
			setupservice.createGuided(gudsetp);
			List<GuidedSetup> arr = new ArrayList<GuidedSetup>();
			arr.add(gudsetp);
			return new ResponseEntity<List<GuidedSetup>>(arr, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Error catched", e);
			return new ResponseEntity<>(new GoAuditsException("Something went wrong"), HttpStatus.EXPECTATION_FAILED);
		}
	}

	@RequestMapping(value = "/editflag/{guid}", method = RequestMethod.GET)
	public ResponseEntity<?> editAuditFlag(@PathVariable("guid") String guid,
			@RequestHeader(name = "Authorization") String token) {
		try {
			if (token != null && token != "" && !token.isEmpty()) {
				token = token.replace("Bearer ", "");
				guid = Utils.getGuid(token);
			}
			boolean flag = setupservice.getEditFlag(guid);
			List<Boolean> list = new ArrayList<Boolean>();
			list.add(flag);
			return new ResponseEntity<List<Boolean>>(list, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Error catched", e);
			return new ResponseEntity<>(new GoAuditsException("Something went wrong"), HttpStatus.EXPECTATION_FAILED);
		}
	}

	@RequestMapping(value = "/completeflag/{guid}", method = RequestMethod.GET)
	public ResponseEntity<?> completeFlag(@PathVariable("guid") String guid,
			@RequestHeader(name = "Authorization") String token) {
		try {
			if (token != null && token != "" && !token.isEmpty()) {
				token = token.replace("Bearer ", "");
				guid = Utils.getGuid(token);
			}
			boolean flag = setupservice.getCompletedFlag(guid);
			List<Boolean> list = new ArrayList<Boolean>();
			list.add(flag);
			return new ResponseEntity<List<Boolean>>(list, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Error catched", e);
			return new ResponseEntity<>(new GoAuditsException("Something went wrong"), HttpStatus.EXPECTATION_FAILED);
		}
	}

	@RequestMapping(value = "/comlocation/list", method = RequestMethod.POST)
	public ResponseEntity<?> getlocationsBasedeOnCompanys(@RequestBody Location location,
			@RequestHeader(name = "Authorization") String token) {
		try {
			if (token != null && token != "" && !token.isEmpty()) {
				token = token.replace("Bearer ", "");
				String guid = Utils.getGuid(token);
				String uid = Utils.getUid(token);
				location.setGuid(guid);
				location.setUid(uid);
			}
			List<Location> locationList = setupservice.getLocationsBasedonCompanys(location);
			return new ResponseEntity<List<Location>>(locationList, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Error catched", e);
			return new ResponseEntity<>(new GoAuditsException("Something went wrong"), HttpStatus.EXPECTATION_FAILED);
		}
	}

	@RequestMapping(value = "/comauditname/list", method = RequestMethod.POST)
	public ResponseEntity<?> getAuditNamesByCompanys(@RequestBody AuditName auditname,
			@RequestHeader(name = "Authorization") String token) {
		try {
			if (token != null && token != "" && !token.isEmpty()) {
				token = token.replace("Bearer ", "");
				String guid = Utils.getGuid(token);
				String uid = Utils.getUid(token);
				auditname.setGuid(guid);
				auditname.setUid(uid);
			}
			List<AuditName> auditNameList = setupservice.getAuditNamesByCompanys(auditname);
			return new ResponseEntity<List<AuditName>>(auditNameList, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Error catched", e);
			return new ResponseEntity<>(new GoAuditsException("Something went wrong"), HttpStatus.EXPECTATION_FAILED);
		}
	}

	@RequestMapping(value = "/menuitems/list", method = RequestMethod.POST)
	public ResponseEntity<?> getAuditNamesByCompanys(@RequestHeader(name = "Authorization") String token) {
		try {
			String guid = "";
			if (token != null && token != "" && !token.isEmpty()) {
				token = token.replace("Bearer ", "");
				guid = Utils.getGuid(token);
//				String uid = Utils.getUid(token);
			}
			List<Menu> menuList = setupservice.getMenulist(guid);
			return new ResponseEntity<List<Menu>>(menuList, HttpStatus.OK);

		} catch (Exception e) {
			e.printStackTrace();
			log.error("Error catched", e);
			return new ResponseEntity<>(new GoAuditsException("Something went wrong"), HttpStatus.EXPECTATION_FAILED);
		}
	}

	@RequestMapping(value = "/help/list/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getHelplist(@PathVariable("id") String id) {
		try {
			List<Help> menuList = setupservice.getHelplist(id);
			HttpHeaders headers = new HttpHeaders();
			headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
			headers.setContentType(MediaType.APPLICATION_JSON);
			headers.setBearerAuth("dG9rOmRkMjg2MjA3X2ZlMzJfNDUwY185NzQ2XzY1NjBiNDhkNjVmMzoxOjA=");
			RestTemplate restTemplate = new RestTemplate();
			HttpEntity<?> requestEntity = new HttpEntity<>(null, headers);
			for (Help p : menuList) {
				final String baseUrl = "https://api.intercom.io/articles/" + p.getApi_id();
				URI uri = new URI(baseUrl);
				ResponseEntity<String> result1 = restTemplate.exchange(uri, HttpMethod.GET, requestEntity,
						String.class);
				p.setBody(result1.getBody());
			}
			return new ResponseEntity<List<Help>>(menuList, HttpStatus.OK);

		} catch (Exception e) {
			e.printStackTrace();
			log.error("Error catched", e);
			return new ResponseEntity<>(new GoAuditsException("Something went wrong"), HttpStatus.EXPECTATION_FAILED);
		}
	}

	@RequestMapping(value = "/reportref/list", method = RequestMethod.POST)
	public ResponseEntity<?> getReportRef(@RequestBody Reportref Reportref,
			@RequestHeader(name = "Authorization") String token) {
		try {
			String guid = "";
			if (token != null && token != "" && !token.isEmpty()) {
				token = token.replace("Bearer ", "");
				guid = Utils.getGuid(token);
				Reportref.setGuid(guid);
//				String uid = Utils.getUid(token);
			}
			List<Reportref> repList = setupservice.getReportRef(Reportref);
			return new ResponseEntity<List<Reportref>>(repList, HttpStatus.OK);

		} catch (Exception e) {
			e.printStackTrace();
			log.error("Error catched", e);
			return new ResponseEntity<>(new GoAuditsException("Something went wrong"), HttpStatus.EXPECTATION_FAILED);
		}
	}

}