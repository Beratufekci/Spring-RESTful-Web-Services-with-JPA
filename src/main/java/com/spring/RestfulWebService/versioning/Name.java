package com.spring.RestfulWebService.versioning;


public class Name {
	
	private String firstName;
	private String lastName;
	
	public Name() {
		
	}
	
	public Name(String firstName, String lastName) {
		
		this.firstName = firstName;
		this.lastName = lastName;
		
				
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	

}