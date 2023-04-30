package com.srivn.works.smusers.db.repo.users;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.srivn.works.smusers.db.dto.users.GuardianInfo;
import com.srivn.works.smusers.db.entity.users.GuardianInfoEn;

public interface GuardianInfoRepo extends JpaRepository<GuardianInfoEn, Integer>{
	
	public GuardianInfoEn findByUserEmail(String userEmail);
}
