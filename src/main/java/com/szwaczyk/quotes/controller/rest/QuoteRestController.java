package com.szwaczyk.quotes.controller.rest;

import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.szwaczyk.quotes.controller.rest.serialization.AuthorConverter;
import com.szwaczyk.quotes.controller.rest.serialization.QuoteConverter;
import com.szwaczyk.quotes.model.Author;
import com.szwaczyk.quotes.model.Quote;
import com.szwaczyk.quotes.model.nonpersistable.QuoteJson;
import com.szwaczyk.quotes.service.interfaces.AuthorService;
import com.szwaczyk.quotes.service.interfaces.QuoteService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping(value = "/quote/")
public class QuoteRestController {
	
	private QuoteService quoteService;
	private AuthorService authorService;
	private AuthorConverter authorConverter;
	private QuoteConverter quoteConverter;

	private Logger logger = LoggerFactory.getLogger(QuoteRestController.class);
	
	@Autowired
	public QuoteRestController(
			QuoteService quoteService, 
			AuthorService authorService,
			AuthorConverter authorConverter,
			QuoteConverter quoteConverter) {
		this.quoteService = quoteService;
		this.authorService = authorService;
		this.authorConverter = authorConverter;
		this.quoteConverter = quoteConverter;
	}
	
	@RequestMapping(value = "/" ,method = RequestMethod.GET)
	@ApiOperation(value = "Get all persisted quotes")
	public ResponseEntity<Set<QuoteJson>> getAllQuotes(){
		return ResponseEntity.ok( 
				quoteConverter.convertQuoteAndAuthor(quoteService.findAll()) 
				);
	}
	
	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	@ApiOperation(value = "Get quote by id")
	public ResponseEntity<QuoteJson> getQuoteById(@ApiParam(value = "Id of quote") @PathVariable("id") String id){
		Quote quote = quoteService.findById(id);
		if(quote != null) {
			return ResponseEntity.ok( quoteConverter.convertQuoteAndAuthor(quote) );
		}
		else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@RequestMapping(value = "{id}", method = RequestMethod.PUT)
	@ApiOperation(value = "Updete quote",
			notes = "If quote has not been found it will be created with random id."
					+ " Author is nullable")
	public ResponseEntity<QuoteJson> putQuote(
			@ApiParam(value = "Id of quote")
			@PathVariable("id") String id,
			@ApiParam(value = "quote, author is nullable")
			@RequestBody QuoteJson quoteJson){
		
		Quote quote = quoteService.findById(id);
		
		if(quote != null) {
			Author authorFromRequest = authorConverter.convertAuthorJson(quoteJson.getAuthorJson());
			Author author = authorService.findByAuthor(authorFromRequest);
			
			if(author == null) {
				author = authorFromRequest;
			}
			
			quote.setAuthor(author);
			quote.setContent(quoteJson.getContent());
			quoteService.save(quote);
			
			logger.info("Quote updated, quote id:" + quote.getId());
			
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}
		else {
			return postQuote(quoteJson);
		}
	}
	
	@RequestMapping(value = "/", method = RequestMethod.POST)
	@ApiOperation(value = "Create quote",
			notes = "Author is nullable")
	public ResponseEntity<QuoteJson> postQuote(
			@ApiParam(value = "quote, author is nullable")
			@RequestBody QuoteJson quoteJson){
		
		Quote quote = quoteConverter.convertQuoteJsonAndAuthor(quoteJson);
		Author author = authorService.findByAuthor(quote.getAuthor());
		
		if(author != null) {
			quote.setAuthor(author);
		}
		
		quoteService.save(quote);
		
		return ResponseEntity.created(null).body( quoteConverter.convertQuoteAndAuthor(quote) );
	}
	
	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
	@ApiOperation(value = "Delete quote by id")
	public ResponseEntity deleteQuote(@PathVariable("id") String id) {
		if (quoteService.delete(id)) {
			return ResponseEntity.ok().build();
		}
		else {
			return ResponseEntity.noContent().build();
		}
	}
}
