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
import org.springframework.transaction.annotation.Transactional;
import com.goaudits.business.entity.ActionPlanAssignee;
import com.goaudits.business.entity.AuditName;
import com.goaudits.business.entity.Company;
import com.goaudits.business.entity.EmailMessage;
import com.goaudits.business.entity.EmailSubject;
import com.goaudits.business.entity.EmailTemplate;
import com.goaudits.business.entity.Group;
import com.goaudits.business.entity.GuidedSetup;
import com.goaudits.business.entity.Location;
import com.goaudits.business.entity.LocationTags;
import com.goaudits.business.entity.PreTemplates;
import com.goaudits.business.entity.Question;
import com.goaudits.business.entity.Report;
import com.goaudits.business.entity.ReportImage;
import com.goaudits.business.entity.ScoreRange;
import com.goaudits.business.entity.Section;
import com.goaudits.business.entity.User;
import com.goaudits.business.entity.Choice;

@Mapper
public interface SetupMapper {

	@Select("SELECT * FROM GA_USERDET_MT where USER_NAME=#{username}")
	User getUserDetails(String username);

	@Select(value = "{ CALL SP_GA_GETCLIENT_DET_V4( #{guid, mode=IN, jdbcType=BINARY}, #{uid, mode=IN, jdbcType=BINARY},#{active, mode=IN, jdbcType=BOOLEAN},#{image_required,mode=IN,jdbcType=BOOLEAN} )}")
	@Options(statementType = StatementType.CALLABLE)
	List<Company> getCompanyList(Company company);

	@Select("SELECT * FROM GA_CLIENTACTIONEMAIL_MT WHERE GUID=#{guid} AND CLIENT_ID=#{client_id}")
	List<ActionPlanAssignee> getActionPlanAssigneeByCompany(Company company);

	@Select(value = "{ CALL SP_GA_GETLOCATIONBYCLIENT_PV4( #{guid, mode=IN, jdbcType=BINARY}, #{uid, mode=IN, jdbcType=BINARY},#{client_id, mode=IN, jdbcType=INTEGER},#{active, mode=IN, jdbcType=BOOLEAN} ) }")
	@Options(statementType = StatementType.CALLABLE)
	List<Location> getLocationsBasedonCompany(Location location);

	@Select(value = "{ CALL SP_GA_GETAUDITTYPESBYCLIENT_PV5( #{guid, mode=IN, jdbcType=BINARY}, #{uid, mode=IN, jdbcType=BINARY},#{client_id, mode=IN, jdbcType=INTEGER},#{active, mode=IN, jdbcType=BOOLEAN},#{image_required,mode=IN,jdbcType=BOOLEAN} ) }")
	@Options(statementType = StatementType.CALLABLE)
	List<AuditName> getAuditNamesByCompany(AuditName auditname);

	@Select("SELECT count(*) FROM GA_CLIENT_MT WHERE GUID=#{guid} AND CLIENT_NAME=#{client_name}")
	int getAddCompanyCount(Company company);

	@Transactional(rollbackFor = Exception.class)
	@Select(value = "{ CALL SP_GA_UPDATE_CLIENT_DET_PV3( #{guid, mode=IN, jdbcType=BINARY}, #{client_id, mode=IN, jdbcType=INTEGER}, #{client_name, mode=IN, jdbcType=VARCHAR },"
			+ "#{submit_button_text, mode=IN, jdbcType=VARCHAR }, #{logo, mode=IN, jdbcType=LONGVARCHAR }, #{active, mode=IN, jdbcType=BOOLEAN },#{logo_binary, mode=IN, jdbcType=BOOLEAN },#{uid, mode=IN, jdbcType=BINARY},#{short_name,mode=IN, jdbcType=VARCHAR} )}")
	Company addOrUpdateCompany(Company company);

	@Select("SELECT count(*) FROM GA_CLIENT_MT WHERE GUID=#{guid} AND CLIENT_NAME=#{client_name} AND CLIENT_ID!=#{client_id}")
	int getEditCompanyCount(Company company);

	@Update(value = "{CALL SP_GA_UPDATE_COMPANY_ORDER_DET(#{guid, mode=IN, jdbcType=BINARY},#{drag_index, mode=IN, jdbcType=INTEGER},"
			+ "#{drop_index, mode=IN, jdbcType=INTEGER})}")
	@Options(statementType = StatementType.CALLABLE)
	void companyReorder(Company company);

	@Select("SELECT COUNT(*) FROM GA_STORE_MT WHERE GUID=#{guid} AND CLIENT_ID=#{client_id} AND STORE_NAME=#{store_name}")
	int getLocationExits(Location location);

	@Select("SELECT COUNT(*) FROM GA_STORE_MT WHERE GUID = #{guid} AND CLIENT_ID = #{client_id} AND STORE_NAME=#{store_name} AND STORE_ID != #{store_id}")
	int getLocationExitsInDb(Location location);

	@Transactional(rollbackFor = Exception.class)
	@Select(value = "{ CALL SP_GA_UPDATE_STORES_DET_PV3( #{guid, mode=IN, jdbcType=BINARY}, #{client_id, mode=IN, jdbcType=INTEGER},#{store_id, mode=IN, jdbcType=INTEGER}, #{store_name, mode=IN, jdbcType=VARCHAR },"
			+ "#{active, mode=IN, jdbcType=BOOLEAN },#{latitude, mode=IN, jdbcType=VARCHAR },#{longitude, mode=IN, jdbcType=VARCHAR },#{radius, mode=IN, jdbcType=VARCHAR },#{uid, mode=IN, jdbcType=BINARY },#{address, mode=IN, jdbcType=VARCHAR },"
			+ "#{logo,mode=IN,jdbcType=LONGVARCHAR},#{to_email,mode=IN,jdbcType=VARCHAR},#{cc_email,mode=IN,jdbcType=VARCHAR},#{storemgr_email,mode=IN,jdbcType=VARCHAR},#{location_code,mode=IN,jdbcType=VARCHAR},#{time_zone,mode=IN,jdbcType=VARCHAR},#{postcode,mode=IN,jdbcType=VARCHAR})}")
	String addOrUpdateLocation(Location location);

	@Insert(value = "{ CALL SP_GA_UPDATESTORETAGS_DET( #{guid, mode=IN, jdbcType=BINARY},#{client_id, mode=IN, jdbcType=INTEGER},#{store_id, mode=IN, jdbcType=INTEGER},#{category_id, mode=IN, jdbcType=INTEGER},#{tag_id, mode=IN, jdbcType=INTEGER} )}")
	@Options(statementType = StatementType.CALLABLE)
	void addLocationTags(LocationTags t);

	@Delete("DELETE FROM GA_LOCATION_TAG_MAP WHERE GUID=#{guid} AND CLIENT_ID=#{client_id} AND STORE_ID=#{store_id}")
	int deleteLocationTags(@Param("guid") String guid, @Param("client_id") String client_id,
			@Param("store_id") String store_id);

	@Insert(value = "{CALL SP_GA_UPDATEACTIONEMAILCLIENT_DET(#{guid, mode=IN, jdbcType=BINARY},#{client_id, mode=IN, jdbcType=INTEGER},#{person_name, mode=IN, jdbcType=VARCHAR},#{person_email, mode=IN, jdbcType=VARCHAR},#{department, mode=IN, jdbcType=VARCHAR},#{isapproval_required, mode=IN, jdbcType=BOOLEAN},#{make_default, mode=IN, jdbcType=BOOLEAN})}")
	int insertActionemailforCompany(ActionPlanAssignee act);

	@Delete("DELETE FROM GA_CLIENTACTIONEMAIL_MT WHERE GUID=#{guid} AND CLIENT_ID=#{client_id}")
	int deleteActionemailCompany(@Param("guid") String guid, @Param("client_id") String client_id);

	@Insert(value = "{CALL SP_GA_UPDATEACTIONEMAIL_DET(#{guid, mode=IN, jdbcType=BINARY},#{client_id, mode=IN, jdbcType=INTEGER},#{store_id, mode=IN, jdbcType=INTEGER},#{person_name, mode=IN, jdbcType=VARCHAR},#{person_email, mode=IN, jdbcType=VARCHAR},#{department, mode=IN, jdbcType=VARCHAR},#{isapproval_required, mode=IN, jdbcType=BOOLEAN},#{make_default, mode=IN, jdbcType=BOOLEAN})}")
	int insertLoationActionemail(ActionPlanAssignee act);

	@Delete("DELETE FROM GA_STOREACTIONEMAIL_MT WHERE GUID=#{guid} AND CLIENT_ID=#{client_id} AND STORE_ID=#{store_id}")
	int deleteLocationActionemail(@Param("guid") String guid, @Param("client_id") String client_id,
			@Param("store_id") String store_id);

	@Select(value = "{ CALL SP_GA_GETTAGSFORLOCATION_DET( #{guid, mode=IN, jdbcType=BINARY},#{client_id, mode=IN, jdbcType=INTEGER},#{store_id, mode=IN, jdbcType=INTEGER} )}")
	@Options(statementType = StatementType.CALLABLE)
	List<LocationTags> getTagsBasedonLocation(@Param("guid") String guid, @Param("uid") String uid,
			@Param("client_id") String client_id, @Param("store_id") String store_id);

	@Select("SELECT * FROM GA_AUDITTYPE_MT WHERE GUID=#{guid} AND CLIENT_ID=#{client_id} AND AUDIT_GROUP_ID=1 AND AUDIT_TYPE_NAME=#{audit_type_name}")
	AuditName getAuditnameDetailsByCompany(@Param("guid") String guid, @Param("client_id") String client_id,
			@Param("audit_type_name") String audit_type_name);

	@Transactional(rollbackFor = Exception.class)
	@Select(value = "{ CALL SP_GA_UPDATE_AUDITTYPE_DET_PV3( #{guid, mode=IN, jdbcType=BINARY}, #{client_id, mode=IN, jdbcType=INTEGER},#{audit_group_id, mode=IN, jdbcType=INTEGER}, #{audit_type_id, mode=IN, jdbcType=VARCHAR },"
			+ "#{audit_type_name, mode=IN, jdbcType=VARCHAR },#{logo, mode=IN, jdbcType=LONGVARCHAR }, #{active, mode=IN, jdbcType=BOOLEAN },#{uid,mode=IN,jdbcType=BOOLEAN},"
			+ "#{signature1_label, mode=IN, jdbcType=VARCHAR },#{signature2_label, mode=IN, jdbcType=VARCHAR },#{signature3_label, mode=IN, jdbcType=VARCHAR },#{sign1_flag,mode=IN,jdbcType=BOOLEAN},#{sign2_flag,mode=IN,jdbcType=BOOLEAN},#{sign3_flag,mode=IN,jdbcType=BOOLEAN},"
			+ "#{is_man_sign1,mode=IN,jdbcType=BOOLEAN},#{is_man_sign2,mode=IN,jdbcType=BOOLEAN},#{is_man_sign3,mode=IN,jdbcType=BOOLEAN},#{person_seen,mode=IN,jdbcType=VARCHAR},#{person_seen_mandatory,mode=IN,jdbcType=BOOLEAN},#{showif_optional,mode=IN,jdbcType=BOOLEAN},#{audit_type_title,mode=IN,jdbcType=VARCHAR},#{hide_signature_app,mode=IN,jdbcType=BOOLEAN})}")
	AuditName insertUpdateAuditName(AuditName auditname);

	@Update(value = "{CALL SP_GA_UPDATE_AUDITTYPE_ORDER_DET(#{guid, mode=IN, jdbcType=BINARY},#{client_id, mode=IN, jdbcType=INTEGER},#{drag_index, mode=IN, jdbcType=INTEGER},"
			+ "#{drop_index, mode=IN, jdbcType=INTEGER})}")
	@Options(statementType = StatementType.CALLABLE)
	void AuditNameReorder(AuditName auditname);

	@Insert(value = "{CALL SP_GA_UPDATEACTIONEMAILAUDITNAME_DET(#{guid, mode=IN, jdbcType=BINARY},#{client_id, mode=IN, jdbcType=INTEGER},#{audit_type_id, mode=IN, jdbcType=INTEGER},#{person_name, mode=IN, jdbcType=VARCHAR},#{person_email, mode=IN, jdbcType=VARCHAR},#{department, mode=IN, jdbcType=VARCHAR},#{isapproval_required, mode=IN, jdbcType=BOOLEAN},#{make_default, mode=IN, jdbcType=BOOLEAN})}")
	void insertActionemailAuditName(ActionPlanAssignee act);

	@Select("SELECT COUNT(*) FROM GA_AUDITTYPE_MT WHERE GUID=#{guid} AND CLIENT_ID=#{client_id} AND AUDIT_GROUP_ID=1 AND AUDIT_TYPE_ID!=#{audit_type_id} AND AUDIT_TYPE_NAME=#{audit_type_name}")
	int getAuditnameDetailsByCompanyEdit(AuditName auditname);

	@Delete("DELETE FROM GA_AUDITNAMEACTIONEMAIL_MT WHERE GUID=#{guid} AND CLIENT_ID=#{client_id} AND AUDIT_TYPE_ID=#{audit_type_id}")
	void deleteActionemailAuditName(AuditName auditname);

	@Select("SELECT * FROM GA_AUDITNAMEACTIONEMAIL_MT WHERE GUID=#{guid} AND CLIENT_ID=#{client_id} AND AUDIT_TYPE_ID=#{audit_type_id}")
	List<ActionPlanAssignee> getActionemailByAuditname(AuditName auditname);

	@Select("SELECT * FROM GA_STOREACTIONEMAIL_MT WHERE GUID=#{guid} AND CLIENT_ID=#{client_id} AND STORE_ID=#{store_id}")
	List<ActionPlanAssignee> getActionemailByLocation(Location location);

	@Select("SELECT DISTINCT TC.* FROM GA_TAG_CATEGORY_MT TC,GA_TAG_MT TM WHERE TC.GUID=TM.GUID AND TC.CATEGORY_ID=TM.CATEGORY_ID AND TC.GUID=#{guid} AND TC.ACTIVE=1 AND TM.ACTIVE=1 AND TC.CATEGORY_TYPE_ID=#{type}")
	List<LocationTags> getTagCategories(String guid, int type);

	@Select("SELECT * FROM GA_TAG_MT WHERE GUID=#{guid} AND CATEGORY_ID=#{category_id} AND ACTIVE=1")
	List<LocationTags> getTagList(String guid, int category_id);

	@Select("SELECT * FROM GA_SCORERANGE_MST WHERE GUID=#{guid} AND CLIENT_ID=#{client_id} AND AUDIT_GROUP_ID=1 AND AUDIT_TYPE_ID=#{audit_type_id} ORDER BY SCORE_RANGE_ID ASC")
	List<ScoreRange> getScoreRange(@Param("guid") String guid, @Param("client_id") String client_id,
			@Param("audit_type_id") String audit_type_id);

	@Delete("DELETE FROM GA_SCORERANGE_MST WHERE GUID=#{guid} AND CLIENT_ID=#{client_id} AND AUDIT_GROUP_ID=1 AND AUDIT_TYPE_ID=#{audit_type_id}")
	void deleteScoreRange(ScoreRange scoreRange);

	@Transactional(rollbackFor = Exception.class)
	@Insert(value = "{CALL SP_GA_UPDATESCORERANGE_DET(#{guid, mode=IN, jdbcType=BINARY},#{client_id, mode=IN, jdbcType=INTEGER}, #{audit_group_id, mode=IN, jdbcType=INTEGER},#{audit_type_id, mode=IN, jdbcType=INTEGER},"
			+ "#{score_range_type, mode=IN, jdbcType=VARCHAR},#{min_value, mode=IN, jdbcType=DOUBLE},#{max_value, mode=IN, jdbcType=DOUBLE},#{grade_text, mode=IN, jdbcType=VARCHAR},#{grade_color, mode=IN, jdbcType=VARCHAR} )}")
	@Options(statementType = StatementType.CALLABLE)
	int addScoreRange(ScoreRange sc);

	@Select(value = "{CALL SP_GA_GETEMAILDIST_DET_PV3( #{guid, mode=IN, jdbcType=BINARY},#{uid, mode=IN, jdbcType=BINARY},#{client_id, mode=IN, jdbcType=INTEGER} )}")
	@Options(statementType = StatementType.CALLABLE)
	List<EmailTemplate> getEmailTemplates(EmailTemplate emailtemplate);

	@Insert(value = "{ CALL SP_GA_UPDATE_EMAILDIST_DET( #{guid, mode=IN, jdbcType=BINARY}, #{client_id, mode=IN, jdbcType=INTEGER}, #{audit_group_id, mode=IN, jdbcType=INTEGER }, #{audit_type_id, mode=IN, jdbcType=INTEGER },"
			+ "#{to_email, mode=IN, jdbcType=VARCHAR },#{cc_email, mode=IN, jdbcType=VARCHAR },#{email_sub, mode=IN, jdbcType=VARCHAR },#{email_body, mode=IN, jdbcType=VARCHAR } )}")
	void updateEmailTemplate(EmailTemplate emailTemplate);

	@Select("SELECT COMPANY_CLONE FROM GA_USERDET_MT WHERE GUID=#{guid} AND SUPER_USER=1")
	String getCompanyCloneFlag(String guid);

	@Select("SELECT COUNT(*) FROM GA_AUDITTYPE_MT WHERE GUID=#{guid} AND FIND_IN_SET(CLIENT_ID,#{client_id})")
	int ispreTemplateExistCompany(PreTemplates preTemplates);

	@Select("SELECT GROUP_CONCAT(CM.CLIENT_NAME SEPARATOR ' ,') FROM GA_AUDITTYPE_MT AT,GA_CLIENT_MT CM WHERE CM.GUID=AT.GUID AND CM.CLIENT_ID=AT.CLIENT_ID AND CM.GUID=#{guid} AND FIND_IN_SET(CM.CLIENT_ID,#{client_id})")
	String getClientnamesExistingCompany(PreTemplates preTemplates);

	@Transactional(rollbackFor = Exception.class)
	@Insert(value = "CALL SP_SETUP_CLONE_COMPANY(#{guid, mode=IN, jdbcType=BINARY},#{uid, mode=IN, jdbcType=BINARY}, #{client_id, mode=IN, jdbcType=INTEGER}, #{pre_client_id, mode=IN, jdbcType=INTEGER})")
	int cloneCompanies(PreTemplates preTemplates);

	@Select("SELECT GPS_LOCATION_FILTER_ENABLED FROM GA_USERDET_MT WHERE GUID=#{guid} AND SUPER_USER=1 LIMIT 1")
	boolean getGpsFlag(String guid);

	@Select(value = "{ CALL SP_GA_GETREPORTADMIN_DET_PV5( #{guid, mode=IN, jdbcType=BINARY}, #{uid, mode=IN, jdbcType=BINARY},#{client_id, mode=IN, jdbcType=INTEGER} ) }")
	@Options(statementType = StatementType.CALLABLE)
	List<Report> getReports(Report report);

	@Insert(value = "{ CALL SP_GA_UPDATE_REPORTCONFIG_DET_PV2( #{guid, mode=IN, jdbcType=BINARY}, #{client_id, mode=IN, jdbcType=INTEGER}, #{audit_group_id, mode=IN, jdbcType=INTEGER}, "
			+ "#{audit_type_id, mode=IN, jdbcType=INTEGER}, #{template_id, mode=IN, jdbcType=INTEGER}, #{description, mode=IN, jdbcType=VARCHAR}, #{report_logo, mode=IN, jdbcType=VARCHAR}, #{confidentiality, mode=IN, jdbcType=VARCHAR},#{client_displayname, mode=IN, jdbcType=VARCHAR},"
			+ "#{active,mode=IN,jdbcType=BOOLEAN},#{leftlogobi,mode=IN,jdbcType=VARCHAR} ) }")
	@Options(statementType = StatementType.CALLABLE)
	int UpdateReport(Report report);

	@Select(value = "{ CALL SP_GA_GETTEMPLATE_DETAILS_V1( #{guid, mode=IN, jdbcType=BINARY} )}")
	@Options(statementType = StatementType.CALLABLE)
	List<Report> getReportTemplates(String guid);

	@Select("SELECT CM.GUID,CM.CLIENT_ID,CM.CLIENT_NAME,CL.LOGO FROM GA_CLIENT_MT CM,GA_CLIENTLOGO_MT CL WHERE CM.GUID=(SELECT GUID FROM GA_USERDET_MT WHERE USER_NAME='templates@goaudits.com') AND CM.ACTIVE=1 AND CM.GUID=CL.GUID AND CM.CLIENT_ID=CL.CLIENT_ID ORDER BY CM.SORT_ORDER")
	List<Company> getPreexistingTemplates();

	@Select(value = "{CALL GA_SP_PORTAL_GET_AUDITNAMES_INDUSTRIES_V1(0, #{client_id, mode=IN, jdbcType=INTEGER})}")
	List<AuditName> getPreAuditnames(int client_id);

	@Select("{CALL GA_SP_PORTAL_GET_AUDITNAMES_INDUSTRIES()}")
	List<AuditName> getPreAuditnamesList();

	@Select("SELECT COUNT(*) FROM GA_QUESTION_MT WHERE GUID=(SELECT GUID FROM GA_USERDET_MT WHERE USER_NAME='templates@goaudits.com') AND CLIENT_ID=#{client_id} AND AUDIT_GROUP_ID=1 AND AUDIT_TYPE_ID=#{audit_type_id} AND ACTIVE=1")
	int getPretempletQuestionscount(@Param("client_id") int client_id, @Param("audit_type_id") int audit_type_id);

	@Select("SELECT * FROM GA_SECTION_MT WHERE GUID=(SELECT GUID FROM GA_USERDET_MT WHERE USER_NAME='templates@goaudits.com') AND CLIENT_ID=#{client_id} AND AUDIT_GROUP_ID=1 AND AUDIT_TYPE_ID=#{audit_type_id}")
	List<Section> getPreSections(Section section);

	@Select(value = "{CALL SP_GA_GETGROUP_DET_PV2(#{guid, mode=IN, jdbcType=BINARY},#{client_id, mode=IN, jdbcType=INTEGER}, #{audit_group_id, mode=IN, jdbcType=INTEGER},#{audit_type_id, mode=IN, jdbcType=INTEGER},"
			+ "#{section_id, mode=IN, jdbcType=INTEGER})}")
	@Options(statementType = StatementType.CALLABLE)
	List<Group> getallGroups(Group group);

	@Select(value = "{CALL SP_GA_GETQUESTION_DET_PV2( #{guid, mode=IN, jdbcType=BINARY}, #{client_id, mode=IN, jdbcType=INTEGER},#{audit_group_id, mode=IN, jdbcType=INTEGER}, #{audit_type_id, mode=IN, jdbcType=INTEGER}, #{section_id, mode=IN, jdbcType=INTEGER}, #{group_id, mode=IN, jdbcType=INTEGER},#{active, mode=IN, jdbcType=BOOLEAN} )}")
	@Options(statementType = StatementType.CALLABLE)
	List<Question> getallQuestions(Group group);

	@Select(value = "{ CALL SP_GA_GETCHOICESBASEDONPATID_DET_PV2(#{guid, mode=IN, jdbcType=BINARY},#{client_id, mode=IN, jdbcType=INTEGER},#{audit_group_id, mode=IN, jdbcType=INTEGER}, #{audit_type_id, mode=IN, jdbcType=INTEGER}, #{question_no, mode=IN, jdbcType=INTEGER},#{choice_pat_id, mode=IN, jdbcType=INTEGER} ) }")
	@Options(statementType = StatementType.CALLABLE)
	List<Choice> getchoicesforquestion(@Param("guid") String guid, @Param("client_id") int client_id,
			@Param("audit_group_id") int audit_group_id, @Param("audit_type_id") int audit_type_id,
			@Param("question_no") int question_no, @Param("choice_pat_id") int choice_pat_id);

	@Select("SELECT COUNT(*) FROM GA_AUDITTYPE_MT WHERE GUID=#{guid} AND FIND_IN_SET(CLIENT_ID,#{client_id}) AND AUDIT_TYPE_NAME=#{pre_audit_type_name}")
	String ispreTemplateExist(PreTemplates preTemplates);

	@Select("SELECT GROUP_CONCAT(CM.CLIENT_NAME SEPARATOR ' ,') FROM GA_AUDITTYPE_MT AT,GA_CLIENT_MT CM WHERE CM.GUID=AT.GUID AND CM.CLIENT_ID=AT.CLIENT_ID AND AT.GUID=#{guid} AND FIND_IN_SET(AT.CLIENT_ID,#{client_id}) AND AT.AUDIT_TYPE_NAME=#{pre_audit_type_name}")
	String getClientnamesExisting(PreTemplates preTemplates);

	@Transactional(rollbackFor = Exception.class)
	@Insert(value = "CALL SP_SETUP_DEFAULT_TEMPLATEADMIN_MT_PV2(#{guid, mode=IN, jdbcType=BINARY},#{uid, mode=IN, jdbcType=BINARY}, #{client_id, mode=IN, jdbcType=VARCHAR}, #{pre_client_id, mode=IN, jdbcType=INTEGER}, #{pre_audit_type_id, mode=IN, jdbcType=INTEGER})")
	int createPreTemplate(PreTemplates preTemplates);

	@Transactional(rollbackFor = Exception.class)
	@Insert(value = "CALL SP_SETUP_CLONE_AUDITNAME(#{guid, mode=IN, jdbcType=BINARY},#{uid, mode=IN, jdbcType=BINARY}, #{client_id, mode=IN, jdbcType=INTEGER}, #{pre_client_id, mode=IN, jdbcType=INTEGER},"
			+ " #{pre_audit_type_id, mode=IN, jdbcType=INTEGER},#{pre_audit_type_name, mode=IN, jdbcType=VARCHAR})")
	int cloneAuditName(PreTemplates preTemplates);

	@Select("SELECT VARIABLE_NAME as subject_name,VARIABLE_VALUE as subject_variables  FROM GA_EMAILDIST_VARIABLE_MT WHERE VARIABLE_TYPE=1 AND ACTIVE=1 ")
	List<EmailSubject> getEmailSubject(EmailTemplate emailtemplate);

	@Select(value = "{ CALL SP_GA_GETEMAILMESSAGEVARIABLES_DET(#{guid, mode=IN, jdbcType=BINARY},#{uid, mode=IN, jdbcType=BINARY},#{client_id, mode=IN, jdbcType=INTEGER}, #{audit_type_id, mode=IN, jdbcType=INTEGER} ) }")
	@Options(statementType = StatementType.CALLABLE)
	List<EmailMessage> getEmailMessage(EmailTemplate emailtemplate);

	@Select("SELECT * FROM GA_REPORTMPLIMAGE_MT WHERE TEMPLATE_ID=#{template_id}")
	List<ReportImage> getReportImages(int template_id);

	@Select("SELECT * FROM GA_GUIDEDSETUP_DT ORDER BY SORT_ORDER ASC")
	List<GuidedSetup> getGuidedSetupdata();

	@Select("SELECT COUNT(*) FROM GA_CLIENT_MT WHERE GUID=#{guid}")
	int getCompanyCount(String guid);

	@Update("UPDATE GA_USERDET_MT SET PRIMARY_GOAL=#{goal},NO_OF_LICENSES=#{no_of_licenses},PHONE_NUMBER=#{number},INDUSTRY=#{bussiness},WALKTHROUGH=#{walkthrough} WHERE GUID=#{guid} AND SUPER_USER=1")
	int createGuided(GuidedSetup gudsetp);
	
	@Select(value = "{ CALL SP_GA_GETLOCATIONBYCLIENT_PV5( #{guid, mode=IN, jdbcType=BINARY}, #{uid, mode=IN, jdbcType=BINARY},#{client_id, mode=IN, jdbcType=INTEGER},#{active, mode=IN, jdbcType=BOOLEAN},"
			+ "#{min, mode=IN, jdbcType=INTEGER},#{max, mode=IN, jdbcType=INTEGER},#{search_item, mode=IN, jdbcType=VARCHAR},#{sort, mode=IN, jdbcType=VARCHAR},#{order, mode=IN, jdbcType=VARCHAR} ) }")
	@Options(statementType = StatementType.CALLABLE)
	List<Location> getLocationsBasedonCompanyv2(Location location);

}
