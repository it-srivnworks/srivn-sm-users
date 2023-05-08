package com.srivn.works.smusers.db.entity.users;

import com.srivn.works.smusers.db.entity.util.ClsnValEn;
import jakarta.persistence.*;
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
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class UserInfoEn {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "userID")
	private int userID;

	@Column(name = "firstName")
	private String firstName;

	@Column(name = "lastName")
	private String lastName;

	@ManyToOne
	@JoinColumn(name = "gender")
	private ClsnValEn gender;

	@Column(name = "userDOB")
	private Timestamp userDOB;

	@ManyToOne
	@JoinColumn(name = "userType")
	private ClsnValEn userType;

	@Column(name = "userEmail")
	private String userEmail;

	public UserInfoEn(String firstName, String lastName, ClsnValEn gender, Timestamp userDOB, ClsnValEn userType,
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
