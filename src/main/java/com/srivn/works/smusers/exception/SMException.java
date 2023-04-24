package com.srivn.works.smusers.exception;


import com.srivn.works.smusers.util.AppMsg.Err;

import jakarta.annotation.Nullable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
@AllArgsConstructor
public class SMException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	private String exType;
	private String message;

	public static SMException getSMException(Err err,@Nullable String xtraString) {
		return new SMException(err.getCode(), err.getMsgP(xtraString));
	}
}
