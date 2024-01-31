package com.bikeWatch.board.service;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.bikeWatch.board.domain.Board;
import com.bikeWatch.board.dto.request.BoardCreateRequest;
import com.bikeWatch.board.dto.response.BoardCreateResponse;
import com.bikeWatch.board.dto.response.BoardFindResponse;
import com.bikeWatch.board.repository.BoardRepository;
import com.bikeWatch.category.domain.Category;
import com.bikeWatch.category.repository.CategoryRepository;
import com.bikeWatch.menu.domain.Menu;
import com.bikeWatch.menu.repository.MenuRepository;

@SpringBootTest
public class BoardServiceTest {

	@Autowired
	private BoardService boardService;

	@Autowired
	private BoardRepository boardRepository;

	@Autowired
	private MenuRepository menuRepository;

	@Autowired
	private CategoryRepository categoryRepository;

	@DisplayName("선택한 메뉴에 게시글을 등록한다.")
	@Test
	void createBoard() {
		// given
		BoardCreateRequest req = new BoardCreateRequest("게시글 제목", "내용 부분");
		Category category = Category.builder().title("카테고리").build();
		Menu menu = Menu.builder().title("메뉴").category(category).build();

		categoryRepository.save(category);
		Menu saveMenu = menuRepository.save(menu);

		Long menuId = saveMenu.getId();

		// when
		BoardCreateResponse boardCreateResponse = boardService.createBoard(req, menuId);

		// then
		assertThat(boardCreateResponse.title()).isEqualTo(req.title());
		assertThat(boardCreateResponse.content()).isEqualTo(req.content());
	}

	@DisplayName("제목 또는 내용에 키워드를 포함하는 게시판을 조회한다.")
	@Test
	void getBoardListByKeyword() {
		// given
		Board board1 = Board.builder().title("슈퍼커브").content("슈퍼커브 내용").build();
		Board board2 = Board.builder().title("시티").content("시티 내용").build();
		boardRepository.save(board1);
		boardRepository.save(board2);
		PageRequest pageRequest = PageRequest.of(0, 5);

		// when
		Page<BoardFindResponse> boardFindResponses = boardService.getBoardListByKeyword(pageRequest, "커브");

		// then
		assertThat(boardFindResponses.getContent())
			.hasSize(1) // 결과 리스트 크기
			.containsExactlyInAnyOrder(
				BoardFindResponse.builder()
					.id(1L).title("슈퍼커브").content("슈퍼커브 내용").build()
			);
		assertThat(boardFindResponses.getTotalElements()).isEqualTo(1); // 전체 엔터티 수
		assertThat(boardFindResponses.getNumber()).isEqualTo(0); // 현재 페이지 번호
		assertThat(boardFindResponses.getTotalPages()).isEqualTo(1); // 전체 페이지 수
		assertThat(boardFindResponses.isFirst()).isTrue(); // 첫 번째 페이지 여부
		assertThat(boardFindResponses.isLast()).isTrue(); // 마지막 페이지 여부
	}

	@DisplayName("선택한 메뉴의 게시판을 조회한다.")
	@Test
	void getBoardListByMenu() {
		// given
		Category category1 = Category.builder().title("카테고리1").build();
		Category category2 = Category.builder().title("카테고리2").build();
		categoryRepository.saveAll(List.of(category1, category2));

		Menu menu1 = Menu.builder().title("메뉴1").category(category1).build();
		Menu menu2 = Menu.builder().title("메뉴2").category(category2).build();
		Menu menu3 = Menu.builder().title("메뉴3").category(category2).build();
		Menu menu4 = Menu.builder().title("메뉴4").category(category2).build();
		List<Menu> menus = menuRepository.saveAll(List.of(menu1, menu2, menu3, menu4));

		for (int i = 0; i < 20; i++) {
			boardRepository.save(Board.builder().title("타이틀 " + i).content("컨텐트 " + i).menu(menu2).build());
		}
		PageRequest pageRequest = PageRequest.of(1, 10);

		// when
		Page<BoardFindResponse> boardFindResponses = boardService.getBoardListByMenu(pageRequest, menus.get(1).getId());

		// then
		assertThat(boardFindResponses.getContent()).hasSize(10);
		assertThat(boardFindResponses.getTotalElements()).isEqualTo(20); // 전체 엔터티 수
		assertThat(boardFindResponses.getNumber()).isEqualTo(1); // 현재 페이지 번호
		assertThat(boardFindResponses.getTotalPages()).isEqualTo(2); // 전체 페이지 수
		assertThat(boardFindResponses.isFirst()).isFalse(); // 첫 번째 페이지 여부
	}
}
