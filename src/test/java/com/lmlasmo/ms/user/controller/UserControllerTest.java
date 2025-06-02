package com.lmlasmo.ms.user.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lmlasmo.ms.user.base.UserBaseTest;
import com.lmlasmo.ms.user.dto.auth.LoginDTO;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@AutoConfigureMockMvc
public class UserControllerTest extends UserBaseTest{

	@Autowired protected MockMvc mockMvc;

	@Autowired ObjectMapper jMapper;

	@Test
	public void checkLoginTest() throws JsonProcessingException, Exception {
		LoginDTO login = new LoginDTO();
		login.setEmail(email);
		login.setPassword(password);

		mockMvc.perform(MockMvcRequestBuilders.post("/api/sign/in")
				.contentType(MediaType.APPLICATION_JSON)
				.content(jMapper.writeValueAsString(login)))
		.andExpect(MockMvcResultMatchers.status().isOk());
	}

}
