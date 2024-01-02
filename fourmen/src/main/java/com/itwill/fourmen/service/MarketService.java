package com.itwill.fourmen.service;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.itwill.fourmen.domain.Market;
import com.itwill.fourmen.domain.User;
import com.itwill.fourmen.domain.WishList;
import com.itwill.fourmen.domain.WorkImage;
import com.itwill.fourmen.dto.market.MarketCreateDto;
import com.itwill.fourmen.dto.market.MarketPostDto;
import com.itwill.fourmen.dto.market.MarketPostRestDto;
import com.itwill.fourmen.dto.market.MarketSearchDto;
import com.itwill.fourmen.dto.market.PagingDto;
import com.itwill.fourmen.repository.MarketDao;
import com.itwill.fourmen.repository.UserDao;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class MarketService {
	private final MarketDao marketDao;
	private final UserDao userDao;
	
	private int postsPerPage = 2;
	private int pagesShownInBar = 3;
	
	/**
	 * 포스트 작성하는 메서드. market테이블과 work_images테이블에 데이터 insert
	 * @param dto
	 * @param sDirectory
	 * @throws IllegalStateException
	 * @throws IOException
	 */
	public void createMarketPost(MarketCreateDto dto, String sDirectory) throws IllegalStateException, IOException {
		log.debug("createMarketPost(dto={}, sDirectory={})", dto, sDirectory);
		
		// 파일 외 업로드
		log.debug("Market={}", dto.toEntity());
		int updatedRow = marketDao.insertMarketPost(dto.toEntity());		
		log.debug("updatedRow={}", updatedRow);
		
		Long workId = marketDao.getWorkId();
		dto.setWorkId(workId);
		log.debug("post's workId={}", dto.getWorkId());
		
		// 파일 업로드
		List<MultipartFile> files = dto.getFiles();			
		log.debug("files.size()=", files.size());
		
		for (MultipartFile file : files) {
			
			if (!file.isEmpty()) {
				String originalFileName = file.getOriginalFilename();				
				log.debug("originalFileName={}", originalFileName);
				
				// 확장자 구함
				String extension = originalFileName.substring(originalFileName.lastIndexOf("."));
				log.debug("확장자={}", extension);
				
				// 새로운 파일 이름 구함
				String savedFileName = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date()) + extension;
				log.debug("새 파일 이름={}", savedFileName);
				
				
				String absolutePath = sDirectory + File.separator + savedFileName;
				log.debug("파일절대경로={}", absolutePath);
				file.transferTo(new File(absolutePath));
				
				WorkImage workImage = WorkImage.builder().workId(workId).originalFileName(originalFileName)
						.savedFileName(savedFileName).build();
				
				marketDao.insertWorkImage(workImage);
				
			}
		}
		
	}
	
	// 포스트 목록 읽어옴
	/**
	 * 전체 포스트의 목록, 해당 포스트에 따른 사진의 목록을 읽어와서 MarketPostDto의 리스트를 리턴
	 * @return
	 */
	public List<MarketPostDto> readMarketPosts() {
		log.debug("readMarketPosts()");
		List<Market> marketPosts = marketDao.readMarketPosts();
		log.debug("readMarketPosts(postLists={})", marketPosts);
		
		List<MarketPostDto> marketPostsWithImages = new ArrayList<>();
		
		for (Market marketPost : marketPosts) {
			// 해당 게시글 이미지 읽어옴
			List<WorkImage> workImages = marketDao.readWorkImagesofPost(marketPost);
			log.debug("Images of the post={}", workImages);
			// DTO생성해서 리스트 추가
			MarketPostDto marketPostWithImage = MarketPostDto.builder()
							.userId(marketPost.getUserId())
							.workId(marketPost.getWorkId())
							.title(marketPost.getTitle())
							.descriptionKor(marketPost.getDescriptionKor())
							.price(marketPost.getPrice())
							.yearCreated(marketPost.getYearCreated())
							.paintingSize(marketPost.getPaintingSize())
							.createdTime(marketPost.getCreatedTime())
							.isSold(marketPost.getIsSold())
							.views(marketPost.getViews())
							.likes(marketPost.getLikes())
							.workImages(workImages)
							.build();
			log.debug("marketPostWithImage={}", marketPostWithImage);
			
			marketPostsWithImages.add(marketPostWithImage);
		}
		
		return marketPostsWithImages;
	}
	
	
	// 페이징처리 서비스 완료하기
	public List<MarketPostDto> readPagedMarketPosts(int page) {
		log.debug("readPagedMarketPosts(page={})", page);
		
		Map<String, Integer> paging = new HashMap<>();
		paging.put("startingPost", (page - 1) * postsPerPage);
		paging.put("postsPerPage", postsPerPage);
		
		List<Market> pagedMarketPosts = marketDao.readPagedMarketPosts(paging);
		log.debug("pagedMarketPosts={}", pagedMarketPosts);
		
		List<MarketPostDto> marketPostsWithImages = new ArrayList<>();
		for (Market pagedMarketPost : pagedMarketPosts) {
			List<WorkImage> workImages = marketDao.readWorkImagesofPost(pagedMarketPost);
			log.debug("Images of the post={}", workImages);
			
			MarketPostDto marketPostWithImage = MarketPostDto.fromEntity(pagedMarketPost, workImages);
			marketPostsWithImages.add(marketPostWithImage);
		}
		log.debug("marketPostsWithImages={}", marketPostsWithImages);
		
		
		return marketPostsWithImages;
	}
	
	/**
	 * 시작페이지, 끝페이지, 마지막 페이지 등 포함한 PagingDto를 반환
	 * @param page
	 * @return
	 */
	public PagingDto paging(int page) {
		int totNumPosts = marketDao.countTotNumber();
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
	
	/**
	 * 현재 페이지를 아규먼트로 받아 해당 페이지의 페이지내이션에서 첫, 끝 페이지 등을 계산해서 PagingDto를 반환
	 */
	public PagingDto searchPaging(int page, MarketSearchDto dto) {
		int totNumPosts = marketDao.searchCountTotNumber(dto);
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
	
	
	/**
	 * 전체 포스트 개수 구함
	 * @return
	 */
	public Integer countTotNumber() {
		Integer result = marketDao.countTotNumber();
		
		if (result == null) {
			result = 0;
		}
		
		return result;
	}
	
	
	/**
	 * 인기 포스트의 목록, 해당 포스트에 따른 사진의 목록을 읽어와서 MarketPostDto의 리스트를 리턴
	 * @return
	 */
	public List<MarketPostDto> readPopularMarketPosts() {
		log.debug("readPopularMarketPosts()");
		List<Market> marketPosts = marketDao.readPopularMarketPosts();
		log.debug("readMarketPosts(postLists={})", marketPosts);
		log.debug("가져온 게시물 개수 = {}", marketPosts.size());
		
		List<MarketPostDto> marketPostsWithImages = new ArrayList<>();
		
		for (Market marketPost : marketPosts) {
			// 해당 게시글 이미지 읽어옴
			List<WorkImage> workImages = marketDao.readWorkImagesofPost(marketPost);
			log.debug("Images of the post={}", workImages);
			// DTO생성해서 리스트 추가
			MarketPostDto marketPostWithImage = MarketPostDto.builder()
							.userId(marketPost.getUserId())
							.workId(marketPost.getWorkId())
							.title(marketPost.getTitle())
							.descriptionKor(marketPost.getDescriptionKor())
							.price(marketPost.getPrice())
							.yearCreated(marketPost.getYearCreated())
							.paintingSize(marketPost.getPaintingSize())
							.createdTime(marketPost.getCreatedTime())
							.isSold(marketPost.getIsSold())
							.views(marketPost.getViews())
							.likes(marketPost.getLikes())
							.workImages(workImages)
							.build();
			log.debug("marketPostWithImage={}", marketPostWithImage);
			
			marketPostsWithImages.add(marketPostWithImage);
		}
		
		return marketPostsWithImages;
	}
	
	/**
	 * 받고싶은 인기 포스트개수를 아규먼트로 받아서, 그 개수만큼의 상위 인기게시물을 리턴
	 * @param numOfPosts
	 * @return
	 */
	public List<MarketPostDto> readPopularMarketPosts(int numOfPosts) {
		log.debug("readPopularMarketPosts()");
		List<Market> marketPosts = marketDao.readPopularMarketPosts();
		log.debug("readPopularMarketPosts(postLists={})", marketPosts);
		log.debug("가져온 게시물 개수 = {}", marketPosts.size());
		
		List<MarketPostDto> marketPostsWithImages = new ArrayList<>();
		
		for (int i = 0; i < numOfPosts; i++) {
			// 해당 게시글 이미지 읽어옴
			List<WorkImage> workImages = marketDao.readWorkImagesofPost(marketPosts.get(i));
			log.debug("Images of the post={}", workImages);
			// DTO생성해서 리스트 추가
			MarketPostDto marketPostWithImage = MarketPostDto.fromEntity(marketPosts.get(i), workImages);
			log.debug("popularMarketPostWithImage={}", marketPostWithImage);
			
			marketPostsWithImages.add(marketPostWithImage);
		}
		
		return marketPostsWithImages;
	}
	
	/**
	 * 특정 포스트를 읽어옴. 
	 * @param workId
	 * @return 해당 마켓포스트에 대한 정보와 이미지를 가지고 있는 객체를 반환
	 */
	public MarketPostDto readMarketPost(Long workId) {
		log.debug("readMarketPost(workId={})", workId);
		
		Market marketPost = marketDao.readMarketPost(workId);
		log.debug("읽어온 마켓포스트 = {}", marketPost);				
		
		User user = userDao.selectByUserid(marketPost.getUserId());
		log.debug("글 올린 유저={}", user);		
		
		List<WorkImage> workImages = marketDao.readWorkImagesofPost(marketPost);
		log.debug("읽어온 마켓포스트의 이미지 리스트 = {}", workImages);
		
		MarketPostDto marketPostWithImages = MarketPostDto.fromEntity(marketPost, workImages, user);
		
		return marketPostWithImages;							
		
	}	// readMarketPost()끝
	
	/**
	 * 유저가 해당 마켓 게시글의 상세보기를 클릭할 시 조회수 증가
	 * @param workId
	 * @return 조회수 증가 성공 시 1, 실패시 0
	 */
	public int addView(Long workId) {
		log.debug("addView(workId={})", workId);
		
		int result = marketDao.addView(workId);
		log.debug("addView결과={}", result);
		
		return result;
	}
	
	
//	public List<MarketPostDto> searchPosts(MarketSearchDto searchDto) {
//		log.debug("searchPosts(searchDto={})", searchDto);
//		
//		List<Market> marketPosts = marketDao.searchPosts(searchDto);
//		
//		List<MarketPostDto> marketPostsWithImages = new ArrayList<>();
//		
//		for (Market marketPost : marketPosts) {
//			// 해당 게시글 이미지 읽어옴
//			List<WorkImage> workImages = marketDao.readWorkImagesofPost(marketPost);
//			log.debug("Images of the searchPost={}", workImages);
//			// DTO생성해서 리스트 추가
//			MarketPostDto marketPostWithImage = MarketPostDto.fromEntity(marketPost, workImages);
//			log.debug("(search)marketPostWithImage={}", marketPostWithImage);
//			
//			marketPostsWithImages.add(marketPostWithImage);
//		}
//		
//		return marketPostsWithImages;
//		
//	}
	
	
	/**
	 * 페이징 처리한 검색 Service method
	 * @param searchDto
	 * @return
	 */
	public List<MarketPostDto> pagedSearchPosts(MarketSearchDto searchDto) {
		log.debug("searchPosts(searchDto={})", searchDto);
		
		// 검색 페이징 처리
		int startingPost = (searchDto.getPage() - 1) * postsPerPage;
		searchDto.setStartingPost(startingPost);
		searchDto.setPostsPerPage(postsPerPage);
		log.debug("searchDto필요정보 다 담음 searchDto={}", searchDto);
		
		// 페이징 처리한 포스트 가져옴
		List<Market> marketPosts = marketDao.searchPosts(searchDto);
		
		// 이미지 입힘
		List<MarketPostDto> marketPostsWithImages = new ArrayList<>();
		
		for (Market marketPost : marketPosts) {
			// 해당 게시글 이미지 읽어옴
			List<WorkImage> workImages = marketDao.readWorkImagesofPost(marketPost);
			log.debug("Images of the searchPost={}", workImages);
			// DTO생성해서 리스트 추가
			MarketPostDto marketPostWithImage = MarketPostDto.fromEntity(marketPost, workImages);
			log.debug("(search)marketPostWithImage={}", marketPostWithImage);
			
			marketPostsWithImages.add(marketPostWithImage);
		}
		
		return marketPostsWithImages;
		
	}
	
	/**
	 * 게시글 삭제.. 데이터베이스의 row와 local file 모두 삭제함
	 * @param workId
	 * @param sDirectory
	 * @return
	 */
	public int deleteMarketPost(Long workId, String sDirectory) {
		int result = 0;
		
		log.debug("deleteMarketPost(workId={}, sDirectory={})", workId, sDirectory);
		Market market = Market.builder().workId(workId).build();
		
		List<WorkImage> workImages = marketDao.readWorkImagesofPost(market);
		log.debug("workImages={}", workImages);
		for(WorkImage workImage : workImages) {
			File file = new File(sDirectory + File.separator + workImage.getSavedFileName());
			boolean isDeleted = file.delete();
			log.debug("is Deleted? = ", isDeleted);
		}
		
		// 테이블에서 삭제
		result += marketDao.deleteMarketPost(workId);
		result += marketDao.deleteWorkImages(workId);
		
		log.debug("result={}", result);
		
		return result;
	}
	
	
	// TODO: 시간 남으면 좀 더 좋은방법 구상
	/**
	 * 특정 포스트에 대한 REST정보 가저옴.. 
	 * @return
	 */
	public MarketPostRestDto readMarketRestPost(Long workId) {
		log.debug("readMarketRestPost(workId={})", workId);
		
		Market marketPost = marketDao.readMarketPost(workId);
		log.debug("읽어온 마켓포스트 = {}", marketPost);
		
		List<WorkImage> workImages = marketDao.readWorkImagesofPost(marketPost);
		log.debug("읽어온 마켓포스트의 이미지 리스트 = {}", workImages);
		
		MarketPostRestDto marketPostWithImages =MarketPostRestDto.fromEntity(marketPost, workImages);
		
		return marketPostWithImages;							
	}
	
	
	/**
	 * 게시글 수정할 때 사진삭제하는 경우 호출하는 서비스 메서드
	 */
	public Integer deleteImage(Long imgId, String sDirectory) {
		log.debug("deleteImage(imgId={}, sDirectory={})", imgId, sDirectory);
		
		// 로컬 이미지 파일 먼저 삭제
		WorkImage workImage = marketDao.readImage(imgId);
		log.debug("workImage={}", workImage);
		
		File file = new File(sDirectory + File.separator + workImage.getSavedFileName());
		boolean isDeleted = file.delete();
		log.debug("is Deleted? = ", isDeleted);
		
		
		// 데이터베이스에서 이미지 삭제
		int result = marketDao.deleteImage(imgId);
		log.debug("image delete result={}", result);
		
		
		return result;
	}
	
	public Integer updateMarketPost(MarketCreateDto dto, String sDirectory) throws IllegalStateException, IOException {
		log.debug("updateMarketPost(MarketCreateDto={})", dto);
		
		// 글 수정
		log.debug("update Market = {}", dto.toEntity());
		int result = marketDao.updateMarketPost(dto.toEntity());
		log.debug("result after post={}", result);
		
		// 이미지 수정
		List<MultipartFile> files = dto.getFiles();
		for (MultipartFile file : files) {
			
			if (!file.isEmpty()) {
				String originalFileName = file.getOriginalFilename();				
				log.debug("originalFileName={}", originalFileName);
				
				// 확장자 구함
				String extension = originalFileName.substring(originalFileName.lastIndexOf("."));
				log.debug("확장자={}", extension);
				
				// 새로운 파일 이름 구함
				String savedFileName = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date()) + extension;
				log.debug("새 파일 이름={}", savedFileName);
				
				
				String absolutePath = sDirectory + File.separator + savedFileName;
				log.debug("파일절대경로={}", absolutePath);
				file.transferTo(new File(absolutePath));
				
				WorkImage workImage = WorkImage.builder().workId(dto.getWorkId()).originalFileName(originalFileName)
						.savedFileName(savedFileName).build();
				
				marketDao.insertWorkImage(workImage);
				
			}
			
		}
		
		return result;
	}
	
	
	/**
	 * 로그인된 유저가 보고있는 마켓 게시물을 위시리스트에 추가.
	 * @return
	 */
	public int addWishList(WishList wishList) {
		int result = 0;
		
		log.debug("addWishList(wishList={})", wishList);
		
		// marketService의 readWishList 메서드 사용
		int isExisting = readWishList(wishList);
		log.debug("이미 찜하기 추가했나?");
		
		if (isExisting >= 1) {
			log.debug("이미 추가되서 돌아감");
			return result;
		} else {
			result = marketDao.addWishList(wishList);
			log.debug("위시리스트 추가 결과={}", result);
			
			// 라이크 추가
			int addLikeResult = marketDao.addLikes(wishList);
			log.debug("좋아요 추가 결과={}", addLikeResult);
			
			return result;
		}
		
	}
	
	
	/**
	 * WishList를 아규먼트로 받아 해당 게시글을 찜하기에서 삭제
	 * @param wishList
	 * @return 제거 성공 시 1, 실패 시 0
	 */
	public int removeWishList(WishList wishList) {
		
		log.debug("removeWishList(wishList={})", wishList);
		
		int result = 0;
		result = marketDao.removeWishList(wishList);
		log.debug("위시리스트 제거 결과={}", result);
		
		
		int subtractLike = marketDao.subtractLikes(wishList);
		log.debug("좋아요 제거 결과={}", subtractLike);
		
		return result;
	}
	
	
	/**
	 * 해당 게시물이 이미 위시리스트에 추가돼 있는지 확인하는 메서드.
	 * @param wishList
	 * @return 해당유저가 이미 찜해놨다면 1, 아니면 0을 반환
	 */
	public int readWishList(WishList wishList) {
		log.debug("readWishList(wishList={})", wishList);
		
		int result = marketDao.readWishList(wishList);
		log.debug("result={}", result);
		
		return result;
	}
	
	
	/**
	 * 로그인된 유저의 찜 목록을 돌려줌
	 * @param signedInUser
	 * @return
	 */
	public List<WishList> readWishList(String signedInUser) {
		
		log.debug("readWishList(signedInUser={})", signedInUser);
		List<WishList> userWishLIst =  marketDao.readWishListOfUser(signedInUser);
		
		log.debug("user's wishlist = {}", userWishLIst);
		
		return userWishLIst;
	}
	
	
	public int confirmDeal(Long workId) {
		log.debug("confirmDeal(workId={})", workId);
		
		int result = marketDao.confirmDeal(workId);
		log.debug("거래 확정 결과={}", result);
		
		return result;
	}
	
	
}	// MarketService 클래스 끝
