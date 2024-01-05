<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
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
<link rel="stylesheet" href="../css/header.css">
<link rel="stylesheet" href="../css/footer.css">    

<style>
@font-face {
    font-family: 'NEXON Lv1 Gothic OTF';
    src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_20-04@2.1/NEXON Lv1 Gothic OTF.woff') format('woff');
    font-weight: normal;
    font-style: normal;
}
div#modify {
	font-family: 'NEXON Lv1 Gothic OTF';
}

</style>

</head>

<body>
	<!-- 헤더 파일 include -->
	<%@ include file="../fragments/navigation.jspf"%>

	<div id="modify" class="my-5 container-fluid w-75">


	<main class = "my-2">
		<div class = "card">
			<c:url var="worksModifyPage" value="/artist/works_modify" />
			<form class="card-body" id="worksModifyForm" action="${worksModifyPage}" method="post" enctype="multipart/form-data" >
	
					<div class = "d-none my-2">
						<label for="userid" class = "form-label">아티스트 아이디</label>
						<input class = "form-control" id = "userid" type = "text" name = "userid" value = "${artist_works.userid}" readonly />
					</div>
					
					<div class = "d-none my-2">
						<label for = "worksid" class = "form-label">작품 번호</label>
						<input class = "form-control" id = "worksid" type = "number" name = "worksid" value = "${artist_works.worksid}" />
					</div>
					
					<div class = "my-2">
						<label for = "title" class = "form-label">작품 제목</label>
						<input class = "form-control" id = "title" type = "text" name = "title" value = "${artist_works.title}" />
					</div>
					
					<div class = "my-2">
						<label for="content_kor" class = "form-label">소개글_한글</label>
						<textarea class = "form-control" id = "content_kor" name = "content_kor">${artist_works.content_kor}</textarea>
					</div>
					
					<div class = "my-2">
						<label for="content_eng" class = "form-label">소개글_영어</label>
						<textarea class = "form-control" id = "content_eng" name = "content_eng">${artist_works.content_eng}</textarea>
					</div>
					
					<div class = "my-2">
						<table class = "table table-striped">
							<thead>
								<tr>
									<th class = "d-none">번호</th>
									<th class = "col-5">사진</th>
									<th class = "col-5">업로드한 이미지 파일</th>
									<th class = "col-1"></th>
									<th class = "col-1">삭제</th>
								</tr>
							</thead>
							<tbody class = "table-group-divider">
								<c:forEach var = "a" items="${artist_works_img_list}">
									<tr>
										<th class ="d-none" scope = "row"><input class ="d-none" id = "imgid" type = "text" name ="imgid" value = "${a.imgid}" readonly /></th>
										<td><img src = "../images/works/${a.saved_file}" style="width:40%; height:40%;"></td>
										<td>${a.original_file}</td>
										<td><input class = "form-control d-none" id = "isFileDeleted" type = "text" name = "isFileDeleted" value ="NO" readonly /></td>
										<td><button class = "btn btn-outline-dark" id = "btnImgDelete" type = "button">X</button></td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
					
					<div class = "my-2">
						<label for = "artist_img" class = "form-label">업로드 할 작품 사진</label>
						<input class = "form-control" type = "file" name = "upload_files" multiple="multiple" />
					</div>
					
					<div class = "my-2">
						<input class = "form-control btn btn-outline-dark" type = "submit" id = "btnUpdate" value = "수정" />
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
	 <script src = "../js/works-detail-modify.js"></script>  
	 <script src="../js/header.js"></script>         
</body>
</html>