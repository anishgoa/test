package com.goaudits.business.serviceimpl;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.goaudits.business.config.DatabaseConfig;
import com.goaudits.business.entity.Questactimage;
import com.goaudits.business.entity.Question;
import com.goaudits.business.entity.S3;
import com.goaudits.business.mapper.S3Mapper;
import com.goaudits.business.service.S3Service;
import com.goaudits.business.util.Constants;
import com.goaudits.business.util.Utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

@Service
public class S3ServiceImpl implements S3Service {
	
	private final Logger log = LogManager.getLogger(getClass().getName());
	
	@Autowired
	DatabaseConfig dbConfig;

	@Autowired
	S3Mapper s3mapper;

	@Autowired
	private HttpSession session;

	public S3 GetPayload(S3 s3) {
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());

		String folderpath = "";
		Map<String, Object> params = new HashMap<String, Object>();
		if (s3.getPage().equals("client")) {
			folderpath = "Companies/" + s3.getGuid() + "/" + s3.getClient_id() + "/Branding";
			params.put("public_id", s3.getClient_name());
			params.put("use_filename", true);
		}
		if (s3.getPage().equals("auditname")) {
			folderpath = "Companies/" + s3.getGuid() + "/" + s3.getClient_id() + "/Branding/"
					+ s3.getAudit_type_id();
			params.put("public_id", s3.getAudit_type_name());
			params.put("use_filename", true);
		}
		if (s3.getPage().equals("question")) {
			folderpath = "Companies/" + s3.getGuid() + "/" + s3.getClient_id() + "/Branding/"
					+ s3.getAudit_type_id() + "/Question";
			params.put("use_filename", false);

		}
		if (s3.getPage().equals("reportconfig")) {
			folderpath = "Companies/" + s3.getGuid() + "/" + s3.getClient_id() + "/Branding/"
					+ s3.getAudit_type_id() + "/report";
			params.put("use_filename", true);

		}

		if (s3.getPage().equals("user")) {

			folderpath = "Companies/" + s3.getGuid() + "/User";
			params.put("use_filename", true);
		}
		if (s3.getPage().equals("followup")) {

			folderpath = "Companies/" + s3.getGuid() + "/" + s3.getClient_id() + "/Branding/" + s3.getAudit_type_id()
					+ "/followup";
			params.put("use_filename", true);
		}

		String apiSecret = Constants.apiSecret;
		Cloudinary cloudinary = new Cloudinary();

		String timestamps = Long.toString(timestamp.getTime());

		params.put("timestamp", timestamps);
		params.put("upload_preset", Constants.upload_preset);
		params.put("folder", folderpath);

		params.put("unique_filename", false);
//		params.put("public_id", "Sample1");

		String signedkey = cloudinary.apiSignRequest(params, apiSecret);

		s3.setTimestamp(timestamps);
		s3.setSignature(signedkey);
		s3.setFolderpath(folderpath);

		return s3;
	}

	@Override
	public int updateQuestionImages(Question question) {

		String scaleDownProperty = "w_1000,c_fill,g_auto";
		String delimiterStr = "/";
		int replaceIndex = 6;

		String reportImgUrl = Utils.splitJoinStringsAtIndex(question.getImage_path(), scaleDownProperty, replaceIndex,
				delimiterStr);
		question.setImage_thumbnail(reportImgUrl);

		return s3mapper.updateQuestionImages(question);
	}

	@Override
	public String getCloudinaryFlag(String guid) {

		return s3mapper.getCloudinaryFlag(guid);
	}

	@Override
	public int Migrateimages(String guid) {

		List<Question> questionList = s3mapper.getQuestionImages(guid);

		Cloudinary cloudinary = new Cloudinary(ObjectUtils.asMap("cloud_name", Constants.cloudname, "api_key",
				Constants.apikey, "api_secret", Constants.apiSecret));
		for (Question q : questionList) {
			String path = "Companies/" + guid + "/" + q.getClient_id() + "/Branding/" + q.getAudit_type_id()
					+ "/Question";

			if (q.getBinaryimage() == null) {

			} else {
				try {
					byte[] images;
					images = q.getBinaryimage();

					String quesimage = "";
					quesimage = "data:image/jpg;base64," + Utils.ConvertToBase64(images);
					@SuppressWarnings("rawtypes")
					Map result = cloudinary.uploader().upload(quesimage.replaceAll("\\r\\n|\\r|\\n", ""),
							ObjectUtils.asMap("folder", path, "use_filename", "true", "unique_filename", "false"));

					System.out.println(result);

					String scaleDownProperty = "w_1000,c_fill,g_auto";
					String delimiterStr = "/";
					int replaceIndex = 6;

					String reportImgUrl = Utils.splitJoinStringsAtIndex(result.get("secure_url").toString(),
							scaleDownProperty, replaceIndex, delimiterStr);
					q.setImage_thumbnail(reportImgUrl);
					q.setImage_path(result.get("secure_url").toString());
					q.setImage_public_id(result.get("public_id").toString());

					s3mapper.updateQuestionImagesforclod(q);
					result.clear();

				} catch (IOException e) {
					e.printStackTrace();
					log.error("Error catched", e);
				}

			}

		}

		return 1;
	}

	@Override
	public int MigrateimagesFollowup() {
		List<Questactimage> questionList = s3mapper.getFollowupImages();

		for (Questactimage q : questionList) {

			String path = "Companies/" + q.getGuid() + "/" + q.getClient_id() + "/Branding/" + q.getAudit_type_id()
					+ "/followup";

			Cloudinary cloudinary = new Cloudinary(ObjectUtils.asMap("cloud_name", Constants.cloudname, "api_key",
					Constants.apikey, "api_secret", Constants.apiSecret));
			if (q.getAction_imagebi() == null) {

			} else {
				try {
					byte[] images;
					images = q.getAction_imagebi();

					String quesimage = "";
					quesimage = "data:image/jpg;base64," + Utils.ConvertToBase64(images);
					@SuppressWarnings("rawtypes")
					Map result = cloudinary.uploader().upload(quesimage.replaceAll("\\r\\n|\\r|\\n", ""),
							ObjectUtils.asMap("folder", path, "use_filename", "true", "unique_filename", "false"));

					System.out.println(result);

					String scaleDownProperty = "w_1000,c_fill,g_auto";
					String delimiterStr = "/";
					int replaceIndex = 6;

					String reportImgUrl = Utils.splitJoinStringsAtIndex(result.get("secure_url").toString(),
							scaleDownProperty, replaceIndex, delimiterStr);

					q.setCloud_image_thumbnail(reportImgUrl);
					q.setCloud_image_path(result.get("secure_url").toString());
					q.setCloud_image_public_id(result.get("public_id").toString());

					s3mapper.updateFollowupforclod(q);

				} catch (IOException e) {
					e.printStackTrace();
					log.error("Error catched", e);
				}

			}

		}

		return 1;

	}

	@Override
	public int MigrateimagesAudit(String guid) {

		List<Questactimage> questionList = s3mapper.getAuditImages(guid);
		Cloudinary cloudinary = new Cloudinary(ObjectUtils.asMap("cloud_name", Constants.cloudname, "api_key",
				Constants.apikey, "api_secret", Constants.apiSecret));
		for (Questactimage q : questionList) {

			int year = Calendar.getInstance().get(Calendar.YEAR);
			Calendar cal = Calendar.getInstance();
			String Month = new SimpleDateFormat("MMMM").format(cal.getTime());

			String date = s3mapper.getdate();

			String path = "Companies/" + q.getGuid() + "/" + q.getClient_id() + "/" + q.getStore_id() + "/Audits/"
					+ q.getAudit_type_id() + "/" + year + "/" + Month + "/" + date;

			if (q.getAction_imagebi() == null) {

			} else {
				try {
					byte[] images;
					images = q.getAction_imagebi();

					String quesimage = "";
					quesimage = "data:image/jpg;base64," + Utils.ConvertToBase64(images);
					Timestamp timestamp = new Timestamp(System.currentTimeMillis());
					String timestamps = Long.toString(timestamp.getTime());
					String filename = q.getClient_id() + "-" + q.getAudit_type_id() + "_" + timestamps;
					@SuppressWarnings("rawtypes")
					
					Map result = cloudinary.uploader().upload(quesimage.replaceAll("\\r\\n|\\r|\\n", ""),
							ObjectUtils.asMap("folder", path, "use_filename", "true", "unique_filename", "false",
									"public_id", filename));

					String scaleDownProperty = "w_1000,c_fill,g_auto";
					String delimiterStr = "/";
					int replaceIndex = 6;

					String reportImgUrl = Utils.splitJoinStringsAtIndex(result.get("secure_url").toString(),
							scaleDownProperty, replaceIndex, delimiterStr);

					q.setCloud_image_thumbnail(reportImgUrl);
					q.setCloud_image_path(result.get("secure_url").toString());
					q.setCloud_image_public_id(result.get("public_id").toString());

					s3mapper.updateAuditforclod(q);
                    result.clear();
				} catch (IOException e) {

					e.printStackTrace();
					log.error("Error catched", e);
				}

			}

		}

		return 1;

	}

	@Override
	public int MigrateAuditReport() {
		List<Questactimage> questionList = s3mapper.getAuditReport();

		for (Questactimage q : questionList) {

//			int year = Calendar.getInstance().get(Calendar.YEAR);
//			Calendar cal = Calendar.getInstance();
//			String Month = new SimpleDateFormat("MMMM").format(cal.getTime());
//
//			String date = s3mapper.getdate();

			String path = "Companies/" + q.getGuid() + "/" + q.getClient_id() + "/" + q.getStore_id() + "/Audits/"
					+ q.getAudit_type_id() + "/Reports";
			Cloudinary cloudinary = new Cloudinary(ObjectUtils.asMap("cloud_name", Constants.cloudname, "api_key",
					Constants.apikey, "api_secret", Constants.apiSecret));

			try {

				String quesimage = "//home/ubuntu/Jasper/outputs/" + q.getAction_image() + ".pdf";
				File f = new File(quesimage);
				if (f.exists()) {
					//Timestamp timestamp = new Timestamp(System.currentTimeMillis());
					//String timestamps = Long.toString(timestamp.getTime());
					String filename = q.getAction_image();
					@SuppressWarnings("rawtypes")
					Map result = cloudinary.uploader().upload(quesimage.replaceAll("\\r\\n|\\r|\\n", ""),
							ObjectUtils.asMap("folder", path, "use_filename", "true", "unique_filename", "true",
									"public_id", filename));

//					String scaleDownProperty = "w_1000,c_fill,g_auto";
//					String delimiterStr = "/";
//					int replaceIndex = 6;
//
//					String reportImgUrl = Utils.splitJoinStringsAtIndex(result.get("secure_url").toString(),
//							scaleDownProperty, replaceIndex, delimiterStr);
//
//					q.setCloud_image_thumbnail(reportImgUrl);
					q.setCloud_image_path(result.get("secure_url").toString());
					q.setCloud_image_public_id(result.get("public_id").toString());

					s3mapper.updateAuditReportforclod(q);

					
					 File afile =new File(quesimage);
					 String directoryName=Constants.reportpath+q.getGuid()+"/"+q.getClient_id()+"/"+q.getAudit_type_id();
					    File directory = new File(directoryName);

//					    if (!directory.exists()){
					        directory.mkdirs();
//					    }
					 
					 afile.renameTo(new File(directoryName+"/" + afile.getName()));
					
				}
			} catch (Exception e) {
				e.printStackTrace();
				log.error("Error catched", e);
			}

		}

		return 1;
	}

	@Override
	public int CreateFolder(S3 s3) {
		
		  File directory = new File(s3.getFolderpath());

		    if (!directory.exists()){
		        directory.mkdirs();
		    }
		return 1;
	}

}
