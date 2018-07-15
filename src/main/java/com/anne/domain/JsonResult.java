package com.anne.domain;

import com.anne.code.ErrorCode;
import com.anne.code.ResultCode;

public class JsonResult {
    private int code;
    private String message;
    private String debug;
    private Object data;
    
    public JsonResult() {
    }
    
    public JsonResult(final int code, final String message, final String debug, final Object data) {
    	this.code = code;
    	this.message = message;
    	this.debug = debug;
    	this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDebug() {
        return debug;
    }

    public void setDebug(String debug) {
        this.debug = debug;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
    
    public static JsonResult success() {
        return success("");
    }
    
    public static JsonResult success(Object data) {
        JsonResult result = new JsonResult(); 
        result.code = ResultCode.SUCCESS.getCode();
        result.message = ResultCode.SUCCESS.getMessage();
        result.data    = data;
        return result;
    }
    
    public static JsonResult fail() {
        return fail(ResultCode.FAIL.getCode(), ResultCode.FAIL.getMessage());
    }
    
    public static JsonResult fail(ErrorCode errorCode) {
		return fail(errorCode.getErrorCode(), errorCode.getErrorMessage());
	}
    
    public static JsonResult fail(int code) {
        return fail(code, ResultCode.FAIL.getMessage());
    }

    public static JsonResult fail(int code, String message) {
        JsonResult result = new JsonResult(); 
        result.code    = code;
        result.message = message;
        return result;
    }
    
    public static JsonResult fail(int code, String message, String debug) {
        JsonResult result = new JsonResult(); 
        result.code    = code;
        result.message = message;
        result.debug   = debug;
        return result;
    }
}