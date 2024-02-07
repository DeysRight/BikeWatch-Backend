package com.bikeWatch.common.error.exception;

import com.bikeWatch.common.error.ErrorCode;

import lombok.Getter;

@Getter
public class CommonException extends RuntimeException {

	private final ErrorCode errorCode;

	public CommonException(ErrorCode errorCode) {
		super(errorCode.getMessage());
		this.errorCode = errorCode;
	}
}
