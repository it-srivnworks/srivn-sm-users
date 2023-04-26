package com.srivn.works.smusers.db.entity.util;

import lombok.Getter;
import lombok.Setter;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.cache.annotation.Cacheable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Getter
@Setter
@Entity
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
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
