<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ADMIN</title>
<style>
@font-face {
    font-family: 'EF_Rebecca';
    src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_2210-EF@1.0/EF_Rebecca.woff2') format('woff2');
    font-weight: normal;
    font-style: normal;
}

.commondesign{
	font-family: 'EF_Rebecca';
}
@font-face {
    font-family: 'NEXON Lv1 Gothic OTF';
    src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_20-04@2.1/NEXON Lv1 Gothic OTF.woff') format('woff');
    font-weight: normal;
    font-style: normal;
}
.gfont{
	font-family: 'NEXON Lv1 Gothic OTF';
}
</style>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN"
	crossorigin="anonymous">
<link rel="stylesheet" href="css/header.css">
<link rel="stylesheet" href="css/footer.css">
<link rel="stylesheet" href="css/forum-table.css">
	<link rel="stylesheet" href="css/underheader.css">
	<link rel="stylesheet" href="css/pagenation.css">	
	<link rel="stylesheet" href="css/forum-kategorie-area.css">
	<link rel="stylesheet" href="css/exhibition.css">
</head>
<%@ include file="fragments/navigation.jspf"%>
<body>
				 
	<!-- 웹페이지 상단 헤더 아래 부분 -->
    <div id="underheader-div">
        <div class="container" id="underheadrcontainer">
            <h2 class="commondesign">
                EXHIBITION ADMIN
            </h2>
        </div>
    </div>
		
		
			  <!-- 게시판 카테고리(자유게시판, 후기게시판, 질문게시판) -->
    <section role="kategorie" class="kategorie gfont" style="border-bottom: 1.5px solid #D8D8D8;">
        <div class="forum-kategorie">
            <ul class="forum-kategorie-board-lists">
                <li class="forum-kategorie-board">
                    <a href="admin">회원조회</a>
                </li>
                <li class="forum-kategorie-board">
                    <a href="artistadmin">아티스트 관리</a>
                </li>
                <li class="forum-kategorie-board">
                    <a href="marketadmin">마켓 관리</a>
                </li>
                <li class="forum-kategorie-board">
                    <a href="exhibitionadmin">전시회 관리</a>
                </li>
            </ul>
        </div>
    </section>
    	<main class="main-content gfont">
    	 <div class="w-75 m-auto ">
		 
		<c:url var="exhibitionSearch" value="/exhibitionadmin" />
    	<form action="${exhibitionSearch}" method="get" id="search-form" role="form">
    	
    	<div class="all-select">
    	
    	<div class="date-select">
    	<label for="date">
  		<input type="date"
         id="startdate-select"
         name="startdate"
         max="2030-01-01"
         min="2023-01-01"
         >
		</label>
		<label for="date">~
  		<input type="date"
         id="enddate-select"
         name="enddate"
         max="2030-01-01"
         min="2023-01-01"
         >
		</label>
		</div>
		
		<div class="text-select">
        <select name="category" id="selectCategory">
            <option value="name" name="name"<c:out value="${scri.category eq 'title' ? 'selected' : ''}"/>>전시회</option>
            <option value="location" name="location"<c:out value="${scri.category eq 'location' ? 'selected' : ''}"/>>위치</option>
        </select>
        <input maxlength="30" id="search-keyword" type="text" name="keyword" placeholder="검색" value="${scri.keyword}">
		</form>
		
 		<button id="btnSearch">
            <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-search" viewBox="0 0 16 16">
                <path d="M11.742 10.344a6.5 6.5 0 1 0-1.397 1.398h-.001c.03.04.062.078.098.115l3.85 3.85a1 1 0 0 0 1.415-1.414l-3.85-3.85a1.007 1.007 0 0 0-.115-.1zM12 6.5a5.5 5.5 0 1 1-11 0 5.5 5.5 0 0 1 11 0"/>
            </svg>
       </button>
		</div>
		</div>   
			
     	<div class="exhibition-plus-box">
     	<a href="exhibitionadmin/plus"><button class="exhibition-plus btn btn-outline-dark">전시회 추가</button></a>
     	</div>
        <!-- 각 메뉴들의 내용을 간략하게 보여줌 -->
    
		
        <!-- 훈련과정 간략하게 보여줌 -->
        <div class="girdbox">
            
             <c:forEach var="exhibition" items="${exhibition}">
               
                    <div class="exhibition-all-item card card border-light mb-3">
                    	
                        
                    	<div class="image-outer-container">
                    	<div class="image-inner-container">
                        <a class="aReserve" href="${exhibition.site}">
                        <img src="image/${exhibition.photo}" class="card-img-top" id="exhibition-image" alt="..."></a></div></div>
                        <div class="card-body">
                        <c:url var="exhibitiondelete" value="/exhibitionadmin/delete"/>
                        <form action="${exhibitiondelete}" method="get" id="exhibition-admin">
                            <h5 class="card-title text-center" id="exhibition-name">${exhibition.name}</h5>
                            </form>
                            <p class="card-text text-center">${exhibition.startdate}~${exhibition.enddate}</p>
                            <div id="btnDiv"><button id="btndelete" data-exname="${exhibition.name}" class="btndelete btn btn-dark">삭제하기</button></div>
                        
                    </div>
                    </div>
                
                </c:forEach>
   			
              </div>

            
            <!-- 게시판 글 페이지네이션(pagination)-->
    <!-- 게시판 글 페이지네이션(pagination)-->
        <div>
            <nav aria-label="Page navigation">
                <ul class="pagination">
                    <!-- 처음 페이지 -->
                    <li class="page-item">
                        <a class="page-link-img" href="exhibitionadmin${pageMaker.makeSearch(1)}">
                            <img id="pagination-img" alt="first page" src="/fourmen/pagination/pagination01.png">
                        </a>
                    </li>
                    <!-- 이전 페이지 -->
                    <li class="page-item">
                        <a class="page-link-img" href="exhibitionadmin${pageMaker.makeSearch(page-1)}" aria-label="previous"> 
                            <img id="pagination-img" alt="previous page" src="/fourmen/pagination/pagination02.png">
                        </a>
                    </li>

                    <!-- 필요한 만큼만 페이지 보여줌 -->
                    <c:forEach begin="${pageMaker.startPage}" end="${pageMaker.endPage}" var="idx">
                        <li class="page-item">
                            <a class="page-link" href="exhibitionadmin${pageMaker.makeSearch(idx)}">${idx}</a>
                        </li>
                    </c:forEach>
                    
                    <!-- 다음 페이지 -->
                    <li class="page-item">
                        <a class="page-link-img" href="exhibitionadmin${pageMaker.makeSearch(page+1)}" aria-label="next"> 
                            <img id="pagination-img" alt="next page" src="/fourmen/pagination/pagination03.png">
                        </a>
                    </li>
                    <!-- 마지막 페이지 -->
                    <li class="page-item">
                        <a class="page-link-img" href="exhibitionadmin${pageMaker.makeSearch(pageMaker.tempEndPage)}">
                            <img id="pagination-img" alt="last page" src="/fourmen/pagination/pagination04.png">
                        </a>
                    </li>

                </ul>
            </nav>
        </div>
        
    </main>
    	<%@ include file="fragments/footer.jspf" %>
	
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
		crossorigin="anonymous"></script>
	<script src="js/exhibitionjs.js"></script>
	<script src="js/exhibitionadmin.js"></script>
	<script src="/fourmen/js/header.js"></script>
</body>
</html>