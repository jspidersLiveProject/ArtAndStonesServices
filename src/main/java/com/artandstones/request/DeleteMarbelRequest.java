/**
 * 
 */
package com.artandstones.request;

/**
 * @author gargvis
 *
 */
public class DeleteMarbelRequest {
	
	private CommonRequest commonRequest;
	
	private String marbelName;

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

	@Override
	public String toString() {
		return "DeleteMarbelRequest [commonRequest=" + commonRequest + ", marbelName=" + marbelName + "]";
	}

}
