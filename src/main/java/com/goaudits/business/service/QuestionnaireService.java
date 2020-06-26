package com.goaudits.business.service;

import java.util.List;

import com.goaudits.business.entity.Choice;
import com.goaudits.business.entity.Group;
import com.goaudits.business.entity.Previewchoice;
import com.goaudits.business.entity.Question;
import com.goaudits.business.entity.QuestionOrder;
import com.goaudits.business.entity.Questionimage;
import com.goaudits.business.entity.Section;
import com.goaudits.business.entity.SectionGroupClone;
import com.goaudits.business.entity.Tag;
import com.goaudits.business.entity.User;

public interface QuestionnaireService {

	List<Section> getSections(Section section);

	boolean isSectionExist(Section section);

	int addSection(Section section);

	boolean issectionExistInDB(Section section);

	int updateSection(Section section);

	int deleteSection(Section section);

	int deleteGroup(Group group);

	boolean isGroupExistInDB(Group group);

	int updateGroup(Group group);

	boolean isGroupExist(Group group);

	int addGroup(Group group);

	List<Choice> getchoicepatterens(String guid);

	List<Section> getQuestions(Section sec);

	boolean isConditionalChoiceQuestionExist(List<Question> question);

	int addQuestion(List<Question> question);

	boolean choiceChangeConditional(Question question);

	boolean updateQuestion(List<Question> question);

	boolean isConditionalChoiceExist(Question question);

	int changeConditionalChoice(Question question);

	List<Questionimage> getQuestionImage(Question question);

	boolean isAudit(String guid, int client_id, int audit_type_id);

	int orderQuestions(QuestionOrder questionOrder);

	boolean isCustomChoiceExist(List<Choice> choice);

	int addCustomChoice(List<Choice> choice);

	int deleteQuestion(Question question);

	List<Tag> getAllTags(Tag tag);

	List<Previewchoice> getPreviewchoice(Previewchoice previchoice);

	int getQuestionAudit(Question question);

	List<Section> getUserQuestions(Section section);

	List<User> getAdminslist(String guid);

	String getCloudinaryFlag(String guid);

	List<SectionGroupClone> cloneSection(SectionGroupClone section);

	int changeConditionalChoicenew(Question question);

	int getQimagecount(Question question);



}
