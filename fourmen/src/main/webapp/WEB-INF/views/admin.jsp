<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ADMIN</title>
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
		
		<main>
			  <!-- 게시판 카테고리(자유게시판, 후기게시판, 질문게시판) -->
    <section role="kategorie" class="kategorie" style="border-bottom: 1.5px solid #D8D8D8;">
        <div class="forum-kategorie">
            <ul class="forum-kategorie-board-lists">
                <li class="forum-kategorie-board">
                    <a href="admin">회원조회</a>
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
                   <li class="forum-kategorie-board">
                    <a href="forumadmin">게시판 관리</a>
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
		
		
		 <div>
        <nav aria-label="Page navigation">            
            <ul class="pagination">
                <!-- 이전, 처음 페이지 -->
                <li class="page-item">
                     <a class="page-link-img" href="admin${pageMaker.makeSearchAdminUser(pageMaker.startPage)}">
                        <img id="pagination-img" alt="first page" src="/fourmen/pagination/pagination01.png">
                    </a>
                </li>
                
              <!--   <c:choose>
                    <c:when  test="${ page le 1 }">
                        <li class="page-item">
                            <span class="page-link-img" aria-label="previous">
                                <img id="pagination-img" alt="previous page" src="/fourmen/pagination/pagination02.png">
                            </span>
                        </li>
                    </c:when>
                    <c:otherwise>  -->  
                        <li class="page-item">
                            <c:url var="prevPage" value="admin${pageMaker.makeSearchAdminUser(pageMaker.startPage)}">
                                <c:param name="page" value=""/>
                            </c:url>
                            <a class="page-link-img" href="admin${pageMaker.makeSearchAdminUser(pageMaker.startPage)}" aria-label="previous">
                                <img id="pagination-img" alt="previous page" src="/fourmen/pagination/pagination02.png">
                            </a>
                        </li>
                  <!--  </c:otherwise>
                </c:choose>  --> 
                
            
                <!-- 필요한만큼만 페이지 보여줌 -->
                 <c:forEach begin="${pageMaker.startPage}" end="${pageMaker.endPage}" var="idx">
                    <li class="page-item">
                        <a class="page-link"  href="admin${pageMaker.makeSearchAdminUser(idx)}">${idx}</a>
                    </li>
                </c:forEach>
                
                <!-- 다음 마지막 페이지 -->
               <!--  <c:choose>
                    <c:when  test="${ page ge pageMaker.endPage }">
                        <li class="page-item">
                            <span class="page-link-img" aria-label="previous">
                                <img id="pagination-img" alt="previous page" src="/fourmen/pagination/pagination03.png">
                            </span>
                        </li>
                    </c:when>
                    <c:otherwise> --> 
      		<li class="page-item">
      			<c:url var="nextPage" value="admin${pageMaker.makeSearchAdminUser(pageMaker.endPage)}">
                            <c:param name="page" value=""></c:param>
                        </c:url>
                        <a class="page-link-img" href="admin${pageMaker.makeSearchAdminUser(pageMaker.endPage)}" aria-label="next">
                            <img id="pagination-img" alt="next page" src="/fourmen/pagination/pagination03.png">
                        </a>
              </li>
             <!--  </c:otherwise>
              </c:choose> --> 
               
                <li class="page-item">
                    <a class="page-link-img" href="admin${pageMaker.makeSearchAdminUser(pageMaker.endPage)}">
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
      
</body>
</html>