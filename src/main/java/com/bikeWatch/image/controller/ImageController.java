package com.bikeWatch.image.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.bikeWatch.common.domain.ApiResponse;
import com.bikeWatch.common.error.ErrorCode;
import com.bikeWatch.image.dto.response.CreateImageResponse;
import com.bikeWatch.image.service.S3Uploader;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Tag(name = "image-controller", description = "이미지 서비스를 위한 컨트롤러")
@RestController
@RequestMapping("/api/images")
@RequiredArgsConstructor
@Slf4j
public class ImageController {

	private final S3Uploader s3Uploader;

	@Operation(summary = "이미지 업로드", description = "S3 버킷에 이미지를 업로드하고 경로를 리턴 받습니다.")
	@PostMapping
	public ApiResponse<CreateImageResponse> createImage(@RequestParam(value = "file") MultipartFile file) {
		try {
			CreateImageResponse data = s3Uploader.create(file, "static");
			return ApiResponse.ok(data);
		} catch (Exception e) {
			log.error(e.getMessage());

			return ApiResponse.error(ErrorCode.INTERNAL_SERVER_ERROR);
		}
	}
}
