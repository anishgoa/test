package com.goaudits.business.entity;


import javax.validation.constraints.Pattern;

import org.springframework.stereotype.Repository;

import com.goaudits.business.util.Utils;

@Repository
public class EmailMessage {

	@Pattern(regexp = Utils.VALIDATION_REGEX, message =Utils.Validation_Message,flags = Pattern.Flag.DOTALL)
	private String message_variables;
	@Pattern(regexp = Utils.VALIDATION_REGEX, message =Utils.Validation_Message,flags = Pattern.Flag.DOTALL)
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
