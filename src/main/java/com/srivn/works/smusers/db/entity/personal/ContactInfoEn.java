package com.srivn.works.smusers.db.entity.personal;

import java.io.Serializable;

import com.srivn.works.smusers.db.entity.util.ClsnValEn;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
@Entity
@Table(name = "CONTACTS")
public class ContactInfoEn {

	@Id
	@Column(name = "ID")
	private long ID;

	@Column(name = "primaryNo")
	private String primaryNo;

	@Column(name = "secondaryNo")
	private String secondaryNo;

	public ContactInfoEn() {
		super();
	}

	@Builder
	public ContactInfoEn(long iD, String primaryNo, String secondaryNo) {
		super();
		ID = iD;
		this.primaryNo = primaryNo;
		this.secondaryNo = secondaryNo;
	}

}
