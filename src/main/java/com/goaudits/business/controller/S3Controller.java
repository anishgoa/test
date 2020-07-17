package com.goaudits.business.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.goaudits.business.entity.S3;
import com.goaudits.business.service.S3Service;

@RestController
public class S3Controller {

	@Autowired
	private S3Service s3Service;

	@RequestMapping(value = "goaudits/quesimagesmigrate/{guid}", method = RequestMethod.GET)
	public ResponseEntity<?> getCloudinaryFlag(@PathVariable String guid) {

		int migrate = s3Service.Migrateimages(guid);
		return new ResponseEntity<Integer>(migrate, HttpStatus.ACCEPTED);
	}

	@RequestMapping(value = "goaudits/followupimagesmigrate", method = RequestMethod.GET)
	public ResponseEntity<?> geFollowupmigrate() {

		int migrate = s3Service.MigrateimagesFollowup();
		return new ResponseEntity<Integer>(migrate, HttpStatus.ACCEPTED);
	}

	@RequestMapping(value = "goaudits/auditimagesmigrate/{guid}", method = RequestMethod.GET)
	public ResponseEntity<?> gImagemigrate(@PathVariable String guid) {

		int migrate = s3Service.MigrateimagesAudit(guid);
		return new ResponseEntity<Integer>(migrate, HttpStatus.ACCEPTED);
	}

	@RequestMapping(value = "goaudits/auditreportmigrate", method = RequestMethod.GET)
	public ResponseEntity<?> auditReportmigrate() {

		int migrate = s3Service.MigrateAuditReport();
		return new ResponseEntity<Integer>(migrate, HttpStatus.ACCEPTED);
	}

	@RequestMapping(value = "goaudits/createfloder", method = RequestMethod.GET)
	public ResponseEntity<?> auditReportmigrate(@RequestBody S3 s3) {

		int migrate = s3Service.CreateFolder(s3);
		return new ResponseEntity<Integer>(migrate, HttpStatus.ACCEPTED);
	}

}
