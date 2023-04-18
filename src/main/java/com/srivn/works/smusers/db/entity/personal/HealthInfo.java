package com.srivn.works.smusers.db.entity.personal;

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
@Entity
@Table(name = "HEALTH_INFO")
public class HealthInfo implements Serializable{

	@Id
    @Column(name = "userID")
    private int id;
	
	@ManyToOne
	@JoinColumn(name = "bloodGroup")
	private ClsnValEn bloodGroup;

	@Column(name = "notes")
	private String notes;

	@OneToOne
    @MapsId
    @JoinColumn(name = "userID")
    private UserInfoEn userInfoEn;

	@Builder
	public HealthInfo(ClsnValEn bloodGroup, String notes) {
		super();
		this.bloodGroup = bloodGroup;
		this.notes = notes;
	}
	
}
