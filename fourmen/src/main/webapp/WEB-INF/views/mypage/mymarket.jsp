<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Fourmen</title>
        
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" 
        integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN"
         crossorigin="anonymous">
        <link rel="stylesheet"
            href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200" />
        <link rel="stylesheet"
            href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200" />
        <link rel="stylesheet" href="../css/header.css">
        <link rel="stylesheet" href="../css/footer.css">
        <link rel="stylesheet" href="../css/forum-table.css">
        <link rel="stylesheet" href="../css/underheader.css">
        <link rel="stylesheet" href="../css/pagenation.css">    
        <link rel="stylesheet" href="../css/forum-kategorie-area.css">
        <link rel="stylesheet" href="/fourmen/css/market-board.css">
        
        
    </head>
    <body>
    <%@ include file="../fragments/navigation.jspf"%>
            <!-- 웹페이지 상단 헤더 아래 부분 -->
    <div id="underheader-div">
        <div class="container" id="underheadrcontainer">
            <h2 class="commondesign">
                My Page
            </h2>
        </div>
    </div>
    
    <main>
        <!-- 게시판 카테고리(자유게시판, 후기게시판, 질문게시판) -->
        <section role="kategorie" class="kategorie" style="border-bottom: 1.5px solid #D8D8D8;">
            <div class="forum-kategorie">
                <ul class="forum-kategorie-board-lists">
                    <li class="forum-kategorie-board">
                        <a href="/fourmen/mypage/myinfo">내 정보 보기/수정</a>
                    </li>
                    <li class="forum-kategorie-board">
                        <a href="/fourmen/mypage/mywork">내 작품 보기</a>
                    </li>
                    <li class="forum-kategorie-board">
                        <b><a href="/fourmen/mypage/mymarket">마켓 작성글 보기</a></b>
                    </li>
                    <li class="forum-kategorie-board">
                        <a href="../exhibitionadmin">게시판 작성글 보기</a>
                    </li>
                </ul>
            </div>
        </section>
        
        <!-- 내 마켓 작성글들을 보여줌 -->
        <div class="list-container my-5">
            <!-- JSTL로 포스트 적용... -->
            <c:if test="${ not empty marketPosts }">
                <c:forEach var="marketPost" items="${ marketPosts }">
                    <div class="listed-item-container">
                        <div class="item-image-container">
                            <div class="image-size-controller">
                                <c:url var="marketPostLink"
                                    value="/market/detail">
                                    <c:param name="workid" value="${ marketPost.workId }" />
                                </c:url>
                                <a href="${ marketPostLink }">
                                    <img src="/fourmen/uploads/${ marketPost.workImages.get(0).savedFileName }">
                                </a>
                            </div>
                        </div>
                        <!-- 아이템 제목, 가격, 업로드 일자 등 -->
                        <div class="item-info-container">
                            <!-- 제목, 조회수, 좋아요 포함 -->
                            <div class="market-item-title my-2">
                                <div>
                                    <a href="${ marketPostLink }"><b>${ marketPost.title }</b></a>
                                </div>
                            </div>

                            <!-- 가격 -->
                            <div class="market-price-views-likes-container">
                                <div class="market-item-price">
                                    <fmt:formatNumber type="number" maxFractionDigits="2" value="${ marketPost.price }" />원
                                </div>
                                <div class="market-views-and-likes">
                                      <!-- 조회수 -->
                                    <span class="material-symbols-outlined">
                                        visibility
                                    </span>
                                    ${ marketPost.views }
                                        <span class="material-symbols-outlined">
                                            favorite
                                        </span>
                                    ${ marketPost.likes }
                                </div>            
                            </div>
                            <c:if
                                test="${ marketPost.isSold eq 'Y' }">
                                <div class="is-sold">
                                    <b>거래 완료</b>
                                </div>
                            </c:if>
                        </div>
                    </div>
                </c:forEach>
            </c:if>
        </div>
        
        <!-- 게시판 글 페이지네이션(pagination)-->
        <div>
            <nav aria-label="Page navigation">            
                <ul class="pagination">
                    <!-- 이전, 처음 페이지 -->
                    <li class="page-item">
                        <c:url var="firstPage" value="${ servletPath }">
                            <c:param name="page" value="1"/>
                        </c:url>
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
                            <a class="page-link" href="${ moveToPage }">
                                <c:if test="${ pageNum == page }">
                                    <span class="pagenation-current-page">
                                </c:if>
                                ${ pageNum }
                                <c:if test="${ pageNum == page }">
                                    </span>
                                </c:if>
                            </a>
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
       
         
    </main>
    
    <%@ include file="../fragments/footer.jspf" %>
        
        
         <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
          integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
           crossorigin="anonymous"></script>
        <script src="../js/header.js"></script>
        <script src="../js/mypage/myinfo.js"></script>
    </body>
    </html>