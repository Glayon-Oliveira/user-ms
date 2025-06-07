package com.lmlasmo.ms.user.dto;

import java.time.Instant;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AddressDTO {

	@JsonProperty
	private String nation;

	@JsonProperty
	private String state;

	@JsonProperty
	private String city;

	@JsonProperty
	private String district;

	@JsonProperty
	private String street;

	@JsonProperty
	private String number;

	@JsonProperty
	private String complement;

	@JsonProperty
	private String postalCode;

	@JsonProperty
	private Instant updatedAt;

}
