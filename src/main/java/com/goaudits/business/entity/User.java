package com.goaudits.business.entity;

import java.sql.Date;

import javax.validation.constraints.Pattern;

import org.springframework.stereotype.Repository;

import com.goaudits.business.util.Utils;

@Repository
public class User {
	@Pattern(regexp = Utils.VALIDATION_REGEX, message =Utils.Validation_Message,flags = Pattern.Flag.DOTALL)
	private String user_name;
	private String usr_pwd;
	private String authToken;
	@Pattern(regexp = Utils.VALIDATION_REGEX, message =Utils.Validation_Message,flags = Pattern.Flag.DOTALL)
	private String uid;
	@Pattern(regexp = Utils.VALIDATION_REGEX, message =Utils.Validation_Message,flags = Pattern.Flag.DOTALL)
	private String guid;
	private boolean super_user;
	private int role_id;
	private boolean publish_access;
	private String reg_token;
	private boolean active;
	private Date last_modified;
	@Pattern(regexp = Utils.VALIDATION_REGEX, message =Utils.Validation_Message,flags = Pattern.Flag.DOTALL)
	private String updated_by;
	private boolean new_user;
	private int login_count;
	@Pattern(regexp = Utils.VALIDATION_REGEX, message =Utils.Validation_Message,flags = Pattern.Flag.DOTALL)
	private String first_name;
	@Pattern(regexp = Utils.VALIDATION_REGEX, message =Utils.Validation_Message,flags = Pattern.Flag.DOTALL)
	private String last_name;
	@Pattern(regexp = Utils.VALIDATION_REGEX, message =Utils.Validation_Message,flags = Pattern.Flag.DOTALL)
	private String reg_platform;
	@Pattern(regexp = Utils.VALIDATION_REGEX, message =Utils.Validation_Message,flags = Pattern.Flag.DOTALL)
	private String reg_device;
	private String logo;
	@Pattern(regexp = Utils.VALIDATION_REGEX, message =Utils.Validation_Message,flags = Pattern.Flag.DOTALL)
	private String role_name;
	@Pattern(regexp = Utils.VALIDATION_REGEX, message =Utils.Validation_Message,flags = Pattern.Flag.DOTALL)
	private String group_name;
	private int group_id;
	private boolean b2b;
	private String grp_logo;
	private byte[] grp_binary;
	private int user_count;
	private int role_type_code;
	@Pattern(regexp = Utils.VALIDATION_REGEX, message =Utils.Validation_Message,flags = Pattern.Flag.DOTALL)
    private String grace_period_days;
    private boolean bypass_flag;
    @Pattern(regexp = Utils.VALIDATION_REGEX, message =Utils.Validation_Message,flags = Pattern.Flag.DOTALL)
    private String name;
    @Pattern(regexp = Utils.VALIDATION_REGEX, message =Utils.Validation_Message,flags = Pattern.Flag.DOTALL)
    private String max_license_date;
    @Pattern(regexp = Utils.VALIDATION_REGEX, message =Utils.Validation_Message,flags = Pattern.Flag.DOTALL)
    private String expiry_date;
    @Pattern(regexp = Utils.VALIDATION_REGEX, message =Utils.Validation_Message,flags = Pattern.Flag.DOTALL)
    private String license_type;
    private int days_remaining;
    private int renewal_notification_days;
    private int access_count;
    private int schedule_count;
    private boolean gps_location_filter_enabled;
    private boolean voice_comment_enabled;
    private boolean portalAccess;
    private boolean analyticsAccess;
    private int auditsCount;
	
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getUsr_pwd() {
		return usr_pwd;
	}
	public void setUsr_pwd(String usr_pwd) {
		this.usr_pwd = usr_pwd;
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getGuid() {
		return guid;
	}
	public void setGuid(String guid) {
		this.guid = guid;
	}
	public boolean isSuper_user() {
		return super_user;
	}
	public void setSuper_user(boolean super_user) {
		this.super_user = super_user;
	}
	public int getRole_id() {
		return role_id;
	}
	public void setRole_id(int role_id) {
		this.role_id = role_id;
	}
	public boolean isPublish_access() {
		return publish_access;
	}
	public void setPublish_access(boolean publish_access) {
		this.publish_access = publish_access;
	}
	public String getReg_token() {
		return reg_token;
	}
	public void setReg_token(String reg_token) {
		this.reg_token = reg_token;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	public Date getLast_modified() {
		return last_modified;
	}
	public void setLast_modified(Date last_modified) {
		this.last_modified = last_modified;
	}
	public String getUpdated_by() {
		return updated_by;
	}
	public void setUpdated_by(String updated_by) {
		this.updated_by = updated_by;
	}
	public boolean isNew_user() {
		return new_user;
	}
	public void setNew_user(boolean new_user) {
		this.new_user = new_user;
	}
	public int getLogin_count() {
		return login_count;
	}
	public void setLogin_count(int login_count) {
		this.login_count = login_count;
	}
	public String getFirst_name() {
		return first_name;
	}
	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}
	public String getLast_name() {
		return last_name;
	}
	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}
	public String getReg_platform() {
		return reg_platform;
	}
	public void setReg_platform(String reg_platform) {
		this.reg_platform = reg_platform;
	}
	public String getReg_device() {
		return reg_device;
	}
	public void setReg_device(String reg_device) {
		this.reg_device = reg_device;
	}
	public String getLogo() {
		return logo;
	}
	public void setLogo(String logo) {
		this.logo = logo;
	}
	public String getRole_name() {
		return role_name;
	}
	public void setRole_name(String role_name) {
		this.role_name = role_name;
	}
	public String getGroup_name() {
		return group_name;
	}
	public void setGroup_name(String group_name) {
		this.group_name = group_name;
	}
	public int getGroup_id() {
		return group_id;
	}
	public void setGroup_id(int group_id) {
		this.group_id = group_id;
	}
	public boolean isB2b() {
		return b2b;
	}
	public void setB2b(boolean b2b) {
		this.b2b = b2b;
	}
	public String getGrp_logo() {
		return grp_logo;
	}
	public void setGrp_logo(String grp_logo) {
		this.grp_logo = grp_logo;
	}
	public byte[] getGrp_binary() {
		return grp_binary;
	}
	public void setGrp_binary(byte[] grp_binary) {
		this.grp_binary = grp_binary;
	}
	public int getUser_count() {
		return user_count;
	}
	public void setUser_count(int user_count) {
		this.user_count = user_count;
	}
	public int getRole_type_code() {
		return role_type_code;
	}
	public void setRole_type_code(int role_type_code) {
		this.role_type_code = role_type_code;
	}
	public String getGrace_period_days() {
		return grace_period_days;
	}
	public void setGrace_period_days(String grace_period_days) {
		this.grace_period_days = grace_period_days;
	}
	public boolean isBypass_flag() {
		return bypass_flag;
	}
	public void setBypass_flag(boolean bypass_flag) {
		this.bypass_flag = bypass_flag;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMax_license_date() {
		return max_license_date;
	}
	public void setMax_license_date(String max_license_date) {
		this.max_license_date = max_license_date;
	}
	public String getExpiry_date() {
		return expiry_date;
	}
	public void setExpiry_date(String expiry_date) {
		this.expiry_date = expiry_date;
	}
	public String getLicense_type() {
		return license_type;
	}
	public void setLicense_type(String license_type) {
		this.license_type = license_type;
	}
	public int getDays_remaining() {
		return days_remaining;
	}
	public void setDays_remaining(int days_remaining) {
		this.days_remaining = days_remaining;
	}
	public int getRenewal_notification_days() {
		return renewal_notification_days;
	}
	public void setRenewal_notification_days(int renewal_notification_days) {
		this.renewal_notification_days = renewal_notification_days;
	}
	public int getAccess_count() {
		return access_count;
	}
	public void setAccess_count(int access_count) {
		this.access_count = access_count;
	}
	public int getSchedule_count() {
		return schedule_count;
	}
	public void setSchedule_count(int schedule_count) {
		this.schedule_count = schedule_count;
	}
	public boolean isGps_location_filter_enabled() {
		return gps_location_filter_enabled;
	}
	public void setGps_location_filter_enabled(boolean gps_location_filter_enabled) {
		this.gps_location_filter_enabled = gps_location_filter_enabled;
	}
	public boolean isVoice_comment_enabled() {
		return voice_comment_enabled;
	}
	public void setVoice_comment_enabled(boolean voice_comment_enabled) {
		this.voice_comment_enabled = voice_comment_enabled;
	}
	public boolean isPortalAccess() {
		return portalAccess;
	}
	public void setPortalAccess(boolean portalAccess) {
		this.portalAccess = portalAccess;
	}
	public boolean isAnalyticsAccess() {
		return analyticsAccess;
	}
	public void setAnalyticsAccess(boolean analyticsAccess) {
		this.analyticsAccess = analyticsAccess;
	}
	public int getAuditsCount() {
		return auditsCount;
	}
	public void setAuditsCount(int auditsCount) {
		this.auditsCount = auditsCount;
	}
	public String getAuthToken() {
		return authToken;
	}
	public void setAuthToken(String authToken) {
		this.authToken = authToken;
	}
	
	
	
}
