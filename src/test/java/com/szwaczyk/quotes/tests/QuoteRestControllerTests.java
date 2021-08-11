package com.szwaczyk.quotes.tests;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.szwaczyk.quotes.model.Quote;
import com.szwaczyk.quotes.service.interfaces.QuoteService;

@SpringBootTest
@AutoConfigureMockMvc
public class QuoteRestControllerTests {

	@MockBean
	private QuoteService quoteService;
	
	private MockMvc mockMvc;
	
	@Autowired
	public QuoteRestControllerTests(MockMvc mockMvc) {
		this.mockMvc = mockMvc;
	}
	
	@Test
	void should_CreateMockMvc() {
		Assertions.assertNotNull(mockMvc);
	}
	
	@Test
	void get_quote_shouldReturn200() throws Exception {
		this.mockMvc.perform(get("/quote/"))
			.andExpect(status().isOk());
	}
	
	@Test
	void get_quote_ShouldReturnArrayQuoteWithStatus200() throws Exception{
		// When
		when(quoteService.findAll()).thenReturn(getQuoteSetWithoutAuthor());
		
		// Then
		this.mockMvc.perform(get("/quote/"))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$[5].content").value("quote") );
	}
	
	@Test
	void get_quoteById_shouldReturn_404() throws Exception{
		this.mockMvc.perform(get("/quote/123"))
			.andExpect(status().isNotFound());
	}
	
	@Test
	void get_quoteById_ShouldReturnQuoteWithStatus200() throws Exception{
		// When
		when(quoteService.findById("17")).thenReturn(getQuotaWithoutAuthor());
		
		// Then
		this.mockMvc.perform(get("/quote/17"))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.content").value("only test") );
	}
	
	@Test
	void get_quoteById_wrongIdFormat_expectedStatus404() throws Exception{
		this.mockMvc.perform(get("/quote/asd"))
			.andExpect(status().isNotFound());
		
	}
	
	@Test
	void put_quote_createNew_expectetStatus201() throws Exception {
		this.mockMvc
			.perform(put("/quote/2")
			.contentType(MediaType.APPLICATION_JSON)
			.content("{\"content\" : \"some quote\"}"))
			.andExpect(status().isCreated())
			.andExpect(jsonPath("$.content").value("some quote"))
			.andExpect(jsonPath("$.id").exists());
	}
	
	@Test
	void put_quote_createUpdate_ExpectStatus204() throws Exception {
		// When
		when(quoteService.findById("2")).thenReturn(getQuotaWithoutAuthor());
		
		// Then
		this.mockMvc
			.perform(put("/quote/2")
			.contentType(MediaType.APPLICATION_JSON)
			.content("{\"content\" : \"some quote\"}"))
			.andExpect(status().isNoContent());
	}
	
	private Quote getQuotaWithoutAuthor() {
		return new Quote.Builder()
				.content("only test")
				.build();
	}
	
	private Set<Quote> getQuoteSetWithoutAuthor(){
		Set<Quote> quoteSet = new HashSet<Quote>();
		
		for(int i = 1; i <= 15; i ++) {
			quoteSet.add(new Quote.Builder()
					.content("quote")
					.build());
		}
		
		return quoteSet;
	}
}
