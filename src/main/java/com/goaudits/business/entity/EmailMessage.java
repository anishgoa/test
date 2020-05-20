package com.goaudits.business.entity;

import org.springframework.stereotype.Repository;

@Repository
public class EmailMessage {

	private String message_variables;
	private String message_name;
	public String getMessage_variables() {
		return message_variables;
	}
	public void setMessage_variables(String message_variables) {
		this.message_variables = message_variables;
	}
	public String getMessage_name() {
		return message_name;
	}
	public void setMessage_name(String message_name) {
		this.message_name = message_name;
	}
	
}
