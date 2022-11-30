<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
<style type="text/css">
	*{
		margin: 0px;
	}
	header{
		width: 800px;
		height: 100px;
		background-color: #22222250; 
	}
	section{
		width: 800px;
		height: 400px;
		background-color: #22222220; 
	}
	footer{
		width: 800px;
		height: 150px;
		background-color: #22222250; 
	}
</style>
</head>
<body>

	<header>
		<tiles:insertAttribute name="header"/><!-- 고정이 아닌 동적 삽입 -->
	</header>
	
	<section>
		<tiles:insertAttribute name="content"/>
	</section>
	
	<footer>
		<tiles:insertAttribute name="footer"/>
	</footer>

</body>
</html>