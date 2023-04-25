package com.srivn.works.smusers.db.dto.personal;

import java.io.Serializable;

import com.srivn.works.smusers.db.entity.users.UserInfoEn;
import com.srivn.works.smusers.db.entity.util.ClsnValEn;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

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
