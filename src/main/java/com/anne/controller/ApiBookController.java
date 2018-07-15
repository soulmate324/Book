package com.anne.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.anne.code.ErrorCode;
import com.anne.config.util.HttpUtil;
import com.anne.domain.JsonResult;
import com.anne.domain.Member;
import com.anne.domain.SearchHistory;
import com.anne.domain.SearchResult;
import com.anne.service.BookService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@RestController
@RequestMapping("/api/book")
public class ApiBookController {
	private Logger logger = LoggerFactory.getLogger(getClass());

	private String sSearchApiUrl = "https://dapi.kakao.com"; // search book API URL

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private BookService bookService;

	@RequestMapping(value = "/signup", method = RequestMethod.POST)
	public JsonResult regist(HttpServletRequest request, @ModelAttribute Member member) {
		member.setPasswd(passwordEncoder.encode(member.getPasswd()));
		try {
			if (bookService.doRegist(member) > 0)
				return JsonResult.success();
		} catch (Exception e) {
			logger.debug(e.getMessage());
		}

		return JsonResult.fail();

	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public JsonResult login(HttpServletRequest request, @ModelAttribute Member member) {
		try {
			Member login = bookService.getLogin(member);
			if (login != null && passwordEncoder.matches(member.getPasswd(), login.getPasswd())) {
				request.getSession(true).setAttribute("member", login);
				return JsonResult.success();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return JsonResult.fail();
	}

	@RequestMapping(value = "/search")
	public JsonResult reward(HttpServletRequest request, @RequestParam(value = "query") String query,
			@RequestParam(value = "sort", defaultValue = "") String sort,
			@RequestParam(value = "page", defaultValue = "1") int page,
			@RequestParam(value = "size", defaultValue = "10") int size,
			@RequestParam(value = "target", defaultValue = "all") String target,
			@RequestParam(value = "category", defaultValue = "-1") int category) throws IOException, Exception {
		HttpSession session = request.getSession(false);
		if (session == null) {
			return JsonResult.fail(ErrorCode.NEED_LOGIN);
		}
		Member member = (Member) session.getAttribute("member");
		if (member == null) {
			return JsonResult.fail(ErrorCode.NEED_LOGIN);
		}
		
		Map<String, Object> args = new HashMap<String, Object>();
		args.put("query", query);
		args.put("sort", sort);
		args.put("page", page);
		args.put("size", size);
		args.put("target", target);
		if (category > -1)
			args.put("category", category);

		String urlString = sSearchApiUrl + "/v2/search/book";
		String response = HttpUtil.getInstance().sendPost(urlString, args);

		Gson gson = new GsonBuilder().disableHtmlEscaping().create();
		SearchResult search = gson.fromJson(response, SearchResult.class);

		if (search != null && search.getMeta() != null) {
			search = setPageInfo(page, size, search);
		}

		try {
			bookService.doSearch(new SearchHistory(member.getAccountId(), query, search.getMeta().getTotal_count()));
			search.setHistory(bookService.getHistory(member.getAccountId()));
		} catch (Exception e) {
			logger.debug(e.getMessage());
		}

		return JsonResult.success(search);
	}

	private SearchResult setPageInfo(final int page, final int size, final SearchResult search) {
		SearchResult ret = search;
		int last_page_size = search.getMeta().getTotal_count() % size == 0 ? size
				: search.getMeta().getTotal_count() % size;
		int last_index = last_page_size == size ? (search.getMeta().getTotal_count() / size) + 1
				: search.getMeta().getTotal_count() / size;
		int start_index = (page / size) > 0 ? ((page / size) * size) + 1 : 1;
		int end_index = start_index + size - 1 > last_index ? last_index : start_index + size - 1;
		int prev_index = start_index < size ? 0 : start_index - size;
		int next_index = start_index + size - 1 > last_index ? 0 : start_index + size;
		int[] page_index = new int[end_index - start_index + 1];
		int curr_index = 0;
		for (int i = 0; i < page_index.length; i++) {
			page_index[i] = start_index + i;
			if (page == start_index + i) {
				curr_index = i;
			}
		}
		ret.getMeta().setCurr_idx(curr_index);
		ret.getMeta().setPage_pre(prev_index);
		ret.getMeta().setPage_next(next_index);
		ret.getMeta().setPage_idx(page_index);
		return ret;
	}

}
