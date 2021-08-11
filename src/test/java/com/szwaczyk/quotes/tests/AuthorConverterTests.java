package com.szwaczyk.quotes.tests;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.szwaczyk.quotes.controller.rest.serialization.AuthorConverter;
import com.szwaczyk.quotes.model.Author;
import com.szwaczyk.quotes.model.nonpersistable.AuthorJson;

@SpringBootTest
public class AuthorConverterTests {


	private AuthorConverter authorConverter;
	
	@Autowired
	public AuthorConverterTests(AuthorConverter authorConverter) {
		this.authorConverter = authorConverter;
	}
	
	@Test
	void convertAuthorJson_GivenNameNull_ShouldBeNameNull() {
		// Given
		String value = null;
		var authorJson = mock(AuthorJson.class);
		when(authorJson.getName()).thenReturn(value);
		
		// When
		Author author = authorConverter.convertAuthorJson(authorJson);
		
		//Then
		Assertions.assertEquals(author.getName(), value);
	}
	
	@Test
	void convertAuthorJson_GivenNameHans_ShouldBeNameHans() {
		// Given
		String value = "Hans";
		var authorJson = mock(AuthorJson.class);
		when(authorJson.getName()).thenReturn(value);
		
		// When
		Author author = authorConverter.convertAuthorJson(authorJson);
		
		//Then
		Assertions.assertEquals(author.getName(), value);
	}
	
	@Test
	void convertAuthorJson_GivenEmptyName_ShouldBeEmptyNameHans() {
		// Given
		String value = "";
		var authorJson = mock(AuthorJson.class);
		when(authorJson.getName()).thenReturn(value);
		
		// When
		Author author = authorConverter.convertAuthorJson(authorJson);
		
		//Then
		Assertions.assertEquals(author.getName(), value);
	}
	
	@Test
	void convertAuthorJson_GivenSurnameNull_ShouldBeSurnameNull() {
		// Given
		String value = null;
		var authorJson = mock(AuthorJson.class);
		when(authorJson.getSurname()).thenReturn(value);
		
		// When
		Author author = authorConverter.convertAuthorJson(authorJson);
		
		//Then
		Assertions.assertEquals(author.getSurname(), value);
	}
	
	@Test
	void convertAuthorJson_GivenSurnameZimmerman_ShouldBeSurnameZimmerman() {
		// Given
		String value = "Zimmerman";
		var authorJson = mock(AuthorJson.class);
		when(authorJson.getSurname()).thenReturn(value);
		
		// When
		Author author = authorConverter.convertAuthorJson(authorJson);
		
		//Then
		Assertions.assertEquals(author.getSurname(), value);
	}
	
	@Test
	void convertAuthorJson_GivenSurnameEmpty_ShouldBeSurnameEmpty() {
		// Given
		String value = "";
		var authorJson = mock(AuthorJson.class);
		when(authorJson.getSurname()).thenReturn(value);
		
		// When
		Author author = authorConverter.convertAuthorJson(authorJson);
		
		//Then
		Assertions.assertEquals(author.getSurname(), value);
	}
	
	@Test
	void convertAuthor_GivenNameNull_ShouldBeNameNull() {
		// Given
		String value = null;
		var author = mock(Author.class);
		when(author.getName()).thenReturn(value);
		
		// When
		AuthorJson authorJson = authorConverter.convertAuthor(author);
		
		//Then
		Assertions.assertEquals(authorJson.getName(), value);
	}
	
	@Test
	void convertAuthor_GivenNameHans_ShouldBeNameHans() {
		// Given
		String value = "Hans";
		var author = mock(Author.class);
		when(author.getName()).thenReturn(value);
		
		// When
		AuthorJson authorJson = authorConverter.convertAuthor(author);
		
		//Then
		Assertions.assertEquals(authorJson.getName(), value);
	}
	
	@Test
	void convertAuthor_GivenEmptyName_ShouldBeEmptyName() {
		// Given
		String value = "";
		var author = mock(Author.class);
		when(author.getName()).thenReturn(value);
		
		// When
		AuthorJson authorJson = authorConverter.convertAuthor(author);
		
		//Then
		Assertions.assertEquals(authorJson.getName(), value);
	}
	
	@Test
	void convertAuthor_GivenSurnameNull_ShouldBeSurnameNull() {
		// Given
		String value = null;
		var author = mock(Author.class);
		when(author.getSurname()).thenReturn(value);
		
		// When
		AuthorJson authorJson = authorConverter.convertAuthor(author);
		
		//Then
		Assertions.assertEquals(authorJson.getSurname(), value);
	}
	
	@Test
	void convertAuthor_GivenSurnameZimmerman_ShouldBeSurnameZimmerman() {
		// Given
		String value = "Zimerman";
		var author = mock(Author.class);
		when(author.getSurname()).thenReturn(value);
		
		// When
		AuthorJson authorJson = authorConverter.convertAuthor(author);
		
		//Then
		Assertions.assertEquals(authorJson.getSurname(), value);
	}
	
	@Test
	void convertAuthor_GivenSurnameEmpty_ShouldBeSurnameEmpty() {
		// Given
		String value = "";
		var author = mock(Author.class);
		when(author.getSurname()).thenReturn(value);
		
		// When
		AuthorJson authorJson = authorConverter.convertAuthor(author);
		
		//Then
		Assertions.assertEquals(authorJson.getSurname(), value);
	}
}
