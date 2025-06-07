package com.lmlasmo.ms.user.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class FullUserDTO extends UserDTO{

	@JsonProperty
	private ProfileDTO profile;

	@JsonProperty
	private AddressDTO address;

}
