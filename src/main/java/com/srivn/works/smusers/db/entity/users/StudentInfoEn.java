package com.srivn.works.smusers.db.entity.users;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;

import com.srivn.works.smusers.db.entity.personal.AddressInfo;
import com.srivn.works.smusers.db.entity.personal.ContactInfo;
import com.srivn.works.smusers.db.entity.personal.HealthInfo;
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
@Table(name = "STUDENTS")
public class StudentInfoEn extends UserInfoEn {

	@ManyToOne
	@JoinColumn(name = "contactInfo")
	private ContactInfo contactInfo;

	@ManyToOne
	@JoinColumn(name = "emergencyContact")
	private ContactInfo emergencyContact;

	@ManyToOne
	@JoinColumn(name = "addressInfo")
	private AddressInfo addressInfo;

	@ManyToOne
	@JoinColumn(name = "healthInfo")
	private HealthInfo healthInfo;

	@ManyToOne
	@JoinColumn(name = "pguardian")
	private GuardianInfoEn pguardian;

	@ManyToOne
	@JoinColumn(name = "sguardian")
	private GuardianInfoEn sguardian;

	public StudentInfoEn(String firstName, String lastName, int gender, Timestamp userDOB, ClsnValEn userType,
			UserLoginInfoEn userLoginInfo, ContactInfo contactInfo, ContactInfo emergencyContact,
			AddressInfo addressInfo, HealthInfo healthInfo, GuardianInfoEn pguardian, GuardianInfoEn sguardian) {
		super(firstName, lastName, gender, userDOB, userType, userLoginInfo);
		this.contactInfo = contactInfo;
		this.emergencyContact = emergencyContact;
		this.addressInfo = addressInfo;
		this.healthInfo = healthInfo;
		this.pguardian = pguardian;
		this.sguardian = sguardian;
	}



}
