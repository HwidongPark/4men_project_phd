package com.itwill.fourmen.service;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.multipart.MultipartFile;

import com.itwill.fourmen.board.SearchCriteria;
import com.itwill.fourmen.board.SearchCriteriaAdminUser;
import com.itwill.fourmen.domain.Artist;
import com.itwill.fourmen.domain.Exhibition;
import com.itwill.fourmen.domain.Market;
import com.itwill.fourmen.domain.User;
import com.itwill.fourmen.domain.WishList;
import com.itwill.fourmen.domain.WorkImage;
import com.itwill.fourmen.dto.admin.UserUpdateDto;
import com.itwill.fourmen.dto.admin.exhibitioncreateDto;
import com.itwill.fourmen.dto.market.MarketPostDto;
import com.itwill.fourmen.dto.market.MarketSearchDto;
import com.itwill.fourmen.dto.market.PagingDto;
import com.itwill.fourmen.repository.AdminUserDao;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class adminUserService {
	private final AdminUserDao adminuserdao;
	private int postsPerPage = 12;
	private int pagesShownInBar = 10;
	
	public List<User> adminuserlist(SearchCriteriaAdminUser scri){
		
		
		return adminuserdao.adminuserlist(scri);
	}
	
	 public int listCount(SearchCriteriaAdminUser scri) {

	        return adminuserdao.listCount(scri);

	    }
	 
	 public User selectById(String userid){
		 
		 return adminuserdao.selectById(userid);
	 }
	 
	 public int userUpdate(UserUpdateDto dto) {
		 log.debug("userUpdate(dto={})", dto);
		 
		 int result =adminuserdao.userUpdate(dto.toEntity());
	 
		 return result;
	 	}
	 
	 public int userdelete(String userid) {
		 int result = adminuserdao.userdelete(userid);
		 
		 return result;
	 }
	 
	    public List<Exhibition> Exhibitonadmin(SearchCriteria scri){
	    	return adminuserdao.Exhibitonadmin(scri);
	    }

	    public int ExhibitionadminlistCount(SearchCriteria scri) {

	        return adminuserdao.ExhibitionadminlistCount(scri);

	    }
	    
	    public int Exhibitiondelete(String name) {
	    	int result = adminuserdao.Exhibitiondelete(name);
	    	return result;
	    }
	   
	
	    
	    
		public void upload(exhibitioncreateDto dto, String sDirectory) throws IllegalStateException, IOException {
			//file.transferTo(new File("c:/abc/"+file.getOriginalFilename()));
			
			//int updateRow = adminuserdao.Exhibitionplus(exhibition);
			log.debug("dto={}",dto);
			List<MultipartFile> files = dto.getUpload_file();
			log.debug("files={}", files);
			for(MultipartFile file : files) {
			if(!file.isEmpty()) {
			String originalFileName = file.getOriginalFilename();
			
			String extension = originalFileName.substring(originalFileName.lastIndexOf("."));
			
			String savedFileName = UUID.randomUUID().toString() + extension;
			
			String absolutePath = sDirectory + File.separator + savedFileName;
			
			file.transferTo(new File(absolutePath));
			
			dto.setPhoto(savedFileName);
			
			adminuserdao.Exhibitionplus(dto.toEntity());
			}
			}
			}
			
			/**
			 * 받고싶은 인기 포스트개수를 아규먼트로 받아서, 그 개수만큼의 상위 인기게시물을 리턴
			 * @param numOfPosts
			 * @return
			 */
			public List<MarketPostDto> readPopularMarketPosts(int numOfPosts) {
				log.debug("readPopularMarketPosts()");
				List<Market> marketPosts = adminuserdao.readPopularMarketPosts();
				log.debug("readPopularMarketPosts(postLists={})", marketPosts);
				log.debug("가져온 게시물 개수 = {}", marketPosts.size());
				
				List<MarketPostDto> marketPostsWithImages = new ArrayList<>();
				
				for (int i = 0; i < numOfPosts; i++) {
					// 해당 게시글 이미지 읽어옴
					List<WorkImage> workImages = adminuserdao.readWorkImagesofPost(marketPosts.get(i));
					log.debug("Images of the post={}", workImages);
					// DTO생성해서 리스트 추가
					MarketPostDto marketPostWithImage = MarketPostDto.fromEntity(marketPosts.get(i), workImages);
					log.debug("popularMarketPostWithImage={}", marketPostWithImage);
					
					marketPostsWithImages.add(marketPostWithImage);
				}
				
				return marketPostsWithImages;
			}
			
			public Integer countTotNumber() {
				Integer result = adminuserdao.countTotNumber();
				
				if (result == null) {
					result = 0;
				}
				
				return result;
			}
			
			public PagingDto paging(int page) {
				int totNumPosts = adminuserdao.countTotNumber();
				int startPage = (int) Math.ceil( ((double) page / pagesShownInBar) - 1 ) * pagesShownInBar + 1 ;
			
				int totNumPages = (int) Math.ceil((double) totNumPosts / postsPerPage);	// 총 페이지 개수
				int endPage = 0;
				if ((startPage + pagesShownInBar - 1) >= totNumPages) {
					endPage = totNumPages;
				} else {
					endPage = startPage + pagesShownInBar - 1;
				};
				
				PagingDto pagingDto = PagingDto.builder().startPage(startPage).endPage(endPage).totNumPages(totNumPages).pagesShownInBar(pagesShownInBar).build();
				
				return pagingDto;
			}
		
			// 페이징처리 서비스 완료하기
			public List<MarketPostDto> readPagedMarketPosts(int page) {
				log.debug("readPagedMarketPosts(page={})", page);
				
				Map<String, Integer> paging = new HashMap<>();
				paging.put("startingPost", (page - 1) * postsPerPage);
				paging.put("postsPerPage", postsPerPage);
				
				List<Market> pagedMarketPosts = adminuserdao.readPagedMarketPosts(paging);
				log.debug("pagedMarketPosts={}", pagedMarketPosts);
				
				List<MarketPostDto> marketPostsWithImages = new ArrayList<>();
				for (Market pagedMarketPost : pagedMarketPosts) {
					List<WorkImage> workImages = adminuserdao.readWorkImagesofPost(pagedMarketPost);
					log.debug("Images of the post={}", workImages);
					
					MarketPostDto marketPostWithImage = MarketPostDto.fromEntity(pagedMarketPost, workImages);
					marketPostsWithImages.add(marketPostWithImage);
				}
				log.debug("marketPostsWithImages={}", marketPostsWithImages);
				
				
				return marketPostsWithImages;
			}
			
			public List<WishList> readWishList(String signedInUser) {
				
				log.debug("readWishList(signedInUser={})", signedInUser);
				List<WishList> userWishLIst =  adminuserdao.readWishListOfUser(signedInUser);
				
				log.debug("user's wishlist = {}", userWishLIst);
				
				return userWishLIst;
		
			}
			
		    public int Marketdelete(String title) {
		    	int result = adminuserdao.Marketdelete(title);
		    	return result;
		    }
		    
		    public List<MarketPostDto> pagedSearchPosts(MarketSearchDto searchDto) {
				log.debug("searchPosts(searchDto={})", searchDto);
				
				// 검색 페이징 처리
				int startingPost = (searchDto.getPage() - 1) * postsPerPage;
				searchDto.setStartingPost(startingPost);
				searchDto.setPostsPerPage(postsPerPage);
				log.debug("searchDto필요정보 다 담음 searchDto={}", searchDto);
				
				// 페이징 처리한 포스트 가져옴
				List<Market> marketPosts = adminuserdao.searchPosts(searchDto);
				
				// 이미지 입힘
				List<MarketPostDto> marketPostsWithImages = new ArrayList<>();
				
				for (Market marketPost : marketPosts) {
					// 해당 게시글 이미지 읽어옴
					List<WorkImage> workImages = adminuserdao.readWorkImagesofPost(marketPost);
					log.debug("Images of the searchPost={}", workImages);
					// DTO생성해서 리스트 추가
					MarketPostDto marketPostWithImage = MarketPostDto.fromEntity(marketPost, workImages);
					log.debug("(search)marketPostWithImage={}", marketPostWithImage);
					
					marketPostsWithImages.add(marketPostWithImage);
				}
				
				return marketPostsWithImages;
				
			}
		    
			public PagingDto searchPaging(int page, MarketSearchDto dto) {
				int totNumPosts = adminuserdao.searchCountTotNumber(dto);
				int startPage = (int) Math.ceil( ((double) page / pagesShownInBar) - 1 ) * pagesShownInBar + 1 ;
			
				int totNumPages = (int) Math.ceil((double) totNumPosts / postsPerPage);	// 총 페이지 개수
				int endPage = 0;
				if ((startPage + pagesShownInBar - 1) >= totNumPages) {
					endPage = totNumPages;
				} else {
					endPage = startPage + pagesShownInBar - 1;
				};
				
				PagingDto pagingDto = PagingDto.builder().startPage(startPage).endPage(endPage)
						.totNumPages(totNumPages).postsPerPage(postsPerPage).pagesShownInBar(pagesShownInBar).build();
				
				return pagingDto;
			}
}
