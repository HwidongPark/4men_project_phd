package com.itwill.fourmen.repository;

import java.util.List;

import com.itwill.dto.MarketPostDto;
import com.itwill.fourmen.domain.Market;
import com.itwill.fourmen.domain.WorkImage;

public interface MarketDao {
	
	// 마켓 글 삽입
	int insertMarketPost(Market market);
	// 포스트 아이디 찾아오기
	Long getWorkId();
	// 마켓 사진 삽입
	int insertWorkImage(WorkImage workImage);
	
	// 마켓 모든 글 + 사진 읽어오기
	List<Market> readMarketPosts();
	
	// 해당 포스트마다 매칭되는 이미지 가져오기
	List<WorkImage> readWorkImagesofPost(Market market);
	
}
