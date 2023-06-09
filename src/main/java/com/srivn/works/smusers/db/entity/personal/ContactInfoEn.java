package com.srivn.works.smusers.db.entity.personal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Objects;

@ToString
@Getter
@Setter
@Entity
@Table(name = "CONTACTS")
public class ContactInfoEn {

	@Id
	@Column(name = "ID")
	private long ID;

	@Column(name = "primaryNo")
	private String primaryNo;

	@Column(name = "secondaryNo")
	private String secondaryNo;

	public ContactInfoEn() {
		super();
	}

	public ContactInfoEn(long iD, String primaryNo, String secondaryNo) {
		super();
		ID = iD;
		this.primaryNo = primaryNo;
		this.secondaryNo = secondaryNo;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof ContactInfoEn))
			return false;
		ContactInfoEn ci = (ContactInfoEn) o;
		return Objects.equals(getPrimaryNo(), ci.getPrimaryNo()) && Objects.equals(getSecondaryNo(), ci.getSecondaryNo());
	}

	// Overriding hashCode() method
	@Override
	public int hashCode() {
		return Objects.hash(getPrimaryNo(), getSecondaryNo());
	}
}
