package com.srivn.works.smusers.util;

import java.util.UUID;

public class AppUtil {

	public static String generatePwd() {
		 	UUID randomUUID = UUID.randomUUID();
		    return randomUUID.toString().replaceAll("_", "").substring(0, 12);
	}

}
