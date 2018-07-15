package com.anne.domain;

import java.util.List;

public class SearchResult {
	private Document[] documents;
	private Meta meta;
	private List<SearchHistory> history;

	public Document[] getDocuments() {
		return documents;
	}

	public void setDocuments(Document[] documents) {
		this.documents = documents;
	}

	public Meta getMeta() {
		return meta;
	}

	public void setMeta(Meta meta) {
		this.meta = meta;
	}

	public List<SearchHistory> getHistory() {
		return history;
	}

	public void setHistory(List<SearchHistory> history) {
		this.history = history;
	}

}