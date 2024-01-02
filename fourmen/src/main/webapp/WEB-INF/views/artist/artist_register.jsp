<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>FOURMEN</title>
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
	src:
		url('https://cdn.jsdelivr.net/gh/Project-Noonnu/noonfonts_2107@1.1/Pretendard-Regular.woff')
		format('woff');
	font-weight: 400;
	font-style: normal;
}

div#modify {
	font-family: 'Pretendard-Regular';
}
</style>

</head>

<body>
	<!-- 헤더 파일 include -->
	<%@ include file="../fragments/navigation.jspf"%>

	<div id="modify" class="my-5 container-fluid w-75">


		<main class="my-2">
			<div class="card">
				<c:url var="artistRegisterPage" value="/artist/artist_register" />
				<form class="card-body" id="artistRegisterForm"
					action="${artistRegisterPage}" method="post"
					enctype="multipart/form-data">
	
	
					<!-- 로그인 했을 때 로그인 유저를 가져와야함. -->
					<div class="my-2">
						<label for="userid" class="form-label">유저 아이디</label> <input
							class="form-control" id="userid" type="text" name="userid"
							value="${signedInUser}" readonly />
					</div>
					
					<!-- 로그인 했을 때 유저의 닉네임을 아티스트의 아이디로 지정 -->
					<div>
						<label for = "artistNickName" class ="form-label">아티스트 닉네임</label>
						<input class = "form-control" id="nickname" type = "text" name = "nickname"/>
					</div>
					
					<div class="my-2">
						<label for="artist_bio_kor" class="form-label">소개글_한글</label>
						<textarea class="form-control" id="artist_bio_kor"
							name="artist_bio_kor">${artist.artist_bio_kor}</textarea>
					</div>

					<div class="my-2">
						<label for="artist_bio_kor" class="form-label">소개글_영어</label>
						<textarea class="form-control" id="artist_bio_eng"
							name="artist_bio_eng">${artist.artist_bio_eng}</textarea>
					</div>

					<div class="my-2">
						<label for="artist_img" class="form-label">아티스트 사진</label> <input
							class="form-control" type="file" name="upload_file"
							id="upload_file" />
					</div>

					<div class="my-2">
						<input class="form-control btn btn-outline-primary" type="submit"
							id="btnUpdate" value="등록" />
					</div>

				</form>
			</div>
		</main>


	</div>



	</div>

	<%@ include file="../fragments/footer.jspf"%>

	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
		crossorigin="anonymous"></script>
</body>
</html>