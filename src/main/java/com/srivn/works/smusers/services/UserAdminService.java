package com.srivn.works.smusers.services;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.srivn.works.smusers.db.entity.users.UserInfoEn;
import com.srivn.works.smusers.db.repo.UserInfoRepo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserAdminService {
	
	private static final Logger logger = LoggerFactory.getLogger(UserAdminService.class);

	private final UserInfoRepo userInfoRepo;
	

}
