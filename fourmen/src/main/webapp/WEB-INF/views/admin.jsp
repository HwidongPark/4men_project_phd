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
@font-face {
    font-family: 'NEXON Lv1 Gothic OTF';
    src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_20-04@2.1/NEXON Lv1 Gothic OTF.woff') format('woff');
    font-weight: normal;
    font-style: normal;
}

.commondesign{
	font-family: 'EF_Rebecca';
}
.gfont{
font-family: 'NEXON Lv1 Gothic OTF';
}
</style>

<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN"
	crossorigin="anonymous">
<link rel="stylesheet" href="css/header.css">
<link rel="stylesheet" href="css/footer.css">
<link rel="stylesheet" href="css/forum-table.css">
	<link rel="stylesheet" href="css/underheader.css">
	<link rel="stylesheet" href="css/pagenation.css">	
	<link rel="stylesheet" href="css/forum-kategorie-area.css">
	<link rel="stylesheet" href="css/forum-search-area.css">
</head>

<%@ include file="fragments/navigation.jspf"%>
<body>
			 
	<!-- 웹페이지 상단 헤더 아래 부분 -->
    <div id="underheader-div">
        <div class="container" id="underheadrcontainer">
            <h2 class="commondesign">
                ADMIN
            </h2>
        </div>
    </div>
		
		<main class="gfont">
			  <!-- 게시판 카테고리(자유게시판, 후기게시판, 질문게시판) -->
    <section role="kategorie" class="kategorie" style="border-bottom: 1.5px solid #D8D8D8;">
        <div class="forum-kategorie">
            <ul class="forum-kategorie-board-lists">
                <li class="forum-kategorie-board">
                    <a href="admin" class="forum-kategori-current-selected">회원조회</a>
                </li>
                <li class="forum-kategorie-board">
                    <a href="artistadmin">아티스트 관리</a>
                </li>
                <li class="forum-kategorie-board">
                    <a href="marketadmin">마켓 관리</a>
                </li>
                <li class="forum-kategorie-board">
                    <a href="exhibitionadmin">전시회 관리</a>
                </li>
         
            </ul>
        </div>
    </section>
    
     <!-- 게시글 검색창 -->
    
      <c:url var="adminSearch" value="/admin" />
    			 <form action="${adminSearch}" method="get" id="search-user">
    <section role="search" class="search">
        <div class="forum-top-area">
            <div class="forum-search-area">
                <div class="forum-search-select-area">
                
    
                    <select class="forum-select-box" name="category" id="selectCategory">
         				<option class="forum-select-option" value="userid" name="userid"<c:out value="${scri.category eq 'userid' ? 'selected' : ''}"/>>아이디</option>
            			<option class="forum-select-option" value="name" name="name"<c:out value="${scri.category eq 'name' ? 'selected' : ''}"/>>이름</option>
            			<option class="forum-select-option" value="nickname" name="nickname"<c:out value="${scri.category eq 'nickname' ? 'selected' : ''}"/>>닉네임</option>
            			<option class="forum-select-option" value="phone" name="phone"<c:out value="${scri.category eq 'phone' ? 'selected' : ''}"/>>전화번호</option>
            			<option class="forum-select-option" value="email" name="email"<c:out value="${scri.category eq 'email' ? 'selected' : ''}"/>>이메일</option>
            			<option class="forum-select-option" value="grade" name="grade"<c:out value="${scri.category eq 'grade' ? 'selected' : ''}"/>>등급</option>
            			
                  <!--    <option class="forum-select-option" value="userid" name="userid">아이디</option>
                        <option class="forum-select-option" value="name" name="name">이름</option>
                        <option class="forum-select-option" value="nickname" name="nickname">닉네임</option>
                        <option class="forum-select-option" value="phone" name="phone">전화번호</option>
                        <option class="forum-select-option" value="email" name="email">이메일</option>
                        <option class="forum-select-option" value="grade" name="grade">등급</option>   -->  
                    </select>
                </div>
                <div class="forum-search-form-area">
                    <input maxlength="30" id=forum-search-input autocomplete="on" placeholder="검색어를 입력하세요." type="text" name="keyword" id="search-keyworduser" value="${scri.keyword}">
                </div>	
				 
                <div class="forum-search-btn-area">
                    <button class="forum-search-btn" id="searchUser">
                        <img id="forum-search-btn-img" alt="검색버튼" src="icon/search01.svg">
                    </button>
                </div>
            </div>
        </div>
    </section>
   	</form>
    
    <!-- board-list-content 게시판 글 리스트 테이블 -->
    <section class="freeboard-list" style="padding: 3.5rem 18.5rem 0rem 18.5rem;">
        <table class="table">
            <colgroup>
                <col style="width: 100px;">
                <col style="width: 100px;">
                <col style="width: 100px;">
                <col style="width: 100px;">
                <col style="width: 100px;">
                <col style="width: 100px;">
                <col style="width: 100px;">
            </colgroup>
            <thead>
            <tr>
                <th>아이디</th>
                <th>비밀번호</th>
                <th>이름</th>
                <th>닉네임</th>
                <th>전화번호</th>
                <th>이메일</th>
                <th>등급</th>
            </tr>
            </thead>
            <tbody class="table-group-divider">
            <c:forEach var="adminuserlist" items="${adminuserlist}">
   
                <tr>
                    <td>
                    <c:url var="userdetail" value="/admin/detail">
                    <c:param name="userid" value="${adminuserlist.userid}" />
                    </c:url>
                    <a href="${userdetail}">${adminuserlist.userid}</a></td>
                    <td>${adminuserlist.password}</td>
                    <td>${adminuserlist.name}</td>
                    <td>${adminuserlist.nickname}</td>
                    <td>${adminuserlist.phone}</td>
                    <td>${adminuserlist.email}</td>
                    <td>${adminuserlist.grade}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </section>
		
		
		
        <!-- 게시판 글 페이지네이션(pagination)-->
        <div>
            <nav aria-label="Page navigation">
                <ul class="pagination">
                    <!-- 처음 페이지 -->
                    <li class="page-item">
                        <a class="page-link-img" href="admin${pageMaker.makeSearchAdminUser(1)}">
                            <img id="pagination-img" alt="first page" src="/fourmen/pagination/pagination01.png">
                        </a>
                    </li>
                    <!-- 이전 페이지 -->
                    <li class="page-item">
                        <a class="page-link-img" href="admin${pageMaker.makeSearchAdminUser(page-1)}" aria-label="previous"> 
                            <img id="pagination-img" alt="previous page" src="/fourmen/pagination/pagination02.png">
                        </a>
                    </li>

                    <!-- 필요한 만큼만 페이지 보여줌 -->
                    <!--<c:forEach begin="${pageMaker.startPage}" end="${pageMaker.endPage}" var="idx">
                        <li class="page-item">
                            <a class="page-link" href="admin${pageMaker.makeSearchAdminUser(idx)}">${idx}</a>
                        </li>
                    </c:forEach>-->
                    
                    <!-- 필요한 만큼만 페이지 보여줌 -->
                    <c:forEach begin="${pageMaker.startPage}" end="${pageMaker.endPage}" var="idx">
                        <li class="page-item">
                            <c:url var="toPage" value="admin${pageMaker.makeSearchAdminUser(idx)}"/>
                            <a class="page-link" href="${toPage}">
                                <c:if test="${page eq idx}">
                                    <span class="pagenation-current-page">
                                </c:if>
                                ${idx}
                                <c:if test="${page eq idx}">
                                    </span>
                                </c:if>
                            </a>
                        </li>
                    </c:forEach>
                    
                    <!-- 다음 페이지 -->
                    <li class="page-item">
                        <a class="page-link-img" href="admin${pageMaker.makeSearchAdminUser(page+1)}" aria-label="next"> 
                            <img id="pagination-img" alt="next page" src="/fourmen/pagination/pagination03.png">
                        </a>
                    </li>
                    <!-- 마지막 페이지 -->
                    <li class="page-item">
                        <a class="page-link-img" href="admin${pageMaker.makeSearchAdminUser(pageMaker.tempEndPage)}">
                            <img id="pagination-img" alt="last page" src="/fourmen/pagination/pagination04.png">
                        </a>
                    </li>

                </ul>
            </nav>
        </div>
		
		</main>
		
		
		<%@ include file="fragments/footer.jspf" %>
	
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
		crossorigin="anonymous"></script>
	<script>
      $(function(){
        $('#searchUser').click(function() {
      
        self.location = "admin" + '${pageMaker.makeQuery(1)}' + "&category=" + $("select option:selected").val() + "&keyword=" + encodeURIComponent($('#search-keyworduser').val());
        });
      });   
      </script>
      <script src="js/adminuser.js"></script>
      <script src="/fourmen/js/header.js"></script>
      
</body>
</html>