package com.goaudits.business.service;

import java.util.List;

import com.goaudits.business.entity.Notification;

public interface NotificationService {

	int createActionTaskNotification(Notification notification);

	int editActionTaskNotification(Notification notification);

	int createActionSummaryNotification(Notification notification);

	List<Notification> editActionTaskNotification(String guid,
			int client_id, int notification_type);

	int deleteNotification(Notification notification);
	
	

}
