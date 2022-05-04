package com.goaudits.business.entity;

import javax.validation.constraints.Pattern;

import org.springframework.stereotype.Repository;

import com.goaudits.business.util.Utils;

@Repository
public class ScoreRange {
	@Pattern(regexp = Utils.VALIDATION_REGEX, message =Utils.Validation_Message )
	String guid;
	@Pattern(regexp = Utils.VALIDATION_REGEX, message =Utils.Validation_Message )
	String uid;
	int client_id;
	int audit_group_id;
	int audit_type_id;
	int score_range_id;
	@Pattern(regexp = Utils.VALIDATION_REGEX, message =Utils.Validation_Message )
	String score_range_type;
	double min_value;
	double max_value;
	@Pattern(regexp = Utils.VALIDATION_REGEX, message =Utils.Validation_Message )
	String grade_text;
	@Pattern(regexp = Utils.VALIDATION_REGEX, message =Utils.Validation_Message )
	String grade_color;
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
	public int getClient_id() {
		return client_id;
	}
	public void setClient_id(int client_id) {
		this.client_id = client_id;
	}
	public int getAudit_group_id() {
		return audit_group_id;
	}
	public void setAudit_group_id(int audit_group_id) {
		this.audit_group_id = audit_group_id;
	}
	public int getAudit_type_id() {
		return audit_type_id;
	}
	public void setAudit_type_id(int audit_type_id) {
		this.audit_type_id = audit_type_id;
	}
	public int getScore_range_id() {
		return score_range_id;
	}
	public void setScore_range_id(int score_range_id) {
		this.score_range_id = score_range_id;
	}
	public String getScore_range_type() {
		return score_range_type;
	}
	public void setScore_range_type(String score_range_type) {
		this.score_range_type = score_range_type;
	}

	public String getGrade_text() {
		return grade_text;
	}
	public void setGrade_text(String grade_text) {
		this.grade_text = grade_text;
	}
	public String getGrade_color() {
		return grade_color;
	}
	public void setGrade_color(String grade_color) {
		this.grade_color = grade_color;
	}
	public double getMin_value() {
		return min_value;
	}
	public void setMin_value(double min_value) {
		this.min_value = min_value;
	}
	public double getMax_value() {
		return max_value;
	}
	public void setMax_value(double max_value) {
		this.max_value = max_value;
	} 
	
	
	

}
