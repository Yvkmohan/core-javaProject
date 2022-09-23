package com.training.model;

import java.time.LocalDate;

public class Employee {
	private String empFirstName;
	private String empLastName;
	private String empAddress;
	private String empEmail;
	private long empPhoneNumber;
	private LocalDate empDateOfBirth;
	private LocalDate empWeddingDate;

	public Employee() {
		super();
	}

	public Employee(String empFirstName, long empPhoneNumber) {
		super();
		this.empFirstName = empFirstName;
		this.empPhoneNumber = empPhoneNumber;
	}

	public Employee(String empFirstName, String empEmail) {
		super();
		this.empFirstName = empFirstName;
		this.empEmail = empEmail;
	}
	

	public Employee(String empFirstName, String empLastName, String empAddress, String empEmail, long empPhoneNumber,
			LocalDate empDateOfBirth, LocalDate empWeddingDate) {
		super();
		this.empFirstName = empFirstName;
		this.empLastName = empLastName;
		this.empAddress = empAddress;
		this.empEmail = empEmail;
		this.empPhoneNumber = empPhoneNumber;
		this.empDateOfBirth = empDateOfBirth;
		this.empWeddingDate = empWeddingDate;
	}

	public String getEmpFirstName() {
		return empFirstName;
	}

	public void setEmpFirstName(String empFirstName) {
		this.empFirstName = empFirstName;
	}

	public String getEmpLastName() {
		return empLastName;
	}

	public void setEmpLastName(String empLastName) {
		this.empLastName = empLastName;
	}

	public String getEmpAddress() {
		return empAddress;
	}

	public void setEmpAddress(String empAddress) {
		this.empAddress = empAddress;
	}

	public String getEmpEmail() {
		return empEmail;
	}

	public void setEmpEmail(String empEmail) {
		this.empEmail = empEmail;
	}

	public long getEmpPhoneNumber() {
		return empPhoneNumber;
	}

	public void setEmpPhoneNumber(long empPhoneNumber) {
		this.empPhoneNumber = empPhoneNumber;
	}

	public LocalDate getEmpDateOfBirth() {
		return empDateOfBirth;
	}

	public void setEmpDateOfBirth(LocalDate empDateOfBirth) {
		this.empDateOfBirth = empDateOfBirth;
	}

	public LocalDate getEmpWeddingDate() {
		return empWeddingDate;
	}

	public void setEmpWeddingDate(LocalDate empWeddingDate) {
		this.empWeddingDate = empWeddingDate;
	}

	@Override
	public String toString() {
		return "Employee [empFirstName=" + empFirstName + ", empLastName=" + empLastName + ", empAddress=" + empAddress
				+ ", empEmail=" + empEmail + ", empPhoneNumber=" + empPhoneNumber + ", empDateOfBirth=" + empDateOfBirth
				+ ", empWeddingDate=" + empWeddingDate + "]";
	}








}
