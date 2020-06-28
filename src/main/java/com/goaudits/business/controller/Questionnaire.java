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
import com.goaudits.business.entity.Previewchoice;
import com.goaudits.business.entity.Question;
import com.goaudits.business.entity.QuestionOrder;
import com.goaudits.business.entity.Questionimage;
import com.goaudits.business.entity.S3;
import com.goaudits.business.entity.Section;
import com.goaudits.business.entity.SectionGroupClone;
import com.goaudits.business.entity.Tag;
import com.goaudits.business.entity.User;
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

	@RequestMapping(value = "/prequestionaudit", method = RequestMethod.POST)
	public ResponseEntity<List<Section>> getPreQuestionsaudit(@RequestBody Section section) {

		List<Section> sectionlist = QuestionnaireService.getUserQuestions(section);
		return new ResponseEntity<List<Section>>(sectionlist, HttpStatus.OK);

	}

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
				HttpStatus.CONFLICT);
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

	@RequestMapping(value = "/question/list", method = RequestMethod.POST)
	public ResponseEntity<List<Section>> getQuestions(@RequestBody Section sec) {
		List<Section> grouplist = QuestionnaireService.getQuestions(sec);
		return new ResponseEntity<List<Section>>(grouplist, HttpStatus.OK);
	}

	@RequestMapping(value = "/question/add", method = RequestMethod.POST)
	public ResponseEntity<?> addQuestion(@RequestBody List<Question> question) {

//		if (question.get(0).isConditionnew()) {
//			if (QuestionnaireService.isConditionalChoiceQuestionExist(question)) {
//				return new ResponseEntity<>(new GoAuditsException("Response already exists"), HttpStatus.CONFLICT);
//			}
//		}

		try {
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

	@RequestMapping(value = "/question/changechoice", method = RequestMethod.POST)
	public ResponseEntity<?> changeConditinalChoice(@RequestBody Question question) {

		if (QuestionnaireService.isConditionalChoiceExist(question)) {
			return new ResponseEntity<>(new GoAuditsException("Response already exists"), HttpStatus.CONFLICT);
		}

		try {
			QuestionnaireService.changeConditionalChoice(question);
			List<Question> QuestionList = new ArrayList<Question>();
			QuestionList.add(question);
			return new ResponseEntity<List<Question>>(QuestionList, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(new GoAuditsException(e.getMessage()), HttpStatus.EXPECTATION_FAILED);
		}
	}

	@RequestMapping(value = "/question/changecondchoice", method = RequestMethod.POST)
	public ResponseEntity<?> chngeConditinalChoice(@RequestBody Question question) {

//		if (QuestionnaireService.isConditionalChoiceExist(question)) {
//			return new ResponseEntity<>(new GoAuditsException("Response already exists"), HttpStatus.CONFLICT);
//		}

		try {
			QuestionnaireService.changeConditionalChoicenew(question);
			List<Question> QuestionList = new ArrayList<Question>();
			QuestionList.add(question);
			return new ResponseEntity<List<Question>>(QuestionList, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(new GoAuditsException(e.getMessage()), HttpStatus.EXPECTATION_FAILED);
		}
	}

	@RequestMapping(value = "/question/image", method = RequestMethod.POST)
	public ResponseEntity<?> getQuestionImage(@RequestBody Question question) {
		try {
			List<Questionimage> questionImagelist = QuestionnaireService.getQuestionImage(question);
			return new ResponseEntity<List<Questionimage>>(questionImagelist, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(new GoAuditsException("Something went wrong"), HttpStatus.EXPECTATION_FAILED);
		}
	}

	@RequestMapping(value = "/question/order", method = RequestMethod.POST)
	public ResponseEntity<?> questionOrder(@RequestBody QuestionOrder questionOrder) {

		if (QuestionnaireService.isAudit(questionOrder.getGuid(), questionOrder.getClient_id(),
				questionOrder.getAudit_type_id())
				&& questionOrder.getDraggroup_id() != questionOrder.getDropgroup_id()) {
			return new ResponseEntity<>(new GoAuditsException("Audits are existing so you can't change the order"),
					HttpStatus.CONFLICT);
		} else {
			try {
				QuestionnaireService.orderQuestions(questionOrder);
				List<QuestionOrder> qOrderList = new ArrayList<QuestionOrder>();
				qOrderList.add(questionOrder);
				return new ResponseEntity<List<QuestionOrder>>(qOrderList, HttpStatus.OK);
			} catch (Exception e) {
				return new ResponseEntity<>(new GoAuditsException(e.getMessage()), HttpStatus.EXPECTATION_FAILED);
			}
		}
	}

	@RequestMapping(value = "/customchoice/add", method = RequestMethod.POST)
	public ResponseEntity<?> addCustomchoice(@RequestBody List<Choice> choice) {
		if (QuestionnaireService.isCustomChoiceExist(choice)) {
			return new ResponseEntity<>(new GoAuditsException("choices cannot be added, already exists"),
					HttpStatus.CONFLICT);
		}
		try {
			int choice_pat_id = QuestionnaireService.addCustomChoice(choice);
			choice.get(0).setChoice_pat_id(choice_pat_id);
			return new ResponseEntity<List<Choice>>(choice, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(new GoAuditsException(e.getMessage()), HttpStatus.EXPECTATION_FAILED);
		}
	}

	@RequestMapping(value = "/question/delete", method = RequestMethod.POST)
	public ResponseEntity<?> deleteQuestion(@RequestBody Question question) {

		// if (auditConfigService.isAudit(question.getGuid(),
		// question.getClient_id(), question.getAudit_type_id())) {
		// return new ResponseEntity(new GoAuditsException(
		// "Audits are existing so you can't delete Question"),
		// HttpStatus.CONFLICT);
		// }
		try {
			QuestionnaireService.deleteQuestion(question);
			List<Question> QuestionList = new ArrayList<Question>();
			QuestionList.add(question);

			return new ResponseEntity<List<Question>>(QuestionList, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(new GoAuditsException(e.getMessage()), HttpStatus.EXPECTATION_FAILED);
		}
	}

	@RequestMapping(value = "/tag/list", method = RequestMethod.POST)
	public ResponseEntity<List<Tag>> getTag(@RequestBody Tag tag) {

		List<Tag> taglist = QuestionnaireService.getAllTags(tag);

		return new ResponseEntity<List<Tag>>(taglist, HttpStatus.OK);
	}

	@RequestMapping(value = "/preview/list", method = RequestMethod.POST)
	public ResponseEntity<List<Previewchoice>> getPreview(@RequestBody Previewchoice previchoice) {
		List<Previewchoice> previewchoicelist = QuestionnaireService.getPreviewchoice(previchoice);
		return new ResponseEntity<List<Previewchoice>>(previewchoicelist, HttpStatus.OK);
	}

	@RequestMapping(value = "/question/audit", method = RequestMethod.POST)
	public ResponseEntity<?> getQuestionauditcount(@RequestBody Question question) {
		int audits_count = QuestionnaireService.getQuestionAudit(question);
		List<Integer> auditList = new ArrayList<Integer>();
		auditList.add(audits_count);
		return new ResponseEntity<List<Integer>>(auditList, HttpStatus.OK);
	}

	@RequestMapping(value = "/users/admin/{guid}", method = RequestMethod.GET)
	public ResponseEntity<List<User>> getUserslist(@PathVariable("guid") String guid) {
		List<User> Adminslist = QuestionnaireService.getAdminslist(guid);
		return new ResponseEntity<List<User>>(Adminslist, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/question/imgcount", method = RequestMethod.POST)
	public ResponseEntity<?> getqimgcouunt(@RequestBody Question question) {
		int qimagecount = QuestionnaireService.getQimagecount(question);
		List<Integer> imgcount=new ArrayList<Integer>();
		imgcount.add(qimagecount);
		return new ResponseEntity<List<Integer>>(imgcount, HttpStatus.OK);
	}

	@RequestMapping(value = "questionimg/signature", method = RequestMethod.POST)
	public ResponseEntity<S3> GetQuesImagSig(@RequestBody Question question) {
		S3 s3 = new S3();
		s3.setClient_id(question.getClient_id() + "");
		s3.setAudit_type_id(question.getAudit_type_id() + "");
		s3.setGuid(question.getGuid());
		s3.setPage("question");
		S3 params = s3Service.GetPayload(s3);
		params.setClient_id(question.getClient_id() + "");
		params.setAudit_type_id(question.getAudit_type_id() + "");
		return new ResponseEntity<S3>(params, HttpStatus.OK);
	}
	

	@RequestMapping(value = "getCloudinaryFlag/{guid}", method = RequestMethod.GET)
	public ResponseEntity<?> getCloudinaryFlag(@PathVariable("guid") String guid) {
		String cloudflag = QuestionnaireService.getCloudinaryFlag(guid);
		List<String> flag = new ArrayList<String>();
		flag.add(cloudflag);
		return new ResponseEntity<List<String>>(flag, HttpStatus.OK);
	}

	@RequestMapping(value = "/section/clone", method = RequestMethod.POST)
	public ResponseEntity<List<SectionGroupClone>> cloneSection(@RequestBody SectionGroupClone section) {
		List<SectionGroupClone> sectionlist = QuestionnaireService.cloneSection(section);
		return new ResponseEntity<List<SectionGroupClone>>(sectionlist, HttpStatus.OK);
	}

}
