<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head>
<title>FOURMEN</title>
<meta charset="Artists Archive">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN"
	crossorigin="anonymous">
<link rel="stylesheet" href="../css/header.css">
<link rel="stylesheet" href="../css/footer.css">
<link rel="stylesheet" href="../css/underheader.css">

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



@media (max-width: 1200px) {
    .row-g-0-reverse {
        flex-direction: column-reverse;
    }

    .col-md-6 {
        flex: 0 0 100%;
        max-width: 100%;
    }
}
.worksnum{
	text-decoration: none;
	color: black;
}

</style>

</head>
<body>
	<!-- 헤더 파일 include -->
	<%@ include file="../fragments/navigation.jspf"%>

    <!-- 웹페이지 상단 헤더 아래 부분 -->
    <div id="underheader-div">
        <div class="container" id="underheadrcontainer">
            <h2 class="commondesign">
                ARTIST_DETAILS
            </h2>
        </div>
    </div>


	<div class="gfont">
	<div id="details" class="my-5 container-fluid w-75">

		<!-- 페이지 상단 아티스트 사진 및 소개 -->
		<div class="card mb-3 border-0" style="max-width: 100%;">
			<input class ="form-control d-none" id = "userid" name="userid" value ="${artist.userid}"/>
			
			<div class="row g-0">
				<div class="px-3 col-md-6">
					<img id="artist_img" src="../images/char/${artist_img.artist_s_img}" 
				  onerror="this.src='../images/char/default_user.png'" class="img-fluid rounded-0" alt="artist_img"/>
				</div>
				<div class="px-3 col-md-6">
					<div class="card-body">
						<h5 id="userid" class="fs-1 card-title text-success fw-bolder">${artist.nickname}</h5>
						<p id="artist_bio_kor" class="fs-5 card-text">${artist.artist_bio_kor}</p> <br><br>
						<p class="fs-5 card-text">
							<small id="artist_bio_eng" class="text-body-secondary">${artist.artist_bio_eng}</small>
						</p>
					</div>
				</div>
			</div>
		</div>

		<!-- 페이지 하단 작품 리스트 -->
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
								<a class="worksnum" href="${worksPage}">${w.title}</a>
							</td>
							<td>${w.getFormattedCreatedDate()}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
		
		<!-- 페이지 최하단 로그인한 아티스트만 소개글 및 사진 수정 & 작품 추가 및 삭제/수정 버튼 -->
		<div class = "mt-4 card-footer d-grid gap-2 d-md-flex justify-content-md-end">
			<!-- 수정 버튼 -->
			<c:if test = "${not empty signedInUser}">
				<c:if test = "${signedInUser eq artist.userid}"> 
					<c:url var = "ArtistModifyPage" value = "/artist/artist_modify">
						<c:param name = "userid" value = "${artist.userid}" />
					</c:url>
					<a href="${ArtistModifyPage}" class="btn btn-outline-dark">수정</a>
					<!-- 작품 추가 버튼 -->
					<c:url var = "WorksAddPage" value = "/artist/artist_add_works">
						<c:param name = "userid" value = "${artist.userid}" />
					</c:url>
					<a href="${WorksAddPage}" class="btn btn-outline-dark">작품 추가</a>
				</c:if>
				<c:if test = "${signedInUser eq artist.userid}">
					<button class ="btn btn-outline-dark me-md-2" id = "btnDelete" value = "삭제">아티스트 삭제</button>
				</c:if> 
				<c:if test="${user.grade eq '관리자'}">
					<button class ="btn btn-outline-dark me-md-2" data-exname="${artist.userid}" id ="btnAdmin" value = "삭제">아티스트 삭제</button>
				</c:if>
			</c:if>
			
			<c:if test = "${empty signedInUser}">
				<div></div>
			</c:if>
		</div>

	</div>
	</div>
	<%@ include file="../fragments/footer.jspf"%>

	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
		crossorigin="anonymous"></script>
	<script src="https://unpkg.com/axios/dist/axios.min.js"></script>		
	<script src = "../js/artist-delete.js"></script>
	<script src = "../js/artistadmin-delete.js"></script>	
	<script src="../js/header.js"></script>
</body>
</html>