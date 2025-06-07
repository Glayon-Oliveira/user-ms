package com.lmlasmo.ms.user.service;

import java.math.BigInteger;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lmlasmo.ms.user.dto.FullUserDTO;
import com.lmlasmo.ms.user.dto.register.SignupDTO;
import com.lmlasmo.ms.user.mapper.UserMapper;
import com.lmlasmo.ms.user.model.User;
import com.lmlasmo.ms.user.repository.UserRepository;

import jakarta.persistence.EntityExistsException;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Service
@Transactional
public class UserService {

	private UserRepository userRepository;
	private UserMapper userMapper;
	private PasswordEncoder encoder;

	public FullUserDTO save(SignupDTO signup) {
		if(userRepository.existsByEmail(signup.getEmail())) throw new EntityExistsException("Email is used.");

		signup.encodePassword(encoder);

		User user = userMapper.toUser(signup);
		user.getProfile().setUser(user);

		user = userRepository.save(user);
		return userMapper.toFullUserDTO(user);
	}

	public void deleteById(BigInteger id) {
		userRepository.deleteById(id);
	}

}
