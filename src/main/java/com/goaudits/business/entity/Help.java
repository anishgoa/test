package com.goaudits.business.entity;

import org.springframework.stereotype.Repository;

@Repository
public class Help {
	String sub_page_id;
	String description;
	String help_link;

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

}
