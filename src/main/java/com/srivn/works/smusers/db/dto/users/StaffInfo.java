package com.srivn.works.smusers.db.dto.users;

import com.srivn.works.smusers.db.dto.personal.AddressInfo;
import com.srivn.works.smusers.db.dto.personal.ContactInfo;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
public class StaffInfo extends UserInfo {

	private ContactInfo contactInfo;
	private AddressInfo addressInfo;
	private String dept;
	private String title;
	
	
	public StaffInfo(String firstName, String lastName, String gender, String userDOB, String userType,
			String userEmail, String dept, String title) {
		super(firstName, lastName, gender, userDOB, userType, userEmail);
		this.dept = dept;
		this.title = title;
	}

}
