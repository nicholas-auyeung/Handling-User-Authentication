package com.phase3;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.phase3.controller.AuthenticationController;
import com.phase3.model.User;


public class AuthenticationTest extends HandlingAuthApplicationTests{
	
	@Autowired
	private WebApplicationContext webApplicationContext;

	private MockMvc mockMvc;

	@Before
	public void setup() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
		AuthenticationController.userList.add(new User(1, "nick2", "pass"));
	}
	

	@Test
	public void loginTest() throws Exception{
		mockMvc.perform(post("/login")
						.param("username", "nick")
						.param("password", "pass"))
			.andExpect(status().isOk())
			.andExpect(content().string("true"));
	}

}
