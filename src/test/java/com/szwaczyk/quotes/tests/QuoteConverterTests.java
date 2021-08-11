package com.szwaczyk.quotes.tests;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.szwaczyk.quotes.controller.rest.serialization.QuoteConverter;
import com.szwaczyk.quotes.model.Quote;
import com.szwaczyk.quotes.model.nonpersistable.AuthorJson;
import com.szwaczyk.quotes.model.nonpersistable.QuoteJson;

@SpringBootTest
public class QuoteConverterTests {

	private QuoteConverter quoteConverter;
	
	@Autowired
	public QuoteConverterTests(QuoteConverter quoteConverter) {
		this.quoteConverter = quoteConverter;
	}
	
	@Test
	void quoteConverter_injected() {
		Assertions.assertNotNull(quoteConverter);
	}
	
	@Test
	void convertQuoteJson_GivenContentNull_ShouldBeContentNull() {
		// Given
		String value = null;
		var quoteJson = mock(QuoteJson.class);
		when(quoteJson.getContent()).thenReturn(value);
		
		// When
		Quote quote = quoteConverter.convertQuoteJson(quoteJson);
		
		//Then
		Assertions.assertEquals(quote.getContent(), value);
	}
	
	@Test
	void convertQuoteJson_GivenContentSomeString_ShouldBeContentSomeString() {
		// Given
		String value = "Some String";
		var quoteJson = mock(QuoteJson.class);
		when(quoteJson.getContent()).thenReturn(value);
		
		// When
		Quote quote = quoteConverter.convertQuoteJson(quoteJson);
		
		//Then
		Assertions.assertEquals(quote.getContent(), value);
	}
	
	@Test
	void convertQuoteJson_GivenContentEmpty_ShouldBeContentEmpty() {
		// Given
		String value = "";
		var quoteJson = mock(QuoteJson.class);
		when(quoteJson.getContent()).thenReturn(value);
		
		// When
		Quote quote = quoteConverter.convertQuoteJson(quoteJson);
		
		//Then
		Assertions.assertEquals(quote.getContent(), value);
	}
	
	@Test
	void convertQuoteJsonAndAuthor_GivenContentSomeStringAuthorNull_ShouldBeContentSomeStringAuthorNull() {
		// Given
		String value = "Some string";
		
		var quoteJson = mock(QuoteJson.class);
		when(quoteJson.getContent()).thenReturn(value);
		when(quoteJson.getAuthorJson()).thenReturn(null);
		
		// When
		Quote quote = quoteConverter.convertQuoteJsonAndAuthor(quoteJson);
		
		//Then
		Assertions.assertEquals(quote.getContent(), value);
		Assertions.assertNull(quote.getAuthor());
	}
	
	@Test
	void convertQuoteJsonAndAuthor_GivenContentSomeStringAuthor_ShouldBeContentSomeStringAuthor() {
		// Given
		String value = "Some string";
		String name = "jan";
		String surname = "pawlak";
		
		var authorJson = mock(AuthorJson.class);
		when(authorJson.getName()).thenReturn(name);
		when(authorJson.getSurname()).thenReturn(surname);
		
		var quoteJson = mock(QuoteJson.class);
		when(quoteJson.getContent()).thenReturn(value);
		when(quoteJson.getAuthorJson()).thenReturn(authorJson);
		
		// When
		Quote quote = quoteConverter.convertQuoteJsonAndAuthor(quoteJson);

		//Then
		Assertions.assertEquals(quote.getContent(), value);
		Assertions.assertNotNull(quote.getAuthor());
		Assertions.assertEquals(quote.getAuthor().getName(), name);
		Assertions.assertEquals(quote.getAuthor().getSurname(), surname);
	}
	
	@Test
	void convertQuoteAndAuthor_GivenContentSomeStringAuthorNull_ShouldBeContentSomeStringAuthorNull() {
		// Given
		String value = "Some string";
		
		var quote = mock(Quote.class);
		when(quote.getContent()).thenReturn(value);
		when(quote.getAuthor()).thenReturn(null);
		
		// When
		QuoteJson quoteJson = quoteConverter.convertQuoteAndAuthor(quote);
		
		//Then
		Assertions.assertEquals(quoteJson.getContent(), value);
		Assertions.assertNull(quoteJson.getAuthorJson());
	}

}
