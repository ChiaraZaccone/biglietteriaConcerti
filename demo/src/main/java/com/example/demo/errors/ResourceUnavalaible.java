package com.example.demo.errors;

public class ResourceUnavalaible extends RuntimeException{

/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
private String message;
	
	public ResourceUnavalaible(String message) {
		super(message);
		this.message = message;
	}
	
	public String getMessage() {
		return message;
	}


	public void setMessage(String message) {
		this.message = message;
	}
}
