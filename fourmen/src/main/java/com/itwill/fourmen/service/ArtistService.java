package com.itwill.fourmen.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.itwill.fourmen.domain.Artist;
import com.itwill.fourmen.domain.Artist_Works;
import com.itwill.fourmen.domain.Artist_Works_Img;
import com.itwill.fourmen.dto.artist.ArtistListItemDto;
import com.itwill.fourmen.dto.artist.ArtistUpdateDto;
import com.itwill.fourmen.dto.artist.ArtistWorksImgListItemDto;
import com.itwill.fourmen.dto.artist.ArtistWorksListItemDto;
import com.itwill.fourmen.repository.ArtistDao;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class ArtistService {

	private final ArtistDao artistDao;
	
	// 등록된 아티스트들을 /aritst 페이지에 뿌려주는 역할 
	public List<ArtistListItemDto> read() {
		log.debug("ArtistService read()");
		
		List<Artist> list = artistDao.selectOrderByUseridDesc();
		
		log.debug("List = {}",list);
		log.debug("Number OF LIST = {}", list.size());
		
		return list.stream().map(ArtistListItemDto::fromEntity).toList();
	}// end of read() method
	
	// 등록된 아티스트의 세부 페이지
	public Artist details(String userid) {
		log.debug("ArtistService details(String userid = {})",userid);
		
		Artist artist = artistDao.selectByUserid(userid);
		
		log.debug("ArtistService details artist = {}",artist);
		
		return artist;
	}
	
	// 각 아티스트 페이지의 작품 리스트를 뿌려주는 역할
	public List<ArtistWorksListItemDto> readWorks(String userid){
		log.debug("ArtistService readWorks()");
		log.debug("ArtistService readWorks USERID = {}", userid);
		
		List<Artist_Works> worksList = artistDao.selectWorksByUserid(userid);
		log.debug("Works_List = {}", worksList);
		log.debug("Number OF Works_List = {}", worksList.size());
		
		return worksList.stream().map(ArtistWorksListItemDto::fromEntity).toList();
	}// end of readWorks() method
	
	// 아티스트 페이지에서 작품을 선택한 경우 작품 페이지에서 좌측 상단에 작성될 작품의 설명을 가져오기 위함...
	public Artist_Works worksDetails(String worksid) {
		log.debug("ArtistService worksDetails(String worksid = {}", worksid);
		
		Artist_Works artist_Works = artistDao.selectByWorksid(worksid);
		
		log.debug("ArtistService worksDetails artist_Works = {}", artist_Works);
		
		return artist_Works;
	}
	
	// 작품 페이지 우측 상단에 표기될 작품의 사진을 가져오기 위함...
	public List<ArtistWorksImgListItemDto> getWorksImg(String worksid){
		log.debug("ArtistService getWorksImg()");
		log.debug("ArtistService getWorksImg WORKSID = {}",worksid);
		
		List<Artist_Works_Img> worksImgList = artistDao.selectWorksImgByWorksid(worksid);
		log.debug("Works_Img_List = {}", worksImgList);
		log.debug("Number Of Works_Img_List = {}", worksImgList.size());
		
		return worksImgList.stream().map(ArtistWorksImgListItemDto::fromEntity).toList();
	}
	
	// artist_details에서 개인 소개글과 사진을 업데이트 하기 위함...
	public int updateArtist(ArtistUpdateDto dto) {
		log.debug("ArtistService updateArtist()");
		log.debug("ArtistService updateArtist DTO = {}", dto);
		
		int result = artistDao.updateArtist(dto.toEntity());
		
		return result;
	}
	
}
