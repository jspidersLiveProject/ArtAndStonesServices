package com.artandstones.request;

public class LoginRequest {

	private CommonRequest commonRequest;

	public CommonRequest getCommonRequest() {
		return commonRequest;
	}

	public void setCommonRequest(CommonRequest commonRequest) {
		this.commonRequest = commonRequest;
	}

	@Override
	public String toString() {
		return "LoginRequest [commonRequest=" + commonRequest + "]";
	}
}
