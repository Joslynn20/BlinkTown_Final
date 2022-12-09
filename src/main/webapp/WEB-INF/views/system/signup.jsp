<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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

<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.1.min.js"></script>
<script type="text/javascript">

 $(function() {
	$(document).ajaxSend(function(e, xhr, options) {
		 xhr.setRequestHeader( "${_csrf.headerName}", "${_csrf.token}" );
	});
	$("#idCheck").on("click", function() {
		alert("아이디체크");
		$.ajax({
			url : "${pageContext.request.contextPath}/users/idCheck", //서버ㅓ요청주소
			type : "post" , //요청방식 (get,post,put,delete,patch)
			dataType : "text" , //서버가 응답(보내온) 한 타입 (text | html | xml | json)
			data :"usersId=" + $("#id").val() , //서버에 보낼 파라미터 타입
			success : function(result){
				if(result==true){
					alert("아이디 중복입니다.");
					$("#id").val('');
					$("#signin__btn").attr("disabled", true);
				} else{
					alert("사용 가능한 아이디 입니다.");
				}
	
			},
			error : function(err){
			  alert(err+"에러발생");
			}	
		});////ajax
	});
////////////////////////////////////////////////////////////////

//  $(function() {
	$("#nickNameCheck").on("click", function() {
		$.ajax({
			url : "${pageContext.request.contextPath}/users/nickCheck", //서버ㅓ요청주소
			type : "post" , //요청방식 (get,post,put,delete,patch)
			dataType : "text" , //서버가 응답(보내온) 한 타입 (text | html | xml | json)
			data :"usersNickName=" + $("#nickname").val() , //서버에 보낼 파라미터 타입
			success : function(result){
				if(result==true){
					alert("닉네임 중복입니다.");
					$("nickname").val('');
					$("#signin__btn").attr("disabled", true);
				} else{
					alert("사용 가능한 닉네임 입니다.");
				}
	
			},
			error : function(err){
			  alert(err+"에러발생");
			}	
		});////ajax
 })
/////////////////////////////////////////////////////


	$("#emailCheck").on("click", function() {
		$.ajax({
			url : "${pageContext.request.contextPath}/users/emailCheck", //서버ㅓ요청주소
			type : "post" , //요청방식 (get,post,put,delete,patch)
			dataType : "text" , //서버가 응답(보내온) 한 타입 (text | html | xml | json)
			data :"usersEmail=" + $("#email").val() , //서버에 보낼 파라미터 타입
			success : function(result){
				if(result==true){
					alert("이메일 중복입니다.");
					$("#signin__btn").attr("disabled", true);	
				} else{
					alert("사용 가능한 이메일 입니다.");
				}
	
			},
			error : function(err){
			  alert(err+"에러발생");
			}	
		});////ajax
 })
	
/////////////////////////////////////////////////////


		$("#phoneCheck").on("click", function() {
			$.ajax({
				url : "${pageContext.request.contextPath}/users/phoneCheck", //서버ㅓ요청주소
				type : "post" , //요청방식 (get,post,put,delete,patch)
				dataType : "text" , //서버가 응답(보내온) 한 타입 (text | html | xml | json)
				data :"usersPhone=" + $("#phone").val() , //서버에 보낼 파라미터 타입
				success : function(result){
					if(result==true){
						alert("전화번호 중복입니다.");
						$("#signin__btn").attr("disabled", true);	
					} else{
						alert("사용 가능한 전화번호 입니다.");
					}
		
				},
				error : function(err){
				  alert(err+"에러발생");
				}	
			});////ajax
	 });
		})
/////////////////////////////////////////////////////
	/*  function checkAll() {
		var form = document.f1;	
	   	
	   	if (!checkPassword(form.id.value, form.password.value, form.passwordchk.value)) {
	       return false;
	   	} 
	   	return true;
	   	console.log("가입완료!");
	}

	//공백확인 함수
	function checkExistData(value, dataName) {
	    if (value == "") {
	        alert(dataName + " 입력해주세요!");
	        return false;
	    }
	    return true;
	}

	//비밀번호
	function checkPassword(id, password, passwordchk) {
	    //비밀번호가 입력되었는지 확인하기
	    if (!checkExistData(password, "비밀번호를"))
	        return false;
	    //비밀번호 확인이 입력되었는지 확인하기
	    if (!checkExistData(passwordchk, "비밀번호 확인을"))
	        return false;

	    //비밀번호와 비밀번호 확인이 맞지 않다면..
	    if (password != passwordchk) {
	        alert("두 비밀번호가 맞지 않습니다.");
	        form.password.value = "";
	        form.passwordchk.value = "";
	        form.passwordchk.focus();
	        return false;
	    }

	 
	    return true; //확인이 완료되었을 때
	}
 */
	

</script>



</head>
<body>

<div class="container">
  <!-- Heading -->
  <h1>회원가입</h1>
  <!-- Links -->
  
  
  <!-- Form -->
  <form name="f1" action="${pageContext.request.contextPath}/users/signup2" method="post">
  
   <!-- id input -->
    <div class="input__block">
       &nbsp;&nbsp;&nbsp;&nbsp;아이디<input type="text" placeholder="Id" class="input" id="id"  name="usersId"   />
    </div>
    &nbsp;&nbsp; &nbsp;&nbsp;<button id="idCheck" onclick="return false;">아이디중복</button>
   <div  class="idCheck"  ></div>
    <!-- password input -->
    <div class="input__block">
       &nbsp;&nbsp;&nbsp;&nbsp;비밀번호<input type="password" placeholder="Password" class="input" id="password"  name="usersPwd"  />
    </div>
    <!-- repeat password input -->
    <div class="input__block">
       &nbsp;&nbsp;&nbsp;&nbsp;비밀번호 확인<input type="password" placeholder="Password" class="input" id="passwordchk"   />
       <span id="check"></span>
    </div>
   
    <!-- nickname input -->
    <div class="input__block">
       &nbsp;&nbsp;&nbsp;&nbsp;닉네임<input type="text" placeholder="NickName" class="input" id="nickname"  name="usersNickName"   />
    </div>
    &nbsp;&nbsp; &nbsp;&nbsp;<button id="nickNameCheck" onclick="return false;">닉네임중복</button>
   <div  class=nickNameCheck"  ></div>
    
    <!-- email input -->
    <div class="input__block">
       &nbsp;&nbsp;&nbsp;&nbsp;이메일<input type="email" placeholder="E-mail" class="input" id="email" name="usersEmail"   />
    </div>
    &nbsp;&nbsp; &nbsp;&nbsp;<button id="emailCheck" onclick="return false;">이메일중복</button>
   <div  class="emailCheck"  ></div>
    <!-- phone input -->
    <div class="input__block">
       &nbsp;&nbsp;&nbsp;&nbsp;전화번호<input type="text" placeholder="Phone" class="input" id="phone"  name="usersPhone"  />
    </div>
     &nbsp;&nbsp; &nbsp;&nbsp;<button id="phoneCheck" onclick="return false;">전화번호중복</button>
   <div  class="phoneCheck"  ></div>
    <!-- sign in button -->
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" >
    <input type="submit" id="signin__btn" value="회원가입">
    
  </form>
 
</div>

<footer>
  
</footer>


</body>
</html>