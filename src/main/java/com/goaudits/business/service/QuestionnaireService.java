package com.goaudits.business.service;

import java.util.List;

import com.goaudits.business.entity.Choice;
import com.goaudits.business.entity.Group;
import com.goaudits.business.entity.Question;
import com.goaudits.business.entity.Section;

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

	List<Group> getQuestions(Group group);

	boolean isConditionalChoiceQuestionExist(List<Question> question);

	int addQuestion(List<Question> question);

	boolean choiceChangeConditional(Question question);

	boolean updateQuestion(List<Question> question);



}
