package com.szwaczyk.quotes.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.szwaczyk.quotes.model.Quote;

public interface QuoteRepository extends JpaRepository<Quote, Long> {
	
	Quote findById(long id);

}
