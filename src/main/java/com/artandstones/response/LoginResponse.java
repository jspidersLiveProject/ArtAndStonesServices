/**
 * 
 */
package com.artandstones.response;

/**
 * @author gargvis
 *
 */
public class LoginResponse {

	private boolean authenticated;
	
	private CommonResponse commonResponse;

	public boolean isAuthenticated() {
		return authenticated;
	}

	public void setAuthenticated(boolean authenticated) {
		this.authenticated = authenticated;
	}

	public CommonResponse getCommonResponse() {
		return commonResponse;
	}

	public void setCommonResponse(CommonResponse commonResponse) {
		this.commonResponse = commonResponse;
	}

	@Override
	public String toString() {
		return "LoginResponse [authenticated=" + authenticated + ", commonResponse=" + commonResponse + "]";
	}
}
