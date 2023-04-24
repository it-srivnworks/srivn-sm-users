package com.srivn.works.smusers.util;

import jakarta.annotation.Nullable;

public class AppMsg {

	public enum Err {
		ERR_UKN_000("UNKNOWN", "Unknown Error, Please Try Again!"), 
		ERR__DNF_001("DNF", "%s not found"),
		ERR_DUP_002("DUP", "%s already present.");

		private final String code;
		private final String msg;

		Err(String code, String msg) {
			this.code = code;
			this.msg = msg;
		}

		public String getCode() {
			return code;
		}

		public String getMsg() {
			return msg;
		}

		public String getMsgP(@Nullable String... params) {
			return getMsg().format(msg, params);
		}

	}

	public enum Msg {
		MSG_OK_000("OK", "Howdy!"),
		MSG_ADD_001("ADDED", "Added %s succesfully"),
		MSG_EXIST_002("EXIST", "Data %s Exist"),
		MSG_UPDATE_003("UPDATED", "Updated %s succesfully"),
		MSG_DELETE_004("DELETED", "Deleted %s succesfully");
		
		private final String code;
		private final String msg;

		Msg(String code, String msg) {
			this.code = code;
			this.msg = msg;
		}

		public String getCode() {
			return code;
		}

		public String getMsg() {
			return msg;
		}
		
		public String getMsgP(@Nullable String... params) {
			if(params.length == 0) {
				return getMsg().format(msg, "");	
			}else {
				return getMsg().format(msg, params);	
			}
		}

	}
}
