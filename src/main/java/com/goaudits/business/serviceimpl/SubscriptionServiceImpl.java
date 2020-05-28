package com.goaudits.business.serviceimpl;

import java.util.LinkedHashMap;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.goaudits.business.entity.Feature;
import com.goaudits.business.entity.Subscriptionentity;
import com.goaudits.business.mapper.SubscriptionMapper;
import com.goaudits.business.service.SubscriptionService;

@Service
public class SubscriptionServiceImpl implements SubscriptionService{
	@Autowired
	private SubscriptionMapper subscriptionmapper;

	@Override
	public List<Subscriptionentity> getSubscriptionList(String guid) {
		
		return subscriptionmapper.getSubscriptionList(guid);
	}

	@Override
	public List<Feature> getFeaturesList(int feature_id) {
		
		return subscriptionmapper.getFeatureList(feature_id);
	}

	@Override
	public int getAuditNameCount(String guid) {
		
		return subscriptionmapper.getAuditNameCount(guid);
	}

	@Override
	public int getCompanyCount(String guid) {
	
		return subscriptionmapper.getCompanyCount(guid);
	}

	@Override
	public int getLocationCount(String guid) {
		return subscriptionmapper.getLocationCount(guid);
	}

	@Override
	public int getUsersCount(String guid) {
		
		return subscriptionmapper.getUsersCount(guid);
	}

	@Override
	public List<LinkedHashMap> getpaymentinfo() {
		
		return subscriptionmapper.getpaymentinfo();
	}

}
