package com.itwill.fourmen.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itwill.fourmen.domain.Qna;
import com.itwill.fourmen.dto.post.PostCreateDto;
import com.itwill.fourmen.dto.post.QnaCreateDto;
import com.itwill.fourmen.dto.post.QnaLIstItemDto;
import com.itwill.fourmen.repository.QnaDao;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service // 스프링 컨테이너에서 관리하는 서비스 컴포넌트.
public class QnaService {
	// QnaDao 주입받기.
	@Autowired
	private QnaDao qnaDao; // 객체 생성 권한이 Spring FrameWork로 이전. (의존성 주입 / 제어의 역전)
	//-> Spring Container가 가지고 있는 빈(bean = 객체) 중에서 타입에 맞는 객체를 주입해 줌.
	
	// 문의게시판 글 목록 보여주기...
	public List<QnaLIstItemDto> read() {
		log.debug("read()");
		// qnaDao의 메서드를 호출해서 qna 목록을 리턴받고, 컨트롤러에게 리턴.
		List<Qna> list = qnaDao.selectOrderByQnaNumDesc(); //-> DB에서 가져오는 데이터 타입은 Qna 타입.
		log.debug("Qna(문의게시판) 포스트 목록 개수 = {}", list.size());
		
		return list.stream()
				.map(QnaLIstItemDto::fromEntity) //-> 람다 표현식을 간단하게 작성. (사용 조건에 주의)
				// map((x) -> QnaListItemDto.fromEntity(x)): 람다 표현식.
				// 일종의 필터링 과정을 거치는 코드.
				.toList();
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
	
}