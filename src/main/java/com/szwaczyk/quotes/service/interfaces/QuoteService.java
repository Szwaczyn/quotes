package com.szwaczyk.quotes.service.interfaces;

import java.util.Set;

import com.szwaczyk.quotes.model.Quote;

public interface QuoteService {

	boolean save(Quote quote);
	
	boolean delete(String id);
	
	Set<Quote> findAll();
	
	Quote findById(String id);
	
}
