package com.srivn.works.smusers.db.entity.users;

import jakarta.persistence.Table;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;

import com.srivn.works.smusers.db.entity.util.ClsnValEn;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

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

}
