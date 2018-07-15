package com.anne.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.anne.domain.Member;

@Controller
@RequestMapping("/book")
public class BookController {
	@RequestMapping("/view")
	public void FrontView(HttpServletRequest request, Model model) throws Exception {
		int status = 0;

		HttpSession session = request.getSession(false);
		if (session != null) {
			Member member = (Member) session.getAttribute("member");
			if (member != null) {
				status = 1;
			}
		}
		model.addAttribute("status", status);
	}
}
