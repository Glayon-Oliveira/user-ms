package com.lmlasmo.ms.user.exception.handler.util;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lmlasmo.ms.user.dto.exception.ErrorMessageDTO;
import com.lmlasmo.ms.user.dto.exception.ExceptionDTO;

import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class ExceptionResponseWriter {

	private ObjectMapper mapper;

	public void writer(HttpStatus status, String path, String message, HttpServletResponse response) throws IOException {
		ExceptionDTO exception = new ExceptionDTO(status, path, new ErrorMessageDTO(message));

		String exceptionValue = mapper.writeValueAsString(exception);

		response.setStatus(status.value());
		response.setContentType(MediaType.APPLICATION_JSON_VALUE);
		response.setCharacterEncoding(StandardCharsets.UTF_8.name());
		response.getWriter().write(exceptionValue);
		response.flushBuffer();
	}

}
