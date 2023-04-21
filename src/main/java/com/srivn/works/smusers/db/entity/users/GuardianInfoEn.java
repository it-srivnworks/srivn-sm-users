package com.srivn.works.smusers.db.entity.users;

import java.sql.Date;
import java.sql.Timestamp;

import com.srivn.works.smusers.db.entity.util.ClsnValEn;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
@Entity
@Table(name = "GUARDIANS")
public class GuardianInfoEn extends UserInfoEn {

	public GuardianInfoEn() {
		super();
	}
	
		
	public GuardianInfoEn(String firstName, String lastName, int gender, Timestamp userDOB, ClsnValEn userType,
			UserLoginInfoEn userLoginInfo) {
		super(firstName, lastName, gender, userDOB, userType, userLoginInfo);
		// TODO Auto-generated constructor stub
	}

}
