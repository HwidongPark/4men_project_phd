package com.itwill.fourmen.service;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.multipart.MultipartFile;

import com.itwill.dto.MarketCreateDto;
import com.itwill.fourmen.board.SearchCriteria;
import com.itwill.fourmen.board.SearchCriteriaAdminUser;
import com.itwill.fourmen.domain.Artist;
import com.itwill.fourmen.domain.Exhibition;
import com.itwill.fourmen.domain.User;
import com.itwill.fourmen.domain.WorkImage;
import com.itwill.fourmen.dto.admin.UserUpdateDto;
import com.itwill.fourmen.dto.admin.exhibitioncreateDto;
import com.itwill.fourmen.dto.artist.ArtistUpdateDto;
import com.itwill.fourmen.repository.AdminUserDao;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class adminUserService {
	private final AdminUserDao adminuserdao;
	
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
		
		
}
