<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/shop/order.css">
</head>
<body>
<section class="cart">
  <h1>Order</h1>
        <div class="cart__information">
            <ul>
                <li>카카오 결제 서비스만 지원합니다.</li>
                <li>가격, 옵션 등 정보가 변경된 경우 주문이 불가할 수 있습니다.</li>
            </ul>
        </div>
         <form>

        <table class="cart__list">   
     
                <thead>
                    <tr>
                        <td colspan="2">상품정보</td>
                        <td>옵션</td>
                        <td>상품금액</td>
                    </tr>
                </thead>
                <tbody>
                    <tr class="cart__list__detail">
                        <td><img src="${pageContext.request.contextPath}/img/FIGURE_JENNIE.png" alt="magic keyboard"></td>
                        <td><a href="#">상품코드</a>
                            <p>제품명</p>
                          
                        <td>
                            <p>총 주문수량: <span>1</span>개</p>
                        </td>
                        <td>
							  <span class="price">11662</span><span>원</span>
                        </td>
                    </tr>
           
                </tbody>
         		
        </table>
        <div class="payment">결제수단 카카오페이</div>
        <div class="total-price">총 결제금액 <span>000000원</span></div>
        
        <div class="orderInfo-wrap">
        <h3 style="color: #f4a7bb; margin-bottom: 5px; margin-left: 100px;">주문정보</h3>
	        <div class="orderInfo">
	         <input type="text" placeholder="주문자이름" class="input_field">
	          <input type="text" placeholder="주문자 전화번호" class="input_field">
	           <input type="text" placeholder="우편번호" class="input_field addr"><button class="addrBtn">우편번호찾기</button>
	            <input type="text" placeholder="주소" class="input_field">
	        </div>
        </div>
        
        
        <div class="cart__mainbtns">
            <button class="cart__bigorderbtn left">취소</button>
            <button class="cart__bigorderbtn right" type="submit">주문하기</button>
        </div>
        </form>
    </section>
</body>
</html>