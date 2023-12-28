
package com.itwill.fourmen.board;

public class  SearchCriteriaAdminUser extends Criteria{

	private String category = "";
	private String keyword = "";

	 
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	@Override
	public String toString() {
		return "SearchCriteria [category=" + category + ", keyword=" + keyword + "]";
	}
	
}
