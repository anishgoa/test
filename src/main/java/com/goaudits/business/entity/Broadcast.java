package com.goaudits.business.entity;

import org.springframework.stereotype.Repository;

@Repository
public class Broadcast {
	String guid;
	String uid;
	int broadcast_id;
	String client_id;
	String broadcast_message;
	String version_name;
	String client_name;
	String name;
	String user_name;
	String status;
	String created_by_uid;
	String latest;
	String imported;
	String created_at;
	String import_device_time;
	String device_model;
	String os_version;
	String device_platform;
	String app_version;
	String broadcast_header;
	String search_item;
	int min;
	int max;
int count;
	public String getBroadcast_header() {
		return broadcast_header;
	}

	public void setBroadcast_header(String broadcast_header) {
		this.broadcast_header = broadcast_header;
	}

	public String getCreated_by_uid() {
		return created_by_uid;
	}

	public void setCreated_by_uid(String created_by_uid) {
		this.created_by_uid = created_by_uid;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getGuid() {
		return guid;
	}

	public void setGuid(String guid) {
		this.guid = guid;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public int getBroadcast_id() {
		return broadcast_id;
	}

	public void setBroadcast_id(int broadcast_id) {
		this.broadcast_id = broadcast_id;
	}

	public String getClient_id() {
		return client_id;
	}

	public void setClient_id(String client_id) {
		this.client_id = client_id;
	}

	public String getBroadcast_message() {
		return broadcast_message;
	}

	public void setBroadcast_message(String broadcast_message) {
		this.broadcast_message = broadcast_message;
	}

	public String getVersion_name() {
		return version_name;
	}

	public void setVersion_name(String version_name) {
		this.version_name = version_name;
	}

	public String getClient_name() {
		return client_name;
	}

	public void setClient_name(String client_name) {
		this.client_name = client_name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getLatest() {
		return latest;
	}

	public void setLatest(String latest) {
		this.latest = latest;
	}

	public String getImported() {
		return imported;
	}

	public void setImported(String imported) {
		this.imported = imported;
	}

	public String getCreated_at() {
		return created_at;
	}

	public void setCreated_at(String created_at) {
		this.created_at = created_at;
	}

	public String getImport_device_time() {
		return import_device_time;
	}

	public void setImport_device_time(String import_device_time) {
		this.import_device_time = import_device_time;
	}

	public String getDevice_model() {
		return device_model;
	}

	public void setDevice_model(String device_model) {
		this.device_model = device_model;
	}

	public String getOs_version() {
		return os_version;
	}

	public void setOs_version(String os_version) {
		this.os_version = os_version;
	}

	public String getDevice_platform() {
		return device_platform;
	}

	public void setDevice_platform(String device_platform) {
		this.device_platform = device_platform;
	}

	public String getApp_version() {
		return app_version;
	}

	public void setApp_version(String app_version) {
		this.app_version = app_version;
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

}
