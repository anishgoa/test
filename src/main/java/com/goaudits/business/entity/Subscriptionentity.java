package com.goaudits.business.entity;

import org.springframework.stereotype.Repository;

@Repository
public class Subscriptionentity {
	String guid;
	int plan_id;
	String plan_name;
	double feature_id;
	String feature_name;
	int feature_units;
	boolean enabled;
	
	public String getGuid() {
		return guid;
	}
	public void setGuid(String guid) {
		this.guid = guid;
	}
	public String getPlan_name() {
		return plan_name;
	}
	public void setPlan_name(String plan_name) {
		this.plan_name = plan_name;
	}
	public int getPlan_id() {
		return plan_id;
	}
	public void setPlan_id(int plan_id) {
		this.plan_id = plan_id;
	}
	
	public double getFeature_id() {
		return feature_id;
	}
	public void setFeature_id(double feature_id) {
		this.feature_id = feature_id;
	}
	public String getFeature_name() {
		return feature_name;
	}
	public void setFeature_name(String feature_name) {
		this.feature_name = feature_name;
	}
	public int getFeature_units() {
		return feature_units;
	}
	public void setFeature_units(int feature_units) {
		this.feature_units = feature_units;
	}
	public boolean isEnabled() {
		return enabled;
	}
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	

}
