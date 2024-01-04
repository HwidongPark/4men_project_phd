package com.itwill.fourmen.board;

public class Criteria {

    

    private int page;

    private int perPageNum;

    private int rowStart;

    private int rowEnd;

    

    public Criteria() {

        this.page = 1;

        this.perPageNum = 9;

    }

    

    public void setPage(int page) {

        if (page <= 0) {

            this.page = 1;

            return;

        }

        this.page = page;

    }

    

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
    public int getPageStart() {

        return (this.page - 1) * perPageNum;

    }

    
    // 한 페이지에 표시될 데이터의 개수를 반환하는 메서드.
    public int getPerPageNum() {

        return this.perPageNum;

    }

    
    // 페이지의 시작 행 인덱스를 계산하여 반환하는 메서드.
    public int getRowStart() {

        rowStart = ((page - 1) * perPageNum) + 1;

        return rowStart;

    }

    
    // 한 페이지에 표시될 데이터의 개수를 반환하는 메서드.
    public int getRowEnd() {

        rowEnd = rowStart + perPageNum - 1;

        return rowEnd;

    }



    @Override

    public String toString() {

        return "Criteria [page=" + page + ", perPageNum=" + perPageNum + ", rowStart=" + rowStart + ", rowEnd=" + rowEnd

                + "]";

    }

    

    

}