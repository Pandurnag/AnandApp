package com.app.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name="registerd_user")
public class RegisterdUser {
		 
		@Id
		@GeneratedValue
		  private Long id;
		  private String firstName;
		  private String lastName;
		  private String password;
		  private String gender;
		  private String email;
		  private String mobile;
		  private String securityQuestion;
		  private String securityAnswer;
		  
		  
		public RegisterdUser() {
			
		}
		
		
		@Override
		public String toString() {
			return "RegisterdUser [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", password="
					+ password + ", gender=" + gender + ", email=" + email + ", mobile=" + mobile
					+ ", securityQuestion=" + securityQuestion + ", securityAnswer=" + securityAnswer + "]";
		}


		public RegisterdUser(String firstName, String lastName, String password, String gender, String email,
				String mobile, String securityQuestion, String securityAnswer) {
			super();
			this.firstName = firstName;
			this.lastName = lastName;
			this.password = password;
			this.gender = gender;
			this.email = email;
			this.mobile = mobile;
			this.securityQuestion = securityQuestion;
			this.securityAnswer = securityAnswer;
		}
		public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
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
		public String getPassword() {
			return password;
		}
		public void setPassword(String password) {
			this.password = password;
		}
		public String getGender() {
			return gender;
		}
		public void setGender(String gender) {
			this.gender = gender;
		}
		public String getEmail() {
			return email;
		}
		public void setEmail(String email) {
			this.email = email;
		}
		public String getMobile() {
			return mobile;
		}
		public void setMobile(String mobile) {
			this.mobile = mobile;
		}
		public String getSecurityQuestion() {
			return securityQuestion;
		}
		public void setSecurityQuestion(String securityQuestion) {
			this.securityQuestion = securityQuestion;
		}
		public String getSecurityAnswer() {
			return securityAnswer;
		}
		public void setSecurityAnswer(String securityAnswer) {
			this.securityAnswer = securityAnswer;
		}
		  
	
	
}
