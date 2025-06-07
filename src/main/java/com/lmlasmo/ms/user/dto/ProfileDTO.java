package com.lmlasmo.ms.user.dto;

import java.time.Instant;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ProfileDTO {

	@JsonProperty
	private String name;

	@JsonProperty
	private String tel;

	@JsonProperty
	private Instant updatedAt;
}
