package com.itwill.fourmen.board;
// SearchCriteriaAdminUser 클래스는 Criteria 클래스를 확장. 상위 클래스인 Criteria의 속성과 메서드를 상속받음.
public class  SearchCriteriaAdminUser extends Criteria{
	
	// category와 keyword라는 두 개의 멤버 변수 선언.
	private String category = "";
	private String keyword = "";

	// category와 keyword 변수에 대한 getter 및 setter 메서드 정의.
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
	
	// toString 메서드가 오버라이드되어 있으며, 이 메서드는 객체의 문자열 표현을 반환함.
	// 반환되는 문자열은 "SearchCriteria [category=카테고리값, keyword=키워드값]" 형식으로 구성.
	@Override
	public String toString() {
		return "SearchCriteria [category=" + category + ", keyword=" + keyword + "]";
	}
}