package com.bikeWatch.common.error;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;

@Getter
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum ErrorCode {

	// User
	NOT_FOUND_USER(HttpStatus.NOT_FOUND, "해당 계정이 존재하지 않습니다."),

	// Review
	NOT_FOUND_REVIEW(HttpStatus.NOT_FOUND, "존재하지 않는 리뷰입니다."),

	// UsedBike
	NOT_FOUND_USED_BIKE(HttpStatus.NOT_FOUND, "존재하지 않는 중고 바이크입니다."),

	// UsedPart
	NOT_FOUND_USED_PART(HttpStatus.NOT_FOUND, "존재하지 않는 부품입니다."),

	// Category
	NOT_FOUND_CATEGORY(HttpStatus.NOT_FOUND, "해당 카테고리가 존재하지 않습니다."),
	DUPLICATION_CATEGORY(HttpStatus.BAD_REQUEST, "카테고리명이 존재합니다."),
	NOT_REMOVE_CATEGORY(HttpStatus.BAD_REQUEST, "지울 수 없는 카테고리입니다."),

	// Image
	FAIL_FILE_TRANSITION(HttpStatus.INTERNAL_SERVER_ERROR, "MultipartFile -> File로 전환이 실패했습니다."),
	NOT_SUPPORTED_EXTENSION(HttpStatus.NOT_FOUND, "지원되지 않는 확장자입니다."),

	// Menu
	NOT_FOUND_MENU(HttpStatus.NOT_FOUND, "해당 메뉴가 존재하지 않습니다."),
	DUPLICATION_MENU(HttpStatus.BAD_REQUEST, "메뉴명이 존재합니다."),
	NOT_REMOVE_MENU(HttpStatus.BAD_REQUEST, "지울 수 없는 메뉴입니다."),

	// Client Error
	BAD_REQUEST_ERROR(HttpStatus.BAD_REQUEST, "요청값이 잘못되었습니다."),

	// Server Error
	INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "요청을 정상 처리하지 못하였습니다.");

	private final HttpStatus httpStatus;
	private final String message;

	ErrorCode(HttpStatus httpStatus, String message) {
		this.httpStatus = httpStatus;
		this.message = message;
	}
}
