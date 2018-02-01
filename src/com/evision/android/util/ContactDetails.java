package com.evision.android.util;

public class ContactDetails {
	
	String contactID;
	String contactNumber;
	String contactName;
	
	public ContactDetails() {
	}
	
	public ContactDetails(String id, String num, String name) {
		this.contactID = id;
		this.contactNumber = num;
		this.contactName = name;
	}
	
	public String getContactID() {
		return contactID;
	}
	public void setContactID(String contactID) {
		this.contactID = contactID;
	}
	public String getContactNumber() {
		return contactNumber;
	}
	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}
	public String getContactName() {
		return contactName;
	}
	public void setContactName(String contactName) {
		this.contactName = contactName;
	}
	
	

}
