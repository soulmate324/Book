package com.anne.config.interceptor;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.anne.domain.Member;

@Component	
public class RequestInterceptor extends HandlerInterceptorAdapter{

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		String url = request.getRequestURI();
		if (url.indexOf("/api/book/login") > -1 || url.indexOf("/api/book/signup") > -1) {
			return true;
		}
		
		HttpSession session = request.getSession(false);
		if (session == null) {
			response.sendRedirect("/book/view");
			return false;
		}

		Member member = (Member) session.getAttribute("member");
		if (member == null) {
			response.sendRedirect("/book/view");
			return false;
		}
		return true;
	}

	@Override
	public void postHandle(
			HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView)
					throws Exception {
	}

	public void printRequestParameterInfo(HttpServletRequest request) {
		Enumeration<String> e = request.getParameterNames();
		while (e.hasMoreElements()) {
			String param = e.nextElement();
			logger.debug("Request Param : {}={}", param, request.getParameter(param));
		}
	}
}
