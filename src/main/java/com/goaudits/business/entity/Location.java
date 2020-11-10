package com.goaudits.business.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public class Location implements Serializable {

	private static final long serialVersionUID = 1L;
	private String guid;
	private String client_id;
	private String client_name;
	private String store_id;
	private String store_name;
	private String latitude;
	private String longitude;
	private String radius;
	private boolean active;
	private String address;
	private String uid;
	private String logo;
	private int group_id;
	private String to_email;
	private String cc_email;
	private String storemgr_email;
	private String location_code;
	private int pl_code;
	private String time_zone;
	private byte[] logo_binary;
	private String map_address;
	int offset = 0;
	int limit = 10;
	private String tags;
	private String postcode;
	private boolean gps_location_filter_enabled; 
	private boolean isdelete;
	int min;
	int max;
	int count;
	String search_item;
	String sort;
	String order;
	
	private List<ActionPlanAssignee> actionlist = new ArrayList<ActionPlanAssignee>();
	private List<LocationTags> tagslist = new ArrayList<LocationTags>();

	public String getPostcode() {
		return postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}


	public int getOffset() {
		return offset;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	public String getMap_address() {
		return map_address;
	}

	public void setMap_address(String map_address) {
		this.map_address = map_address;
	}

	public byte[] getLogo_binary() {
		return logo_binary;
	}

	public void setLogo_binary(byte[] logo_binary) {
		this.logo_binary = logo_binary;
	}

	public String getTime_zone() {
		return time_zone;
	}

	public void setTime_zone(String time_zone) {
		this.time_zone = time_zone;
	}

	public int getPl_code() {
		return pl_code;
	}

	public void setPl_code(int pl_code) {
		this.pl_code = pl_code;
	}

	public String getLocation_code() {
		return location_code;
	}

	public void setLocation_code(String location_code) {
		this.location_code = location_code;
	}

	public List<ActionPlanAssignee> getActionlist() {
		return actionlist;
	}

	public void setActionlist(List<ActionPlanAssignee> actionlist) {
		this.actionlist = actionlist;
	}

	public String getStoremgr_email() {
		return storemgr_email;
	}

	public void setStoremgr_email(String storemgr_email) {
		this.storemgr_email = storemgr_email;
	}

	public String getTo_email() {
		return to_email;
	}

	public void setTo_email(String to_email) {
		this.to_email = to_email;
	}

	public String getCc_email() {
		return cc_email;
	}

	public void setCc_email(String cc_email) {
		this.cc_email = cc_email;
	}

	public int getGroup_id() {
		return group_id;
	}

	public void setGroup_id(int group_id) {
		this.group_id = group_id;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getGuid() {
		return guid;
	}

	public void setGuid(String guid) {
		this.guid = guid;
	}

	public String getClient_id() {
		return client_id;
	}

	public void setClient_id(String client_id) {
		this.client_id = client_id;
	}

	public String getClient_name() {
		return client_name;
	}

	public void setClient_name(String client_name) {
		this.client_name = client_name;
	}

	public String getStore_id() {
		return store_id;
	}

	public void setStore_id(String store_id) {
		this.store_id = store_id;
	}

	public String getStore_name() {
		return store_name;
	}

	public void setStore_name(String store_name) {
		this.store_name = store_name;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getRadius() {
		return radius;
	}

	public void setRadius(String radius) {
		this.radius = radius;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public List<LocationTags> getTagslist() {
		return tagslist;
	}

	public void setTagslist(List<LocationTags> tagslist) {
		this.tagslist = tagslist;
	}

	public boolean isGps_location_filter_enabled() {
		return gps_location_filter_enabled;
	}

	public void setGps_location_filter_enabled(boolean gps_location_filter_enabled) {
		this.gps_location_filter_enabled = gps_location_filter_enabled;
	}

	/**
	 * @return the isdelete
	 */
	public boolean isIsdelete() {
		return isdelete;
	}

	/**
	 * @param isdelete the isdelete to set
	 */
	public void setIsdelete(boolean isdelete) {
		this.isdelete = isdelete;
	}

	/**
	 * @return the min
	 */
	public int getMin() {
		return min;
	}

	/**
	 * @param min the min to set
	 */
	public void setMin(int min) {
		this.min = min;
	}

	/**
	 * @return the max
	 */
	public int getMax() {
		return max;
	}

	/**
	 * @param max the max to set
	 */
	public void setMax(int max) {
		this.max = max;
	}

	/**
	 * @return the count
	 */
	public int getCount() {
		return count;
	}

	/**
	 * @param count the count to set
	 */
	public void setCount(int count) {
		this.count = count;
	}

	/**
	 * @return the search_item
	 */
	public String getSearch_item() {
		return search_item;
	}

	/**
	 * @param search_item the search_item to set
	 */
	public void setSearch_item(String search_item) {
		this.search_item = search_item;
	}

	/**
	 * @return the sort
	 */
	public String getSort() {
		return sort;
	}

	/**
	 * @param sort the sort to set
	 */
	public void setSort(String sort) {
		this.sort = sort;
	}

	/**
	 * @return the order
	 */
	public String getOrder() {
		return order;
	}

	/**
	 * @param order the order to set
	 */
	public void setOrder(String order) {
		this.order = order;
	}


	

}
