package com.szwaczyk.quotes.service.interfaces;

import java.util.Set;

import com.szwaczyk.quotes.model.Author;

public interface AuthorService {

	boolean save(Author author);
	
	Set<Author> findAll();
	
	Author findById(String id);

	Author findByNameAndSurname(String name, String surname);
	
	Author findByAuthor(Author author);
}
