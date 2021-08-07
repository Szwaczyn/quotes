package com.szwaczyk.quotes.service.interfaces;

import java.util.Set;

import com.szwaczyk.quotes.model.Author;
import com.szwaczyk.quotes.model.Quote;

public interface AuthorService {

	public boolean save(Author author);
	
	public Set<Author> findAll();
	
	public Author findById(String id);

	public Author findByNameAndSurname(String name, String surname);
}
