package com.pair.assignment;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pair.assignment.pojo.TradeStore;
import com.pair.assignment.service.TradeStoreService;

@SpringBootTest
@AutoConfigureMockMvc
public class TradeStoreControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Test
	void addStore() throws Exception {
		this.mockMvc
				.perform(
						post("/tradestore/save")
								.content(
										asJsonString(new TradeStore("T5", 2,
												"CP-3", "B3", TradeStoreService
														.getYearDate(1))))
								.contentType(MediaType.APPLICATION_JSON)
								.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.version").exists());
	}

	@Test
	@DisplayName("Maturity Date Validation")
	void addStoreWithInValidMaturityDate() throws Exception {
		this.mockMvc
				.perform(
						post("/tradestore/save")
								.content(
										asJsonString(new TradeStore("T7", 2,
												"CP-3", "B3", TradeStoreService
														.getYearDate(-1))))
								.contentType(MediaType.APPLICATION_JSON)
								.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isBadRequest())
				.andExpect(
						MockMvcResultMatchers.jsonPath("$.message").value(
								"Validation Failed"))
				.andExpect(
						MockMvcResultMatchers
								.jsonPath("$.details")
								.value("[maturityDate:maturity date should be current or future date]"));
	}

	@Test
	void getTradeStoreS() throws Exception {
		this.mockMvc.perform(get("/tradestore/get")).andDo(print())
				.andExpect(status().isOk());
	}

	public static String asJsonString(final Object obj) {
		try {
			final ObjectMapper mapper = new ObjectMapper();
			final String jsonContent = mapper.writeValueAsString(obj);
			return jsonContent;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
