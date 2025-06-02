package com.lmlasmo.ms.user.exception.handler;

import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.lmlasmo.ms.user.dto.exception.ExceptionDTO;
import com.lmlasmo.ms.user.exception.SystemFailedException;
import com.lmlasmo.ms.user.exception.handler.util.ExceptionDTOFactory;

import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice
@ResponseBody
@Order(0)
public class ApplicationExceptionHandler {

	@ExceptionHandler(SystemFailedException.class)
	@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
	public ExceptionDTO handleInternalServerExceptions(SystemFailedException exception, HttpServletRequest request) {
		return ExceptionDTOFactory.getExceptionDTO(HttpStatus.INTERNAL_SERVER_ERROR, request, exception.getMessage());
	}

	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	@ResponseStatus(code = HttpStatus.METHOD_NOT_ALLOWED)
	public ExceptionDTO handleInternalServerExceptions(HttpRequestMethodNotSupportedException exception, HttpServletRequest request) {
		return ExceptionDTOFactory.getExceptionDTO(HttpStatus.METHOD_NOT_ALLOWED, request, exception);
	}

	@ExceptionHandler(HttpMediaTypeNotSupportedException.class)
	@ResponseStatus(code = HttpStatus.UNSUPPORTED_MEDIA_TYPE)
	public ExceptionDTO handleUnsupportedExceptions(HttpMediaTypeNotSupportedException exception, HttpServletRequest request) {
		String message = "Media type not supported. Use 'application/json' in the Content-Type.";
		return ExceptionDTOFactory.getExceptionDTO(HttpStatus.UNSUPPORTED_MEDIA_TYPE, request, message);
	}

}
