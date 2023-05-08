package com.srivn.works.smusers.db.dto.personal;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Objects;

@ToString
@Getter
@Setter
public class AddressInfo implements Serializable{

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

	// Overriding equals() method
	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof AddressInfo))
			return false;
		AddressInfo ai = (AddressInfo) o;
		return Objects.equals(getZipCode(), ai.getZipCode()) && Objects.equals(getHouseNumber(), ai.getHouseNumber())
				&& Objects.equals(getStreet(), ai.getStreet()) && Objects.equals(getCity(), ai.getCity());
	}

	// Overriding hashCode() method
	@Override
	public int hashCode() {
		return Objects.hash(getZipCode(), getHouseNumber(), getStreet(), getCity());
	}
	
}
