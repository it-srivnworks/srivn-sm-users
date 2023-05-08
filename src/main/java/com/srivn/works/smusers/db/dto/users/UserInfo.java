package com.srivn.works.smusers.db.dto.users;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@ToString
@Getter
@Setter
public class UserInfo implements Serializable {

	private String firstName;
	private String lastName;
	private String gender;
	private String userDOB;
	private String userType;
	private String userEmail;
	
	public UserInfo() {
		super();
	}
	
	public UserInfo(String firstName, String lastName, String gender, String userDOB, String userType,
			String userEmail) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.gender = gender;
		this.userDOB = userDOB;
		this.userType = userType;
		this.userEmail = userEmail;
	}
	
	
}
