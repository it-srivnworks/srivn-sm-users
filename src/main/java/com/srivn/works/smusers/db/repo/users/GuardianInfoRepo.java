package com.srivn.works.smusers.db.repo.users;

import com.srivn.works.smusers.db.entity.users.GuardianInfoEn;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GuardianInfoRepo extends JpaRepository<GuardianInfoEn, Integer>{
	
	public GuardianInfoEn findByUserEmail(String userEmail);
}
