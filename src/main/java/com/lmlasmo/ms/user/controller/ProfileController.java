package com.lmlasmo.ms.user.controller;

import java.math.BigInteger;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.lmlasmo.ms.user.dto.ProfileDTO;
import com.lmlasmo.ms.user.dto.update.UpdateProfileDTO;
import com.lmlasmo.ms.user.security.UserAuth;
import com.lmlasmo.ms.user.service.ProfileService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@ResponseBody
@RequestMapping("/api/profile")
public class ProfileController {

	private ProfileService profileService;

	@PutMapping
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public Void update(@RequestBody @Valid UpdateProfileDTO update) {
		BigInteger userId = UserAuth.getAuth().idToBigInteger();
		profileService.update(update, userId);
		return null;
	}

	@GetMapping
	@ResponseStatus(code = HttpStatus.OK)
	public ProfileDTO getI() {
		BigInteger userId = UserAuth.getAuth().idToBigInteger();
		return profileService.findByUser(userId);
	}

}
