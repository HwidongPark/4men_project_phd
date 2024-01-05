<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c"  uri="jakarta.tags.core"%>    

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

<!-- css 파일 적용 -->

<link rel="stylesheet" href="../css/header.css">
<link rel="stylesheet" href="../css/footer.css">
<link rel="stylesheet" href="../css/underheader.css">
<link rel="stylesheet" href="../css/forum-search-area.css">
<link rel="stylesheet" href="../css/forum-kategorie-area.css">
<link rel="stylesheet" href="../css/forum-create-post.css">

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
    <main>
    
    <!-- 게시판 카테고리(자유게시판, 문의게시판, Faq게시판, 공지게시판) -->
    <section role="kategorie" class="kategorie" style="border-bottom: 1.5px solid #D8D8D8;">
        <div class="forum-kategorie">
            <ul class="forum-kategorie-board-lists">
                <li class="forum-kategorie-board">
                    <a href="freeboard" class="category-button" onclick="change_category_style(event)">자유게시판</a>
                </li>
                <li class="forum-kategorie-board">
                    <a href="qnaboard" class="category-button" onclick="change_category_style(event)">QUALIFY</a>
                </li>
                <li class="forum-kategorie-board">
                    <a href="faqboard" class="category-button" onclick="change_category_style(event)">FAQ</a>
                </li>
                <li class="forum-kategorie-board">
                    <a href="noticeboard" class="category-button" onclick="change_category_style(event)">NOTICE</a>
                </li>
            </ul>
        </div>
    </section>
    
    
    <!-- 문의게시판 글 작성 -->
    <section style="padding: 3.5rem 15.5rem 2.5rem 15.5rem;">
        <div>
            <c:url var="qnaboardCreatePage" value="/forum/qnaboard-create" />
            <form id="qnaboard-create-form" action="/forum/qnaboard-create" method="post" enctype="multipart/form-data">
                <div id="qnaboard-create-title">
                    <input id="qna_title" name="qna_title" type="text" placeholder="제목을 입력하세요 (공백 포함 40자)" maxlength="40" onkeyup="return input_maxlength(this)" />
                </div>
                <div id="qnaboard-create-content">
                    <textarea id="qna_content" name="qna_content" placeholder="내용을 입력하세요 (공백 포함 1000자)" maxlength="1000" onkeyup="return textarea_maxlength(this)"></textarea>
                </div>
                <!-- 작성자 아이디는 로그인한 사용자 아이디로 + 보이지 않도록 설정 -->
                <div id="qnaboard-create-author" class="d-none">
                    <input type="text" name="user_id" value="${signedInUser}" readonly />
                </div>
            </form>
                <div id="qnaboard-create-forUnderbar"></div>
                <div id="qnaboard-create-button">
                    <button id="qnaboard-create" class="btn btn-outline-secondary" type="submit">완료</button>
                </div>
        </div>
    </section>
    
    </main>
    
    <!-- 푸터 파일 include -->
    <%@ include file="../fragments/footer.jspf" %>
    
    <script
        src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
        crossorigin="anonymous"></script>
    <script src="../js/header.js"></script>
    <script src="../js/forum/qnaboard-create.js"></script>
    <script src="../js/forum/forum-category-bold-style.js"></script>
    
</body>
</html>