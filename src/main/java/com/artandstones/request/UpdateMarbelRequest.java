/**
 * 
 */
package com.artandstones.request;

/**
 * @author gargvis
 *
 */
public class UpdateMarbelRequest {
	
	private CommonRequest commonRequest;
	
	private String marbelName;
	
	private String price;

	public CommonRequest getCommonRequest() {
		return commonRequest;
	}

	public void setCommonRequest(CommonRequest commonRequest) {
		this.commonRequest = commonRequest;
	}

	public String getMarbelName() {
		return marbelName;
	}

	public void setMarbelName(String marbelName) {
		this.marbelName = marbelName;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "UpdateMarbelRequest [commonRequest=" + commonRequest + ", marbelName=" + marbelName + ", price=" + price
				+ "]";
	}
	
	

}
