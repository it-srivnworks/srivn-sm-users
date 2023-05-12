package com.srivn.works.smusers.db.repo.users;

import com.srivn.works.smusers.db.entity.users.VerifTokenEn;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VerifTokenRepo extends JpaRepository<VerifTokenEn, Integer> {

}
