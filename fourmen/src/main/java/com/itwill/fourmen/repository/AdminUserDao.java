package com.itwill.fourmen.repository;

import java.util.List;

import com.itwill.fourmen.board.SearchCriteria;
import com.itwill.fourmen.board.SearchCriteriaAdminUser;
import com.itwill.fourmen.domain.Exhibition;
import com.itwill.fourmen.domain.User;

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
	
}
