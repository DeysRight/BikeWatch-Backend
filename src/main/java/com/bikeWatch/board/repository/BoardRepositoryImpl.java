package com.bikeWatch.board.repository;

import static com.bikeWatch.board.domain.QBoard.*;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;

import com.bikeWatch.board.dto.response.FindBoardResponse;
import com.bikeWatch.board.dto.response.QFindBoardResponse;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class BoardRepositoryImpl implements BoardRepositoryCustom {

	private final JPAQueryFactory jpaQueryFactory;

	@Override
	public Page<FindBoardResponse> getListByKeyword(Pageable pageable, String keyword) {
		List<FindBoardResponse> content = jpaQueryFactory
			.select(new QFindBoardResponse(
				board.id,
				board.title,
				board.content))
			.from(board)
			.where(titleOrContentContainsByKeyword(keyword))
			.offset(pageable.getOffset())
			.limit(pageable.getPageSize())
			.fetch();

		JPAQuery<Long> countQuery = jpaQueryFactory
			.select(board.count())
			.from(board)
			.where(titleOrContentContainsByKeyword(keyword));

		return PageableExecutionUtils.getPage(content, pageable, countQuery::fetchOne);
	}

	@Override
	public Page<FindBoardResponse> getListByMenu(Pageable pageable, Long menuId) {
		List<FindBoardResponse> content = jpaQueryFactory
			.select(new QFindBoardResponse(
				board.id,
				board.title,
				board.content))
			.from(board)
			.where(board.menu.id.eq(menuId))
			.offset(pageable.getOffset())
			.limit(pageable.getPageSize())
			.fetch();

		JPAQuery<Long> countQuery = jpaQueryFactory
			.select(board.count())
			.from(board)
			.where(board.menu.id.eq(menuId));

		return PageableExecutionUtils.getPage(content, pageable, countQuery::fetchOne);
	}

	@Override
	public Page<FindBoardResponse> getBoardListByMenuAndKeyword(Pageable pageable, Long menuId, String keyword) {
		List<FindBoardResponse> content = jpaQueryFactory
			.select(new QFindBoardResponse(
				board.id,
				board.title,
				board.content))
			.from(board)
			.where(board.menu.id.eq(menuId)
				.and(titleOrContentContainsByKeyword(keyword)))
			.offset(pageable.getOffset())
			.limit(pageable.getPageSize())
			.fetch();

		JPAQuery<Long> countQuery = jpaQueryFactory
			.select(board.count())
			.from(board)
			.where(board.menu.id.eq(menuId));

		return PageableExecutionUtils.getPage(content, pageable, countQuery::fetchOne);
	}

	private BooleanExpression titleOrContentContainsByKeyword(String keyword) {
		return StringUtils.isEmpty(keyword) ? null :
			board.title.contains(keyword).or(board.content.contains(keyword));
	}
}
