package com.srivn.works.smusers.db.dao.users;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
@Builder
@AllArgsConstructor
public class GuardianInfo {

	private String firstName;
	private String lastName;
	private int userGender;
	private String userDOB;
	private String userType;
	private String userEmail;
	
}
