package com.srivn.works.smusers.db.repo.users;

import com.srivn.works.smusers.db.entity.util.ClsnValEn;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClsnValRepo  extends JpaRepository<ClsnValEn, Integer>{

	ClsnValEn findByValue(String value);
}
