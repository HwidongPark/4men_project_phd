<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>people example</title>
<meta name='viewport' content='width=device-width, initial-scale=1'>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN"
	crossorigin="anonymous">

<link rel="stylesheet" href="css/header.css">

<link rel="stylesheet" href="css/footer.css">

<style>
div#artist_container {

	margin: 0 auto;
	display: grid;
	grid-template-columns: repeat(auto-fit, minmax(240px, 1fr));
	grid-column-gap: 30px;
	grid-row-gap: 40px;
}

div#artist {
	grid-auto-rows: 200px;
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
<body>
	<!-- 네비게이션 -->
	<%@ include file="fragments/navigation.jspf"%>
	<div id="works" class="my-5 container-fluid w-75">
		<div id = "artist_container">
			<a href="artist/artist_details">
				<div class="card text-bg-light">
					<img src="images/char/VanGogh.png" 
					class="card-img" alt="van_Gogh" />
					<div class="card-img-overlay">
						<h5 class="card-title fs-2">Van Gogh</h5>
					</div>
				</div>
			</a>
			<a href="artist/artist_details">
				<div class="card text-bg-light">
					<img src="images/char/ClaudeMonet.png" 
					class="card-img" alt="van_Gogh" />
					<div class="card-img-overlay">
						<h5 class="card-title fs-2">Claude Monet</h5>
					</div>
				</div>
			</a>
			<a href="artist/artist_details">
				<div class="card text-bg-light">
					<img src="images/char/PabloPicasso.png" 
					class="card-img" alt="van_Gogh" />
					<div class="card-img-overlay">
						<h5 class="card-title fs-2">Pablo Picasso</h5>
					</div>
				</div>
			</a>
			<a href="artist/artist_details">
				<div class="card text-bg-light">
					<img src="images/char/SalvadorDali.png" 
					class="card-img" alt="van_Gogh" />
					<div class="card-img-overlay">
						<h5 class="card-title fs-2">Salvador Dali</h5>
					</div>
				</div>
			</a>
			<a href="artist/artist_details">
				<div class="card text-bg-light">
					<img src="images/char/VanGogh.png" 
					class="card-img" alt="van_Gogh" />
					<div class="card-img-overlay">
						<h5 class="card-title fs-2">Van Gogh</h5>
					</div>
				</div>
			</a>
			<a href="artist/artist_details">
				<div class="card text-bg-light">
					<img src="images/char/ClaudeMonet.png" 
					class="card-img" alt="van_Gogh" />
					<div class="card-img-overlay">
						<h5 class="card-title fs-2">Claude Monet</h5>
					</div>
				</div>
			</a>
			<a href="artist/artist_details">
				<div class="card text-bg-light">
					<img src="images/char/PabloPicasso.png" 
					class="card-img" alt="van_Gogh" />
					<div class="card-img-overlay">
						<h5 class="card-title fs-2">Pablo Picasso</h5>
					</div>
				</div>
			</a>
			<a href="artist/artist_details">
				<div class="card text-bg-light">
					<img src="images/char/SalvadorDali.png" 
					class="card-img" alt="van_Gogh" />
					<div class="card-img-overlay">
						<h5 class="card-title fs-2">Salvador Dali</h5>
					</div>
				</div>
			</a>
			<a href="artist/artist_details">
				<div class="card text-bg-light">
					<img src="images/char/VanGogh.png" 
					class="card-img" alt="van_Gogh" />
					<div class="card-img-overlay">
						<h5 class="card-title fs-2">Van Gogh</h5>
					</div>
				</div>
			</a>
			<a href="artist/artist_details">
				<div class="card text-bg-light">
					<img src="images/char/ClaudeMonet.png" 
					class="card-img" alt="van_Gogh" />
					<div class="card-img-overlay">
						<h5 class="card-title fs-2">Claude Monet</h5>
					</div>
				</div>
			</a>
			<a href="artist/artist_details">
				<div class="card text-bg-light">
					<img src="images/char/PabloPicasso.png" 
					class="card-img" alt="van_Gogh" />
					<div class="card-img-overlay">
						<h5 class="card-title fs-2">Pablo Picasso</h5>
					</div>
				</div>
			</a>
			<a href="artist/artist_details">
				<div class="card text-bg-light">
					<img src="images/char/SalvadorDali.png" 
					class="card-img" alt="van_Gogh" />
					<div class="card-img-overlay">
						<h5 class="card-title fs-2">Salvador Dali</h5>
					</div>
				</div>
			</a>
		</div>
	</div>	
	<%@ include file="fragments/footer.jspf" %>
    

    <script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
		crossorigin="anonymous"></script>
		
		<script src='js/main.js'></script>
</body>
</html>