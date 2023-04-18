package com.srivn.works.smusers.db.entity.users;

import java.io.Serializable;
import java.sql.Date;

import com.srivn.works.smusers.db.entity.personal.AddressInfo;
import com.srivn.works.smusers.db.entity.personal.ContactInfo;
import com.srivn.works.smusers.db.entity.util.ClsnValEn;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
@Entity
@Table(name = "TEACHERS")
public class TeacherInfoEn extends UserInfoEn {
	@ManyToOne
	@JoinColumn(name = "contactInfo")
	private ContactInfo contactInfo;

	@ManyToOne
	@JoinColumn(name = "addressInfo")
	private AddressInfo addressInfo;
	
	@ManyToOne
	@JoinColumn(name = "dept")
	private ClsnValEn dept;
	
	@ManyToOne
	@JoinColumn(name = "title")
	private ClsnValEn title;

	public TeacherInfoEn(String firstName, String lastName, int userGender, Date userDOB, ClsnValEn userType,
			UserLoginInfoEn userLoginInfo, ContactInfo contactInfo, AddressInfo addressInfo,
			ClsnValEn dept, ClsnValEn title) {
		super(firstName, lastName, userGender, userDOB, userType, userLoginInfo);
		this.contactInfo = contactInfo;
		this.addressInfo = addressInfo;
		this.dept = dept;
		this.title = title;
	}


	
}
