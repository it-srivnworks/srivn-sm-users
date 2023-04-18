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
public class ContactInfo implements Serializable{

	@Id
	@Column(name = "primaryNo")
	private String primaryNo;

	@Column(name = "secondaryNo")
	private String secondaryNo;

	@Builder	
	public ContactInfo(String primaryNo, String secondaryNo) {
		super();
		this.primaryNo = primaryNo;
		this.secondaryNo = secondaryNo;
	}
	
}
