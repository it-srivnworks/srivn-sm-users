package com.srivn.works.smusers.db.entity.util;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;



import jakarta.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "CLSNVAL")
public class ClsnValEn {

	@Id
	@Column(name = "clsnValID")
	private int clsnValID;
	
	@Column(name = "clsnVal")
	private String value;


	@ManyToOne
	@JoinColumn(name = "clsnid")
	ClsnEn clsn;
	
	public ClsnValEn() {
		super();
	}
	
	public ClsnValEn(int clsnValID, String value, ClsnEn clsn) {
		super();
		this.clsnValID = clsnValID;
		this.value = value;
		this.clsn = clsn;
	}

}
