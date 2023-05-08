package com.srivn.works.smusers.db.repo.users;

import com.srivn.works.smusers.db.entity.users.StaffInfoEn;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StaffInfoRepo extends JpaRepository<StaffInfoEn, Integer>{
	
	public StaffInfoEn findByUserEmail(String userEmail);
}
