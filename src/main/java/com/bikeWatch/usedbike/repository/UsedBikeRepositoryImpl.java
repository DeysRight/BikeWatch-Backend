package com.bikeWatch.usedbike.repository;

import static com.bikeWatch.usedbike.domain.QUsedBike.*;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;

import com.bikeWatch.usedbike.dto.response.QSelectUsedBikeResponse;
import com.bikeWatch.usedbike.dto.response.SelectUsedBikeResponse;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class UsedBikeRepositoryImpl implements UsedBikeRepositoryCustom {

	private final JPAQueryFactory jpaQueryFactory;

	@Override
	public Page<SelectUsedBikeResponse> getUsedBikesByKeyword(Pageable pageable, String keyword) {
		List<SelectUsedBikeResponse> content = jpaQueryFactory
			.select(new QSelectUsedBikeResponse(
				usedBike.id,
				usedBike.title,
				usedBike.imagePath,
				usedBike.content,
				usedBike.createdDateTime
			))
			.from(usedBike)
			.where(titleOrContentContainsByKeyword(keyword))
			.offset(pageable.getOffset())
			.limit(pageable.getPageSize())
			.fetch();

		JPAQuery<Long> countQuery = jpaQueryFactory
			.select(usedBike.count())
			.from(usedBike)
			.where(titleOrContentContainsByKeyword(keyword));

		return PageableExecutionUtils.getPage(content, pageable, countQuery::fetchOne);
	}

	private BooleanExpression titleOrContentContainsByKeyword(String keyword) {
		return StringUtils.isEmpty(keyword) ? null :
			usedBike.title.contains(keyword).or(usedBike.content.contains(keyword));
	}
}
