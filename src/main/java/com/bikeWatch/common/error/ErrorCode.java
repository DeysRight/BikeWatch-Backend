package com.bikeWatch.common.error;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;

@Getter
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum ErrorCode {

	// Board
	NOT_FOUND_BOARD(HttpStatus.NOT_FOUND, "해당하는 게시글이 존재하지 않습니다."),

	// Category
	NOT_FOUND_CATEGORY(HttpStatus.NOT_FOUND, "해당하는 카테고리가 존재하지 않습니다."),
	DUPLICATION_CATEGORY(HttpStatus.BAD_REQUEST, "카테고리명이 존재합니다."),

	// Menu
	NOT_FOUND_MENU(HttpStatus.NOT_FOUND, "해당하는 메뉴가 존재하지 않습니다."),
	DUPLICATION_MENU(HttpStatus.BAD_REQUEST, "메뉴명이 존재합니다."),

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
