package com.goaudits.business.service;

import com.goaudits.business.entity.Question;
import com.goaudits.business.entity.S3;



public interface S3Service {

	S3 GetPayload(S3 s3);

	int updateQuestionImages(Question question);

	String getCloudinaryFlag(String guid);

	int Migrateimages(String guid);

	int MigrateimagesFollowup();

	int MigrateimagesAudit(String guid);

	int MigrateAuditReport();

	int CreateFolder(S3 s3);



}
