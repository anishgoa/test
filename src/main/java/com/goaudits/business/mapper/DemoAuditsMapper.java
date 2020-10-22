package com.goaudits.business.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.mapping.StatementType;

import com.goaudits.business.entity.DemoAudits;

@Mapper
public interface DemoAuditsMapper {
	@Select(value = { "CALL SP_GA_APP_GET_APPROVER_AUDITS_LIST_V2_DEMO()" })
	@Options(statementType = StatementType.CALLABLE)
	List<DemoAudits> getDemoAuditsList();

	@Select(value = "{ CALL SP_GA_GETAUDITSTATUS_DET_PV3( #{guid, mode=IN, jdbcType=BINARY}, #{uid, mode=IN, jdbcType=BINARY},#{client_id, mode=IN, jdbcType=INTEGER},"
			+ "#{audit_type_id, mode=IN, jdbcType=VARCHAR},#{store_id, mode=IN, jdbcType=VARCHAR},#{start_date, mode=IN, jdbcType=VARCHAR},#{end_date, mode=IN, jdbcType=VARCHAR}, #{status, mode=IN, jdbcType=VARCHAR},#{tab, mode=IN, jdbcType=VARCHAR} ) }")
	@Options(statementType = StatementType.CALLABLE)
	List<DemoAudits> getManageAuditsList(DemoAudits audits);


	@Select(value = "{ CALL SP_GA_GETAUDITSTATUS_DET_PV4( #{guid, mode=IN, jdbcType=BINARY}, #{uid, mode=IN, jdbcType=BINARY},#{client_id, mode=IN, jdbcType=INTEGER},"
			+ "#{audit_type_id, mode=IN, jdbcType=VARCHAR},#{store_id, mode=IN, jdbcType=VARCHAR},#{start_date, mode=IN, jdbcType=VARCHAR},"
			+ "#{end_date, mode=IN, jdbcType=VARCHAR}, #{status, mode=IN, jdbcType=VARCHAR},#{tab, mode=IN, jdbcType=VARCHAR},#{min, mode=IN, jdbcType=INTEGER},#{max, mode=IN, jdbcType=INTEGER} ) }")
	@Options(statementType = StatementType.CALLABLE)
	List<DemoAudits> getManageAuditsListv1(DemoAudits audits);
}
