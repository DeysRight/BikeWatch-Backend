package com.bikeWatch.information.service;

import java.util.List;

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
		List<Information> informations = informationRepository.findAll();

		if (informations.isEmpty()) {
			throw new InternalServerException(ErrorCode.INTERNAL_SERVER_ERROR);
		}
		return informations.get(0);
	}
}
