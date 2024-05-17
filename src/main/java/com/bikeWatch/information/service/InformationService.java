package com.bikeWatch.information.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bikeWatch.common.error.ErrorCode;
import com.bikeWatch.common.error.exception.InternalServerException;
import com.bikeWatch.information.domain.Information;
import com.bikeWatch.information.repository.InformationRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class InformationService {

	private final InformationRepository informationRepository;

	public Information findAll() {
		return informationRepository.findAll()
			.stream()
			.findFirst()
			.orElseThrow(() -> new InternalServerException(ErrorCode.INTERNAL_SERVER_ERROR));
	}
}
