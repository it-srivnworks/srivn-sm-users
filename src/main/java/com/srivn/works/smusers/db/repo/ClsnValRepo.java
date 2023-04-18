package com.srivn.works.smusers.db.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.srivn.works.smusers.db.entity.util.ClsnValEn;

public interface ClsnValRepo  extends JpaRepository<ClsnValEn, Integer>{

	ClsnValEn findByValue(String value);
}
