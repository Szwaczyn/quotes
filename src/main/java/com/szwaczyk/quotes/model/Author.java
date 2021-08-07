package com.szwaczyk.quotes.model;

import java.util.Collection;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Author {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	private String name;
	
	private String surname;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public static class Builder{
		private Author author;
		
		public Builder() {
			this.author = new Author();
		}
		
		public Author build() {
			return this.author;
		}
		
		public Builder name(String name) {
			this.author.name = name;
			return this;
		}
		
		public Builder suranem(String surname) {
			this.author.surname = surname;
			return this;
		}
		
	}
	
}
