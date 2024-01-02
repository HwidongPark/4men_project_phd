package com.itwill.fourmen.service;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.itwill.fourmen.domain.Artist;
import com.itwill.fourmen.domain.Artist_Img;
import com.itwill.fourmen.domain.Artist_Works;
import com.itwill.fourmen.domain.Artist_Works_Img;
import com.itwill.fourmen.dto.artist.ArtistAddWorksDto;
import com.itwill.fourmen.dto.artist.ArtistAddWorksImgDto;
import com.itwill.fourmen.dto.artist.ArtistDto;
import com.itwill.fourmen.dto.artist.ArtistWorksImgListItemDto;
import com.itwill.fourmen.dto.artist.ArtistWorksListItemDto;
import com.itwill.fourmen.dto.artist.WorksDto;
import com.itwill.fourmen.repository.ArtistDao;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class ArtistService {

	private final ArtistDao artistDao;
	
	// 등록된 아티스트들을 /aritst 페이지에 뿌려주는 역할 
	public List<ArtistDto> readArtist() {
		log.debug("ArtistService read()");
		
		List<Artist> list = artistDao.selectOrderByUseridDesc();
		
		log.debug("List = {}",list);
		log.debug("Number OF LIST = {}", list.size());
		
		return list.stream().map(ArtistDto::fromEntity).toList();
	}// end of read() method
	
	public List<ArtistDto> readArtistImg() {
		log.debug("ArtistService - read()");
		
		List<Artist_Img> artistImgList = artistDao.selectUserImgByUserid();
		
		log.debug("ArtistServie - readArtistImg List = {}",artistImgList);
		log.debug("Number Of List = {}", artistImgList.size());
		
		return artistImgList.stream().map(ArtistDto::fromEntity).toList();
	}
	
	public ArtistDto getArtist(String userid) {
		log.debug("ArtistService - getArtist = {}", userid);
		
		Artist artist = artistDao.selectByUserid(userid);
		
		Artist_Img artistImg = artistDao.selectUserProfileImgByUserid(userid);
		
		ArtistDto artistWithImg = ArtistDto.fromEntity(artist, artistImg);
		
		return artistWithImg;
		
	}
	
	// 등록된 아티스트의 세부 페이지
	public Artist details(String userid) {
		log.debug("ArtistService details(String userid = {})",userid);
		
		Artist artist = artistDao.selectByUserid(userid);
		
		log.debug("ArtistService details artist = {}",artist);
		
		return artist;
	}
	
	public Artist_Img artistImgDetails(String userid) {
		log.debug("ArtistService artistImgDetails(String userid = {})",userid);
		
		Artist_Img artist_Img = artistDao.selectUserProfileImgByUserid(userid);
		
		return artist_Img;
	}
	
	// 각 아티스트 페이지의 작품 리스트를 뿌려주는 역할
	public List<ArtistWorksListItemDto> readWorks(String userid){
		log.debug("ArtistService readWorks()");
		log.debug("ArtistService readWorks USERID = {}", userid);
		
		List<Artist_Works> worksList = artistDao.selectWorksListByUserid(userid);
		log.debug("Works_List = {}", worksList);
		log.debug("Number OF Works_List = {}", worksList.size());
		
		return worksList.stream().map(ArtistWorksListItemDto::fromEntity).toList();
	}// end of readWorks() method
	
	// 아티스트 페이지에서 작품을 선택한 경우 작품 페이지에서 좌측 상단에 작성될 작품의 설명을 가져오기 위함...
	public Artist_Works worksDetails(Long worksid) {
		log.debug("ArtistService worksDetails WORKSID = {})", worksid);
		
		Artist_Works artist_Works = artistDao.selectByWorksid(worksid);
		
		log.debug("ArtistService worksDetails artist_Works = {}", artist_Works);
		
		return artist_Works;
	}
	
	// 작품 페이지 우측 상단에 표기될 작품의 사진을 가져오기 위함...
	public List<ArtistWorksImgListItemDto> getWorksImg(Long worksid){
		log.debug("ArtistService getWorksImg()");
		log.debug("ArtistService getWorksImg WORKSID = {}",worksid);
		
		List<Artist_Works_Img> worksImgList = artistDao.selectWorksImgByWorksid(worksid);
		log.debug("Works_Img_List = {}", worksImgList);
		log.debug("Number Of Works_Img_List = {}", worksImgList.size());
		
		return worksImgList.stream().map(ArtistWorksImgListItemDto::fromEntity).toList();
	}
	
	
	// 아티스트 등록
	public void registerArtist(ArtistDto dto, String sDirectory) throws IllegalStateException, IOException {
		log.debug("ArtistService registerArtist()");
		log.debug("===============PARAMS===============");
		log.debug("DTO =  {}",dto);
		log.debug("sDirectory = {}", sDirectory);
		log.debug("====================================");
		
		MultipartFile file = dto.getUpload_file();
		
		if(!file.isEmpty()) {
			String originalFileName = file.getOriginalFilename();
			log.debug("originalFileName = {}", originalFileName);
			
			dto.setArtist_o_img(originalFileName);
			
			String fileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));
			log.debug("fileExtension = {}", fileExtension);
			
			String artist_img = UUID.randomUUID().toString() + fileExtension;

			String absolutePath = sDirectory + File.separator + artist_img;
			log.debug("absolutePath = {}", absolutePath);
			file.transferTo(new File(absolutePath));
			
			dto.setArtist_s_img(artist_img);
						
			int registerArtistResult = artistDao.registerArtist(dto.artisToEntity());
			log.debug("아티스트 등록 = {}", registerArtistResult);
			
			int registerArtistImgResult = artistDao.registerArtistImg(dto.artistImgToEntity());
			log.debug("아티스트 사진 등록 = {}", registerArtistImgResult);
		}
	}
	
	
	// artist_details에서 개인 소개글과 사진을 업데이트 하기 위함...
	public void updateArtist(ArtistDto dto, String sDirectory) throws IllegalStateException, IOException {
		log.debug("ArtistService updateArtist()");
		log.debug("ArtistService updateArtist DTO = {}", dto);
		log.debug("ArtistService updateArtist sDirectory = {}", sDirectory);

		Artist artist = artistDao.selectByUserid(dto.getUserid());
		log.debug("ArtistService updateArtist Artist = {}", artist.toString());
				
		Artist_Img artist_Img = artistDao.selectUserProfileImgByUserid(dto.getUserid());

		MultipartFile file = dto.getUpload_file();
		
		if (!file.isEmpty()) {
			// 파일이 업로드 되어 있음.
			// 단순히 원래 있던 프로필 사진을 삭제한다.
			File exsistFile = new File(sDirectory + File.separator + artist_Img.getArtist_s_img());
			boolean existFileIsDeleted = exsistFile.delete();
			log.debug("Profile IMG DELETE = ", existFileIsDeleted);

			String originalFileName = file.getOriginalFilename();
			log.debug("originalFileName = {}", originalFileName);

			dto.setArtist_o_img(originalFileName);

			// 확장자....!
			String fileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));
			log.debug("fileExtension = {}", fileExtension);

			// 새로운 파일 이름...!
			String artist_img = UUID.randomUUID().toString() + fileExtension;

			String absolutePath = sDirectory + File.separator + artist_img;
			log.debug("absolutePath = {}", absolutePath);
			file.transferTo(new File(absolutePath));

			dto.setArtist_s_img(artist_img);
			
			artistDao.updateArtist(dto.artisToEntity());
			artistDao.updateArtistProfileImg(dto.artistImgToEntity());
			
			log.debug("=============================================================================");
			log.debug("ArtistUpdate - UPLOAD SUCCESS....!");
			log.debug("=============================================================================");
		} else {
			// 업로드된 파일이 없음...
			if(dto.getIsFileDeleted().equals("NO")) {
				int artistUpdate = artistDao.updateArtist(dto.artisToEntity());
				log.debug("=============================================================================");
				log.debug("ArtistService - updateArtist - 프사는 그대로 내용만 업데이트됨...!", artistUpdate);
				log.debug("=============================================================================");
			} else if(dto.getIsFileDeleted().equals("YES")) {
				// 업로드된 파일을 지우겠다..
				File exsistFile = new File(sDirectory + File.separator + artist_Img.getArtist_s_img());
				boolean existFileIsDeleted = exsistFile.delete();
				log.debug("Profile IMG DELETE = ", existFileIsDeleted);
				
				int artistUpdate = artistDao.updateArtist(dto.artisToEntity());
				
				int artistProfileDelete = artistDao.deleteArtistProfileImg(dto.getUserid());
				log.debug("=============================================================================");
				log.debug("ArtistService - updateArtist 내용 업데이트됨...!", artistUpdate);
				log.debug("ArtistService - updateArtist 프사 삭제됨...!", artistProfileDelete);
				log.debug("=============================================================================");
			}
		}
	}// end of updateArtist
	
	/**
	 * 아티스트의 프로필 사진을 지우는 메서드
	 * @param userid
	 * @param sDirectory
	 */
	public int deleteProfileImg(String userid, String sDirectory) {
		log.debug("deleteProfileImg()");
		log.debug("deleteProfileImg(USERID = {}, sDIRECTORY = {}", userid, sDirectory);
		
		Artist_Img artist_Img = artistDao.selectUserProfileImgByUserid(userid);
		log.debug("ARTIST_IMG = {}", artist_Img.getUserid());
	
		// TOMCAT 서버 (WAS)에서 이미지 삭제.
		
		File file = new File(sDirectory + File.separator + artist_Img.getArtist_s_img());
		boolean isDeleted = file.delete();
		log.debug("Profile IMG DELETE = ", isDeleted);
		
		// DB 이미지 데이터 삭제
		int result = artistDao.deleteArtistProfileImg(artist_Img.getUserid());
		log.debug("deleteProfileImg result = {}", result);
		
		return result;
	}
	
	
	public void addWorks(ArtistAddWorksDto dto) {
		log.debug("ArtistService addWorks");
		log.debug("ArtistService addWorks dto = {}", dto);
		
		artistDao.createWorks(dto.toEntity());
	}
	
	// 작품 별 이미지를 넣는 작업
	public void insertWorksImg(WorksDto dto, String sDirectory) throws IllegalStateException, IOException {
		log.debug("ArtistService insertWorksImg");
		log.debug("ArtistService insertWorksImg dto = {}", dto);
		log.debug("ArtistService insertWorksImg sDirectory = {}", sDirectory);
				
		List<MultipartFile> files = dto.getUpload_files();
		
		Long worksid = artistDao.getWorksID();
		
		for(MultipartFile file : files) {
			
			if(!file.isEmpty()) {
				String origninalFileName = file.getOriginalFilename();
				
				String fileExtension = origninalFileName.substring(origninalFileName.lastIndexOf("."));
				
				String savedFile = UUID.randomUUID().toString() + fileExtension;
				
				String absolutePath = sDirectory + File.separator + savedFile;
				
				file.transferTo(new File(absolutePath));
				
				Artist_Works_Img artist_Works_Img = Artist_Works_Img.builder()
						.worksid(worksid)
						.original_file(origninalFileName)
						.saved_file(savedFile)
						.build();
				log.debug("============================================================");
				log.debug("생성된 artist_Works_Img = {}", artist_Works_Img.toString());
				log.debug("============================================================");
				artistDao.createWorksImg(artist_Works_Img);
			}
		}
	}
	
	public int deleteWorks(Long worksid, String sDirectory) {
		log.debug("ArtistService deleteWorks()");
		log.debug("ArtistService deleteWorks worksid = {}, sDirectory = {}", worksid, sDirectory);
		
		List<Artist_Works_Img> works_Imgs = artistDao.selectWorksImgByWorksid(worksid);
		log.debug("===============================");
		log.debug("works_Img = {}", works_Imgs);
		log.debug("===============================");
		for(Artist_Works_Img aWorks_Img : works_Imgs) {
			File file = new File(sDirectory + File.separator + aWorks_Img.getSaved_file());
			boolean isDeleted = file.delete();
			log.debug("===============================");
			log.debug("사진이 삭제 되었습니까?? = {}", isDeleted);
			log.debug("===============================");
		}
		
		// DB에서 삭제
		int result =  artistDao.deleteWorks(worksid);
		
		return result;
	}
	
	public String getUseridByWorksId(Long worksid) {
		log.debug("ArtistService getUseridByWorksId - WORKSID = {}", worksid);
		
		String getUserid = artistDao.getUserIdByWorksId(worksid);
		
		return getUserid;
	}
	
	public void updateWorks(String sDirectory, WorksDto dto) throws IllegalStateException, IOException {
		log.debug("==============================================");
		log.debug("ArtistService - UPDATEWORKS()");
		log.debug("sDirectory = {} , dto = {}", sDirectory, dto);
		log.debug("==============================================");
		log.debug("==============================================");
		log.debug("USERID = {} ,WORKSID = {}", dto.getUserid(), dto.getWorksid());
		log.debug("==============================================");
		
		Artist_Works artist_Works = dto.worksToEntity();
		log.debug("중간 점검 = {}", artist_Works);
		
		int result = artistDao.updateWorksArticle(artist_Works);
		
		log.debug("=====================================");
		log.debug("아티스트 작품 아티클 업데이트 결과 = {}", result);
		log.debug("=====================================");

		//TODO : WorksDto로 넘어온 필드로 통해 이미지, 소개글, 제목을 수정한다.
		
			List<String> deletedImageIds = dto.getDeletedImageIds();
			List<Long> convertedIds = new ArrayList<>();

			for (String innerList : deletedImageIds) {
				String[] ids = innerList.split(",");
				for (String imgId : ids) {
					String trimmedId = imgId.trim().replaceAll("[\"\\[\\]]", ""); // 따옴표 제거
					if (!trimmedId.isEmpty()) { // 빈 문자열이 아닌 경우만 추가
						convertedIds.add(Long.parseLong(trimmedId));
					}
				}
			}

			if(!(convertedIds == null || convertedIds.isEmpty())) {
			for (Long imgid : convertedIds) {
				log.debug("imgid = {}", imgid);
				// 로컬 폴더에서 제거

				Artist_Works_Img artist_Works_Img = artistDao.selectWorksImgByImgId(imgid);

				log.debug("AWSI = {}", artist_Works_Img);

				File file = new File(sDirectory + File.separator + artist_Works_Img.getSaved_file());
				boolean isDeleted = file.delete();
				log.debug("===============================");
				log.debug("사진이 삭제 되었습니까?? = {}", isDeleted);
				log.debug("===============================");

				// DB에서 제거
				artistDao.deleteWorksImg(imgid);
			}
		} else {
			log.debug("삭제될 파일이 없습니다.");
		}
			
			List<MultipartFile> files = dto.getUpload_files();
			
			Long worksid = artistDao.getWorksID();
			
			for(MultipartFile file : files) {
				
				if(!file.isEmpty()) {
					String origninalFileName = file.getOriginalFilename();
					
					String fileExtension = origninalFileName.substring(origninalFileName.lastIndexOf("."));
					
					String savedFile = UUID.randomUUID().toString() + fileExtension;
					
					String absolutePath = sDirectory + File.separator + savedFile;
					
					file.transferTo(new File(absolutePath));
					
					Artist_Works_Img artist_Works_Img = Artist_Works_Img.builder()
							.worksid(worksid)
							.original_file(origninalFileName)
							.saved_file(savedFile)
							.build();
					log.debug("============================================================");
					log.debug("생성된 artist_Works_Img = {}", artist_Works_Img.toString());
					log.debug("============================================================");
					artistDao.createWorksImg(artist_Works_Img);
				}
			}
		
	}// end of UpdateArtistWorks
	
}
