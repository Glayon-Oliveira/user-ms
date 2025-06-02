package com.lmlasmo.ms.user.exception.handler;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.lmlasmo.ms.user.dto.exception.ExceptionDTO;
import com.lmlasmo.ms.user.exception.handler.util.ExceptionDTOFactory;

import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice
@ResponseBody
@Order(Ordered.LOWEST_PRECEDENCE)
@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
public class LowExceptionHandler {

	@ExceptionHandler(Exception.class)
	public ExceptionDTO handleInternalServerExceptions(Exception exception, HttpServletRequest request) {
		exception.printStackTrace();
		return ExceptionDTOFactory.getExceptionDTO(HttpStatus.INTERNAL_SERVER_ERROR, request);
	}

}
