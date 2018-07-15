package com.anne.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anne.dao.IBookDao;
import com.anne.domain.Member;
import com.anne.domain.SearchHistory;

@Service
public class BookService {

	@Autowired
	private IBookDao bookDao;

	public int doRegist(Member member) throws Exception {
		return bookDao.doRegist(member);
	}

	public void doSearch(SearchHistory history) throws Exception {
		bookDao.doSearch(history);
	}

	public Member getLogin(Member member) throws Exception {
		return bookDao.getLogin(member);
	}

	public List<SearchHistory> getHistory(int accountId) throws Exception {
		return bookDao.getHistory(accountId);
	}
}
