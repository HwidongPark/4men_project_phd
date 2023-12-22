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
<link rel="stylesheet" href="css/header.css">
<link rel="stylesheet" href="css/forum.css">
<link rel="stylesheet" href="css/footer.css">

</head>

<body>
    
    <!-- 헤더 파일 include -->
    <%@ include file="fragments/navigation.jspf"%>
    
    <!-- Carousel 사진들 추가 -->
    <div id="carousel" class="carousel slide w-100">
        <div class="carousel-indicators">
          <button type="button" data-bs-target="#carousel" data-bs-slide-to="0" class="active" aria-current="true" aria-label="Slide 1"></button>
          <button type="button" data-bs-target="#carousel" data-bs-slide-to="1" aria-label="Slide 2"></button>
          <button type="button" data-bs-target="#carousel" data-bs-slide-to="2" aria-label="Slide 3"></button>
        </div>
        <div class="carousel-inner">
          <div class="carousel-item active img-size-control">
            <img src="imagemain/main01.jpg"
             class="d-block w-100 carousel-img-size" alt="...">
          </div>
          <div class="carousel-item img-size-control">
            <img src="imagemain/main02.jpg" 
            class="d-block w-100 carousel-img-size" alt="...">
          </div>
          <div class="carousel-item img-size-control">
            <img src="imagemain/main031.jpg"
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
    
   
    
    <!-- 각 메뉴들의 내용을 간략하게 보여줌 -->
    <main class="main-content">
			

			
   		   <div class="gridbox">
            <div class="row row-cols-1 row-cols-md-3 g-4">
             <c:forEach var="exhibition" items="${exhibition}">
                <div class="col">
                    <div class="card card border-light mb-3">
                        <img src="${exhibition.photo}" class="card-img-top" alt="...">
                    </div>
                    
                </div>
                </c:forEach>
   
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