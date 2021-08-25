package com.goaudits.business.entity;

import org.springframework.stereotype.Repository;

@Repository
public class Menu {
	boolean notification_enable;
	boolean groupaudit_enable;
	public boolean isNotification_enable() {
		return notification_enable;
	}
	public void setNotification_enable(boolean notification_enable) {
		this.notification_enable = notification_enable;
	}
	public boolean isGroupaudit_enable() {
		return groupaudit_enable;
	}
	public void setGroupaudit_enable(boolean groupaudit_enable) {
		this.groupaudit_enable = groupaudit_enable;
	}
	

}
