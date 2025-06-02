package com.lmlasmo.ms.user.controller;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.lmlasmo.ms.user.dto.auth.LoginDTO;
import com.lmlasmo.ms.user.dto.token.JWTTokenDTO;
import com.lmlasmo.ms.user.model.User;
import com.lmlasmo.ms.user.security.token.RefleshJWTProvider;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@ResponseBody
@RequestMapping("/api/sign")
public class SignController {

	private AuthenticationManager manager;
	private RefleshJWTProvider refleshProvider;

	@PostMapping("/in")
	@ResponseStatus(code = HttpStatus.OK)
	public JWTTokenDTO in(@RequestBody @Valid LoginDTO login) {
		Authentication auth = new UsernamePasswordAuthenticationToken(login.getEmail(), login.getPassword());
		auth = manager.authenticate(auth);

		User user = (User) auth.getPrincipal();

		String token = refleshProvider.gerateRefleshToken(user);
		return new JWTTokenDTO(token);
	}

}
