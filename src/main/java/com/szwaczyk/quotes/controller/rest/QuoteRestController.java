package com.szwaczyk.quotes.controller.rest;

import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.szwaczyk.quotes.model.Author;
import com.szwaczyk.quotes.model.Quote;
import com.szwaczyk.quotes.service.interfaces.AuthorService;
import com.szwaczyk.quotes.service.interfaces.QuoteService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(value = "/quote/")
public class QuoteRestController {
	
	@Autowired
	private QuoteService quoteService;
	
	@Autowired
	private AuthorService authorService;

	Logger logger = LoggerFactory.getLogger(QuoteRestController.class);
	
	@RequestMapping(value = "/" ,method = RequestMethod.GET)
	@ApiOperation(value = "Get all persisted quotes")
	public ResponseEntity<Set<Quote>> getAllQuotes(){
		return ResponseEntity.ok(quoteService.findAll());
	}
	
	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	@ApiOperation(value = "Get quote by id")
	public ResponseEntity<Quote> getQuoteById(@ApiParam(value = "Id of quote") @PathVariable("id") String id){
		Quote quote = quoteService.findById(id);
		if(quote != null) {
			return ResponseEntity.ok(quote);
		}
		else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@RequestMapping(value = "{id}", method = RequestMethod.PUT)
	@ApiOperation(value = "Updete quote",
			notes = "If quote has not been found it will be created with random id."
					+ " If the author exist, provide name and surname")
	public ResponseEntity<Quote> putQuote(
			@ApiParam(value = "Id of quote")
			@PathVariable("id") String id,
			@ApiParam(value = "quote")
			@RequestParam("quote") String quoteContent,
			@ApiParam(value = "Author name")
			@RequestParam(name = "authorName", required = false) String authorName,
			@ApiParam(value = "Author surname")
			@RequestParam(name = "authorSurname", required = false) String authorSurname){
		
		Quote quote = quoteService.findById(id);
		
		if(quote != null) {
			Author author = authorService.findByNameAndSurname(authorName, authorSurname);
			
			quote.setAuthor(author);
			quote.setContent(quoteContent);
			quoteService.save(quote);
			logger.info("Quote updated, quote id:" + quote.getId());
			
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}
		else {
			Author author = authorService.findByNameAndSurname(authorName, authorSurname);
			
			quote = new Quote.Builder()
						.author(author)
						.content(quoteContent)
						.build();
			
			quoteService.save(quote);
			logger.info("Quote created with id " + quote.getId());
			
			return ResponseEntity.created(null).body(quote);
		}
	}
	
	@RequestMapping(value = "/", method = RequestMethod.POST)
	@ApiOperation(value = "Create quote",
			notes = "If the author exist, provide name and surname")
	public ResponseEntity<Quote>postQuote(
			@ApiParam(value = "quote")
			@RequestParam("quote") String quoteContent, 
			@ApiParam(value = "Author", allowEmptyValue = true)
			@RequestParam(name = "authorName", required = false) String authorName,
			@ApiParam(value = "Author surname", allowEmptyValue = false)
			@RequestParam(name = "authorSurname", required = false) String authorSurname){
		
		Author author = authorService.findByNameAndSurname(authorName, authorSurname);
		Quote quote = new Quote.Builder()
				.author(author)
				.content(quoteContent)
				.build();
	
		quoteService.save(quote);
		logger.info("Quote created with id " + quote.getId());
		
		return ResponseEntity.created(null).body(quote);
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
