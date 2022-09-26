package com.goaudits.business.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.goaudits.business.entity.Choice;
import com.goaudits.business.entity.Group;
import com.goaudits.business.entity.GroupOrder;
import com.goaudits.business.entity.Previewchoice;
import com.goaudits.business.entity.Question;
import com.goaudits.business.entity.QuestionOrder;
import com.goaudits.business.entity.Questionimage;
import com.goaudits.business.entity.S3;
import com.goaudits.business.entity.Section;
import com.goaudits.business.entity.SectionGroupClone;
import com.goaudits.business.entity.SectionItem;
import com.goaudits.business.entity.Tag;
import com.goaudits.business.entity.User;
import com.goaudits.business.service.QuestionnaireService;
import com.goaudits.business.service.S3Service;
import com.goaudits.business.util.GoAuditsException;
import com.goaudits.business.util.Utils;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@Validated
@RequestMapping("/api/setup")
public class Questionnaire {

	private final Logger log = LogManager.getLogger(getClass().getName());
	
	@Autowired
	QuestionnaireService QuestionnaireService;

	@Autowired
	private S3Service s3Service;

	@RequestMapping(value = "/prequestionaudit", method = RequestMethod.POST)
	public ResponseEntity<?> getPreQuestionsaudit(@RequestBody Section section,
			@RequestHeader(name = "Authorization") String token) {

		try {
			if (token != null && token != "" && !token.isEmpty()) {
				token = token.replace("Bearer ", "");
				String guid = Utils.getGuid(token);
				String uid = Utils.getUid(token);
				section.setGuid(guid);
				section.setUid(uid);
			}
			List<Section> sectionlist = QuestionnaireService.getUserQuestions(section);
			return new ResponseEntity<List<Section>>(sectionlist, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Error catched", e);
			return new ResponseEntity<>(new GoAuditsException("Something went wrong"), HttpStatus.EXPECTATION_FAILED);
		}

	}

	@RequestMapping(value = "/section/list", method = RequestMethod.POST)
	public ResponseEntity<?> getSection(@RequestBody Section section,
			@RequestHeader(name = "Authorization") String token) {
		try {
			if(section.getGuid().equals("1e3bff8b-746f-4e58-b72f-361afc0aca50")) {
				section.setActive(true);
			}
			else if (token != null && token != "" && !token.isEmpty()) {
				token = token.replace("Bearer ", "");
				String guid = Utils.getGuid(token);
				String uid = Utils.getUid(token);
				section.setGuid(guid);
				section.setUid(uid);
			}
			List<Section> sectionlist = QuestionnaireService.getSections(section);
			return new ResponseEntity<List<Section>>(sectionlist, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Error catched", e);
			return new ResponseEntity<>(new GoAuditsException("Something went wrong"), HttpStatus.EXPECTATION_FAILED);
		}
	}

	@RequestMapping(value = "/section/add", method = RequestMethod.POST)
	public ResponseEntity<?> addSection(@Valid @RequestBody Section section,
			@RequestHeader(name = "Authorization") String token) {

		try {
			if (token != null && token != "" && !token.isEmpty()) {
				token = token.replace("Bearer ", "");
				String guid = Utils.getGuid(token);
				String uid = Utils.getUid(token);
				section.setGuid(guid);
				section.setUid(uid);
			}

			if (QuestionnaireService.isSectionExist(section)) {
				return new ResponseEntity<>(
						new GoAuditsException("Please provide a different section name, this one already exists"),
						HttpStatus.CONFLICT);
			}

			int section_id = QuestionnaireService.addSection(section);
			section.setSection_id(section_id);
			List<Section> sectionList = new ArrayList<Section>();
			sectionList.add(section);
			return new ResponseEntity<List<Section>>(sectionList, HttpStatus.CREATED);
		} catch (Exception e) {
			log.error("Error catched", e);
			return new ResponseEntity<>(new GoAuditsException("Something went wrong"), HttpStatus.EXPECTATION_FAILED);
		}
	}

	@RequestMapping(value = "/section/update", method = RequestMethod.PUT)
	public ResponseEntity<?> updateSection(@Valid @RequestBody Section section,
			@RequestHeader(name = "Authorization") String token) {

		try {
			if (token != null && token != "" && !token.isEmpty()) {
				token = token.replace("Bearer ", "");
				String guid = Utils.getGuid(token);
				String uid = Utils.getUid(token);
				section.setGuid(guid);
				section.setUid(uid);
			}
			if (!QuestionnaireService.issectionExistInDB(section)) {

				QuestionnaireService.updateSection(section);
				List<Section> sectionList = new ArrayList<Section>();
				sectionList.add(section);
				return new ResponseEntity<List<Section>>(sectionList, HttpStatus.OK);
			}
			return new ResponseEntity<>(
					new GoAuditsException("Please provide a different section name, this one already exists"),
					HttpStatus.CONFLICT);
		} catch (Exception e) {
			log.error("Error catched", e);
			return new ResponseEntity<>(new GoAuditsException("Something went wrong"), HttpStatus.EXPECTATION_FAILED);
		}
	}

	@RequestMapping(value = "/section/delete", method = RequestMethod.POST)
	public ResponseEntity<?> deleteSection(@RequestBody Section section,
			@RequestHeader(name = "Authorization") String token) {

		try {
			if (token != null && token != "" && !token.isEmpty()) {
				token = token.replace("Bearer ", "");
				String guid = Utils.getGuid(token);
				String uid = Utils.getUid(token);
				section.setGuid(guid);
				section.setUid(uid);
			}
			// if (auditConfigService.isAudit(section.getGuid(),
			// section.getClient_id(), section.getAudit_type_id())) {
			// return new ResponseEntity(new GoAuditsException(
			// "Audits are existing so you can't delete Section"),
			// HttpStatus.CONFLICT);
			// }

			QuestionnaireService.deleteSection(section);
			List<Section> sectionList = new ArrayList<Section>();
			sectionList.add(section);
			return new ResponseEntity<List<Section>>(sectionList, HttpStatus.OK);
		} catch (Exception e) {
			log.error("Error catched", e);
			return new ResponseEntity<>(new GoAuditsException("Something went wrong"), HttpStatus.EXPECTATION_FAILED);
		}
	}

	@RequestMapping(value = "/group/add", method = RequestMethod.POST)
	public ResponseEntity<?> addGroup(@Valid @RequestBody Group group, @RequestHeader(name = "Authorization") String token) {

		try {
			if (token != null && token != "" && !token.isEmpty()) {
				token = token.replace("Bearer ", "");
				String guid = Utils.getGuid(token);
				String uid = Utils.getUid(token);
				group.setGuid(guid);
				group.setUid(uid);
			}
			if (QuestionnaireService.isGroupExist(group)) {
				return new ResponseEntity<>(
						new GoAuditsException("Please provide a different subsection name, this one already exists"),
						HttpStatus.CONFLICT);
			}

			int group_id = QuestionnaireService.addGroup(group);
			group.setGroup_id(group_id);
			List<Group> groupList = new ArrayList<Group>();
			groupList.add(group);

			return new ResponseEntity<List<Group>>(groupList, HttpStatus.CREATED);
		} catch (Exception e) {
			log.error("Error catched", e);
			return new ResponseEntity<>(new GoAuditsException("Something went wrong"), HttpStatus.EXPECTATION_FAILED);
		}
	}

	@RequestMapping(value = "/group/update", method = RequestMethod.PUT)
	public ResponseEntity<?> updateGroup(@Valid @RequestBody Group group,
			@RequestHeader(name = "Authorization") String token) {

		try {
			if (token != null && token != "" && !token.isEmpty()) {
				token = token.replace("Bearer ", "");
				String guid = Utils.getGuid(token);
				String uid = Utils.getUid(token);
				group.setGuid(guid);
				group.setUid(uid);
			}
			if (!QuestionnaireService.isGroupExistInDB(group)) {

				QuestionnaireService.updateGroup(group);
				List<Group> groupList = new ArrayList<Group>();
				groupList.add(group);
				return new ResponseEntity<List<Group>>(groupList, HttpStatus.OK);

			}
			return new ResponseEntity<>(
					new GoAuditsException("Please provide a different subsection name, this one already exists"),
					HttpStatus.CONFLICT);
		} catch (Exception e) {
			log.error("Error catched", e);
			return new ResponseEntity<>(new GoAuditsException("Something went wrong"), HttpStatus.EXPECTATION_FAILED);
		}
	}

	@RequestMapping(value = "/group/delete", method = RequestMethod.POST)
	public ResponseEntity<?> deleteGroup(@RequestBody Group group,
			@RequestHeader(name = "Authorization") String token) {

		try {
			if (token != null && token != "" && !token.isEmpty()) {
				token = token.replace("Bearer ", "");
				String guid = Utils.getGuid(token);
				String uid = Utils.getUid(token);
				group.setGuid(guid);
				group.setUid(uid);
			}
			// if (auditConfigService.isAudit(group.getGuid(), group.getClient_id(),
			// group.getAudit_type_id())) {
			// return new ResponseEntity(new GoAuditsException(
			// "Audits are existing so you can't delete Group"),
			// HttpStatus.CONFLICT);
			// }

			QuestionnaireService.deleteGroup(group);
			List<Group> groupList = new ArrayList<Group>();
			groupList.add(group);
			return new ResponseEntity<List<Group>>(groupList, HttpStatus.OK);
		} catch (Exception e) {
			log.error("Error catched", e);
			return new ResponseEntity<>(new GoAuditsException("Something went wrong"), HttpStatus.EXPECTATION_FAILED);
		}
	}

	@RequestMapping(value = "/choicelist/{guid}", method = RequestMethod.GET)
	public ResponseEntity<?> getChoices(@PathVariable("guid") String guid,
			@RequestHeader(name = "Authorization") String token) {
		try {
			if (token != null && token != "" && !token.isEmpty()) {
				token = token.replace("Bearer ", "");
				guid = Utils.getGuid(token);
			}
			List<Choice> choicepatternlist = QuestionnaireService.getchoicepatterens(guid);
			return new ResponseEntity<List<Choice>>(choicepatternlist, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Error catched", e);
			return new ResponseEntity<>(new GoAuditsException("Something went wrong"), HttpStatus.EXPECTATION_FAILED);
		}
	}

	@RequestMapping(value = "/question/list", method = RequestMethod.POST)
	public ResponseEntity<List<Section>> getQuestions(@RequestBody Section sec) {
		List<Section> grouplist = QuestionnaireService.getQuestions(sec);
		return new ResponseEntity<List<Section>>(grouplist, HttpStatus.OK);
	}

	@RequestMapping(value = "/question/list2", method = RequestMethod.POST)
	public ResponseEntity<?> getOpenQuestionsList(@RequestBody Section audit,
			@RequestHeader(name = "Authorization") String token) {
		try {
			if (token != null && token != "" && !token.isEmpty()) {
				token = token.replace("Bearer ", "");
				String guid = Utils.getGuid(token);
				String uid = Utils.getUid(token);
				audit.setGuid(guid);
				audit.setUid(uid);
			}
			List<SectionItem> sectionList = QuestionnaireService.getQuestionList(audit);
			return new ResponseEntity<List<SectionItem>>(sectionList, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Error catched", e);
			return new ResponseEntity<>(new GoAuditsException("Something went wrong"), HttpStatus.EXPECTATION_FAILED);
		}
	}

	@RequestMapping(value = "/question/add", method = RequestMethod.POST)
	public ResponseEntity<?> addQuestion(@Valid @RequestBody List<Question> question,
			@RequestHeader(name = "Authorization") String token) {

		try {
			if (token != null && token != "" && !token.isEmpty()) {
				token = token.replace("Bearer ", "");
				String guid = Utils.getGuid(token);
				String uid = Utils.getUid(token);
				question.get(0).setGuid(guid);
				question.get(0).setUid(uid);
			}
//		if (question.get(0).isConditionnew()) {
//			if (QuestionnaireService.isConditionalChoiceQuestionExist(question)) {
//				return new ResponseEntity<>(new GoAuditsException("Response already exists"), HttpStatus.CONFLICT);
//			}
//		}

			int qno = QuestionnaireService.addQuestion(question);
			S3 s3 = new S3();
			s3.setClient_name(question.get(0).getClient_name());
			s3.setAudit_type_name(question.get(0).getAudit_type_name());
			s3.setClient_id(question.get(0).getClient_id() + "");
			s3.setAudit_type_id(question.get(0).getAudit_type_id() + "");
			s3.setQuestion_no(qno + "");
			s3.setGuid(question.get(0).getGuid());
			s3.setPage("question");
			S3 params = s3Service.GetPayload(s3);
			params.setQuestion_no(qno + "");
			params.setClient_id(question.get(0).getClient_id() + "");
			params.setAudit_type_id(question.get(0).getAudit_type_id() + "");
			List<S3> s3List = new ArrayList<S3>();
			s3List.add(params);
			return new ResponseEntity<List<S3>>(s3List, HttpStatus.CREATED);
		} catch (Exception e) {
			log.error("Error catched", e);
			return new ResponseEntity<>(new GoAuditsException("Something went wrong"), HttpStatus.EXPECTATION_FAILED);
		}
	}

	@RequestMapping(value = "/question/update", method = RequestMethod.PUT)
	public ResponseEntity<?> updateQuestion(@Valid @RequestBody List<Question> question,
			@RequestHeader(name = "Authorization") String token) {

		try {
			if (token != null && token != "" && !token.isEmpty()) {
				token = token.replace("Bearer ", "");
				String guid = Utils.getGuid(token);
				String uid = Utils.getUid(token);
				question.get(0).setGuid(guid);
				question.get(0).setUid(uid);
			}
			if (QuestionnaireService.choiceChangeConditional(question.get(0))) {
				return new ResponseEntity<>(
						new GoAuditsException(
								"Conditional questions are configured, So you can't update choice pattern"),
						HttpStatus.NOT_FOUND);
			}

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
			log.error("Error catched", e);
			return new ResponseEntity<>(new GoAuditsException("Something went wrong"), HttpStatus.EXPECTATION_FAILED);
		}
	}

	@RequestMapping(value = "/question/changechoice", method = RequestMethod.POST)
	public ResponseEntity<?> changeConditinalChoice(@Valid @RequestBody Question question,
			@RequestHeader(name = "Authorization") String token) {

		try {
			if (token != null && token != "" && !token.isEmpty()) {
				token = token.replace("Bearer ", "");
				String guid = Utils.getGuid(token);
				String uid = Utils.getUid(token);
				question.setGuid(guid);
				question.setUid(uid);
			}
			if (QuestionnaireService.isConditionalChoiceExist(question)) {
				return new ResponseEntity<>(new GoAuditsException("Response already exists"), HttpStatus.CONFLICT);
			}

			QuestionnaireService.changeConditionalChoice(question);
			List<Question> QuestionList = new ArrayList<Question>();
			QuestionList.add(question);
			return new ResponseEntity<List<Question>>(QuestionList, HttpStatus.CREATED);
		} catch (Exception e) {
			log.error("Error catched", e);
			return new ResponseEntity<>(new GoAuditsException("Something went wrong"), HttpStatus.EXPECTATION_FAILED);
		}
	}

	@RequestMapping(value = "/question/changecondchoice", method = RequestMethod.POST)
	public ResponseEntity<?> chngeConditinalChoice(@Valid @RequestBody Question question,
			@RequestHeader(name = "Authorization") String token) {

		try {
			if (token != null && token != "" && !token.isEmpty()) {
				token = token.replace("Bearer ", "");
				String guid = Utils.getGuid(token);
				String uid = Utils.getUid(token);
				question.setGuid(guid);
				question.setUid(uid);
			}
//		if (QuestionnaireService.isConditionalChoiceExist(question)) {
//			return new ResponseEntity<>(new GoAuditsException("Response already exists"), HttpStatus.CONFLICT);
//		}

			QuestionnaireService.changeConditionalChoicenew(question);
			List<Question> QuestionList = new ArrayList<Question>();
			QuestionList.add(question);
			return new ResponseEntity<List<Question>>(QuestionList, HttpStatus.CREATED);
		} catch (Exception e) {
			log.error("Error catched", e);
			return new ResponseEntity<>(new GoAuditsException("Something went wrong"), HttpStatus.EXPECTATION_FAILED);
		}
	}

	@RequestMapping(value = "/question/image", method = RequestMethod.POST)
	public ResponseEntity<?> getQuestionImage(@RequestBody Question question,
			@RequestHeader(name = "Authorization") String token) {
		try {
			if (token != null && token != "" && !token.isEmpty()) {
				token = token.replace("Bearer ", "");
				String guid = Utils.getGuid(token);
				String uid = Utils.getUid(token);
				question.setGuid(guid);
				question.setUid(uid);
			}
			List<Questionimage> questionImagelist = QuestionnaireService.getQuestionImage(question);
			return new ResponseEntity<List<Questionimage>>(questionImagelist, HttpStatus.OK);
		} catch (Exception e) {
			log.error("Error catched", e);
			return new ResponseEntity<>(new GoAuditsException("Something went wrong"), HttpStatus.EXPECTATION_FAILED);
		}
	}

	@RequestMapping(value = "/question/order", method = RequestMethod.POST)
	public ResponseEntity<?> questionOrder(@Valid @RequestBody QuestionOrder questionOrder,
			@RequestHeader(name = "Authorization") String token) {

		try {
			if (token != null && token != "" && !token.isEmpty()) {
				token = token.replace("Bearer ", "");
				String guid = Utils.getGuid(token);
				String uid = Utils.getUid(token);
				questionOrder.setGuid(guid);
				questionOrder.setUid(uid);
			}
			if (QuestionnaireService.isAudit(questionOrder.getGuid(), questionOrder.getClient_id(),
					questionOrder.getAudit_type_id())
					&& questionOrder.getDraggroup_id() != questionOrder.getDropgroup_id()) {
				return new ResponseEntity<>(new GoAuditsException("Audits are existing so you can't change the order"),
						HttpStatus.CONFLICT);
			} else {

				QuestionnaireService.orderQuestions(questionOrder);
				List<QuestionOrder> qOrderList = new ArrayList<QuestionOrder>();
				qOrderList.add(questionOrder);
				return new ResponseEntity<List<QuestionOrder>>(qOrderList, HttpStatus.OK);
			}
		} catch (Exception e) {
			log.error("Error catched", e);
			return new ResponseEntity<>(new GoAuditsException("Something went wrong"), HttpStatus.EXPECTATION_FAILED);
		}
	}

	@RequestMapping(value = "/group/order", method = RequestMethod.POST)
	public ResponseEntity<?> groupOrder(@Valid @RequestBody GroupOrder grouporder,
			@RequestHeader(name = "Authorization") String token) {

		try {
			if (token != null && token != "" && !token.isEmpty()) {
				token = token.replace("Bearer ", "");
				String guid = Utils.getGuid(token);
				String uid = Utils.getUid(token);
				grouporder.setGuid(guid);
				grouporder.setUid(uid);
			}
			QuestionnaireService.groupOrder(grouporder);
			List<GroupOrder> qOrderList = new ArrayList<GroupOrder>();
			qOrderList.add(grouporder);
			return new ResponseEntity<List<GroupOrder>>(qOrderList, HttpStatus.OK);
		} catch (Exception e) {
			log.error("Error catched", e);
			return new ResponseEntity<>(new GoAuditsException("Something went wrong"), HttpStatus.EXPECTATION_FAILED);
		}
	}

	@RequestMapping(value = "/customchoice/add", method = RequestMethod.POST)
	public ResponseEntity<?> addCustomchoice(@Valid @RequestBody List<Choice> choice,
			@RequestHeader(name = "Authorization") String token) {

		try {
			if (token != null && token != "" && !token.isEmpty()) {
				token = token.replace("Bearer ", "");
				String guid = Utils.getGuid(token);
				choice.get(0).setGuid(guid);
			}
			if (QuestionnaireService.isCustomChoiceExist(choice)) {
				return new ResponseEntity<>(new GoAuditsException("choices cannot be added, already exists"),
						HttpStatus.CONFLICT);
			}

			int choice_pat_id = QuestionnaireService.addCustomChoice(choice);
			choice.get(0).setChoice_pat_id(choice_pat_id);
			return new ResponseEntity<List<Choice>>(choice, HttpStatus.CREATED);
		} catch (Exception e) {
			log.error("Error catched", e);
			return new ResponseEntity<>(new GoAuditsException("Something went wrong"), HttpStatus.EXPECTATION_FAILED);
		}
	}

	@RequestMapping(value = "/question/delete", method = RequestMethod.POST)
	public ResponseEntity<?> deleteQuestion(@RequestBody Question question,
			@RequestHeader(name = "Authorization") String token) {

		try {
			if (token != null && token != "" && !token.isEmpty()) {
				token = token.replace("Bearer ", "");
				String guid = Utils.getGuid(token);
				String uid = Utils.getUid(token);
				question.setGuid(guid);
				question.setUid(uid);
			}
			// if (auditConfigService.isAudit(question.getGuid(),
			// question.getClient_id(), question.getAudit_type_id())) {
			// return new ResponseEntity(new GoAuditsException(
			// "Audits are existing so you can't delete Question"),
			// HttpStatus.CONFLICT);
			// }

			QuestionnaireService.deleteQuestion(question);
			List<Question> QuestionList = new ArrayList<Question>();
			QuestionList.add(question);

			return new ResponseEntity<List<Question>>(QuestionList, HttpStatus.OK);
		} catch (Exception e) {
			log.error("Error catched", e);
			return new ResponseEntity<>(new GoAuditsException("Something went wrong"), HttpStatus.EXPECTATION_FAILED);
		}
	}

	@RequestMapping(value = "/tag/list", method = RequestMethod.POST)
	public ResponseEntity<?> getTag(@RequestBody Tag tag, @RequestHeader(name = "Authorization") String token) {

		try {
			if (token != null && token != "" && !token.isEmpty()) {
				token = token.replace("Bearer ", "");
				String guid = Utils.getGuid(token);
				String uid = Utils.getUid(token);
				tag.setGuid(guid);
				tag.setUid(uid);
			}
			List<Tag> taglist = QuestionnaireService.getAllTags(tag);

			return new ResponseEntity<List<Tag>>(taglist, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Error catched", e);
			return new ResponseEntity<>(new GoAuditsException("Something went wrong"), HttpStatus.EXPECTATION_FAILED);
		}
	}

	@RequestMapping(value = "/preview/list", method = RequestMethod.POST)
	public ResponseEntity<?> getPreview(@RequestBody Previewchoice previchoice,
			@RequestHeader(name = "Authorization") String token) {
		try {
			if (token != null && token != "" && !token.isEmpty()) {
				token = token.replace("Bearer ", "");
				String guid = Utils.getGuid(token);
				String uid = Utils.getUid(token);
				previchoice.setGuid(guid);
				previchoice.setUid(uid);
			}
			List<Previewchoice> previewchoicelist = QuestionnaireService.getPreviewchoice(previchoice);
			return new ResponseEntity<List<Previewchoice>>(previewchoicelist, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Error catched", e);
			return new ResponseEntity<>(new GoAuditsException("Something went wrong"), HttpStatus.EXPECTATION_FAILED);
		}
	}

	@RequestMapping(value = "/question/audit", method = RequestMethod.POST)
	public ResponseEntity<?> getQuestionauditcount(@RequestBody Question question,
			@RequestHeader(name = "Authorization") String token) {
		try {
			if (token != null && token != "" && !token.isEmpty()) {
				token = token.replace("Bearer ", "");
				String guid = Utils.getGuid(token);
				String uid = Utils.getUid(token);
				question.setGuid(guid);
				question.setUid(uid);
			}
			int audits_count = QuestionnaireService.getQuestionAudit(question);
			List<Integer> auditList = new ArrayList<Integer>();
			auditList.add(audits_count);
			return new ResponseEntity<List<Integer>>(auditList, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Error catched", e);
			return new ResponseEntity<>(new GoAuditsException("Something went wrong"), HttpStatus.EXPECTATION_FAILED);
		}
	}

	@RequestMapping(value = "/users/admin/{guid}", method = RequestMethod.GET)
	public ResponseEntity<?> getUserslist(@PathVariable("guid") String guid,
			@RequestHeader(name = "Authorization") String token) {
		try {
			if (token != null && token != "" && !token.isEmpty()) {
				token = token.replace("Bearer ", "");
				guid = Utils.getGuid(token);
			}
			List<User> Adminslist = QuestionnaireService.getAdminslist(guid);
			return new ResponseEntity<List<User>>(Adminslist, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Error catched", e);
			return new ResponseEntity<>(new GoAuditsException("Something went wrong"), HttpStatus.EXPECTATION_FAILED);
		}
	}

	@RequestMapping(value = "/question/imgcount", method = RequestMethod.POST)
	public ResponseEntity<?> getqimgcouunt(@RequestBody Question question,
			@RequestHeader(name = "Authorization") String token) {

		try {
			if (token != null && token != "" && !token.isEmpty()) {
				token = token.replace("Bearer ", "");
				String guid = Utils.getGuid(token);
				String uid = Utils.getUid(token);
				question.setGuid(guid);
				question.setUid(uid);
			}
			int qimagecount = QuestionnaireService.getQimagecount(question);
			List<Integer> imgcount = new ArrayList<Integer>();
			imgcount.add(qimagecount);
			return new ResponseEntity<List<Integer>>(imgcount, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Error catched", e);
			return new ResponseEntity<>(new GoAuditsException("Something went wrong"), HttpStatus.EXPECTATION_FAILED);
		}
	}

	@RequestMapping(value = "questionimg/signature", method = RequestMethod.POST)
	public ResponseEntity<?> GetQuesImagSig(@RequestBody Question question,
			@RequestHeader(name = "Authorization") String token) {

		try {
			if (token != null && token != "" && !token.isEmpty()) {
				token = token.replace("Bearer ", "");
				String guid = Utils.getGuid(token);
				String uid = Utils.getUid(token);
				question.setGuid(guid);
				question.setUid(uid);
			}
			S3 s3 = new S3();
			s3.setClient_id(question.getClient_id() + "");
			s3.setAudit_type_id(question.getAudit_type_id() + "");
			s3.setGuid(question.getGuid());
			s3.setPage("question");
			S3 params = s3Service.GetPayload(s3);
			params.setClient_id(question.getClient_id() + "");
			params.setAudit_type_id(question.getAudit_type_id() + "");
			return new ResponseEntity<S3>(params, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Error catched", e);
			return new ResponseEntity<>(new GoAuditsException("Something went wrong"), HttpStatus.EXPECTATION_FAILED);
		}
	}

	@RequestMapping(value = "getCloudinaryFlag/{guid}", method = RequestMethod.GET)
	public ResponseEntity<?> getCloudinaryFlag(@PathVariable("guid") String guid,@RequestHeader(name = "Authorization") String token
) {
		try {
			if (token != null && token != "" && !token.isEmpty()) {
				token = token.replace("Bearer ", "");
				 guid = Utils.getGuid(token);
			}
		String cloudflag = QuestionnaireService.getCloudinaryFlag(guid);
		List<String> flag = new ArrayList<String>();
		flag.add(cloudflag);
		return new ResponseEntity<List<String>>(flag, HttpStatus.OK);
		}catch (Exception e) {
			e.printStackTrace();
			log.error("Error catched", e);
			return new ResponseEntity<>(new GoAuditsException("Something went wrong"), HttpStatus.EXPECTATION_FAILED);
		}
		}

	@RequestMapping(value = "/section/clone", method = RequestMethod.POST)
	public ResponseEntity<?> cloneSection(@Valid @RequestBody SectionGroupClone section, Section section1, Group group,
			@RequestHeader(name = "Authorization") String token) {

		try {
			if (token != null && token != "" && !token.isEmpty()) {
				token = token.replace("Bearer ", "");
				String guid = Utils.getGuid(token);
				String uid = Utils.getUid(token);
				section.setGuid(guid);
				section.setUid(uid);
			}
			if (section.getGroup_id() == 0) {
				section1.setGuid(section.getGuid());
				section1.setClient_id(section.getClient_id());
				section1.setAudit_type_id(section.getAudit_type_id());
				section1.setSection_name(section.getNew_section_name());
				if (QuestionnaireService.isSectionExist(section1)) {
					return new ResponseEntity<>(new GoAuditsException("Section cannot be added, already exists"),
							HttpStatus.CONFLICT);
				}

			} else {
				group.setGuid(section.getGuid());
				group.setClient_id(section.getClient_id());
				group.setAudit_type_id(section.getAudit_type_id());
				group.setSection_id(section.getSection_id());
				group.setGroup_name(section.getNew_group_name());

				if (QuestionnaireService.isGroupExist(group)) {
					return new ResponseEntity<>(new GoAuditsException("Group cannot be added, already exists"),
							HttpStatus.CONFLICT);
				}
			}

			List<SectionGroupClone> sectionlist = QuestionnaireService.cloneSection(section);
			return new ResponseEntity<List<SectionGroupClone>>(sectionlist, HttpStatus.OK);
		} catch (Exception e) {
			log.error("Error catched", e);
			return new ResponseEntity<>(new GoAuditsException("Something went wrong"), HttpStatus.EXPECTATION_FAILED);
		}

	}
	
	@RequestMapping(value = "/failedchoice/flag", method = RequestMethod.GET)
	public ResponseEntity<?> failedChoiceFlag(@RequestHeader(name = "Authorization") String token) {
		String guid = null;
		try {
			if (token != null && token != "" && !token.isEmpty()) {
				token = token.replace("Bearer ", "");
				 guid = Utils.getGuid(token);
			}
	
			boolean flag = QuestionnaireService.getFailedChoiceFlag(guid);
			List<Boolean> failflag=new ArrayList<Boolean>();
			failflag.add(flag);
			return new ResponseEntity<List<Boolean>>(failflag, HttpStatus.OK);
		} catch (Exception e) {
			log.error("Error catched", e);
			return new ResponseEntity<>(new GoAuditsException("Something went wrong"), HttpStatus.EXPECTATION_FAILED);
		}

	}
	
	@RequestMapping(value = "/question/sections", method = RequestMethod.POST)
	public ResponseEntity<?> getSections(@RequestBody Section sec) {
		try {
		List<Section> sectionlist = QuestionnaireService.getSections(sec);
		return new ResponseEntity<List<Section>>(sectionlist, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Error catched", e);
			return new ResponseEntity<>(new GoAuditsException("Something went wrong"), HttpStatus.EXPECTATION_FAILED);
		}
	}
	
	@RequestMapping(value = "/question/groups", method = RequestMethod.POST)
	public ResponseEntity<?> getGroups(@RequestBody Group grp) {
		try {
		List<Group> grouplist = QuestionnaireService.getGroups(grp);
		return new ResponseEntity<List<Group>>(grouplist, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Error catched", e);
			return new ResponseEntity<>(new GoAuditsException("Something went wrong"), HttpStatus.EXPECTATION_FAILED);
		}
	}
	@RequestMapping(value = "/question/allQuestions", method = RequestMethod.POST)
	public ResponseEntity<?> getAllQuestions(@RequestBody Group grp) {
		try {
		List<Question> questionsList = QuestionnaireService.getAllQuestions(grp);
		return new ResponseEntity<List<Question>>(questionsList, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Error catched", e);
			return new ResponseEntity<>(new GoAuditsException("Something went wrong"), HttpStatus.EXPECTATION_FAILED);
		}
	}
	
}