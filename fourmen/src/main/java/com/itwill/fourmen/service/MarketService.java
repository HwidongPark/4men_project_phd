package com.itwill.fourmen.service;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.itwill.dto.MarketCreateDto;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class MarketService {
	
	public void createMarketPost(MarketCreateDto dto, String sDirectory) throws IllegalStateException, IOException {
		log.debug("createMarketPost(dto={}, sDirectory={})", dto, sDirectory);
		
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
				String newFileName = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date()) + extension;
				log.debug("새 파일 이름={}", newFileName);
				
				
				String absolutePath = sDirectory + File.separator + newFileName;
				file.transferTo(new File(absolutePath));
			}
		}
		
	}
	
}
