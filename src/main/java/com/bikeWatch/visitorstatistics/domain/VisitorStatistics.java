package com.bikeWatch.visitorstatistics.domain;

import java.time.LocalDateTime;

import com.bikeWatch.common.domain.BaseTimeEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class VisitorStatistics extends BaseTimeEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String ip;

	private LocalDateTime visitorDateTime;

	@Builder
	public VisitorStatistics(String ip, LocalDateTime visitorDateTime) {
		this.ip = ip;
		this.visitorDateTime = visitorDateTime;
	}
}
