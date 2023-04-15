package com.srivn.works.smusers.db.entity.users;

import jakarta.persistence.Table;

import java.sql.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
@Entity
@Table(name = "USERSLOGIN")
public class UserLoginInfoEn {

	@Id
    @Column(name = "userID")
    private int id;
	
	@Column(name = "userEmail")
	private String userEmail;
	
	@Column(name = "userPassword")
	private String userPassword;

	@Column(name = "lastLogin")
	private Timestamp lastLogin;

	@Column(name = "currentstatus")
	private int currentStatus;

	@OneToOne
    @MapsId
    @JoinColumn(name = "userID")
    private UserInfoEn userInfoEn;

}
