<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
  <%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>

<style type="text/css">
	body {
	  font-family: "Montserrat", sans-serif;
	  background: white;
	}
	
	.container {
	  display: block;
	  max-width: 680px;
	  width: 80%;
	  margin: 120px auto;
	}
	
	h1 {
	  color: #e91e63;
	  font-size: 30px;
	  letter-spacing: -3px;
	  text-align: center;
	  margin: 120px 0 10px 0;
	  transition: 0.2s linear;
	}
	
	h2 {
	  color: #e91e63;
	  font-size: 15px;
	  letter-spacing: -3px;
	  text-align: center;
	  margin: 50px 0 50px 0;
	  transition: 0.2s linear;
	}
	
	.links {
	  list-style-type: none;
	}
	.links li {
	  display: inline-block;
	  margin: 0 20px 0 0;
	  transition: 0.2s linear;
	}
	.links li:nth-child(2) {
	  opacity: 0.6;
	}
	.links li:nth-child(2):hover {
	  opacity: 1;
	}
	.links li:nth-child(3) {
	  opacity: 0.6;
	  float: right;
	}
	.links li:nth-child(3):hover {
	  opacity: 1;
	}
	.links li a {
	  text-decoration: none;
	  color: #0f132a;
	  font-weight: bolder;
	  text-align: center;
	  cursor: pointer;
	}
	
	form {
	  width: 100%;
	  max-width: 680px;
	  margin: 40px auto 10px;
	}
	form .input__block {
	  margin: 20px auto;
	  display: block;
	  position: relative;
	}
	form .input__block.first-input__block::before {
	  content: "";
	  position: absolute;
	  top: -15px;
	  left: 50px;
	  display: block;
	  width: 0;
	  height: 0;
	  background: transparent;
	  border-left: 15px solid transparent;
	  border-right: 15px solid transparent;
	  border-bottom: 15px solid rgba(15, 19, 42, 0.1);
	  transition: 0.2s linear;
	}
	form .input__block.signup-input__block::before {
	  content: "";
	  position: absolute;
	  top: -15px;
	  left: 150px;
	  display: block;
	  width: 0;
	  height: 0;
	  background: transparent;
	  border-left: 15px solid transparent;
	  border-right: 15px solid transparent;
	  border-bottom: 15px solid rgba(15, 19, 42, 0.1);
	  transition: 0.2s linear;
	}
	form .input__block input {
	  display: block;
	  width: 90%;
	  max-width: 680px;
	  height: 50px;
	  margin: 0 auto;
	  border-radius: 8px;
	  border: none;
	  background: rgba(15, 19, 42, 0.1);
	  color: rgba(15, 19, 42, 0.3);
	  padding: 0 0 0 15px;
	  font-size: 14px;
	  font-family: "Montserrat", sans-serif;
	}
	form .input__block input:focus, form .input__block input:active {
	  outline: none;
	  border: none;
	  color: #0f132a;
	}
	form .input__block input.repeat__password {
	  opacity: 0;
	  display: none;
	  transition: 0.2s linear;
	}
	form .signin__btn {
	  background: #e91e63;
	  color: white;
	  display: block;
	  width: 92.5%;
	  max-width: 680px;
	  height: 50px;
	  border-radius: 8px;
	  margin: 0 auto;
	  border: none;
	  cursor: pointer;
	  font-size: 14px;
	  font-family: "Montserrat", sans-serif;
	  box-shadow: 0 15px 30px rgba(233, 30, 99, 0.36);
	  transition: 0.2s linear;
	}
	form .signin__btn:hover {
	  box-shadow: 0 0 0 rgba(233, 30, 99, 0);
	}
	
	.separator {
	  display: block;
	  margin: 30px auto 10px;
	  text-align: center;
	  height: 40px;
	  position: relative;
	  background: transparent;
	  color: rgba(15, 19, 42, 0.4);
	  font-size: 13px;
	  width: 90%;
	  max-width: 680px;
	}
	.separator::before {
	  content: "";
	  position: absolute;
	  top: 8px;
	  left: 0;
	  background: rgba(15, 19, 42, 0.2);
	  height: 1px;
	  width: 45%;
	}
	.separator::after {
	  content: "";
	  position: absolute;
	  top: 8px;
	  right: 0;
	  background: rgba(15, 19, 42, 0.2);
	  height: 1px;
	  width: 45%;
	}
	
	.google__btn,
	.github__btn {
	  display: block;
	  width: 90%;
	  max-width: 680px;
	  margin: 20px auto;
	  height: 50px;
	  cursor: pointer;
	  font-size: 14px;
	  font-family: "Montserrat", sans-serif;
	  border-radius: 8px;
	  border: none;
	  line-height: 40px;
	}
	.google__btn.google__btn{
	  background: #ffd400;
	  color: white;
	  box-shadow: 0 15px 30px rgba(91, 144, 240, 0.36);
	  transition: 0.2s linear;
	}
	.github__btn.google__btn {
	  background: #5b90f0;
	  color: white;
	  box-shadow: 0 15px 30px rgba(91, 144, 240, 0.36);
	  transition: 0.2s linear;
	}
	.google__btn.google__btn .fa,
	.github__btn.google__btn .fa {
	  font-size: 20px;
	  padding: 0 5px 0 0;
	}
	.google__btn.google__btn:hover,
	.github__btn.google__btn:hover {
	  box-shadow: 0 0 0 rgba(91, 144, 240, 0);
	}
	.google__btn.github__btn,
	.github__btn.github__btn {
	  background: #25282d;
	  color: white;
	  box-shadow: 0 15px 30px rgba(37, 40, 45, 0.36);
	  transition: 0.2s linear;
	}
	.google__btn.github__btn .fa,
	.github__btn.github__btn .fa {
	  font-size: 20px;
	  padding: 0 5px 0 0;
	}
	.google__btn.github__btn:hover,
	.github__btn.github__btn:hover {
	  box-shadow: 0 0 0 rgba(37, 40, 45, 0);
	}
	
	footer p {
	  text-align: center;
	}
	footer p .fa {
	  color: #e91e63;
	}
	footer p a {
	  text-decoration: none;
	  font-size: 17px;
	  margin: 0 5px;
	}
	footer p a .fa-facebook {
	  color: #3b5998;
	}
	footer p a .fa-twitter {
	  color: #1da1f2;
	}
	footer p a .fa-instagram {
	  color: #f77737;
	}
	footer p a .fa-linkedin {
	  color: #0077b5;
	}
	footer p a .fa-behance {
	  color: #1769ff;
	}
</style>




<script type="text/javascript">

//유효성검사
function loginChk() {
    var form = document.f1;
    if (!form.id.value) {
        alert("아이디를 입력해 주십시오.");
        form.id.focus();
        return;
    }
 
    if (!form.password.value) {
        alert("비밀번호를 입력해 주십시오.");
        form.password.focus();
        return;
    }
    form.action = "${pageContext.request.contextPath}/users/login"; //로그인하기기 눌렀을 떄
    form.submit();
}



</script>


</head>
<body>
info:
 <sec:authorize access="isAuthenticated()">
   <sec:authentication property='principal.usersEmail'/>
 </sec:authorize>

<div class="container">
  <!-- Heading -->
  <h1>로그인 또는 회원가입</h1>
  <h2>블링크 타운의 회원이 되어주세요!</h2>
  <!-- Links -->
  <ul class="links">
    <li>
      <a href="#" id="signin">SIGN IN</a>
    </li>
    <li>
      <a href="/system/signup" id="signup">SIGN UP</a>
    </li>
  </ul>
  
  <!-- Form -->
  
   <c:if test="${errorMessage!=null}">
     <b style="color:red">error : ${errorMessage}</b>
   </c:if>
  
  <form name="f1" action="${pageContext.request.contextPath}/loginCheck" method="post">
    <!-- email input -->
    <div class="first-input input__block first-input__block">
       <input type="text" placeholder="Id" class="input" id="id" name="usersId"  />
    </div>
    <!-- password input -->
    <div class="input__block">
       <input type="password" placeholder="Password" class="input" id="password" name="usersPwd"  />
    </div>
    <!-- repeat password input -->
    <div class="input__block">
       <input type="password" placeholder="Repeat password" class="input repeat__password" id="repeat__password"    />
    </div>
    <!-- sign in button -->
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" >
    
    <input type="submit" id="signin__btn" value="로그인">
    
  <!--   <button class="signin__btn" onclick="loginChk()">
      로그인
    </button> -->
    
  </form>
  <!-- separator -->
  <div class="separator">
    <p>또는</p>
  </div>
  <!-- google button -->
  <button class="google__btn" onclick="location.href='https://kauth.kakao.com/oauth/authorize?client_id=1b05f9fc0b0d4e666e395b0bb60c0f4b&redirect_uri=http://localhost:8000/system/auth/kakao/callback&response_type=code'">
    <i class="fa fa-google"></i>
    카카오 로그인
  </button>

</div>

<footer>
  <p>
    Thank you for watching
    <i class="fa fa-heart"></i> 
    <i class="fa fa-heart"></i> 
    <i class="fa fa-heart"></i> 
  </p>
  <p>
    Chouaib Blgn :
    <a href="https://www.facebook.com/chouaib45" >
      <i class="fa fa-facebook"></i>
    </a>
    <a href="http://twitter.com/chouaibblgn45">
      <i class="fa fa-twitter"></i> 
    </a>
    <a href="http://instagram.com/chouaib_blgn">
      <i class="fa fa-instagram"></i> 
    </a>
    <a href="http://linkedin.com/in/chouaibblgn/">
      <i class="fa fa-linkedin"></i>
    </a>
    <a href="https://www.behance.net/geek30">
      <i class="fa fa-behance"></i>
    </a>
  </p>
</footer>


</body>
</html>