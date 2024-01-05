<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
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
<link rel="stylesheet" href="css/header.css">
<link rel="stylesheet" href="css/footer.css">
	<link rel="stylesheet" href="css/underheader.css">
	<link rel="stylesheet" href="css/pagenation.css">	

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
@font-face {
    font-family: 'NEXON Lv1 Gothic OTF';
    src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_20-04@2.1/NEXON Lv1 Gothic OTF.woff') format('woff');
    font-weight: normal;
    font-style: normal;
}
.gfont{
	font-family: 'NEXON Lv1 Gothic OTF';
}
html, body, div, span, applet, object, iframe,
h1, h2, h3, h4, h5, h6, p, blockquote, pre,
a, abbr, acronym, address, big, cite, code,
del, dfn, em, img, ins, kbd, q, s, samp,
small, strike, strong, sub, sup, tt, var,
b, u, i, center,
dl, dt, dd, ol, ul, li,
fieldset, form, label, legend,
table, caption, tbody, tfoot, thead, tr, th, td,
article, aside, canvas, details, embed, 
figure, figcaption, footer, header, hgroup, 
menu, nav, output, ruby, section, summary,
time, mark, audio, video {
	margin: 0;
	padding: 0;
	border: 0;
	font-size: 100%;
	font: inherit;
	vertical-align: baseline;
}
/* HTML5 display-role reset for older browsers */
article, aside, details, figcaption, figure, 
footer, header, hgroup, menu, nav, section {
	display: block;
}
body {
	line-height: 1;
}
ol, ul {
	list-style: none;
}
blockquote, q {
	quotes: none;
}
blockquote:before, blockquote:after,
q:before, q:after {
	content: '';
	content: none;
}
table {
	border-collapse: collapse;
	border-spacing: 0;
}
   #exhibition-image:hover {
    transform:scale(1.1);
    transition: transform.5s;
    }
    
    .btnReserve{
    font-size: 13px;
    margin: 10px;
    
    }
	.aReserve{
	color:white;
	text-decoration: none;
	}
	.btnReserve:hover{
	background-color: black;
	}

    #btnDiv {
    
    	text-align: center;
    	width: 100%;
    }

    
    .btnPage{
        background-color: Transparent;
    background-repeat:no-repeat;
    border: none;
    cursor:pointer;
    overflow: hidden;        
    }
   	
   	.girdbox{
   	display: grid;
   	grid-template-columns: 1fr 1fr 1fr;
    width: 100%;
    margin-bottom: 3em;
   	}
   	
   	.exhibition-all-item {
   		width: 90%;
   		margin: 0 auto;
   	}
   	
   	.image-outer-container {
   		width: 100%;
   		height: 0;
   		padding-bottom: 148%;
   		position: relative;
   	}
   	
	.image-outer-container img{
		width: 100%;
		height: 100%;
		object-fit: cover;
		position: absolute;
		top: 0;
		left: 0;
	}
   	
   	/* 메인 바디 CSS */
	.main-content {
    width: 75%;
    margin: 0 auto;
	}
   	/* 검색에 관한 css */
	#search-form {
    display: flex;
    flex-wrap: nowrap;
    justify-content: center;
    margin: 3rem auto;
	}

	#search-form select {
    border-radius: 0.5em;
	}

	#search-keyword {
    width: 50%;
    margin: 0 0.5em;
    margin-right: 0.8em;
    height: 100%;
    border-radius: 0.5em;
	}

	#btnSearch {
    background-color: transparent;
    border: none
	}
	
	/* 반응형 디자인 */
@media (max-width: 768px) {

    /* 서치버튼과 캐러샐 간격 조정 */
    #search-form select {
        width: auto;
        font-size: 0.5em;
    }
    
    #search-form {
        margin: 2rem;
        font-size:0.8em;
    }

    /* 훈련과정 h2폰트 사이즈 줄임 */
    .sample-trainings {
        font-size: 1.5em;
    }
    

    /* 게시물들 한 행당 한 칼럼으로 정리 */
    .forum-container {
        flex-direction: column;
    }

    .forum-subcontainer {
        width: 100%;
    }

    .forum-text {
        font-size: 1.5em;
    }
}

	.date-select{
	text-align: left 10px;
	margin-bottom: 10px;
	padding-left: 10px;
	
	}
	.text-select{
	text-align: center;
	width: 300px;
	}
	.button-select{
	text-align: center;
	width: auto;
    font-size: 0.5em;
	}
	#startdate-select, #enddate-select{
	border-radius: 0.5em;
	border-color: lightgary;
	padding: 2px;
	}

	
	.page_wrap {
	text-align:center;
	font-size:0;
 }
.page_nation {
	display:inline-block;
}
.page_nation .none {
	display:none;
}
.page_nation a {
	display:block;
	margin:0 3px;
	float:left;
	border:1px solid #e6e6e6;
	width:28px;
	height:28px;
	line-height:28px;
	text-align:center;
	background-color:#fff;
	font-size:13px;
	color:#999999;
	text-decoration:none;
}

.page_nation a:hover {
	background-color:#42454c;
	color:#fff;
	border:1px solid #42454c;
}


	
</style>

</head>

<%@ include file="fragments/navigation.jspf"%>
<body>
		 
		 
	<!-- 웹페이지 상단 헤더 아래 부분 -->
    <div id="underheader-div">
        <div class="container" id="underheadrcontainer">
            <h2 class="commondesign">
                EXHIBITION
            </h2>
        </div>
    </div>
    
    
		 <main class="main-content gfont">
		 <div class="w-75 m-auto ">
	
		<c:url var="exhibitionSearch" value="/exhibition" />
    	<form action="${exhibitionSearch}" method="get" id="search-form" role="form">
    	
    	<!-- 전시회 날짜선택 -->
    	<div class="all-select">
    	<div class="date-select">
    	<label for="date">
  		<input type="date"
         id="startdate-select"
         name="startdate"
         max="2030-01-01"
         min="2023-01-01"
         >
		</label>
		<label for="date">~
  		<input type="date"
         id="enddate-select"
         name="enddate"
         max="2030-01-01"
         min="2023-01-01"
         >
		</label>
		</div>
		 
		 <!-- 전시회 카테고리 -->
		<div class="text-select">
        <select name="category" id="selectCategory">
            <option value="name" name="name"<c:out value="${scri.category eq 'title' ? 'selected' : ''}"/>>전시회</option>
            <option value="location" name="location"<c:out value="${scri.category eq 'location' ? 'selected' : ''}"/>>위치</option>
        </select>
        <input maxlength="30" id="search-keyword" type="text" name="keyword" placeholder="검색" value="${scri.keyword}">
		
		</form>
		
		<!-- 검색버튼 -->
 		<button id="btnSearch">
            <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-search" viewBox="0 0 16 16">
                <path d="M11.742 10.344a6.5 6.5 0 1 0-1.397 1.398h-.001c.03.04.062.078.098.115l3.85 3.85a1 1 0 0 0 1.415-1.414l-3.85-3.85a1.007 1.007 0 0 0-.115-.1zM12 6.5a5.5 5.5 0 1 1-11 0 5.5 5.5 0 0 1 11 0"/>
            </svg>
       </button>
		</div>
		</div>   
	
        

    
    
		
        <!-- 반복으로 db에서 exhibition 가져오기 -->
        <div class="girdbox">
             <c:forEach var="exhibition" items="${exhibition}">
             	
                    <div class="exhibition-all-item card card border-light mb-3">
                        <div class="image-outer-container">
                        	<div class="image-inner-container">
                        		<a class="aReserve" href="${exhibition.site}">
                        			<img src="image/${exhibition.photo}" class="card-img-top" id="exhibition-image" alt="...">
                       			</a>
                     		</div>
                    	</div>
                        <div class="card-body">
                            <h5 class="card-title text-center">${exhibition.name}</h5>
                            <p class="card-text text-center">${exhibition.startdate}~${exhibition.enddate}</p>
                            <div id="btnDiv"><button class="btnReserve btn btn-secondary"><a class="aReserve" href="${exhibition.site}">예매하기</a></button></div>
                        </div>
                    </div>
                </c:forEach>
            </div>
    
  

	
	   <!-- 게시판 글 페이지네이션(pagination)-->
        <div>
            <nav aria-label="Page navigation">
                <ul class="pagination">
                    <!-- 처음 페이지 -->
                    <li class="page-item">
                        <a class="page-link-img" href="exhibition${pageMaker.makeSearch(1)}">
                            <img id="pagination-img" alt="first page" src="/fourmen/pagination/pagination01.png">
                        </a>
                    </li>
                    <!-- 이전 페이지 -->
                    <li class="page-item">
                        <a class="page-link-img" href="exhibition${pageMaker.makeSearch(page-1)}" aria-label="previous"> 
                            <img id="pagination-img" alt="previous page" src="/fourmen/pagination/pagination02.png">
                        </a>
                    </li>

                    <!-- 필요한 만큼만 페이지 보여줌 -->
                    <!--<c:forEach begin="${pageMaker.startPage}" end="${pageMaker.endPage}" var="idx">
                        <li class="page-item">
                            <a class="page-link" href="exhibition${pageMaker.makeSearch(idx)}">${idx}</a>
                        </li>
                    </c:forEach>-->
                    
                    <!-- 필요한 만큼만 페이지 보여줌 -->
                    <c:forEach begin="${pageMaker.startPage}" end="${pageMaker.endPage}" var="idx">
                        <li class="page-item">
                            <c:url var="toPage" value="exhibition${pageMaker.makeSearch(idx)}"/>
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
                        <a class="page-link-img" href="exhibition${pageMaker.makeSearch(page+1)}" aria-label="next"> 
                            <img id="pagination-img" alt="next page" src="/fourmen/pagination/pagination03.png">
                        </a>
                    </li>
                    <!-- 마지막 페이지 -->
                    <li class="page-item">
                        <a class="page-link-img" href="exhibition${pageMaker.makeSearch(pageMaker.tempEndPage)}">
                            <img id="pagination-img" alt="last page" src="/fourmen/pagination/pagination04.png">
                        </a>
                    </li>

                </ul>
            </nav>
        </div>
    	
  	
  	   
  
		
		
	<div class="m-5"></div>
		
	</main>	

		<%@ include file="fragments/footer.jspf" %>
	
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
		crossorigin="anonymous"></script>

	 <script>
      $(function(){
        $('#searchBtn').click(function() {
          self.location = "exhibition" + '${pageMaker.makeQuery(1)}' + "&category=" + $("select option:selected").val() + "&keyword=" + encodeURIComponent($('#search-keyword').val()
          + "&startdate=" + encodeURIComponent($('#startdate-select').val())+ "&enddate=" + encodeURIComponent($('#enddate-select').val()));
        });
      });   
    </script>

    <script src="/fourmen/js/header.js"></script>

	<script src="js/exhibitionjs.js"></script>
</body>
</html>