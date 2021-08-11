package com.szwaczyk.quotes.model.nonpersistable;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName(value = "quote")
public class QuoteJson {

	private String id;
	private String content;
	@JsonProperty("author")
	private AuthorJson authorJson;
	
	public QuoteJson() {}
	
	public QuoteJson(String id, String content, AuthorJson authorJson) {
		this.id = id;
		this.content = content;
		this.authorJson = authorJson;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public AuthorJson getAuthorJson() {
		return authorJson;
	}
	public void setAuthorJson(AuthorJson authorJson) {
		this.authorJson = authorJson;
	}
	
	public static class Builder {
		private String id;
		private String content;
		private AuthorJson authorJson;
		
		public QuoteJson build() {
			return new QuoteJson(id, content, authorJson);
		}
		
		public Builder id(long id) {
			this.id = Long.toString(id);
			return this;
		}
		
		public Builder content(String content) {
			this.content = content;
			return this;
		}
		
		public Builder authorJson(AuthorJson authorJson) {
			this.authorJson = authorJson;
			return this;
		}
		
	}
}
