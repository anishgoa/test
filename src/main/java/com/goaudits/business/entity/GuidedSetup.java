package com.goaudits.business.entity;

import javax.validation.constraints.Pattern;

import org.springframework.stereotype.Repository;

import com.goaudits.business.util.Utils;

@Repository
public class GuidedSetup {
	
	int id;
	@Pattern(regexp = Utils.VALIDATION_REGEX, message =Utils.Validation_Message,flags = Pattern.Flag.DOTALL)
	String name;
	@Pattern(regexp = Utils.VALIDATION_REGEX, message =Utils.Validation_Message,flags = Pattern.Flag.DOTALL)
	String company_name;
	String logo;
	@Pattern(regexp = Utils.VALIDATION_REGEX, message =Utils.Validation_Message,flags = Pattern.Flag.DOTALL)
	String bussiness;
	@Pattern(regexp = Utils.VALIDATION_REGEX, message =Utils.Validation_Message,flags = Pattern.Flag.DOTALL)
	String template;
	@Pattern(regexp = Utils.VALIDATION_REGEX, message =Utils.Validation_Message,flags = Pattern.Flag.DOTALL)
	String goal;
	@Pattern(regexp = Utils.VALIDATION_REGEX, message =Utils.Validation_Message,flags = Pattern.Flag.DOTALL)
	String launch;
	@Pattern(regexp = Utils.VALIDATION_REGEX, message =Utils.Validation_Message,flags = Pattern.Flag.DOTALL)
	String guid;
	@Pattern(regexp = Utils.VALIDATION_REGEX, message =Utils.Validation_Message,flags = Pattern.Flag.DOTALL)
	String uid;
	@Pattern(regexp = Utils.VALIDATION_REGEX, message =Utils.Validation_Message,flags = Pattern.Flag.DOTALL)
	String no_of_licenses;
	@Pattern(regexp = Utils.VALIDATION_REGEX, message =Utils.Validation_Message,flags = Pattern.Flag.DOTALL)
	String number;
	boolean walkthrough;
	
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the company_name
	 */
	public String getCompany_name() {
		return company_name;
	}
	/**
	 * @param company_name the company_name to set
	 */
	public void setCompany_name(String company_name) {
		this.company_name = company_name;
	}
	/**
	 * @return the logo
	 */
	public String getLogo() {
		return logo;
	}
	/**
	 * @param logo the logo to set
	 */
	public void setLogo(String logo) {
		this.logo = logo;
	}
	/**
	 * @return the bussiness
	 */
	public String getBussiness() {
		return bussiness;
	}
	/**
	 * @param bussiness the bussiness to set
	 */
	public void setBussiness(String bussiness) {
		this.bussiness = bussiness;
	}
	/**
	 * @return the template
	 */
	public String getTemplate() {
		return template;
	}
	/**
	 * @param template the template to set
	 */
	public void setTemplate(String template) {
		this.template = template;
	}
	/**
	 * @return the goal
	 */
	public String getGoal() {
		return goal;
	}
	/**
	 * @param goal the goal to set
	 */
	public void setGoal(String goal) {
		this.goal = goal;
	}
	/**
	 * @return the launch
	 */
	public String getLaunch() {
		return launch;
	}
	/**
	 * @param launch the launch to set
	 */
	public void setLaunch(String launch) {
		this.launch = launch;
	}
	/**
	 * @return the guid
	 */
	public String getGuid() {
		return guid;
	}
	/**
	 * @param guid the guid to set
	 */
	public void setGuid(String guid) {
		this.guid = guid;
	}
	/**
	 * @return the uid
	 */
	public String getUid() {
		return uid;
	}
	/**
	 * @param uid the uid to set
	 */
	public void setUid(String uid) {
		this.uid = uid;
	}
	/**
	 * @return the no_of_licenses
	 */
	public String getNo_of_licenses() {
		return no_of_licenses;
	}
	/**
	 * @param no_of_licenses the no_of_licenses to set
	 */
	public void setNo_of_licenses(String no_of_licenses) {
		this.no_of_licenses = no_of_licenses;
	}
	/**
	 * @return the number
	 */
	public String getNumber() {
		return number;
	}
	/**
	 * @param number the number to set
	 */
	public void setNumber(String number) {
		this.number = number;
	}
	/**
	 * @return the walkthrough
	 */
	public boolean isWalkthrough() {
		return walkthrough;
	}
	/**
	 * @param walkthrough the walkthrough to set
	 */
	public void setWalkthrough(boolean walkthrough) {
		this.walkthrough = walkthrough;
	}
	

}
