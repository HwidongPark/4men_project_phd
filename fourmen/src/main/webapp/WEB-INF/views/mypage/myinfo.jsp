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
        <link rel="stylesheet" href="../css/footer.css">
        <link rel="stylesheet" href="../css/forum-table.css">
        <link rel="stylesheet" href="../css/underheader.css">
        <link rel="stylesheet" href="../css/pagenation.css">    
        <link rel="stylesheet" href="../css/forum-kategorie-area.css">
        <link rel="stylesheet" href="../css/mypage-myinfo.css">
        
	</head>
	<body>
    <%@ include file="../fragments/navigation.jspf"%>
            <!-- 웹페이지 상단 헤더 아래 부분 -->
    <div id="underheader-div">
        <div class="container" id="underheadrcontainer">
            <h2 class="commondesign">
                My Page
            </h2>
        </div>
    </div>
    
    <main>
        <!-- 게시판 카테고리(자유게시판, 후기게시판, 질문게시판) -->
        <section role="kategorie" class="kategorie" style="border-bottom: 1.5px solid #D8D8D8;">
            <div class="forum-kategorie">
                <ul class="forum-kategorie-board-lists">
                    <li class="forum-kategorie-board">
                        <b><a href="/fourmen/mypage/myinfo">내 정보 보기/수정</a></b>
                    </li>
                    <li class="forum-kategorie-board">
                        <a href="/fourmen/mypage/mywork">내 작품 보기</a>
                    </li>
                    <li class="forum-kategorie-board">
                        <a href="/fourmen/mypage/mymarket">마켓 작성글 보기</a>
                    </li>
                    <li class="forum-kategorie-board">
                        <a href="#">게시판 작성글 보기</a>
                    </li>
                </ul>
            </div>
        </section>
    
        <form id="myinfo-form" method="post" action="/fourmen/mypage/myinfo/update" enctype="multipart/form-data">
            <div class="myinfo-info-photo-container card w-75">
                <div class="row">
                    <div class="col">
                        <div class="card">
                            <div class="card-body">
                                <label class="mb-1">아이디</label>
                                <input name="userid" value="${ user.userid }" class="myinfo-not-modifiable form-control mb-2" readonly>
                                <label class="mb-1">이름</label>
                                <input name="name" value="${ user.name }" class="myinfo-not-modifiable form-control mb-2" readonly>
                                <label class="mb-1">등급</label>
                                <input name="grade" value="${ user.grade }" class="myinfo-not-modifiable form-control mb-2" readonly>                                
                                <label class="mb-1">닉네임</label>
                                <input name="nickname" value="${ user.nickname }" class="form-control mb-2">
                                <label class="mb-1">전화번호</label>
                                <input name="phone" type="number" value="${ user.phone }" class="form-control mb-2">
                                <label class="mb-1">이메일</label>
                                <input name="email" type="email" value="${ user.email }" class="form-control mb-2">
                                <label class="mb-1">비밀번호</label>
                                <input id="password" name="password" type="password" class="form-control mb-2">                    
                                <label class="mb-1">비밀번호 확인</label>
                                <input id="passwordConfirm" name="passwordConfirm" type="password" class="form-control mb-2">
                                <input id="realPassword" type="hidden" value=${ user.password }>
                            </div>
                        </div>
                    </div>
                    <div class="col">
                        <div class="myinfo-profile-image-container card">
                            <div class="card-body">
                                <img id="myinfo-profile-image" src="/fourmen/image/${ user.profile_s_img }" alt="profile-img" class="myinfo-profile-image">
                                <label class="btn btn-outline-dark w-100 mt-3" for="myinfo-profile-image-file">프로필 사진 변경</label>
                                <input type="file" name="profileImage" id="myinfo-profile-image-file" class="d-none">
                                <div id="myinfo-file-name" class="my-2 font-weight-bold ml-auto mr-auto"></div>
                            </div>
                        </div>
                    </div>
                </div>            
            </div>
        </form>
        <div class="mypage-info-buttons-container w-75">
            <div>
                <button class="btn btn-danger" id="btnDelete">탈퇴</button>
            </div>
            <div>
                <!-- Button trigger modal -->
                <button type="button" class="btn btn-secondary" data-bs-toggle="modal" data-bs-target="#modalChangePassword">
                    비밀번호 변경
                </button>
                <button class="btn btn-success" id="btnUpdate">회원수정</button>
            </div>
        </div>
    </main>
    
    <!-- 비밀번호 변경 모달 -->
    <div class="modal fade" id="modalChangePassword" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                     <h1 class="modal-title fs-5" id="exampleModalLabel">비밀번호 변경</h1>
                     <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <div class="w-100 d-flex justify-content-center mb-3">
                        <div class="card w-75">
                            <div class="card-body">
                                <form id="formChangePassword" method="post" action="/mypage/myinfo/changepassword">
                                    <label class="mb-1">현재 비밀번호</label>
                                    <input id="modalPassword" type="password" name="currentPassword" class="form-control mb-2" required>
                                    <label class="mb-1">현재 비밀번호 확인</label>
                                    <input id="modalPasswordConfirm" type="password" name="currentPasswordConfirm" class="form-control mb-5" required>
                                    <label class="mb-1">새 비밀번호</label>
                                    <input id="modalNewPassword" type="password" name="newPassword" class="form-control mb-2" required>
                                    <label class="mb-1">새 비밀번호 확인</label>
                                    <input id="modalNewPasswordConfirm" type="password" name="newPasswordConfirm" class="form-control mb-4" required>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="modal-footer d-flex justify-content-between">
                     <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">닫기</button>
                     <button id="btnChangePassword" type="button" class="btn btn-primary">비밀번호 변경</button>
                </div>
            </div>
        </div>
    </div>
    
    
    <%@ include file="../fragments/footer.jspf" %>
        
		
		 <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
		  integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
		   crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/axios@1.1.2/dist/axios.min.js"></script>
        <script>
            const existingSFileName = `${ user.profile_s_img }`;
        </script>
		<script src="../js/header.js"></script>
        <script src="../js/mypage/myinfo.js"></script>
	</body>
	</html>