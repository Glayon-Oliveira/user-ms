package com.lmlasmo.ms.user.dto.auth;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.lmlasmo.ms.user.validator.constraint.StrongPassword;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class LoginDTO extends EmailDTO{

	@JsonProperty
	@JsonAlias({"pass"})
	@NotBlank
	@Size(min = 8, max = 255)
	@StrongPassword
	private String password;

}
