package com.srivn.works.smusers.db.dto.personal;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.io.Serializable;
import java.sql.Date;

import com.srivn.works.smusers.db.entity.users.GuardianInfoEn;
import com.srivn.works.smusers.db.entity.util.ClsnValEn;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
public class AddressInfo implements Serializable {

	private String houseNumber;
	private String street;
	private String city;
	private String state;
	private String country;
	private String zipCode;

	@Builder
	public AddressInfo(String houseNumber, String street, String city, String state, String country,
			String zipCode) {
		super();
		this.houseNumber = houseNumber;
		this.street = street;
		this.city = city;
		this.state = state;
		this.country = country;
		this.zipCode = zipCode;
	}

}
