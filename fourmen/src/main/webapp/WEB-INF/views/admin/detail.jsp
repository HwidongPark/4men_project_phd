<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원정보</title>
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
	<link rel="stylesheet" href="../css/admindetail.css">

</head>
<%@ include file="../fragments/navigation.jspf"%>
<body>
			 
	<!-- 웹페이지 상단 헤더 아래 부분 -->
    <div id="underheader-div">
        <div class="container" id="underheadrcontainer">
            <h2 class="commondesign">
                ADMIN
            </h2>
        </div>
    </div>
    
    <main>	
			  <!-- 게시판 카테고리(자유게시판, 후기게시판, 질문게시판) -->
    <section role="kategorie" class="kategorie" style="border-bottom: 1.5px solid #D8D8D8;">
        <div class="forum-kategorie">
            <ul class="forum-kategorie-board-lists">
                <li class="forum-kategorie-board">
                    <a href="../admin">회원조회</a>
                </li>
                <li class="forum-kategorie-board">
                    <a href="../artistadmin">아티스트 관리</a>
                </li>
                <li class="forum-kategorie-board">
                    <a href="../marketadmin">마켓 관리</a>
                </li>
                <li class="forum-kategorie-board">
                    <a href="../exhibitionadmin">전시회 관리</a>
                </li>
            </ul>
        </div>
    </section>
    
      <c:url var="adminuserdetail" value="/admin/detail" />
    <form action="${adminuserdetail}" method="get" id="detail-user">
    <div class="search">
        <div class="forum-top-area">
            <div class="forum-search-area card">
       
            	<div class="oneinfo">
                <div class="forum-search-select-area">
                <div class="forum-select-box">아이디</div>
                </div>
                <div></div>
                <div class="forum-search-form-area">
                    <input class="forum-search-nochange" type="text" name="userid" id="userid" value="${user.userid}" readonly />
                </div>	
          		</div>
          		<div class="oneinfo">
				 <div class="forum-search-select-area">
                <div class="forum-select-box">비밀번호</div>
                </div>
                <div></div>
                <div class="forum-search-form-area">
                    <input class="forum-search-nochange"  type="text" name="password" id="password" value="${user.password}" readonly>
                </div>
                </div>
                <div class="oneinfo">	
                 <div class="forum-search-select-area">
                <div class="forum-select-box">이름</div>
                </div>
                <div></div>
                <div class="forum-search-form-area">
                    <input class="forum-search-input"  type="text" name="name" id="name" value="${user.name}">
                </div>	
                </div>
                <div class="oneinfo">
                 <div class="forum-search-select-area">
                <div class="forum-select-box">닉네임</div>
                </div>
                <div></div>
                <div class="forum-search-form-area">
                    <input class="forum-search-input"  type="text" name="nickname" id="nickname" value="${user.nickname}">
                </div>
                </div>
                <div class="oneinfo">	
                 <div class="forum-search-select-area">
                <div class="forum-select-box">전화번호</div>
                </div>
                <div></div>
                <div class="forum-search-form-area">
                    <input class="forum-search-input"  type="text" name="phone" id="phone" value="${user.phone}">
                </div>	
                </div>
                <div class="oneinfo">
                 <div class="forum-search-select-area">
                <div class="forum-select-box">이메일</div>
                </div>
                <div></div>
                <div class="forum-search-form-area">
                    <input class="forum-search-input"  type="email" name="email" id="email" value="${user.email}">
                </div>
                </div>
                <div class="oneinfo">	
                 <div class="forum-search-select-area">
                <div class="forum-select-box">등급</div>
                </div>
                <div></div>
                <div class="forum-search-form-area" id="radiotest">
             	
                    <input class="radioselect" type="radio" name="grade" id="grade" value="일반">일반
                    <input class="radioselect" type="radio" name="grade" id="grade" value="아티스트">아티스트
                    <input class="radioselect" type="radio" name="grade" id="grade" value="관리자">관리자
                    
                </div>	
                </div>
       
                 
			
            </div>
            <div class="card img-card">
            	<img src="${user.profile_s_img}" id="profile_s_img" class="card-img" alt="...">
            </div>
            
        </div>
           
    </div>
    
   	</form>
   			
   		 <div class="detailbutton">
            <button class="btn btn-dark" id="btndelete">탈퇴</button>
            <button class="btn btn-outline-secondary" id="btnupdate">회원수정</button>
         </div>	
   
    
    </main>
    

    <%@ include file="../fragments/footer.jspf" %>
	
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
		crossorigin="anonymous"></script>
	
	<script src="../js/detail.js"></script>
	<script>
window.onload = function(){
	 document.querySelector("#radiotest input[value=${user.grade}]").setAttribute('checked' , true);

		 
}
</script>


</body>

</html>




	
</body>


</html>