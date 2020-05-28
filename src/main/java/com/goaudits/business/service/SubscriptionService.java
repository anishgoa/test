package com.goaudits.business.service;

import java.util.LinkedHashMap;
import java.util.List;

import com.goaudits.business.entity.Feature;
import com.goaudits.business.entity.Subscriptionentity;


public interface SubscriptionService {

	List<Subscriptionentity> getSubscriptionList(String guid);

	List<Feature> getFeaturesList( int feature_id);

	int getAuditNameCount(String attribute);

	int getCompanyCount(String attribute);

	int getLocationCount(String attribute);

	int getUsersCount(String attribute);

	List<LinkedHashMap> getpaymentinfo();

}
