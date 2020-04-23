package com.goaudits.business.controller;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.goaudits.business.entity.Choice;
import com.goaudits.business.entity.Group;
import com.goaudits.business.entity.Question;
import com.goaudits.business.entity.S3;
import com.goaudits.business.entity.Section;
import com.goaudits.business.service.QuestionnaireService;
import com.goaudits.business.util.GoAuditsException;
import com.goaudits.business.service.S3Service;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/setup")
public class Questionnaire {

	@Autowired
	QuestionnaireService QuestionnaireService;

	@Autowired
	private S3Service s3Service;

	@RequestMapping(value = "/section/list", method = RequestMethod.POST)
	public ResponseEntity<List<Section>> getSection(@RequestBody Section section) {
		List<Section> sectionlist = QuestionnaireService.getSections(section);
		return new ResponseEntity<List<Section>>(sectionlist, HttpStatus.OK);
	}

	@RequestMapping(value = "/section/add", method = RequestMethod.POST)
	public ResponseEntity<?> addSection(@RequestBody Section section) {

		if (QuestionnaireService.isSectionExist(section)) {
			return new ResponseEntity<>(new GoAuditsException("Section cannot be added, already exists"),
					HttpStatus.CONFLICT);
		}
		try {
			int section_id = QuestionnaireService.addSection(section);
			section.setSection_id(section_id);
			List<Section> sectionList = new ArrayList<Section>();
			sectionList.add(section);
			return new ResponseEntity<List<Section>>(sectionList, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(new GoAuditsException("Something went wrong"), HttpStatus.EXPECTATION_FAILED);
		}
	}

	@RequestMapping(value = "/section/update", method = RequestMethod.PUT)
	public ResponseEntity<?> updateSection(@RequestBody Section section) {

		if (!QuestionnaireService.issectionExistInDB(section)) {
			try {
				QuestionnaireService.updateSection(section);
				List<Section> sectionList = new ArrayList<Section>();
				sectionList.add(section);
				return new ResponseEntity<List<Section>>(sectionList, HttpStatus.OK);
			} catch (Exception e) {
				return new ResponseEntity<>(new GoAuditsException("Something went wrong"),
						HttpStatus.EXPECTATION_FAILED);
			}
		}
		return new ResponseEntity<>(new GoAuditsException("Section cannot be updated, already exists"),
				HttpStatus.NOT_FOUND);
	}

	@RequestMapping(value = "/section/delete", method = RequestMethod.POST)
	public ResponseEntity<?> deleteSection(@RequestBody Section section) {

		// if (auditConfigService.isAudit(section.getGuid(),
		// section.getClient_id(), section.getAudit_type_id())) {
		// return new ResponseEntity(new GoAuditsException(
		// "Audits are existing so you can't delete Section"),
		// HttpStatus.CONFLICT);
		// }
		try {
			QuestionnaireService.deleteSection(section);
			List<Section> sectionList = new ArrayList<Section>();
			sectionList.add(section);
			return new ResponseEntity<List<Section>>(sectionList, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(new GoAuditsException(e.getMessage()), HttpStatus.EXPECTATION_FAILED);
		}
	}

	@RequestMapping(value = "/group/add", method = RequestMethod.POST)
	public ResponseEntity<?> addGroup(@RequestBody Group group) {

		if (QuestionnaireService.isGroupExist(group)) {
			return new ResponseEntity<>(new GoAuditsException("Group cannot be added, already exists"),
					HttpStatus.CONFLICT);
		}
		try {
			int group_id = QuestionnaireService.addGroup(group);
			group.setGroup_id(group_id);
			List<Group> groupList = new ArrayList<Group>();
			groupList.add(group);

			return new ResponseEntity<List<Group>>(groupList, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(new GoAuditsException(e.getMessage()), HttpStatus.EXPECTATION_FAILED);
		}
	}

	@RequestMapping(value = "/group/update", method = RequestMethod.PUT)
	public ResponseEntity<?> updateGroup(@RequestBody Group group) {

		if (!QuestionnaireService.isGroupExistInDB(group)) {
			try {
				QuestionnaireService.updateGroup(group);
				List<Group> groupList = new ArrayList<Group>();
				groupList.add(group);
				return new ResponseEntity<List<Group>>(groupList, HttpStatus.OK);
			} catch (Exception e) {
				return new ResponseEntity<>(new GoAuditsException(e.getMessage()), HttpStatus.EXPECTATION_FAILED);
			}
		}
		return new ResponseEntity<>(new GoAuditsException("Group cannot be updated, already exists"),
				HttpStatus.NOT_FOUND);
	}

	@RequestMapping(value = "/group/delete", method = RequestMethod.POST)
	public ResponseEntity<?> deleteGroup(@RequestBody Group group) {

		// if (auditConfigService.isAudit(group.getGuid(), group.getClient_id(),
		// group.getAudit_type_id())) {
		// return new ResponseEntity(new GoAuditsException(
		// "Audits are existing so you can't delete Group"),
		// HttpStatus.CONFLICT);
		// }
		try {
			QuestionnaireService.deleteGroup(group);
			List<Group> groupList = new ArrayList<Group>();
			groupList.add(group);
			return new ResponseEntity<List<Group>>(groupList, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(new GoAuditsException(e.getMessage()), HttpStatus.EXPECTATION_FAILED);
		}
	}

	@RequestMapping(value = "/choicelist/{guid}", method = RequestMethod.GET)
	public ResponseEntity<List<Choice>> getChoices(@PathVariable("guid") String guid) {
		List<Choice> choicepatternlist = QuestionnaireService.getchoicepatterens(guid);
		return new ResponseEntity<List<Choice>>(choicepatternlist, HttpStatus.OK);
	}

	@RequestMapping(value = "/question/list", method = RequestMethod.GET)
	public ResponseEntity<List<Group>> getQuestions(Group group) {
		List<Group> grouplist = QuestionnaireService.getQuestions(group);
		return new ResponseEntity<List<Group>>(grouplist, HttpStatus.OK);
	}

	@RequestMapping(value = "/question/add", method = RequestMethod.POST)
	public ResponseEntity<?> addQuestion(@RequestBody List<Question> question) {

		if (question.get(0).isConditionnew()) {
			if (QuestionnaireService.isConditionalChoiceQuestionExist(question)) {
				return new ResponseEntity<>(new GoAuditsException("Response already exists"), HttpStatus.CONFLICT);
			}
		}

		try {
			int qno = QuestionnaireService.addQuestion(question);
			S3 s3 = new S3();
			s3.setClient_name(question.get(0).getClient_name());
			s3.setAudit_type_name(question.get(0).getAudit_type_name());
			s3.setClient_id(question.get(0).getClient_id() + "");
			s3.setAudit_type_id(question.get(0).getAudit_type_id() + "");
			s3.setQuestion_no(qno + "");
			s3.setPage("question");
			S3 params = s3Service.GetPayload(s3);
			params.setQuestion_no(qno + "");
			params.setClient_id(question.get(0).getClient_id() + "");
			params.setAudit_type_id(question.get(0).getAudit_type_id() + "");
			List<S3> s3List = new ArrayList<S3>();
			s3List.add(params);
			return new ResponseEntity<List<S3>>(s3List, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(new GoAuditsException(e.getMessage()), HttpStatus.EXPECTATION_FAILED);
		}
	}

	@RequestMapping(value = "/question/update", method = RequestMethod.PUT)
	public ResponseEntity<?> updateQuestion(@RequestBody List<Question> question) {

		if (QuestionnaireService.choiceChangeConditional(question.get(0))) {
			return new ResponseEntity<>(
					new GoAuditsException("Conditional questions are configured, So you can't update choice pattern"),
					HttpStatus.NOT_FOUND);
		}

		try {
			QuestionnaireService.updateQuestion(question);
			S3 s3 = new S3();
			s3.setClient_name(question.get(0).getClient_name());
			s3.setAudit_type_name(question.get(0).getAudit_type_name());
			s3.setClient_id(question.get(0).getClient_id() + "");
			s3.setAudit_type_id(question.get(0).getAudit_type_id() + "");
			s3.setQuestion_no(question.get(0).getQuestion_no() + "");
			s3.setPage("question");
			S3 params = s3Service.GetPayload(s3);
			params.setQuestion_no(question.get(0).getQuestion_no() + "");
			params.setClient_id(question.get(0).getClient_id() + "");
			params.setAudit_type_id(question.get(0).getAudit_type_id() + "");
			List<S3> s3List = new ArrayList<S3>();
			s3List.add(params);
			return new ResponseEntity<List<S3>>(s3List, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(new GoAuditsException(e.getMessage()), HttpStatus.EXPECTATION_FAILED);
		}
	}

}
