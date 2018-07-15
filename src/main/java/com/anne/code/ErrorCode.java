package com.anne.code;

/**
 * Error Code, Error Message(Message Resource Bundle ID) 정의
 */
public enum ErrorCode {
	
	//-1 ~ 99 system 오류 관련
	SYSTEM_ERROR(-1, "system.error"), //서버 오류
	MISSING_PARAMETER(-2, "missing.parameter"), //필수 파라미터 누락
	DB_INSERT_ERROR(-3, "insert.error"), //데이터 인서트 에러
	DB_UPDATE_ERROR(-4, "update.error"), //데이터 업데이트 에러
	DB_DELETE_ERROR(-5, "delete.error"), //데이터 삭제 에러
	NEED_LOGIN(-6, "login.error");
		
	private int errorCode;
	private String errorMessage;
	
	ErrorCode(int errorCode, String errorMessage){
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
	}
	
	public int getErrorCode(){
		return errorCode;
	}
	
	public String getErrorMessage(){
		return errorMessage;
	}

}
