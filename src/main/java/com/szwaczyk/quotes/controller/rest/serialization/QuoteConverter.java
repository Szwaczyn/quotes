package com.szwaczyk.quotes.controller.rest.serialization;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.szwaczyk.quotes.model.Quote;
import com.szwaczyk.quotes.model.nonpersistable.QuoteJson;

@Component
public class QuoteConverter {

	private AuthorConverter authorConverter;
	
	@Autowired
	public QuoteConverter(AuthorConverter authorConverter) {
		this.authorConverter = authorConverter;
	}
	
	public Quote convertQuoteJson(QuoteJson quoteJson) {
		if(quoteJson == null) return null;
		
		return new Quote.Builder()
				.content(quoteJson.getContent())
				.build();
	}
	
	public Quote convertQuoteJsonAndAuthor(QuoteJson quoteJson) {
		if(quoteJson == null) return null;
		
		return new Quote.Builder()
				.content(quoteJson.getContent())
				.author( authorConverter.convertAuthorJson(quoteJson.getAuthorJson()) )
				.build();
	}
	
	public QuoteJson convertQuoteAndAuthor(Quote quote) {
		return new QuoteJson.Builder()
				.content(quote.getContent())
				.id(quote.getId())
				.authorJson(authorConverter.convertAuthor(quote.getAuthor()))
				.build();
	}
	
	public Set<QuoteJson> convertQuoteAndAuthor(Collection<Quote> quoteSet){
		Set<QuoteJson> quoteJsonSet = new HashSet<QuoteJson>();
		
		for(Quote quote : quoteSet) {
			quoteJsonSet.add( convertQuoteAndAuthor(quote) );
		}
		
		return quoteJsonSet;
	}
}
