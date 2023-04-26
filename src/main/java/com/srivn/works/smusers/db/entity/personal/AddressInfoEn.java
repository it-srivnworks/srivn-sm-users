package com.srivn.works.smusers.db.entity.personal;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.io.Serializable;
import java.sql.Date;
import java.util.Objects;

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
@Entity
@Table(name = "ADDRESS")
public class AddressInfoEn {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "addressID")
	private int addressID;

	@Column(name = "houseNumber")
	private String houseNumber;

	@Column(name = "street")
	private String street;

	@Column(name = "city")
	private String city;

	@Column(name = "state")
	private String state;

	@ManyToOne
	@JoinColumn(name = "country")
	private ClsnValEn country;

	@Column(name = "zipCode")
	private String zipCode;

	public AddressInfoEn() {
		super();
	}

	public AddressInfoEn(String houseNumber, String street, String city, String state, ClsnValEn country,
			String zipCode) {
		super();
		this.houseNumber = houseNumber;
		this.street = street;
		this.city = city;
		this.state = state;
		this.country = country;
		this.zipCode = zipCode;
	}

	// Overriding equals() method
	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof AddressInfoEn))
			return false;
		AddressInfoEn ai = (AddressInfoEn) o;
		return Objects.equals(getZipCode(), ai.getZipCode()) && Objects.equals(getHouseNumber(), ai.getHouseNumber())
				&& Objects.equals(getStreet(), ai.getStreet()) && Objects.equals(getCity(), ai.getCity());
	}

	// Overriding hashCode() method
	@Override
	public int hashCode() {
		return Objects.hash(getZipCode(), getHouseNumber(), getStreet(), getCity());
	}
}
