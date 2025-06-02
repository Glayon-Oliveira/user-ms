package com.lmlasmo.ms.user.dto.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class ErrorMessageDTO {

	@JsonProperty
	@JsonInclude(value = Include.NON_NULL)
	private String cause;

	@JsonProperty
	@NonNull
	private String message;

}
