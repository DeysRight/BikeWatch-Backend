package com.bikeWatch.common.error.exception;

import com.bikeWatch.common.error.ErrorCode;

public class InternalServerException extends CommonException {

	public InternalServerException(ErrorCode errorCode) {
		super(errorCode);
	}
}
