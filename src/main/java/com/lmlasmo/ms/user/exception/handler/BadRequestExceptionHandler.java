package com.lmlasmo.ms.user.exception.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.HandlerMethodValidationException;

import com.lmlasmo.ms.user.dto.exception.ErrorMessageDTO;
import com.lmlasmo.ms.user.dto.exception.ExceptionDTO;
import com.lmlasmo.ms.user.exception.handler.util.ExceptionDTOFactory;

import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice
@ResponseBody
@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class BadRequestExceptionHandler {

	@ExceptionHandler(HandlerMethodValidationException.class)
	public ExceptionDTO handleBadRequestExceptions(HandlerMethodValidationException exception, HttpServletRequest request) {
		ErrorMessageDTO[] messages = exception.getParameterValidationResults().stream()
				.flatMap(e -> e.getResolvableErrors().stream())
				.map(e -> {
					String cause = e.getCodes()[0];
					cause = cause.substring(cause.lastIndexOf(".")+1);
					return new ErrorMessageDTO(cause, e.getDefaultMessage());
				})
				.toArray(ErrorMessageDTO[]::new);

		return ExceptionDTOFactory.getExceptionDTO(request, messages);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ExceptionDTO handleBadRequestExceptions(MethodArgumentNotValidException exception, HttpServletRequest request){
		ErrorMessageDTO[] messages = exception.getFieldErrors().stream()
				.map(e -> new ErrorMessageDTO(e.getField(), e.getDefaultMessage()))
				.toArray(ErrorMessageDTO[]::new);

		return ExceptionDTOFactory.getExceptionDTO(request, messages);
	}

	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ExceptionDTO handleBadRequestExceptions(HttpMessageNotReadableException exception, HttpServletRequest request){
		String message = "The requisition body is malformed, missing, or in invalid form.";
		return ExceptionDTOFactory.getExceptionDTO(request, message);
	}

	@ExceptionHandler(MissingServletRequestParameterException.class)
	public ExceptionDTO handleBadRequestException(MissingServletRequestParameterException exception, HttpServletRequest request) {
		return ExceptionDTOFactory.getExceptionDTO(request, exception.getMessage());
	}

}

