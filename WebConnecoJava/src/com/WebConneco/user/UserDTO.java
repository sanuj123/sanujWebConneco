package com.WebConneco.user;

import java.io.InputStream;

public class UserDTO {
	
	private int studentId;
	private String userName;
	private String emailId;
	private String password;
	private String emailHash;
	private String status;
	private InputStream inputStream;
			
	public UserDTO() {
	}

	@Override
	public String toString() {
		return "UserDTO [studentId=" + studentId + ", userName=" + userName + ", emailId=" + emailId + ", password="
				+ password + ", emailHash=" + emailHash + ", status=" + status + ", inputStream=" + inputStream + "]";
	}

	public UserDTO(int studentId, String userName, String emailId, String password, String emailHash, String status,
			InputStream inputStream) {
		this.studentId = studentId;
		this.userName = userName;
		this.emailId = emailId;
		this.password = password;
		this.emailHash = emailHash;
		this.status = status;
		this.inputStream = inputStream;
	}
			
	public UserDTO(int studentId, String userName) {
		this.studentId = studentId;
		this.userName = userName;
	}

	public UserDTO(String userName, String emailId, String password, String emailHash, String status,
			InputStream inputStream) {
		this.userName = userName;
		this.emailId = emailId;
		this.password = password;
		this.emailHash = emailHash;
		this.status = status;
		this.inputStream = inputStream;
	}
		
	public UserDTO(String userName, String emailId, String password, String emailHash, String status) {
		this.userName = userName;
		this.emailId = emailId;
		this.password = password;
		this.emailHash = emailHash;
		this.status = status;
	}

	public UserDTO(String emailId, String password) {
		this.emailId = emailId;
		this.password = password;
	}

	public int getStudentId() {
		return studentId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmailHash() {
		return emailHash;
	}

	public void setEmailHash(String emailHash) {
		this.emailHash = emailHash;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public InputStream getInputStream() {
		return inputStream;
	}

	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}
    
	
	
}
