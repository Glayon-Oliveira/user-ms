package com.lmlasmo.ms.user.dto.register;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RegisterAddressDTO {

	@JsonProperty
	@JsonAlias({"countryCode", "country_code", "cty", "cc"})
	@NotBlank
	@Size(min = 2, max = 2)
	private String nation;

	@JsonProperty
	@JsonAlias({"stateCode", "state_code", "st", "sc"})
	@NotBlank
	@Size(min = 2, max = 2)
	private String state;

	@JsonProperty
	@JsonAlias({"ct"})
	@NotBlank
	@Size(max = 255)
	private String city;

	@JsonProperty
	@JsonAlias({"neighborhood", "nbh", "dist"})
	@NotBlank
	@Size(max = 255)
	private String district;

	@JsonProperty
	@JsonAlias({"str"})
	@NotBlank
	@Size(max = 255)
	private String street;

	@JsonProperty
	@JsonAlias({"num"})
	@NotBlank
	@Size(max = 20)
	private String number;

	@JsonProperty
	@JsonAlias({"comp"})
	@Size(max = 255)
	private String complement;

	@JsonProperty
	@JsonAlias({"zip", "pc"})
	@Size(max = 20)
	private String postalCode;

}
