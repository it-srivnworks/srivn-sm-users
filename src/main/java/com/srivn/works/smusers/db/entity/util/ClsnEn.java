package com.srivn.works.smusers.db.entity.util;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

import jakarta.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "CLSN")
public class ClsnEn {

	@Id
	@Column(name = "clsnid")
	private int clsnID;
	
	@Column(name = "clsnKey")
	private String clsnKey;
	
	public ClsnEn() {
		super();
	}
	
	public ClsnEn(int clsnID, String clsnKey) {
		super();
		this.clsnID = clsnID;
		this.clsnKey = clsnKey;
	}
    
}
