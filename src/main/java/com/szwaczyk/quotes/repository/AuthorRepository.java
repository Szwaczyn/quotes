package com.szwaczyk.quotes.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import com.szwaczyk.quotes.model.Author;
import com.szwaczyk.quotes.model.Quote;

public interface AuthorRepository extends JpaRepository<Author, Long> {

	public Author findById(long id);
	
	public Author findByNameAndSurname(String name, String surname);
}
