package com.itwill.fourmen.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itwill.fourmen.domain.Faq;
import com.itwill.fourmen.dto.post.FaqCreateDto;
import com.itwill.fourmen.dto.post.FaqListItemDto;
import com.itwill.fourmen.dto.post.PostCreateDto;
import com.itwill.fourmen.repository.FaqDao;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service // 스프링 컨테이너에서 관리하는 서비스 컴포넌트.
public class FaqService {
	// FaqDao 주입받기.
	@Autowired
	private FaqDao faqDao; // 객체 생성 권한이 Spring FrameWork로 이전. (의존성 주입 / 제어의 역전)
	//-> Spring Container가 가지고 있는 빈(bean = 객체) 중에서 타입에 맞는 객체를 주입해 줌.
	
	// Faq게시판 글 목록 보여주기...
	public List<FaqListItemDto> read() {
		log.debug("read()");
		// faqDao의 메서드를 호출해서 faq 목록을 리턴받고, 컨트롤러에게 리턴.
		List<Faq> list = faqDao.selectOrderByFaqNumDesc(); //-> DB에서 가져오는 데이터 타입은 Qna 타입.
		log.debug("Qna(문의게시판) 포스트 목록 개수 = {}", list.size());
		
		return list.stream()
				.map(FaqListItemDto::fromEntity) //-> 람다 표현식을 간단하게 작성. (사용 조건에 주의)
				// map((x) -> QnaListItemDto.fromEntity(x)): 람다 표현식.
				// 일종의 필터링 과정을 거치는 코드.
				.toList();
	}
	
	// Faq게시판 선택한 글 상세내용 보여주기...
	public Faq detail(Long id) { // 전달받은 id 값에 해당하는 포스트를 리턴하므로 Faq 타입 사용.
		log.debug("faqboard-detail(id={}", id);
		
		// 리포지토리 계층의 메서드를 호출해서 DB 테이블에서 해당 아이디의 포스트 상세내용을 검색.
		Faq faq = faqDao.selectByFaqId(id);
		log.debug("faqboard-detail:{}", faq);
		
		return faq;
	}
	
	// Faq게시판 새 글 작성하기...
	public int create(FaqCreateDto dto) { // 생성(삽입) 성공한 행의 개수를 리턴하므로 int 타입 사용.
		log.debug("faqboard-create(dto={})", dto);
		
		// 리포지토리 계층의 메서드를 호출해서 DB 테이블에 데이터를 삽입.
		int result = faqDao.insert(dto.toEntity());
		log.debug("faqboard-create-result ={}", result);
		
		return result;
	}
	
	// Faq게시판 글 삭제하기...
	public int delete(long id) { // 삭제 성공한 행의 개수를 리턴하므로 int 타입 사용
		
		// 리포지토리 계층의 메서드를 호출해서 delete SQL을 실행.
		int result = faqDao.delete(id);
		
		return result;
	}
	
}