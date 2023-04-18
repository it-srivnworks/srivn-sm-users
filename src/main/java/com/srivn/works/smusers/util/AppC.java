package com.srivn.works.smusers.util;

public class AppC {

	public enum Gender {
		MALE(1), FEMALE(2);
		private int code;
		Gender(int code) {
			this.code = code;
		}
		public int getCode() {
			return code;
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
	}

}
