package com.srivn.works.smusers.db.repo.users;

import com.srivn.works.smusers.db.entity.users.UserInfoEn;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserInfoRepo extends JpaRepository<UserInfoEn, Integer>{

	@Query("SELECT COUNT(*) FROM UserInfoEn  UI WHERE UI.userEmail = :userEmail")
	int checkUserEmail(@Param("userEmail") String userEmail);
	
}
