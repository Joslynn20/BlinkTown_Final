<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<style>
/*
Noto+Sans+KR
Noto+Sans
Roboto
  */
@import
	url('https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@100;300;400;500;700;900&family=Noto+Sans:ital,wght@0,200;0,300;0,400;0,500;0,600;0,700;0,800;0,900;1,200;1,300;1,400;1,500;1,600;1,700;1,800;1,900&family=Roboto:wght@100;300;400;500;700;900&display=swap')
	;
</style>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.1.0/jquery.min.js"></script>
<title>Insert title here</title>
<style type="text/css">
* {
	margin: 0;
	font-family: NotoSans;
}

.header-wrap {
	width: 100%;
	height: 70px;
	background-color: #000000;
}

.header-top {
	width: 100%;
	height: 30px;
}

.header-menu {
	color: #ffffff;
	background-color: inherit;
	width: 100px;
	border: none;
	float: right;
	text-align: center;
	font-size: 15px;
	padding: 8px 0px;
}

.header-bottom {
	width: 100%;
	height: 38px;
}

.menu-popup-button {
	width: 35px;
	height: 35px;
	float: left;
	position: relative;
	margin-left: 20px;
	top: -13px;
	background-color: #ffffff;
	display: block;
}

.header-logo {
	width: 178px;
	height: 42px;
	background-color: #ffffff;
	margin: 0 auto;
	position: relative;
	top: -13px;
}

.header-languag {
	float: right;
}

.select-option {
	background-color: #000000;
	font-size: 18px;
	border: none;
}
</style>
<script type="text/javascript">
	$(document).ready(function() {

		$("#selectbox").change(function() {
			var lang = $("#selectbox option:selected").val();
			//현재 주소를 가져온다.
			var renewURL = location.href;
			//현재 주소 중 page 부분이 있다면 날려버린다.
			renewURL = renewURL.split('?');
			var newURL = renewURL[0]

			$.ajax({
				type : "get",
				success : function(result) {
					newURL += '?lang=' + lang;
					//페이지 갱신 실행!
					history.pushState(null, null, newURL);
					document.location.reload();
				}
			})

		});//click
	});//jquery
</script>
</head>
<body>
	<!-- sec:authoize 태그 - 현 사용자가 특정 권한이 있으면 몸체의 내용을 처리한다.
	   access속성에 어떤 권한인지를 표현한다. - 스프링 시큐리티의 spEL-->
	

	<div class="header-wrap">
		<div class="header-top">
			<select id="selectbox" name="selectbox"
				class="header-languag header-menu">
				<option selected="selected" value="ko" class="select-option"
					<c:if test="${param.lang.toString()== 'ko'}"> selected</c:if>>KOR</option>
				<option value="en" class="select-option"
					<c:if test="${param.lang.toString()==  'en'}"> selected</c:if>>ENG</option>
			</select>
          <sec:authorize access="isAuthenticated()">
				<div class="header-login header-menu">LOGOUT</div>
				<div class="header-mypage header-menu">
					<a href="${pageContext.request.contextPath}/users/findUser">MYPAGE</a>
				</div>
				<div class="header-cart header-menu">CART</div>
			</sec:authorize>
		</div>
		

		<div class="header-bottom">
			<div class="menu-popup-button" id="popup-button">
				<a href="#popup-article" class="open-popup"
					onclick="openPopup('popup-button')"> </a>
			</div>
			<div class="header-logo">logo</div>
		</div>
	</div>
	<script>
		function openPopup(id) {
			var e = document.getElementById(id);
			e.style.display = ((e.style.display != 'none') ? 'none' : 'block');
		}
	</script>


</body>
</html>