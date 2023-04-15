package com.srivn.works.smusers.db.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.srivn.works.smusers.db.entity.users.UserInfoEn;

public interface UserInfoRepo extends JpaRepository<UserInfoEn, Integer>{

}
