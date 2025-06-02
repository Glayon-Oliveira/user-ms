package com.lmlasmo.ms.user.security.handler;

import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import com.lmlasmo.ms.user.exception.handler.util.ExceptionResponseWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class AccessDeniedHandlerImpl implements AccessDeniedHandler {

	private ExceptionResponseWriter responseWriter;

	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response,
			AccessDeniedException accessDeniedException) throws IOException, ServletException {
		String message = "Access denied";
		responseWriter.writer(HttpStatus.FORBIDDEN, request.getRequestURI(), message, response);
	}

}
