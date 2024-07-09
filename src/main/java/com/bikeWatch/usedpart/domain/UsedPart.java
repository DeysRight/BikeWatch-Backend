package com.bikeWatch.usedpart.domain;

import com.bikeWatch.common.domain.BaseTimeEntity;

import jakarta.persistence.Column;
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
public class UsedPart extends BaseTimeEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String title;

	private String imagePath;

	@Lob
	@Column(columnDefinition = "TEXT")
	private String content;

	@Builder
	public UsedPart(String title, String imagePath, String content) {
		this.title = title;
		this.imagePath = imagePath;
		this.content = content;
	}

	public UsedPart updateUsedPart(String title, String imagePath, String content) {
		this.title = title;
		this.imagePath = imagePath;
		this.content = content;

		return this;
	}
}
