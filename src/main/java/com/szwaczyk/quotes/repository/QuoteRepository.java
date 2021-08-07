package com.szwaczyk.quotes.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import com.szwaczyk.quotes.model.Author;
import com.szwaczyk.quotes.model.Quote;

public interface QuoteRepository extends JpaRepository<Quote, Long> {
	
	public Quote findById(long id);

}
