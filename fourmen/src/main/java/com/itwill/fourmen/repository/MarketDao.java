package com.itwill.fourmen.repository;

import java.util.List;
import java.util.Map;

import com.itwill.fourmen.domain.Market;
import com.itwill.fourmen.domain.WishList;
import com.itwill.fourmen.domain.WorkImage;
import com.itwill.fourmen.dto.market.MarketPostDto;
import com.itwill.fourmen.dto.market.MarketSearchDto;

public interface MarketDao {
	
	// 마켓 글 삽입
	int insertMarketPost(Market market);
	
	// 포스트 아이디 찾아오기
	Long getWorkId();
	
	// 마켓 사진 삽입
	int insertWorkImage(WorkImage workImage);
	
	// 마켓 모든 글 읽어오기
	List<Market> readMarketPosts();
	
	// 전체 포스트 개수 읽어오기
	Integer countTotNumber();
	
	// 검색 전체 포스트 개수 읽어오기
	Integer searchCountTotNumber(MarketSearchDto dto);
	
	// 페이징처리한 글 읽어오기
	List<Market> readPagedMarketPosts(Map<String, Integer> paging);
	
	// 마켓 인기글 읽어오기
	List<Market> readPopularMarketPosts();
	
	// 특정 마켓 포스트 정보 가져오기
	Market readMarketPost(Long workId);
	
	// 해당 포스트마다 매칭되는 이미지 가져오기
	List<WorkImage> readWorkImagesofPost(Market market);
	
	// 해당 포스트 조회시 조회수 증가
	int addView(Long workId);
	
	// 좋아요 추가
	int addLikes(WishList wishList);
	
	// 좋아요 하나 빼기
	int subtractLikes(WishList wishList);
	
	// 검색
	List<Market> searchPosts(MarketSearchDto dto);
	
	// 게시글 삭제
	int deleteMarketPost(Long workId);
	
	// work_Images테이블에서 행 삭제
	int deleteWorkImages(Long workId);
	
	// 이미지 하나 삭제
	int deleteImage(Long imgId);
	
	// 특정 이미지 가져오기
	 WorkImage readImage (Long imgId);
	 
	 // 포스트 글 수정
	 int updateMarketPost(Market market);
	 
	 // 찜하기 추가
	 int addWishList(WishList wishList);
	 	 
	// 찜하기 제거
	int removeWishList(WishList wishList);
	 
	 // 이미 찜했는지 확인하는 메서드
	 int readWishList(WishList wishList);
	 
	 // 유저가 찜한 게시글 리스트 반환
	 List<WishList> readWishListOfUser(String signedInUser);
	 
	 
	 // 거래 확정 메서드
	 int confirmDeal(Long workId);
	 
}
