package com.srivn.works.smusers.db.repo.users;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.srivn.works.smusers.db.dto.users.GuardianInfo;
import com.srivn.works.smusers.db.entity.users.GuardianInfoEn;
import com.srivn.works.smusers.db.entity.users.StaffInfoEn;

public interface StaffInfoRepo extends JpaRepository<StaffInfoEn, Integer>{
	
	public StaffInfoEn findByUserEmail(String userEmail);
}
