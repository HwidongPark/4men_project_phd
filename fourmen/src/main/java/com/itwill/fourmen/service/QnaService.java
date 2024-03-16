package com.itwill.fourmen.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itwill.fourmen.board.Criteria;
import com.itwill.fourmen.board.SearchCriteriaAdminUser;
import com.itwill.fourmen.domain.Qna;
import com.itwill.fourmen.dto.market.PagingDto;
import com.itwill.fourmen.dto.post.QnaCreateDto;
import com.itwill.fourmen.dto.post.QnaLIstItemDto;
import com.itwill.fourmen.dto.post.QnaModifyDto;
import com.itwill.fourmen.dto.post.QnaSearchDto;
import com.itwill.fourmen.repository.QnaDao;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service // 스프링 컨테이너에서 관리하는 서비스 컴포넌트.
public class QnaService {
	
	
	int postsPerPage = 9;	// 페이지당 게시글 개수
	int pagesShownInBar = 5;
	
	// QnaDao 주입받기.
	@Autowired
	private QnaDao qnaDao; // 객체 생성 권한이 Spring FrameWork로 이전. (의존성 주입 / 제어의 역전)
	//-> Spring Container가 가지고 있는 빈(bean = 객체) 중에서 타입에 맞는 객체를 주입해 줌.
	
	// 문의게시판 글 목록 보여주기...
//	public List<Qna> read(SearchCriteriaAdminUser scri) {
//		log.debug("read()");
//		// qnaDao의 메서드를 호출해서 포스트 목록을 리턴받고, 컨트롤러에게 리턴.
//		List<Qna> list = qnaDao.selectOrderByQnaIdDesc(scri); // -> DB에서 가져오는 데이터 타입은 Qna 타입.
//		return list;
//
//	}

	public List<Qna> read(int page) {
		log.debug("read(page={})", page);
		
		QnaSearchDto dto = new QnaSearchDto();
		int rowStart = (page - 1) * postsPerPage;
		dto.setRowStart(rowStart);
		dto.setPostsPerPage(postsPerPage);
		
		
		log.debug("qna 페이지 가기 전 dto = {}", dto);
		// qnaDao의 메서드를 호출해서 포스트 목록을 리턴받고, 컨트롤러에게 리턴.
		List<Qna> list = qnaDao.selectOrderByQnaIdDesc(dto); // -> DB에서 가져오는 데이터 타입은 Qna 타입.
		return list;

	}
	
	
	public int totQnaPosts() {
		log.debug("totQnaPosts()");
		
		int result = qnaDao.readTotQnaPosts();
		
		return result;
	}
	
	public int listCount(SearchCriteriaAdminUser scri) {

		return qnaDao.listCount(scri);

	}
	
	// 문의게시판 선택한 글 상세내용 보여주기...
	public Qna detail(Long id) { // 전달받은 id 값에 해당하는 포스트를 리턴하므로 Post 타입 사용.
		log.debug("qnaboard-detail(id={}", id);
		
		// 리포지토리 계층의 메서드를 호출해서 DB 테이블에서 해당 아이디의 포스트 상세내용을 검색.
		Qna qna = qnaDao.selectByQnaId(id);
		log.debug("qnaboard-detail:{}", qna);
		
		return qna;
	}
	
	// 문의게시판 새 글 작성하기...
	public int create(QnaCreateDto dto) { // 생성(삽입) 성공한 행의 개수를 리턴하므로 int 타입 사용.
		log.debug("qnaboard-create(dto={})", dto);
		
		// 리포지토리 계층의 메서드를 호출해서 DB 테이블에 데이터를 삽입.
		int result = qnaDao.qnaboard_insert(dto.toEntity());
		log.debug("qnaboard-create-result ={}", result);
		
		return result;
	}
	
	// 문의게시판 글 삭제하기...
	public int delete(long qna_id) { // 삭제 성공한 행의 개수를 리턴하므로 int 타입 사용
		
		// 리포지토리 계층의 메서드를 호출해서 delete SQL을 실행.
		int result = qnaDao.qnaboard_delete(qna_id);
		
		return result;
	}
	
	// 유저가 문의게시판 게시글의 상세보기를 클릭하면 조회수 증가...
	public int addView(Long qna_id) {
		log.debug("addView(faq_id={})", qna_id);
		
		int result = qnaDao.qnaboard_addView(qna_id);
		log.debug("addView 결과={}", result);
		
		return result;
	}
	
	// 문의게시판에서 검색하기...
	public List<QnaLIstItemDto> search(QnaSearchDto dto, int page) {
		log.debug("search(dto={}, page={}", dto, page);
		
		int rowStart = (page - 1) * postsPerPage;
		dto.setRowStart(rowStart);
		dto.setPostsPerPage(postsPerPage);
		
		List<Qna> list = qnaDao.qnaboard_search(dto);
		
		return list.stream()
				.map(QnaLIstItemDto::fromEntity)
				.toList();
	}
	
	
	// 총 검색 게시물 개수 가져오기
	public int searchTotNumber(QnaSearchDto dto) {
		log.debug("searchTotNumber={}", dto);
		
		int result = qnaDao.searchTotNumber(dto);
		log.debug("총 검색 개수 = {}", result);
		
		return result;
	}
	
	// 문의게시판 글 수정하기...
	public int update(QnaModifyDto dto, long qna_id) {
		log.debug("update(dto={})", dto);
			
		// 리포지토리 계층의 메서드를 호출해서 qna_board 테이블에서 게시글 1개를 업데이트.
		int result = qnaDao.update(dto.toEntity());
		log.debug("게시글 업데이트 결과 = {}", result);
			
		return result;
	}
	
	
	public PagingDto paging(int page, int totPostsNumber) {
		
		
		
		
		int startPage = (int) Math.ceil( ((double) page / pagesShownInBar) - 1 ) * pagesShownInBar + 1 ;
		
		int totNumPage = (int) Math.ceil( (double) totPostsNumber / postsPerPage );	// 총 페이지 개수
		
		int endPage = 0;
		if ((startPage + pagesShownInBar - 1) >= totNumPage) {
			endPage = totNumPage;
		} else {
			endPage = startPage + pagesShownInBar - 1;
		};
		
		
		PagingDto pagingDto = PagingDto.builder().startPage(startPage).endPage(endPage).totNumPages(totNumPage).pagesShownInBar(pagesShownInBar).build();
		
		return pagingDto;
	}
	
	
}