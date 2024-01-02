package com.itwill.fourmen.repository;

import java.util.List;
import java.util.Map;

import com.itwill.fourmen.board.SearchCriteria;
import com.itwill.fourmen.board.SearchCriteriaAdminUser;
import com.itwill.fourmen.domain.Exhibition;
import com.itwill.fourmen.domain.Market;
import com.itwill.fourmen.domain.User;
import com.itwill.fourmen.domain.WishList;
import com.itwill.fourmen.domain.WorkImage;
import com.itwill.fourmen.dto.market.MarketSearchDto;

public interface AdminUserDao {
	List<User> adminuserlist (SearchCriteriaAdminUser scri);
	
	int listCount(SearchCriteriaAdminUser scri);
	
	User selectById(String userid);
	
	int userUpdate(User user);
	
	int userdelete(String userid);
	
	List<Exhibition> Exhibitonadmin(SearchCriteria scri);

    int ExhibitionadminlistCount(SearchCriteria scri);
    
    int Exhibitiondelete(String name);
    
    int Exhibitionplus(Exhibition exhibition);
    
    
    
    //marketadmin
    Long getWorkId();
    List<Market> readPagedMarketPosts(Map<String, Integer> paging);
    Integer countTotNumber();
 // 마켓 모든 글 읽어오기
 	List<Market> readMarketPosts();
	// 특정 마켓 포스트 정보 가져오기
	Market readMarketPost(Long workId);
	// 마켓 인기글 읽어오기
	List<Market> readPopularMarketPosts();
	// 해당 포스트마다 매칭되는 이미지 가져오기
	List<WorkImage> readWorkImagesofPost(Market market);
	// 검색
	List<Market> searchPosts(MarketSearchDto dto);
	// 검색 전체 포스트 개수 읽어오기
		Integer searchCountTotNumber(MarketSearchDto dto);
		// 특정 이미지 가져오기
		 WorkImage readImage (Long imgId);
		 // 이미 찜했는지 확인하는 메서드
		 int readWishList(WishList wishList);
		 
		 // 유저가 찜한 게시글 리스트 반환
		 List<WishList> readWishListOfUser(String signedInUser);
		 int Marketdelete(String title);
}
