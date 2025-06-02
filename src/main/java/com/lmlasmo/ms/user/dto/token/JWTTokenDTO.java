package com.lmlasmo.ms.user.dto.token;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Service
public class JWTTokenDTO {

	@JsonProperty
	@JsonAlias({"jwt_token"})
	@NotBlank
	private String jwtToken;

}
