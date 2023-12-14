<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Fourmen</title>
		
		<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" 
		integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN"
		 crossorigin="anonymous">
         
         <link rel="stylesheet" href="../css/header.css">
         <link rel="stylesheet" href="../css/market-detail.css">
         <link rel="stylesheet" href="../css/footer.css">
		
	</head>
	<body>
		<%@ include file="../fragments/navigation.jspf" %>
		
		<section class="product-container">
            <div class="photo-container">
                <img src="https://m.picturemall.co.kr/web/product/big/202011/9c418fbb88f4aa60a9780c7c871378db.jpg">
            </div>
            
            <div class="product-description">
                <h1>마이크를 든 단단히 화가난 할아버지</h1>
                <div>by <a href="#">아트아카이브 회원1</a></div>
                <div><b>50,000원</b></div>
                <div>20.3 x 20.3 x 5.1 cm</div>
                <div>작품 연도: 2002년</div>
                
                <div class="text-center">
                    <button class="btn btn-danger w-75">찜하기</button>
                    <button class="btn btn-primary w-75">쪽지 보내기</button><br>
                    
                </div>
            </div>
        </section>
        
        
        
        
        <%@ include file="../fragments/footer.jspf" %>
        
         <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
		  integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
		   crossorigin="anonymous"></script>
		
        <script src="../js/header.js"></script>
        
	</body>
	</html>