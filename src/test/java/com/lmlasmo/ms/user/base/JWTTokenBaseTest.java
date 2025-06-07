package com.lmlasmo.ms.user.base;

import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lmlasmo.ms.user.security.UserAuth;
import com.lmlasmo.ms.user.security.token.AccessJWTProvider;
import com.lmlasmo.ms.user.security.token.RefleshJWTProvider;

public abstract class JWTTokenBaseTest extends UserBaseTest{

	@Autowired private RefleshJWTProvider refleshProvider;
	@Autowired private AccessJWTProvider accessProvider;
	@Autowired protected ObjectMapper jMapper;

	protected String refleshToken;
	protected String accessToken;

	@BeforeEach
	public void setUpToken() {
		this.refleshToken = refleshProvider.gerateRefleshToken(user);
		UserAuth userAuth = refleshProvider.validateRefleshToken(refleshToken);
		this.accessToken = accessProvider.gerateAccessToken(userAuth);
	}

}
