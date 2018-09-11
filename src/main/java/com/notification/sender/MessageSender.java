package com.notification.sender;

import com.notification.model.Notification;

/**
 * Abstract version of the message sender class
 * @author Hiral Salvi
 *
 */
public abstract  class MessageSender {

	protected String channel;
	public abstract void send(Notification notification);
	public abstract void setChannel();
	
	public MessageSender() {
		setChannel();
	}
	
}
