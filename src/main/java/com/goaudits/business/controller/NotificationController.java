package com.goaudits.business.controller;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.goaudits.business.entity.Notification;
import com.goaudits.business.service.NotificationService;
import com.goaudits.business.util.GoAuditsException;
import com.goaudits.business.util.Utils;

@RestController
@EnableAutoConfiguration
public class NotificationController {

	
	@Autowired
	private NotificationService NotificationService;
	

	@RequestMapping(value = "createactiontasknotification", method = RequestMethod.POST)
	public ResponseEntity<?> createActionTaskNotification(@RequestBody Notification Notification,@RequestHeader(name = "Authorization") String token
) {
		
		try {
			if (token != null && token != "" && !token.isEmpty()) {
				token = token.replace("Bearer ", "");
				String guid = Utils.getGuid(token);
				String uid = Utils.getUid(token);
				Notification.setGuid(guid);
				Notification.setUid(uid);
			}
			NotificationService.createActionTaskNotification(Notification);
			List<Notification> NotiList=new ArrayList<Notification>();
			NotiList.add(Notification);
			return new ResponseEntity<List<Notification>>(NotiList, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(new GoAuditsException("Something went wrong" ),
					HttpStatus.EXPECTATION_FAILED);
		}

	}
	
	
	@RequestMapping(value = "editactionnotification", method = RequestMethod.POST)
	public ResponseEntity<?> editActionTaskNotification(@RequestBody Notification Notification,@RequestHeader(name = "Authorization") String token
) {
		try {
			if (token != null && token != "" && !token.isEmpty()) {
				token = token.replace("Bearer ", "");
				String guid = Utils.getGuid(token);
				Notification.setGuid(guid);
			}
			NotificationService.editActionTaskNotification(Notification);
			List<Notification> NotiList=new ArrayList<Notification>();
			NotiList.add(Notification);
			return new ResponseEntity<List<Notification>>(NotiList, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(new GoAuditsException("Something went wrong" ),
					HttpStatus.EXPECTATION_FAILED);
		}

	}
	
	
	@RequestMapping(value = "createactionsummarynotification", method = RequestMethod.POST)
	public ResponseEntity<?> createActionSummaryNotification(@RequestBody Notification Notification,@RequestHeader(name = "Authorization") String token
) {		
				
		try {
			if (token != null && token != "" && !token.isEmpty()) {
				token = token.replace("Bearer ", "");
				String guid = Utils.getGuid(token);
				String uid = Utils.getUid(token);
				Notification.setGuid(guid);
				Notification.setUid(uid);
			}
			NotificationService.createActionSummaryNotification(Notification);
			List<Notification> NotiList=new ArrayList<Notification>();
			return new ResponseEntity<List<Notification>>(NotiList, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(new GoAuditsException("Something went wrong" ),
					HttpStatus.EXPECTATION_FAILED);
		}

	}
	
	
	@RequestMapping(value = "getactionnotification/{guid}/{client_id}/{notification_type}", method = RequestMethod.GET)
	public ResponseEntity<?> editActionTaskNotification(@PathVariable("guid") String guid,@PathVariable("client_id") int client_id,@PathVariable("notification_type") int notification_type,@RequestHeader(name = "Authorization") String token) {
		
		try {
			if (token != null && token != "" && !token.isEmpty()) {
				token = token.replace("Bearer ", "");
				 guid = Utils.getGuid(token);
			}
			List<Notification>  notificationlist=NotificationService.editActionTaskNotification(guid,client_id,notification_type);
		
			return new ResponseEntity<List<Notification>>(notificationlist, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(new GoAuditsException("Something went wrong" ),
					HttpStatus.EXPECTATION_FAILED);
		}

	}
	
	
	@RequestMapping(value = "deletenotification", method = RequestMethod.POST)
	public ResponseEntity<?>deletenotificationNotification(@RequestBody Notification Notification,@RequestHeader(name = "Authorization") String token
) {		
		try {
			if (token != null && token != "" && !token.isEmpty()) {
				token = token.replace("Bearer ", "");
				String guid = Utils.getGuid(token);
				String uid = Utils.getUid(token);
				Notification.setGuid(guid);
				Notification.setUid(uid);
			}
			NotificationService.deleteNotification(Notification);
			List<Notification> NotiList=new ArrayList<Notification>();
			return new ResponseEntity<List<Notification>>(NotiList, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(new GoAuditsException("Something went wrong" ),
					HttpStatus.EXPECTATION_FAILED);
		}
	}
	
	@RequestMapping(value = "createauditnotification", method = RequestMethod.POST)
	public ResponseEntity<?> createAuditNotification(@RequestBody Notification Notification,@RequestHeader(name = "Authorization") String token
) {
		
		try {
			if (token != null && token != "" && !token.isEmpty()) {
				token = token.replace("Bearer ", "");
				String guid = Utils.getGuid(token);
				String uid = Utils.getUid(token);
				Notification.setGuid(guid);
				Notification.setUid(uid);
			}
			NotificationService.createAuditNotification(Notification);
			List<Notification> NotiList=new ArrayList<Notification>();
			NotiList.add(Notification);
			return new ResponseEntity<List<Notification>>(NotiList, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(new GoAuditsException("Something went wrong" ),
					HttpStatus.EXPECTATION_FAILED);
		}

	}
	
	@RequestMapping(value = "editauditnotification", method = RequestMethod.POST)
	public ResponseEntity<?> editAusitNotification(@RequestBody Notification Notification,@RequestHeader(name = "Authorization") String token
) {
		try {
			if (token != null && token != "" && !token.isEmpty()) {
				token = token.replace("Bearer ", "");
				String guid = Utils.getGuid(token);
				Notification.setGuid(guid);
			}
			NotificationService.editActionTaskNotification(Notification);
			List<Notification> NotiList=new ArrayList<Notification>();
			NotiList.add(Notification);
			return new ResponseEntity<List<Notification>>(NotiList, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(new GoAuditsException("Something went wrong" ),
					HttpStatus.EXPECTATION_FAILED);
		}

	}
	

}
