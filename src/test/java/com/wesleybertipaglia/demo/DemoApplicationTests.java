package com.wesleybertipaglia.demo;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wesleybertipaglia.demo.dtos.DemoCreateDTO;
import com.wesleybertipaglia.demo.dtos.DemoReadDTO;
import com.wesleybertipaglia.demo.dtos.DemoUpdateDTO;
import com.wesleybertipaglia.demo.services.DemoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.List;
import java.util.Optional;

@SpringBootTest
@ActiveProfiles("test")
class DemoApplicationTests {

	@Autowired
	private WebApplicationContext webApplicationContext;

	@Autowired
	private ObjectMapper objectMapper;

	@MockBean
	private DemoService demoService;

	private MockMvc mockMvc;

	@BeforeEach
	void setUp() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	@Test
	void testCreateDemo() throws Exception {
		DemoCreateDTO demoCreateDto = new DemoCreateDTO("SWE 1", "Software Engineering 1");
		DemoReadDTO demoReadDto = new DemoReadDTO(1L, "SWE 1", "Software Engineering 1");

		when(demoService.createDemo(any(DemoCreateDTO.class))).thenReturn(demoReadDto);

		mockMvc.perform(post("/demos")
				.contentType("application/json")
				.content(objectMapper.writeValueAsString(demoCreateDto)))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.title").value("SWE 1"))
				.andExpect(jsonPath("$.description").value("Software Engineering 1"));
	}

	@Test
	void testListDemos() throws Exception {
		DemoReadDTO demoReadDto = new DemoReadDTO(1L, "SWE 1", "Software Engineering 1");

		when(demoService.listDemos()).thenReturn(List.of(demoReadDto));

		mockMvc.perform(get("/demos"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$[0].title").value("SWE 1"))
				.andExpect(jsonPath("$[0].description").value("Software Engineering 1"));
	}

	@Test
	void testGetDemo() throws Exception {
		DemoReadDTO demoReadDto = new DemoReadDTO(1L, "SWE 1", "Software Engineering 1");

		when(demoService.getDemo(1L)).thenReturn(demoReadDto);

		mockMvc.perform(get("/demos/1"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.title").value("SWE 1"))
				.andExpect(jsonPath("$.description").value("Software Engineering 1"));
	}

	@Test
	void testUpdateDemo() throws Exception {
		DemoUpdateDTO demoUpdateDto = new DemoUpdateDTO(Optional.of("Updated Title"),
				Optional.of("Updated Description"));
		DemoReadDTO demoReadDto = new DemoReadDTO(1L, "Updated Title", "Updated Description");

		when(demoService.updateDemo(anyLong(), any(DemoUpdateDTO.class))).thenReturn(demoReadDto);

		mockMvc.perform(put("/demos/1")
				.contentType("application/json")
				.content(objectMapper.writeValueAsString(demoUpdateDto)))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.title").value("Updated Title"))
				.andExpect(jsonPath("$.description").value("Updated Description"));
	}

	@Test
	void testDeleteDemo() throws Exception {
		mockMvc.perform(delete("/demos/1"))
				.andExpect(status().isOk());
	}
}