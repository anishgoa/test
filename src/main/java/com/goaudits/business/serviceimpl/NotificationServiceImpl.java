package com.goaudits.business.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.goaudits.business.entity.Notification;
import com.goaudits.business.mapper.NotificationMapper;
import com.goaudits.business.service.NotificationService;

@Service
public class NotificationServiceImpl implements NotificationService {

	@Autowired
	private NotificationMapper NotificationMapper;

	@Override
	public int createActionTaskNotification(Notification notification) {

		if (notification.getFrequency() == 1) {

			String auditTypes[] = notification.getAudit_type_id().split(",");

			for (int i = 0; i < auditTypes.length; i++) {

				if (!auditTypes[i].equals("-1")) {
					notification.setAudit_type_id(auditTypes[i]);

					NotificationMapper
							.createActionTaskNotification(notification);
				}
			}

		}

		if (notification.getFrequency() == 2) {

			String auditTypes[] = notification.getAudit_type_id().split(",");

			String trigger_days[] = notification.getTrigger_day().split(",");

			for (int j = 0; j < trigger_days.length; j++) {

				for (int i = 0; i < auditTypes.length; i++) {

					if (!auditTypes[i].equals("-1")) {
						notification.setAudit_type_id(auditTypes[i]);
						notification.setTrigger_day(trigger_days[j]);

						NotificationMapper
								.createActionTaskNotification(notification);
					}
				}

			}

		}

		return 1;
	}

	@Override
	public int editActionTaskNotification(Notification notification) {

		NotificationMapper.editActionTaskNotification(notification);

		return 1;
	}

	@Override
	public int createActionSummaryNotification(Notification notification) {
		if (notification.getFrequency() == 1) {

			String auditTypes[] = notification.getAudit_type_id().split(",");

			for (int i = 0; i < auditTypes.length; i++) {

				if (!auditTypes[i].equals("-1")) {
					notification.setAudit_type_id(auditTypes[i]);

					NotificationMapper
							.createActionSummaryNotification(notification);
				}
			}

		}

		if (notification.getFrequency() == 2) {

			String auditTypes[] = notification.getAudit_type_id().split(",");

			String trigger_days[] = notification.getTrigger_day().split(",");

			for (int j = 0; j < trigger_days.length; j++) {

				for (int i = 0; i < auditTypes.length; i++) {

					if (!auditTypes[i].equals("-1")) {
						notification.setAudit_type_id(auditTypes[i]);
						notification.setTrigger_day(trigger_days[j]);

						NotificationMapper
								.createActionSummaryNotification(notification);
					}
				}

			}

		}
		return 1;
	}

	@Override
	public List<Notification> editActionTaskNotification(String guid,
			int client_id, int notification_type) {
		
		return NotificationMapper.getActionPlanNotification(guid,client_id,notification_type);
	}

	@Override
	public int deleteNotification(Notification notification) {
	
		return NotificationMapper.deleteNotification(notification);
	}

}
