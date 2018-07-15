package com.anne.dao;

import java.util.List;

import com.anne.annotation.Book;
import com.anne.domain.Member;
import com.anne.domain.SearchHistory;

@Book
public interface IBookDao {
	public int doRegist(Member member);
	
	public void doSearch(SearchHistory history);
	
	public Member getLogin(Member member);
	
	public List<SearchHistory> getHistory(int accountId);
}
