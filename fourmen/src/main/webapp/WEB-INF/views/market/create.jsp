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
        <link rel="stylesheet" href="/fourmen/css/header.css">
        <link rel="stylesheet" href="/fourmen/css/footer.css">
		
	</head>
	<body>
        <%@ include file="../fragments/navigation.jspf" %>
        <div class="container w-75 my-4">
		  <h1 class="h1">장터글 작성</h1>
        </div>
        <!-- 일단 CSS파일을 만들었는데 그냥 post.css나 나중에 하나로 통일해도 될 듯.. -->
        <div id="market-create-container" class="container w-75">
            <div class="justify-content-center">
                <form action="/fourmen/market/create" method="post" enctype="multipart/form-data" id="market-post-create-form">
                    <label class="mb-2">제목: </label>
                    <input type="text" name="title" class="form-control mb-3" placeholder="제목" maxlength="50" autofocus required/>
                    <div class="my-2">
                        <span class="market-upload-photos mr-5">
                            <label>사진 첨부:</label><br>
                            <input type="file" name="files" class="files">                            
                        </span>
                        <!-- +누를때마다 파일첨부 하나씩 추가.. 최대 20개 -->
                        <button class="market-create-add-phooto" type="button">+</button><br>
                        
                    </div>
                    <textarea name="description"  class="form-control my-2" rows="20" placeholder="내용" maxlength="1000" required></textarea>
                    <label>가격:</label>
                    <input id="market-price" type="number" name="price" class="form-control mb-2" required>
                    <label>창작 연도:</label>
                    <input id="market-created-year" type="number" name="yearCreated" class="form-control mb-2"/>
                    <label class="mb-2">작품 사이즈:</label><br>
                    <div class="mb-2 container">
                        <div class="row row-cols-auto">
                            <div class="d-flex col">
                                폭: <input class="market-dimension" type="number" name="width"> cm
                            </div>
                            <div class="d-flex col">
                                높이: <input class="market-dimension" type="number" name="height"> cm
                            </div>
                            <div class="d-flex col">
                                두께: <input class="market-dimension" type="number" name="depth"> cm<br>
                            </div>
                        </div>
                    </div>
                    <!-- TODO: 유저아이디 일단 적는데 나중에 지울거임 -->
                    <input type="text" name="userId" class="form-control mb-2 d-none" value=${ sessionScope.signedInUser } readonly>
                    
                    <button class="market-create-cancel btn btn-outline-secondary" type="button">취소</button>
                    <button id="btn-market-create-submit" type="button" class="btn btn-outline-success">작성</button>
                    
                </form>
                
            </div>
		</div>
        
        <%@ include file="../fragments/footer.jspf" %>
         
		<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
		  integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
		   crossorigin="anonymous"></script>
        <script src="../js/header.js"></script>
        <script src="../js/market/market-create.js"></script>
		
	</body>
	</html>