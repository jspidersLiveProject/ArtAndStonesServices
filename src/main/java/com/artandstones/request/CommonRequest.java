package com.artandstones.request;

public class CommonRequest {

	private String trasactionId;
	
	private String userName;
	
	private String passWord;

	public String getTrasactionId() {
		return trasactionId;
	}

	public void setTrasactionId(String trasactionId) {
		this.trasactionId = trasactionId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	
	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	@Override
	public String toString() {
		return "CommonRequest [trasactionId=" + trasactionId + ", userName=" + userName + ", passWord=" + passWord
				+ "]";
	}

}
