package com.anne.config.handler;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.ui.Model;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.anne.code.ErrorCode;
import com.anne.domain.JsonResult;

/**
 * @description Common Exception Handler
 *
 */
@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice(annotations = RestController.class)
public class RestControllerExceptionHandler {

	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@ExceptionHandler(MissingServletRequestParameterException.class)
	@ResponseBody
	public JsonResult missingParameterException(HttpServletRequest req, MissingServletRequestParameterException e,  Model model) {
		logger.error("MissingServletRequestParameterException :" + e.getMessage());
		return JsonResult.fail(ErrorCode.MISSING_PARAMETER);
	}
	
	@ExceptionHandler(value = Exception.class)
	@ResponseBody
	public JsonResult defaultException(HttpServletRequest req, Exception e, Model model){
		logger.error("Exception :" + e);
		return JsonResult.fail(ErrorCode.SYSTEM_ERROR);
	}
	
	
	
	
}
