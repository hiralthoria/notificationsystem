package com.notification.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.notification.controller.ConfigInitializer;
import com.notification.exception.NotificationException;
import com.notification.model.Notification;
import com.notification.queue.NotificationQueue;

/**
 * Implementation of the service class to send notification or to check whether the channel is supported or not
 * @author Hiral Salvi
 *
 */
@Service
public class NotificationServiceImpl implements NotificationService {

	private static final Logger logger = LoggerFactory.getLogger(NotificationServiceImpl.class);
	
	@Autowired
	public NotificationQueue queue;
	
	@Override
	public String sendNotification(final Notification notification, String channel) 
			throws NotificationException {
		if(!initializer.isChannelSupported(channel)) {
			logger.error(channel + " channel is not supported");
			throw new NotificationException(channel + " channel is not supported");
		}
		queue.addNotificationInqueue(notification, channel);
		return "Added to the Queue";
	}
	
	@Override
	public boolean isChannelSupported(String channel) throws NotificationException {
		return initializer.isChannelSupported(channel);
	}
	
	@Autowired
	public ConfigInitializer initializer;

	public NotificationQueue getQueue() {
		return queue;
	}

	public void setQueue(NotificationQueue queue) {
		this.queue = queue;
	}
	
}
