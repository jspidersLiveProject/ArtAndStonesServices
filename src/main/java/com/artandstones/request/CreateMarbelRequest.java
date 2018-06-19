/**
 * 
 */
package com.artandstones.request;

/**
 * @author gargvis
 *
 */
public class CreateMarbelRequest {

	private CommonRequest commonRequest;
	
	private String marbelName;
	
	private String marbelType;
	
	private String marbelPrice;
	
	private String marbelDesc;

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

	public String getMarbelType() {
		return marbelType;
	}

	public void setMarbelType(String marbelType) {
		this.marbelType = marbelType;
	}

	public String getMarbelPrice() {
		return marbelPrice;
	}

	public void setMarbelPrice(String marbelPrice) {
		this.marbelPrice = marbelPrice;
	}

	public String getMarbelDesc() {
		return marbelDesc;
	}

	public void setMarbelDesc(String marbelDesc) {
		this.marbelDesc = marbelDesc;
	}

	@Override
	public String toString() {
		return "CreateMarbelRequest [commonRequest=" + commonRequest + ", marbelName=" + marbelName + ", marbelType="
				+ marbelType + ", marbelPrice=" + marbelPrice + ", marbelDesc=" + marbelDesc + "]";
	}
	
	
}
