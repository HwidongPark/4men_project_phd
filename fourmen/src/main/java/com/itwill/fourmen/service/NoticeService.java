package com.itwill.fourmen.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itwill.fourmen.domain.Notice;
import com.itwill.fourmen.dto.post.NoticeListItemDto;
import com.itwill.fourmen.repository.NoticeDao;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service // 스프링 컨테이너에서 관리하는 서비스 컴포넌트.
public class NoticeService {
	// NoticeDao 주입받기.
	@Autowired
	private NoticeDao noticeDao; // 객체 생성 권한이 Spring FrameWork로 이전. (의존성 주입 / 제어의 역전)
	//-> Spring Container가 가지고 있는 빈(bean = 객체) 중에서 타입에 맞는 객체를 주입해 줌.
	
	// Notice게시판(공지게시판) 글 목록 보여주기...
	public List<NoticeListItemDto> read() {
		log.debug("read()");
		// noticeDao의 메서드를 호출해서 notice 목록을 리턴받고, 컨트롤러에게 리턴.
		List<Notice> list = noticeDao.selectOrderByNoticeNumDesc(); //-> DB에서 가져오는 데이터 타입은 Qna 타입.
		log.debug("공지게시판 포스트 목록 개수 = {}", list.size());
		
		return list.stream()
				.map(NoticeListItemDto::fromEntity) //-> 람다 표현식을 간단하게 작성. (사용 조건에 주의)
				// map((x) -> QnaListItemDto.fromEntity(x)): 람다 표현식.
				// 일종의 필터링 과정을 거치는 코드.
				.toList();
	}
	
}