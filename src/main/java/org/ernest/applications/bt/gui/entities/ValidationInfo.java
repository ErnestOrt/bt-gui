package org.ernest.applications.bt.gui.entities;

public class ValidationInfo {

	private String userId;
	private String userName;
	
	public ValidationInfo(String id, String name){
		userId = id;
		userName = name;
	}
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
}