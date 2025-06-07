package com.lmlasmo.ms.user.base;

import java.util.UUID;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.transaction.TestTransaction;
import org.springframework.transaction.annotation.Transactional;

import com.lmlasmo.ms.user.UserApplicationTests;
import com.lmlasmo.ms.user.dto.register.RegisterProfileDTO;
import com.lmlasmo.ms.user.dto.register.SignupDTO;
import com.lmlasmo.ms.user.model.User;
import com.lmlasmo.ms.user.service.UserService;

@Transactional
public abstract class UserBaseTest extends UserApplicationTests {

	@Autowired private UserService userService;

	protected User user;
	protected String email = "test@gmail.com";
	protected String password = UUID.randomUUID().toString()+"A1";

	@BeforeEach
	public void setUp() {
		RegisterProfileDTO rProfile = new RegisterProfileDTO();
		rProfile.setName("Test");

		SignupDTO signup = new SignupDTO();
		signup.setEmail(email);
		signup.setPassword(password);
		signup.setProfile(rProfile);

		userService.save(signup);
		user = userService.getUserRepository().findByEmail(email).orElseGet(() -> null);
	}

	@AfterEach
	public void tearDown() {
		TestTransaction.flagForRollback();
	}

}
