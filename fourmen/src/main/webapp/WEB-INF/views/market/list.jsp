<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Fourmen</title>


<link
    href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
    rel="stylesheet"
    integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN"
    crossorigin="anonymous">

<link rel="stylesheet" href="/fourmen/css/header.css">
<link rel="stylesheet" href="/fourmen/css/market-board.css">
<link rel="stylesheet" href="/fourmen/css/footer.css">
<link rel="stylesheet"
    href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200" />
<link rel="stylesheet"
    href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200" />
<link rel="stylesheet" href="/fourmen/css/pagenation.css">    

</head>

<body>
    <%@include file="../fragments/navigation.jspf"%>

    <section role="search" class="search-container">
        <c:url var="searchPage" value="/market/search"/>
        <form id="search-form" action="${ searchPage }">
            <div class="search-detail-container">
                <!-- 검색 카테고리 내역 -->
                상세 검색:<br> <input type="checkbox" id="oriental"
                    name="category"> <label for="oriental">동양화</label>
                <input type="checkbox" id="western" name="category">
                <label for="western">서양화</label> <input type="checkbox"
                    id="portrait" name="category"> <label
                    for="portrait">초상화</label> <input type="checkbox"
                    id="statue" name="category"> <label
                    for="statue">조각</label> <input type="checkbox"
                    id="modern-art" name="category"> <label
                    for="modern-art">현대미술</label> <input type="checkbox"
                    id="oriental" class="expand" name="category">
                <label for="oriental" class="expand">동양화</label> <input
                    type="checkbox" id="western" class="expand"
                    name="category"> <label for="western"
                    class="expand">서양화</label> <input type="checkbox"
                    id="portrait" class="expand" name="category">
                <label for="portrait" class="expand">초상화</label> <input
                    type="checkbox" id="statue" class="expand"
                    name="category"> <label for="statue"
                    class="expand">조각</label> <input type="checkbox"
                    id="modern-art" class="expand" name="category">
                <label for="modern-art" class="expand">현대미술</label>

                <!-- 버튼 누르면 전체 카테고리 내역 확장해서 보여줌 -->
                <button class="search-form-btn" type="button">
                    <svg xmlns="http://www.w3.org/2000/svg" width="16"
                        height="16" fill="currentColor"
                        class="bi bi-plus-lg" viewBox="0 0 16 16">
                          <path fill-rule="evenodd"
                            d="M8 2a.5.5 0 0 1 .5.5v5h5a.5.5 0 0 1 0 1h-5v5a.5.5 0 0 1-1 0v-5h-5a.5.5 0 0 1 0-1h5v-5A.5.5 0 0 1 8 2" />
                        </svg>
                </button>
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
                <input class="search-keyword" type="text" name="keyword">
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
        <!-- 글 작성 버튼 -->
        <div class="market-create-btn-container">
            <c:url var="createMarketPost" value="/market/create" />
            <button class="btn btn-outline-success">글 작성</button>
        </div>
        
        <!-- 게시판 -->
        <div class="list-item-category-title">
            <h2>
                ${ pageTitle }
            </h2>
        </div>

        <div class="list-container">
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
                                    <fmt:formatNumber type="number" maxFractionDigits="2" value="${marketPost.price }" />원
                                </div>
                                <div class="market-views-and-likes">
                                      <!-- 조회수 -->
                                    <span class="material-symbols-outlined">
                                        visibility
                                    </span>
                                    ${ marketPost.views }
                                    <!-- 좋아요(찜) -->
                                    <span class="material-symbols-outlined">
                                        favorite
                                    </span>
                                    ${ marketPost.likes }
                                </div>                                
                            </div>
                            <c:if
                                test="${ not empty marketPost.isSold }">
                                <div class="is-sold">
                                    <b>거래 완료</b>
                                </div>
                            </c:if>
                        </div>
                    </div>
                </c:forEach>
            </c:if>
        </div>
    </main>
    
    <!-- 게시판 글 페이지네이션(pagination)-->
    <div>
        <nav aria-label="Page navigation">            
            <ul class="pagination">
                <!-- 이전, 처음 페이지 -->
                <li class="page-item">
                    <c:url var="firstPage" value="${ servletPath }">
                        <c:param name="page" value="1"/>
                        <c:param name="searchCategory" value="${ param.searchCategory }"/>
                        <c:param name="keyword" value="${ param.keyword }"/>
                        <c:param name="minPrice" value="${ param.minPrice }" />
                        <c:param name="maxPrice" value="${ param.maxPrice }"/>
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
                                <c:param name="searchCategory" value="${ param.searchCategory }"/>
                                <c:param name="keyword" value="${ param.keyword }"/>
                                <c:param name="minPrice" value="${ param.minPrice }" />
                                <c:param name="maxPrice" value="${ param.maxPrice }"/>
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
                            <c:param name="searchCategory" value="${ param.searchCategory }"/>
                            <c:param name="keyword" value="${ param.keyword }"/>
                            <c:param name="minPrice" value="${ param.minPrice }" />
                            <c:param name="maxPrice" value="${ param.maxPrice }"/>
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
                            <c:param name="searchCategory" value="${ param.searchCategory }"/>
                            <c:param name="keyword" value="${ param.keyword }"/>
                            <c:param name="minPrice" value="${ param.minPrice }" />
                            <c:param name="maxPrice" value="${ param.maxPrice }"/>
                        </c:url>
                        <a class="page-link-img" href="${ nextPage }" aria-label="next">
                            <img id="pagination-img" alt="next page" src="/fourmen/pagination/pagination03.png">
                        </a>
                    </c:otherwise>
                </c:choose>
                <c:url var="lastPage" value="${ servletPath }">
                    <c:param name="page" value="${ pagingDto.totNumPages }"/>
                    <c:param name="searchCategory" value="${ param.searchCategory }"/>
                    <c:param name="keyword" value="${ param.keyword }"/>
                    <c:param name="minPrice" value="${ param.minPrice }" />
                    <c:param name="maxPrice" value="${ param.maxPrice }"/>
                </c:url>
                <li class="page-item">
                    <a class="page-link-img" href="${ lastPage }" aria-label="last page">
                        <img id="pagination-img" alt="last page" src="/fourmen/pagination/pagination04.png">
                    </a>
                </li>
            </ul>
        </nav>
    </div>
    
    
    
    <%@ include file="../fragments/footer.jspf"%>

    <script
        src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
        crossorigin="anonymous"></script>

    <script src="/fourmen/js/market/market-board.js"></script>

    <script src="/fourmen/js/header.js"></script>
</body>

</body>
</html>