package com.goaudits.business.entity;

import org.springframework.stereotype.Repository;

@Repository
public class EmailSubject {
	private String subject_name;
	private String subject_variables;
	public String getSubject_name() {
		return subject_name;
	}
	public void setSubject_name(String subject_name) {
		this.subject_name = subject_name;
	}
	public String getSubject_variables() {
		return subject_variables;
	}
	public void setSubject_variables(String subject_variables) {
		this.subject_variables = subject_variables;
	}

}
