package com.notification.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.notification.exception.NotificationException;
import com.notification.model.Notification;
import com.notification.service.NotificationService;

/**
 * Rest controller that exposes two services
 * 	1. if the channel is supported
 *  2. Send notification
 * @author Hiral Salvi
 *
 */
@RestController
public class NotificationController {

	private static final Logger logger = LoggerFactory.getLogger(NotificationController.class);

	@Autowired
	private NotificationService Service;
	
	/**
	 * Service to check if the channel is supported
	 * @param channel
	 * @return
	 * @throws NotificationException
	 */
	@PostMapping("/supportedchannel")
	public boolean isChannelSupported(String channel) throws NotificationException {
			return Service.isChannelSupported(channel);
	}
	
	/**
	 * Service to send notifications
	 * @param notification
	 * @param channel
	 * @return
	 * @throws NotificationException
	 * @throws IllegalArgumentException
	 */
	@PostMapping("/notification")
	public String sendNotification(@RequestBody Notification notification, String channel) 
			throws NotificationException, IllegalArgumentException {
		if(channel==null || notification==null) {
			logger.error("Channel or Notification information is missing");
			throw new NotificationException("Channel or Notification information is missing");
		}
		return Service.sendNotification(notification, channel);
	}
	
}
