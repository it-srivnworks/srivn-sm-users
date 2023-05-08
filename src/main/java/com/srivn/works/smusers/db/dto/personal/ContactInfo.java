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
public class ContactInfo implements Serializable{

	private String primaryNo;
	private String secondaryNo;

	@Builder	
	public ContactInfo(String primaryNo, String secondaryNo) {
		super();
		this.primaryNo = primaryNo;
		this.secondaryNo = secondaryNo;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof ContactInfo))
			return false;
		ContactInfo ci = (ContactInfo) o;
		return Objects.equals(getPrimaryNo(), ci.getPrimaryNo()) && Objects.equals(getSecondaryNo(), ci.getSecondaryNo());
	}

	// Overriding hashCode() method
	@Override
	public int hashCode() {
		return Objects.hash(getPrimaryNo(), getSecondaryNo());
	}
}
