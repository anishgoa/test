package com.goaudits.business.controller;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.goaudits.business.entity.Feature;
import com.goaudits.business.entity.Subscriptionentity;
import com.goaudits.business.service.SubscriptionService;

@RestController
@EnableAutoConfiguration
public class SubscriptionController {

	@Autowired
	private SubscriptionService subscriptionservice;

	@RequestMapping(value = "/getSubscriptions/{guid}", method = RequestMethod.GET)
	public ResponseEntity<?> getSubscriptions(@PathVariable("guid") String guid) {

		List<Subscriptionentity> sublist = subscriptionservice.getSubscriptionList(guid);
		return new ResponseEntity<List<Subscriptionentity>>(sublist, HttpStatus.OK);

	}

	@RequestMapping(value = "/getfeatures/{feature_id}", method = RequestMethod.GET)
	public ResponseEntity<?> getFeatures(@PathVariable("feature_id") int feature_id) {

		List<Feature> featurelist = subscriptionservice.getFeaturesList(feature_id);
		return new ResponseEntity<List<Feature>>(featurelist, HttpStatus.OK);
	}

	@RequestMapping(value = "/getauditnamecount", method = RequestMethod.GET)
	public ResponseEntity<?> getAuditnameCount(@PathVariable("guid") String guid) {
		int auditNameCount = subscriptionservice.getAuditNameCount(guid);
		List<Integer> countList=new ArrayList<Integer>();
		countList.add(auditNameCount);
		return new ResponseEntity<List<Integer>>(countList, HttpStatus.OK);
	}

	@RequestMapping(value = "/getcompanycount", method = RequestMethod.GET)
	public ResponseEntity<?> getCompanyCount(@PathVariable("guid") String guid) {
		int companyCount = subscriptionservice.getCompanyCount(guid);
		List<Integer> countList=new ArrayList<Integer>();
		countList.add(companyCount);
		return new ResponseEntity<List<Integer>>(countList, HttpStatus.OK);	}

	@RequestMapping(value = "/getlocationcount", method = RequestMethod.GET)
	public ResponseEntity<?> getLocationCount(@PathVariable("guid") String guid) {
		int LocationCount = subscriptionservice.getLocationCount(guid);
		List<Integer> countList=new ArrayList<Integer>();
		countList.add(LocationCount);
		return new ResponseEntity<List<Integer>>(countList, HttpStatus.OK);
	}

	@RequestMapping(value = "/getuserscount", method = RequestMethod.GET)
	public ResponseEntity<?> getUsersCount(@PathVariable("guid") String guid) {

		int UsersCount = subscriptionservice.getUsersCount(guid);
		List<Integer> countList=new ArrayList<Integer>();
		countList.add(UsersCount);
		return new ResponseEntity<List<Integer>>(countList, HttpStatus.OK);
	}

	@RequestMapping(value = "/getpaymentinfo", method = RequestMethod.GET)
	public ResponseEntity<?> getPaymentinfo() {
		List<LinkedHashMap> paymentinfo = subscriptionservice.getpaymentinfo();
		return new ResponseEntity<List<LinkedHashMap>>(paymentinfo, HttpStatus.OK);
	}
	
	

}
