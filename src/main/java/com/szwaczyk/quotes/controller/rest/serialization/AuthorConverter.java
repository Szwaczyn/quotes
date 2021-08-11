package com.szwaczyk.quotes.controller.rest.serialization;

import org.springframework.stereotype.Component;

import com.szwaczyk.quotes.model.Author;
import com.szwaczyk.quotes.model.nonpersistable.AuthorJson;

@Component
public class AuthorConverter {

	public Author convertAuthorJson(AuthorJson authorJson) {
		if(authorJson == null) return null;
		
		return new Author.Builder()
				.name(authorJson.getName())
				.suranem(authorJson.getSurname())
				.build();
	}
	
	public AuthorJson convertAuthor(Author author) {
		if(author == null) return null;
		
		return new AuthorJson.Builder()
				.name(author.getName())
				.surname(author.getSurname())
				.build();
	}
}
