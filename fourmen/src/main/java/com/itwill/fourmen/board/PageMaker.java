package com.itwill.fourmen.board;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.springframework.web.util.UriComponents;

import org.springframework.web.util.UriComponentsBuilder;

public class PageMaker {

	// 전체 데이터의 개수를 나타내는 변수.
	private int totalCount;
	// 페이지네이션의 시작 페이지 번호를 나타내는 변수.
	private int startPage;
	// 페이지네이션의 끝 페이지 번호를 나타내는 변수.
	private int endPage;
	// 이전 페이지가 있는지 여부를 나타내는 변수.
	private boolean prev;
	// 다음 페이지가 있는지 여부를 나타내는 변수.
	private boolean next;
	// 페이지네이션에 표시될 페이지 번호의 개수를 나타내는 변수.
	private int displayPageNum = 5;
	// Criteria 객체를 저장하는 변수로, 페이지 정보와 검색 조건을 담고 있음.
	private Criteria cri;
	
	int tempEndPage;
	
	// Criteria 객체를 설정하는 메서드.
	public void setCri(Criteria cri) {

		this.cri = cri;

	}

	// 전체 데이터의 개수를 설정하고, 해당 값을 기반으로 페이지네이션 정보를 계산하는 메서드.
	public void setTotalCount(int totalCount) {

		this.totalCount = totalCount;

		calcData();

	}

	public int getTotalCount() {

		return totalCount;

	}

	public int getStartPage() {

		return startPage;

	}

	public int getEndPage() {

		return endPage;

	}

	public boolean isPrev() {

		return prev;

	}

	public boolean isNext() {

		return next;

	}

	public int getDisplayPageNum() {

		return displayPageNum;

	}

	public Criteria getCri() {

		return cri;

	}
	
	public int getTempEndPage() {
		return tempEndPage;
	}
	
	// 페이지네이션 정보를 계산하는 메서드로, startPage, endPage, prev, next 등을 계산.
	private void calcData() {
		
		// 현재 페이지를 기준으로 보여줄 페이지 번호의 끝을 계산.
		// 현재 페이지를 displayPageNum으로 나눈 뒤 올림하여 다시 곱한 값을 endPage에 저장.
		endPage = (int) (Math.ceil(cri.getPage() / (double) displayPageNum) * displayPageNum);
		
		// endPage를 기준으로 보여줄 페이지 번호의 시작을 계산.
		// endPage에서 displayPageNum을 뺀 뒤 1을 더한 값을 startPage에 저장.
		startPage = (endPage - displayPageNum) + 1;
		
		// tempEndPage는 전체 데이터 개수를 기준으로 보여줄 마지막 페이지 번호를 계산.
		// 전체 데이터 개수를 한 페이지에 보여줄 데이터 개수로 나눈 뒤 올림하여 tempEndPage에 저장
		tempEndPage = (int) (Math.ceil(totalCount / (double) cri.getPerPageNum()));
		
		// 만약 endPage가 tempEndPage보다 크다면, endPage를 tempEndPage로 설정하여 페이지네이션의 끝을 조정.
		if (endPage > tempEndPage) {
			endPage = tempEndPage;
		}
		
		// prev는 이전 페이지가 있는지 여부를 나타내는 변수로, 
		// startPage가 1인 경우에는 false로, 그렇지 않은 경우에는 true로 설정.
		prev = startPage == 1 ? false : true;
		
		// next는 다음 페이지가 있는지 여부를 나타내는 변수로, 
		// endPage에 보여지는 페이지 수를 곱한 값이 전체 데이터 개수보다 크거나 같은 경우에는 false로, 
		// 그렇지 않은 경우에는 true로 설정.
		next = endPage * cri.getPerPageNum() >= totalCount ? false : true;

	}

	// 페이지 번호에 따른 쿼리 문자열을 생성하는 메서드로,
	// UriComponentsBuilder를 사용하여 쿼리 파라미터를 추가하고 URI 문자열을 반환.
	public String makeQuery(int page) {

		UriComponents uriComponents =

				UriComponentsBuilder.newInstance()

						.queryParam("page", page)

						.queryParam("perPageNum", cri.getPerPageNum())

						.build();

		return uriComponents.toUriString();

	}

	// 검색 조건이 포함된 쿼리 문자열을 생성하는 메서드로,
	// UriComponentsBuilder를 사용하여 검색 조건에 해당하는 쿼리 파라미터를 추가하고 URI 문자열을 반환.
	public String makeSearch(int page) {

		UriComponents uriComponents = UriComponentsBuilder.newInstance().queryParam("page", page)
				.queryParam("perPageNum", cri.getPerPageNum())
				.queryParam("category", ((SearchCriteria) cri).getCategory())
				.queryParam("keyword", encoding(((SearchCriteria) cri).getKeyword()))
				.queryParam("startdate", encoding2(((SearchCriteria) cri).getStartdate()))
				.queryParam("enddate", encoding3(((SearchCriteria) cri).getEnddate())).build();
		return uriComponents.toUriString();
	}

	// 검색어를 UTF-8로 인코딩하는 메서드.
	private String encoding(String keyword) {
		if (keyword == null || keyword.trim().length() == 0) {
			return "";
		}
		try {
			return URLEncoder.encode(keyword, "UTF-8");

		} catch (UnsupportedEncodingException e) {
			return "";
		}
	}

	// 시작 날짜를 UTF-8로 인코딩하는 메서드.
	private String encoding2(String startdate) {
		if (startdate == null || startdate.trim().length() == 0) {
			return "";
		}

		try {
			return URLEncoder.encode(startdate, "UTF-8");

		} catch (UnsupportedEncodingException e) {
			return "";
		}
	}
	
	// 종료 날짜를 UTF-8로 인코딩하는 메서드.
	private String encoding3(String enddate) {
		if (enddate == null || enddate.trim().length() == 0) {
			return "";
		}

		try {
			return URLEncoder.encode(enddate, "UTF-8");

		} catch (UnsupportedEncodingException e) {
			return "";
		}
	}
	
	// 관리자 사용자의 검색 조건이 포함된 쿼리 문자열을 생성하는 메서드로, 
	// UriComponentsBuilder를 사용하여 검색 조건에 해당하는 쿼리 파라미터를 추가하고 URI 문자열을 반환.
	public String makeSearchAdminUser(int page) {

		UriComponents uriComponents = UriComponentsBuilder.newInstance().queryParam("page", page)
				.queryParam("perPageNum", cri.getPerPageNum())
				.queryParam("category", ((SearchCriteriaAdminUser) cri).getCategory())
				.queryParam("keyword", encodingUser(((SearchCriteriaAdminUser) cri).getKeyword())).build();
		return uriComponents.toUriString();
	}
	
	// 사용자 검색어를 UTF-8로 인코딩하는 메서드.
	private String encodingUser(String keyword) {
		if (keyword == null || keyword.trim().length() == 0) {
			return "";
		}
		try {
			return URLEncoder.encode(keyword, "UTF-8");

		} catch (UnsupportedEncodingException e) {
			return "";
		}
	}

}