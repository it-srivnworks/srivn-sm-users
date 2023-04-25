package com.srivn.works.smusers.util;

import java.util.UUID;

import com.srivn.works.smusers.db.dto.personal.AddressInfo;
import com.srivn.works.smusers.db.entity.personal.AddressInfoEn;

public class AppUtil {

	public static String generatePwd() {
		 	UUID randomUUID = UUID.randomUUID();
		    return randomUUID.toString().replaceAll("_", "").substring(0, 12);
	}

}
