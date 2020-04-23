package com.goaudits.business.serviceimpl;

import java.io.IOException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.goaudits.business.entity.Choice;
import com.goaudits.business.entity.Group;
import com.goaudits.business.entity.Previewchoice;
import com.goaudits.business.entity.Question;
import com.goaudits.business.entity.QuestionOrder;
import com.goaudits.business.entity.Questionimage;
import com.goaudits.business.entity.Section;
import com.goaudits.business.entity.Tag;
import com.goaudits.business.mapper.QuestionnaireMapper;
import com.goaudits.business.service.QuestionnaireService;
import com.goaudits.business.util.Constants;
import com.goaudits.business.util.Utils;

@Service
public class QuestionnaireServiceImpl implements QuestionnaireService {

	@Autowired
	QuestionnaireMapper questionnairemapper;

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
	public List<Group> getQuestions(Group group) {
		List<Group> Grouplist = questionnairemapper.getallGroups(group);

		for (Group grp : Grouplist) {
			group.setGroup_id(grp.getGroup_id());
			List<Question> QuestionList = questionnairemapper.getallQuestions(group);

			for (Question ques : QuestionList) {
				List<Choice> choicelist = questionnairemapper.getchoicesforquestion(group.getGuid(),
						ques.getClient_id(), ques.getAudit_group_id(), ques.getAudit_type_id(), ques.getQuestion_no(),
						ques.getChoice_pat_id());
				ques.getChoiceList().addAll(choicelist);

				List<Choice> chlist = questionnairemapper.getParentChoice(ques);
				for (Choice c : chlist) {
					ques.setConditional_choiceid(Integer.parseInt(c.getChoice_id()));
					List<Question> QuestionList1 = questionnairemapper.getallsubQuestions(ques);
					for (Question ques1 : QuestionList1) {

						List<Choice> choicelist4 = questionnairemapper.getchoicesforquestion(group.getGuid(),
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

		return Grouplist;
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

			}

			List<Choice> choicelists = ques.getChoiceList();
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

			questionnairemapper.addCustomChoiceorUpdate(ques);

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

	@Override
	public boolean choiceChangeConditional(Question question) {
		return questionnairemapper.choiceChangeConditional(question) > 0 ? true : false;
	}

	@Override
	public boolean updateQuestion(List<Question> questionlist) {
		for (Question ques : questionlist) {
			ques.setAudit_group_id(1);

			List<Choice> choicelists = ques.getChoiceList();
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
			questionnairemapper.addCustomChoiceorUpdate(ques);

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
		String choice_type = "";
		String guid=choices.get(0).getGuid();
		for (Choice cho : choices) {

			choice_type = cho.getChoice_type();
			if (i++ == choices.size() - 1) {
				choice_pattern = choice_pattern + cho.getChoice_text();
			} else {
				choice_pattern = choice_pattern + cho.getChoice_text() + ",";
			}
		}

		return (questionnairemapper.ischoicePatternExits(guid, choice_pattern, choice_type)) > 0 ? true : false;	}

	@Override
	public int addCustomChoice(List<Choice> choices) {
		int i = 0;
		String choice_pattern = "";
		String choice_type = "";
		int choice_pat_id = 0;
		String guid=choices.get(0).getGuid();
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

}
