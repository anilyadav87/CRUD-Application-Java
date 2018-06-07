package com.niit.model;

import com.niit.validation.ValidateEmployeeData;

public class EmployeeModel {
	
	private int eId;
	private String firstName, lastName, address, contactNo, eMail, password;
	
	public EmployeeModel()
	{
	}

	public EmployeeModel(int eId, String firstName, String lastName, String address, String contactNo, String eMail,
			String password) {
		super();
		this.eId = eId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.contactNo = contactNo;
		this.eMail = eMail;
		this.password = password;
	}

	public int geteId() {
		return eId;
	}

	public void seteId(int eId) {
		this.eId = eId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getContactNo() {
		return contactNo;
	}

	public void setContactNo(String contactNo) {
		boolean status=ValidateEmployeeData.validateContact(contactNo);
		if(status)
			this.contactNo = contactNo;
		else
			System.out.println("Incorrect Contact");
	}

	public String geteMail() {
		return eMail;
	}

	public void seteMail(String eMail) {
		boolean status=ValidateEmployeeData.validateEmail(eMail);
		if(status)
			this.eMail = eMail;
		else
			System.out.println("Incorrect email please enter correct email");
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	

	
}
