package com.itwill.fourmen.board;
// 페이지네이션과 관련된 정보를 저장하고, 페이지에 표시될 데이터의 범위를 계산하는 기능을 제공하는 Criteria 클래스를 정의.
// 페이지와 관련된 정보를 저장하기 위한 멤버 변수가 포함된 클래스. 
// 멤버 변수: page, perPageNum, rowStart, rowEnd.
public class Criteria {
	private int page;
	private int perPageNum;
	private int rowStart;
	private int rowEnd;
	private int fetchRowStart;
	private int fetchRowEnd;
	private int pagesInBar;	// 페이지네이션에서 보여줄 페이지들의 개수
	
	// 기본 생성자가 정의되어 있으며, 이 생성자에서 page와 perPageNum의 초기값이 각각 1과 9로 설정.
	public Criteria() {
		this.page = 1;
		this.perPageNum = 9;
	}
	
	// page 값을 설정하는 메서드. 입력된 값이 유효한 범위에 있는지를 확인하고, 그에 따라 값을 설정.
	public void setPage(int page) {
		if (page <= 0) {
			this.page = 1;
			return;
		}
		this.page = page;
	}
	
	// 한 페이지당 보여주고 싶은 개수를 설정
	public void setPagesInBar(int pagesInBar) {
		this.pagesInBar = pagesInBar;
	}
	
	// perPageNum 값을 설정하는 메서드. 입력된 값이 유효한 범위에 있는지를 확인하고, 그에 따라 값을 설정.
	public void setPerPageNum(int perPageNum) {
		if (perPageNum <= 0 || perPageNum > 100) {
			this.perPageNum = 9;
			return;
		}
		this.perPageNum = perPageNum;
	}

	// 현재 페이지 번호를 반환하는 메서드.
	public int getPage() {
		return page;
	}

	// 페이지의 시작 행 인덱스를 계산하여 반환하는 메서드.
	// 페이지의 시작 지점!
	// getPageStart 메서드는 페이지네이션에서 현재 페이지의 시작 지점을 나타냄.
	// 이 메서드는 현재 페이지 번호와 페이지당 표시될 데이터의 개수를 곱하여 현재 페이지의 시작 지점을 계산.
	public int getPageStart() {
		return (this.page - 1) * perPageNum;
	}

	// 한 페이지에 표시될 데이터의 개수를 반환하는 메서드.
	public int getPerPageNum() {
		return this.perPageNum;
	}

	// 페이지의 시작 행 인덱스를 계산하여 반환하는 메서드.
	// 데이터의 시작 지점!
	// getRowStart 메서드는 데이터베이스나 데이터 집합에서 한 페이지에 표시될 데이터의 시작 지점을 나타냄.
	// 이 메서드는 페이지네이션에서 현재 페이지의 시작 행 인덱스를 계산하여 반환.
	public int getRowStart() {
		rowStart = ((page - 1) * perPageNum) + 1;
		return rowStart;
	}

	// 한 페이지에 표시될 데이터의 개수를 반환하는 메서드.
	public int getRowEnd() {
		rowEnd = rowStart + perPageNum - 1;
		return rowEnd;
	}
	
	public int getFetchRowStart() {
		fetchRowStart = (int) Math.ceil((double) ( page / pagesInBar ) );
		return fetchRowStart;
	}
	
	
	public int getFetchRowEnd() {
		fetchRowEnd = (int) Math.ceil((double) ( page / pagesInBar ) ) + pagesInBar - 1;
		return fetchRowEnd;
	}
	
	// 반환되는 문자열: "Criteria [page=페이지번호, perPageNum=페이지당데이터개수, rowStart=시작행인덱스, rowEnd=끝행인덱스]"
	@Override
	public String toString() {
		return "Criteria [page=" + page + ", perPageNum=" + perPageNum + ", rowStart=" + rowStart + ", rowEnd=" + rowEnd
				+ "]";
	}
}