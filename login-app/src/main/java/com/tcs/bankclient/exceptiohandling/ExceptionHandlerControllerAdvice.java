package com.tcs.bankclient.exceptiohandling;


import javax.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.ResourceAccessException;

import com.tcs.bankclient.model.Response;

@ControllerAdvice
public class ExceptionHandlerControllerAdvice {

	@ExceptionHandler(ResourceAccessException.class)
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	public @ResponseBody Response handleResourceNotFound(final ResourceAccessException exception,
			final HttpServletRequest request) throws Exception {

		return new Response(false,exception.getMessage());
	}

	@ExceptionHandler(Exception.class)
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	public @ResponseBody Response handleException(final Exception exception,
			final HttpServletRequest request) throws Exception {

		return new Response(false,exception.getMessage());
	}

}