package com.goaudits.business.serviceimpl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.goaudits.business.entity.Choice;
import com.goaudits.business.entity.ChoiceItem;
import com.goaudits.business.entity.Group;
import com.goaudits.business.entity.GroupItem;
import com.goaudits.business.entity.ParentChoice;
import com.goaudits.business.entity.Previewchoice;
import com.goaudits.business.entity.Question;
import com.goaudits.business.entity.QuestionItem;
import com.goaudits.business.entity.QuestionOrder;
import com.goaudits.business.entity.QuestionVo;
import com.goaudits.business.entity.Questionimage;
import com.goaudits.business.entity.Section;
import com.goaudits.business.entity.SectionGroupClone;
import com.goaudits.business.entity.SectionItem;
import com.goaudits.business.entity.Tag;
import com.goaudits.business.entity.User;
import com.goaudits.business.mapper.QuestionnaireMapper;
import com.goaudits.business.service.QuestionnaireService;
import com.goaudits.business.util.Constants;
import com.goaudits.business.util.Utils;

@Service
public class QuestionnaireServiceImpl implements QuestionnaireService {

	@Autowired
	QuestionnaireMapper questionnairemapper;

	@Override
	public List<Section> getUserQuestions(Section section) {
		List<Section> Sectionlist = questionnairemapper.getUserSections(section);
		Group group = new Group();
		for (Section sec : Sectionlist) {

			group.setGuid(sec.getGuid());
			group.setClient_id(sec.getClient_id());
			group.setAudit_group_id(1);
			group.setAudit_type_id(sec.getAudit_type_id());
			group.setSection_id(sec.getSection_id());
			group.setActive(true);
			List<Group> Grouplist = questionnairemapper.getallGroupspre(group);

			for (Group grp : Grouplist) {
				group.setGroup_id(grp.getGroup_id());
				List<Question> QuestionList = questionnairemapper.getallQuestions(group);

				for (Question ques : QuestionList) {
					List<Choice> choicelist = questionnairemapper.getchoicesforquestion(group.getGuid(),
							ques.getClient_id(), ques.getAudit_group_id(), ques.getAudit_type_id(),
							ques.getQuestion_no(), ques.getChoice_pat_id());
					ques.getChoiceList().addAll(choicelist);

				}
				grp.getQuestionList().addAll(QuestionList);
			}

			sec.getGroupList().addAll(Grouplist);
		}

		return Sectionlist;
	}

	@Override
	public List<Section> getSections(Section section) {

		return questionnairemapper.getSections(section);
	}

	@Override
	public boolean isSectionExist(Section section) {

		return questionnairemapper.isSectionExist(section) != null ? true : false;
	}

	@Override
	public int addSection(Section section) {

		return questionnairemapper.addOrUpdateSection(section);
	}

	@Override
	public boolean issectionExistInDB(Section section) {

		return questionnairemapper.issectionExistInDB(section) > 0 ? true : false;
	}

	@Override
	public int updateSection(Section section) {
		return questionnairemapper.addOrUpdateSection(section);
	}

	@Override
	public int deleteSection(Section section) {

		return questionnairemapper.deleteSection(section);
	}

	@Override
	public int deleteGroup(Group group) {

		return questionnairemapper.deleteGroup(group);
	}

	@Override
	public boolean isGroupExistInDB(Group group) {

		return questionnairemapper.isGroupExistInDB(group) > 0 ? true : false;
	}

	@Override
	public int updateGroup(Group group) {

		return questionnairemapper.addOrUpdateGroup(group);
	}

	@Override
	public boolean isGroupExist(Group group) {

		return questionnairemapper.isGroupExist(group) != null ? true : false;
	}

	@Override
	public int addGroup(Group group) {

		return questionnairemapper.addOrUpdateGroup(group);
	}

	@Override
	public List<Choice> getchoicepatterens(String guid) {
		List<Choice> choicelist = questionnairemapper.getChoiceList(guid);
		for (Choice choices : choicelist) {
			int choice = choices.getChoice_pat_id();
			List<Choice> choicelists = questionnairemapper.getChoicesforPattern(guid, choice);

			choices.getChoiceList().addAll(choicelists);

		}
		return choicelist;
	}

	@Override
	public List<Section> getQuestions(Section sec) {

		List<Section> Sectionlist = questionnairemapper.getSectionList(sec);

		for (Section secl : Sectionlist) {

			List<Group> Grouplist = questionnairemapper.getallGroups(secl);
			for (Group grp : Grouplist) {
				grp.setGroup_id(grp.getGroup_id());
				grp.setInactiveq(sec.isActive());
				List<Question> QuestionList = questionnairemapper.getallQuestions(grp);

				for (Question ques : QuestionList) {
					List<Choice> choicelist = questionnairemapper.getchoicesforquestion(grp.getGuid(),
							ques.getClient_id(), ques.getAudit_group_id(), ques.getAudit_type_id(),
							ques.getQuestion_no(), ques.getChoice_pat_id());
					ques.getChoiceList().addAll(choicelist);

					List<Choice> chlist = questionnairemapper.getParentChoice(ques);
					for (Choice c : chlist) {
						ques.setConditional_choiceid(c.getChoice_id());
						List<Question> QuestionList1 = questionnairemapper.getallsubQuestions(ques);
						for (Question ques1 : QuestionList1) {

							List<Choice> choicelist4 = questionnairemapper.getchoicesforquestion(secl.getGuid(),
									ques1.getClient_id(), ques1.getAudit_group_id(), ques1.getAudit_type_id(),
									ques1.getQuestion_no(), ques1.getChoice_pat_id());
							ques1.getChoiceList().addAll(choicelist4);
						}

						c.getQuestionlist().addAll(QuestionList1);

					}

					ques.getSublist().addAll(chlist);

				}
				grp.getQuestionList().addAll(QuestionList);
			}
			secl.getGroupList().addAll(Grouplist);
		}

		return Sectionlist;
	}

	@Override
	public boolean isConditionalChoiceQuestionExist(List<Question> question) {
		return questionnairemapper.isConditionalQuestionExist(question.get(0)) > 0 ? true : false;
	}

	@Override
	public int addQuestion(List<Question> questionlist) {
		int qno = 0;

		for (Question ques : questionlist) {
			ques.setAudit_group_id(1);

			qno = questionnairemapper.generateqno(ques);

			int mainqno = ques.getQuestion_no();

			if (ques.isConditionalflag()) {
				ques.setIs_sub_question(true);

			}

			ques.setQuestion_no(qno);
			if (ques.getQuestion_type() == 2) {
				ques.setChoice_pat_id(-1);
				ques.getChoiceList().clear();
			}
			List<Choice> choicelists = new ArrayList<Choice>();
			if (ques.getChoiceList().size() > 0) {

				ques.getChoiceList().get(0).setGuid(ques.getGuid());
				ques.getChoiceList().get(0).setChoice_type(ques.getVchoice_type());
				choicelists = addCustomChoice1(ques.getChoiceList());
			}

			for (Choice choice : choicelists) {
				ques.setChoice_pat_id(choicelists.get(0).getChoice_pat_id());
				if (ques.getDefault_choice_id() != null && ques.getDefault_choice_id() != "") {
					ques.setDefault_choice_id(ques.getDefault_choice_id().replace(choice.getChoice_id(),
							choice.getCreated_choice_id() + ""));
				}

				if (ques.getEmail_choices() != null && ques.getEmail_choices() != "") {
					ques.setEmail_choices(
							ques.getEmail_choices().replace(choice.getChoice_id(), choice.getCreated_choice_id() + ""));
				}

				if (ques.getAction_choices() != null && ques.getAction_choices() != "") {
					try {
						ques.setAction_choices(ques.getAction_choices().replace(choice.getChoice_id(),
								choice.getCreated_choice_id() + ""));
					} catch (Exception e) {
						e.printStackTrace();
					}
				}

				if (ques.getComment_choices() != null && ques.getComment_choices() != "") {
					ques.setComment_choices(ques.getComment_choices().replace(choice.getChoice_id(),
							choice.getCreated_choice_id() + ""));
				}

				if (ques.getImage_choices() != null && ques.getImage_choices() != "") {
					ques.setImage_choices(
							ques.getImage_choices().replace(choice.getChoice_id(), choice.getCreated_choice_id() + ""));
				}

				if (ques.getAuto_fail() != null && ques.getAuto_fail() != "") {
					ques.setAuto_fail(
							ques.getAuto_fail().replace(choice.getChoice_id(), choice.getCreated_choice_id() + ""));
				}

				if (ques.getCritical_email_list() != null && ques.getCritical_email_list() != "") {
					ques.setCritical_email_list(ques.getCritical_email_list().replace(choice.getChoice_id(),
							choice.getCreated_choice_id() + ""));
				}
				if (ques.getConditional_choice_pat_id() != null && ques.getConditional_choice_pat_id() != "") {
					ques.setConditional_choice_pat_id(ques.getConditional_choice_pat_id().replace(choice.getChoice_id(),
							choice.getCreated_choice_id() + ""));
				}
				if (ques.getConditional_choiceid() != null && ques.getConditional_choiceid() != "") {
					ques.setConditional_choiceid(ques.getConditional_choiceid().replace(choice.getChoice_id(),
							choice.getCreated_choice_id() + ""));
				}

				choice.setChoice_id(choice.getCreated_choice_id() + "");
			}

			for (Choice choice : choicelists) {
				// System.out.println(choice.getChoice_id()+"score
				// :"+choice.getDefault_score_percent());

				questionnairemapper.addquestscores(ques.getGuid(), ques.getClient_id(), 1, ques.getAudit_type_id(),
						ques.getQuestion_no(), ques.getChoice_pat_id(), choice.getChoice_id(),
						choice.getDefault_score_percent(), choice.getScore_type(), choice.getChoice_colour());

			}

			if (ques.getQuestion_type() == 2) {
				questionnairemapper.addquestscores(ques.getGuid(), ques.getClient_id(), 1, ques.getAudit_type_id(),
						ques.getQuestion_no(), -1, "-1", "0", 0, "000");

			} else {
				questionnairemapper.addquestscores(ques.getGuid(), ques.getClient_id(), 1, ques.getAudit_type_id(),
						ques.getQuestion_no(), ques.getChoice_pat_id(), "0", "0", 0, "000");
			}

			String tag_id[] = null;
			String oldtagid = null;
			if (ques.getTag_id() != null) {
				tag_id = ques.getTag_id().split(",");
				oldtagid = tag_id[0];
			}

			ques.setOldtagid(oldtagid);

			questionnairemapper.addquest(ques);

			if (ques.isConditionalflag()) {
				ques.setSub_question_no(qno);

				questionnairemapper.addConditinalQuestion(ques.getGuid(), ques.getClient_id(), ques.getAudit_type_id(),
						mainqno, ques.getConditional_choice_pat_id(), ques.getConditional_choiceid(),
						ques.getSub_question_no());
				// ques.setIs_parent_question(true);
				ques.setIs_sub_question(true);

			}

//			questionnairemapper.addCustomChoiceorUpdate(ques);

			List<Questionimage> qimagelist = ques.getQuestimagelist();

			for (Questionimage qimage : qimagelist) {
				if (qimage.getIsdeleted() == 0) {
					byte[] quesimage = null;
					String[] imagesrc = null;

					if (qimage.getImage() != null && !(qimage.getImage().trim().isEmpty())) {
						imagesrc = qimage.getImage().split(",");
						try {
							quesimage = Utils.Base64ToBytes(imagesrc[1]);
							qimage.setBinaryimage(quesimage);
						} catch (IOException e) {
							e.printStackTrace();
						}
					}

					String scaleDownProperty = "w_1000,c_fill,g_auto";
					String delimiterStr = "/";
					int replaceIndex = 6;

					String reportImgUrl = null;
					if (qimage.getImage_path() == "" || qimage.getImage_path() == null) {

					} else {
						reportImgUrl = Utils.splitJoinStringsAtIndex(qimage.getImage_path(), scaleDownProperty,
								replaceIndex, delimiterStr);
					}

					questionnairemapper.addOrUpDatequestionimg(ques.getGuid(), ques.getClient_id(), 1,
							ques.getAudit_type_id(), ques.getQuestion_no(), qimage.getBinaryimage(),
							qimage.getImage_path(), qimage.getImage_public_id(), reportImgUrl);
				}

			}
		}

		return qno;
	}

	public List<Choice> addCustomChoice1(List<Choice> choices) {
		int i = 0;
		String choice_pattern = "";
		String choice_type = "";
		int choice_pat_id = 0;
		String guid = choices.get(0).getGuid();

		if (isCustomChoiceExist(choices)) {
			for (Choice cho : choices) {
				cho.setGuid(guid);
				choice_type = choices.get(0).getChoice_type();
				if (i++ == choices.size() - 1) {
					choice_pattern = choice_pattern + cho.getChoice_text();
					// choice_pattern=choice_pattern+"ZERO";
				} else {
					choice_pattern = choice_pattern + cho.getChoice_text() + ",";
				}
			}

			int choicepatid = questionnairemapper.getChoicePatId(guid, choice_pattern, choice_type);
//			System.out.println(choicepatid);
			List<Choice> choiceList = questionnairemapper.getChoicesforPatternForQues(guid, choicepatid);

			for (Choice chc : choiceList) {
				for (Choice chs : choices) {
					if (chc.getChoice_text().equals(chs.getChoice_text())) {
						chs.setCreated_choice_id(chc.getCreated_choice_id());
						chs.setChoice_pat_id(chc.getChoice_pat_id());
					}
				}
			}

			return choices;
		} else {
			choice_pat_id = questionnairemapper.generateChoicepatid(guid);
			for (Choice cho : choices) {
				cho.setChoice_pat_id(choice_pat_id);
				cho.setGuid(guid);
				choice_type = choices.get(0).getChoice_type();
				if (i++ == choices.size() - 1) {
					choice_pattern = choice_pattern + cho.getChoice_text();
					// choice_pattern=choice_pattern+"ZERO";
				} else {
					choice_pattern = choice_pattern + cho.getChoice_text() + ",";
				}
				cho.setChoice_colour(cho.getChoice_colour().replace("#", ""));
				cho.setCreated_choice_id(questionnairemapper.addCustomChoice(cho));
			}

			questionnairemapper.addExtraChoice(guid, choice_pat_id);
			questionnairemapper.addCustomChoicepattern(guid, choice_pat_id, choice_pattern, choice_type);
			return choices;

		}
	}

	@Override
	public boolean choiceChangeConditional(Question question) {
		return questionnairemapper.choiceChangeConditional(question) > 0 ? true : false;
	}

	@Override
	public boolean updateQuestion(List<Question> questionlist) {
		for (Question ques : questionlist) {
			ques.setAudit_group_id(1);

			if (ques.getQuestion_type() == 2) {
				ques.setChoice_pat_id(-1);
				ques.getChoiceList().clear();
			}

			List<Choice> choicelists = new ArrayList<Choice>();
			if (ques.getChoiceList().size() > 0) {

				ques.getChoiceList().get(0).setGuid(ques.getGuid());
				ques.getChoiceList().get(0).setChoice_type(ques.getVchoice_type());
				choicelists = addCustomChoice1(ques.getChoiceList());
			}

			for (Choice choice : choicelists) {
				ques.setChoice_pat_id(choicelists.get(0).getChoice_pat_id());
				if (ques.getDefault_choice_id() != null && ques.getDefault_choice_id() != "") {
					ques.setDefault_choice_id(ques.getDefault_choice_id().replace(choice.getChoice_id(),
							choice.getCreated_choice_id() + ""));
				}

				if (ques.getEmail_choices() != null && ques.getEmail_choices() != "") {
					ques.setEmail_choices(
							ques.getEmail_choices().replace(choice.getChoice_id(), choice.getCreated_choice_id() + ""));
				}

				if (ques.getAction_choices() != null && ques.getAction_choices() != "") {
					try {
						ques.setAction_choices(ques.getAction_choices().replace(choice.getChoice_id(),
								choice.getCreated_choice_id() + ""));
					} catch (Exception e) {
						e.printStackTrace();
					}
				}

				if (ques.getComment_choices() != null && ques.getComment_choices() != "") {
					ques.setComment_choices(ques.getComment_choices().replace(choice.getChoice_id(),
							choice.getCreated_choice_id() + ""));
				}

				if (ques.getImage_choices() != null && ques.getImage_choices() != "") {
					ques.setImage_choices(
							ques.getImage_choices().replace(choice.getChoice_id(), choice.getCreated_choice_id() + ""));
				}

				if (ques.getAuto_fail() != null && ques.getAuto_fail() != "") {
					ques.setAuto_fail(
							ques.getAuto_fail().replace(choice.getChoice_id(), choice.getCreated_choice_id() + ""));
				}

				if (ques.getCritical_email_list() != null && ques.getCritical_email_list() != "") {
					ques.setCritical_email_list(ques.getCritical_email_list().replace(choice.getChoice_id(),
							choice.getCreated_choice_id() + ""));
				}

				choice.setChoice_id(choice.getCreated_choice_id() + "");
			}

			// List<Choice> choicelists1 = ques.getChoiceList();
			for (Choice choice : choicelists) {

				questionnairemapper.addquestscores(ques.getGuid(), ques.getClient_id(), 1, ques.getAudit_type_id(),
						ques.getQuestion_no(), ques.getChoice_pat_id(), choice.getChoice_id(),
						choice.getDefault_score_percent(), choice.getScore_type(), choice.getChoice_colour());

			}
			String tag_id[] = null;
			String oldtagid = null;
			if (ques.getTag_id() != null) {
				tag_id = ques.getTag_id().split(",");
				oldtagid = tag_id[0];
			}

			ques.setOldtagid(oldtagid);

			questionnairemapper.addquest(ques);
//			questionnairemapper.addCustomChoiceorUpdate(ques);

			if (ques.isImageflag()) {
				List<Questionimage> qimglist = ques.getQuestimagelist();

				for (Questionimage img : qimglist) {

					if (img.getIsdeleted() == 1) {
						if (img.getImage_public_id() == null || img.getImage_public_id().equals("")
								|| img.getImage_public_id() == "") {
						} else {
							Cloudinary cloudinary = new Cloudinary(ObjectUtils.asMap("cloud_name", Constants.cloudname,
									"api_key", Constants.apikey, "api_secret", Constants.apiSecret));

							try {
								cloudinary.uploader().destroy(img.getImage_public_id(), ObjectUtils.emptyMap());
							} catch (IOException e) {
								e.printStackTrace();
							}
						}

						questionnairemapper.deleteQuestionimage(ques.getGuid(), ques.getClient_id(), 1,
								ques.getAudit_type_id(), ques.getQuestion_no(), img.getImage_id());
					}

				}

				List<Questionimage> qimagelist = ques.getQuestimagelist();

				for (Questionimage qimage : qimagelist) {
					if (qimage.getIsdeleted() == 0) {

						byte[] quesimage = null;
						String[] imagesrc = null;

						if (qimage.getImage() != null && !(qimage.getImage().trim().isEmpty())) {
							imagesrc = qimage.getImage().split(",");
							try {
								quesimage = Utils.Base64ToBytes(imagesrc[1]);
								qimage.setBinaryimage(quesimage);
							} catch (IOException e) {
								e.printStackTrace();
							}
						}

						String scaleDownProperty = "w_1000,c_fill,g_auto";
						String delimiterStr = "/";
						int replaceIndex = 6;

						String reportImgUrl = null;
						if (qimage.getImage_path() == "" || qimage.getImage_path() == null) {

						} else {
							reportImgUrl = Utils.splitJoinStringsAtIndex(qimage.getImage_path(), scaleDownProperty,
									replaceIndex, delimiterStr);
						}

						questionnairemapper.addOrUpDatequestionimg(ques.getGuid(), ques.getClient_id(), 1,
								ques.getAudit_type_id(), ques.getQuestion_no(), qimage.getBinaryimage(),
								qimage.getImage_path(), qimage.getImage_public_id(), reportImgUrl);
					}
				}

			}

		}
		return true;

	}

	@Override
	public boolean isConditionalChoiceExist(Question question) {
		return questionnairemapper.isConditionalExist(question) > 0 ? true : false;
	}

	@Override
	public int changeConditionalChoice(Question question) {
		return questionnairemapper.changeConditionalChoice(question);
	}

	@Override
	public List<Questionimage> getQuestionImage(Question question) {
		List<Questionimage> questionimglist = questionnairemapper.getquestionImage(question);
		for (Questionimage qimg : questionimglist) {
			byte[] images;
			images = qimg.getBinaryimage();

			if (images == null) {
				qimg.setImage("");

			} else {
				qimg.setImage("data:image/png;base64," + Utils.ConvertToBase64(images));
			}
		}

		return questionimglist;
	}

	@Override
	public boolean isAudit(String guid, int client_id, int audit_type_id) {
		return ((questionnairemapper.checkAudit(guid, client_id, audit_type_id)) > 0 ? true : false);
	}

	@Override
	public int orderQuestions(QuestionOrder questionOrder) {
		return questionnairemapper.questionOrder(questionOrder);
	}

	@Override
	public boolean isCustomChoiceExist(List<Choice> choices) {
		int i = 0;
		String choice_pattern = "";
		String choice_type = choices.get(0).getChoice_type();
		String guid = choices.get(0).getGuid();
		for (Choice cho : choices) {

			if (i++ == choices.size() - 1) {
				choice_pattern = choice_pattern + cho.getChoice_text();
			} else {
				choice_pattern = choice_pattern + cho.getChoice_text() + ",";
			}
		}

		return (questionnairemapper.ischoicePatternExits(guid, choice_pattern, choice_type)) > 0 ? true : false;
	}

	@Override
	public int addCustomChoice(List<Choice> choices) {
		int i = 0;
		String choice_pattern = "";
		String choice_type = "";
		int choice_pat_id = 0;
		String guid = choices.get(0).getGuid();
		choice_pat_id = questionnairemapper.generateChoicepatid(guid);
		for (Choice cho : choices) {
			cho.setChoice_pat_id(choice_pat_id);
			choice_type = cho.getChoice_type();
			if (i++ == choices.size() - 1) {
				choice_pattern = choice_pattern + cho.getChoice_text();
				// choice_pattern=choice_pattern+"ZERO";
			} else {
				choice_pattern = choice_pattern + cho.getChoice_text() + ",";
			}

			questionnairemapper.addCustomChoice(cho);
		}

		questionnairemapper.addExtraChoice(guid, choice_pat_id);
		questionnairemapper.addCustomChoicepattern(guid, choice_pat_id, choice_pattern, choice_type);

		return choice_pat_id;
	}

	@Override
	public int deleteQuestion(Question question) {
		return questionnairemapper.deleteQuestion(question);

	}

	@Override
	public List<Tag> getAllTags(Tag tag) {
		return questionnairemapper.getAllTags(tag);
	}

	@Override
	public List<Previewchoice> getPreviewchoice(Previewchoice previchoice) {
		return questionnairemapper.getPreviewchoice(previchoice);
	}

	@Override
	public int getQuestionAudit(Question question) {
		return questionnairemapper.getQuestionAudit(question);
	}

	@Override
	public List<User> getAdminslist(String guid) {
		return questionnairemapper.getAdminslist(guid);
	}

	@Override
	public String getCloudinaryFlag(String guid) {
		return questionnairemapper.getCloudinaryFlag(guid);
	}

	@Override
	public List<SectionGroupClone> cloneSection(SectionGroupClone section) {
		return questionnairemapper.cloneSectionGroup(section);
	}

	@Override
	public int changeConditionalChoicenew(Question question) {
		return questionnairemapper.changeConditionalChoicenew(question);
	}

	@Override
	public int getQimagecount(Question question) {
		return questionnairemapper.getQimagecount(question);
	}

	@Override
	public List<SectionItem> getQuestionList(Section audit) {

		List<QuestionVo> questionList = questionnairemapper.getQuestionsList(audit); // query to be done

		List<SectionItem> sectionItemList = questionList.stream().map(n -> new SectionItem(n)).distinct()
				.collect(Collectors.toList());

		for (SectionItem sectionItem : sectionItemList) {

			List<GroupItem> groupItemList = questionList.stream()
					.filter(p -> p.getSection_id() == sectionItem.getSection_id())
					.map(p -> new GroupItem(p)).distinct()
					.collect(Collectors.toList());

//			System.out.println(groupItemList);

			for (GroupItem groupItem : groupItemList) {

				List<QuestionItem> questionItemList = questionList.stream()
						.filter(p -> p.getSection_id() == groupItem.getSection_id()
								&& p.getGroup_id() == groupItem.getGroup_id() && !p.isIs_sub_question())
						.map(p -> new QuestionItem(p)).distinct().collect(Collectors.toList());

//				System.out.println("Question List => " + questionItemList);

				for (QuestionItem questionItem : questionItemList) {
					List<ChoiceItem> choiceItemList = questionList.stream()
							.filter(q -> q.getSection_id() == groupItem.getSection_id()
									&& q.getGroup_id() == groupItem.getGroup_id()
									&& q.getQuestion_no() == questionItem.getQuestion_no()
									&& q.getChoice_pat_id() == questionItem.getChoice_pat_id())
							.map(q -> new ChoiceItem(q)).distinct().collect(Collectors.toList());
					questionItem.getChoiceList().addAll(choiceItemList);

					for (ChoiceItem chs : choiceItemList) {
						List<ParentChoice> subChoicelist = questionList.stream()
								.filter(q -> q.getSection_id() == questionItem.getSection_id()
										&& q.getGroup_id() == questionItem.getGroup_id()
										&& q.getParent_question_no() == questionItem.getQuestion_no()
										&& q.getParent_choice_pat_id() == chs.getChoice_pat_id()
										&& q.getParent_choice_id()==Integer.parseInt(chs.getChoice_id())
									&& q.isIs_sub_question()
										)
								.map(q -> new ParentChoice(chs)).distinct().collect(Collectors.toList());
						
						Set<ParentChoice> subChoicelist1 = subChoicelist.stream()
					            .collect(Collectors.toCollection(() -> 
					                 new TreeSet<>(Comparator.comparing(ParentChoice::getChoice_id))));
						
						for(ParentChoice sub:subChoicelist1) {
							List<QuestionItem> questionlist=questionList.stream()
									.filter(p -> p.getSection_id() == groupItem.getSection_id()
									&& p.getGroup_id() == groupItem.getGroup_id() 
									&& p.getParent_question_no() == questionItem.getQuestion_no()
									&& p.getParent_choice_pat_id()==sub.getChoice_pat_id()
									&& p.getParent_choice_id()==Integer.parseInt(sub.getChoice_id())
									&& p.isIs_sub_question())
							.map(p -> new QuestionItem(p)).distinct().collect(Collectors.toList());

							for(QuestionItem questionItem1:questionlist) {
							List<ChoiceItem> choiceItemList1 = questionList.stream()
									.filter(q -> q.getSection_id() == groupItem.getSection_id()
											&& q.getGroup_id() == groupItem.getGroup_id()
											&& q.getQuestion_no() == questionItem1.getQuestion_no()
											&& q.getChoice_pat_id() == questionItem1.getChoice_pat_id())
									.map(q -> new ChoiceItem(q)).distinct().collect(Collectors.toList());
							
							questionItem1.getChoiceList().addAll(choiceItemList1);
							
							}
						sub.getQuestionlist().addAll(questionlist);
							
						}
						
						
						questionItem.getSublist().addAll(subChoicelist1);


					}
					// sublist

				}

				groupItem.getQuestionList().addAll(questionItemList);
			}
			sectionItem.getGroupList().addAll(groupItemList);
		}

		return sectionItemList;
	}

}
