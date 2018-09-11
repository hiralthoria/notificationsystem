package com.notification.service;

import org.springframework.stereotype.Service;

import com.notification.exception.NotificationException;
import com.notification.model.Notification;

/**
 * Service class to send notification or to check whether the channel is supported or not
 * @author Hiral Salvi
 *
 */
@Service
public interface NotificationService {

	public String sendNotification(final Notification notification, String channel) 
			throws NotificationException;
	
	public boolean isChannelSupported(String channel) throws NotificationException;
}
