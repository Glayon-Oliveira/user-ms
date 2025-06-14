package com.lmlasmo.ms.user.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.lmlasmo.ms.user.base.JWTTokenBaseTest;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@AutoConfigureMockMvc
public class UserControllerTest extends JWTTokenBaseTest{

	@Autowired protected MockMvc mockMvc;

	@Test
	public void checkDelete() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.delete("/api/user")
				.header("Authorization", "Bearer " + accessToken))
		.andExpect(MockMvcResultMatchers.status().isNoContent());

		mockMvc.perform(MockMvcRequestBuilders.get("/api/user")
				.header("Authorization", "Bearer " + accessToken))
		.andExpect(MockMvcResultMatchers.status().isForbidden());
	}

}
