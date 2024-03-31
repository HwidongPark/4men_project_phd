<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
<style>
@font-face {
    font-family: 'EF_Rebecca';
    src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_2210-EF@1.0/EF_Rebecca.woff2') format('woff2');
    font-weight: normal;
    font-style: normal;
}

.fontchange{
	font-family: 'EF_Rebecca';
}
@font-face {
    font-family: 'NEXON Lv1 Gothic OTF';
    src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_20-04@2.1/NEXON Lv1 Gothic OTF.woff') format('woff');
    font-weight: normal;
    font-style: normal;
}
.gfont{
	 font-family: 'NEXON Lv1 Gothic OTF';
}
</style>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN"
	crossorigin="anonymous">
<link rel="stylesheet" href="css/signup.css">

</head>
<body>

<div class="container gfont" id="container">
  <div class="form-container sign-up-container">
  	<c:url var="signUpPage" value="signup" />
    <form action="${signUpPage}" method="post">
 		<h1 class="fontchange">Create Account</h1>	
      <input type="text" id="userid" name="userid" class="gfont" placeholder="ID" required/>
      <div class="checkIdResult" id="checkIdResult"></div>
      <input type="password" id="password" name="password" class="gfont" placeholder="Password" required/>
      <input type="text" id="name" name="name" class="gfont" placeholder="Name" required/>
      <input type="text" id="nickname" name="nickname" class="gfont" placeholder="Nickname" required />
      <div class="checkNicknameResult" class="gfont" id="checkNicknameResult"></div>
      <input type="text" id="phone" name="phone" class="gfont" placeholder="Phone" required/>
   	  <input type="email" id="email" name="email" class="gfont" placeholder="Email" required/>   
  
      <button type="submit" id="btnSignup" class="btn disabled"><a class="fontchange">Sign Up</a></button>
    </form>
  </div>
  <div class="form-container sign-in-container">
  	<c:url var="signinPage" value="signin" />
    <form action="${signinPage}" method="post">
      <a href="/fourmen"><img class="logo" src="logo/logofinal.png" alt="4men_logo"/></a>
	
      <input maxlength="30" type="text" name="userid" class="gfont" placeholder="ID" required/>
      <input maxlength="30" type="password" name="password" class="gfont" placeholder="Password" required/>
      	 <c:if test="${not empty param.result && param.result eq 'f'}">
      <div class="text-danger gfont">아이디와 패스워드를 확인해주세요</div>
      </c:if>
      <button type="submit" id="btnSignin" class="btn"><a class="fontchange">Sign In</a></button>
    </form>
  </div>
  <div class="overlay-container">
    <div class="overlay">
      <div class="overlay-panel overlay-left">
        <h1 class="fontchange">Login</h1>
        <p class="gfont">회원이시라면 로그인해주세요 </p>
        <button class="ghost fontchange" id="signIn">Sign In</button>
      </div>
      <div class="overlay-panel overlay-right">
        <h1 class="fontchange">Create Account</h1>
        <p class="gfont">회원이 아니시라면 회원가입해주세요</p>
        <button class="ghost fontchange" id="signUp">Sign Up</button>
      </div>
    </div>
  </div>
</div>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
		crossorigin="anonymous"></script>
	<script src="https://unpkg.com/axios/dist/axios.min.js"></script>
	<script src="js/signup.js"></script>
</body>
</html>