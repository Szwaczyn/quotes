package com.szwaczyk.quotes.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Author {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	private String name;
	
	private String surname;
	
	public Author() {}
	
	public Author(String name, String surname) {
		this.name = name;
		this.surname = surname;
	}
	
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
		private String name;
		private String surname;
		
		public Author build() {
			return new Author(this.name, this.surname);
		}
		
		public Builder name(String name) {
			this.name = name;
			return this;
		}
		
		public Builder suranem(String surname) {
			this.surname = surname;
			return this;
		}
		
	}
	
}
