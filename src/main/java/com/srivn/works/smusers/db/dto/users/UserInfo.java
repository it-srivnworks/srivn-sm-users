package com.srivn.works.smusers.db.dto.users;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

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
