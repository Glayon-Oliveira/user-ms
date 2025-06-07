package com.lmlasmo.ms.user.controller;

import java.security.interfaces.RSAPublicKey;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.lmlasmo.ms.user.dto.token.JWTAccessKeyComponentsDTO;
import com.lmlasmo.ms.user.dto.token.JWTTokenDTO;
import com.lmlasmo.ms.user.security.UserAuth;
import com.lmlasmo.ms.user.security.token.AccessJWTProvider;
import com.lmlasmo.ms.user.security.token.KeysProvider;
import com.lmlasmo.ms.user.security.token.RefleshJWTProvider;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@ResponseBody
@RequestMapping("/api/jwt")
public class JWTController {

	private RefleshJWTProvider refleshProvider;
	private AccessJWTProvider accessProvider;

	@GetMapping("/pk")
	@ResponseStatus(code = HttpStatus.OK)
	public JWTAccessKeyComponentsDTO getPublicAccess() {
		RSAPublicKey publicKey = (RSAPublicKey) KeysProvider.getAccessKeys().getPublic();
		return new JWTAccessKeyComponentsDTO(publicKey.getPublicExponent(), publicKey.getModulus());
	}

	@GetMapping("/access")
	@ResponseStatus(code = HttpStatus.OK)
	public JWTTokenDTO verifyReflesh(@RequestBody @Valid JWTTokenDTO refleshToken) {
		UserAuth userAuth = refleshProvider.validateRefleshToken(refleshToken.getJwtToken());
		String token = accessProvider.gerateAccessToken(userAuth);
		return new JWTTokenDTO(token);
	}

}
