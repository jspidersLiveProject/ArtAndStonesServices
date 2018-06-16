package com.artandstones.response;

public class LogoutResponse {

	CommonResponse commonResponse;
	
	String message;

	public CommonResponse getCommonResponse() {
		return commonResponse;
	}

	public void setCommonResponse(CommonResponse commonResponse) {
		this.commonResponse = commonResponse;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "LogoutResponse [commonResponse=" + commonResponse + ", message=" + message + "]";
	}
}
