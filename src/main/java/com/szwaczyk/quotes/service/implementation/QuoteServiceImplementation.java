package com.szwaczyk.quotes.service.implementation;

import java.util.Set;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.szwaczyk.quotes.model.Author;
import com.szwaczyk.quotes.model.Quote;
import com.szwaczyk.quotes.repository.QuoteRepository;
import com.szwaczyk.quotes.service.interfaces.QuoteService;

@Service
public class QuoteServiceImplementation implements QuoteService{

	@Autowired
	private QuoteRepository quoteRepository;
	
	Logger logger = LoggerFactory.getLogger(QuoteServiceImplementation.class);
	
	@Override
	public boolean save(Quote quote) {
		try {
			quoteRepository.save(quote);
			return true;
		}catch (Exception e) {
			logger.error("There is no possibility to persist the quote! " + e);
			return false;
		}
	}

	@Override
	public Set<Quote> findAll() {
		return quoteRepository.findAll().stream().collect(Collectors.toSet());
	}

	@Override
	public Quote findById(String id) {
		try {
			return quoteRepository.findById(Long.parseLong(id));
		}catch(NumberFormatException e) {
			logger.error("There is no possibility to obtain quote by id! id: " + id + ", " + e );
			return null;
		}
	}

	@Override
	public boolean delete(String id) {
		try {
			quoteRepository.deleteById(Long.parseLong(id));
			logger.error("Quote with id:" + id + " has beed deleted");
			return true;
		}catch(NumberFormatException e) {
			logger.error("There is no possibility to delete quote by id! id: " + id + ", " + e );
			return false;
		}catch(EmptyResultDataAccessException e) {
			return false;
		}
	}
	
	
}
