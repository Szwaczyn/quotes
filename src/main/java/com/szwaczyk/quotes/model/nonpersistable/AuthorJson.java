package com.szwaczyk.quotes.model.nonpersistable;

import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName(value = "author")
public class AuthorJson {

	private String name;
	private String surname;
	
	public AuthorJson() {}
	
	public AuthorJson(String name, String surname) {
		this.name = name;
		this.surname = surname;
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
		
		public AuthorJson build() {
			return new AuthorJson(name, surname);
		}
		
		public Builder name(String name) {
			this.name = name;
			return this;
		}
		
		public Builder surname(String surname) {
			this.surname = surname;
			return this;
		}
	}
}
