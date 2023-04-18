package com.srivn.works.smusers.services;

import java.sql.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.srivn.works.smusers.db.entity.users.GuardianInfoEn;
import com.srivn.works.smusers.db.entity.util.ClsnValEn;
import com.srivn.works.smusers.db.repo.ClsnRepo;
import com.srivn.works.smusers.db.repo.ClsnValRepo;
import com.srivn.works.smusers.db.repo.GuardianInfoRepo;
import com.srivn.works.smusers.util.AppC;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserAdminService {

	private static final Logger logger = LoggerFactory.getLogger(UserAdminService.class);

	private final ClsnValRepo clsnValRepo;

	private final GuardianInfoRepo guardianRepo;

	public void addNewGuardianInfo() {
	
	}
}
