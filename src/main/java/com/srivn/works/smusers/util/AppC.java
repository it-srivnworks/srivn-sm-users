package com.srivn.works.smusers.util;

public class AppC {

	public static final String TS_DEF = " 00:00:01.123456789";

	public enum Gender {
		MALE(1), FEMALE(2);

		private int code;

		Gender(int code) {
			this.code = code;
		}

		public int getCode() {
			return code;
		}

		public static String getKey(int code) {
			Gender gender[] = Gender.values();
			for (Gender g : gender) {
				if (code == g.code) {
					return g.name();
				}
			}
			return null;
		}
	}

	public enum Status {
		NEW(1), ACTIVE(2);

		private int code;

		Status(int code) {
			this.code = code;
		}

		public int getCode() {
			return code;
		}

		public static String getKey(int code) {
			Status status[] = Status.values();
			for (Status g : status) {
				if (code == g.code) {
					return g.name();
				}
			}
			return null;
		}
	}

}
