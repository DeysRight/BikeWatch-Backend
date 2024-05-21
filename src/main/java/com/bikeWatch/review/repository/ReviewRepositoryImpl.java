package com.bikeWatch.review.repository;

import static com.bikeWatch.review.domain.QReview.*;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;

import com.bikeWatch.review.dto.response.QSelectReviewResponse;
import com.bikeWatch.review.dto.response.SelectReviewResponse;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class ReviewRepositoryImpl implements ReviewRepositoryCustom {

	private final JPAQueryFactory jpaQueryFactory;

	@Override
	public Page<SelectReviewResponse> getReviewsByKeyword(Pageable pageable, String keyword) {
		List<SelectReviewResponse> content = jpaQueryFactory
			.select(new QSelectReviewResponse(
				review.id,
				review.title,
				review.imagePath,
				review.content,
				review.createdDateTime
			))
			.from(review)
			.where(titleOrContentContainsByKeyword(keyword))
			.offset(pageable.getOffset())
			.limit(pageable.getPageSize())
			.fetch();

		JPAQuery<Long> countQuery = jpaQueryFactory
			.select(review.count())
			.from(review)
			.where(titleOrContentContainsByKeyword(keyword));

		return PageableExecutionUtils.getPage(content, pageable, countQuery::fetchOne);
	}

	private BooleanExpression titleOrContentContainsByKeyword(String keyword) {
		return StringUtils.isEmpty(keyword) ? null :
			review.title.contains(keyword).or(review.content.contains(keyword));
	}
}
