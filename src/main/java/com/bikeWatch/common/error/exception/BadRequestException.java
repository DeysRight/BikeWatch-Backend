package com.bikeWatch.common.error.exception;

import com.bikeWatch.common.error.ErrorCode;

public class BadRequestException extends CommonException {

	public BadRequestException(ErrorCode errorCode) {
		super(errorCode);
	}
}
