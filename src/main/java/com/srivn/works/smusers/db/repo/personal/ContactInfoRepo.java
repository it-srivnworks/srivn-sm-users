package com.srivn.works.smusers.db.repo.personal;

import org.springframework.data.jpa.repository.JpaRepository;
import com.srivn.works.smusers.db.entity.personal.ContactInfoEn;

public interface ContactInfoRepo extends JpaRepository<ContactInfoEn, Long>{

}
