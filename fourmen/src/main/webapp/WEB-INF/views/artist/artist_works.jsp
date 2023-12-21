<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head>
<title>FOURMEN</title>
<meta charset="UTF-8" name="viewport"
	content="width=device-width, initial-scale=1" />
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN"
	crossorigin="anonymous">
<link rel="stylesheet" href="../css/header.css">
<link rel="stylesheet" href="../css/footer.css">
<style>
@font-face {
    font-family: 'Pretendard-Regular';
    src: url('https://cdn.jsdelivr.net/gh/Project-Noonnu/noonfonts_2107@1.1/Pretendard-Regular.woff') format('woff');
    font-weight: 400;
    font-style: normal;
}

div#works {
	font-family: 'Pretendard-Regular';
	margin: 0 auto;
	display: grid;
	grid-template-columns: repeat(auto-fit, minmax(500px, 1fr));
	grid-column-gap: 10px;
	grid-row-gap: 20px;
}

div#works-bottom{
	font-family: 'Pretendard-Regular';
}

.card .card-img {
	width:100%;
	height:100%;
	object-fit: cover;
}


div#Works_Title{
	font-size: 50px;
	color: #FF9B00;
}

html {
	scroll-behavior: smooth;
}



@media ( prefers-reduced-motion : reduce) {
	html {
		scroll-behavior: auto;
	}
}
</style>
</head>
<body>
	<%@ include file="../fragments/navigation.jspf"%>

	<div id="works" class="my-5 container-fluid container-xl w-75">

		<div class="card border-0">
			<div id="Works_Title" class="card-text">
				<p id="title" class="card-text">${artist_works.title}</p>
			</div>
			
			<div class="card p-4 border-0">
				<p id="content_kor" class="card-text fs-5">${artist_works.content_kor}</p>
			</div>
			<div class="card p-4 border-0">
				<p id="content_eng" class="card-text fs-5">${artist_works.content_eng}</p>
			</div>
		</div>
		
		<div class = "card border-0 rounded-0">
			<c:forEach var = "wi" items = "${worksImgList}">
				<img class="card-img mb-5 rounded-0" src = "../images/works/${wi.original_file}" onerror="this.onerror=null; this.src='../images/works/default_works.png'" alt="test_works_img"/>
			</c:forEach>
		</div>
	</div>

	<div id="works-bottom" class="my-5 p-0 container-fluid container-xl w-75">
		<!--  댓글 작성 -->
		<div class="my-4 card border-light mb-3" id="comment">
			<div class="card-header d-inline-flex gap-1">
				<button id="btnToggleComment" class="btn fs-5">작품 댓글 달기...</button>
			</div>
			<div class="card-body collapse" id="collapseComments">
				<div>
					<div class="row my-2">
						<div class="col-10">
							<textarea class="form-control" id="commentText" autofocus></textarea>
						</div>
						<div class="col-2">
							<button class="btn fs-4" id="btnAddComment">등록</button>
						</div>
					</div>

					<div class="my-2" id="comments">댓글 목록</div>
				</div>
			</div>
		</div>

		<!-- 뒤로가기 및 위로 올라가기 버튼 -->
		<div class="d-flex justify-content-between">

			<button id="btn_back_to_list" type="button" class="btn fs-5"
				onclick="history.back()">목록으로</button>


			<button id="btn-up-position" class="btn fs-5"
				onclick="window.scrollTo(0,0);">위로 가기</button>

		</div>

	</div>

	<%@ include file="../fragments/footer.jspf"%>

	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
		crossorigin="anonymous"></script>
	<script src="../js/works-comments.js"></script>

</body>
</html>