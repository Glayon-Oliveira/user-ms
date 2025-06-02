package com.lmlasmo.ms.user.token;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lmlasmo.ms.user.base.UserBaseTest;
import com.lmlasmo.ms.user.dto.auth.UserAuthDTO;
import com.lmlasmo.ms.user.dto.token.JWTTokenDTO;
import com.lmlasmo.ms.user.mapper.UserMapper;
import com.lmlasmo.ms.user.security.token.AccessJWTProvider;
import com.lmlasmo.ms.user.security.token.RefleshJWTProvider;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@AutoConfigureMockMvc
public class JWTTokenTest extends UserBaseTest{

	@Autowired private RefleshJWTProvider refleshProvider;
	@Autowired private AccessJWTProvider accessProvider;
	@Autowired protected MockMvc mockMvc;
	@Autowired protected ObjectMapper jMapper;

	protected String refleshToken;
	protected String accessToken;

	@BeforeEach
	public void setUpToken() {
		this.refleshToken = refleshProvider.gerateRefleshToken(user);
		UserAuthDTO userAuth = Mappers.getMapper(UserMapper.class).toUserAuthDTO(user);
		this.accessToken = accessProvider.gerateAccessToken(userAuth);
	}

	@Test
	public void checkRefleshTest() throws Exception {
		JWTTokenDTO tokenDto = new JWTTokenDTO(refleshToken);

		mockMvc.perform(MockMvcRequestBuilders.get("/api/jwt/access")
				.contentType(MediaType.APPLICATION_JSON)
				.content(jMapper.writeValueAsString(tokenDto)))
		.andExpect(MockMvcResultMatchers.status().isOk());
	}

	@Test
	public void checkAccessTest() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/api/user")
				.header("Authorization", "Bearer " + accessToken))
		.andExpect(MockMvcResultMatchers.status().isOk());
	}

}
