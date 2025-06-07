package com.lmlasmo.ms.user.dto.register;

import org.springframework.security.crypto.password.PasswordEncoder;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.lmlasmo.ms.user.dto.auth.LoginDTO;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SignupDTO extends LoginDTO{

	@JsonProperty
	@NotNull
	@Valid
	private RegisterProfileDTO profile;

	public void encodePassword(PasswordEncoder encoder) {
		String password = encoder.encode(getPassword());
		setPassword(password);
	}

}
