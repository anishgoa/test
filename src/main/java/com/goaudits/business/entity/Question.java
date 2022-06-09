package com.goaudits.business.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;

import org.springframework.stereotype.Repository;

import com.goaudits.business.util.Utils;

@Repository
//@JsonSerialize(include = JsonSerialize.Inclusion.NON_EMPTY)
public class Question implements Serializable {

	private static final long serialVersionUID = 1L;
	@Pattern(regexp = Utils.VALIDATION_REGEX, message =Utils.Validation_Message,flags = Pattern.Flag.DOTALL)
	private String guid;
	@Pattern(regexp = Utils.VALIDATION_REGEX, message =Utils.Validation_Message,flags = Pattern.Flag.DOTALL)
	private String uid;
	private int client_id;
	private int store_id;
	private int audit_group_id;
	private int audit_type_id;
	private int section_id;
	private int group_id;
	private int question_no;
	@Pattern(regexp = Utils.VALIDATION_REGEX, message =Utils.Validation_Message,flags = Pattern.Flag.DOTALL)
	private String question_text;
	private int choice_pat_id;
	private double available_score;
	@Pattern(regexp = Utils.VALIDATION_REGEX, message =Utils.Validation_Message,flags = Pattern.Flag.DOTALL)
	private String question_help;
	@Pattern(regexp = Utils.VALIDATION_REGEX, message =Utils.Validation_Message,flags = Pattern.Flag.DOTALL)
	private String question_weight;
	private boolean active;
	@Pattern(regexp = Utils.VALIDATION_REGEX, message =Utils.Validation_Message,flags = Pattern.Flag.DOTALL)
	private String default_choice_id;
	private boolean ismandatory;
	private boolean image_mandatory;
	private boolean comment_mandatory;
	@Pattern(regexp = Utils.VALIDATION_REGEX, message =Utils.Validation_Message,flags = Pattern.Flag.DOTALL)
	private String question_order;
	@Pattern(regexp = Utils.VALIDATION_REGEX, message =Utils.Validation_Message,flags = Pattern.Flag.DOTALL)
	private String default_choice_text;
	@Pattern(regexp = Utils.VALIDATION_REGEX, message =Utils.Validation_Message,flags = Pattern.Flag.DOTALL)
	private String choice_type;
	@Pattern(regexp = Utils.VALIDATION_REGEX, message =Utils.Validation_Message,flags = Pattern.Flag.DOTALL)
	private String tag_id;
	@Pattern(regexp = Utils.VALIDATION_REGEX, message =Utils.Validation_Message,flags = Pattern.Flag.DOTALL)
	private String tag_code;
	@Pattern(regexp = Utils.VALIDATION_REGEX, message =Utils.Validation_Message,flags = Pattern.Flag.DOTALL)
	private String tag_description;
	@Pattern(regexp = Utils.VALIDATION_REGEX, message =Utils.Validation_Message,flags = Pattern.Flag.DOTALL)
	private String passing_level;
	@Pattern(regexp = Utils.VALIDATION_REGEX, message =Utils.Validation_Message,flags = Pattern.Flag.DOTALL)
	private String sort_order;
	@Pattern(regexp = Utils.VALIDATION_REGEX, message =Utils.Validation_Message,flags = Pattern.Flag.DOTALL)
	String choice_text1;
	@Pattern(regexp = Utils.VALIDATION_REGEX, message =Utils.Validation_Message,flags = Pattern.Flag.DOTALL)
	String choice_text2;
	@Pattern(regexp = Utils.VALIDATION_REGEX, message =Utils.Validation_Message,flags = Pattern.Flag.DOTALL)
	String choice_text3;
	@Pattern(regexp = Utils.VALIDATION_REGEX, message =Utils.Validation_Message,flags = Pattern.Flag.DOTALL)
	String choice_text4;
	@Pattern(regexp = Utils.VALIDATION_REGEX, message =Utils.Validation_Message,flags = Pattern.Flag.DOTALL)
	private String failed_choice_id;
	private boolean notification_type;
	private String critical_email_list;
	private boolean notification_enabled;
	@Pattern(regexp = Utils.VALIDATION_REGEX, message =Utils.Validation_Message,flags = Pattern.Flag.DOTALL)
	private String comments;
	@Pattern(regexp = Utils.VALIDATION_REGEX, message =Utils.Validation_Message,flags = Pattern.Flag.DOTALL)
	private String due_date;
	@Pattern(regexp = Utils.VALIDATION_REGEX, message =Utils.Validation_Message,flags = Pattern.Flag.DOTALL)
	private String priority;
	@Pattern(regexp = Utils.VALIDATION_REGEX, message =Utils.Validation_Message,flags = Pattern.Flag.DOTALL)
	private String audit_date;
	@Pattern(regexp = Utils.VALIDATION_REGEX, message =Utils.Validation_Message,flags = Pattern.Flag.DOTALL)
	private String seq_no;
	@Pattern(regexp = Utils.VALIDATION_REGEX, message =Utils.Validation_Message,flags = Pattern.Flag.DOTALL)
	private String status;
	@Pattern(regexp = Utils.VALIDATION_REGEX, message =Utils.Validation_Message,flags = Pattern.Flag.DOTALL)
	private String status_id;
	@Pattern(regexp = Utils.VALIDATION_REGEX, message =Utils.Validation_Message,flags = Pattern.Flag.DOTALL)
	private String status_name;
	@Pattern(regexp = Utils.VALIDATION_REGEX, message =Utils.Validation_Message,flags = Pattern.Flag.DOTALL)
	private String action_taken;
	@Pattern(regexp = Utils.VALIDATION_REGEX, message =Utils.Validation_Message,flags = Pattern.Flag.DOTALL)
	private String action_taken_date;
	private boolean isactionplan_mandatory;
	@Pattern(regexp = Utils.VALIDATION_REGEX, message =Utils.Validation_Message,flags = Pattern.Flag.DOTALL)
	private String auto_fail;
	@Pattern(regexp = Utils.VALIDATION_REGEX, message =Utils.Validation_Message,flags = Pattern.Flag.DOTALL)
	private String assignedto_email;
	@Pattern(regexp = Utils.VALIDATION_REGEX, message =Utils.Validation_Message,flags = Pattern.Flag.DOTALL)
	private String client_name;
	@Pattern(regexp = Utils.VALIDATION_REGEX, message =Utils.Validation_Message,flags = Pattern.Flag.DOTALL)
	private String audit_type_name;
	@Pattern(regexp = Utils.VALIDATION_REGEX, message =Utils.Validation_Message,flags = Pattern.Flag.DOTALL)
	private String store_name;
	@Pattern(regexp = Utils.VALIDATION_REGEX, message =Utils.Validation_Message,flags = Pattern.Flag.DOTALL)
	private String over_due_color;
	@Pattern(regexp = Utils.VALIDATION_REGEX, message =Utils.Validation_Message,flags = Pattern.Flag.DOTALL)
	private String priority_color;
	@Pattern(regexp = Utils.VALIDATION_REGEX, message =Utils.Validation_Message,flags = Pattern.Flag.DOTALL)
	private String status_color;
	private boolean isapproval_required;
	private String token;
	@Pattern(regexp = Utils.VALIDATION_REGEX, message =Utils.Validation_Message,flags = Pattern.Flag.DOTALL)
	private String extend_duedate;
	private boolean extend_flag;
	@Pattern(regexp = Utils.VALIDATION_REGEX, message =Utils.Validation_Message,flags = Pattern.Flag.DOTALL)
	private String assignedto_name;
	@Pattern(regexp = Utils.VALIDATION_REGEX, message =Utils.Validation_Message,flags = Pattern.Flag.DOTALL)
	private String display_code;
	private boolean imageflag;
	private int question_type;
	private boolean image_position;
	@Pattern(regexp = Utils.VALIDATION_REGEX, message =Utils.Validation_Message,flags = Pattern.Flag.DOTALL)
	private String ext_comments;
	@Pattern(regexp = Utils.VALIDATION_REGEX, message =Utils.Validation_Message,flags = Pattern.Flag.DOTALL)
	private String assignedto_dept;
	@Pattern(regexp = Utils.VALIDATION_REGEX, message =Utils.Validation_Message,flags = Pattern.Flag.DOTALL)
	private String assignedto_person;
	@Pattern(regexp = Utils.VALIDATION_REGEX, message =Utils.Validation_Message,flags = Pattern.Flag.DOTALL)
	private String ext_admin_comments;
	@Pattern(regexp = Utils.VALIDATION_REGEX, message =Utils.Validation_Message,flags = Pattern.Flag.DOTALL)
	private String ext_log;
	@Pattern(regexp = Utils.VALIDATION_REGEX, message =Utils.Validation_Message,flags = Pattern.Flag.DOTALL)
	private String reassign_comments;
	@Pattern(regexp = Utils.VALIDATION_REGEX, message =Utils.Validation_Message,flags = Pattern.Flag.DOTALL)
	private String approved_duedate;
	@Pattern(regexp = Utils.VALIDATION_REGEX, message =Utils.Validation_Message,flags = Pattern.Flag.DOTALL)
	private String actual_due_date;
	private boolean action_enabled;
	private boolean email_enabled;
	@Pattern(regexp = Utils.VALIDATION_REGEX, message =Utils.Validation_Message,flags = Pattern.Flag.DOTALL)
	private String action_choices;
	@Pattern(regexp = Utils.VALIDATION_REGEX, message =Utils.Validation_Message,flags = Pattern.Flag.DOTALL)
	private String email_choices;
	private boolean no_score;
	private int conditionalchoice;
	private int sub_question_no;
	private int choice_id;
	private boolean is_parent_question;
	private boolean is_sub_question;
	@Pattern(regexp = Utils.VALIDATION_REGEX, message =Utils.Validation_Message,flags = Pattern.Flag.DOTALL)
	private String conditional_choice_pat_id;
	@Pattern(regexp = Utils.VALIDATION_REGEX, message =Utils.Validation_Message,flags = Pattern.Flag.DOTALL)
	private String conditional_choiceid;
	private boolean conditionalflag;
	private boolean conditionnew;
	private int oldchoiceid;
	@Pattern(regexp = Utils.VALIDATION_REGEX, message =Utils.Validation_Message,flags = Pattern.Flag.DOTALL)
	private String selectedadmins;
	@Pattern(regexp = Utils.VALIDATION_REGEX, message =Utils.Validation_Message,flags = Pattern.Flag.DOTALL)
	private String temp_min;
	@Pattern(regexp = Utils.VALIDATION_REGEX, message =Utils.Validation_Message,flags = Pattern.Flag.DOTALL)
	private String temp_max;
	@Pattern(regexp = Utils.VALIDATION_REGEX, message =Utils.Validation_Message,flags = Pattern.Flag.DOTALL)
	private String vchoice_type;
	@Pattern(regexp = Utils.VALIDATION_REGEX, message =Utils.Validation_Message,flags = Pattern.Flag.DOTALL)
	private String temp_unit;
	private boolean is_multichoice;
	@Pattern(regexp = Utils.VALIDATION_REGEX, message =Utils.Validation_Message,flags = Pattern.Flag.DOTALL)
	private String oldtagid;
	private int picture_layout;
	private String comment_choices;
	@Pattern(regexp = Utils.VALIDATION_REGEX, message =Utils.Validation_Message,flags = Pattern.Flag.DOTALL)
	private String image_choices;
	@Pattern(regexp = Utils.VALIDATION_REGEX, message =Utils.Validation_Message,flags = Pattern.Flag.DOTALL)
	private String question_text_color;
	private String image_path;
	@Pattern(regexp = Utils.VALIDATION_REGEX, message =Utils.Validation_Message,flags = Pattern.Flag.DOTALL)
	private String image_public_id;
	@Pattern(regexp = Utils.VALIDATION_REGEX, message =Utils.Validation_Message,flags = Pattern.Flag.DOTALL)
	private String image_thumbnail;
	private String image;
	private int image_id;
	@Pattern(regexp = Utils.VALIDATION_REGEX, message =Utils.Validation_Message,flags = Pattern.Flag.DOTALL)
	private String qcomments;
	private int audits_count;
	private int addedquestion_order=0;
    byte[] binaryimage;
    private boolean copy=false;
    @Pattern(regexp = Utils.VALIDATION_REGEX, message =Utils.Validation_Message,flags = Pattern.Flag.DOTALL)
    private String copyques_no;
    @Pattern(regexp = Utils.VALIDATION_REGEX, message =Utils.Validation_Message,flags = Pattern.Flag.DOTALL)
    private String failed_choice;
    @Pattern(regexp = Utils.VALIDATION_REGEX, message =Utils.Validation_Message,flags = Pattern.Flag.DOTALL)
    private String default_value;
    @Pattern(regexp = Utils.VALIDATION_REGEX, message =Utils.Validation_Message,flags = Pattern.Flag.DOTALL)
    private String action_default_value;
    @Valid
	private List<Choice> choiceList = new ArrayList<Choice>();
    @Valid
	private List<Choice> sublist = new ArrayList<Choice>();
    @Valid
	private List<Quesactionfield> questionactfldlist = new ArrayList<Quesactionfield>();
    @Valid
	private List<Questactimage> questactimglist = new ArrayList<Questactimage>();
    @Valid
	private List<Actioncount> questactcountlist = new ArrayList<Actioncount>();
    @Valid
	private List<Questionimage> questimagelist = new ArrayList<Questionimage>();
	

	
	public String getQcomments() {
		return qcomments;
	}

	public void setQcomments(String qcomments) {
		this.qcomments = qcomments;
	}

	public String getComment_choices() {
		return comment_choices;
	}

	public void setComment_choices(String comment_choices) {
		this.comment_choices = comment_choices;
	}

	public String getImage_choices() {
		return image_choices;
	}

	public void setImage_choices(String image_choices) {
		this.image_choices = image_choices;
	}

	public int getPicture_layout() {
		return picture_layout;
	}

	public void setPicture_layout(int picture_layout) {
		this.picture_layout = picture_layout;
	}

	public String getOldtagid() {
		return oldtagid;
	}

	public void setOldtagid(String oldtagid) {
		this.oldtagid = oldtagid;
	}

	public boolean isIs_multichoice() {
		return is_multichoice;
	}

	public void setIs_multichoice(boolean is_multichoice) {
		this.is_multichoice = is_multichoice;
	}

	public String getTemp_unit() {
		return temp_unit;
	}

	public void setTemp_unit(String temp_unit) {
		this.temp_unit = temp_unit;
	}

	public String getTemp_min() {
		return temp_min;
	}

	public void setTemp_min(String temp_min) {
		this.temp_min = temp_min;
	}

	public String getTemp_max() {
		return temp_max;
	}

	public void setTemp_max(String temp_max) {
		this.temp_max = temp_max;
	}

	public String getVchoice_type() {
		return vchoice_type;
	}

	public void setVchoice_type(String vchoice_type) {
		this.vchoice_type = vchoice_type;
	}

	public String getSelectedadmins() {
		return selectedadmins;
	}

	public void setSelectedadmins(String selectedadmins) {
		this.selectedadmins = selectedadmins;
	}

	public int getOldchoiceid() {
		return oldchoiceid;
	}

	public void setOldchoiceid(int oldchoiceid) {
		this.oldchoiceid = oldchoiceid;
	}

	public boolean isConditionnew() {
		return conditionnew;
	}

	public void setConditionnew(boolean conditionnew) {
		this.conditionnew = conditionnew;
	}

	public boolean isConditionalflag() {
		return conditionalflag;
	}

	public void setConditionalflag(boolean conditionalflag) {
		this.conditionalflag = conditionalflag;
	}

	public List<Choice> getSublist() {
		return sublist;
	}

	public void setSublist(List<Choice> sublist) {
		this.sublist = sublist;
	}



	/**
	 * @return the conditional_choice_pat_id
	 */
	public String getConditional_choice_pat_id() {
		return conditional_choice_pat_id;
	}

	/**
	 * @param conditional_choice_pat_id the conditional_choice_pat_id to set
	 */
	public void setConditional_choice_pat_id(String conditional_choice_pat_id) {
		this.conditional_choice_pat_id = conditional_choice_pat_id;
	}

	/**
	 * @return the conditional_choiceid
	 */
	public String getConditional_choiceid() {
		return conditional_choiceid;
	}

	/**
	 * @param conditional_choiceid the conditional_choiceid to set
	 */
	public void setConditional_choiceid(String conditional_choiceid) {
		this.conditional_choiceid = conditional_choiceid;
	}

	public boolean isIs_parent_question() {
		return is_parent_question;
	}

	public void setIs_parent_question(boolean is_parent_question) {
		this.is_parent_question = is_parent_question;
	}

	public boolean isIs_sub_question() {
		return is_sub_question;
	}

	public void setIs_sub_question(boolean is_sub_question) {
		this.is_sub_question = is_sub_question;
	}

	public int getChoice_id() {
		return choice_id;
	}

	public void setChoice_id(int choice_id) {
		this.choice_id = choice_id;
	}

	public int getSub_question_no() {
		return sub_question_no;
	}

	public void setSub_question_no(int sub_question_no) {
		this.sub_question_no = sub_question_no;
	}

	public int getConditionalchoice() {
		return conditionalchoice;
	}

	public void setConditionalchoice(int conditionalchoice) {
		this.conditionalchoice = conditionalchoice;
	}

	public boolean isNo_score() {
		return no_score;
	}

	public void setNo_score(boolean no_score) {
		this.no_score = no_score;
	}

	public String getAction_choices() {
		return action_choices;
	}

	public void setAction_choices(String action_choices) {
		this.action_choices = action_choices;
	}

	public String getEmail_choices() {
		return email_choices;
	}

	public void setEmail_choices(String email_choices) {
		this.email_choices = email_choices;
	}

	public String getApproved_duedate() {
		return approved_duedate;
	}

	public void setApproved_duedate(String approved_duedate) {
		this.approved_duedate = approved_duedate;
	}

	public String getExt_admin_comments() {
		return ext_admin_comments;
	}

	public void setExt_admin_comments(String ext_admin_comments) {
		this.ext_admin_comments = ext_admin_comments;
	}

	public String getExt_log() {
		return ext_log;
	}

	public void setExt_log(String ext_log) {
		this.ext_log = ext_log;
	}

	public String getReassign_comments() {
		return reassign_comments;
	}

	public void setReassign_comments(String reassign_comments) {
		this.reassign_comments = reassign_comments;
	}

	public String getAssignedto_person() {
		return assignedto_person;
	}

	public void setAssignedto_person(String assignedto_person) {
		this.assignedto_person = assignedto_person;
	}

	public String getAssignedto_dept() {
		return assignedto_dept;
	}

	public void setAssignedto_dept(String assignedto_dept) {
		this.assignedto_dept = assignedto_dept;
	}

	public String getExt_comments() {
		return ext_comments;
	}

	public void setExt_comments(String ext_comments) {
		this.ext_comments = ext_comments;
	}

	public boolean isImage_position() {
		return image_position;
	}

	public void setImage_position(boolean image_position) {
		this.image_position = image_position;
	}

	public int getQuestion_type() {
		return question_type;
	}

	public void setQuestion_type(int question_type) {
		this.question_type = question_type;
	}

	public boolean isImageflag() {
		return imageflag;
	}

	public void setImageflag(boolean imageflag) {
		this.imageflag = imageflag;
	}

	public String getAssignedto_name() {
		return assignedto_name;
	}

	public void setAssignedto_name(String assignedto_name) {
		this.assignedto_name = assignedto_name;
	}

	public List<Questionimage> getQuestimagelist() {
		return questimagelist;
	}

	public void setQuestimagelist(List<Questionimage> questimagelist) {
		this.questimagelist = questimagelist;
	}

	public String getDisplay_code() {
		return display_code;
	}

	public void setDisplay_code(String display_code) {
		this.display_code = display_code;
	}

	public List<Actioncount> getQuestactcountlist() {
		return questactcountlist;
	}

	public void setQuestactcountlist(List<Actioncount> questactcountlist) {
		this.questactcountlist = questactcountlist;
	}

	public String getExtend_duedate() {
		return extend_duedate;
	}

	public void setExtend_duedate(String extend_duedate) {
		this.extend_duedate = extend_duedate;
	}

	public boolean isExtend_flag() {
		return extend_flag;
	}

	public void setExtend_flag(boolean extend_flag) {
		this.extend_flag = extend_flag;
	}

	public List<Quesactionfield> getQuestionactfldlist() {
		return questionactfldlist;
	}

	public void setQuestionactfldlist(List<Quesactionfield> questionactfldlist) {
		this.questionactfldlist = questionactfldlist;
	}

	public List<Questactimage> getQuestactimglist() {
		return questactimglist;
	}

	public void setQuestactimglist(List<Questactimage> questactimglist) {
		this.questactimglist = questactimglist;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public boolean isIsapproval_required() {
		return isapproval_required;
	}

	public void setIsapproval_required(boolean isapproval_required) {
		this.isapproval_required = isapproval_required;
	}

	public String getStatus_id() {
		return status_id;
	}

	public void setStatus_id(String status_id) {
		this.status_id = status_id;
	}

	public String getStatus_name() {
		return status_name;
	}

	public void setStatus_name(String status_name) {
		this.status_name = status_name;
	}

	public String getStatus_color() {
		return status_color;
	}

	public void setStatus_color(String status_color) {
		this.status_color = status_color;
	}

	public String getOver_due_color() {
		return over_due_color;
	}

	public void setOver_due_color(String over_due_color) {
		this.over_due_color = over_due_color;
	}

	public String getPriority_color() {
		return priority_color;
	}

	public void setPriority_color(String priority_color) {
		this.priority_color = priority_color;
	}

	public String getClient_name() {
		return client_name;
	}

	public void setClient_name(String client_name) {
		this.client_name = client_name;
	}

	public String getAudit_type_name() {
		return audit_type_name;
	}

	public void setAudit_type_name(String audit_type_name) {
		this.audit_type_name = audit_type_name;
	}

	public String getStore_name() {
		return store_name;
	}

	public void setStore_name(String store_name) {
		this.store_name = store_name;
	}

	public String getAssignedto_email() {
		return assignedto_email;
	}

	public void setAssignedto_email(String assignedto_email) {
		this.assignedto_email = assignedto_email;
	}

	public String getAuto_fail() {
		return auto_fail;
	}

	public void setAuto_fail(String auto_fail) {
		this.auto_fail = auto_fail;
	}

	public boolean isIsactionplan_mandatory() {
		return isactionplan_mandatory;
	}

	public void setIsactionplan_mandatory(boolean isactionplan_mandatory) {
		this.isactionplan_mandatory = isactionplan_mandatory;
	}

	public int getStore_id() {
		return store_id;
	}

	public void setStore_id(int store_id) {
		this.store_id = store_id;
	}

	public String getAction_taken() {
		return action_taken;
	}

	public void setAction_taken(String action_taken) {
		this.action_taken = action_taken;
	}

	public String getAction_taken_date() {
		return action_taken_date;
	}

	public void setAction_taken_date(String action_taken_date) {
		this.action_taken_date = action_taken_date;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getAudit_date() {
		return audit_date;
	}

	public void setAudit_date(String audit_date) {
		this.audit_date = audit_date;
	}

	public String getSeq_no() {
		return seq_no;
	}

	public void setSeq_no(String seq_no) {
		this.seq_no = seq_no;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getDue_date() {
		return due_date;
	}

	public void setDue_date(String due_date) {
		this.due_date = due_date;
	}

	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

	public boolean isNotification_type() {
		return notification_type;
	}

	public void setNotification_type(boolean notification_type) {
		this.notification_type = notification_type;
	}

	public boolean isNotification_enabled() {
		return notification_enabled;
	}

	public void setNotification_enabled(boolean notification_enabled) {
		this.notification_enabled = notification_enabled;
	}

	public String getFailed_choice_id() {
		return failed_choice_id;
	}

	public void setFailed_choice_id(String failed_choice_id) {
		this.failed_choice_id = failed_choice_id;
	}

	public String getCritical_email_list() {
		return critical_email_list;
	}

	public void setCritical_email_list(String critical_email_list) {
		this.critical_email_list = critical_email_list;
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

	public String getTag_code() {
		return tag_code;
	}

	public void setTag_code(String tag_code) {
		this.tag_code = tag_code;
	}

	public String getTag_description() {
		return tag_description;
	}

	public void setTag_description(String tag_description) {
		this.tag_description = tag_description;
	}

	public String getPassing_level() {
		return passing_level;
	}

	public void setPassing_level(String passing_level) {
		this.passing_level = passing_level;
	}

	public String getSort_order() {
		return sort_order;
	}

	public void setSort_order(String sort_order) {
		this.sort_order = sort_order;
	}

	public String getTag_id() {
		return tag_id;
	}

	public void setTag_id(String tag_id) {
		this.tag_id = tag_id;
	}

	public String getChoice_type() {
		return choice_type;
	}

	public void setChoice_type(String choice_type) {
		this.choice_type = choice_type;
	}

	public String getDefault_choice_text() {
		return default_choice_text;
	}

	public void setDefault_choice_text(String default_choice_text) {
		this.default_choice_text = default_choice_text;
	}

	public List<Choice> getChoiceList() {
		return choiceList;
	}

	public void setChoiceList(List<Choice> choiceList) {
		this.choiceList = choiceList;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
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

	public int getSection_id() {
		return section_id;
	}

	public void setSection_id(int section_id) {
		this.section_id = section_id;
	}

	public int getGroup_id() {
		return group_id;
	}

	public void setGroup_id(int group_id) {
		this.group_id = group_id;
	}

	public int getQuestion_no() {
		return question_no;
	}

	public void setQuestion_no(int question_no) {
		this.question_no = question_no;
	}

	public String getQuestion_text() {
		return question_text;
	}

	public void setQuestion_text(String question_text) {
		this.question_text = question_text;
	}

	public int getChoice_pat_id() {
		return choice_pat_id;
	}

	public void setChoice_pat_id(int choice_pat_id) {
		this.choice_pat_id = choice_pat_id;
	}

	public double getAvailable_score() {
		return available_score;
	}

	public void setAvailable_score(double available_score) {
		this.available_score = available_score;
	}

	public String getQuestion_help() {
		return question_help;
	}

	public void setQuestion_help(String question_help) {
		this.question_help = question_help;
	}

	public String getQuestion_weight() {
		return question_weight;
	}

	public void setQuestion_weight(String question_weight) {
		this.question_weight = question_weight;
	}



	/**
	 * @return the default_choice_id
	 */
	public String getDefault_choice_id() {
		return default_choice_id;
	}

	/**
	 * @param default_choice_id the default_choice_id to set
	 */
	public void setDefault_choice_id(String default_choice_id) {
		this.default_choice_id = default_choice_id;
	}

	public String getQuestion_order() {
		return question_order;
	}

	public void setQuestion_order(String question_order) {
		this.question_order = question_order;
	}

	public boolean isIsmandatory() {
		return ismandatory;
	}

	public void setIsmandatory(boolean ismandatory) {
		this.ismandatory = ismandatory;
	}

	public boolean isImage_mandatory() {
		return image_mandatory;
	}

	public void setImage_mandatory(boolean image_mandatory) {
		this.image_mandatory = image_mandatory;
	}

	public boolean isComment_mandatory() {
		return comment_mandatory;
	}

	public void setComment_mandatory(boolean comment_mandatory) {
		this.comment_mandatory = comment_mandatory;
	}

	public String getActual_due_date() {
		return actual_due_date;
	}

	public void setActual_due_date(String actual_due_date) {
		this.actual_due_date = actual_due_date;
	}

	public boolean isAction_enabled() {
		return action_enabled;
	}

	public void setAction_enabled(boolean action_enabled) {
		this.action_enabled = action_enabled;
	}

	public boolean isEmail_enabled() {
		return email_enabled;
	}

	public void setEmail_enabled(boolean email_enabled) {
		this.email_enabled = email_enabled;
	}

	public String getQuestion_text_color() {
		return question_text_color;
	}

	public void setQuestion_text_color(String question_text_color) {
		this.question_text_color = question_text_color;
	}

	public String getImage_path() {
		return image_path;
	}

	public void setImage_path(String image_path) {
		this.image_path = image_path;
	}

	public String getImage_public_id() {
		return image_public_id;
	}

	public void setImage_public_id(String image_public_id) {
		this.image_public_id = image_public_id;
	}

	public String getImage_thumbnail() {
		return image_thumbnail;
	}

	public void setImage_thumbnail(String image_thumbnail) {
		this.image_thumbnail = image_thumbnail;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public int getImage_id() {
		return image_id;
	}

	public void setImage_id(int image_id) {
		this.image_id = image_id;
	}

	public byte[] getBinaryimage() {
		return binaryimage;
	}

	public void setBinaryimage(byte[] binaryimage) {
		this.binaryimage = binaryimage;
	}

	public int getAudits_count() {
		return audits_count;
	}

	public void setAudits_count(int audits_count) {
		this.audits_count = audits_count;
	}

	/**
	 * @return the addedquestion_order
	 */
	public int getAddedquestion_order() {
		return addedquestion_order;
	}

	/**
	 * @param addedquestion_order the addedquestion_order to set
	 */
	public void setAddedquestion_order(int addedquestion_order) {
		this.addedquestion_order = addedquestion_order;
	}

	/**
	 * @return the copy
	 */
	public boolean isCopy() {
		return copy;
	}

	/**
	 * @param copy the copy to set
	 */
	public void setCopy(boolean copy) {
		this.copy = copy;
	}

	/**
	 * @return the copyques_no
	 */
	public String getCopyques_no() {
		return copyques_no;
	}

	/**
	 * @param copyques_no the copyques_no to set
	 */
	public void setCopyques_no(String copyques_no) {
		this.copyques_no = copyques_no;
	}

	public String getFailed_choice() {
		return failed_choice;
	}

	public void setFailed_choice(String failed_choice) {
		this.failed_choice = failed_choice;
	}

	public String getDefault_value() {
		return default_value;
	}

	public void setDefault_value(String default_value) {
		this.default_value = default_value;
	}

	public String getAction_default_value() {
		return action_default_value;
	}

	public void setAction_default_value(String action_default_value) {
		this.action_default_value = action_default_value;
	}
	
	
	
}