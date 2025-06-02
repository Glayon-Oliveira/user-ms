package com.lmlasmo.ms.user.security.handler;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.lmlasmo.ms.user.dto.exception.ExceptionDTO;
import com.lmlasmo.ms.user.exception.handler.util.ExceptionDTOFactory;

import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice
@ResponseBody
@Order(Ordered.HIGHEST_PRECEDENCE)
public class SecurityExceptionHandler {

	@ExceptionHandler(AuthenticationException.class)
	@ResponseStatus(code = HttpStatus.UNAUTHORIZED)
	public ExceptionDTO handleUnauthorizedExceptions(BadCredentialsException exception, HttpServletRequest request){
		return ExceptionDTOFactory.getExceptionDTO(HttpStatus.UNAUTHORIZED, request, exception);
	}

	@ExceptionHandler(AccessDeniedException.class)
	@ResponseStatus(code = HttpStatus.FORBIDDEN)
	public ExceptionDTO handleForbiddenExceptions(AccessDeniedException exception, HttpServletRequest request){
		return ExceptionDTOFactory.getExceptionDTO(HttpStatus.FORBIDDEN, request, exception);
	}

}
