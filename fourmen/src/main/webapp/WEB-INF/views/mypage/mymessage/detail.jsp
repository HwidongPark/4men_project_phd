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
		
	</head>
	
	<body>
		<h1>쪽지</h1>
        <form>
            <input type="text" id="messageId" class="d-none" value="${ messageDto.id }">
            <label class="mb-1">제목</label>
            <input type="text" class="form-control mb-2" value="${ messageDto.title }" readonly>
            <label class="mb-1">내용</label>
            <textarea id="message-detail-content" class="form-control" rows="22" readonly>${ messageDto.content }</textarea>
            <div class="mb-4">
                <a href="/fourmen/market/detail?workid=${ messageDto.workId }" class="float-end mx-2">&gt;&gt;&gt;게시글 보러가기</a>
            </div>
            <div class="my-2">
                <label>내 제시 가격</label>
                <input type="text" value="${ messageDto.postDto.price }" readonly>
                <label>상대 제안 가격</label>
                <input type="text" value="${ messageDto.priceOffered }" readonly>
            </div>
            <label>보낸 사람</label>
            <input type="text" class="form-control mb-2" value="${ messageDto.sender }" readonly>
                                   
        </form>
        
        <button id="mymessage-reply" class="btn btn-primary my-4">답장하기</button>
        
		
		<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
		 integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
		  crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/axios@1.1.2/dist/axios.min.js"></script>
          
        <script src="/fourmen/js/mypage/mymessage-detail.js"></script>
   </body>
</html>