package com.goaudits.business.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.mapping.StatementType;

import com.goaudits.business.entity.Notification;

@Mapper
public interface NotificationMapper {

	
	@Insert(value = "{ CALL GA_SP_PORTAL_CREATE_ACTIONTASK_NOTIFICATION( #{guid, mode=IN, jdbcType=BINARY}, #{client_id, mode=IN, jdbcType=VARCHAR},#{audit_type_id, mode=IN, jdbcType=VARCHAR}," +
			" #{notification_type, mode=IN, jdbcType=VARCHAR},#{frequency, mode=IN, jdbcType=VARCHAR},#{trigger_day, mode=IN, jdbcType=VARCHAR},#{trigger_date, mode=IN, jdbcType=VARCHAR}," +
			"#{trigger_time, mode=IN, jdbcType=VARCHAR},#{time_zone, mode=IN, jdbcType=VARCHAR},#{email_list, mode=IN, jdbcType=VARCHAR})}")
	@Options(statementType = StatementType.CALLABLE)
	int createActionTaskNotification(Notification notification);

	
	@Insert(value = "{ CALL GA_SP_PORTAL_EDIT_ACTIONTASK_NOTIFICATION( #{guid, mode=IN, jdbcType=BINARY}, #{notification_id, mode=IN, jdbcType=VARCHAR}," +
			" #{notification_type, mode=IN, jdbcType=VARCHAR},#{trigger_time, mode=IN, jdbcType=VARCHAR},#{email_list, mode=IN, jdbcType=VARCHAR})}")
	@Options(statementType = StatementType.CALLABLE)
	int editActionTaskNotification(Notification notification);

	@Insert(value = "{ CALL GA_SP_PORTAL_CREATE_ACTIONSUMMARY_NOTIFICATION( #{guid, mode=IN, jdbcType=BINARY}, #{client_id, mode=IN, jdbcType=VARCHAR},#{audit_type_id, mode=IN, jdbcType=VARCHAR}," +
			" #{notification_type, mode=IN, jdbcType=VARCHAR},#{frequency, mode=IN, jdbcType=VARCHAR},#{trigger_day, mode=IN, jdbcType=VARCHAR},#{trigger_date, mode=IN, jdbcType=VARCHAR}," +
			"#{trigger_time, mode=IN, jdbcType=VARCHAR},#{time_zone, mode=IN, jdbcType=VARCHAR},#{email_list, mode=IN, jdbcType=VARCHAR})}")
	@Options(statementType = StatementType.CALLABLE)
	int createActionSummaryNotification(Notification notification);


	@Select(value = "{ CALL GA_SP_PORTAL_GET_ACTION_NOTIFICATION_LIST( #{guid, mode=IN, jdbcType=BINARY}, #{client_id, mode=IN, jdbcType=VARCHAR},#{notification_type, mode=IN, jdbcType=VARCHAR})}")
	@Options(statementType = StatementType.CALLABLE)
	List<Notification> getActionPlanNotification(@Param("guid")String guid,@Param("client_id") int client_id,
			@Param("notification_type") int notification_type);


	@Delete("DELETE FROM GA_NOTIFICATION_MT WHERE NOTIFICATION_ID=#{notification_id}")
	int deleteNotification(Notification notification);

}
