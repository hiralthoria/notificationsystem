package com.notification.sender;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.notification.model.Notification;

/**
 * Notification sender class implementation for slack channel
 * This contains mock implementation
 * @author Hiral Salvi
 *
 */
public class SlackSender extends MessageSender {

	private static final Logger logger = LoggerFactory.getLogger(SlackSender.class);
	
	@Override
	public void send(Notification notification) {
			logger.info("Slack message Sent for " + notification);
	}

	@Override
	public void setChannel() {
		this.channel="slack";
	}

}
