package com.anne.config.handler;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.anne.code.ErrorCode;

@Order(Ordered.LOWEST_PRECEDENCE)
@ControllerAdvice
public class ControllerExceptionHandler {

	private Logger logger = LoggerFactory.getLogger(getClass());
	
	public static final String DEFAULT_ERROR_VIEW = "/error/default_error";
	
	@ExceptionHandler(value = Exception.class)
	public String defaultException(HttpServletRequest req, Exception e, Model model){
		logger.error("Exception :" + e.getMessage());
		model.addAttribute("message",ErrorCode.SYSTEM_ERROR.getErrorMessage());
		return DEFAULT_ERROR_VIEW;
	}
	
}
