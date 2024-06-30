package com.bikeWatch.usedpart.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bikeWatch.common.error.ErrorCode;
import com.bikeWatch.common.error.exception.BadRequestException;
import com.bikeWatch.usedpart.domain.UsedPart;
import com.bikeWatch.usedpart.dto.request.CreateUsedPartRequest;
import com.bikeWatch.usedpart.dto.response.SelectUsedPartResponse;
import com.bikeWatch.usedpart.repository.UsedPartRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UsedPartService {

	private final UsedPartRepository usedPartRepository;

	@Transactional
	public void createUsedPart(CreateUsedPartRequest request) {
		usedPartRepository.save(request.toEntity());
	}

	public Page<SelectUsedPartResponse> getUsedPartsByKeyword(Pageable pageable, String keyword) {
		return usedPartRepository.getUsedPartsByKeyword(pageable, keyword);
	}

	public SelectUsedPartResponse getUsedPartDetail(Long id) {
		return SelectUsedPartResponse.of(usedPartRepository.findById(id)
			.orElseThrow(() -> new BadRequestException(ErrorCode.NOT_FOUND_USED_PART)));
	}

	@Transactional
	public void updateUsedPart(Long id, CreateUsedPartRequest request) {
		UsedPart usedPart = usedPartRepository.findById(id)
			.orElseThrow(() -> new BadRequestException(ErrorCode.NOT_FOUND_USED_PART));

		usedPart.updateUsedPart(request.title(), request.imagePath(), request.content());
	}

	@Transactional
	public void deleteUsedPart(Long id) {
		UsedPart usedPart = usedPartRepository.findById(id)
			.orElseThrow(() -> new BadRequestException(ErrorCode.NOT_FOUND_USED_PART));

		usedPartRepository.delete(usedPart);
	}
}
