<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c"  uri="jakarta.tags.core"%>    

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Artists Archive</title>
<link
    href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
    rel="stylesheet"
    integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN"
    crossorigin="anonymous">

<!-- css 파일 적용 -->
<link rel="stylesheet" href="../css/header.css">
<link rel="stylesheet" href="../css/footer.css">
<link rel="stylesheet" href="../css/forum-table.css">
<link rel="stylesheet" href="../css/underheader.css">
<link rel="stylesheet" href="../css/pagenation.css">
<link rel="stylesheet" href="../css/forum-search-area.css">
<link rel="stylesheet" href="../css/forum-kategorie-area.css">
<link rel="stylesheet" href="../css/forum-under-menubar.css">

</head>

<!-- 헤더 파일 include -->
<%@ include file="../fragments/navigation.jspf"%>

<!-- body 시작점 -->
<body>
    
    <!-- 웹페이지 상단 헤더 아래 부분 -->
    <div id="underheader-div">
        <div class="container" id="underheadrcontainer">
            <h2 class="commondesign">
                QUALIFY
            </h2>
        </div>
    </div>
    
    <!-- main 시작점 -->
    <main class="gfont">
    
    <!-- 게시판 카테고리(자유게시판, 문의게시판, Faq게시판, 공지게시판) -->
    <section role="kategorie" class="kategorie" style="border-bottom: 1.5px solid #D8D8D8;">
        <div class="forum-kategorie">
            <ul class="forum-kategorie-board-lists">
                <li class="forum-kategorie-board">
                    <a href="freeboard" class="category-button">자유게시판</a>
                </li>
                <li class="forum-kategorie-board">
                    <a href="qnaboard" class="category-button forum-kategori-current-selected">등업게시판</a>
                </li>
                <li class="forum-kategorie-board">
                    <a href="faqboard" class="category-button">FAQ</a>
                </li>
                <li class="forum-kategorie-board">
                    <a href="noticeboard" class="category-button">NOTICE</a>
                </li>
            </ul>
        </div>
    </section>
    
    <!-- 게시글 검색창 -->
    <section role="search" class="search">
        <div class="forum-top-area">
            <div class="forum-search-area">
                <c:url var="qnaboard_searchPage" value="qnaboard_search" />
                <form action="${qnaboard_searchPage}" method="get">
                <div class="forum-search-select-area">
                    <select class="forum-select-box" name="category">
                        <option class="forum-select-option" value="qna_all">전체</option>
                        <option class="forum-select-option" value="qna_title">제목</option>
                        <option class="forum-select-option" value="qna_author">작성자</option>
                        <option class="forum-select-option" value="qna_content">내용</option>
                    </select>
                </div>
                <div class="forum-search-form-area">
                    <input id="forum-search-input" name="keyword" autocomplete="on" placeholder="검색어를 입력하세요." type="text" maxlength="500">
                </div>

                <div class="forum-search-btn-area">
                    <button class="forum-search-btn" type="submit">
                        <img id="forum-search-btn-img" alt="검색버튼" src="../icon/search01.svg">
                    </button>
                </div>
                </form>
            </div>
        </div>
    </section>
    
    <!-- board-list-content 게시판 글 리스트 테이블 -->
    <section class="qnaboard-list" style="padding: 3.5rem 18.5rem 0rem 18.5rem;">
        <table class="table">
            <colgroup>
            
                <col style="width: 10px;">
                <col style="width: auto;">
                <col style="width: 10%;">
                <col style="width: 15%;">
                <col style="width: 10%;">
            </colgroup>
            <thead>
            <tr>
                <!-- <th>번호</th> -->
                <th></th>
                <th>제목</th>
                <th>작성자</th>
                <th>작성일</th>
                <th>조회</th>
            </tr>
            </thead>
            <tbody class="table-group-divider">
            <c:forEach var="q" items="${qnaboard_posts}">
            <!-- var: 변수(리스트 값을 저장) / items: 리스트 -->
            <!-- PostController.java에서 전달된 데이터 사용 (리스트의 이름이 items에 들어가야 함)
            -> model.addAttribute("freeboard_posts", list); //-> 뷰에 전달되는 데이터. -->
                <tr>
                    <td class="d-none">${q.qna_id}</td>
                    <td></td>
                    <td id="table-td" style="text-align: left; padding-left: 2rem">
                        <c:url var="qnaboard_qnaDetailPage" value="/forum/qnaboard-detail">
                            <c:param name="qna_id" value="${q.qna_id}" />
                        </c:url>
                        <a href="${qnaboard_qnaDetailPage}">${q.qna_title}</a>
                    </td>
                    <td>${q.userid}</td>
                    <td>${q.qna_created_time}</td>
                    <td>${q.qna_view_count}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </section>
    
    <!-- 새 글 작성 내비게이션(HOME / MAP / NEW POST) -->
    <div class="new-post-div">
        <nav class="new-post-nav">
            <ul class="new-post-ul">
                <li id="new-post-li-one" class="new-post-li">
                    <c:url var="homePage" value="/" />
                    <a href="${homePage}">HOME</a>
                </li>
                <li id="new-post-li-two" class="new-post-li">
                    <c:url var="siteMapPage" value="#" />
                    <a href="${siteMapPage}">MAP</a>    
                </li>
                <c:if test="${signedInUser ne null}">
                <li id="new-post-li-three" class="new-post-li">
                    <c:url var="qnaboardCreatePage" value="/forum/qnaboard-create" />
                    <a href="${qnaboardCreatePage}">NEW POST</a>
                </li>
                </c:if>
            </ul>
        </nav>
    </div>
 
        <!-- 게시판 글 페이지네이션(pagination)-->
        <div>
            <nav aria-label="Page navigation">
                <ul class="pagination">
                    <!-- 처음 페이지 -->
                    <li class="page-item">
                        <c:url var="firstPage" value="${ servletPath }">
                            <c:param name="page" value="1" />
                            <c:param name="category" value="${ param.category }"/>
                            <c:param name="keyword" value="${ param.keyword }"/>
                        </c:url>
                        <a class="page-link-img" href="${ firstPage }">
                            <img id="pagination-img" alt="first page" src="/fourmen/pagination/pagination01.png">
                        </a>
                    </li>
                    <!-- 이전 페이지 -->
                    <li class="page-item">
                        <c:url var="prevPage" value="${ servletPath }">
                            <c:param name="page" value="${ page - 1 }" />
                            <c:param name="category" value="${ param.category }"/>
                            <c:param name="keyword" value="${ param.keyword }"/>
                        </c:url>
                        <c:choose>
                            <c:when test="${ page le 1 }">
                                <span class="page-link-img" aria-label="previous"> 
                                    <img id="pagination-img" alt="previous page" src="/fourmen/pagination/pagination02.png">
                                </span>
                            </c:when>
                            <c:otherwise>
                                <a class="page-link-img" href="${ prevPage }" aria-label="previous"> 
                                    <img id="pagination-img" alt="previous page" src="/fourmen/pagination/pagination02.png">
                                </a>
                            </c:otherwise>
                        </c:choose>

                    </li>

                    <!-- 필요한 만큼만 페이지 보여줌 -->
                    <c:forEach begin="${pagingDto.startPage}" end="${pagingDto.endPage}" var="idx">
                        <c:url var="toPage" value="${ servletPath }">
                            <c:param name="page" value="${ idx }" />
                            <c:param name="category" value="${ param.category }"/>
                            <c:param name="keyword" value="${ param.keyword }"/>
                        </c:url>
                        <li class="page-item">
                            <a class="page-link" href="${ toPage }">
                                <c:if test="${ page == idx }">
                                    <span class="pagenation-current-page">
                                </c:if>
                                ${idx}
                                <c:if test="${ page == idx }">
                                    <span class="pagenation-current-page">
                                </c:if>
                            </a>
                        </li>
                    </c:forEach>
                    
                    <!-- 다음 페이지 -->
                    <li class="page-item">
                        <c:url var="nextPage" value="${ servletPath }">
                            <c:param name="page" value="${ page + 1 }" />
                            <c:param name="category" value="${ param.category }"/>
                            <c:param name="keyword" value="${ param.keyword }"/>
                        </c:url>
                        <c:choose>
                            <c:when test="${ page ge pagingDto.totNumPages }">
                                <span class="page-link-img" aria-label="next"> 
                                    <img id="pagination-img" alt="next page" src="/fourmen/pagination/pagination03.png">
                                </span>
                            </c:when>
                            <c:otherwise>
                                <a class="page-link-img" href="${ nextPage }" aria-label="next"> 
                                    <img id="pagination-img" alt="next page" src="/fourmen/pagination/pagination03.png">
                                </a>
                            </c:otherwise>
                        </c:choose>

                    </li>
                    <!-- 마지막 페이지 -->
                    <li class="page-item">
                        <c:url var="lastPage" value="${ servletPath }">
                            <c:param name="page" value="${ pagingDto.totNumPages }" />
                            <c:param name="category" value="${ param.category }"/>
                            <c:param name="keyword" value="${ param.keyword }"/>
                        </c:url>
                        <a class="page-link-img" href="${ lastPage }">
                            <img id="pagination-img" alt="last page" src="/fourmen/pagination/pagination04.png">
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
    <script src="../js/header.js"></script>

</body>
</html>