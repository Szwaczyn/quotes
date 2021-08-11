package com.szwaczyk.quotes.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.szwaczyk.quotes.model.Author;

public interface AuthorRepository extends JpaRepository<Author, Long> {

	Author findById(long id);
	
	Author findByNameAndSurname(String name, String surname);
}
