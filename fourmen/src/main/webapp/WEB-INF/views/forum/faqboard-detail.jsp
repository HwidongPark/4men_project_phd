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
<link rel="stylesheet" href="../css/forum-faqboard-detail.css">

<!-- 헤더 파일 include -->
<%@ include file="../fragments/navigation.jspf"%>

</head>

<!-- body 시작점 -->
<body>
    
    <!-- 웹페이지 상단 헤더 아래 부분 -->
    <div id="underheader-div">
        <div class="container" id="underheadrcontainer">
            <h2 class="commondesign">
                FAQ
            </h2>
        </div>
    </div>
    
    <!-- main 시작점 -->
    <main class="gfont">
    
    <section role="faqboard-view"> <!-- 게시글이 보이는 부분... -->
        <div class="faqboard-view-detail"> <!-- 제목 / 작성정보 / 내용을 묶는 div -->
            <div class="faqboard-view-detail-title"> <!-- 제목 -->
                ${faq.faq_title}
            </div>
            <div class="d-none"> <!-- 게시글 고유 아이디를 보이지 않게 가림 -->
                <input id="faq_id" name="faq_id" value="${faq.faq_id}">
            </div>
            <ul class="faqboard-view-detail-info">
                <li>
                    <label class="faqboard-view-detail-info-label">작성일</label>
                    <input id="faqboard-view-detail-createdTime" value="${faq.faq_created_time}" type="text" readonly="readonly">
                </li>
                <li>
                    <label class="faqboard-view-detail-info-label">작성자</label>
                    <input id="faqboard-view-detail-userId"  value="${faq.userid}" type="text" readonly="readonly">
                </li>
                <li>
                    <label class="faqboard-view-detail-info-label">조회</label>
                    <input id="faqboard-view-detail-view" value="${faq.faq_view_count}" type="number" readonly="readonly">
                </li>
            </ul>
            <div class="faqboard_md_del_btn">
                <!-- 작성자 아이디와 로그인 사용자 아이디가 같을 때만 수정 버튼을 보여줌 -->
                <c:if test="${faq.userid eq signedInUser}">
                    <c:url var="postModifyPage" value="/forum/faqboard-modify">
                        <c:param name="faq_id" value="${faq.faq_id}" />
                    </c:url>
                    <a href="${postModifyPage}" id="faqboard-detail-modify">수정</a> <!-- 수정 버튼 -->
                </c:if>
                <!-- 작성자 아이디와 로그인 사용자 아이디가 같을 때 그리고 관리자(admin)만 삭제 버튼을 보여줌 -->
                <c:if test="${faq.userid eq signedInUser || user.grade eq '관리자'}">
                    <button id="faqboard-detail-delete" type="button">삭제</button> <!-- 삭제 버튼 -->
                </c:if>
            </div>
            <div> <!-- 내용 -->
                <textarea id="faqboard-view-detail-content" class="faqboard-view-detail-content" readonly="readonly">${faq.faq_content}</textarea>
            </div>
        </div>
        <div class="faqboard-view-list-button">
            <button id="faqboard-view-btnList" class="btn btn-outline-secondary" type="button">
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
    <script src="../js/header.js"></script>
    <script src="../js/forum/faqboard-detail.js"></script>

</body>
</html>