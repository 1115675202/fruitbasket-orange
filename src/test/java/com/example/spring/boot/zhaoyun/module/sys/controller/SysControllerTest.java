package com.example.spring.boot.zhaoyun.module.sys.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringRunner.class)
@SpringBootTest
class SysControllerTest {

	@Autowired
	private WebApplicationContext wac;

	MockMvc mockMvc;

	@BeforeEach
	void before() {
		mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
	}

	@Test
	void listConfigs() throws Exception {
		MvcResult mvcResult = mockMvc.perform(
				MockMvcRequestBuilders
						.get("/sys/configs")
						.contentType(MediaType.APPLICATION_FORM_URLENCODED)
						.param("id", "1"))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andDo(MockMvcResultHandlers.print())
				.andReturn();
		System.out.println(mvcResult.getResponse().getContentAsString());
	}

//	@Test
//	void listConfigs2() throws Exception {
//		ObjectMapper om = new ObjectMapper();
//		Object o = new Object();
//		String s = om.writeValueAsString(o);
//		MvcResult mvcResult = mockMvc.perform(
//				MockMvcRequestBuilders
//						.post("/sys/configs")
//						.contentType(MediaType.APPLICATION_JSON)
//						.content(s))
//				.andExpect(MockMvcResultMatchers.status().isOk())
//				.andDo(MockMvcResultHandlers.print())
//				.andReturn();
//		System.out.println(mvcResult.getResponse().getContentAsString());
//	}
}