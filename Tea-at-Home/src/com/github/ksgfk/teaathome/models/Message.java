package com.github.ksgfk.teaathome.models;

public class Message {
	private boolean success;
	private String message;
	public Message(boolean b, String str) {
		success=b;
		message=str;
	}
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
}
