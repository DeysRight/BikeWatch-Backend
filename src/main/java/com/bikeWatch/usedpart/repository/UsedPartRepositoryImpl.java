package com.bikeWatch.usedpart.repository;

import static com.bikeWatch.usedpart.domain.QUsedPart.*;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;

import com.bikeWatch.usedpart.dto.response.QSelectUsedPartResponse;
import com.bikeWatch.usedpart.dto.response.SelectUsedPartResponse;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class UsedPartRepositoryImpl implements UsedPartRepositoryCustom {

	private final JPAQueryFactory jpaQueryFactory;

	@Override
	public Page<SelectUsedPartResponse> getUsedPartsByKeyword(Pageable pageable, String keyword) {
		List<SelectUsedPartResponse> content = jpaQueryFactory
			.select(new QSelectUsedPartResponse(
				usedPart.id,
				usedPart.title,
				usedPart.imagePath,
				usedPart.content,
				usedPart.createdDateTime
			))
			.from(usedPart)
			.where(titleOrContentContainsByKeyword(keyword))
			.offset(pageable.getOffset())
			.limit(pageable.getPageSize())
			.fetch();

		JPAQuery<Long> countQuery = jpaQueryFactory
			.select(usedPart.count())
			.from(usedPart)
			.where(titleOrContentContainsByKeyword(keyword));

		return PageableExecutionUtils.getPage(content, pageable, countQuery::fetchOne);
	}

	private BooleanExpression titleOrContentContainsByKeyword(String keyword) {
		return StringUtils.isEmpty(keyword) ? null :
			usedPart.title.contains(keyword).or(usedPart.content.contains(keyword));
	}
}
