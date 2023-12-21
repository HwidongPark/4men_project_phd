package com.itwill.fourmen.repository;

import java.util.List;

import com.itwill.fourmen.domain.Artist;
import com.itwill.fourmen.domain.Artist_Works;
import com.itwill.fourmen.domain.Artist_Works_Img;

public interface ArtistDao {

	List<Artist> selectOrderByUseridDesc();
	
	Artist selectByUserid(String userid);

	List<Artist_Works> selectWorksByUserid(String userid);
	
	Artist_Works selectByWorksid(String worksid);
	
	List<Artist_Works_Img> selectWorksImgByWorksid(String worksid);
	
	int updateArtist(Artist artist);
}
