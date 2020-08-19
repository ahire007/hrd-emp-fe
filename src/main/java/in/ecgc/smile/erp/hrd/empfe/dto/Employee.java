package in.ecgc.smile.erp.hrd.empfe.dto;


import java.util.Date;

import javax.validation.constraints.NotBlank;

import org.springframework.format.annotation.DateTimeFormat;


/**
 *Employee Input DTO class
 *
 *@version 1.2 15-May-20
 *@Author Architecture Team C-DAC Mumbai
 *
 **/
public class Employee {
	
	/* Employee Id which is used for Employee reference */
	private int empId;
	
	/* Employee First Name*/
	@NotBlank(message = "{firstName.required}")
	private String firstName;
	
	/* Employee Middle Name*/
	@NotBlank(message = "{midName.required}")
	private String midName;
	
	/* Employee Last Name*/
	@NotBlank(message = "{lastName.required}")
	private String lastName;
	
	/* Employee DOB*/
//	@NotBlank(message = "{dob.required}")
	@DateTimeFormat(pattern="yyyy-MM-dd")  
	private Date dob;
	
	/* Employee Date of joining*/
//	@NotBlank(message = "{doj.required}")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date doj;
	
	/* Employee Type*/
	@NotBlank(message = "{empType.required}")
	private String empType;
	
	/* Employee designation*/
	@NotBlank(message = "{designation.required}")
	private	String designation;

	public int getEmpId() {
		return empId;
	}

	public void setEmpId(int empId) {
		this.empId = empId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMidName() {
		return midName;
	}

	public void setMidName(String midName) {
		this.midName = midName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public Date getDoj() {
		return doj;
	}

	public void setDoj(Date doj) {
		this.doj = doj;
	}

	public String getEmpType() {
		return empType;
	}

	public void setEmpType(String empType) {
		this.empType = empType;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	@Override
	public String toString() {
		return "Employee [empId=" + empId + ", firstName=" + firstName + ", midName=" + midName + ", lastName="
				+ lastName + ", dob=" + dob + ", doj=" + doj + ", empType=" + empType + ", designation=" + designation
				+ "]";
	}
	
	
	
}

