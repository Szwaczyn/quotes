package com.szwaczyk.quotes.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Quote {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	private String content;
	
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "author")
	private Author author;
	
	public Quote() {}
	
	public Quote(String content, Author author) {
		this.content = content;
		this.author = author;
	}
	
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
		
		private String content;
		private Author author;
		
		public Quote build() {
			return new Quote(content, author);
		}
		
		public Builder content(String content) {
			this.content = content;
			return this;
		}
		
		public Builder author(Author author) {
			this.author = author;
			return this;
		}
	}
}
