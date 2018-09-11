package com.notification.exception;

/**
 * Response class in case of exception
 * @author Hiral Salvi
 *
 */
public class ExceptionResponse {

	private String errorMessage;
	private String messageURI;
	
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(final String errorMessage) {
		this.errorMessage = errorMessage;
	}
	public String getMessageURI() {
		return messageURI;
	}
	public void setMessageURI(final String messageURI) {
		this.messageURI = messageURI;
	}
	
	
}
