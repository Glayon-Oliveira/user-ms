package com.lmlasmo.ms.user.dto.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ExceptionDTO {

	@JsonProperty
	private Integer status = HttpStatus.BAD_REQUEST.value();

	@JsonProperty
	private String error = HttpStatus.BAD_REQUEST.getReasonPhrase();

	@JsonProperty
	private LocalDateTime timestamp = LocalDateTime.now();

	@JsonProperty
	private String path;

	@JsonProperty
	@JsonInclude(value = Include.NON_NULL)
	private ErrorMessageDTO[] messages;

	public ExceptionDTO(String path, ErrorMessageDTO... messages) {
		this.path = path;
		this.messages = messages;
	}

	public ExceptionDTO(HttpStatus status, String path) {
		this.status = status.value();
		this.error = status.getReasonPhrase();
		this.path = path;
	}

	public ExceptionDTO(HttpStatus status, String path, ErrorMessageDTO... messages) {
		this(path, messages);
		this.status = status.value();
		this.error = status.getReasonPhrase();
	}

}
