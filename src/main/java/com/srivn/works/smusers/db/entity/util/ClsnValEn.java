package com.srivn.works.smusers.db.entity.util;

import lombok.Getter;
import lombok.Setter;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.cache.annotation.Cacheable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Getter
@Setter
@Entity
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
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
