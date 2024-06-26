package com.bikeWatch.image.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.bikeWatch.common.error.ErrorCode;
import com.bikeWatch.common.error.exception.BadRequestException;
import com.bikeWatch.common.error.exception.InternalServerException;
import com.bikeWatch.image.dto.response.CreateImageResponse;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class S3Uploader {

	private final AmazonS3Client amazonS3Client;

	@Value("${cloud.aws.s3.bucket}")
	private String bucket;

	public CreateImageResponse create(MultipartFile file, String dirName) throws IOException {
		File uploadFile = convert(file)
			.orElseThrow(() -> new InternalServerException(ErrorCode.FAIL_FILE_TRANSITION));

		return upload(uploadFile, dirName);
	}

	public CreateImageResponse upload(File uploadFile, String dirName) {
		String extension = extractExtension(uploadFile.getName());
		String fileName = dirName + "/" + organizeSavedFileName(extension); // S3에 저장된 파일 이름
		String uploadImageUrl = putS3(uploadFile, fileName); // S3로 업로드

		log.info("uploadImageUrl = " + uploadImageUrl);

		removeNewFile(uploadFile);

		return new CreateImageResponse(uploadImageUrl);
	}

	public String extractExtension(String originFileName) {
		int fileExtensionIndex = originFileName.lastIndexOf('.');
		String fileExtension = originFileName.substring(fileExtensionIndex + 1);

		if (validateExtension(fileExtension)) {
			return fileExtension;
		}
		throw new BadRequestException(ErrorCode.NOT_SUPPORTED_EXTENSION);
	}

	public String organizeSavedFileName(String extension) {
		LocalDateTime now = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");

		return now.format(formatter) + "." + extension;
	}

	public boolean validateExtension(String fileExtension) {
		String[] extension = {"jpg", "jpeg", "bmp", "gif", "png"};

		return Arrays.asList(extension).contains(fileExtension);
	}

	// 1. 로컬에 파일 생성
	private Optional<File> convert(MultipartFile file) throws IOException {
		// File convertFile = new File(System.getProperty("user.dir") + "/" + file.getOriginalFilename());
		File convertFile = new File("/home/worker/" + file.getOriginalFilename());

		if (convertFile.createNewFile()) {
			try (FileOutputStream fos = new FileOutputStream(convertFile)) {
				fos.write(file.getBytes());
			}
			return Optional.of(convertFile);
		}
		return Optional.empty();
	}

	// 2. S3에 업로드
	private String putS3(File uploadFile, String fileName) {
		amazonS3Client.putObject(new PutObjectRequest(bucket, fileName, uploadFile).withCannedAcl(
			CannedAccessControlList.PublicRead));
		return amazonS3Client.getUrl(bucket, fileName).toString();
	}

	// 3. 로컬에 생성된 파일 삭제
	private void removeNewFile(File targetFile) {
		if (targetFile.delete()) {
			log.info("파일이 삭제되었습니다.");
		} else {
			log.info("파일 삭제 실패.");
		}
	}
}
