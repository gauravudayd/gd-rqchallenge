package com.example.rqchallenge;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class RqChallengeApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Test
	void contextLoads() {
	}

	@Test
	public void getAllEmployees() throws Exception {
		this.mockMvc.perform(get("/employees")).andDo(print()).andExpect(status().isOk());
	}

	@Test
	public void getAllEmployeesIncorrectCall() throws Exception {
		this.mockMvc.perform(get("/emp")).andDo(print()).andExpect(status().isNotFound());
	}

	@Test
	public void getEmployeeByFirstName() throws Exception {
		this.mockMvc.perform(get("/employee/search/Charde")).andDo(print()).andExpect(status().isOk());
	}

	@Test
	public void getEmployeeByLastName() throws Exception {
		this.mockMvc.perform(get("/employee/search/gaines")).andDo(print()).andExpect(status().isOk());
	}

	@Test
	public void getEmployeeByFullName() throws Exception {
		this.mockMvc.perform(get("/employee/search/Gloria Little")).andDo(print()).andExpect(status().isOk());
	}

	@Test
	public void getEmployeeByExistingId() throws Exception {
		this.mockMvc.perform(get("/employee/12")).andDo(print()).andExpect(status().isOk());
	}

	@Test
	public void getEmployeeByNonExistingId() throws Exception {
		this.mockMvc.perform(get("/employee/3453")).andDo(print()).andExpect(status().isNotFound());
	}

	@Test
	public void topHighestSalary() throws Exception {
		this.mockMvc.perform(get("/highestSalary")).andDo(print()).andExpect(status().isOk());
	}

	@Test
	public void topTenHighestEarningEmployeeNames() throws Exception {
		this.mockMvc.perform(get("/topTenHighestEarningEmployeeNames")).andDo(print()).andExpect(status().isOk());
	}

	@Test
	public void createEmployee() throws Exception {
		this.mockMvc
				.perform(post("/create").contentType(MediaType.APPLICATION_JSON)
						.content("{\"name\":\"test\",\"salary\":\"123\",\"age\":\"23\"}"))
				.andDo(print()).andExpect(status().isOk());
	}

}
