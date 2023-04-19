package com.srivn.works.smusers.exception;

import java.io.Serializable;

import com.srivn.works.smusers.util.AppMsg.Msg;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;


@Data
@Builder
@AllArgsConstructor
public class SMMessage implements Serializable{

	private static final long serialVersionUID = 1L;
	private String code;
	private String message;
	
	public static SMMessage getSMMessage(Msg msg) {
		return SMMessage.builder().code(msg.getCode()).message(msg.getMsg())
				.build();
	}
	
}
