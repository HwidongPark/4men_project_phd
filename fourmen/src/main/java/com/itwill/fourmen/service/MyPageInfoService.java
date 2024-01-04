package com.itwill.fourmen.service;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.itwill.fourmen.dto.market.PagingDto;
import com.itwill.fourmen.dto.myinfo.MyInfoUpdateDto;
import com.itwill.fourmen.repository.MyPageInfoDao;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class MyPageInfoService {
	
	private final MyPageInfoDao myPageInfoDao;
	private int postsPerPage = 2;
	private int pagesShownInBar = 2;
	
	/**
	 * 프로필 사진 업로드할 때 그냥 임시로 파일 저장 후, savedFileName을 반환
	 * @param file
	 * @param sDirectory
	 * @return
	 * @throws IllegalStateException
	 * @throws IOException
	 */
	public String uploadTemporaryImage(MultipartFile file, String sDirectory) throws IllegalStateException, IOException {
		
		log.debug("uploadTemporaryImage(Sdirectory={}, file={})", sDirectory, file);
		
		String originalFileName = file.getOriginalFilename();				
		log.debug("originalFileName={}", originalFileName);
		
		// 확장자 구함
		String extension = originalFileName.substring(originalFileName.lastIndexOf("."));
		log.debug("확장자={}", extension);
		
		// 새로운 파일 이름 구함
		String savedFileName = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date()) + "temporary_profile" + extension;
		log.debug("새 파일 이름={}", savedFileName);
		
		
		String absolutePath = sDirectory + File.separator + savedFileName;
		log.debug("파일절대경로={}", absolutePath);
		file.transferTo(new File(absolutePath));
		
		
		return savedFileName;
	}
	
	
	/**
	 * 임시로 저장한 이미지 파일을 삭제하는 서비스 메서드
	 * @param newSFileName
	 * @param sDirectory
	 * @return 삭제 성공시 true, 실패시 false
	 */
	public boolean deleteTemporaryImage(String newSFileName, String sDirectory) {
		boolean result = false;
		
		log.debug("deleteTemporaryImage(newSFileName={}, sDirectory={})", newSFileName, sDirectory);
		
		File temporaryFile = new File(sDirectory + File.separator + newSFileName);
		// 삭제 성공 시 true, 실패 시 false를 리턴해줌
		result = temporaryFile.delete();
		
		return result;
	}
	
	
	/**
	 * 비밀번호를 업데이트 시키는 서비스 메서드
	 * @param updateDto
	 * @return 성공시 1, 실패시 0
	 */
	public int updatePassword(MyInfoUpdateDto updateDto) {
		log.debug("updatePassword(updateDto={})", updateDto);
		
		int result = myPageInfoDao.updatePassword(updateDto);
		log.debug("result={}", result);
		
		return result;
	}
	
	
	
	/**
	 * 시작페이지, 끝페이지, 마지막 페이지 등 포함한 PagingDto를 반환
	 * @param page
	 * @return
	 */
	public PagingDto paging(int page, String signedInUser) {
		log.debug("paging(page={})", page);
		int totNumPosts = myPageInfoDao.countTotNumber(signedInUser);
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
	
}
