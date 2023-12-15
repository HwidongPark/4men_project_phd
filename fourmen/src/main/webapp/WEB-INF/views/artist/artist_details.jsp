<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head>
<title>FOURMEN</title>
<meta charset="UTF-8">
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
div#details {
	
	font-family: 'Pretendard-Regular';
}


@media (max-width: 1200px) {
    .row-g-0-reverse {
        flex-direction: column-reverse;
    }

    .col-md-6 {
        flex: 0 0 100%;
        max-width: 100%;
    }
}

</style>

</head>
<body>
	<!-- 헤더 파일 include -->
	<%@ include file="../fragments/navigation.jspf"%>

	<div id="details" class="my-5 container-fluid w-75">


		<div class="card mb-3 border-0" style="max-width: 100%;">
			<div class="row g-0">
				<div class="col-md-6">
					<img src="../images/char/VanGogh.png"
						class="img-fluid rounded-0" alt="Van Gogh">
				</div>
				<div class="col-md-6">
					<div class="card-body">
						<h5 class="fs-1 card-title text-success fw-bolder">Vincent Willem van Gogh</h5>
						<p class="fs-5 card-text">네덜란드의 화가로서 일반적으로 서양 미술사상 가장 위대한 화가 중 한
							사람으로 여겨진다. 그는 자신의 작품 전부(900여 점의 그림들과 1100여 점의 습작들)를 정신질환(측두엽
							기능장애로 추측됨)을 앓고 자살을 감행하기 전의 단지 10년 동안에 만들어냈다. 그는 살아있는 동안 거의 성공을
							거두지 못하고 사후에 비로소 알려졌는데, 특히 1901년 3월 17일 파리에서 71점의 그림을 전시한 이후 명성을
							얻게 되었다.</p> <br><br>
						<p class="fs-5 card-text">
							<small class="text-body-secondary">Dutch
								Post-Impressionist painter who is among the most famous and
								influential figures in the history of Western art. In just over
								a decade he created approximately 2100 artworks, including
								around 860 oil paintings, most of them in the last two years of
								his life. They include landscapes, still lifes, portraits and
								self-portraits, and are characterised by bold, symbolic colours,
								and dramatic, impulsive and highly expressive brushwork that
								contributed to the foundations of modern art. Only one of his
								paintings was known by name to have been sold during his
								lifetime. Van Gogh became famous after his suicide at age 37,
								which followed years of poverty and mental illness.</small>
						</p>
					</div>
				</div>
			</div>
		</div>

		<div class="mt-4">
			<table class="table table-striped fs-5">
				<thead>
					<tr>
						<th class="col-2" >#</th>
						<th class="col-7" >작품제목</th>
						<th class="col-3" >등록일자</th>
					</tr>
				</thead>
				<tbody class="table-group-divider">
					<tr>
						<th scope="row">1</th>
						<td><a href="works">자화상</a></td>
						<td>#</td>
					</tr>
					<tr>
						<th scope="row">2</th>
						<td><a href="works">자화상</a></td>
						<td>#</td>
					</tr>
					<tr>
						<th scope="row">3</th>
						<td><a href="works">자화상</a></td>
						<td>#</td>
					</tr>
					<tr>
						<th scope="row">4</th>
						<td><a href="works">자화상</a></td>
						<td>#</td>
					</tr>
				</tbody>
			</table>
		</div>
	</div>

	<%@ include file="../fragments/footer.jspf"%>

	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
		crossorigin="anonymous"></script>
</body>
</html>