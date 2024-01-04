<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Fourmen</title>
        
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" 
        integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN"
         crossorigin="anonymous">
        <link rel="stylesheet" href="../css/header.css">
        <link rel="stylesheet" href="../css/footer.css">
        <link rel="stylesheet" href="../css/forum-table.css">
        <link rel="stylesheet" href="../css/underheader.css">
        <link rel="stylesheet" href="../css/pagenation.css">    
        <link rel="stylesheet" href="../css/forum-kategorie-area.css">
        <link rel="stylesheet" href="../css/mypage-myinfo.css">
        
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
                    <b><a href="/fourmen/mypage/mywork">내 작품 보기</a></b>
                </li>
                <li class="forum-kategorie-board">
                    <a href="/fourmen/mypage/mymarket">마켓 작성글 보기</a>
                </li>
                <li class="forum-kategorie-board">
                    <a href="#">게시판 작성글 보기</a>
                </li>
            </ul>
        </div>
    </section>
    
    <!-- 페이지 하단 작품 리스트 -->
    
    <section class="d-flex justify-content-center my-4">
        <div class="w-75">
            <div class="mt-4">
                <table class="table table-striped fs-5">
                    <thead>
                        <tr>
                            <th class="col-2 d-none" >작품번호</th>
                            <th class="col-7" >작품제목</th>
                            <th class="col-2" >등록일자</th>
                        </tr>
                    </thead>
                    <tbody class="table-group-divider">
                        <c:forEach var="w" items="${worksList}">
                            <tr>
                                <th class="d-none" scope="row">${w.worksid}</th>
                                <td>
                                    <c:url var="worksPage" value="/artist/artist_works">
                                        <c:param name="worksid" value="${w.worksid}" />
                                    </c:url>
                                    <a href="${worksPage}">${w.title}</a>
                                </td>
                                <td>${w.getFormattedCreatedDate()}</td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>        
            </div>
        <div>
    </section>
    
    </main>
    
        <%@ include file="../fragments/footer.jspf" %>
        
        
         <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
          integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
           crossorigin="anonymous"></script>
        <script src="../js/header.js"></script>
        <script src="../js/mypage/myinfo.js"></script>
    </body>
    </html>