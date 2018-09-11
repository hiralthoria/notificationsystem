package com.notification.sender;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.notification.model.Notification;

/**
 * Notification sender class implementation for sms channel
 * This contains mock implementation
 * @author Hiral Salvi
 *
 */
public class SMSSender extends MessageSender {

	private static final Logger logger = LoggerFactory.getLogger(SMSSender.class);
	
	@Override
	public void send(Notification notification) {
			logger.info("SMS message Sent for" + notification);
	}
	

	@Override
	public void setChannel() {
		this.channel="sms";
	}


}
