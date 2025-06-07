package com.lmlasmo.ms.user.controller;

import java.math.BigInteger;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.lmlasmo.ms.user.dto.UserDTO;
import com.lmlasmo.ms.user.model.User;
import com.lmlasmo.ms.user.security.UserAuth;
import com.lmlasmo.ms.user.service.UserService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@ResponseBody
@RequestMapping("/api/user")
public class UserController {

	private UserService userService;

	@GetMapping
	@ResponseStatus(code = HttpStatus.OK)
	public UserDTO getI() {
		BigInteger userId = UserAuth.getAuth().idToBigInteger();
		User user = userService.getUserRepository().findById(userId).get();
		return userService.getUserMapper().toUserDTO(user);
	}

	@DeleteMapping
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public Void deleteI() {
		BigInteger userId = UserAuth.getAuth().idToBigInteger();
		userService.deleteById(userId);
		return null;
	}

}
