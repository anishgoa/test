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

import com.goaudits.business.entity.AuditName;
import com.goaudits.business.entity.AuditWorkFlow;
import com.goaudits.business.entity.Company;
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

	@Select(value = "{ CALL SP_GA_GETREPORTTAGLIST_DET( #{guid, mode=IN, jdbcType=BINARY},#{uid, mode=IN, jdbcType=BINARY},#{client_id, mode=IN, jdbcType=VARCHAR})}")
	@Options(statementType = StatementType.CALLABLE)
	List<Tag> getAllReportTags(Tag tag);

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

	@Insert(value = "{ CALL SP_GA_ADDAUDITWORKFLOW_DET( #{guid, mode=IN, jdbcType=BINARY},#{client_id, mode=IN, jdbcType=VARCHAR},#{store_id, mode=IN, jdbcType=VARCHAR},#{audit_type_id, mode=IN, jdbcType=VARCHAR},#{assignee, mode=IN, jdbcType=VARCHAR}," +
			"#{workflow_type, mode=IN, jdbcType=BOOLEAN},#{uuid, mode=IN, jdbcType=VARCHAR},#{signature_required, mode=IN, jdbcType=BOOLEAN})}")
	@Options(statementType = StatementType.CALLABLE)
	int addAuditWorkFlow(@Param("guid") String guid,@Param("client_id") String client_id,@Param("store_id") String store_id,@Param("audit_type_id") String audit_type_id,@Param("assignee") String assignee,
			@Param("workflow_type") boolean workflow_type,@Param("uuid") String uuid,@Param("signature_required") boolean signature_required);

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

	
}