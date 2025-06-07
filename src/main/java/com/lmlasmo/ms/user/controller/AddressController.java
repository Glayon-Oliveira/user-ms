package com.lmlasmo.ms.user.controller;

import java.math.BigInteger;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.lmlasmo.ms.user.dto.AddressDTO;
import com.lmlasmo.ms.user.dto.register.RegisterAddressDTO;
import com.lmlasmo.ms.user.dto.update.UpdateAddressDTO;
import com.lmlasmo.ms.user.security.UserAuth;
import com.lmlasmo.ms.user.service.AddressService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@ResponseBody
@RequestMapping("/api/address")
public class AddressController {

	private AddressService addressService;

	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public AddressDTO register(@RequestBody @Valid RegisterAddressDTO register) {
		return addressService.save(register, UserAuth.getAuth().idToBigInteger());
	}

	@PutMapping
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public Void update(@RequestBody @Valid UpdateAddressDTO update) {
		BigInteger userId = UserAuth.getAuth().idToBigInteger();
		addressService.update(update, userId);
		return null;
	}

	@GetMapping
	@ResponseStatus(code = HttpStatus.OK)
	public AddressDTO getI() {
		BigInteger userId = UserAuth.getAuth().idToBigInteger();
		return addressService.findByUser(userId);
	}

}
