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
public class RegisterProfileDTO {

	@JsonProperty
	@JsonAlias({"fullName", "full_name", "nm"})
	@NotBlank
	@Size(max = 255)
	private String name;

	@JsonProperty
	@JsonAlias({"phone", "phoneNumber", "phone_number", "ph"})
	@Size(max = 20)
	private String tel;

}
