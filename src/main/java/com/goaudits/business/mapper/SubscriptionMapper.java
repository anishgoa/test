package com.goaudits.business.mapper;

import java.util.LinkedHashMap;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.mapping.StatementType;

import com.goaudits.business.entity.Feature;
import com.goaudits.business.entity.Subscriptionentity;

@Mapper
public interface SubscriptionMapper {

	@Select(value = "{ CALL SP_GA_GETSUBSCRIPTIONS_DET( #{guid, mode=IN, jdbcType=BINARY} )}")
	@Options(statementType = StatementType.CALLABLE)
	List<Subscriptionentity> getSubscriptionList(String guid);

	@Select("SELECT * FROM GA_FEATURE_MT WHERE FEATURE_ID=#{feature_id}")
	@Options(statementType = StatementType.CALLABLE)
	List<Feature> getFeatureList(@Param("feature_id") int feature_id);

	@Select("SELECT COUNT(*) FROM GA_AUDITTYPE_MT AT,GA_CLIENT_MT CM  WHERE AT.GUID=CM.GUID AND AT.CLIENT_ID=CM.CLIENT_ID AND AT.ACTIVE=1 AND AT.GUID=#{guid} AND CM.ACTIVE=1")
	int getAuditNameCount(String guid);

	@Select("SELECT COUNT(*) FROM GA_CLIENT_MT WHERE GUID=#{guid} AND ACTIVE=1")
	int getCompanyCount(String guid);

	@Select("SELECT COUNT(*) FROM GA_STORE_MT ST,GA_CLIENT_MT CM WHERE ST.GUID=CM.GUID AND ST.CLIENT_ID=CM.CLIENT_ID AND ST.GUID=#{guid} AND ST.ACTIVE=1 AND CM.ACTIVE=1")
	int getLocationCount(String guid);

	@Select("SELECT COUNT(*) FROM GA_USERDET_MT WHERE GUID=#{guid} AND ACTIVE=1")
	int getUsersCount(String guid);

	@Select(value = "{ CALL SP_GA_GETPAYMENTINFO_DET()}")
	@Options(statementType = StatementType.CALLABLE)
	List<LinkedHashMap> getpaymentinfo();

}
