package com.lmlasmo.ms.user.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.lmlasmo.ms.user.base.JWTTokenBaseTest;
import com.lmlasmo.ms.user.dto.UserDTO;
import com.lmlasmo.ms.user.dto.token.JWTTokenDTO;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@AutoConfigureMockMvc
public class JWTControllerTest extends JWTTokenBaseTest{

	@Autowired protected MockMvc mockMvc;

	@Test
	public void checkRefleshTest() throws Exception {
		JWTTokenDTO tokenDto = new JWTTokenDTO(refleshToken);

		String response = mockMvc.perform(MockMvcRequestBuilders.get("/api/jwt/access")
				.contentType(MediaType.APPLICATION_JSON)
				.content(jMapper.writeValueAsString(tokenDto)))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andReturn().getResponse().getContentAsString();

		tokenDto = jMapper.readValue(response, JWTTokenDTO.class);
	}

	@Test
	public void checkAccessTest() throws Exception {
		String response = mockMvc.perform(MockMvcRequestBuilders.get("/api/user")
				.header("Authorization", "Bearer " + accessToken))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andReturn().getResponse().getContentAsString();

		UserDTO user = jMapper.readValue(response, UserDTO.class);

		if(user.getEmail() == null) throw new Exception("Email is null");

		if(user.getCreatedAt() == null) throw new Exception("CreatedAt is null");
	}

}
