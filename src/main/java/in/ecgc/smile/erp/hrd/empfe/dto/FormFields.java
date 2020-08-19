package in.ecgc.smile.erp.hrd.empfe.dto;

import org.springframework.format.annotation.DateTimeFormat;



import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


public class FormFields {

	public FormFields() {
		// TODO Auto-generated constructor stub
	}
	   @Size(min = 1 , max=5,message = "This is mandatory")
	  private String userName;
	 @NotBlank(message = "This is mandatory")
	    private String userEmail;
	/* @NotBlank(message = "this is mandatory") */
	    private String userPassword;
	/* @NotBlank(message = "this is mandatory") */
	    private String gender;
	/*
	 * @NotBlank(message = "this is mandatory")
	 */	    private String textArea;
	    private boolean check1;
	    private boolean check2;
	/* @NotBlank(message = "this is mandatory") */
	    private String category;
	    @DateTimeFormat(pattern= "yyyy-MM-dd")
		private Date date;
		public String getUserName() {
			return userName;
		}
		public void setUserName(String userName) {
			this.userName = userName;
		}
		public String getUserEmail() {
			return userEmail;
		}
		public void setUserEmail(String userEmail) {
			this.userEmail = userEmail;
		}
		public String getUserPassword() {
			return userPassword;
		}
		public void setUserPassword(String userPassword) {
			this.userPassword = userPassword;
		}
		public String getGender() {
			return gender;
		}
		public void setGender(String gender) {
			this.gender = gender;
		}
		public String getTextArea() {
			return textArea;
		}
		public void setTextArea(String textArea) {
			this.textArea = textArea;
		}
		public boolean isCheck1() {
			return check1;
		}
		public void setCheck1(boolean check1) {
			this.check1 = check1;
		}
		public boolean isCheck2() {
			return check2;
		}
		public void setCheck2(boolean check2) {
			this.check2 = check2;
		}
		public String getCategory() {
			return category;
		}
		public void setCategory(String category) {
			this.category = category;
		}
		public Date getDate() {
			return date;
		}
		public void setDate(Date date) {
			this.date = date;
		}
		@Override
		public String toString() {
			return "FormFields [userName=" + userName + ", userEmail=" + userEmail + ", userPassword=" + userPassword
					+ ", gender=" + gender + ", textArea=" + textArea + ", check1=" + check1 + ", check2=" + check2
					+ ", category=" + category + ", date=" + date + "]";
		}

	    

}
