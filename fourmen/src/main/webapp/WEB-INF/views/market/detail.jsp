<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Fourmen</title>
		
		<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" 
		integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN"
		 crossorigin="anonymous">
         <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200" />
        <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200" />
         
         <link rel="stylesheet" href="../css/header.css">
         <link rel="stylesheet" href="../css/market-detail.css">
         <link rel="stylesheet" href="../css/footer.css">
		
	</head>
	<body>
		<%@ include file="../fragments/navigation.jspf" %>
		
        <section class="product-container">
            
            <!-- JSTL로 사진 대체 -->
            <div id="market-photo-container" class="photo-container">
                <div class="carousel-outer-container">
                    <button id="prev-slide" class="material-symbols-outlined slide-button">chevron_left</button>
                    <div class="carousel-images-container">
                        <div class="carousel-images-wrapper">
                            <c:forEach var="postImage" items="${ marketPost.workImages }">
                                <div class="carousel-each-image">
                                    <img src="/fourmen/uploads/${ postImage.savedFileName }" class="carousel-image" alt="supposed to be an image">
                                </div>
                            </c:forEach>                       
                        </div>
                    </div>
                    <button id="next-slide" class="material-symbols-outlined slide-button">chevron_right</button>
                </div>
                <!-- 작은사진 -->
                <div class="image-slider-container">
                    <div class="image-slide-wrapper">
                        <c:forEach var="postImage" items="${ marketPost.workImages }">
                            <img class="image-slide-item" src="/fourmen/uploads/${ postImage.savedFileName }" alt="123">
                        </c:forEach>
                    </div>
                </div>
            </div>
            
            
            <div class="product-description">
                <h1 id="product-title">${ marketPost.title }</h1>
                <c:if test="${ not empty marketPost.yearCreated }">
                    <div id="production-year" class="year-dimension-description"><em>(${ marketPost.yearCreated })</em></div>
                </c:if>
                <c:if test="${ marketPost.paintingSize ne 'n x n x n cm' }">
                    <div id="product-dimension" class="year-dimension-description"><em>${ marketPost.paintingSize }</em></div>
                </c:if>
                <hr>
                <div id="product-price"><b><fmt:formatNumber type="number" maxFractionDigits="2" value="${ marketPost.price }" />원</b></div>
                
                <hr>
                <div id="seller-description">
                     <div id="artist">
                        <input id="artist-userid" value="${ marketPost.userId }" type="hidden" />
                        <em><a href="#" id="artist-name">${ marketPost.nickname }</a></em>
                        <div id="artist-bio">
                            저는 어렸을때부터 코끼리 그림을 그렸던 예술가입니다. 저의 작품은 매우 질이 좋습니다. 저의 그림실력은 뛰어납니다.
                            지금이 저의 작품을 살 절호의 기회입니다. 지금 저의 작품은 비록 5만원이지만, 훗날 사람들이 저의 진가를 알아보고 가격이 뛸 것이라 생각합니다.
                            저의 작품은 2013년 당시의 비트코인과 같습니다. 저의 페이지에 들어와서 포트폴리오를 확인해보세요
                            저는 어렸을때부터 코끼리 그림을 그렸던 예술가입니다. 저의 작품은 매우 질이 좋습니다. 저의 그림실력은 뛰어납니다.
                            지금이 저의 작품을 살 절호의 기회입니다. 지금 저의 작품은 비록 5만원이지만, 훗날 사람들이 저의 진가를 알아보고 가격이 뛸 것이라 생각합니다.
                            저의 작품은 2013년 당시의 비트코인과 같습니다. 저의 페이지에 들어와서 포트폴리오를 확인해보세요
                            저는 어렸을때부터 코끼리 그림을 그렸던 예술가입니다. 저의 작품은 매우 질이 좋습니다. 저의 그림실력은 뛰어납니다.
                            지금이 저의 작품을 살 절호의 기회입니다. 지금 저의 작품은 비록 5만원이지만, 훗날 사람들이 저의 진가를 알아보고 가격이 뛸 것이라 생각합니다.
                            저의 작품은 2013년 당시의 비트코인과 같습니다. 저의 페이지에 들어와서 포트폴리오를 확인해보세요
                            저는 어렸을때부터 코끼리 그림을 그렸던 예술가입니다. 저의 작품은 매우 질이 좋습니다. 저의 그림실력은 뛰어납니다.
                            지금이 저의 작품을 살 절호의 기회입니다. 지금 저의 작품은 비록 5만원이지만, 훗날 사람들이 저의 진가를 알아보고 가격이 뛸 것이라 생각합니다.
                            저의 작품은 2013년 당시의 비트코인과 같습니다. 저의 페이지에 들어와서 포트폴리오를 확인해보세요
                            저는 어렸을때부터 코끼리 그림을 그렸던 예술가입니다. 저의 작품은 매우 질이 좋습니다. 저의 그림실력은 뛰어납니다.
                            지금이 저의 작품을 살 절호의 기회입니다. 지금 저의 작품은 비록 5만원이지만, 훗날 사람들이 저의 진가를 알아보고 가격이 뛸 것이라 생각합니다.
                            저의 작품은 2013년 당시의 비트코인과 같습니다. 저의 페이지에 들어와서 포트폴리오를 확인해보세요
                            저는 어렸을때부터 코끼리 그림을 그렸던 예술가입니다. 저의 작품은 매우 질이 좋습니다. 저의 그림실력은 뛰어납니다.
                            지금이 저의 작품을 살 절호의 기회입니다. 지금 저의 작품은 비록 5만원이지만, 훗날 사람들이 저의 진가를 알아보고 가격이 뛸 것이라 생각합니다.
                            저의 작품은 2013년 당시의 비트코인과 같습니다. 저의 페이지에 들어와서 포트폴리오를 확인해보세요
                            저는 어렸을때부터 코끼리 그림을 그렸던 예술가입니다. 저의 작품은 매우 질이 좋습니다. 저의 그림실력은 뛰어납니다.
                            지금이 저의 작품을 살 절호의 기회입니다. 지금 저의 작품은 비록 5만원이지만, 훗날 사람들이 저의 진가를 알아보고 가격이 뛸 것이라 생각합니다.
                            저의 작품은 2013년 당시의 비트코인과 같습니다. 저의 페이지에 들어와서 포트폴리오를 확인해보세요
                            저는 어렸을때부터 코끼리 그림을 그렸던 예술가입니다. 저의 작품은 매우 질이 좋습니다. 저의 그림실력은 뛰어납니다.
                            지금이 저의 작품을 살 절호의 기회입니다. 지금 저의 작품은 비록 5만원이지만, 훗날 사람들이 저의 진가를 알아보고 가격이 뛸 것이라 생각합니다.
                            저의 작품은 2013년 당시의 비트코인과 같습니다. 저의 페이지에 들어와서 포트폴리오를 확인해보세요
                        </div>
                        <button class="btn-read-more">Read More</button> 
                    </div> 
                </div>
                <div class="text-center">
                    <button id="market-request-deal" class="btn btn-secondary my-1 btn-rounded">거래요청 쪽지 보내기</button><br>            
                    <button id="market-add-to-wishlist" class="btn btn-danger btn-rounded">찜하기</button>                    
                </div>
            </div>

        </section>
        
        <!-- 작품 설명 -->
        <section class="product-detail-description">
            <hr>
            <div>
                <h2>Description</h2>
            </div>
            <div>
                <p>
                    ${ marketPost.descriptionKor }                
                </p>
            </div>
        </section>
        <!-- 수정 삭제 버튼 -->
        <div class="container text-right my-3 d-grid gap-2 d-md-flex justify-content-md-end market-btn-container">
            
            <form action="/fourmen/market/delete" method="post">
                <input id="workid" name="workid" class="d-none" type="text" value="${ marketPost.workId }">                     
                <input type="submit" value="삭제" id="market-btn-delete" class="btn btn-outline-danger mr-2"/>
            </form>
            
            <form action="/fourmen/market/update" method="post">
                <input id="workid" name="workid" class="d-none" type="text" value="${ marketPost.workId }">
                <button id="market-btn-update" class="btn btn-outline-primary">수정</button>
            </form>
        </div>
        
        <%@ include file="../fragments/footer.jspf" %>
        
         <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
		  integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
		   crossorigin="anonymous"></script>
		
        <script>
            const signedInUser = '${signedInUser}';
        </script>
        <script src="https://cdn.jsdelivr.net/npm/axios@1.1.2/dist/axios.min.js"></script>
        <script src="../js/line-control.js"></script>
        <script src="../js/header.js"></script>
        <script src="../js/market/market-detail.js"></script>
                
	</body>
	</html>