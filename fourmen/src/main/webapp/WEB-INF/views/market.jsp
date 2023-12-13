<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Fourmen</title>
		
		
		<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
		 rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" 
		 crossorigin="anonymous">
         
         <link rel="stylesheet" href="css/header.css">
         <link rel="stylesheet" href="css/market-board.css">
		 <link rel="stylesheet" href="css/footer.css">
        
	</head>
	
	<body>
		<%@include file="fragments/navigation.jspf" %>
		
        <section role="search" class="search-container">
            <form id="search-form">
            <div class="search-detail-container">
                <!-- 검색 카테고리 내역 -->
                    상세 검색:<br>          
                    <input type="checkbox" id="oriental" name="category">
                    <label for="oriental">동양화</label>
                    <input type="checkbox" id="western" name="category">
                    <label for="western">서양화</label>
                    <input type="checkbox" id="portrait" name="category">
                    <label for="portrait">초상화</label>
                    <input type="checkbox" id="statue" name="category">
                    <label for="statue">조각</label>
                    <input type="checkbox" id="modern-art" name="category">
                    <label for="modern-art">현대미술</label>
                    
                    <input type="checkbox" id="oriental" class="expand" name="category">
                    <label for="oriental" class="expand">동양화</label>
                    <input type="checkbox" id="western" class="expand" name="category">
                    <label for="western" class="expand">서양화</label>
                    <input type="checkbox" id="portrait" class="expand" name="category">
                    <label for="portrait" class="expand">초상화</label>
                    <input type="checkbox" id="statue" class="expand" name="category">
                    <label for="statue" class="expand">조각</label>
                    <input type="checkbox" id="modern-art" class="expand" name="category">
                    <label for="modern-art" class="expand">현대미술</label>
                    
                    <!-- 버튼 누르면 전체 카테고리 내역 확장해서 보여줌 -->
                    <button class="search-form-btn" type="button">
                        <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-plus-lg" viewBox="0 0 16 16">
                          <path fill-rule="evenodd" d="M8 2a.5.5 0 0 1 .5.5v5h5a.5.5 0 0 1 0 1h-5v5a.5.5 0 0 1-1 0v-5h-5a.5.5 0 0 1 0-1h5v-5A.5.5 0 0 1 8 2"/>
                        </svg>
                    </button><br>
                    
                    <!-- 가격대 설정 -->
                    <div class="price-range-container">
                        <label for="low-price-border">가격대</label>
                        <input id="low-price-border" name="min-price" class="price-range" type="type"> ~ 
                        <input type="type" name="max-price" class="price-range">
                    </div>
                </div>
                
                <!-- 검색어 input -->
                <input class="search-keyword" name="keyword">
                <button id="btnSearch">
                    <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-search" viewBox="0 0 16 16">
                        <path d="M11.742 10.344a6.5 6.5 0 1 0-1.397 1.398h-.001c.03.04.062.078.098.115l3.85 3.85a1 1 0 0 0 1.415-1.414l-3.85-3.85a1.007 1.007 0 0 0-.115-.1zM12 6.5a5.5 5.5 0 1 1-11 0 5.5 5.5 0 0 1 11 0"/>
                    </svg>
                </button>                                       
            </form>
        </section>
        
        
        <main>
            <div>
                <a href="#">인기 리스트</a>
                <a href="#">최신 리스트</a>
            </div>
            <div class="list-container">
                <!-- TODO: 나중에 JSTL의 for문으로 대체 가능한 부분 -->
                <div class="listed-item-container">
                    <div class="item-image-container">
                        <div class="image-size-controller">
                            <img src="https://images.tennis.com/image/private/t_q-best/tenniscom-prd/a34ajom83aptn8iwwodh.jpg">
                        </div>
                    </div>
                    <!-- 아이템 제목, 가격, 업로드 일자 등 -->
                    <div class="item-info-container">
                        <p><b>이 상품은 판다입니다.</b></p>
                        <p>가격: 50,000원</p>
                    </div>
                </div>
                
                <!-- 테스트 시작 -->
                <div class="listed-item-container">
                    <div class="item-image-container">
                        <div class="image-size-controller">
                            <img src="https://images.tennis.com/image/private/t_q-best/tenniscom-prd/a34ajom83aptn8iwwodh.jpg">
                        </div>
                    </div>
                    <!-- 아이템 제목, 가격, 업로드 일자 등 -->
                    <div class="item-info-container">
                        <p><b>이 상품은 판다입니다.</b></p>
                        <p>가격: 50,000원</p>
                    </div>
                </div>
                                <div class="listed-item-container">
                    <div class="item-image-container">
                        <div class="image-size-controller">
                            <img src="https://images.tennis.com/image/private/t_q-best/tenniscom-prd/a34ajom83aptn8iwwodh.jpg">
                        </div>
                    </div>
                    <!-- 아이템 제목, 가격, 업로드 일자 등 -->
                    <div class="item-info-container">
                        <p><b>이 상품은 판다입니다.</b></p>
                        <p>가격: 50,000원</p>
                    </div>
                </div>
                                <div class="listed-item-container">
                    <div class="item-image-container">
                        <div class="image-size-controller">
                            <img src="https://images.tennis.com/image/private/t_q-best/tenniscom-prd/a34ajom83aptn8iwwodh.jpg">
                        </div>
                    </div>
                    <!-- 아이템 제목, 가격, 업로드 일자 등 -->
                    <div class="item-info-container">
                        <p><b>이 상품은 판다입니다.</b></p>
                        <p>가격: 50,000원</p>
                    </div>
                </div>
                                <div class="listed-item-container">
                    <div class="item-image-container">
                        <div class="image-size-controller">
                            <img src="https://images.tennis.com/image/private/t_q-best/tenniscom-prd/a34ajom83aptn8iwwodh.jpg">
                        </div>
                    </div>
                    <!-- 아이템 제목, 가격, 업로드 일자 등 -->
                    <div class="item-info-container">
                        <p><b>이 상품은 판다입니다.</b></p>
                        <p>가격: 50,000원</p>
                    </div>
                </div>
                                <div class="listed-item-container">
                    <div class="item-image-container">
                        <div class="image-size-controller">
                            <img src="https://images.tennis.com/image/private/t_q-best/tenniscom-prd/a34ajom83aptn8iwwodh.jpg">
                        </div>
                    </div>
                    <!-- 아이템 제목, 가격, 업로드 일자 등 -->
                    <div class="item-info-container">
                        <p><b>이 상품은 판다입니다.</b></p>
                        <p>가격: 50,000원</p>
                    </div>
                </div>





                       
                             
                
                <!-- 테스트 끝 -->
                
                
                
                
            </div>
        </main>
        
        
        <%@ include file="fragments/footer.jspf" %>
        
		<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
		 integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
		  crossorigin="anonymous"></script>
          
        <script src="js/header.js"></script>
  </body>
		
	</body>
</html>