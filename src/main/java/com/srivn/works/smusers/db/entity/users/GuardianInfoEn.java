package com.srivn.works.smusers.db.entity.users;

import java.sql.Date;

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

	public GuardianInfoEn(String firstName, String lastName,int userGender,Date userDOB,ClsnValEn userType) {
		super(firstName, lastName, userGender, userDOB, userType);
	}

}
