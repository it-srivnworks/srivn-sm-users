package com.srivn.works.smusers.db.dto.personal;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@ToString
@Getter
@Setter
public class HealthInfo implements Serializable {

	private String bloodGroup;
	private String notes;

	@Builder
	public HealthInfo(String bloodGroup, String notes) {
		super();
		this.bloodGroup = bloodGroup;
		this.notes = notes;
	}

}
