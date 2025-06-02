package com.lmlasmo.ms.user.security.handler;

import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import com.lmlasmo.ms.user.exception.handler.util.ExceptionResponseWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint {

	private ExceptionResponseWriter responseWriter;

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException, ServletException {
		String message = "Invalid, missing, or expired token.";
		responseWriter.writer(HttpStatus.UNAUTHORIZED, request.getRequestURI(), message, response);
	}

}
