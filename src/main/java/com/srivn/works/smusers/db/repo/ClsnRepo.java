package com.srivn.works.smusers.db.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.srivn.works.smusers.db.entity.users.GuardianInfoEn;
import com.srivn.works.smusers.db.entity.util.ClsnEn;

public interface ClsnRepo extends JpaRepository<ClsnEn, Integer>{

}
