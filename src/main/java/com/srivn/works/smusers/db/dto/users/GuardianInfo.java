package com.srivn.works.smusers.db.dto.users;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
public class GuardianInfo extends UserInfo {

	public GuardianInfo() {
		super();
	}
	
	public GuardianInfo(String firstName, String lastName, String gender, String userDOB, String userType,
			String userEmail) {
		super(firstName, lastName, gender, userDOB, userType, userEmail);
	}

}
