package com.srivn.works.smusers.db.entity.personal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
@Entity
@Table(name = "CONTACTS")
public class ContactInfo {

	@Id
	@Column(name = "primaryNo")
	private String primaryNo;

	@Column(name = "secondaryNo")
	private String secondaryNo;

	
	public ContactInfo(String primaryNo, String secondaryNo) {
		super();
		this.primaryNo = primaryNo;
		this.secondaryNo = secondaryNo;
	}
	
}
