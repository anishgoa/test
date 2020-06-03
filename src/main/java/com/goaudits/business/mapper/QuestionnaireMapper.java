package com.goaudits.business.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.mapping.StatementType;

import com.goaudits.business.entity.Choice;
import com.goaudits.business.entity.Group;
import com.goaudits.business.entity.Previewchoice;
import com.goaudits.business.entity.Question;
import com.goaudits.business.entity.QuestionOrder;
import com.goaudits.business.entity.Questionimage;
import com.goaudits.business.entity.Section;
import com.goaudits.business.entity.Tag;
import com.goaudits.business.entity.User;

@Mapper
public interface QuestionnaireMapper {

	@Select(value = "{CALL SP_GA_GETSECTION_DET_PV3( #{guid, mode=IN, jdbcType=BINARY},#{uid, mode=IN, jdbcType=BINARY}, #{client_id, mode=IN, jdbcType=INTEGER},#{audit_group_id, mode=IN, jdbcType=INTEGER}, #{audit_type_id, mode=IN, jdbcType=INTEGER} )}")
	@Options(statementType = StatementType.CALLABLE)
	List<Section> getSections(Section section);

	@Select("SELECT * FROM GA_SECTION_MT WHERE GUID=#{guid} AND CLIENT_ID=#{client_id} AND AUDIT_GROUP_ID=1 AND AUDIT_TYPE_ID=#{audit_type_id} AND SECTION_NAME=#{section_name} ")
	Section isSectionExist(Section section);

	@Select(value = "{ CALL SP_GA_UPDATE_SECTION_DETV2( #{guid, mode=IN, jdbcType=BINARY}, #{client_id, mode=IN, jdbcType=INTEGER},#{audit_group_id, mode=IN, jdbcType=INTEGER}, #{audit_type_id, mode=IN, jdbcType=INTEGER },"
			+ "#{section_id, mode=IN, jdbcType=INTEGER },#{section_name, mode=IN, jdbcType=VARCHAR }, #{active, mode=IN, jdbcType=BOOLEAN },#{section_help, mode=IN, jdbcType=VARCHAR },#{help_color, mode=IN, jdbcType=VARCHAR },#{is_help_bold, mode=IN, jdbcType=BOOLEAN },#{is_help_italic, mode=IN, jdbcType=BOOLEAN },#{help_text_position, mode=IN, jdbcType=BOOLEAN} )}")
	int addOrUpdateSection(Section section);

	@Select("SELECT COUNT(*) FROM GA_SECTION_MT WHERE GUID=#{guid} AND CLIENT_ID=#{client_id} AND AUDIT_GROUP_ID=1 AND AUDIT_TYPE_ID=#{audit_type_id} AND SECTION_ID!=#{section_id} AND SECTION_NAME=#{section_name} ")
	int issectionExistInDB(Section section);

	@Delete(value = "{ CALL SP_GA_DELETE_SECTION_DET( #{guid, mode=IN, jdbcType=BINARY}, #{client_id, mode=IN, jdbcType=INTEGER},#{audit_group_id, mode=IN, jdbcType=INTEGER}, #{audit_type_id, mode=IN, jdbcType=INTEGER },"
			+ "#{section_id, mode=IN, jdbcType=INTEGER },#{section_name, mode=IN, jdbcType=VARCHAR }, #{active, mode=IN, jdbcType=BOOLEAN } )}")
	int deleteSection(Section section);

	@Delete(value = "{CALL SP_GA_DELETE_GROUP_DET(#{guid, mode=IN, jdbcType=BINARY},#{client_id, mode=IN, jdbcType=INTEGER}, #{audit_group_id, mode=IN, jdbcType=INTEGER},#{audit_type_id, mode=IN, jdbcType=INTEGER},"
			+ "#{section_id, mode=IN, jdbcType=INTEGER},#{group_id, mode=IN, jdbcType=INTEGER},#{group_name, mode=IN, jdbcType=VARCHAR},#{active, mode=IN, jdbcType=BOOLEAN})}")
	@Options(statementType = StatementType.CALLABLE)
	int deleteGroup(Group group);

	@Select(value = "{CALL SP_GA_UPDATE_GROUP_DET(#{guid, mode=IN, jdbcType=BINARY},#{client_id, mode=IN, jdbcType=INTEGER}, #{audit_group_id, mode=IN, jdbcType=INTEGER},#{audit_type_id, mode=IN, jdbcType=INTEGER},"
			+ "#{section_id, mode=IN, jdbcType=INTEGER},#{group_id, mode=IN, jdbcType=INTEGER},#{group_name, mode=IN, jdbcType=VARCHAR},#{active, mode=IN, jdbcType=BOOLEAN})}")
	@Options(statementType = StatementType.CALLABLE)
	int addOrUpdateGroup(Group group);

	@Select("SELECT * FROM GA_GROUP_MT WHERE GUID=#{guid} AND CLIENT_ID=#{client_id} AND AUDIT_GROUP_ID=1 AND AUDIT_TYPE_ID=#{audit_type_id} AND SECTION_ID=#{section_id} AND GROUP_NAME=#{group_name} ")
	Group isGroupExist(Group group);

	@Select("SELECT COUNT(*) FROM GA_GROUP_MT WHERE GUID=#{guid} AND CLIENT_ID=#{client_id} AND AUDIT_GROUP_ID=1 AND AUDIT_TYPE_ID=#{audit_type_id} AND SECTION_ID=#{section_id} AND GROUP_ID!=#{group_id} AND GROUP_NAME=#{group_name} ")
	int isGroupExistInDB(Group group);

	@Select(value = "{ CALL SP_GA_GETALLCHOICES_PV2( #{guid, mode=IN, jdbcType=BINARY} ) }")
	@Options(statementType = StatementType.CALLABLE)
	List<Choice> getChoiceList(String guid);

	@Select(value = "{ CALL SP_GA_GETCHOICESFORUSER( #{guid, mode=IN, jdbcType=BINARY},#{choicepatid, mode=IN, jdbcType=INTEGER} ) }")
	@Options(statementType = StatementType.CALLABLE)
	List<Choice> getChoicesforPattern(@Param("guid") String guid,@Param("choicepatid") int choicepatid);

	
	@Select(value = "{CALL SP_GA_GETGROUP_DET_PV2(#{guid, mode=IN, jdbcType=BINARY},#{client_id, mode=IN, jdbcType=INTEGER}, #{audit_group_id, mode=IN, jdbcType=INTEGER},#{audit_type_id, mode=IN, jdbcType=INTEGER},"
			+ "#{section_id, mode=IN, jdbcType=INTEGER})}")
	@Options(statementType = StatementType.CALLABLE)
	List<Group> getallGroupspre(Group grp);

	
	@Select(value = "{CALL SP_GA_GETGROUP_DET_PV2(#{guid, mode=IN, jdbcType=BINARY},#{client_id, mode=IN, jdbcType=INTEGER}, #{audit_group_id, mode=IN, jdbcType=INTEGER},#{audit_type_id, mode=IN, jdbcType=INTEGER},"
			+ "#{section_id, mode=IN, jdbcType=INTEGER})}")
	@Options(statementType = StatementType.CALLABLE)
	List<Group> getallGroups(Section sec);

	@Select(value = "{CALL SP_GA_GETQUESTION_DET_PV2( #{guid, mode=IN, jdbcType=BINARY}, #{client_id, mode=IN, jdbcType=INTEGER},#{audit_group_id, mode=IN, jdbcType=INTEGER}, #{audit_type_id, mode=IN, jdbcType=INTEGER}, #{section_id, mode=IN, jdbcType=INTEGER}, #{group_id, mode=IN, jdbcType=INTEGER},#{active, mode=IN, jdbcType=BOOLEAN} )}")
	@Options(statementType = StatementType.CALLABLE)
	List<Question> getallQuestions(Group group);

	@Select(value = "{ CALL SP_GA_GETCHOICESBASEDONPATID_DET_PV2(#{guid, mode=IN, jdbcType=BINARY},#{client_id, mode=IN, jdbcType=INTEGER},#{audit_group_id, mode=IN, jdbcType=INTEGER}, #{audit_type_id, mode=IN, jdbcType=INTEGER}, #{question_no, mode=IN, jdbcType=INTEGER},#{choice_pat_id, mode=IN, jdbcType=INTEGER} ) }")
	@Options(statementType = StatementType.CALLABLE)
	List<Choice> getchoicesforquestion(@Param("guid") String guid, @Param("client_id") int client_id,
			@Param("audit_group_id") int audit_group_id, @Param("audit_type_id") int audit_type_id,
			@Param("question_no") int question_no, @Param("choice_pat_id") int choice_pat_id);

	@Select(value = "{CALL SP_GA_GETSUBQUESTION_DET_PV2( #{guid, mode=IN, jdbcType=BINARY}, #{client_id, mode=IN, jdbcType=INTEGER},#{audit_group_id, mode=IN, jdbcType=INTEGER}, #{audit_type_id, mode=IN, jdbcType=INTEGER}, #{question_no, mode=IN, jdbcType=INTEGER}, #{conditional_choiceid,mode=IN, jdbcType=INTEGER} )}")
	@Options(statementType = StatementType.CALLABLE)
	List<Question> getallsubQuestions(Question ques);

	@Select("SELECT DISTINCT CQ.CHOICE_PAT_ID,CQ.CHOICE_ID,CP.CHOICE_TEXT FROM GA_CONDITIONAL_QUESTION_MT CQ,GA_CHOICETYPE_MT_V2  CP WHERE CP.GUID=#{guid} AND CQ.CLIENT_ID=#{client_id} AND CQ.AUDIT_TYPE_ID=#{audit_type_id} AND CQ.QUESTION_NO=#{question_no} AND CP.GUID=CQ.GUID AND CQ.CHOICE_PAT_ID=CP.CHOICE_PAT_ID AND CQ.CHOICE_ID=CP.CHOICE_ID")
	List<Choice> getParentChoice(Question ques);

	@Select("SELECT COUNT(*) FROM GA_CONDITIONAL_QUESTION_MT WHERE GUID=#{guid} AND CLIENT_ID=#{client_id} AND AUDIT_TYPE_ID=#{audit_type_id} AND QUESTION_NO=#{question_no} AND CHOICE_PAT_ID=#{conditional_choice_pat_id} AND CHOICE_ID=#{conditional_choiceid}")
	int isConditionalQuestionExist(Question question);

	@Select("SELECT IFNULL(MAX(QUESTION_NO),0)+1  FROM GA_QUESTION_MT WHERE GUID=#{guid} AND CLIENT_ID=#{client_id} AND AUDIT_GROUP_ID=#{audit_group_id} AND AUDIT_TYPE_ID=#{audit_type_id}")
	int generateqno(Question ques);

	@Insert(value = "{CALL SP_GA_UPDATE_QUESTIONSCORE_DET_PV2(#{guid, mode=IN, jdbcType=BINARY},#{client_id, mode=IN, jdbcType=INTEGER}, #{audit_group_id, mode=IN, jdbcType=INTEGER},#{audit_type_id, mode=IN, jdbcType=INTEGER},"
			+ "#{question_no, mode=IN, jdbcType=INTEGER},#{choice_pat_id, mode=IN, jdbcType=INTEGER},#{choice_id, mode=IN, jdbcType=INTEGER},#{default_score_percent, mode=IN, jdbcType=VARCHAR},#{score_type, mode=IN, jdbcType=INTEGER},#{choice_colour,mode=IN, jdbcType=VARCHAR})}")
	@Options(statementType = StatementType.CALLABLE)
	int addquestscores(@Param("guid") String guid, @Param("client_id") int client_id,
			@Param("audit_group_id") int audit_group_id, @Param("audit_type_id") int audit_type_id,
			@Param("question_no") int question_no, @Param("choice_pat_id") int choice_pat_id,
			@Param("choice_id") String choice_id, @Param("default_score_percent") String default_score_percent,
			@Param("score_type") int score_type, @Param("choice_colour") String choice_colour);

	@Insert(value = "{ CALL SP_GA_UPDATE_CONDITINALQUESTION(#{guid, mode=IN, jdbcType=BINARY},#{client_id, mode=IN, jdbcType=INTEGER}, #{audit_type_id, mode=IN, jdbcType=INTEGER},"
			+ "#{question_no, mode=IN, jdbcType=INTEGER},#{choice_pat_id, mode=IN, jdbcType=INTEGER},#{choice_id, mode=IN, jdbcType=INTEGER},#{sub_question_no, mode=IN, jdbcType=INTEGER} ) }")
	int addConditinalQuestion(@Param("guid") String guid, @Param("client_id") int client_id,
			@Param("audit_type_id") int audit_type_id, @Param("question_no") int question_no,
			@Param("choice_pat_id") String choice_pat_id, @Param("choice_id") String choice_id,
			@Param("sub_question_no") int sub_question_no);

	@Insert(value = "{CALL SP_GA_UPDATE_QUESTIONS_DET_PV4(#{guid, mode=IN, jdbcType=BINARY},#{client_id, mode=IN, jdbcType=INTEGER}, #{audit_group_id, mode=IN, jdbcType=INTEGER},#{audit_type_id, mode=IN, jdbcType=INTEGER},"
			+ "#{section_id, mode=IN, jdbcType=INTEGER},#{group_id, mode=IN, jdbcType=INTEGER},#{question_no, mode=IN, jdbcType=INTEGER},#{choice_pat_id, mode=IN, jdbcType=INTEGER},#{question_text, mode=IN, jdbcType=VARCHAR},#{available_score, mode=IN, jdbcType=VARCHAR},"
			+ "#{active, mode=IN, jdbcType=BOOLEAN},#{default_choice_id, mode=IN, jdbcType=INTEGER},#{ismandatory, mode=IN, jdbcType=BOOLEAN},#{image_mandatory, mode=IN, jdbcType=BOOLEAN},#{comment_mandatory, mode=IN, jdbcType=BOOLEAN},#{tag_id, mode=IN, jdbcType=VARCHAR},#{question_help, mode=IN, jdbcType=VARCHAR},"
			+ "#{isactionplan_mandatory, mode=IN, jdbcType=BOOLEAN},#{auto_fail, mode=IN, jdbcType=VARCHAR},#{question_type, mode=IN, jdbcType=INTEGER},#{image_position, mode=IN, jdbcType=BOOLEAN},#{action_enabled, mode=IN, jdbcType=BOOLEAN},#{email_enabled, mode=IN, jdbcType=BOOLEAN},#{action_choices, mode=IN, jdbcType=BOOLEAN},#{email_choices, mode=IN, jdbcType=BOOLEAN},#{critical_email_list, mode=IN, jdbcType=VARCHAR},"
			+ "#{is_parent_question, mode=IN, jdbcType=BOOLEAN},#{is_sub_question, mode=IN, jdbcType=BOOLEAN},#{no_score, mode=IN, jdbcType=BOOLEAN},#{temp_min, mode=IN, jdbcType=VARCHAR},#{temp_max, mode=IN, jdbcType=VARCHAR},#{vchoice_type, mode=IN, jdbcType=VARCHAR},#{temp_unit, mode=IN, jdbcType=VARCHAR},#{is_multichoice, mode=IN, jdbcType=BOOLEAN},#{oldtagid, mode=IN, jdbcType=BOOLEAN},#{picture_layout, mode=IN, jdbcType=VARCHAR},#{comment_choices, mode=IN, jdbcType=VARCHAR},#{image_choices, mode=IN, jdbcType=VARCHAR},#{question_text_color, mode=IN, jdbcType=VARCHAR})}")
	@Options(statementType = StatementType.CALLABLE)
	int addquest(Question ques);

	@Insert(value = "{CALL SP_GA_UPDATEPREVIEWCHOICE_DET_PV2( #{guid, mode=IN, jdbcType=BINARY}, #{client_id, mode=IN, jdbcType=INTEGER},#{audit_group_id, mode=IN, jdbcType=INTEGER}, #{audit_type_id, mode=IN, jdbcType=INTEGER},"
			+ "#{choice_text1, mode=IN, jdbcType=VARCHAR},#{choice_text2, mode=IN, jdbcType=VARCHAR},#{choice_text3, mode=IN, jdbcType=VARCHAR},#{choice_text4, mode=IN, jdbcType=VARCHAR})}")
	@Options(statementType = StatementType.CALLABLE)
	int addCustomChoiceorUpdate(Question ques);

	@Insert(value = "{CALL SP_GA_UPDATE_QUESTIONIMAGE_DET(#{guid, mode=IN, jdbcType=BINARY},#{client_id, mode=IN, jdbcType=INTEGER}, #{audit_group_id, mode=IN, jdbcType=INTEGER},#{audit_type_id, mode=IN, jdbcType=INTEGER},"
			+ "#{question_no, mode=IN, jdbcType=INTEGER},#{binaryimage, mode=IN, jdbcType=VARCHAR},#{image_path, mode=IN, jdbcType=VARCHAR},#{image_public_id, mode=IN, jdbcType=VARCHAR},#{image_thumbnail, mode=IN, jdbcType=VARCHAR})}")
	@Options(statementType = StatementType.CALLABLE)
	int addOrUpDatequestionimg(@Param("guid") String guid, @Param("client_id") int client_id,
			@Param("audit_group_id") int audit_group_id, @Param("audit_type_id") int audit_type_id,
			@Param("question_no") int question_no, @Param("binaryimage") byte[] binaryimage,
			@Param("image_path") String image_path, @Param("image_public_id") String image_public_id,
			@Param("image_thumbnail") String image_thumbnail);

	@Select(value = "{CALL SP_GA_VALIDATECONDITIONALCHOICE( #{guid, mode=IN, jdbcType=BINARY}, #{client_id, mode=IN, jdbcType=INTEGER}, #{audit_type_id, mode=IN, jdbcType=INTEGER}, #{question_no, mode=IN, jdbcType=INTEGER}, #{choice_pat_id,mode=IN, jdbcType=INTEGER} )}")
	@Options(statementType = StatementType.CALLABLE)
	int choiceChangeConditional(Question question);

	@Delete("DELETE FROM GA_QUESTIONPHOTO_MT WHERE GUID=#{guid} AND CLIENT_ID=#{client_id} AND AUDIT_GROUP_ID=#{audit_group_id} AND AUDIT_TYPE_ID=#{audit_type_id} "
			+ "AND QUESTION_NO=#{question_no} AND IMAGE_ID=#{image_id}")
	int deleteQuestionimage(@Param("guid") String guid, @Param("client_id") int client_id,
			@Param("audit_group_id") int audit_group_id, @Param("audit_type_id") int audit_type_id,
			@Param("question_no") int question_no, @Param("image_id") int image_id);

	@Select("SELECT COUNT(*) FROM GA_CONDITIONAL_QUESTION_MT WHERE GUID=#{guid} AND CLIENT_ID=#{client_id} AND AUDIT_TYPE_ID=#{audit_type_id} AND QUESTION_NO=#{question_no} AND CHOICE_PAT_ID=#{choice_pat_id} AND CHOICE_ID=#{choice_id} AND CHOICE_ID!=#{oldchoiceid}")
	int isConditionalExist(Question question);

	@Update(value = "{CALL SP_GA_UPDATECONDITIONALCHOICE_PV2( #{guid, mode=IN, jdbcType=BINARY}, #{client_id, mode=IN, jdbcType=INTEGER}, #{audit_type_id, mode=IN, jdbcType=INTEGER}, #{question_no, mode=IN, jdbcType=INTEGER}, #{choice_pat_id,mode=IN, jdbcType=INTEGER},#{oldchoiceid,mode=IN, jdbcType=INTEGER},#{choice_id,mode=IN, jdbcType=INTEGER} )}")
	@Options(statementType = StatementType.CALLABLE)
	int changeConditionalChoice(Question question);

	@Select("SELECT IMAGE AS binaryimage,CLOUD_IMAGE_PATH as image_path,CLOUD_IMAGE_THUMBNAIL AS image_thumbnail,CLOUD_IMAGE_PUBLIC_ID as image_public_id,IMAGE_ID,CLIENT_ID,AUDIT_TYPE_ID  FROM GA_QUESTIONPHOTO_MT WHERE GUID=#{guid} AND CLIENT_ID=#{client_id} AND AUDIT_GROUP_ID=1 AND AUDIT_TYPE_ID=#{audit_type_id} AND QUESTION_NO=#{question_no}")
	List<Questionimage> getquestionImage(Question question);

	@Select("SELECT COUNT(*) FROM GA_QUESTION_DT WHERE GUID =#{guid} AND CLIENT_ID=#{client_id} AND AUDIT_GROUP_ID=1 AND AUDIT_TYPE_ID=#{audit_type_id}")
	int checkAudit(@Param("guid") String guid, @Param("client_id") int client_id,
			@Param("audit_type_id") int audit_type_id);

	@Insert(value = "{CALL SP_GA_UPDATE_QUESTIONORDER_DET_PV2(#{guid, mode=IN, jdbcType=BINARY},#{client_id, mode=IN, jdbcType=INTEGER}, #{audit_group_id, mode=IN, jdbcType=INTEGER},#{audit_type_id, mode=IN, jdbcType=INTEGER},"
			+ "#{section_id, mode=IN, jdbcType=INTEGER},#{draggroup_id, mode=IN, jdbcType=INTEGER},#{dropgroup_id, mode=IN, jdbcType=INTEGER},#{dragquestion_order, mode=IN, jdbcType=INTEGER},#{dropquestion_order, mode=IN, jdbcType=INTEGER})}")
	int questionOrder(QuestionOrder q);

	@Select("SELECT COUNT(*) FROM GA_CHOICEPAT_MT_V2 WHERE GUID =#{guid} AND CHOICE_PATTERN=#{choice_pattern} AND CHOICE_TYPE=#{choice_type}")
	int ischoicePatternExits(@Param("guid") String guid, @Param("choice_pattern") String choice_pattern,
			@Param("choice_type") String choice_type);

	@Select(value = "{CALL SP_GA_GETGENERATEDCHOICEPATID_DET ( #{guid, mode=IN, jdbcType=BINARY})}")
	int generateChoicepatid(String guid);

	@Select(value = "{CALL SP_GA_UPDATECUSTOMCHOICE_DET ( #{guid, mode=IN, jdbcType=BINARY}, #{choice_pat_id, mode=IN, jdbcType=INTEGER},#{choice_text, mode=IN, jdbcType=VARCHAR},#{choice_colour, mode=IN, jdbcType=VARCHAR})}")
	int addCustomChoice(Choice cho);

	@Insert("REPLACE INTO GA_CHOICETYPE_MT_V2(GUID,CHOICE_PAT_ID,CHOICE_ID,CHOICE_TEXT,CHOICE_COLOUR,LAST_MODIFIED) VALUES (#{guid}, #{choice_pat_id} ,0,'Zero','000',now() )")
	int addExtraChoice(@Param("guid") String guid, @Param("choice_pat_id") int choice_pat_id);

	@Insert(value = "{CALL SP_GA_UPDATECUSTOMPATTERN_DET ( #{guid, mode=IN, jdbcType=BINARY}, #{choice_pat_id, mode=IN, jdbcType=INTEGER},#{choice_pattern, mode=IN, jdbcType=VARCHAR},#{choice_type, mode=IN, jdbcType=VARCHAR})}")
	int addCustomChoicepattern(@Param("guid") String guid, @Param("choice_pat_id") int choice_pat_id,
			@Param("choice_pattern") String choice_pattern, @Param("choice_type") String choice_type);

	@Delete(value = "{CALL SP_GA_DELETE_QUESTION_DET(#{guid, mode=IN, jdbcType=BINARY},#{client_id, mode=IN, jdbcType=INTEGER}, #{audit_group_id, mode=IN, jdbcType=INTEGER},#{audit_type_id, mode=IN, jdbcType=INTEGER},"
			+ "#{section_id, mode=IN, jdbcType=INTEGER},#{group_id, mode=IN, jdbcType=INTEGER},#{question_no, mode=IN, jdbcType=INTEGER})}")
	@Options(statementType = StatementType.CALLABLE)
	int deleteQuestion(Question question);

	@Select(value = "{CALL SP_GA_GETTAG_DET_PV2( #{guid, mode=IN, jdbcType=BINARY}, #{client_id, mode=IN, jdbcType=INTEGER},#{audit_group_id, mode=IN, jdbcType=INTEGER}, #{audit_type_id, mode=IN, jdbcType=INTEGER} )}")
	@Options(statementType = StatementType.CALLABLE)
	List<Tag> getAllTags(Tag tag);

	@Select(value = "{CALL SP_GA_GETPREVIEWCHOICE_DET_PV2( #{guid, mode=IN, jdbcType=BINARY}, #{client_id, mode=IN, jdbcType=INTEGER},#{audit_group_id, mode=IN, jdbcType=INTEGER}, #{audit_type_id, mode=IN, jdbcType=INTEGER} )}")
	@Options(statementType = StatementType.CALLABLE)
	List<Previewchoice> getPreviewchoice(Previewchoice previchoice);

	@Select("SELECT COUNT(*) FROM GA_QUESTION_DT WHERE GUID=#{guid} AND CLIENT_ID=#{client_id} AND AUDIT_GROUP_ID=1 AND AUDIT_TYPE_ID=#{audit_type_id} AND SECTION_ID=#{section_id} AND GROUP_ID=#{group_id} AND QUESTION_NO=#{question_no}")
	int getQuestionAudit(Question question);

	@Select("SELECT * FROM GA_SECTION_MT WHERE GUID=#{guid} AND CLIENT_ID=#{client_id} AND AUDIT_GROUP_ID=1 AND AUDIT_TYPE_ID=#{audit_type_id}")
	List<Section> getUserSections(Section section);

	@Select("SELECT * FROM GA_SECTION_MT WHERE GUID=#{guid} AND CLIENT_ID=#{client_id} AND AUDIT_GROUP_ID=1 AND AUDIT_TYPE_ID=#{audit_type_id}")
	List<Section> getSectionList(Section sec);

	@Select("SELECT FIRST_NAME,LAST_NAME,USER_NAME,UID FROM GA_USERDET_MT WHERE GUID=#{guid}  AND ACTIVE=1")
	List<User> getAdminslist(String guid);

	@Select("SELECT ENABLE_CLOUDINARY FROM GA_USERDET_MT WHERE GUID=#{guid} AND SUPER_USER=1")
	String getCloudinaryFlag(String guid);

	@Select("SELECT CHOICE_PAT_ID FROM GA_CHOICEPAT_MT_V2 WHERE GUID=#{guid} AND CHOICE_PATTERN=#{choice_pattern} AND CHOICE_TYPE=#{choice_type} LIMIT 1")
	int getChoicePatId(@Param("guid")String guid,@Param("choice_pattern")String choice_pattern,@Param("choice_type") String choice_type);

	@Select("SELECT CHOICE_ID AS CREATED_CHOICE_ID,CHOICE_TEXT,CHOICE_COLOUR,CHOICE_PAT_ID FROM GA_CHOICETYPE_MT_V2 WHERE GUID=#{guid} AND CHOICE_PAT_ID=#{choicepatid} AND CHOICE_ID!=0")
	List<Choice> getChoicesforPatternForQues(@Param("guid")String guid,@Param("choicepatid") int choicepatid);
}
