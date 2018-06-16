/**
 * 
 */
package com.artandstones.request;

/**
 * @author gargvis
 *
 */
public class LogoutRequest {
	
	String userName;
	
	String transactionId;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	@Override
	public String toString() {
		return "LogoutRequest [userName=" + userName + ", transactionId=" + transactionId + "]";
	}

}
