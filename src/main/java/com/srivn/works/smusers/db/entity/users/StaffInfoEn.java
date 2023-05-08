package com.srivn.works.smusers.db.entity.users;

import com.srivn.works.smusers.db.entity.personal.AddressInfoEn;
import com.srivn.works.smusers.db.entity.personal.ContactInfoEn;
import com.srivn.works.smusers.db.entity.util.ClsnValEn;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
@Table(name = "STAFF")
public class StaffInfoEn extends UserInfoEn {

	@ManyToOne
	@JoinColumn(name = "contactInfo")
	private ContactInfoEn contactInfo;

	@ManyToOne
	@JoinColumn(name = "addressInfo")
	private AddressInfoEn addressInfo;

	@ManyToOne
	@JoinColumn(name = "dept")
	private ClsnValEn dept;

	@ManyToOne
	@JoinColumn(name = "title")
	private ClsnValEn title;

	public StaffInfoEn(String firstName, String lastName, ClsnValEn gender, Timestamp userDOB, ClsnValEn userType,
			String userEmail, ContactInfoEn contactInfo, AddressInfoEn addressInfo, ClsnValEn dept, ClsnValEn title) {
		super(firstName, lastName, gender, userDOB, userType, userEmail);
		this.contactInfo = contactInfo;
		this.addressInfo = addressInfo;
		this.dept = dept;
		this.title = title;
	}

}
