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
<link rel="stylesheet" href="../css/forum-noticeboard-detail.css">

<!-- 헤더 파일 include -->
<%@ include file="../fragments/navigation.jspf"%>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

</head>

<!-- body 시작점 -->
<body>
    
    <!-- 웹페이지 상단 헤더 아래 부분 -->
    <div id="underheader-div">
        <div class="container" id="underheadrcontainer">
            <h2 class="commondesign">
                NOTICE
            </h2>
        </div>
    </div>
    
    <!-- main 시작점 -->
    <main class="gfont">
    
    <section role="noticeboard-view"> <!-- 게시글이 보이는 부분... -->
        <div class="noticeboard-view-detail"> <!-- 제목 / 작성정보 / 내용을 묶는 div -->
            <div class="noticeboard-view-modify-title"> <!-- 제목 -->
                <input autofocus id="noticeboard-view-modify-title" name="notice_title" autofocus type="text" maxlength="40" placeholder="제목을 입력하세요 (공백 포함 40자)" onkeyup="return input_maxlength(this)" value="${notice.notice_title}"/>
            </div>
            <div class="d-none"> <!-- 게시글 고유 아이디를 보이지 않게 가림 -->
                <input id="notice_id" name="notice_id" value="${notice.notice_id}">
            </div>
            <ul class="noticeboard-view-detail-info">
                <li>
                    <label class="noticeboard-view-detail-info-label">작성일</label>
                    <input id="noticeboard-view-detail-createdTime" value="${notice.notice_created_time}" type="text" readonly="readonly">
                </li>
                <li>
                    <label class="noticeboard-view-detail-info-label">작성자</label>
                    <input id="noticeboard-view-detail-userId"  value="${notice.userid}" type="text" readonly="readonly">
                </li>
                <li>
                    <label class="noticeboard-view-detail-info-label">조회</label>
                    <input id="noticeboard-view-detail-view" value="${notice.notice_view_count}" type="number" readonly="readonly">
                </li>
            </ul>
            
            <div class="noticeboard_md_del_btn">
                <!-- 작성자 아이디와 로그인 사용자 아이디가 같을 때만 버튼을 보여줌 -->
                <c:if test="${notice.userid eq signedInUser}">
                    <button id="btn-noticeboard-modify">수정완료</button> <!-- 수정 완료 버튼 -->
                    <button id="btn-noticeboard-delete">삭제</button> <!-- 삭제 버튼 -->
                </c:if>
            </div>
            <div> <!-- 내용 -->
                <textarea id="noticeboard-view-detail-content" name="noticeboard_content" class="noticeboard-view-detail-content" maxlength="1000" placeholder="내용을 입력하세요 (공백 포함 1000자)" onkeyup="return textarea_maxlength(this)">${notice.notice_content}</textarea>
            </div>
        </div>
        
        <div class="noticeboard-view-list-button">
            <button id="noticeboard-view-btnList" class="btn btn-outline-secondary" type="button">
                목록
            </button>
        </div>
    </section>
    
    </main>
    
    <!-- 푸터 파일 include -->
    <%@ include file="../fragments/footer.jspf" %>
    
    <script
        src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
        crossorigin="anonymous"></script>
    <script src="https://unpkg.com/axios/dist/axios.min.js"></script>
    <script src="../js/header.js"></script>
    <script src="../js/forum/noticeboard-modify.js"></script>
    <script>
        var len = $('#noticeboard-view-modify-title').val().length;
        $('#noticeboard-view-modify-title').focus();
        $('#noticeboard-view-modify-title')[0].setSelectionRange(len, len);
    </script>

</body>
</html>