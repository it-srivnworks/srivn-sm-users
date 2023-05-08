package com.srivn.works.smusers.db.entity.personal;

import com.srivn.works.smusers.db.entity.users.UserInfoEn;
import com.srivn.works.smusers.db.entity.util.ClsnValEn;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
@Entity
@Table(name = "HEALTH_INFO")
public class HealthInfoEn{

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

	public HealthInfoEn(){};

	public HealthInfoEn(ClsnValEn bloodGroup, String notes) {
		super();
		this.bloodGroup = bloodGroup;
		this.notes = notes;
	}
	
}
