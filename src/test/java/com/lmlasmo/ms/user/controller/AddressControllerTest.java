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
import com.lmlasmo.ms.user.dto.AddressDTO;
import com.lmlasmo.ms.user.dto.register.RegisterAddressDTO;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@AutoConfigureMockMvc
public class AddressControllerTest extends JWTTokenBaseTest{

	@Autowired protected MockMvc mockMvc;

	@Test
	public void checkCreateAddress() throws Exception {
		RegisterAddressDTO register = new RegisterAddressDTO();
		register.setNation("BR");
		register.setState("SE");
		register.setCity("Aracaju");
		register.setDistrict("Center");
		register.setStreet("Test Street");
		register.setNumber("NaN");

		String response = mockMvc.perform(MockMvcRequestBuilders.post("/api/address")
				.header("Authorization", "Bearer " + accessToken)
				.contentType(MediaType.APPLICATION_JSON)
				.content(jMapper.writeValueAsString(register)))
				.andExpect(MockMvcResultMatchers.status().isCreated())
				.andReturn().getResponse().getContentAsString();

		AddressDTO address = jMapper.readValue(response, AddressDTO.class);

		if(address.getNation() == null) throw new Exception("Nation is null");

		if(address.getState() == null) throw new Exception("State is null");

		if(address.getCity() == null) throw new Exception("City is null");

		if(address.getDistrict() == null) throw new Exception("District is null");

		if(address.getStreet() == null) throw new Exception("Street is null");

		if(address.getNumber() == null) throw new Exception("Number is null");
	}

}
