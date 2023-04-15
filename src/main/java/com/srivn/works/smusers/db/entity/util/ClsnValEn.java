package com.srivn.works.smusers.db.entity.util;

import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "CLSNVAL")
public class ClsnValEn {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "clsnValID")
	private int clsnValID;
	
	@Column(name = "clsnVal")
	private String value;
	
}
