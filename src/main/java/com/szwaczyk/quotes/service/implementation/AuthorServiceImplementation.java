package com.szwaczyk.quotes.service.implementation;

import java.util.Set;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.szwaczyk.quotes.model.Author;
import com.szwaczyk.quotes.repository.AuthorRepository;
import com.szwaczyk.quotes.service.interfaces.AuthorService;

@Service
public class AuthorServiceImplementation implements AuthorService {

	private AuthorRepository authorRepository;
	
	private Logger logger = LoggerFactory.getLogger(AuthorServiceImplementation.class);
	
	@Autowired
	public AuthorServiceImplementation(AuthorRepository authorRepository) {
		this.authorRepository = authorRepository;
	}
	
	@Override
	public boolean save(Author author) {
		try {
			authorRepository.save(author);
			return true;
		}catch (Exception e) {
			logger.error("There is no possibility to persist the author! " + e);
			return false;
		}
	}

	@Override
	public Set<Author> findAll() {
		return authorRepository.findAll().stream().collect(Collectors.toSet());
	}

	@Override
	public Author findById(String id) {
		try {
			return authorRepository.findById(Long.parseLong(id));
		}catch(NumberFormatException e) {
			logger.error("There is no possibility to obtain author by id! id: " + id + ", " + e );
			return null;
		}
	}

	@Override
	public Author findByNameAndSurname(String name, String surname) {
		Author author = authorRepository.findByNameAndSurname(name, surname);
		
		return author;
	}

	@Override
	public Author findByAuthor(Author author) {
		if(author == null) return null;
		
		return this.findByNameAndSurname(author.getName(), author.getSurname());
	}
	
	
	
}
