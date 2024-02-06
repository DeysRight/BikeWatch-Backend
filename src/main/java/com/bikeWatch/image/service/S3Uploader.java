package com.bikeWatch.image.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;
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
			.orElseThrow(() -> new IllegalArgumentException("MultipartFile -> File로 전환이 실패했습니다."));

		return upload(uploadFile, dirName);
	}

	public CreateImageResponse upload(File uploadFile, String dirName) {
		String fileName = dirName + "/" + uploadFile.getName(); // S3에 저장된 파일 이름
		String uploadImageUrl = putS3(uploadFile, fileName); // S3로 업로드
		log.info("uploadImageUrl = " + uploadImageUrl);

		removeNewFile(uploadFile);

		return new CreateImageResponse(uploadImageUrl);
	}

	// 1. 로컬에 파일 생성
	private Optional<File> convert(MultipartFile file) throws IOException {
		File convertFile = new File(System.getProperty("user.dir") + "/" + file.getOriginalFilename());

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
