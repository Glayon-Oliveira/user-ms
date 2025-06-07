package com.lmlasmo.ms.user.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lmlasmo.ms.user.base.UserBaseTest;
import com.lmlasmo.ms.user.dto.FullUserDTO;
import com.lmlasmo.ms.user.dto.auth.LoginDTO;
import com.lmlasmo.ms.user.dto.register.RegisterProfileDTO;
import com.lmlasmo.ms.user.dto.register.SignupDTO;
import com.lmlasmo.ms.user.dto.token.JWTTokenDTO;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@AutoConfigureMockMvc
public class SignControllerTest extends UserBaseTest{

	@Autowired protected MockMvc mockMvc;
	@Autowired ObjectMapper jMapper;

	@Test
	public void checkLoginTest() throws Exception {
		LoginDTO login = new LoginDTO();
		login.setEmail(email);
		login.setPassword(password);

		String response = mockMvc.perform(MockMvcRequestBuilders.post("/api/sign/in")
				.contentType(MediaType.APPLICATION_JSON)
				.content(jMapper.writeValueAsString(login)))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andReturn().getResponse().getContentAsString();

		JWTTokenDTO token = jMapper.readValue(response, JWTTokenDTO.class);

		if(token.getJwtToken() == null) throw new Exception("Token is null");
	}

	@Test
	public void checkSignupTest() throws Exception {
		RegisterProfileDTO rProfile = new RegisterProfileDTO();
		rProfile.setName("Test");

		SignupDTO signup = new SignupDTO();
		signup.setEmail("signuptest@gmail.com");
		signup.setPassword(password);
		signup.setProfile(rProfile);

		String response = mockMvc.perform(MockMvcRequestBuilders.post("/api/sign/up")
				.contentType(MediaType.APPLICATION_JSON)
				.content(jMapper.writeValueAsString(signup)))
				.andExpect(MockMvcResultMatchers.status().isCreated())
				.andReturn().getResponse().getContentAsString();

		FullUserDTO user = jMapper.readValue(response, FullUserDTO.class);

		if(user.getEmail() == null) throw new Exception("Email is null");

		if((user.getCreatedAt() == null) || (user.getProfile() == null)) throw new Exception("CreatedAt is null");
	}

}
