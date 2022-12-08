<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>

<style type="text/css">
	body{
		margin: 30px 0px 100px 0px;
	}
	
	form {
	  width: 100%;
	  max-width: 680px;
	  margin: 40px 40px 10px;
	}
	form .input__block {
	  margin: 20px auto;
	  display: block;
	  position: relative;
	  font-size: 14px;
	  color: #ffffff;
	}
	form .input__block input {
	  display: block;
	  width: 90%;
	  max-width: 680px;
	  height: 30px;
	  margin: 5px auto 0px;
	  border-radius: 5px;
	  border: none;
	  background: #ffffff20;
	  padding: 0 0 0 10px;
	  font-size: 14px;
	  color: #F8D8E3;
	}
	form .input__block input:focus{
	  outline: none;
	  border: none;
	  color: #ffffff;
	}
	form .input__block input:active{
	  outline: none;
	  border: none;
	  color: #F8D8E3;
	}
	form .input__block input.repeat__password {
	  opacity: 0;
	  display: none;
	  transition: 0.2s linear;
	}
	form .signin__btn {
	  background: #F4A7BB;
	  color: white;
	  display: block;
	  width: 92.5%;
	  max-width: 680px;
	  height: 50px;
	  border-radius: 5px;
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
	
	.userInfo-title{
  	color: #F4A7BB;
	font-weight: 700;
	width: 650px;
	padding: 0px 30px;
	text-align: center;
	border-radius: 5px;
	font-size: 30px;
	margin-left: 30px;
	}
	
.userInfo-wrap{
padding: 30px 20px;
width: 760px;
margin: 100px 50px 100px 80px;
}

/*box*/

@property --angle {
  syntax: '<angle>';
  initial-value: 90deg;
  inherits: true;
}

@property --gradX {
  syntax: '<percentage>';
  initial-value: 50%;
  inherits: true;
}

@property --gradY {
  syntax: '<percentage>';
  initial-value: 0%;
  inherits: true;
}

:root {
	--d: 2500ms;
	--angle: 90deg;
	--gradX: 100%;
	--gradY: 50%;
	--c1: #F4A7BB;
	--c2: #F4A7BB10;
}

.box {
	border: 0.1rem solid;
	border-image: radial-gradient(ellipse at var(--gradX) var(--gradY), var(--c1), var(--c1) 10%, var(--c2) 40%) 30;
	animation: borderRadial var(--d) linear infinite forwards;
}

@keyframes borderRotate {
	100% {
		--angle: 420deg;
	}
}

@keyframes borderRadial {
	20% {
		--gradX: 100%;
		--gradY: 50%;
	}
	40% {
		--gradX: 100%;
		--gradY: 100%;
	}
	60% {
		--gradX: 50%;
		--gradY: 100%;
	}
	80% {
		--gradX: 0%;
		--gradY: 50%;
	}
	100% {
		--gradX: 50%;
		--gradY: 0%;
	}
}
</style>



</head>
<body>

	<div class="userInfo-wrap box">
	
  <!-- Form -->
    <div class="userInfo-title">회원정보</div>
  <form name="f1" action="" method="post" onsubmit="return checkAll()">
    <!-- id input -->
    <div class="input__block">
       &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;아이디<input type="text" value="Id" class="input" id="id"   disabled="disabled"  />
    </div>
    <!-- password input -->
    <div class="input__block">
       &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;비밀번호<input type="password" value="Password" class="input" id="password"   disabled="disabled"    />
    </div>
    <!-- repeat password input -->
    <div class="input__block">
       &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;비밀번호 확인<input type="password" placeholder="Password" class="input" id="passwordchk"    />
       <span id="check"></span>
    </div>
    
    <!-- name input -->
    <div class="input__block">
       &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;이름<input type="text" value="Name" class="input" id="name"   disabled="disabled"  />
    </div>
    <!-- nickname input -->
    <div class="input__block">
       &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;닉네임<input type="text" placeholder="NickName" class="input" id="nickname"    />
    </div>
    <!-- email input -->
    <div class="input__block">
       &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;이메일<input type="email" placeholder="E-mail" class="input" id="email"    />
    </div>
    <!-- phone input -->
    <div class="input__block">
       &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;전화번호<input type="text" value="Phone" class="input" id="phone"   disabled="disabled"   />
    </div>
    <!-- sign in button -->
    <button class="signin__btn" onsubmit="checkAll()">
      가입
    </button>
  </form>

</div>
</body>
</html>