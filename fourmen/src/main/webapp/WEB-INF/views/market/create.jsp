<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
		
		<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" 
		integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN"
		 crossorigin="anonymous">
		
	</head>
	<body>
		<h1>장터 글 작성</h1>
        <!-- 일단 CSS파일을 만들었는데 그냥 post.css나 나중에 하나로 통일해도 될 듯.. -->
        <div id="market-create-container" class="container w-75">
            <div class="justify-content-center">
                <form action="/fourmen/market/create" method="post" enctype="multipart/form-data">
                    <label>제목: </label>
                    <input type="text" name="title" class="form-control" placeholder="제목" autofocus>
                    <div>
                        <span class="market-upload-photos">
                            <label>사진 첨부:</label>
                            <input type="file" name="files">
                        </span>
                        <!-- +누를때마다 파일첨부 하나씩 추가.. 최대 20개 -->
                        <button class="market-create-add-phooto" type="button">+</button><br>
                    </div>
                    <textarea name="description"  class="form-control" rows="20"></textarea>
                    
                    <!-- TODO: 유저아이디 일단 적는데 나중에 지울거임 -->
                    <label>유저아이디:</label>
                    <input type="text" name="userId" class="form-control">
                    
                    <button class="market-create-cancel btn btn-outline-secondary" type="button">취소</button>
                    <input type="submit" value="작성" class="btn btn-outline-success">
                </form>
                
            </div>
		</div>
        
		 <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
		  integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
		   crossorigin="anonymous"></script>
         <script src="../js/market/market-create.js"></script>
		
	</body>
	</html>