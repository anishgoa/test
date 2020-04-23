package com.goaudits.business.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.mapping.StatementType;
import com.goaudits.business.entity.Questactimage;
import com.goaudits.business.entity.Question;
@Mapper
public interface S3Mapper {

	@Insert(value = "{CALL SP_GA_UPDATE_QUESTIONIMAGE_DET(#{guid, mode=IN, jdbcType=BINARY},#{client_id, mode=IN, jdbcType=INTEGER}, #{audit_group_id, mode=IN, jdbcType=INTEGER},#{audit_type_id, mode=IN, jdbcType=INTEGER},"
			+ "#{question_no, mode=IN, jdbcType=INTEGER},#{image, mode=IN, jdbcType=VARCHAR},#{image_path, mode=IN, jdbcType=VARCHAR},#{image_public_id, mode=IN, jdbcType=VARCHAR},#{image_thumbnail, mode=IN, jdbcType=VARCHAR})}")
	@Options(statementType = StatementType.CALLABLE)
	int updateQuestionImages(Question question);
	
	@Select("SELECT ENABLE_CLOUDINARY FROM GA_USERDET_MT WHERE GUID=#{guid} AND SUPER_USER=1")
	String getCloudinaryFlag(String guid);

	@Select("SELECT GUID, AUDIT_GROUP_ID, CLIENT_ID, AUDIT_TYPE_ID, QUESTION_NO, IMAGE_ID,IMAGE as binaryimage FROM GA_QUESTIONPHOTO_MT WHERE GUID=#{guid} AND LENGTH(IMAGE)>0")
	List<Question> getQuestionImages(String guid);

	@Update("UPDATE GA_QUESTIONPHOTO_MT SET CLOUD_IMAGE_PATH=#{image_path},CLOUD_IMAGE_PUBLIC_ID=#{image_public_id},CLOUD_IMAGE_THUMBNAIL=#{image_thumbnail} WHERE GUID=#{guid} AND CLIENT_ID=#{client_id} AND AUDIT_TYPE_ID=#{audit_type_id}"
			+ " AND QUESTION_NO=#{question_no} AND IMAGE_ID=#{image_id}")
	void updateQuestionImagesforclod(Question q);

	@Select("SELECT GUID,UID, CLIENT_ID,AUDIT_GROUP_ID, AUDIT_TYPE_ID, QUESTION_NO, IMAGE_ID,ACTION_IMAGE as action_imagebi,STORE_ID,AUDIT_DATE,SEQ_NO FROM GA_QUESTACTIONIMG_DT WHERE  LENGTH(ACTION_IMAGE)>0")
	List<Questactimage> getFollowupImages();

	@Update("UPDATE GA_QUESTACTIONIMG_DT SET CLOUD_IMAGE_PATH=#{cloud_image_path},CLOUD_IMAGE_PUBLIC_ID=#{cloud_image_public_id},CLOUD_IMAGE_THUMBNAIL=#{cloud_image_thumbnail} WHERE GUID=#{guid} AND UID=#{uid} AND CLIENT_ID=#{client_id} AND AUDIT_GROUP_ID=#{audit_group_id} AND AUDIT_TYPE_ID=#{audit_type_id} AND QUESTION_NO=#{question_no} AND STORE_ID=#{store_id} AND AUDIT_DATE=#{audit_date} AND SEQ_NO=#{seq_no} AND IMAGE_ID=#{image_id}")
	void updateFollowupforclod(Questactimage q);

	@Select("SELECT GUID,UID, CLIENT_ID,AUDIT_GROUP_ID, AUDIT_TYPE_ID, QUESTION_NO, IMAGE_ID,IMAGE as action_imagebi,STORE_ID,AUDIT_DATE,SEQ_NO FROM GA_QUESTIMG_DT WHERE GUID=#{guid} AND LENGTH(IMAGE)>0")
	List<Questactimage> getAuditImages(String guid);

	@Update("UPDATE GA_QUESTIMG_DT SET CLOUD_IMAGE_URL=#{cloud_image_path},REPORT_IMAGE_URL=#{cloud_image_thumbnail} WHERE GUID=#{guid} AND UID=#{uid} AND CLIENT_ID=#{client_id} AND AUDIT_GROUP_ID=#{audit_group_id} AND AUDIT_TYPE_ID=#{audit_type_id} AND QUESTION_NO=#{question_no} AND STORE_ID=#{store_id} AND AUDIT_DATE=#{audit_date} AND SEQ_NO=#{seq_no} AND IMAGE_ID=#{image_id}")
	void updateAuditforclod(Questactimage q);
	
	@Select("SELECT DAY(NOW())")
	String getdate();

	@Select("SELECT GUID,UID, CLIENT_ID,AUDIT_GROUP_ID, AUDIT_TYPE_ID,REPORT_PATH as action_image,STORE_ID,AUDIT_DATE,SEQ_NO FROM GA_REPORTS_DT")	
	List<Questactimage> getAuditReport();
	
	@Update("UPDATE GA_REPORTS_DT SET CLOUD_REPORT_PATH=#{cloud_image_path} WHERE GUID=#{guid} AND UID=#{uid} AND CLIENT_ID=#{client_id} AND AUDIT_GROUP_ID=#{audit_group_id} AND AUDIT_TYPE_ID=#{audit_type_id} AND STORE_ID=#{store_id} AND AUDIT_DATE=#{audit_date} AND SEQ_NO=#{seq_no}")
	int updateAuditReportforclod(Questactimage q);

}
