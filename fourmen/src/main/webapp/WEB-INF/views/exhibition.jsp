<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>전시회</title>

<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN"
	crossorigin="anonymous">
<link rel="stylesheet" href="css/header.css">

<link rel="stylesheet" href="css/footer.css">
<style>
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
    img:hover {
    transform:scale(1.1);
    transition: transform.5s;
    }
    
    .btnReserve{
    font-size: 13px;
    margin: 10px;
    
    
    }
    .btnReserve:hover{
    background-color: olive;
    }
    
    #btnDiv {
    	text-align: center;
    	width: 100%;

    }
    
    .aReserve{
    text-decoration: none;
    color: white;
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
   	 margin: 1px;
   	}
   	.all-select{
   	display: flex;
   	flex-direction: column;
   	align-item: center;
   	}
   	
   	.date-select {
   		text-align: center;
   	}
   	.text-select{
   		text-align: center;
   	}

</style>	
</head>
<body>
		 <%@ include file="fragments/navigation.jspf"%>
		 
		 <main class="w-75 m-auto">
		<c:url var="exhibitionSearch" value="/exhibitionSearch" />
    	<form action="${exhibitionSearch}" method="get" id="search-form">
    	<div class="all-select">
    	<div class="date-select">
    	<label for="date">
  		<input type="date"
         id="startdate-select"
         max="2030-01-01"
         min="2023-01-01"
         value="2023-12-01">
		</label>
		<label for="date">~
  		<input type="date"
         id="enddate-select"
         max="2030-01-01"
         min="2023-01-01"
         value="2023-12-01">
		</label>
		</div>
		<div class="text-select">
        <select name="category" id="selectCategory">
            <option value="title">전시회</option>
            <option value="length">전시기간</option>
            <option value="location">위치</option>
        </select>
        <input id="search-keyword" type="text" name="keyword" placeholder="검색">
        <button id="btnSearch">
            <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-search" viewBox="0 0 16 16">
                <path d="M11.742 10.344a6.5 6.5 0 1 0-1.397 1.398h-.001c.03.04.062.078.098.115l3.85 3.85a1 1 0 0 0 1.415-1.414l-3.85-3.85a1.007 1.007 0 0 0-.115-.1zM12 6.5a5.5 5.5 0 1 1-11 0 5.5 5.5 0 0 1 11 0"/>
            </svg>
        </button>
        </div>
        </div>
    </form>
    
        <!-- 각 메뉴들의 내용을 간략하게 보여줌 -->
    <main class="main-content">

        <!-- 훈련과정 간략하게 보여줌 -->
        <div class="gridbox">
            <div class="row row-cols-1 row-cols-md-3 g-4">
             <c:forEach var="exhibition" items="${exhibition}">
                <div class="col">
                    <div class="card card border-light mb-3">
                        <img src="${exhibition.photo}" class="card-img-top" alt="...">
                        <div class="card-body">
                            <h5 class="card-title text-center">${exhibition.name}</h5>
                            <p class="card-text text-center">${exhibition.startdate}~${exhibition.enddate}</p>
                            <div id="btnDiv"><button class="btnReserve btn btn-secondary"><a class="aReserve" href="${exhibition.site}">예매하기</a></button></div>
                        </div>
                    </div>
                    
                </div>
                </c:forEach>
   
              </div>
            </div>
            
       <div class="m-5">  
     <button id="backButton" class="btnPage position-relative top-100 start-50 translate-middle">
			<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-chevron-left" viewBox="0 0 16 16">
  <path fill-rule="evenodd" d="M11.354 1.646a.5.5 0 0 1 0 .708L5.707 8l5.647 5.646a.5.5 0 0 1-.708.708l-6-6a.5.5 0 0 1 0-.708l6-6a.5.5 0 0 1 .708 0z"/>
</svg>
        </button>
        
        <button id="goButton" class="btnPage position-relative top-100 start-50 translate-middle">
       			<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-chevron-right" viewBox="0 0 16 16">
  <path fill-rule="evenodd" d="M4.646 1.646a.5.5 0 0 1 .708 0l6 6a.5.5 0 0 1 0 .708l-6 6a.5.5 0 0 1-.708-.708L10.293 8 4.646 2.354a.5.5 0 0 1 0-.708z"/>
</svg>
        </button>
    	</div> 
    	
		</main>
		
		<%@ include file="fragments/footer.jspf" %>
	
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
		crossorigin="anonymous"></script>
		
	<script src="js/exhibitionjs.js"></script>
</body>
</html>