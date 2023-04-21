package com.srivn.works.smusers.db.mappers;

import java.sql.Timestamp;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;
import com.srivn.works.smusers.db.dao.users.GuardianInfo;
import com.srivn.works.smusers.db.entity.users.GuardianInfoEn;
import com.srivn.works.smusers.db.repo.ClsnValRepo;
import com.srivn.works.smusers.util.AppC;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class CustomGuardianInfoMapper {

	private final ClsnValRepo clsnValRepo;
	
	GuardianInfoMapper bookInfoMapper = Mappers.getMapper(GuardianInfoMapper.class);
	
	public GuardianInfo EnToDTO(GuardianInfoEn en) {
		GuardianInfo dto = bookInfoMapper.EnToDTO(en);
		dto.setUserDOB(en.getUserDOB().toString().substring(0, 10));
		dto.setUserType(en.getUserType().getValue());
		return dto;
	}
	
	public GuardianInfoEn DTOToEn(GuardianInfo dto) {
		GuardianInfoEn en = bookInfoMapper.DTOToEn(dto);
		en.setUserDOB(Timestamp.valueOf(dto.getUserDOB()+AppC.TS_DEF));
		en.setUserType(clsnValRepo.findByValue(dto.getUserType()));
		return en;
	}
}
