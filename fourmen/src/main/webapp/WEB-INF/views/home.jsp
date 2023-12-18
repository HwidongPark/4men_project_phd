<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>FOURMEN</title>

<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN"
	crossorigin="anonymous">

<!-- css 파일 적용 -->
<link rel="stylesheet" href="css/app.css">
<link rel="stylesheet" href="css/header.css">
<link rel="stylesheet" href="css/footer.css">
<link rel="stylesheet" href="css/homeunderheader.css">

</head>

<!-- body 시작점 -->
<body>
    
    <!-- 헤더 파일 include -->
    <%@ include file="fragments/navigation.jspf"%>
    
    <!-- 웹페이지 상단 헤더 아래 부분 -->
    <div id="underheader-div">
        <div class="container" id="underheadrcontainer">
            <h2 class="commondesign">
            <!-- 홈페이지 헤더와 캐로셀 사이의 여백 만들기 -->
            </h2>
        </div>
    </div>
    
    <!-- Carousel 사진들 추가 -->
    <div id="carousel" class="carousel slide w-100">
        <div class="carousel-indicators">
          <button type="button" data-bs-target="#carousel" data-bs-slide-to="0" class="active" aria-current="true" aria-label="Slide 1"></button>
          <button type="button" data-bs-target="#carousel" data-bs-slide-to="1" aria-label="Slide 2"></button>
          <button type="button" data-bs-target="#carousel" data-bs-slide-to="2" aria-label="Slide 3"></button>
        </div>
        <div class="carousel-inner">
          <div class="carousel-item active img-size-control">
            <img src="picture/반고흐01.jpg"
             class="d-block w-100 carousel-img-size" alt="...">
          </div>
          <div class="carousel-item img-size-control">
            <img src="picture/반고흐02.jpg"
            class="d-block w-100 carousel-img-size" alt="...">
          </div>
          <div class="carousel-item img-size-control">
            <img src="picture/르누아르01.jpg"
             class="d-block w-100 carousel-img-size" alt="...">
          </div>
        </div>
        <button class="carousel-control-prev" type="button" data-bs-target="#carousel" data-bs-slide="prev">
          <span class="carousel-control-prev-icon" aria-hidden="true"></span>
          <span class="visually-hidden">Previous</span>
        </button>
        <button class="carousel-control-next" type="button" data-bs-target="#carousel" data-bs-slide="next">
          <span class="carousel-control-next-icon" aria-hidden="true"></span>
          <span class="visually-hidden">Next</span>
        </button>
    </div>
    
    <!-- 검색어 form -->
    <form action="#" id="search-form">
        <select>
            <option value="title">제목</option>
            <option value="content">내용</option>
            <option value="title_content">제목 + 내용</option>
            <option value="username">글쓴이</option>
        </select>
        <input id="search-keyword" type="text" name="keyword" placeholder="검색">
        <button id="btnSearch">
            <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-search" viewBox="0 0 16 16">
                <path d="M11.742 10.344a6.5 6.5 0 1 0-1.397 1.398h-.001c.03.04.062.078.098.115l3.85 3.85a1 1 0 0 0 1.415-1.414l-3.85-3.85a1.007 1.007 0 0 0-.115-.1zM12 6.5a5.5 5.5 0 1 1-11 0 5.5 5.5 0 0 1 11 0"/>
            </svg>
        </button>
    </form>
    
    <!-- 각 메뉴들의 내용을 간략하게 보여줌 -->
    <main class="main-content">

        <!-- 훈련과정 간략하게 보여줌 -->
        <div>
            <h2 class="sample-trainings">전시회 정보</h2>
            <div class="row row-cols-1 row-cols-sm-2 row-cols-md-3 g-3">
                <div class="col">
                    <div class="card">
                        <img src="https://t1.daumcdn.net/cfile/tistory/9906804C5FB7337315" class="card-img-top" alt="...">
                        <div class="card-body">
                            <h5 class="card-title"><a href="#">Card title</a></h5>
                            <p class="card-text">This is a longer card with supporting text below as a natural lead-in to additional content. This content is a little bit longer.</p>
                        </div>
                    </div>
                </div>
                <div class="col">
                    <div class="card">
                        <img src="https://t1.daumcdn.net/cfile/tistory/9906804C5FB7337315" class="card-img-top" alt="...">
                        <div class="card-body">
                            <h5 class="card-title"><a href="#">Card title</a></h5>
                            <p class="card-text">This is a longer card with supporting text below as a natural lead-in to additional content. This content is a little bit longer.</p>
                        </div>
                    </div>
                </div>
                <div class="col">
                    <div class="card">
                        <img src="https://t1.daumcdn.net/cfile/tistory/9906804C5FB7337315" class="card-img-top" alt="...">
                        <div class="card-body">
                            <h5 class="card-title"><a href="#">Card title</a></h5>
                            <p class="card-text">This is a longer card with supporting text below as a natural lead-in to additional content.</p>
                        </div>
                    </div>
                </div>
                <div class="col">
                    <div class="card">
                        <img src="https://t1.daumcdn.net/cfile/tistory/9906804C5FB7337315" class="card-img-top" alt="...">
                        <div class="card-body">
                            <h5 class="card-title"><a href="#">Card title</a></h5>
                            <p class="card-text">This is a longer card with supporting text below as a natural lead-in to additional content. This content is a little bit longer.</p>
                        </div>
                    </div>
                </div>
                <div class="col">
                    <div class="card">
                        <img src="https://t1.daumcdn.net/cfile/tistory/9906804C5FB7337315" class="card-img-top" alt="...">
                        <div class="card-body">
                            <h5 class="card-title"><a href="#">Card title</a></h5>
                            <p class="card-text">This is a longer card with supporting text below as a natural lead-in to additional content. This content is a little bit longer.</p>
                        </div>
                    </div>
                </div>
                <div class="col">
                    <div class="card">
                        <img src="https://t1.daumcdn.net/cfile/tistory/9906804C5FB7337315" class="card-img-top" alt="...">
                        <div class="card-body">
                            <h5 class="card-title"><a href="#">Card title</a></h5>
                            <p class="card-text">This is a longer card with supporting text below as a natural lead-in to additional content. This content is a little bit longer.</p>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        
        <div class="forum-container">
            <!-- 자유게시판, 취업 후기 몇 개 보여줌 -->
            <div class="forum-subcontainer">
                <a class="link-to-board" href="#">
                    <b><h2 class="forum-text">인기 포트폴리오</h2></b>
                </a>
                <ul class="forum-sample-list">
                    <li>
                        <a href="#">자유게시판 글 제목1</a>
                    </li>
                    <li>
                        <a href="#">자유게시판 글 제목2</a>
                    </li>
                    <li>
                        <a href="#">자유게시판 글 제목3</a>
                    </li>
                    <li>
                        <a href="#">가나다라마바사아자차카타파ㅏㅏ하하쟈댜라쟈댜겨갸니니댜료펴퍄ㅕ재잳ㅈ벼ㅑ교소지냐야펴펴댜재지나어</a>
                    </li>
                    <li>
                        <a href="#">자유게시판 글 제목5</a>
                    </li>
                </ul>
            </div>
            <!-- 취업 후기 게시판 몇 개 보여줌 -->
            <div class="forum-subcontainer">
                <a class="link-to-board" href="#">
                    <b><h2 class="forum-text">최신 포트폴리오</h2></b>
                </a>
                <ul class="forum-sample-list">
                    <li>
                        <a href="#">취업후기 게시판 글 제목 1</a>
                    </li>
                    <li>
                        <a href="#">취업후기 게시판 글 제목 2</a>
                    </li>
                    <li>
                        <a href="#">취업후기 게시판 글 제목 3</a>
                    </li>
                    <li>
                        <a href="#">취업후기 게시판 글 제목 4</a>
                    </li>
                    <li>
                        <a href="#">취업후기 게시판 글 제목 5</a>
                    </li>
                </ul>
            </div>
        </div>
    </main>
    
    <!-- 푸터 파일 include -->
    <%@ include file="fragments/footer.jspf" %>

	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
		crossorigin="anonymous"></script>
    <script src="js/header.js"></script>

</body>
</html>