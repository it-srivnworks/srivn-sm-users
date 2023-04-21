package com.srivn.works.smusers.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.srivn.works.smusers.db.entity.users.GuardianInfoEn;
import com.srivn.works.smusers.db.entity.users.UserLoginInfoEn;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Service
@Transactional
public class DataTransactionService {

	@PersistenceContext
    private EntityManager entityManager;
	
	public void addUserDetailsAndLogin(GuardianInfoEn grEn,UserLoginInfoEn ulEn){
		 entityManager.persist(grEn);
		 entityManager.persist(ulEn);
	 }
}
