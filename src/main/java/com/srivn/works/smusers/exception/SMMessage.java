package com.srivn.works.smusers.exception;

import com.srivn.works.smusers.util.AppMsg.Msg;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;


@Data
@Builder
@AllArgsConstructor
public class SMMessage implements Serializable{

	private static final long serialVersionUID = 1L;
	private String appCode;
	private String message;
	
	public static SMMessage getSMMessage(Msg msg) {
		return SMMessage.builder().appCode(msg.getCode()).message(msg.getMsg())
				.build();
	}
	
}
