package com.srivn.works.smusers.db.entity.users;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
@Entity
@Table(name = "GUARDIANS")
public class GuardianInfoEn extends UserInfoEn{

}
