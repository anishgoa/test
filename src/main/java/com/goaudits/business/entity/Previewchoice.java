package com.goaudits.business.entity;
import java.io.Serializable;

import javax.validation.constraints.Pattern;

import org.springframework.stereotype.Repository;

import com.goaudits.business.util.Utils;

@Repository
public class Previewchoice  implements Serializable {
	
private static final long serialVersionUID = 1L;

	@Pattern(regexp = Utils.VALIDATION_REGEX, message =Utils.Validation_Message,flags = Pattern.Flag.DOTALL)
	private String guid;
	@Pattern(regexp = Utils.VALIDATION_REGEX, message =Utils.Validation_Message,flags = Pattern.Flag.DOTALL)
	private String uid;
	private int client_id;
	private int audit_group_id=1;
	private int audit_type_id;
	@Pattern(regexp = Utils.VALIDATION_REGEX, message =Utils.Validation_Message,flags = Pattern.Flag.DOTALL)
	String choice_text1;
	@Pattern(regexp = Utils.VALIDATION_REGEX, message =Utils.Validation_Message,flags = Pattern.Flag.DOTALL)
	String choice_text2;
	@Pattern(regexp = Utils.VALIDATION_REGEX, message =Utils.Validation_Message,flags = Pattern.Flag.DOTALL)
	String choice_text3;
	@Pattern(regexp = Utils.VALIDATION_REGEX, message =Utils.Validation_Message,flags = Pattern.Flag.DOTALL)
	String choice_text4;
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
	public String getChoice_text1() {
		return choice_text1;
	}
	public void setChoice_text1(String choice_text1) {
		this.choice_text1 = choice_text1;
	}
	public String getChoice_text2() {
		return choice_text2;
	}
	public void setChoice_text2(String choice_text2) {
		this.choice_text2 = choice_text2;
	}
	public String getChoice_text3() {
		return choice_text3;
	}
	public void setChoice_text3(String choice_text3) {
		this.choice_text3 = choice_text3;
	}
	public String getChoice_text4() {
		return choice_text4;
	}
	public void setChoice_text4(String choice_text4) {
		this.choice_text4 = choice_text4;
	}
	
	
	

}
