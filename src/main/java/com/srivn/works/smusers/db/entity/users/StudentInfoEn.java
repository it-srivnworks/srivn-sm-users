package com.srivn.works.smusers.db.entity.users;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;

import com.srivn.works.smusers.db.entity.personal.AddressInfoEn;
import com.srivn.works.smusers.db.entity.personal.ContactInfoEn;
import com.srivn.works.smusers.db.entity.personal.HealthInfoEn;
import com.srivn.works.smusers.db.entity.util.ClsnValEn;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "STUDENTS")
public class StudentInfoEn extends UserInfoEn {

	@ManyToOne
	@JoinColumn(name = "contactInfo")
	private ContactInfoEn contactInfo;

	@ManyToOne
	@JoinColumn(name = "emergencyContact")
	private ContactInfoEn emergencyContact;

	@ManyToOne
	@JoinColumn(name = "addressInfo")
	private AddressInfoEn addressInfo;

	@ManyToOne
	@JoinColumn(name = "healthInfo")
	private HealthInfoEn healthInfo;

	@ManyToOne
	@JoinColumn(name = "pguardian")
	private GuardianInfoEn pguardian;

	@ManyToOne
	@JoinColumn(name = "sguardian")
	private GuardianInfoEn sguardian;

	public StudentInfoEn(String firstName, String lastName, ClsnValEn gender, Timestamp userDOB, ClsnValEn userType,
			String userEmail, ContactInfoEn contactInfo, ContactInfoEn emergencyContact, AddressInfoEn addressInfo,
			HealthInfoEn healthInfo, GuardianInfoEn pguardian, GuardianInfoEn sguardian) {
		super(firstName, lastName, gender, userDOB, userType, userEmail);
		this.contactInfo = contactInfo;
		this.emergencyContact = emergencyContact;
		this.addressInfo = addressInfo;
		this.healthInfo = healthInfo;
		this.pguardian = pguardian;
		this.sguardian = sguardian;
	}

}
