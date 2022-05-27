package com.goaudits.business.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.goaudits.business.entity.DemoAudits;
import com.goaudits.business.service.DemoServiceInterface;
import com.goaudits.business.util.GoAuditsException;
import com.goaudits.business.util.Utils;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/demo")
public class DemoApi {

	private final Logger log = LogManager.getLogger(getClass().getName());
	
	@Autowired
	DemoServiceInterface demoService;

	@GetMapping("/list")
	public ResponseEntity<List<DemoAudits>> getListOfAudits() {
		List<DemoAudits> auditsList = demoService.getDemoAuditsList();
		return new ResponseEntity<List<DemoAudits>>(auditsList, HttpStatus.OK);
	}

	@PostMapping("/getaudits")
	public ResponseEntity<?> getManageAuditsList(@RequestBody DemoAudits audits,
			@RequestHeader(name = "Authorization") String token) {
		try {
			if (token != null && token != "" && !token.isEmpty()) {
				token = token.replace("Bearer ", "");
				String guid = Utils.getGuid(token);
				String uid = Utils.getUid(token);
				audits.setGuid(guid);
				audits.setUid(uid);
			}
			List<DemoAudits> auditsList = demoService.getManageAuditsList(audits);
			return new ResponseEntity<List<DemoAudits>>(auditsList, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Error catched", e);
			return new ResponseEntity<>(new GoAuditsException("Something went wrong"), HttpStatus.EXPECTATION_FAILED);
		}
	}

	@PostMapping("/getauditsv1")
	public ResponseEntity<?> getManageAuditsListv1(@RequestBody DemoAudits audits,
			@RequestHeader(name = "Authorization") String token) {
		try {
			if (token != null && token != "" && !token.isEmpty()) {
				token = token.replace("Bearer ", "");
				String guid = Utils.getGuid(token);
				String uid = Utils.getUid(token);
				audits.setGuid(guid);
				audits.setUid(uid);
			}
			List<DemoAudits> auditsList = demoService.getManageAuditsListv1(audits);
			return new ResponseEntity<List<DemoAudits>>(auditsList, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Error catched", e);
			return new ResponseEntity<>(new GoAuditsException("Something went wrong"), HttpStatus.EXPECTATION_FAILED);
		}
	}

}