package com.notification.exception;

/**
 * Geenral Exception class for the system
 * @author Hiral Salvi
 *
 */
public class NotificationException extends Exception {

	private static final long serialVersionUID = 198783434398398978L;

	public NotificationException() {
	
	}
	
	public NotificationException(final String message) {
		super(message);
	}
}
