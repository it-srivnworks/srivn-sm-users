package com.srivn.works.smusers.db.entity.users;

import com.srivn.works.smusers.util.AppC;
import com.srivn.works.smusers.util.AppUtil;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.sql.Timestamp;
import java.time.Instant;

@ToString
@Getter
@Setter
@Entity
@Table(name = "USERSLOGIN")
public class UserLoginInfoEn implements Serializable{

	@Id
	@Column(name = "userEmail")
	private String userEmail;
	
	@Column(name = "userPassword")
	private String userPassword;

	@Column(name = "lastLogin")
	private Timestamp lastLogin;

	@Column(name = "currentstatus")
	private int currentStatus;

	public UserLoginInfoEn() {
		super();
	}
	
	public UserLoginInfoEn(String userEmail, String userPassword, Timestamp lastLogin, int currentStatus) {
		super();
		this.userEmail = userEmail;
		this.userPassword = userPassword;
		this.lastLogin = lastLogin;
		this.currentStatus = currentStatus;
	}

	public static UserLoginInfoEn createNew(String userEmail){
		UserLoginInfoEn ulEn = new UserLoginInfoEn(userEmail, AppUtil.generatePwd(), Timestamp.from(Instant.now()), AppC.Status.NEW.getCode());
		return ulEn;
	}
}
