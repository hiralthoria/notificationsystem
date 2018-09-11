package com.notification.exception;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * It handles exceptions and prepares related response
 * @author Hiral Salvi
 *
 */
@ControllerAdvice
public class ExceptionHandlerAdvice {

	/**
	 * Handles Notifications exception
	 * @param e
	 * @param req
	 * @return
	 */
	@ExceptionHandler(NotificationException.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST )
	public @ResponseBody ExceptionResponse handleNotificationException(
			final NotificationException e, final HttpServletRequest req) {
		ExceptionResponse error = new ExceptionResponse();
		error.setErrorMessage(e.getMessage());
		error.setMessageURI(req.getRequestURI());
		return error;
	}
	
	/**
	 * Handles illegal argument exception
	 * @param e
	 * @param req
	 * @return
	 */
	@ExceptionHandler(IllegalArgumentException.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST )
	public @ResponseBody ExceptionResponse handleIllegalArgumentException(
			final IllegalArgumentException e, final HttpServletRequest req) {
		ExceptionResponse error = new ExceptionResponse();
		error.setErrorMessage("Channel not supported");
		error.setMessageURI(req.getRequestURI());
		return error;
	}
	
}
