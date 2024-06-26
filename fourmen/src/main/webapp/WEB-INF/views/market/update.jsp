<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Artists Archive</title>
		
		<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" 
		integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN"
		 crossorigin="anonymous">
        <link rel="stylesheet" href="/fourmen/css/header.css">
        <link rel="stylesheet" href="/fourmen/css/footer.css">
		
	</head>
	
	<style>
@font-face {
    font-family: 'NEXON Lv1 Gothic OTF';
    src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_20-04@2.1/NEXON Lv1 Gothic OTF.woff') format('woff');
    font-weight: normal;
    font-style: normal;
}
.marketplus {
	font-family: 'NEXON Lv1 Gothic OTF';
}
	.marketplus{
	padding: 30px;
	margin-top: 50px;
	}

</style>
	
	<body>
        <%@ include file="../fragments/navigation.jspf" %>
        <div class="container w-75 my-4">
		  
        
        <div class="card marketplus">
        <!-- 일단 CSS파일을 만들었는데 그냥 post.css나 나중에 하나로 통일해도 될 듯.. -->
        <div id="market-create-container" class="container">
            <div class="justify-content-center">
                <form action="/fourmen/market/modify" method="post" enctype="multipart/form-data" id="market-post-update-form">
                    <input id="workid" name="workId" value="${ marketPost.workId }" type="hidden">
                    <label class="mb-2">제목 </label>
                    <input type="text" name="title" class="form-control mb-4" placeholder="제목" maxlength="50" value="${ marketPost.title }" autofocus required>
                    <div>                        
                        <ul>
                            <c:forEach var="workImage" items="${ marketPost.workImages }">
                                <li><span class="mr-4 existing-files">${ workImage.originalFileName }</span><button type="button" class="marketBtnDeleteImage">삭제</button></li>
                            </c:forEach>
                        </ul>                        
                    </div>
                    
                    <div class="my-2">
                        <span class="market-upload-photos">
                            <label>사진 첨부</label><br>
                            <input type="file" name="files" class="files">                            
                        </span>
                        <!-- +누를때마다 파일첨부 하나씩 추가.. 최대 20개 -->
                        <button class="market-create-add-phooto" type="button">+</button><br>                       
                    </div>
                    <textarea name="description"  class="form-control my-2" rows="15" placeholder="내용" maxlength="1000" required>${ marketPost.descriptionKor }</textarea>
                    <label>가격:</label>
                    <input id="market-price" type="number" name="price" class="form-control mb-3" value="${ marketPost.price }" required>
                    <label>창작 연도:</label>
                    <input id="market-created-year" type="number" name="yearCreated" class="form-control mb-3" value="${ marketPost.yearCreated }"/>
                    <label class="mb-3">작품 사이즈</label><br>
                    <div class="mb-4 container">
                        <div class="row row-cols-auto">
                            <div class="d-flex col">
                                폭<input class="market-dimension" type="number" name="width" value="${ width }" placeholder="폭"> cm
                            </div>
                            <div class="d-flex col">
                                높이<input class="market-dimension" type="number" name="height" value="${ height }"  placeholder="높이"> cm
                            </div>
                            <div class="d-flex col">    
                                두께<input class="market-dimension" type="number" name="depth" value="${ depth }" placeholder="넓이"> cm
                            </div>
                        </div>
                    </div>
                    <!-- TODO: 유저아이디 일단 적는데 나중에 지울거임 -->
                    <input type="text" name="userId" class="form-control mb-2 d-none" value="${ marketPost.userId }" readonly>
                    
                    <button class="market-create-cancel btn btn-outline-secondary" type="button" id="market-update-btn-cancel">취소</button>
                    <button type="button" class="btn btn-dark" id="btn-market-update-submit">수정</button>
                    <!-- 테스트 -->
                    <input type="hidden" id="hidReqAttr" value="${ marketPost }" />
                </form>
                </div>
            </div>
		</div>
        </div>
        <%@ include file="../fragments/footer.jspf" %>
         
		<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
		  integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
		   crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/axios@1.1.2/dist/axios.min.js"></script>
        <script src="../js/header.js"></script>        
        <script src="../js/market/market-create.js"></script>
        <script src="../js/market/market-update.js"></script>
		
	</body>
	</html>