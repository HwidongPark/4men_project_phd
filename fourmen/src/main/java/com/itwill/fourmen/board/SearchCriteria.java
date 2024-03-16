package com.itwill.fourmen.board;
// Criteria 클래스를 확장.
public class SearchCriteria extends Criteria{
	// 검색과 관련된 정보를 저장하기 위한 멤버 변수 포함.
	// category, keyword, startdate, enddate.
	private String category = "";
	private String keyword = "";
	private String startdate ="";
	private String enddate ="";
	 
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
	public String getStartdate() {
		return startdate;
	}
	public void setStartdate(String startdate) {
		this.startdate = startdate;
	}
	public String getEnddate() {
		return enddate;
	}
	public void setEnddate(String enddate) {
		this.enddate = enddate;
	}
	@Override
	public String toString() {
		return "SearchCriteria [category=" + category + ", keyword=" + keyword + ", startdate=" + startdate + ""
				+ ", enddate= " + enddate + "]";
	}
	
}
