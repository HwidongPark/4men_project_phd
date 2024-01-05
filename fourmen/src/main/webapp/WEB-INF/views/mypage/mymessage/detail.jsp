<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
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
		<h1>MESSAGE</h1>
        <form>
            <input type="text" id="messageId" class="d-none" value="${ messageDto.id }">
            <label class="mb-1">title</label>
            <input type="text" class="form-control mb-4" value="${ messageDto.title }" readonly>
            <label class="mb-1">content</label>
            <textarea id="message-detail-content" class="form-control" rows="10" readonly>${ messageDto.content }</textarea>
            <div class="mb-4 justify-content-end">
                <a href="/fourmen/market/detail?workid=${ messageDto.workId }" class="mx-2 align-self-end marketname">&gt;&gt;&gt;게시글 보러가기</a>
            </div>
            <div class="my-2">
                <div class="container text-start">
                    <div class="row">
                        <div class="col">
                            <label>내 제시 가격</label>
                            <c:choose>
                                <c:when test="${ messageDto.price != null }">
                                    <input id="offered-price" type="text" value="${ messageDto.price }" readonly>
                                </c:when>
                                <c:otherwise>
                                    <input id="offered-price" type="text" value="${ messageDto.postDto.price }" readonly>
                                </c:otherwise>
                            </c:choose>
                            
                        </div>
                        <div class="col">
                            <label>상대 제안 가격</label>
                            <input type="text" value="${ messageDto.priceOffered }" readonly>
                        </div>
                    </div>
                </div>
            </div>
            <label>보낸 사람</label>
            <input type="text" class="form-control mb-2" value="${ messageDto.sender }" readonly>                        
        </form>
        <div class="my-4 d-flex justify-content-between">
            <c:choose>
                <c:when test="${ messageDto.postDto.isSold eq 'Y' }">
                    <button class="btn btn-secondary">거래 완료된 상품</button>
                </c:when>
                <c:otherwise>
                    <c:if test="${ signedInUser != messageDto.sender }">
                        <button id="mymessage-make-deal" class="btn btn-dark">거래 확정</button>
                    </c:if>
                </c:otherwise>
                
            </c:choose>            
            <c:if test="${ messageDto.sender != '탈퇴한 회원' && messageDto.sender != signedInUser }">
                <button id="mymessage-reply" class="btn btn-secondary">답장하기</button>
            </c:if>                
        </div>
        
        <!-- 서로 주고받은 메세지를 보여줌 -->
        <c:if test="${ firstMessageDto.replies.size() ge 1 }">
        <h3 class="mx-1 mb-2">주고 받은 메세지</h3>
        <div class="card mx-1 my-2 p-0">
            <div class="card-body">
                <table class="table">
                    <thead>
                        <th scope="col">보낸이</th>
                        <th scope="col">제목</th>
                        <th scope="col">날짜</th>
                    </thead>
                    <tbody>
                        <c:forEach begin="0" end="${ firstMessageDto.replies.size() - 1 ge 0 ? firstMessageDto.replies.size() - 1 : 0 }" step="1" var="i">
                            <c:set var="realIndex" value="${ firstMessageDto.replies.size() - 1 - i }" />
                            <c:choose>                            
                                <c:when test="${ messageDto.id eq firstMessageDto.replies[realIndex].id }">
                                    <tr class="table-secondary">
                                </c:when>
                                <c:otherwise>
                                    <tr>
                                </c:otherwise>
                            </c:choose>
                                <td>${ firstMessageDto.replies[realIndex].sender }</td>
                                <td>                                
                                    <c:choose>
                                        <c:when test="${ messageDto.id eq firstMessageDto.replies[realIndex].id }">
                                            ${ firstMessageDto.replies[realIndex].title }
                                        </c:when>
                                        <c:otherwise>
                                            <a href="/fourmen/mypage/mymessage/detail?id=${firstMessageDto.replies[realIndex].id}">
                                                ${ firstMessageDto.replies[realIndex].title }
                                            </a>
                                        </c:otherwise>
                                    </c:choose>
                                </td>
                                <td>${ firstMessageDto.replies[realIndex].timeSent }</td>                        
                            </tr>
                        </c:forEach>
                        <c:choose>
                            <c:when test="${ messageDto.id eq firstMessageDto.id }">
                                <tr class="table-secondary">
                            </c:when>
                            <c:otherwise>
                                <tr>
                            </c:otherwise>
                        </c:choose>
                            <td>${ firstMessageDto.sender }</td>
                            <td>
                                <c:choose>
                                    <c:when test="${ messageDto.id eq firstMessageDto.id }">
                                        ${ firstMessageDto.title }
                                    </c:when>
                                    <c:otherwise>
                                        <a href="/fourmen/mypage/mymessage/detail?id=${ firstMessageDto.id }">${ firstMessageDto.title }</a>
                                    </c:otherwise>
                                </c:choose>
                            </td>
                            <td>${ firstMessageDto.timeSent }</td>
                        </tr>
                    </tbody>
                </table>
            </div>
        </div>
		</c:if>
        
		<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
		 integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
		  crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/axios@1.1.2/dist/axios.min.js"></script>
        
        <script>
            const workId = ${ messageDto.workId };
            const buyerId = `${ firstMessageDto.sender }`;
        </script>
        <script src="/fourmen/js/mypage/mymessage-detail.js"></script>
   </body>
</html>