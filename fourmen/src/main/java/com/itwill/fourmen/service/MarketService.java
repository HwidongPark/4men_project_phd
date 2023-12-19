package com.itwill.fourmen.service;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.itwill.dto.MarketCreateDto;
import com.itwill.dto.MarketPostDto;
import com.itwill.fourmen.domain.Market;
import com.itwill.fourmen.domain.WorkImage;
import com.itwill.fourmen.repository.MarketDao;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class MarketService {
	private final MarketDao marketDao;
	
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
							.createdTime(marketPost.getCreatedTime())
							.views(marketPost.getViews())
							.likes(marketPost.getLikes())
							.workImages(workImages)
							.build();
			log.debug("marketPostWithImage={}", marketPostWithImage);
			
			marketPostsWithImages.add(marketPostWithImage);
		}
		
		return marketPostsWithImages;
	}
	
	
}
