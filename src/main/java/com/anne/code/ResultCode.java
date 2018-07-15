package com.anne.code;

public enum ResultCode {

	FAIL(-1,"fail"),
	SUCCESS(0,"success");
	
	private int code;
	private String message;
	
	ResultCode(int code, String message){
		this.code = code;
		this.message = message;
	}
	
	public int getCode(){
		return this.code;
	}
	
	public String getMessage(){
		return this.message;
	}
}
