package com.artandstones.response;

public class GetMarbelDetailsResponse {
	
	private CommonResponse commonResponse;
	
	private String marbelName;
	
	private String type;
	
	private String description;
	
	private int price;

	public CommonResponse getCommonResponse() {
		return commonResponse;
	}

	public void setCommonResponse(CommonResponse commonResponse) {
		this.commonResponse = commonResponse;
	}

	public String getMarbelName() {
		return marbelName;
	}

	public void setMarbelName(String marbelName) {
		this.marbelName = marbelName;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "GetMarbelDetailsResponse [commonResponse=" + commonResponse + ", marbelName=" + marbelName + ", type="
				+ type + ", description=" + description + ", price=" + price + "]";
	}


}
