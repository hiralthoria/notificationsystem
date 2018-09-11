package com.notification.model;

/**
 * Bean class for the Notification. It contains from, to, message and subject
 * @author Hiral Salvi
 *
 */
public class Notification {
	
	private String from;
	private String to;
	private String message;
	private String subject;
	
	public Notification() {
	}
	
	public Notification(String from, String to, String message, String subject) {
		this.from = from;
		this.to = to;
		this.message = message;
		this.subject = subject;
	}
	
	public String getFrom() {
		return from;
	}
	
	public void setFrom(String from) {
		this.from = from;
	}
	public String getTo() {
		return to;
	}
	public void setTo(String to) {
		this.to = to;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}

	@Override
	public String toString() {
		return "Notification [from=" + from + ", to=" + to + ", message=" + message + ", subject=" + subject + "]";
	}
	
	

}
