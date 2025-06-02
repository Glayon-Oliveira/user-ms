package com.lmlasmo.ms.user.controller;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.lmlasmo.ms.user.dto.auth.UserAuthDTO;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@ResponseBody
@RequestMapping("/api/user")
public class UserController {

	@GetMapping
	@ResponseStatus(code = HttpStatus.OK)
	public UserAuthDTO getI() {
		return (UserAuthDTO) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	}

}
