package com.goaudits.business.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.goaudits.business.entity.DemoAudits;
import com.goaudits.business.service.DemoServiceInterface;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/demo")
public class DemoApi {

	@Autowired
	DemoServiceInterface demoService;
	
	@GetMapping("/list")
	public ResponseEntity<List<DemoAudits>> getListOfAudits(){
		List<DemoAudits> auditsList = demoService.getDemoAuditsList();
		return new ResponseEntity<List<DemoAudits>>(auditsList, HttpStatus.OK);
	}
	
	
	@PostMapping("/getaudits")
	public ResponseEntity<?> getManageAuditsList(@RequestBody DemoAudits audits){
		List<DemoAudits> auditsList = demoService.getManageAuditsList(audits);
		return new ResponseEntity<List<DemoAudits>>(auditsList, HttpStatus.OK);
	}
	
}