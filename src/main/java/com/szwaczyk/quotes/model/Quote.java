package com.szwaczyk.quotes.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.szwaczyk.quotes.controller.rest.QuoteRestController;

@Entity
public class Quote {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	private String content;
	
	@ManyToOne
	@JoinColumn(name = "author")
	private Author author;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Author getAuthor() {
		return author;
	}

	public void setAuthor(Author author) {
		this.author = author;
	}
	
	public static class Builder{
		
		private Quote quote;
		Logger logger = LoggerFactory.getLogger(Quote.Builder.class);
		
		public Builder() {
			this.quote = new Quote();
		}
		
		public Quote build() {
			return this.quote;
		}
		
		public Builder content(String content) {
			this.quote.content = content;
			
			return this;
		}
		
		public Builder author(Author author) {
			this.quote.author = author;
			
			return this;
		}
	}
}
