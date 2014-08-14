package com.dec.model;

import java.sql.Timestamp;

public class Customer {

	private Integer customerId;
	private String customerName;
	private String password;
	private String idNumber;
	private String email;
	private Timestamp lastupdate;
	
	public Integer getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getIdNumber() {
		return idNumber;
	}
	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Timestamp getLastupdate() {
		return lastupdate;
	}
	public void setLastupdate(Timestamp lastupdate) {
		this.lastupdate = lastupdate;
	}
	
	@Override
	public String toString()
	{
		return "Customer [customerId="   + customerId
				+      ", customerName=" + customerName
				+	   ", idNumber="     + idNumber
				+      ", email="        + email
				+      ", lastupdate="   + lastupdate
				+"]";
	}
	
}
