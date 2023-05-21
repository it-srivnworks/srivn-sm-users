package com.srivn.works.smusers.util;

import jakarta.annotation.Nullable;

public class AppMsg {

	public enum Err {
		ERR_UKN_000("UNKNOWN", "Unknown Error, Please Try Again!"), 
		ERR_DNF_001("DNF", "%s not found"),
		ERR_DUP_002("DUP", "%s already present."),
		ERR_AUTH_0031("DUP", "%s Unable to get JWT Token !."),
		ERR_AUTH_0032("DUP", "%s JWT Token has expired !."),
		ERR_AUTH_0033("DUP", "%s Bad Request !."),
		ERR_AUTH_0034("INVALID", "Invalid Data : %s"),
		ERR_INACTIVE_0035("INACTIVE", "User Inactive : %s"),
		ERR_EXPR_004("EXPIRED", "%s expired");

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
		MSG_DELETE_004("DELETED", "Deleted %s succesfully"),
		MSG_ACTIVATED_005("ACTIVATED", "User %s Activated");
		
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
