package com.goaudits.business.entity;

import javax.validation.constraints.Pattern;

import org.springframework.stereotype.Repository;

import com.goaudits.business.util.Utils;

@Repository
public class Help {
	@Pattern(regexp = Utils.VALIDATION_REGEX, message = "Special character = is not allowed")
	String sub_page_id;
	@Pattern(regexp = Utils.VALIDATION_REGEX, message = "Special character = is not allowed")
	String description;
	@Pattern(regexp = Utils.VALIDATION_REGEX, message = "Special character = is not allowed")
	String help_link;
	@Pattern(regexp = Utils.VALIDATION_REGEX, message = "Special character = is not allowed")
	String title;
	@Pattern(regexp = Utils.VALIDATION_REGEX, message = "Special character = is not allowed")
	String body;
	@Pattern(regexp = Utils.VALIDATION_REGEX, message = "Special character = is not allowed")
	String api_id;
	public String getSub_page_id() {
		return sub_page_id;
	}

	public void setSub_page_id(String sub_page_id) {
		this.sub_page_id = sub_page_id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getHelp_link() {
		return help_link;
	}

	public void setHelp_link(String help_link) {
		this.help_link = help_link;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getApi_id() {
		return api_id;
	}

	public void setApi_id(String api_id) {
		this.api_id = api_id;
	}

}
