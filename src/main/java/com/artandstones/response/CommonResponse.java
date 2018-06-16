package com.artandstones.response;

import com.artandstones.util.ResponseStatus;

public class CommonResponse {

	private String responseCode;

    private ResponseStatus responseStatus;
	
	public String getResponseCode() {
		return responseCode;
	}

	public void setResponseCode(String responseCode) {
		this.responseCode = responseCode;
	}

	public ResponseStatus getResponseStatus() {
		return responseStatus;
	}

	public void setResponseStatus(ResponseStatus responseStatus) {
		this.responseStatus = responseStatus;
	}

	public CommonResponse(String responseCode, ResponseStatus responseStatus)
    {
	this.responseCode = responseCode;
	this.responseStatus = responseStatus;
    }

	@Override
	public String toString() {
		return "LoginResponse [responseCode=" + responseCode + ", responseStatus=" + responseStatus + "]";
	}
}
