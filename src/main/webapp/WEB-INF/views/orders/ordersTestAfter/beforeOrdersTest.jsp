<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%-- <%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %> --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<form name="cart" id="ordersForm" method="post" action="${pageContext.request.contextPath}/orders/user/beforeOrdersForm" >
<input type="hidden" name="productCode" value="A01">
<input type="hidden" name="cartPrice" value="2000">
<input type="hidden" name="cartQty" value="2">
<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
<input type="submit" value="유저 주문하러가기">
</form>

<form name="cart" id="ordersForm" method="post" action="${pageContext.request.contextPath}/orders/member/beforeOrdersForm" >
<input type="hidden" name="productCode" value="A01">
<input type="hidden" name="productMembershipOnly" value="0">
<input type="hidden" name="cartPrice" value="2000">
<input type="hidden" name="cartQty" value="2">
<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
<input type="submit" value="멤버 주문하러가기">
</form>

<form name="cart" id="ordersForm" method="post" action="${pageContext.request.contextPath}/orders/ordersTestAfter/ordersFinalTest" >
<input type="hidden" name="productCode" value="A01">
<input type="hidden" name="productMembershipOnly" value="0">
<input type="hidden" name="orderdetailsPrice" value="2000">
<input type="hidden" name="orderdetailsQty" value="2">
<!-- <input type="hidden" name="productCode" value="A02"> -->
<!-- <input type="hidden" name="productMembershipOnly" value="0"> -->
<!-- <input type="hidden" name="orderdetailsPrice" value="1000"> -->
<!-- <input type="hidden" name="orderdetailsQty" value="1"> -->
<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
<input type="submit" value="경로 바로 주문하러가기">
</form>
</body>
</html>