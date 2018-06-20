/**
 * 
 */
package com.artandstones.response;

/**
 * @author gargvis
 *
 */
public class GetMarblePriceResponse {
	
	private CommonResponse commonResponse;
	private String price;
	private String message;
	public CommonResponse getCommonResponse() {
		return commonResponse;
	}
	public void setCommonResponse(CommonResponse commonResponse) {
		this.commonResponse = commonResponse;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	@Override
	public String toString() {
		return "GetMarblePriceResponse [commonResponse=" + commonResponse + ", price=" + price + ", message=" + message
				+ "]";
	}
	

}
