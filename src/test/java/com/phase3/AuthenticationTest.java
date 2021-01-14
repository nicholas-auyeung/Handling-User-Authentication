package com.phase3;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

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
		AuthenticationController.userList.add(new User(1, "nick", "pass", "admin"));
	}
	
	
	@Test
	public void userNameTest() throws Exception{
		mockMvc.perform(get("/username"))
				.andExpect(status().isOk())
				.andExpect(content().string("nick"));
			
	}
	
	@Test
	public void roleTest() throws Exception{
		mockMvc.perform(get("/role"))
				.andExpect(status().isOk())
				.andExpect(content().string("admin"));
			
	}
	
	@Test
	public void userTest() throws Exception{
		mockMvc.perform(get("/user"))
				.andExpect(status().isOk())
				.andExpect(content().contentType("application/json;"))
				.andExpect(jsonPath("$.username").value("nick"))
				.andExpect(jsonPath("$.password").value("pass"))
				.andExpect(jsonPath("$.role").value("admin"));
	}
	
	@Test
	public void loginTest() throws Exception{
		mockMvc.perform(post("/login")
						.param("username", "nick")
						.param("password", "pass"))
			.andExpect(status().isOk())
			.andExpect(content().string("true"));
	}
	
	@Test
	public void failLoginTest() throws Exception{
		mockMvc.perform(post("/login")
						.param("username", "nick")
						.param("password", "wrongpass"))
			.andExpect(status().isOk())
			.andExpect(content().string("true"));
	}
	
	
	@Test
	public void logoutTest() throws Exception{
		mockMvc.perform(post("/logout"))
				.andExpect(status().isOk())
				.andExpect(content().string(""));
	}

}
