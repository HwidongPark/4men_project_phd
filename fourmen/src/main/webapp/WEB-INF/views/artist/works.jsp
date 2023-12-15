<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head>
<title>FOURMEN</title>
<meta charset="UTF-8" name="viewport"
	content="width=device-width, initial-scale=1" />
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN"
	crossorigin="anonymous">
<link rel="stylesheet" href="../css/header.css">
<link rel="stylesheet" href="../css/footer.css">
<style>
@font-face {
    font-family: 'Pretendard-Regular';
    src: url('https://cdn.jsdelivr.net/gh/Project-Noonnu/noonfonts_2107@1.1/Pretendard-Regular.woff') format('woff');
    font-weight: 400;
    font-style: normal;
}

div#works {
	font-family: 'Pretendard-Regular';
	margin: 0 auto;
	display: grid;
	grid-template-columns: repeat(auto-fit, minmax(500px, 1fr));
	grid-column-gap: 10px;
	grid-row-gap: 20px;
}

div#works-bottom{
	font-family: 'Pretendard-Regular';
}

.card .card-img {
	width:100%;
	height:100%;
	object-fit: cover;
}


div#Works_Title{
	font-size: 50px;
	color: #FF9B00;
}

html {
	scroll-behavior: smooth;
}



@media ( prefers-reduced-motion : reduce) {
	html {
		scroll-behavior: auto;
	}
}
</style>
</head>
<body>
	<%@ include file="../fragments/navigation.jspf"%>

	<div id="works" class="my-5 container-fluid container-xl w-75">

		<div class="card border-0">
			<div id="Works_Title" class="card-text">
				<p class="card-text">자화상</p>
			</div>
			
			<div class="card p-4 border-0">
				<p class="card-text fs-5">네덜란드 출신의 화가인 빈센트 반 고흐는 가장 불운했던 화가 중 한
					명으로 꼽힌다. 고흐는 그 생전에는 제대로 된 평을 받지 못했고 작품 역시 팔지 못했으나 그의 사후에야 명성을 얻게
					되었다. 또한 오늘날에 이르러 고흐는 가장 사랑받는 화가 중 한 명이기도 하다. 고흐의 불운한 삶을 더 극대화한 것은
					그가 앓고 있던 정신질환이었다. 고흐의 불안한 정신 상태는 그의 작품에서 나타나는 구불구불한 선들에 반영된 것으로
					이해된다. 무엇보다 고흐의 삶을 더 극적으로 만든 사건은 1888년대 크리스마스 즈음에 고흐가 귀를 자른 일이었다. 더
					놀라운 점은 이렇게 이상한 행위를 한 뒤 귀에 붕대를 감은 자신의 모습을 그림으로 담았다는 사실이다.</p>
			</div>
			<div class="card p-4 border-0">
				<p class="card-text fs-5">On July 14, 2022, an almost certainly
					authentic self-portrait of van Gogh was uncovered under his 1885
					painting "Head of a peasant woman". Lesley Stevenson, a conservator
					at the Scottish National Gallery of Modern Art, discovered it
					during an X-ray examination of their existing pieces. It shows a
					bearded van Gogh in a brimmed hat and a neckerchief around his
					throat. His left ear was clearly visible. The portrait is covered
					under layers of cardboard and glue, which experts are searching for
					ways to remove in order to confirm its authenticity. They believe
					it was painted when van Gogh moved to France and learnt about the
					work of the impressionists there, an experience that influenced his
					more colourful and expressive style that is much admired today. The
					Dutch painter is known to often reuse his canvases or work on their
					reverse in order to save money. The X-ray image will be featured at
					a Royal Scottish Academy exhibit in Edinburgh and displayed using a
					specially made lightbox.</p>
			</div>
		</div>
		
		<div class = "card border-0 rounded-0">
			<img class="card-img mb-5 rounded-0" src = "https://courtauld.ac.uk/wp-content/uploads/2021/04/vsn-gogh-self-portrait-P.1948.SC_.175_cs-scaled.jpg">
			<img class="card-img mb-5 rounded-0" src = "https://upload.wikimedia.org/wikipedia/commons/f/f6/Zelfportret_Rijksmuseum_SK-A-3262.jpeg">
			<img class="card-img mb-5 rounded-0" src = "https://upload.wikimedia.org/wikipedia/commons/0/02/Van_Gogh_-_Selbstbildnis_mit_verbundenem_Ohr_und_Pfeife.jpeg">
		</div>
	</div>

	<div id="works-bottom" class="my-5 p-0 container-fluid container-xl w-75">
		<!--  댓글 작성 -->
		<div class="my-4 card border-light mb-3" id="comment">
			<div class="card-header d-inline-flex gap-1">
				<button id="btnToggleComment" class="btn fs-5">작품 댓글 달기...</button>
			</div>
			<div class="card-body collapse" id="collapseComments">
				<div>
					<div class="row my-2">
						<div class="col-10">
							<textarea class="form-control" id="commentText" autofocus></textarea>
						</div>
						<div class="col-2">
							<button class="btn fs-4" id="btnAddComment">등록</button>
						</div>
					</div>

					<div class="my-2" id="comments">댓글 목록</div>
				</div>
			</div>
		</div>

		<!-- 뒤로가기 및 위로 올라가기 버튼 -->
		<div class="d-flex justify-content-between">

			<button id="btn_back_to_list" type="button" class="btn fs-5"
				onclick="history.back()">목록으로</button>


			<button id="btn-up-position" class="btn fs-5"
				onclick="window.scrollTo(0,0);">위로 가기</button>

		</div>

	</div>

	<%@ include file="../fragments/footer.jspf"%>

	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
		crossorigin="anonymous"></script>
	<script src="../js/works-comments.js"></script>

</body>
</html>