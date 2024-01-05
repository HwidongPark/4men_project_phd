<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Message</title>
				<style>
				@font-face {
    font-family: 'NEXON Lv1 Gothic OTF';
    src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_20-04@2.1/NEXON Lv1 Gothic OTF.woff') format('woff');
    font-weight: normal;
    font-style: normal;
}
.gfont {
	font-family: 'NEXON Lv1 Gothic OTF';
	margin-top: 50px;
}
.marketname{
	color: black;
}
		</style>
		
		<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
		 rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" 
		 crossorigin="anonymous">
		
	</head>
	
	<body class="gfont">
		<h1>SEND</h1>
        <form action="/fourmen/mypage/mymessage/reply" method="post">
            <input type="text" name="replyTo" id="messageId" class="d-none" value="${ messageDto.id }">
            <input type="number" name="workId" class="d-none" value="${ messageDto.workId }">
            <label>받는사람</label>
            <input type="text" name="recipient" class="form-control mb-2" value="${ messageDto.sender }" readonly>
            <input class="d-none" name="sender" value="${ signedInUser }"> <!-- 보내는 사람 (나) -->
            <label class="mb-1">title</label>
            <input type="text" name="title" class="form-control mb-4" value="Re: ${ messageDto.title }" required>
            <label class="mb-1">content</label>
            <textarea id="message-detail-content" name="content" class="form-control" rows="10" required></textarea>
            <div class="mb-4">
                <a href="/fourmen/market/detail?workid=${ messageDto.workId }" class="mx-2 marketname">&gt;&gt;&gt;게시글 보러가기</a>
            </div>
            <div class="my-2">
                <label>상대 제안 가격</label>
                <input type="number" name="price" value="${ messageDto.priceOffered }" readonly> <!-- price는 상대방이 제시했'던' 가격 -->
                <label>내 제시 가격</label>
                <input type="number" name="priceOffered" required>  <!-- priceOffered는 내가 새로 제시하는 가격 -->
            </div>

            <button id="mymessage-reply-send" class="btn btn-dark my-4">답장 보내기</button>                 
        </form>

        
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
         integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
          crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/axios@1.1.2/dist/axios.min.js"></script>
          
        <script src="/fourmen/js/mypage/mymessage-detail.js"></script>
  </body>
		
	</body>
</html>