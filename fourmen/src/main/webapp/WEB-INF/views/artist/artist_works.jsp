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
<link rel="stylesheet" href="../css/underheader.css">
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
	<%@ include file="../fragments/navigation.jspf"%>
<body>
    
    <!-- 웹페이지 상단 헤더 아래 부분 -->
    <div id="underheader-div">
        <div class="container" id="underheadrcontainer">
            <h2 class="commondesign">
                artists detail
            </h2>
        </div>
    </div>
    
	<div id="works" class="my-5 container-fluid container-xl w-75">

		<div class="card border-0">
			<div class = "card-text d-none">
				<input id = "worksid" name = "worksid" class = "card-text" value = "${artist_works.worksid}" />
				<input id = "userid" name = "userid" class = "card-text" value = "${artist_works.userid}" />
			</div>
		
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
			<c:if test = "${empty worksImgList}">
					<img class="card-img mb-5 rounded-0" src = "../images/works/default_works.png"/>
			</c:if>
			
			<c:if test = "${not empty worksImgList}">
				<c:forEach var = "wi" items = "${worksImgList}">
					<img class="card-img mb-5 rounded-0" src = "../images/works/${wi.saved_file}" onerror="this.src='../images/works/default_works.png'" alt="test_works_img"/>
				</c:forEach>
			</c:if>
		</div>
	</div>

	<div id="works-bottom" class="my-5 p-0 container-fluid container-xl w-75">
		<!--  댓글 작성 -->
		<div class="my-4 card border-light mb-3" id="comment">
			<div class="card-header border-0 rounded-0 d-inline-flex gap-1">
				<button id="btnToggleComment" class="btn fs-5">감상평 남기기</button>
			</div>
			<div class="card-body collapse" id="collapseComments">
				<div>
					<div class="row my-2">
						<div>
							<!-- 댓글 입력 text-area -->
							<textarea class="form-control" id = "comment_content" autofocus></textarea>
							<!-- 댓글 작성자 아이디 현재는 default 작성자 TEST  -->
							<input class = "d-none" id = "comment_writer" value = "TEST" />
						</div>
						<div class="my-2 d-grid d-md-flex justify-content-md-end">
							<button class="btn btn-sm btn-secondary" id="btnAddComment">감상평 등록</button>
						</div>
					</div>

					<div class="my-2" id="comments">감상평 보기</div>
				</div>
			</div>
		</div>

		<!-- 댓글 업데이트 모달(다이얼로그) -->
		<div id="commentModal" class="modal" tabindex="-1">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<h5 class="modal-title">글 수정</h5>
						<button type="button" class="btn-close" data-bs-dismiss="modal"
							aria-label="Close"></button>
					</div>
					<div class="modal-body">
						<input class="d-none" id="modalCommentId" />
						<textarea class="form-control" id="modalCommentText"></textarea>
						<!-- 수정할 부분 -->
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-secondary"
							data-bs-dismiss="modal">취소</button>
						<button id="btnUpdateComment" type="button"
							class="btn btn-primary">글 변경</button>
					</div>
				</div>
			</div>
		</div>
		<!-- end of modal -->
		
		<!-- 뒤로가기 및 위로 올라가기 버튼 -->
		<div class="d-flex justify-content-between">

			<button id="btn_back_to_list" type="button" class="btn fs-5"
				onclick="history.back()">목록으로</button>


			<button id="btn-up-position" class="btn fs-5"
				onclick="window.scrollTo(0,0);">위로 가기</button>

		</div>
		
		<div class = "mt-4 card-footer d-grid gap-2 d-md-flex justify-content-md-end">
			<!-- 수정 버튼 -->
			<c:url var = "worksModifyPage" value = "/artist/works_modify">
				<c:param name = "worksid" value = "${artist_works.worksid}" />
			</c:url>
			<a href="${worksModifyPage}" class="btn btn-outline-dark me-md-2">작품 수정</a>
			<!-- 작품 삭제 버튼 -->
			<button class="btn btn-outline-dark" id="btnDelete">작품 삭제</button>
		</div>

	</div>

	<%@ include file="../fragments/footer.jspf"%>

	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
		crossorigin="anonymous"></script>
	<script src="https://unpkg.com/axios/dist/axios.min.js"></script>	
	<script src="../js/works-comments.js"></script>
	<script src="../js/works-modify.js"></script>

</body>
</html>