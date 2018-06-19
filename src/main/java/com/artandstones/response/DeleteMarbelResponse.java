/**
 * 
 */
package com.artandstones.response;

/**
 * @author gargvis
 *
 */
public class DeleteMarbelResponse {
	
	private CommonResponse commonResponse;
	
	private String message;

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
		return "DeleteMarbelResponse [commonResponse=" + commonResponse + ", message=" + message + "]";
	}

}
