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
	// Criterai 객체에는 
	// 현재 페이지 번호, 한 페이지에 표시될 데이터의 개수, 페이지의 시작 행 인덱스, 한 페이지에 표시될 데이터의 끝 인덱스(개수)가 포함됨.
	private Criteria cri;
	// 페이지네이션에서 임시로 계산된 끝 페이지를 나타내는 변수.
	// 페이지네이션은 전체 데이터의 개수와 페이지당 표시할 데이터의 개수를 기반으로 시작 페이지와 끝 페이지를 계산
	// tempEndPage는 실제로 표시되는 페이지의 끝을 나타내며, 이 값은 계산된 페이지의 범위를 벗어나면 안됨.
	// 이 변수는 페이지네이션을 계산하고 표시하는 동안 임시로 사용되며, 
	// 페이지네이션의 끝 페이지를 나타내는 endPage 변수에 올바른 값을 설정하는 데 사용.
	// 예를 들어, 페이지당 10개의 데이터를 표시하는 경우 전체 데이터가 100개인 경우, 
	// 페이지네이션은 10개의 페이지를 표시해야 함. 
	// 이때 tempEndPage는 10이 될 것이며, 이 값은 실제로 표시되는 페이지 범위를 나타냄.
	// 따라서 tempEndPage는 페이지네이션을 계산하고 표시하는 과정에서 임시로 사용되는 변수로, 
	// 페이지 범위를 정확하게 설정하는 데 도움을 줌.
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

	@Override
	public String toString() {
		return "PageMaker [totalCount=" + totalCount + ", startPage=" + startPage + ", endPage=" + endPage + ", prev="
				+ prev + ", next=" + next + ", displayPageNum=" + displayPageNum + ", cri=" + cri + ", tempEndPage="
				+ tempEndPage + "]";
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