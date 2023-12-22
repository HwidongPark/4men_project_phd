package com.itwill.fourmen.board;

import java.time.LocalDate;

public class SearchCriteria extends Criteria{

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
