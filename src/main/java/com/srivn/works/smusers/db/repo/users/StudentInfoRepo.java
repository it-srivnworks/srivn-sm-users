package com.srivn.works.smusers.db.repo.users;

import com.srivn.works.smusers.db.entity.users.StudentInfoEn;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentInfoRepo extends JpaRepository<StudentInfoEn, Integer> {
    public StudentInfoEn findByUserEmail(String userEmail);
}
