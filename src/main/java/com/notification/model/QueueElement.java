package com.notification.model;

/**
 * Element to be stored in the queue. It has base notification and channel
 * @author Hiral Salvi
 *
 */
public class QueueElement {

	private Notification notification;
	private String channel;
	
	public QueueElement(Notification notification, String channel) {
		this.notification = notification;
		this.channel = channel;
	}
	
	public Notification getNotification() {
		return notification;
	}

	public void setNotification(Notification notification) {
		this.notification = notification;
	}

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	@Override
	public String toString() {
		return "QueueElemenet [notification=" + notification + ", channel=" + channel + "]";
	}

}
