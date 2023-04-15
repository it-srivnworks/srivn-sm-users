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
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "clsnid")
	private int clsnID;
	
	@Column(name = "clsnKey")
	private String key;
	
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true,fetch = FetchType.LAZY)
    @JoinColumn(name = "clsnid")
    private List<ClsnValEn> ClsnVals;
}
