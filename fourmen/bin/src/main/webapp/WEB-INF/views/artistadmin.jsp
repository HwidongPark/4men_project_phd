<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ADMIN</title>
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

div#works{
	font-family: 'NEXON Lv1 Gothic OTF';
}

div#artist_container {
	margin: 0 auto;
	display: grid;
	grid-template-columns: repeat(auto-fit, minmax(240px, 1fr));
	grid-column-gap: 30px;
	grid-row-gap: 40px;
}

div#artist {
	grid-auto-rows: 200px;
}

.card .card-img {
    width: 100%; /* 이미지 너비 100%로 설정 */
    height: 100%; /* 이미지 높이 100%로 설정 */
    object-fit: cover; /* 이미지 비율 유지하면서 카드에 맞춰 잘립니다 */
}

.card .card-img-overlay {
    visibility: hidden;
    opacity: 0;
    transition: visibility 0s, opacity 0.5s ease; /* 효과 부드럽게 전환 */
    position: absolute;
    top: 0;
    left: 0;
    width: 100%;
    height: 100%;
    background-color: rgba(0, 0, 0, 0.5); /* 반투명한 검은 배경 */
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
}

/* 마우스 호버 시 오버레이 나타남 */
.card:hover .card-img-overlay {
    visibility: visible;
    opacity: 1;
}

.card .card-img-overlay h5 {
    color: white; /* 텍스트의 색상을 흰색으로 변경 */
}

#works{
	margin-top: 50px;
}
</style>	
	
</head>

<%@ include file="fragments/navigation.jspf"%>
<body>
			 
	<!-- 웹페이지 상단 헤더 아래 부분 -->
    <div id="underheader-div">
        <div class="container" id="underheadrcontainer">
            <h2 class="commondesign">
                ARTISTS ADMIN
            </h2>
        </div>
    </div>
		
		
			  <!-- 게시판 카테고리(자유게시판, 후기게시판, 질문게시판) -->
    <section role="kategorie" class="kategorie" style="border-bottom: 1.5px solid #D8D8D8;">
        <div class="forum-kategorie">
            <ul class="forum-kategorie-board-lists gfont">
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
	
	<div id="works" class="mb-5 container-fluid w-75">
	<div id = "artist_container">
			<c:forEach var="a" items="${artist}" varStatus = "loop">
				<c:url var="artist_details" value="/artist/artist_details">
					<c:param name="userid" value="${a.userid}"/>
				</c:url>
				
				<a href="${artist_details}">
					<div class="card text-bg-light">
						<c:forEach var="img" items ="${artistImg}">
							<c:if test="${a.userid eq img.userid}">
								<img src="images/char/${img.artist_s_img}" onerror="this.src='images/char/default_user.png'" class="card-img" alt="test_user"/>
							</c:if>
						</c:forEach>
								
						<div class="card-img-overlay">
							<h5 class="card-title fs-2">${a.nickname}</h5>
						</div>
					</div>
				</a>
			</c:forEach>
		</div>
	</div>	
	
	
    <%@ include file="fragments/footer.jspf" %>

    <script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
		crossorigin="anonymous"></script>
		
		<script src='js/main.js'></script>
		<script src="/fourmen/js/header.js"></script>
</body>
</html>