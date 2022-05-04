package com.goaudits.business.entity;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.Pattern;

import com.goaudits.business.util.Utils;

public class ParentChoice {

	@Pattern(regexp = Utils.VALIDATION_REGEX, message =Utils.Validation_Message )
	String guid;
	int choice_pat_id;
	@Pattern(regexp = Utils.VALIDATION_REGEX, message =Utils.Validation_Message )
	String choice_pattern;
	@Pattern(regexp = Utils.VALIDATION_REGEX, message =Utils.Validation_Message )
	String choice_type;
	@Pattern(regexp = Utils.VALIDATION_REGEX, message =Utils.Validation_Message )
	String choice_id;
	@Pattern(regexp = Utils.VALIDATION_REGEX, message =Utils.Validation_Message )
	String default_score_percent;
	@Pattern(regexp = Utils.VALIDATION_REGEX, message =Utils.Validation_Message )
	String active;
	@Pattern(regexp = Utils.VALIDATION_REGEX, message =Utils.Validation_Message )
	String choice_text;
	@Pattern(regexp = Utils.VALIDATION_REGEX, message =Utils.Validation_Message )
	String choice_colour;
	int  score_type;
	private int question_no;
	private List<ParentChoice> choiceList = new ArrayList<ParentChoice>();
 	
	@Pattern(regexp = Utils.VALIDATION_REGEX, message =Utils.Validation_Message )
	String choice_text1;
	@Pattern(regexp = Utils.VALIDATION_REGEX, message =Utils.Validation_Message )
	String choice_text2;
	@Pattern(regexp = Utils.VALIDATION_REGEX, message =Utils.Validation_Message )
	String choice_text3;
	@Pattern(regexp = Utils.VALIDATION_REGEX, message =Utils.Validation_Message )
	String choice_text4;
	@Pattern(regexp = Utils.VALIDATION_REGEX, message =Utils.Validation_Message )
	String short_text;
	@Pattern(regexp = Utils.VALIDATION_REGEX, message =Utils.Validation_Message )
	String image_mandatory;
	@Pattern(regexp = Utils.VALIDATION_REGEX, message =Utils.Validation_Message )
	String comment_mandatory;
	int created_choice_id;
	boolean is_custom;

	private List<QuestionItem> questionlist = new ArrayList<QuestionItem>();
	
	
	public ParentChoice(ChoiceItem chs) {
		this.choice_pat_id = chs.getChoice_pat_id();
		this.choice_id = chs.getChoice_id()+"";
		this.choice_text=chs.getChoice_text();
		this.default_score_percent=chs.getDefault_score_percent();
//		this.choice_colour=q.getChoice_colour();
//		this.question_no=q.getQuestion_no();
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof ParentChoice) {
			return ((ParentChoice) obj).choice_id == choice_id;
		}
		return false;
	}

	@Override
	public int hashCode() {
		return Integer.parseInt(this.choice_id);
	}
	
	
	public List<QuestionItem> getQuestionlist() {
		return questionlist;
	}
	public void setQuestionlist(List<QuestionItem> questionlist) {
		this.questionlist = questionlist;
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

	public int getScore_type() {
		return score_type;
	}
	public void setScore_type(int score_type) {
		this.score_type = score_type;
	}
	public List<ParentChoice> getChoiceList() {
		return choiceList;
	}
	public void setChoiceList(List<ParentChoice> choiceList) {
		this.choiceList = choiceList;
	}
	
	
	public String getChoice_colour() {
		return choice_colour;
	}
	public void setChoice_colour(String choice_colour) {
		this.choice_colour = choice_colour;
	}

	public String getChoice_text() {
		return choice_text;
	}
	public void setChoice_text(String choice_text) {
		this.choice_text = choice_text;
	}
	public String getDefault_score_percent() {
		return default_score_percent;
	}
	public void setDefault_score_percent(String default_score_percent) {
		this.default_score_percent = default_score_percent;
	}
	public String getActive() {
		return active;
	}
	public void setActive(String active) {
		this.active = active;
	}
	
	public String getGuid() {
		return guid;
	}
	public void setGuid(String guid) {
		this.guid = guid;
	}
	
	public String getChoice_pattern() {
		return choice_pattern;
	}
	public void setChoice_pattern(String choice_pattern) {
		this.choice_pattern = choice_pattern;
	}
	public String getChoice_type() {
		return choice_type;
	}
	public void setChoice_type(String choice_type) {
		this.choice_type = choice_type;
	}
	public String getShort_text() {
		return short_text;
	}
	public void setShort_text(String short_text) {
		this.short_text = short_text;
	}
	public String getImage_mandatory() {
		return image_mandatory;
	}
	public void setImage_mandatory(String image_mandatory) {
		this.image_mandatory = image_mandatory;
	}
	public String getComment_mandatory() {
		return comment_mandatory;
	}
	public void setComment_mandatory(String comment_mandatory) {
		this.comment_mandatory = comment_mandatory;
	}
	/**
	 * @return the created_choice_id
	 */
	public int getCreated_choice_id() {
		return created_choice_id;
	}
	/**
	 * @param created_choice_id the created_choice_id to set
	 */
	public void setCreated_choice_id(int created_choice_id) {
		this.created_choice_id = created_choice_id;
	}
	/**
	 * @return the is_custom
	 */
	public boolean isIs_custom() {
		return is_custom;
	}
	/**
	 * @param is_custom the is_custom to set
	 */
	public void setIs_custom(boolean is_custom) {
		this.is_custom = is_custom;
	}

	/**
	 * @return the question_no
	 */
	public int getQuestion_no() {
		return question_no;
	}

	/**
	 * @param question_no the question_no to set
	 */
	public void setQuestion_no(int question_no) {
		this.question_no = question_no;
	}

	/**
	 * @return the choice_pat_id
	 */
	public int getChoice_pat_id() {
		return choice_pat_id;
	}

	/**
	 * @param choice_pat_id the choice_pat_id to set
	 */
	public void setChoice_pat_id(int choice_pat_id) {
		this.choice_pat_id = choice_pat_id;
	}

	/**
	 * @return the choice_id
	 */
	public String getChoice_id() {
		return choice_id;
	}

	/**
	 * @param choice_id the choice_id to set
	 */
	public void setChoice_id(String choice_id) {
		this.choice_id = choice_id;
	}
	
	

}
