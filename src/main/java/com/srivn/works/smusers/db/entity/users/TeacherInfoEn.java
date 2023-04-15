package com.srivn.works.smusers.db.entity.users;

import com.srivn.works.smusers.db.entity.personal.AddressInfo;
import com.srivn.works.smusers.db.entity.personal.ContactInfo;
import com.srivn.works.smusers.db.entity.util.ClsnValEn;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
@Entity
@Table(name = "TEACHERS")
public class TeacherInfoEn extends UserInfoEn{

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
}
