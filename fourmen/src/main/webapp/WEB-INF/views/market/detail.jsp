<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Artists Archive</title>
		
		<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" 
		integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN"
		 crossorigin="anonymous">
         <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200" />
        <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200" />
         
         <link rel="stylesheet" href="../css/header.css">
         <link rel="stylesheet" href="../css/market-detail.css">
         <link rel="stylesheet" href="../css/footer.css">
		<style>
    		@font-face {
            font-family: 'NEXON Lv1 Gothic OTF';
            src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_20-04@2.1/NEXON Lv1 Gothic OTF.woff') format('woff');
            font-weight: normal;
            font-style: normal;
             }
            .gfont {
            	font-family: 'NEXON Lv1 Gothic OTF';
            }
		</style>
	</head>
	<body class="gfont">
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
                        <em><a href="/fourmen/artist/artist_details?userid=${ artistDto.userid }" id="artist-name">${ artistDto.nickname }</a></em>
                        <div id="artist-bio">                                                                                                               
                            ${ marketPost.artist.artist_bio_kor }
                            <br><br>
                            ${ marketPost.artist.artist_bio_eng }
                        </div>
                        <button class="btn-read-more">Read More</button> 
                    </div> 
                </div>
                <div class="text-center">
                    <!-- Button trigger modal -->
                    <button type="button" id="market-request-deal" class="btn btn-secondary my-1 btn-rounded"
                        data-bs-toggle="modal" data-bs-target="#requestDeal">
                        거래요청 쪽지 보내기
                    </button><br />
                    
                    <!-- Modal -->
                    <div class="modal fade modal-to-show" tabindex="-1"
                        aria-labelledby="exampleModalLabel" aria-hidden="true">
                        <div class="modal-dialog">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <h1 class="modal-title fs-5" id="exampleModalLabel">
                                        거래요청 쪽지 보내기
                                    </h1>
                                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                </div>
                                <div class="modal-body">
                                    <form method="post" action="/fourmen/mypage/message" id="request-deal-form">
                                        <div class="modal-label">
                                            <label class="mb-1">제목</label>
                                        </div>
                                        <input id="market-message-title" type="text" name="title" class="form-control mb-2" maxlength="50" required/>
                                        <div class="modal-label">
                                            <label class="mb-1">내용</label>
                                        </div>
                                        <textarea id="market-message-content" class="form-control mb-2" name="content" rows="10" maxlength="1000" required></textarea>
                                        <div>
                                            <label class="mb-1">제시 가격</label>
                                            <input id="market-message-price-offered" type="number" name="price_offered" value="${ marketPost.price }" class="form-control" required/>
                                        </div>
                                        <input type="hidden" name="workId" value="${ marketPost.workId }">
                                        <input type="hidden" name="recipient" value="${ marketPost.userId }">
                                        <input type="hidden" name="sender" value="${ signedInUser }">
                                    </form>
                                </div>
                                <div class="modal-footer">
                                    <button type="button" id="market-request-deal-close" class="btn btn-secondary" data-bs-dismiss="modal">
                                        닫기
                                    </button>
                                    <button id="market-send-request" type="button" class="btn btn-dark">
                                        거래 요청
                                    </button>
                                </div>
                            </div>
                        </div>
                    </div>
                    <c:choose>
                        <c:when test="${ marketPost.isSold == 'Y' }">
                            <button class="btn btn-dark btn-rounded">이미 거래 종료된 물건</button>
                            <button id="market-add-to-wishlist" class="btn btn-danger btn-rounded d-none">찜하기</button>
                            <button id="market-remove-from-wishlist" class="btn btn-success btn-rounded d-none">찜 취소</button>
                        </c:when>
                        <c:otherwise>
                            <c:if test="${ isWishListed le 0 }">
                                <button id="market-add-to-wishlist" class="btn btn-danger btn-rounded">찜하기</button>
                                <button id="market-remove-from-wishlist" class="btn btn-success btn-rounded d-none">찜 취소</button>
                            </c:if>
                            <c:if test="${ isWishListed ge 1 }">
                                <button id="market-add-to-wishlist" class="btn btn-danger btn-rounded d-none">찜하기</button>
                                <button id="market-remove-from-wishlist" class="btn btn-success btn-rounded">찜 취소</button>
                            </c:if>
                        </c:otherwise>
                    </c:choose>          
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
        <c:if test="${ signedInUser == marketPost.userId && marketPost.isSold != 'Y' }">
            <div class="container text-right my-3 d-grid gap-2 d-md-flex justify-content-md-end market-btn-container">
                <form action="/fourmen/market/delete" method="post">
                    <input id="workid" name="workid" class="d-none" type="text" value="${ marketPost.workId }">                     
                    <input type="submit" value="삭제" id="market-btn-delete" class="btn btn-outline-secondary mr-2"/>
                </form>
                
                <form action="/fourmen/market/update" method="post">
                    <input id="workid" name="workid" class="d-none" type="text" value="${ marketPost.workId }">
                    <button id="market-btn-update" class="btn btn-dark">수정</button>
                </form>
            </div>
        </c:if>
        <!-- JavaScript에서 사용하기 위해서.. -->
        <input id="workid" name="workid" class="d-none" type="text" value="${ marketPost.workId }">                     
        
        <%@ include file="../fragments/footer.jspf" %>
        
         <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
		  integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
		   crossorigin="anonymous"></script>
		
        <script>
            const signedInUser = '${signedInUser}';
            const isSold = `${ marketPost.isSold }`;
        </script>
        <script src="https://cdn.jsdelivr.net/npm/axios@1.1.2/dist/axios.min.js"></script>
        <script src="../js/line-control.js"></script>
        <script src="../js/header.js"></script>
        <script src="../js/market/market-detail.js"></script>
                
	</body>
	</html>