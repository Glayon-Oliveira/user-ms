package com.lmlasmo.ms.user.dto.token;

import java.math.BigInteger;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class JWTAccessKeyComponentsDTO {

	@JsonProperty
	private BigInteger modulus;

	@JsonProperty
	private BigInteger expoent;

}
