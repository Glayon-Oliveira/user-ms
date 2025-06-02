package com.lmlasmo.ms.user.base;

import java.util.UUID;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.transaction.TestTransaction;
import org.springframework.transaction.annotation.Transactional;

import com.lmlasmo.ms.user.UserApplicationTests;
import com.lmlasmo.ms.user.model.User;
import com.lmlasmo.ms.user.repository.UserRepository;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Transactional
public abstract class UserBaseTest extends UserApplicationTests {

	@Autowired private UserRepository userRepository;
	@Autowired private PasswordEncoder encoder;
	protected User user;

	protected String email = "test@gmail.com";
	protected String password = UUID.randomUUID().toString()+"A1";

	@BeforeEach
	public void setUp() {
		User user = new User();
		user.setEmail(email);
		user.setPassword(encoder.encode(password));
		this.user = userRepository.save(user);
	}

	@AfterEach
	public void tearDown() {
		TestTransaction.flagForRollback();
	}

}
