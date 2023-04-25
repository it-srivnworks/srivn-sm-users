package com.srivn.works.smusers.db.repo.users;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.srivn.works.smusers.db.entity.users.UserLoginInfoEn;

public interface UserLoginInfoRepo extends JpaRepository<UserLoginInfoEn, Integer>{

	@Query("SELECT COUNT(*) FROM UserLoginInfoEn  UL WHERE UL.userEmail = :userEmail")
	int checkUserEmail(@Param("userEmail") String userEmail);
	
	UserLoginInfoEn findByUserEmail(String userEmail);
}
