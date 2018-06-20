/**
 * 
 */
package com.artandstones.request;

/**
 * @author gargvis
 *
 */
public class GetMarblePriceRequest {
	
		private CommonRequest commonRequest;
		private String type;
		public CommonRequest getCommonRequest() {
			return commonRequest;
		}
		public void setCommonRequest(CommonRequest commonRequest) {
			this.commonRequest = commonRequest;
		}
		public String getType() {
			return type;
		}
		public void setType(String type) {
			this.type = type;
		}
		@Override
		public String toString() {
			return "GetMarblePriceRequest [commonRequest=" + commonRequest + ", type=" + type + "]";
		}
		

}
