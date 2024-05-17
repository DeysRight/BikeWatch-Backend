package com.bikeWatch.review.domain;

import com.bikeWatch.common.domain.BaseTimeEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Review extends BaseTimeEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String title;

	private String imagePath;

	@Lob
	private String content;

	@Builder
	public Review(String title, String imagePath, String content) {
		this.title = title;
		this.imagePath = imagePath;
		this.content = content;
	}

	public Review updateReview(String title, String imagePath, String content) {
		this.title = title;
		this.imagePath = imagePath;
		this.content = content;

		return this;
	}
}
