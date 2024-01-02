<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ADMIN</title>
<style>
.btndelete{
    font-size: 13px;
    margin: 10px;
    }
        #btnDiv {
    	text-align: right;
    	width: 100%;

    }
</style>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN"
	crossorigin="anonymous">

<link rel="stylesheet" href="css/forum-table.css">
	<link rel="stylesheet" href="css/underheader.css">
	<link rel="stylesheet" href="css/pagenation.css">	
	<link rel="stylesheet" href="css/forum-kategorie-area.css">
	<link rel="stylesheet" href="css/forum-search-area.css">
	
	 <link rel="stylesheet" href="/fourmen/css/header.css">
    <link rel="stylesheet" href="/fourmen/css/market-board.css">
    <link rel="stylesheet" href="/fourmen/css/footer.css">
    <link rel="stylesheet"
        href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200" />
    <link rel="stylesheet"
        href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200" />
    <link rel="stylesheet" href="/fourmen/css/pagenation.css">
    
    <script src="https://kit.fontawesome.com/5b5cfeea7f.js" crossorigin="anonymous"></script>
</head>

<%@ include file="fragments/navigation.jspf"%>
<body>
			 
	<!-- 웹페이지 상단 헤더 아래 부분 -->
    <div id="underheader-div">
        <div class="container" id="underheadrcontainer">
            <h2 class="commondesign">
                MARKET ADMIN
            </h2>
        </div>
    </div>
		
		
			  <!-- 게시판 카테고리(자유게시판, 후기게시판, 질문게시판) -->
    <section role="kategorie" class="kategorie" style="border-bottom: 1.5px solid #D8D8D8;">
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
                   <li class="forum-kategorie-board">
                    <a href="forumadmin">게시판 관리</a>
                </li>
            </ul>
        </div>
    </section>
	
	
	    <section role="search" class="search-container">
        <c:url var="searchPage" value="/marketadmin/search"/>
        <form id="search-form" action="${ searchPage }">
            <div class="search-detail-container">
            
                <br>

                <!-- 가격대 설정 -->
                <div class="price-range-container">
                    <label for="low-price-border">가격대</label>
                    <input name="minPrice" class="price-range" type="number">원 ~ 
                    <input type="number" name="maxPrice" class="price-range">원
                </div>
            </div>
            
            <div class="market-search-category-keyword">
                <select name="searchCategory">
                    <option value="title">제목</option>
                    <option value="content">내용</option>
                    <option value="userId">작성자</option>
                    <option value="titleContent">제목 + 내용</option>
                    <option value="titleContentUserId">제목 + 내용 + 작성자</option>
                </select>
                <!-- 검색어 input -->
                <input maxlength="30" class="search-keyword" name="keyword">
                <button id="btnSearch">
                    <svg xmlns="http://www.w3.org/2000/svg" width="16"
                        height="16" fill="currentColor" class="bi bi-search"
                        viewBox="0 0 16 16">
                            <path
                            d="M11.742 10.344a6.5 6.5 0 1 0-1.397 1.398h-.001c.03.04.062.078.098.115l3.85 3.85a1 1 0 0 0 1.415-1.414l-3.85-3.85a1.007 1.007 0 0 0-.115-.1zM12 6.5a5.5 5.5 0 1 1-11 0 5.5 5.5 0 0 1 11 0" />
                        </svg>
                </button>
            </div>
        </form>
    </section>
	<main>
        
        <div class="list-container">
            <!-- JSTL의 for문으로 인기글 8개만 보여줌 -->
            <c:if test="${ not empty popularMarketPosts }">
                <c:forEach var="popularMarketPost" items="${ popularMarketPosts }">
                    <div class="listed-item-container">
                        <div class="item-image-container">
                            <div class="image-size-controller">
                                <c:url var="marketPostLink" value="/market/detail">
                                    <c:param name="workid" value="${ popularMarketPost.workId }" />
                                </c:url>
                                <a href="${ marketPostLink }">
                                    <img src="/fourmen/uploads/${ popularMarketPost.workImages.get(0).savedFileName }">
                                </a>
                            </div>
                        </div>
                        
                        <!-- 아이템 제목, 가격, 업로드 일자 등 -->
                        <div class="item-info-container">
                            <!-- 제목, 조회수, 좋아요 포함 -->
                            <div class="market-item-title my-2">
                            <!-- TODO: 여기 못오고 있음 -->
                                <div>
                                <c:url var="marketadmindelete" value="/marketadmin/delete"/>
                                <form action="${marketadmindelete}" method="get" id="market-admin-delete">
                                    <a href="${ marketPostLink }"><b>${ popularMarketPost.title }</b></a>
                                   </form>
                                </div>
                            </div>

                            <!-- 가격 -->
                            <div class="market-price-views-likes-container">
                                <div class="market-item-price">
                                    <fmt:formatNumber type="number" maxFractionDigits="2" value="${popularMarketPost.price }" />${popularMarketPost.price}원
                                </div>
                                <div class="market-views-and-likes">
                                      <!-- 조회수 -->
                                    <span class="material-symbols-outlined">
                                        visibility
                                    </span>
                                    ${ popularMarketPost.views }
                                    <!-- 좋아요(찜) -->
                                    <c:set var="isWishListed" value="false" />
                                    <c:forEach items="${ userWishList }" var="wishList">
                                        <c:if test="${ wishList.workId eq popularMarketPost.workId }">
                                            <c:set var="isWishListed" value="true" />
                                        </c:if>
                                    </c:forEach>
                                    
                                    <c:if test="${ isWishListed }">
                                        <i class="fa-solid fa-heart  market-filled-heart"></i>                             
                                    </c:if>
                                    <c:if test="${ !isWishListed }">
                                        <i class="fa-regular fa-heart market-empty-heart"></i> 
                                    </c:if>
                                    ${ popularMarketPost.likes }
                                    
                                </div>
                                                          
                            </div>
                           
                            <c:if
                                test="${ not empty popularMarketPost.isSold }">
                                <div class="is-sold">
                                    <b>거래 완료</b>
                                </div>
                                
                            </c:if>
                            <div id=btnDiv><button id="btndelete" data-exname="${popularMarketPost.title}" class="btndelete btn btn-danger">삭제</button></div>
                        </div>
                    </div>
                    
                </c:forEach>
            </c:if>
        </div>

        <!-- 인기 목록 끝 최신목록 시작 -->
	
  
    </main>
    
    
    <!-- 게시판 글 페이지네이션(pagination)-->
   <div>
        <nav aria-label="Page navigation">            
            <ul class="pagination">
                <!-- 이전, 처음 페이지 -->
                <li class="page-item">
                    <c:url var="firstPage" value="${ servletPath }" />
                    <a class="page-link-img" href="${ firstPage }" aria-label="first page">
                        <img id="pagination-img" alt="first page" src="/fourmen/pagination/pagination01.png">
                    </a>
                </li>
                <c:choose>
                    <c:when  test="${ page le 1 }">
                        <li class="page-item">
                            <span class="page-link-img" aria-label="previous">
                                <img id="pagination-img" alt="previous page" src="/fourmen/pagination/pagination02.png">
                            </span>
                        </li>
                    </c:when>
                    <c:otherwise>
                        <li class="page-item">
                            <c:url var="prevPage" value="${ servletPath }">
                                <c:param name="page" value="${ page - 1 }"/>
                            </c:url>
                            <a class="page-link-img" href="${prevPage }" aria-label="previous">
                                <img id="pagination-img" alt="previous page" src="/fourmen/pagination/pagination02.png">
                            </a>
                        </li>
                    </c:otherwise>
                </c:choose>
                
                <!-- 필요한만큼만 페이지 보여줌 -->
                <c:forEach var="pageNum" begin="${ pagingDto.startPage }" end="${ pagingDto.endPage }" step="1">
                    <li class="page-item">
                        <c:url var="moveToPage" value="${ servletPath }">
                            <c:param name="page" value="${ pageNum }"/>
                        </c:url>
                        <a class="page-link" href="${ moveToPage }">${ pageNum }</a>
                    </li>
                </c:forEach>
                
                <!-- 다음 마지막 페이지 -->
                <c:choose>
                    <c:when test="${ page ge pagingDto.totNumPages }">
                        <li class="page-item">
                            <span class="page-link-img" aria-label="next">
                                <img id="pagination-img" alt="next page" src="/fourmen/pagination/pagination03.png">
                            </span>
                        </li>
                    </c:when>
                    <c:otherwise>
                        <c:url var="nextPage" value="${ servletPath }">
                            <c:param name="page" value="${ page + 1 }"></c:param>
                        </c:url>
                        <a class="page-link-img" href="${ nextPage }" aria-label="next">
                            <img id="pagination-img" alt="next page" src="/fourmen/pagination/pagination03.png">
                        </a>
                    </c:otherwise>
                </c:choose>
                <c:url var="lastPage" value="${ servletPath }">
                    <c:param name="page" value="${ pagingDto.totNumPages }"/>
                </c:url>
                <li class="page-item">
                    <a class="page-link-img" href="${ lastPage }" aria-label="last page">
                        <img id="pagination-img" alt="last page" src="/fourmen/pagination/pagination04.png">
                    </a>
                </li>
            </ul>
        </nav>
    </div>
    
    

    <%@ include file="fragments/footer.jspf"%>

    <script
        src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
        crossorigin="anonymous"></script>
	<script src="/fourmen/js/marketadmin.js"></script>
    <script src="/fourmen/js/market/market-board.js"></script>

    <script src="/fourmen/js/header.js"></script>
</body>
</html>