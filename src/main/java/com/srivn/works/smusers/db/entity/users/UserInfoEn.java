package com.srivn.works.smusers.db.entity.users;



import java.sql.Date;

import com.srivn.works.smusers.db.entity.personal.AddressInfo;
import com.srivn.works.smusers.db.entity.util.ClsnValEn;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrimaryKeyJoinColumn;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
@Entity
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

	@Column(name = "userGender")
	private int userGender;

	@Column(name = "userDOB")
	private Date userDOB;

	@ManyToOne
	@JoinColumn(name = "userType")
	private ClsnValEn userType;

	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "userEmail" , referencedColumnName = "userEmail")
    private UserLoginInfoEn userLoginInfo;

	public UserInfoEn(String firstName, String lastName, int userGender, Date userDOB, ClsnValEn userType, UserLoginInfoEn userLoginInfo) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.userGender = userGender;
		this.userDOB = userDOB;
		this.userType = userType;
		this.userLoginInfo = userLoginInfo;
	}

}
