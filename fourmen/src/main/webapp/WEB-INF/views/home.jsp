<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
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

<!-- css 파일 적용 -->
<link rel="stylesheet" href="css/app.css">
<link rel="stylesheet" href="css/header.css">
<link rel="stylesheet" href="css/footer.css">
<link rel="stylesheet" href="css/homeunderheader.css">
<style>
@font-face {
    font-family: 'EF_Rebecca';
    src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_2210-EF@1.0/EF_Rebecca.woff2') format('woff2');
    font-weight: normal;
    font-style: normal;
}
.main-content{
font-family: 'EF_Rebecca';
}
.bighome{
margin-top: 60px;
display: flex;

}
.cardbox{
width: 25%;
height: 35%;
text-align: center;
font-size: 30px;
margin-right: 30px;
}
.page{
text-align: left;
font-size: 30px;
}
.more{
text-align: right;
font-size: 10px;
}
.a-more{
color:black;
}
.card{
margin-top:10px;
}
.market-img{
width: 50%;
height: 50%;
margin-right: 20px;
}
.marketimg{
width: 100%;
height: 100%;
}
.market-images{
display: flex;
margin-top: 10px;
}
.marketzone{
margin-left: 20px;
}
.artists{
test-align: center;
size: 60px;
}
.artist-img{
width:100%;

}
.artistimg{
width: 100%;
height: 100%;
}
.enter{
	margin:100px;
}
.artist{
text-align: right;
font-size: 60px;
background-color: white;
padding-top: 20px;

color: black;
}
.artistzone-size{
height: auto;
background-color:white;
padding-top: 20px;
padding-left: 10px;
}
.artistzone{
padding-bottom: 50px;
}

div#artist_container {
	margin: 0 auto;
	display: grid;
	grid-template-columns: 1fr 1fr 1fr 1fr;
	grid-column-gap: 30px;
	grid-row-gap: 40px;
}
.card .card-img {
    width: 100%; /* 이미지 너비 100%로 설정 */
    height: 100%; /* 이미지 높이 100%로 설정 */
    object-fit: cover; /* 이미지 비율 유지하면서 카드에 맞춰 잘립니다 */
}

.card .card-img-overlay {
    visibility: hidden;
    opacity: 0;
    transition: visibility 0s, opacity 0.5s ease; /* 효과 부드럽게 전환 */
    position: absolute;
    top: 0;
    left: 0;
    width: 100%;
    height: 100%;
    background-color: rgba(0, 0, 0, 0.5); /* 반투명한 검은 배경 */
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
}


/* 마우스 호버 시 오버레이 나타남 */
.card:hover .card-img-overlay {
    visibility: visible;
    opacity: 1;
}

.card .card-img-overlay h5 {
    color: white; /* 텍스트의 색상을 흰색으로 변경 */
}

</style>
</head>

<!-- body 시작점 -->
<body onload="showImage()">
    
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


            <img src="picture/hand.png"
             class="d-block w-100 carousel-img-size" alt="...">
          </div>
          <div class="carousel-item img-size-control">
            <img src="picture/background.png"
            class="d-block w-100 carousel-img-size" alt="...">
          </div>
          <div class="carousel-item img-size-control">
            <img src="picture/인간들.jpg"

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
			
		<div class="bighome">
		<div class="cardbox">
		<p class="page">EXHIBITION</p>
		<p class="more"><a class="a-more" href="exhibition">more</a><p>
		<div class="card">
        <img id="introImg" border="0">
        </div>
        </div>
        
        <div class="marketzone">
        <p class="page">MARKET</p>
		<p class="more"><a class="a-more" href="market">more</a><p>
		<div class="market-images">
        <c:if test="${ market.size() ne 0 }">
	        <c:forEach begin="0" end="${ market.size() - 1 ge 2 ? 2 : market.size() - 1}" step="1" var="index">
	        		<div class="market-img"><img class="marketimg" src="/fourmen/uploads/${market[index].workImages[0].savedFileName}"/></div>
	        </c:forEach>
        </c:if>
        </div>
        <div class="enter"></div>
        <p class="artist">ARTISTS</p>
       	<p class="more"><a class="a-more" href="artist">more</a><p>
        </div>
        
        </div>
        <div class="artistzone">
        	<div class="artistzone-size">
	        	<div id = "artist_container">
				<c:forEach var="a" items="${artist}" varStatus = "loop">
					<c:url var="artist_details" value="/artist/artist_details">
						<c:param name="userid" value="${a.userid}"/>
					</c:url>
					
					<a href="${artist_details}">
						<div class="card text-bg-light">
							<c:forEach var="img" items ="${artistImg}">
								<c:if test="${a.userid eq img.userid}">
									<div class="artist-img"><img class="artistimg" src="images/char/${img.artist_s_img}" onerror="this.src='images/char/default_user.png'" class="card-img" alt="test_user"/></div>
								</c:if>
							</c:forEach>
									
							<div class="card-img-overlay">
								<h5 class="card-title fs-2">${a.nickname}</h5>
							</div>
						</div>
					</a>
				</c:forEach>
				</div>
        	</div>
        </div>
    </main>  
        
</body>
			
	
       
    
    
    
    
    
    
    <!-- 푸터 파일 include -->
    <%@ include file="fragments/footer.jspf" %>
	 <script type="text/javascript">
        var imgArray = new Array();
        imgArray[0] = "image/exhibition1.jpg";
        imgArray[1] = "image/exhibition2.jpg";
        imgArray[2] = "image/exhibition3.png";
        imgArray[3] = "image/exhibition4.jpg";
        imgArray[4] = "image/exhibition5.png";
        imgArray[5] = "image/exhibition6.png";
        imgArray[6] = "image/exhibition7.jpg";
        imgArray[7] = "image/exhibition8.jpg";
        imgArray[8] = "image/exhibition9.png";
        imgArray[9] = "image/exhibition10.jpg";
        imgArray[10] = "image/exhibition11.png";
        imgArray[11] = "image/exhibition12.jpg";
        imgArray[12] = "image/exhibition13.png";
        imgArray[13] = "image/exhibition14.jpg";
        imgArray[14] = "image/exhibition15.png";
        imgArray[15] = "image/exhibition16.jpg";
 
        function showImage() {
            var imgNum = Math.round(Math.random() * 9);
            var objImg = document.getElementById("introImg");
            objImg.src = imgArray[imgNum];
 
            setTimeout("showImage()", 4000);
        }
 
        
    </script>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
		crossorigin="anonymous"></script>
    <script src="js/header.js"></script>
	
</body>
</html>