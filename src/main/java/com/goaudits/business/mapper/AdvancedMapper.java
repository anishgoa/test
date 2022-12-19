package com.goaudits.business.mapper;

import java.util.LinkedHashMap;
import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.mapping.StatementType;

import com.goaudits.business.entity.ActionPlanSettings;
import com.goaudits.business.entity.AuditName;
import com.goaudits.business.entity.AuditWorkFlow;
import com.goaudits.business.entity.Broadcast;
import com.goaudits.business.entity.Company;
import com.goaudits.business.entity.CustomFieldList;
import com.goaudits.business.entity.Customfields;
import com.goaudits.business.entity.FileNameChecklist;
import com.goaudits.business.entity.GroupAudit;
import com.goaudits.business.entity.Location;
import com.goaudits.business.entity.LocationTags;
import com.goaudits.business.entity.Personseen;
import com.goaudits.business.entity.Tag;
import com.goaudits.business.entity.User;

@Mapper
public interface AdvancedMapper {

	@Select(value = "{ CALL SP_GA_GETPERSONSEENEMAILS_DET( #{guid, mode=IN, jdbcType=BINARY} )}")
	@Options(statementType = StatementType.CALLABLE)
	List<Personseen> getPersonseenList(String guid);

	@Select(value = "{ CALL SP_GA_GETPERSONSEENCLIENTS_DET( #{guid, mode=IN, jdbcType=BINARY},#{email, mode=IN, jdbcType=VARCHAR} )}")
	@Options(statementType = StatementType.CALLABLE)
	List<Company> getpersonseenClients(@Param("guid") String guid, @Param("email") String email);

	@Select(value = "{ CALL SP_GA_GETPERSONSEENAUDITNAMES_DET( #{guid, mode=IN, jdbcType=BINARY},#{email, mode=IN, jdbcType=VARCHAR},#{client_id, mode=IN, jdbcType=VARCHAR} )}")
	@Options(statementType = StatementType.CALLABLE)
	List<AuditName> getPersonseenAudits(@Param("guid") String guid, @Param("email") String email,
			@Param("client_id") String client_id);

	@Select(value = "{ CALL SP_GA_GETPERSONSEENLOCATIONS_DET( #{guid, mode=IN, jdbcType=BINARY},#{email, mode=IN, jdbcType=VARCHAR},#{client_id, mode=IN, jdbcType=VARCHAR},#{audit_type_id, mode=IN, jdbcType=VARCHAR} )}")
	@Options(statementType = StatementType.CALLABLE)
	List<Location> getPersonseenLocations(@Param("guid") String guid, @Param("email") String email,
			@Param("client_id") String client_id, @Param("audit_type_id") String audit_type_id);

	@Select(value = "{ CALL SP_GA_VALIDATEADDPERSONSEEN_DET( #{guid, mode=IN, jdbcType=BINARY},#{person_email, mode=IN, jdbcType=VARCHAR},#{client_id, mode=IN, jdbcType=VARCHAR},#{store_ids, mode=IN, jdbcType=VARCHAR},#{audit_type_ids, mode=IN, jdbcType=VARCHAR} )}")
	@Options(statementType = StatementType.CALLABLE)
	List<LinkedHashMap> validateaddPersonSeen(Personseen pseen);

	@Insert(value = "{ CALL SP_GA_ADDOREDITPERSONSEEN_DET( #{guid, mode=IN, jdbcType=BINARY}, #{id, mode=IN, jdbcType=INTEGER},#{client_id, mode=IN, jdbcType=INTEGER},#{audit_type_id, mode=IN, jdbcType=INTEGER},#{store_id, mode=IN, jdbcType=INTEGER},#{person_name, mode=IN, jdbcType=VARCHAR},#{person_email, mode=IN, jdbcType=VARCHAR} ,#{active, mode=IN, jdbcType=BOOLEAN} )}")
	@Options(statementType = StatementType.CALLABLE)
	int addorEditPersonSeen(Personseen p);

	@Select(value = "{ CALL SP_GA_VALIDATEEDITPERSONSEEN_DET( #{guid, mode=IN, jdbcType=BINARY},#{person_email, mode=IN, jdbcType=VARCHAR},#{client_id, mode=IN, jdbcType=VARCHAR},#{store_ids, mode=IN, jdbcType=VARCHAR},#{audit_type_ids, mode=IN, jdbcType=VARCHAR},#{fromclient_id, mode=IN, jdbcType=VARCHAR},#{fromemail, mode=IN, jdbcType=VARCHAR} )}")
	@Options(statementType = StatementType.CALLABLE)
	List<LinkedHashMap> validateeditPersonSeen(Personseen pseen);

	@Delete("DELETE FROM GA_PERSONSEEN_MT WHERE GUID=#{guid} AND CLIENT_ID=#{fromclient_id}  AND PERSON_EMAIL=#{fromemail} ")
	int deletePersonSeen(Personseen p);

	@Select(value = "{ CALL SP_GA_UPDATETAGS_DET( #{guid, mode=IN, jdbcType=BINARY},#{category_id, mode=IN, jdbcType=INTEGER},#{tag_id, mode=IN, jdbcType=INTEGER},#{tag_name, mode=IN, jdbcType=VARCHAR},#{active, mode=IN, jdbcType=BOOLEAN} )}")
	@Options(statementType = StatementType.CALLABLE)
	int addTags(LocationTags loct);

	@Select("SELECT COUNT(*) FROM GA_TAG_MT WHERE GUID=#{guid} AND CATEGORY_ID=#{category_id} AND TAG_NAME=#{tag_name}")
	int validateTags(LocationTags loct);

	@Select("SELECT COUNT(*) FROM GA_TAG_MT WHERE GUID=#{guid} AND CATEGORY_ID=#{category_id} AND TAG_NAME=#{tag_name} AND TAG_ID!=#{tag_id}")
	int validateedTags(LocationTags loct);

	@Select("SELECT COUNT(*) FROM GA_TAG_CATEGORY_MT WHERE GUID=#{guid} AND CATEGORY_NAME=#{category_name}")
	int validateCategory(LocationTags tgs);

	@Select("SELECT COUNT(*) FROM GA_TAG_CATEGORY_MT WHERE GUID=#{guid} AND CATEGORY_ID!=#{category_id} AND CATEGORY_NAME=#{category_name}")
	int validateEdCategory(LocationTags tgs);

	@Select(value = "{ CALL SP_GA_UPDATETAGCATEGORY_DET( #{guid, mode=IN, jdbcType=BINARY},#{category_id, mode=IN, jdbcType=INTEGER},#{category_name, mode=IN, jdbcType=VARCHAR},#{category_desription, mode=IN, jdbcType=VARCHAR},#{active, mode=IN, jdbcType=BOOLEAN},#{category_type_id, mode=IN, jdbcType=INTEGER} )}")
	@Options(statementType = StatementType.CALLABLE)
	int addTagCategory(LocationTags tgs);

	@Select("SELECT * FROM GA_TAG_CATEGORY_MT WHERE GUID=#{guid}")
	List<LocationTags> getTagCategories(String guid);

	@Select("SELECT * FROM GA_TAG_MT WHERE GUID=#{guid} AND CATEGORY_ID=#{category_id}")
	List<LocationTags> getTagList(@Param("guid") String guid, @Param("category_id") int category_id);

	@Select(value = "{ CALL SP_GA_GETAUDITWORKFLOW_DET( #{guid, mode=IN, jdbcType=BINARY},#{uid, mode=IN, jdbcType=BINARY},#{type, mode=IN, jdbcType=BOOLEAN})}")
	@Options(statementType = StatementType.CALLABLE)
	List<AuditWorkFlow> getAuditWorkflowList(AuditWorkFlow workflow);

	@Select(value = "{ CALL SP_GA_GETADMINLIST_DET( #{guid, mode=IN, jdbcType=BINARY},#{client_id, mode=IN, jdbcType=VARCHAR},#{audit_type_id, mode=IN, jdbcType=VARCHAR},#{store_id, mode=IN, jdbcType=VARCHAR})}")
	@Options(statementType = StatementType.CALLABLE)
	List<User> getAdminlist(AuditWorkFlow auditWorkFlow);

	@Insert(value = "{ CALL SP_GA_ADDAUDITWORKFLOW_DET( #{guid, mode=IN, jdbcType=BINARY},#{client_id, mode=IN, jdbcType=VARCHAR},#{store_id, mode=IN, jdbcType=VARCHAR},#{audit_type_id, mode=IN, jdbcType=VARCHAR},#{assignee, mode=IN, jdbcType=VARCHAR},"
			+ "#{workflow_type, mode=IN, jdbcType=BOOLEAN},#{uuid, mode=IN, jdbcType=VARCHAR},#{signature_required, mode=IN, jdbcType=BOOLEAN})}")
	@Options(statementType = StatementType.CALLABLE)
	int addAuditWorkFlow(@Param("guid") String guid, @Param("client_id") String client_id,
			@Param("store_id") String store_id, @Param("audit_type_id") String audit_type_id,
			@Param("assignee") String assignee, @Param("workflow_type") boolean workflow_type,
			@Param("uuid") String uuid, @Param("signature_required") boolean signature_required);

	@Delete("DELETE FROM GA_WORKFLOW_DT WHERE UUID=#{uuid}")
	int deleteAuditWorkFlow(@Param("uuid") String uuid);

	@Select("SELECT COUNT(*) FROM GA_LOCATION_TAG_MAP WHERE GUID=#{guid} AND CATEGORY_ID=#{category_id} AND TAG_ID=#{tag_id}")
	int checkTagsAssigned(LocationTags loct);

	@Delete("DELETE FROM GA_TAG_MT WHERE GUID=#{guid} AND CATEGORY_ID=#{category_id} AND TAG_ID=#{tag_id}")
	int deleteTag(LocationTags tgs);

	@Select("SELECT COUNT(*) FROM GA_LOCATION_TAG_MAP WHERE GUID=#{guid} AND CATEGORY_ID=#{category_id}")
	int checkTagsCategoryAssigned(LocationTags loct);

	@Delete(value = "{ CALL SP_GA_DELETETAGCATEGORY_DET( #{guid, mode=IN, jdbcType=BINARY},#{category_id, mode=IN, jdbcType=INTEGER} )}")
	@Options(statementType = StatementType.CALLABLE)
	int deleteTagCategory(LocationTags tgs);

	@Select(value = "{ CALL SP_GA_GETCUSTOMFIELDS_DET_PV3( #{guid, mode=IN, jdbcType=BINARY}, #{uid, mode=IN, jdbcType=BINARY},#{client_id, mode=IN, jdbcType=INTEGER} ) }")
	@Options(statementType = StatementType.CALLABLE)
	List<Customfields> getAllCustomfields(@Param("guid") String guid, @Param("uid") String uid,
			@Param("client_id") String client_id);

	@Select(value = "{ CALL GA_SP_PORTAL_GET_CUSTOMFIELDS_MT( #{guid, mode=IN, jdbcType=BINARY}, #{uid, mode=IN, jdbcType=BINARY},#{client_id, mode=IN, jdbcType=INTEGER},#{active, mode=IN, jdbcType=BOOLEAN} ) }")
	@Options(statementType = StatementType.CALLABLE)
	List<CustomFieldList> getCustomFieldsList(AuditName audit);

	@Select("SELECT * FROM GA_CUSTOM_ACTION_PRIORITIES_MT WHERE GUID=#{guid} AND CLIENT_ID=#{client_id}  AND AUDIT_GROUP_ID=1 AND AUDIT_TYPE_ID=#{audit_type_id}  AND ACTIVE=#{active}")
	List<ActionPlanSettings> getActionPlanSettngs(ActionPlanSettings ActionPlanSettings);

	@Select("SELECT COUNT(*) FROM GA_CUSTOM_ACTION_PRIORITIES_MT WHERE GUID=#{guid} AND CLIENT_ID=#{client_id}  AND AUDIT_GROUP_ID=1 AND AUDIT_TYPE_ID=#{audit_type_id} AND PRIORITY_NAME=#{priority_name}")
	int validateaddActionSts(ActionPlanSettings actionPlanSettings);

	@Select("SELECT COUNT(*) FROM GA_CUSTOM_ACTION_PRIORITIES_MT WHERE GUID=#{guid} AND CLIENT_ID=#{client_id}  AND AUDIT_GROUP_ID=1 AND AUDIT_TYPE_ID=#{audit_type_id} AND PRIORITY_NAME=#{priority_name} AND PRIORITY_ID!=#{priority_id}")
	int validateeditActionSts(ActionPlanSettings actionPlanSettings);

	@Select(value = "{ CALL SP_GA_UPDATEACTIONPLANSETTINGS_DET( #{guid, mode=IN, jdbcType=BINARY},#{client_id, mode=IN, jdbcType=INTEGER},#{audit_type_id, mode=IN, jdbcType=INTEGER},#{priority_id, mode=IN, jdbcType=INTEGER},#{priority_name, mode=IN, jdbcType=VARCHAR},#{priority_color, mode=IN, jdbcType=VARCHAR} ,#{default_due_days, mode=IN, jdbcType=INTEGER},#{active, mode=IN, jdbcType=BOOLEAN},#{make_default, mode=IN, jdbcType=BOOLEAN} )}")
	@Options(statementType = StatementType.CALLABLE)
	int addOrEditActionPlanSettngs(ActionPlanSettings actionPlanSettings);

	@Select("SELECT COUNT(*) FROM GA_CUSTOMFIELDS_MT WHERE GUID = #{guid} AND CLIENT_ID = #{client_id} AND AUDIT_TYPE_ID = #{audit_type_id} AND FIELD_NAME=#{field_name}")
	int isCustomfieldsExist(Customfields customfields);

	@Insert(value = "{ CALL SP_GA_UPDATE_CUSTOMFIELDS_DET_PV2( #{guid, mode=IN, jdbcType=BINARY}, #{client_id, mode=IN, jdbcType=INTEGER}, #{audit_group_id, mode=IN, jdbcType=INTEGER}, "
			+ "#{audit_type_id, mode=IN, jdbcType=VARCHAR}, #{field_name, mode=IN, jdbcType=VARCHAR}, #{field_label, mode=IN, jdbcType=VARCHAR}, #{field_type, mode=IN, jdbcType=VARCHAR}, #{pactive, mode=IN, jdbcType=BOOLEAN},#{show_app_list, mode=IN, jdbcType=BOOLEAN} ) }")
	int addCustomfields(Customfields customfields);

	@Select(value = "{ CALL SP_GA_GETREPORTTAGLIST_DET( #{guid, mode=IN, jdbcType=BINARY},#{uid, mode=IN, jdbcType=BINARY},#{client_id, mode=IN, jdbcType=VARCHAR})}")
	@Options(statementType = StatementType.CALLABLE)
	List<Tag> getAllReportTags(@Param("guid") String guid, @Param("uid") String uid, @Param("client_id") int client_id);

	@Select(value = "{ CALL SP_GA_GETREPORTTAGLIST_DET_V2( #{guid, mode=IN, jdbcType=BINARY},#{uid, mode=IN, jdbcType=BINARY},#{client_id, mode=IN, jdbcType=VARCHAR})}")
	@Options(statementType = StatementType.CALLABLE)
	List<Tag> getAllReportTagsV2(@Param("guid") String guid, @Param("uid") String uid,
			@Param("client_id") int client_id);

	@Select("SELECT COUNT(*) FROM GA_AUDIT_REPORTTAG_MT WHERE GUID=#{guid} AND CLIENT_ID=#{client_id} AND AUDIT_GROUP_ID=1 AND AUDIT_TYPE_ID=#{audit_type_id} AND TAG_CODE=#{tag_code}")
	int validateaddTag(Tag tag);

	@Insert(value = "{ CALL SP_GA_ADDREPORTTAG_DET( #{guid, mode=IN, jdbcType=BINARY},#{client_id, mode=IN, jdbcType=VARCHAR},#{audit_type_id, mode=IN, jdbcType=VARCHAR},"
			+ "#{tag_code, mode=IN, jdbcType=VARCHAR},#{tag_description, mode=IN, jdbcType=VARCHAR},#{passing_level, mode=IN, jdbcType=VARCHAR},#{id, mode=IN, jdbcType=VARCHAR})}")
	@Options(statementType = StatementType.CALLABLE)
	int addReportTag(Tag tag);

	@Select("SELECT COUNT(*) FROM GA_AUDIT_REPORTTAG_MT WHERE ID!=#{id} AND GUID=#{guid} AND CLIENT_ID=#{client_id} AND AUDIT_GROUP_ID=1 AND AUDIT_TYPE_ID=#{audit_type_id} AND TAG_CODE=#{tag_code}")
	int validateeditTag(Tag tag);

	@Select(value = "{ CALL SP_GA_GETGROUPAUDIT_DET( #{guid, mode=IN, jdbcType=BINARY},#{active, mode=IN, jdbcType=BOOLEAN})}")
	@Options(statementType = StatementType.CALLABLE)
	List<GroupAudit> getGroupAudit(@Param("guid") String guid, @Param("active") boolean active);

	@Select(value = "{ CALL SP_GA_GROUPAUDIT_AUDITNAMES_DET( #{guid, mode=IN, jdbcType=BINARY},#{uid, mode=IN, jdbcType=BINARY},#{client_id, mode=IN, jdbcType=INTEGER},#{parent_audit_id, mode=IN, jdbcType=INTEGER})}")
	@Options(statementType = StatementType.CALLABLE)
	List<AuditName> getAuditTypeList(String guid, String uid, int client_id, int parent_audit_id);

	@Insert(value = "{ CALL SP_GA_ADDGROUPAUDIT_DET( #{guid, mode=IN, jdbcType=BINARY},#{client_id, mode=IN, jdbcType=INTEGER},#{parent_audit_id, mode=IN, jdbcType=VARCHAR},#{parent_audit_type_name, mode=IN, jdbcType=VARCHAR},#{sub_title, mode=IN, jdbcType=VARCHAR}," +
			"#{logo, mode=IN, jdbcType=VARCHAR},#{active, mode=IN, jdbcType=BOOLEAN},#{audit_type_ids, mode=IN, jdbcType=VARCHAR},#{toemail, mode=IN, jdbcType=VARCHAR},#{ccemail, mode=IN, jdbcType=VARCHAR})}")
	@Options(statementType = StatementType.CALLABLE)
	int addGroupAudit(GroupAudit groupAudit);

	@Select("SELECT COUNT(*) FROM GA_AUDITTYPE_PARENT_MT WHERE GUID=#{guid} AND PARENT_AUDIT_TYPE_NAME=#{parent_audit_type_name}")
	int validateGroupName(GroupAudit GroupAudit);
	
	@Select("SELECT COUNT(*) FROM GA_AUDITTYPE_PARENT_MT WHERE GUID=#{guid} AND PARENT_AUDIT_ID!=#{parent_audit_id} AND PARENT_AUDIT_TYPE_NAME=#{parent_audit_type_name}")
	int validateGroupName1(GroupAudit GroupAudit);

	@Select(value = "{ CALL GA_SP_PORTAL_GET_MASTERDATA_BROADCAST_LIST( #{guid, mode=IN, jdbcType=BINARY})}")
	@Options(statementType = StatementType.CALLABLE)
	List<Broadcast> getBroadcastList(Broadcast broadcast);


	@Select(value = "{ CALL GA_SP_PORTAL_GET_MASTERDATA_BROADCAST_LISTV1( #{guid, mode=IN, jdbcType=BINARY},#{min, mode=IN, jdbcType=INTEGER},#{max, mode=IN, jdbcType=INTEGER},#{search_item, mode=IN, jdbcType=VARCHAR})}")
	@Options(statementType = StatementType.CALLABLE)
	List<Broadcast> getBroadcastdetailsv1(Broadcast broadcast);

	@Select(value = "{ CALL GA_SP_PORTAL_BROADCAST_DETILS( #{guid, mode=IN, jdbcType=BINARY},#{uid, mode=IN, jdbcType=BINARY})}")
	@Options(statementType = StatementType.CALLABLE)
	List<Broadcast> getBroadcastdetails(Broadcast broadcast);

	@Select(value = "{ CALL GA_SP_PORTAL_ADD_MASTERDATA_BROADCAST( #{guid, mode=IN, jdbcType=BINARY},#{broadcast_message, mode=IN, jdbcType=VARCHAR},#{created_by_uid, mode=IN, jdbcType=VARCHAR},#{broadcast_header, mode=IN, jdbcType=VARCHAR})}")
	@Options(statementType = StatementType.CALLABLE)
	int addPublish(Broadcast broadcast);

	
	@Select(value = "{ CALL GA_SP_PORTAL_ADD_TRANSDATA_BROADCAST( #{guid, mode=IN, jdbcType=BINARY},#{uid, mode=IN, jdbcType=BINARY},#{client_id, mode=IN, jdbcType=VARCHAR},#{broadcast_id, mode=IN, jdbcType=INTEGER},#{created_at, mode=IN, jdbcType=VARCHAR})}")
	@Options(statementType = StatementType.CALLABLE)
	int addPublishTrans(Broadcast broadcast);

	@Select(value = "{ CALL GA_SP_PORTAL_GET_MASTERDATA_BROADCASTDETAILS_LIST( #{guid, mode=IN, jdbcType=BINARY},#{uid, mode=IN, jdbcType=BINARY})}")
	@Options(statementType = StatementType.CALLABLE)
	List<Broadcast> getBroadcastListDetails(Broadcast broadcast);

	@Select("SELECT BROADCAST_ENABLED FROM GA_USERDET_MT WHERE GUID=#{guid} AND SUPER_USER=1 LIMIT 1")
	boolean getBroadcastflag(Broadcast broadcast);

	@Insert("INSERT INTO `GA_CUSTOMFIELDVALUES_MT`(`GUID`,`CLIENT_ID`,`AUDIT_GROUP_ID`,`AUDIT_TYPE_ID`,`FIELD_NAME`,`FIELD_VALUE`,`ACTIVE`,`LAST_MODIFIED`) VALUES (#{guid},#{client_id},1,#{audit_type_id},#{field_name},#{field_value},1,now())")
	int addCustomfieldsList(Customfields customfields);

	@Delete("DELETE FROM GA_CUSTOMFIELDVALUES_MT WHERE GUID=#{guid} AND CLIENT_ID=#{client_id} AND AUDIT_TYPE_ID=#{audit_type_id} AND FIELD_NAME=#{field_name}")
	int deleteCustomFields(Customfields customfields);

	@Select("SELECT FIELD_VALUE FROM GA_CUSTOMFIELDVALUES_MT WHERE GUID=#{guid} AND CLIENT_ID=#{client_id} AND AUDIT_TYPE_ID=#{audit_type_id} AND FIELD_NAME=#{field_name} ")
	List<Customfields> getCustomfieldvalues(Customfields customfields);

	@Insert("INSERT INTO `GA_PDF_FILENAME_FORMAT_MT` (`GUID`, `CLIENT_ID`, `AUDIT_TYPE_ID`, `REPORT_ID_ENABLED`,`CLIENT_NAME_ENABLED`, `CHECKLIST_NAME_ENABLED`, `STORE_NAME_ENABLED`, `AUDIT_DATE_ENBALED`, `CUSTOM_FIELD_ENABLED`, `CUSTOM_FIELD_NAME`,`LOCATION_CODE_ENABLED`) values (#{guid},#{client_id},#{audit_type_id},#{report_id_enabled},#{client_name_enabled},#{checklist_name_enabled},#{store_name_enabled},#{audit_date_enabled},#{custom_field_enabled},#{custom_field_name},#{location_code_enabled})")
	int addFileNameChecklist(FileNameChecklist fnchecklist);

	@Select("SELECT * FROM GA_PDF_FILENAME_FORMAT_MT WHERE GUID=#{guid} AND CLIENT_ID=#{client_id} AND AUDIT_TYPE_ID=#{audit_type_id} ")
	List<FileNameChecklist> getFilenameChecklist(FileNameChecklist fileNameChecklist);

	
	@Select("SELECT COUNT(*) FROM GA_PDF_FILENAME_FORMAT_MT WHERE GUID = #{guid} AND CLIENT_ID = #{client_id} AND AUDIT_TYPE_ID = #{audit_type_id}")
	int isFileNameChecklistExist(FileNameChecklist fileNameChecklist);

	@Insert(value = "{CALL GA_PDF_UPDATE_FILENAME_FORMAT( #{guid, mode=IN, jdbcType=BINARY}, #{client_id, mode=IN, jdbcType=INTEGER},#{audit_type_id, mode=IN, jdbcType=INTEGER},"
			+ "#{report_id_enabled, mode=IN, jdbcType=INTEGER},#{client_name_enabled, mode=IN, jdbcType=INTEGER},"
			+ "#{checklist_name_enabled, mode=IN, jdbcType=INTEGER},#{store_name_enabled, mode=IN, jdbcType=INTEGER} ,"
			+ "#{audit_date_enabled, mode=IN, jdbcType=INTEGER},#{location_code_enabled, mode=IN, jdbcType=INTEGER},"
			+ "#{custom_field_enabled, mode=IN, jdbcType=INTEGER},#{custom_field_name, mode=IN, jdbcType=VARCHAR} )}")
	@Options(statementType = StatementType.CALLABLE)
	int updateFileNameChecklist(FileNameChecklist fileNameChecklist);
	
}