<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ADMIN</title>
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
	#startdate-select, #enddate-select{
	border-radius: 0.5em;
	border-color: #dee2e6;;
	padding: 2px;
	}
	.cardbox{
	 margin-top: 40px;
	 margin-bottom: 20px;
	 padding: 20px;
	}
	.exhibition-plus{
    border-radius: 7px;
    padding: 0.5rem;
    width:100%;
	}

</style>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN"
	crossorigin="anonymous">
<link rel="stylesheet" href="../css/header.css">
<link rel="stylesheet" href="../css/footer.css">
<link rel="stylesheet" href="../css/forum-table.css">
	<link rel="stylesheet" href="../css/underheader.css">
	<link rel="stylesheet" href="../css/pagenation.css">	
	<link rel="stylesheet" href="../css/forum-kategorie-area.css">
</head>
<%@ include file="../fragments/navigation.jspf"%>
<body>
<!-- 웹페이지 상단 헤더 아래 부분 -->
    <div id="underheader-div">
        <div class="container" id="underheadrcontainer">
            <h2 class="commondesign">
                EXHIBITION ADMIN
            </h2>
        </div>
    </div>
		
		<main>
			  <!-- 게시판 카테고리(자유게시판, 후기게시판, 질문게시판) -->
    <section role="kategorie" class="kategorie" style="border-bottom: 1.5px solid #D8D8D8;">
        <div class="forum-kategorie">
            <ul class="forum-kategorie-board-lists">
                <li class="forum-kategorie-board">
                    <a href="/fourmen/admin">회원조회</a>
                </li>
                <li class="forum-kategorie-board">
                    <a href="/fourmen/artistadmin">아티스트 관리</a>
                </li>
                <li class="forum-kategorie-board">
                    <a href="/fourmen/marketadmin">마켓 관리</a>
                </li>
                <li class="forum-kategorie-board">
                    <a href="/fourmen/exhibitionadmin">전시회 관리</a>
                </li>
            </ul>
        </div>
    </section>
    
    <div class="container w-75 my-4">
		  <h1 class="h1"></h1>
        </div>
        <!-- 일단 CSS파일을 만들었는데 그냥 post.css나 나중에 하나로 통일해도 될 듯.. -->
        <div id="market-create-container" class="container w-75">
            <div class="justify-content-center">
            <div class="card cardbox">
                <form action="../exhibitionadmin/plus/update" method="post" enctype="multipart/form-data" id="exhibition-plus-form">
                    <label class="mb-2">전시회 이름</label>
                    <input type="text" id="name" name="name" class="exhibition-plus mb-3 " placeholder="이름" autofocus required/>
                    <div class="my-2">
                        <span id="exhibiton-upload-photo">
                            <label class="mb-2">전시회 포스터 첨부</label><br>
                            <input type="file" id="photo" name="upload_file" class="files mb-3 exhibition-plus">                            
                        </span>
                        <!-- +누를때마다 파일첨부 하나씩 추가.. 최대 20개 -->
                        <!--  <button class="market-create-add-phooto exhibition-plus" type="button">추가 +</button>--><br>
                        
                    </div>
                     <label class="mb-2">전시회 링크</label><br>
                    <textarea id="site" name="site" class="exhibition-plus my-2 mb-3" rows="6" placeholder="링크" required></textarea>
                    <label class="mb-2">위치</label>
                    <input type="text" id="location" name="location" class="exhibition-plus mb-3"  placeholder="ex) 서울, 경기, 전남, 대전 등" required>
                    
    	
    	<label class="mb-2">전시회 기간</label><br>
    	<div class="date-select mb-3">
    	<label for="date">
  		<input type="date"
         id="startdate-select"
         name="startdate"
         max="2030-01-01"
         min="2023-01-01"
         class="exhibition-plus"
         >
		</label>
		~
		<label for="date">
  		<input type="date"
         id="enddate-select"
         name="enddate"
         max="2030-01-01"
         min="2023-01-01"
         class="exhibition-plus"
         >
		</label>
		</div>
		    
               
                    <a href="../exhibitionadmin"><button id="btn-exhibition-cancel" class="btn btn-light border border-dark" type="button">취소</button></a>
                    <button id="btn-exhibition-plus" type="button" class="btn btn-dark">추가</button>
               </form>
            
            </div>    
            </div>    
            
		</div>
    
   <%@ include file="../fragments/footer.jspf" %>
	
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
		crossorigin="anonymous"></script>
		<script src="../js/plus.js"></script>
</body>
</html>