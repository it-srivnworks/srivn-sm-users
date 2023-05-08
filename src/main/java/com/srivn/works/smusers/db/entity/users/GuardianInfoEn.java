package com.srivn.works.smusers.db.entity.users;

import com.srivn.works.smusers.db.entity.util.ClsnValEn;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.sql.Timestamp;

@ToString
@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "GUARDIANS")
public class GuardianInfoEn extends UserInfoEn {
	public GuardianInfoEn(String firstName, String lastName, ClsnValEn gender, Timestamp userDOB, ClsnValEn userType,
			String userEmail) {
		super(firstName, lastName, gender, userDOB, userType, userEmail);
		// TODO Auto-generated constructor stub
	}

}
