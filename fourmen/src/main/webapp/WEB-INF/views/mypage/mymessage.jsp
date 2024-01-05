<%@page import="com.itwill.fourmen.domain.Message"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fn" uri="jakarta.tags.functions" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>My page</title>
		
		
		<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
		 rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" 
		 crossorigin="anonymous">
        <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200" />
        <!-- css 파일 적용 -->
        <link rel="stylesheet" href="/fourmen/css/header.css">
        <link rel="stylesheet" href="/fourmen/css/footer.css">
        <link rel="stylesheet" href="/fourmen/css/forum-table.css">
        <link rel="stylesheet" href="/fourmen/css/underheader.css">
        <link rel="stylesheet" href="/fourmen/css/pagenation.css">
        <link rel="stylesheet" href="/fourmen/css/forum-search-area.css">
        <link rel="stylesheet" href="/fourmen/css/forum-kategorie-area.css">
        <link rel="stylesheet" href="/fourmen/css/forum-create-new-post.css">
		<link rel="stylesheet" href="/fourmen/css/mypage-message.css">
	</head>
	
	<body>
		
        <!-- 헤더 파일 include -->
<%@ include file="../fragments/navigation.jspf"%>

<!-- body 시작점 -->
<body>
    
    <!-- 웹페이지 상단 헤더 아래 부분 -->
    <div id="underheader-div">
        <div class="container" id="underheadrcontainer">
            <h2 class="commondesign">
                거래 관리
            </h2>
        </div>
    </div>
    
    <!-- main 시작점 -->
    <main class="gfont">
    
    <!-- 게시판 카테고리(자유게시판, 후기게시판, 질문게시판) -->
    <section role="kategorie" class="kategorie" style="border-bottom: 1.5px solid #D8D8D8;">
        <div class="forum-kategorie">
            <ul class="forum-kategorie-board-lists">
                <li class="forum-kategorie-board">                
                    <a href="/fourmen/mypage/mymessage/received">
                        <c:if test="${ category == 'received' }">
                            <b>
                        </c:if>
                        받은 메세지
                        <c:if test="${ category == 'received' }">
                            </b>
                        </c:if>
                    </a>
                </li>
                <li class="forum-kategorie-board">
                    <a href="/fourmen/mypage/mymessage/sent">
                        <c:if test="${ category == 'sent' }">
                            <b>
                        </c:if>
                        보낸 메세지
                        <c:if test="${ category == 'sent' }">
                            </b>
                        </c:if>
                    </a>
                </li>                
            </ul>
        </div>
    </section>
    
    <!-- 게시글 검색창 -->
    <section role="search" class="search">
        <div class="forum-top-area">
            <div class="forum-search-area">
                <div class="forum-search-select-area">
                    <select class="forum-select-box">
                        <option class="forum-select-option">전체</option>
                        <option class="forum-select-option">제목</option>
                        <option class="forum-select-option">작성자</option>
                        <option class="forum-select-option">내용</option>
                    </select>
                </div>
                <div class="forum-search-form-area">
                    <input id=forum-search-input autocomplete="on" placeholder="검색어를 입력하세요." type="text">
                </div>

                <div class="forum-search-btn-area">
                    <button class="forum-search-btn" type="button">
                        <img id="forum-search-btn-img" alt="검색버튼" src="../../icon/search01.svg">
                    </button>
                </div>
            </div>
        </div>
    </section>
    
    <!-- board-list-content 게시판 글 리스트 테이블 -->
    <section class="freeboard-list w-100" style="padding: 3.5rem 18.5rem 0rem 18.5rem;">
        <table class="table w-100">
            <colgroup>

                <col style="width: 15%;">
                <col style="width: 50%;">
                <col style="width: 15%;">
                <col style="width: 10%;">
                <col style="width: 10%;">
            </colgroup>
            <thead>
            <tr>
                <th>대표 사진</th>
                <th>제목</th>
                <th>제시 가격</th>
                <th>보낸이</th>
                <th>status</th>       
            </tr>
            </thead>
            <tbody class="table-group-divider">
            
            <c:if test="${ messageDtoList.size() ge 1 }">
                <c:forEach begin="0" end="${ messageDtoList.size() - 1 }" step="1" var="index">                 
                <!-- var: 변수(리스트 값을 저장) / items: 리스트 -->
                <!-- PostController.java에서 전달된 데이터 사용 (리스트의 이름이 items에 들어가야 함)
                -> model.addAttribute("freeboard_posts", list); //-> 뷰에 전달되는 데이터. -->
                    <tr>
                        <td>
                            <div class="mymessage-image-container">
                                <img src="/fourmen/uploads/${ messageDtoList[index].postDto.workImages[0].savedFileName }">
                            </div>
                        </td>
                        <td>
                            <div class="mymessage-title">
                                ${ messageDtoList[index].title }
                            </div>
                        </td>
                        <td>${ messageDtoList[index].priceOffered }</td>
                        <td>${ messageDtoList[index].sender }</td>
                        <td>
                            <c:if test="${ messageDtoList[index].postDto.isSold == 'Y' && (messageDtoList[index].postDto.buyerId ==  signedInUser
                                || messageDtoList[index].postDto.userId == signedInUser)}">
                                <span class="deal-completed">거래완료</span>
                            </c:if>
                            <c:if test="${ messageDtoList[index].postDto.isSold == 'Y' && (messageDtoList[index].postDto.buyerId !=  signedInUser
                                && messageDtoList[index].postDto.userId != signedInUser) }">
                                <span class="deal-sold-by-another">타인에 팔림</span>
                            </c:if>
                        </td>
                    </tr>
                    <div class="d-none mymessage-workid">${ messageDtoList[index].workId }</div>
                    <div class="d-none mymessage-id">${ messageDtoList[index].id }</div>             
                </c:forEach>
            </c:if>
            </tbody>
        </table>
    </section>
    
    
    <!-- 게시판 글 페이지네이션(pagination)-->
        <div>
            <nav aria-label="Page navigation">
                <ul class="pagination">
                    <li class="page-item">
                        <c:url var="firstPage" value="/mypage/mymessage/${ category }">
                            <c:param name="page">1</c:param>
                        </c:url>
                        <a class="page-link-img" href="${ firstPage }" aria-label="first page">
                            <img id="pagination-img" alt="first page" src="../../pagination/pagination01.png">
                        </a>
                    </li>
                    <li class="page-item">                        
                        <c:url var="previousPage" value="/mypage/mymessage/${ category }">
                            <c:param name="page">${ page - 1 }</c:param>
                        </c:url>
                        <c:if test="${ page gt 1 }">
                            <a class="page-link-img" href="${ previousPage }" aria-label="previous">
                                <img id="pagination-img" alt="previous page" src="../../pagination/pagination02.png">
                            </a>
                        </c:if>
                        <c:if test="${ page le 1 }">
                            <span class="page-link-img" aria-label="previous">
                                <img id="pagination-img" alt="previous page" src="../../pagination/pagination02.png">
                            </span>
                        </c:if>
                    </li>
                    <c:forEach begin="${ pagingDto.startPage }" end="${ pagingDto.endPage }" step="1" var="i">
                        <li class="page-item">
                            <c:url var="moveToPage" value="/mypage/mymessage/${ category }">
                                <c:param name="page">${ i }</c:param>
                            </c:url>
                            <a class="page-link" href="${ moveToPage }">${ i }</a>
                        </li>
                    </c:forEach>
                    
                    <li class="page-item">
                        <c:url var="nextPage" value="/mypage/mymessage/${ category }">
                            <c:param name="page"> ${ page + 1 }</c:param>
                        </c:url>
                        <c:if test="${ page lt pagingDto.endPage }">
                            <a class="page-link-img" href="${ nextPage }" aria-label="next">
                                <img id="pagination-img" alt="next page" src="../../pagination/pagination03.png">
                            </a>
                        </c:if>
                        <c:if test="${ page ge pagingDto.endPage }">
                            <span class="page-link-img" aria-label="next">
                                <img id="pagination-img" alt="next page" src="../../pagination/pagination03.png">                                
                            </span>
                        </c:if>
                    </li>
                    <li class="page-item">
                        <c:url var="lastPage" value="/mypage/mymessage/${ category }">
                            <c:param name="page">${ pagingDto.endPage }</c:param>
                        </c:url>
                        <a class="page-link-img" href="${ lastPage }" aria-label="last page">
                            <img id="pagination-img" alt="last page" src="../../pagination/pagination04.png">
                        </a>
                    </li>
                </ul>
            </nav>
        </div>
    </main>
    
    <!-- 푸터 파일 include -->
    <%@ include file="../fragments/footer.jspf" %>
    
    <script
        src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
        crossorigin="anonymous"></script>
    <script src="/fourmen/js/header.js"></script>
        
		
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
	 integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
	  crossorigin="anonymous"></script>
    
    
    <script src="https://cdn.jsdelivr.net/npm/axios@1.1.2/dist/axios.min.js"></script>
    <script src="/fourmen/js/mypage/mymessage.js"></script>  
    
  </body>
		
	</body>
</html>