package com.lmlasmo.ms.user.controller;

import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.lmlasmo.ms.user.base.JWTTokenBaseTest;
import com.lmlasmo.ms.user.dto.update.UpdateProfileDTO;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@AutoConfigureMockMvc
public class ProfileControllerTest extends JWTTokenBaseTest{

	@Autowired protected MockMvc mockMvc;

	@Test
	public void checkUpdateProfile() throws Exception {
		UpdateProfileDTO update = new UpdateProfileDTO();
		update.setName(UUID.randomUUID().toString());
		update.setTel("746912736-");

		mockMvc.perform(MockMvcRequestBuilders.put("/api/profile")
				.header("Authorization", "Bearer " + accessToken)
				.contentType(MediaType.APPLICATION_JSON)
				.content(jMapper.writeValueAsString(update)))
		.andExpect(MockMvcResultMatchers.status().isNoContent());
	}

}
