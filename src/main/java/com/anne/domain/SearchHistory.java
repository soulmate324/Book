package com.anne.domain;

import java.util.Date;

public class SearchHistory {
	private int accountId;
	private String query;
	private int searchCount;
	private Date created;

	public SearchHistory(final int accountId, final String query, final int searchCount) {
		this.accountId = accountId;
		this.query = query;
		this.searchCount = searchCount;
	}

	public int getAccountId() {
		return accountId;
	}

	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}

	public String getQuery() {
		return query;
	}

	public void setQuery(String query) {
		this.query = query;
	}

	public int getSearchCount() {
		return searchCount;
	}

	public void setSearchCount(int searchCount) {
		this.searchCount = searchCount;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

}