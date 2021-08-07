package com.szwaczyk.quotes.service.interfaces;

import java.util.Set;

import com.szwaczyk.quotes.model.Author;
import com.szwaczyk.quotes.model.Quote;

public interface QuoteService {

	public boolean save(Quote quote);
	
	public boolean delete(String id);
	
	public Set<Quote> findAll();
	
	public Quote findById(String id);
	
}
