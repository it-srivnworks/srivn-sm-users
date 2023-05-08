package com.srivn.works.smusers.db.mappers;

import com.srivn.works.smusers.db.dto.users.StudentInfo;
import com.srivn.works.smusers.db.entity.users.StudentInfoEn;
import com.srivn.works.smusers.db.repo.users.ClsnValRepo;
import com.srivn.works.smusers.db.repo.users.GuardianInfoRepo;
import com.srivn.works.smusers.util.AppC;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

@Component
@RequiredArgsConstructor
public class CustomStudentInfoMapper {

	private final ClsnValRepo clsnValRepo;

	private final GuardianInfoRepo guardianRepo;

	public StudentInfo EnToDTO(StudentInfoEn en) {
		StudentInfo dto = StudentInfoMapper.INSTANCE.EnToDTO(en);
		return dto;
	}
	
	public StudentInfoEn DTOToEn(StudentInfo dto) {
		StudentInfoEn en = StudentInfoMapper.INSTANCE.DTOToEn(dto);
		en.setUserDOB(Timestamp.valueOf(dto.getUserDOB()+ AppC.TS_DEF));
		en.setUserType(clsnValRepo.findByValue(dto.getUserType()));
		en.setGender(clsnValRepo.findByValue(dto.getGender()));
		en.setPguardian(guardianRepo.findByUserEmail(dto.getPguardian()));
		en.setSguardian(guardianRepo.findByUserEmail(dto.getSguardian()));
		return en;
	}

	public StudentInfoEn DTOToUpdateEn(StudentInfo dto,StudentInfoEn en) {
		StudentInfoEn enU = StudentInfoMapper.INSTANCE.DTOToUpdateEn(dto,en);
		en.setUserDOB(Timestamp.valueOf(dto.getUserDOB()+ AppC.TS_DEF));
		en.setUserType(clsnValRepo.findByValue(dto.getUserType()));
		en.setGender(clsnValRepo.findByValue(dto.getGender()));
		en.setPguardian(guardianRepo.findByUserEmail(dto.getPguardian()));
		en.setSguardian(guardianRepo.findByUserEmail(dto.getSguardian()));
		return enU;
	}
}
