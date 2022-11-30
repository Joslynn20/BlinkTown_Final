<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
    <%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
<style type="text/css">
	table{
		width: 800px;
		height: 600px;
		border: 1px gray solid;
	}
	.a{height: 100px; background-color: #22222280;}
	.b{height: 100px; background-color: #22222280;}
	.aside{ width: 200px; background-color: #22222250;}
	.c{background-color: #22222220;}
</style>
</head>

<body>
<table>
	<tr class="a">
		<th colspan="2"><tiles:insertAttribute name="header"/></th>
	</tr>
	
	<tr class="c">
		<th><tiles:insertAttribute name="content"/></th>
		
	</tr>
	
	<tr class="b">
		<th colspan="2"><tiles:insertAttribute name="footer"/></th>
	</tr>
	<tr>
	<th class="aside"><tiles:insertAttribute name="popup-menu"/></th>
	</tr>
	
	<tr>
	<th><tiles:insertAttribute name="content-background"/></th>
	</tr>
	
</table>
</body>
</html>