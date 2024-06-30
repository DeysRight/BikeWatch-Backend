package com.bikeWatch.usedbike.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bikeWatch.common.error.ErrorCode;
import com.bikeWatch.common.error.exception.BadRequestException;
import com.bikeWatch.usedbike.domain.UsedBike;
import com.bikeWatch.usedbike.dto.request.CreateUsedBikeRequest;
import com.bikeWatch.usedbike.dto.response.SelectUsedBikeResponse;
import com.bikeWatch.usedbike.repository.UsedBikeRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UsedBikeService {

	private final UsedBikeRepository usedBikeRepository;

	@Transactional
	public void createUsedBike(CreateUsedBikeRequest request) {
		usedBikeRepository.save(request.toEntity());
	}

	public Page<SelectUsedBikeResponse> getUsedBikesByKeyword(Pageable pageable, String keyword) {
		return usedBikeRepository.getUsedBikesByKeyword(pageable, keyword);
	}

	public SelectUsedBikeResponse getUsedBikeDetail(Long id) {
		return SelectUsedBikeResponse.of(usedBikeRepository.findById(id)
			.orElseThrow(() -> new BadRequestException(ErrorCode.NOT_FOUND_USED_BIKE)));
	}

	@Transactional
	public void updateUsedBike(Long id, CreateUsedBikeRequest request) {
		UsedBike usedBike = usedBikeRepository.findById(id)
			.orElseThrow(() -> new BadRequestException(ErrorCode.NOT_FOUND_USED_BIKE));

		usedBike.updateUsedBike(request.title(), request.imagePath(), request.content());
	}

	@Transactional
	public void deleteUsedBike(Long id) {
		UsedBike usedBike = usedBikeRepository.findById(id)
			.orElseThrow(() -> new BadRequestException(ErrorCode.NOT_FOUND_USED_BIKE));

		usedBikeRepository.delete(usedBike);
	}
}
