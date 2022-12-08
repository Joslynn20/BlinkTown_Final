<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/mypage/orderList.css">
</head>
<body>
	<div class="likeList-wrap box">
	 <div class="userInfo-title">주문조회</div>
		<div class="accordionWrapper ">
			<table class="titleTable">
				<!-- 고정 타이틀 -->
				<tr>
					<th style="width: 10%;">주문번호</th>
					<th style="width: 30%;">주소지</th>
					<th style="width: 10%;">이름</th>
					<th style="width: 15%;">전화번호</th>
					<th style="width: 10%;">우편번호</th>
					<th style="width: 15%;">주문날짜</th>
				</tr>
			</table>
			<!-- 고정 타이틀 -->
			<!-- 반복 -->
			<div class="accordionItem close">
				<div class="accordionItemHeading">
					<table>
						<tr>
							<td style="width: 10%;">A01</td>
							<td style="width: 30%;">경기도성남시중원구오리역</td>
							<td style="width: 10%;">김이름</td>
							<td style="width: 15%;">010-1234-1234</td>
							<td style="width: 10%;">12345</td>
							<td style="width: 15%;">2022-12-08</td>
						</tr>
					</table>
				</div>

				<div class="accordionItemContent">
					<br>
					<div class="orderDetail-title">[주문번호: AQW01] [주문시간:
						2022-12-08-12:00] [총금액: 1000,000원]</div>
					<br>
					<br>
					<table>
						<tr style="color: #ffffff; margin-bottom: 5px;">
							<th style="width: 15%;">주문상세번호</th>
							<th style="width: 10%;">주문금액</th>
							<th style="width: 10%;">주문수량</th>
							<th style="width: 10%;">상품코드</th>
							<th style="width: 30%;">상품명</th>
							<th style="width: 15%;">상품금액</th>
						</tr>
						<tr>
							<td style="width: 15%;">AQW01</td>
							<td style="width: 10%;">100,000원</td>
							<td style="width: 10%;">2개</td>
							<td style="width: 10%;">a01</td>
							<td style="width: 30%;">ksadjpoasjdpsajpdspasdjasm[상품명]</td>
							<td style="width: 15%;">50,000원</td>
						</tr>
						<tr>
							<td style="width: 15%;">AQW01</td>
							<td style="width: 10%;">100,000원</td>
							<td style="width: 10%;">2개</td>
							<td style="width: 10%;">a01</td>
							<td style="width: 30%;">ksadjpoasjdpsajpdspasdjasm[상품명]</td>
							<td style="width: 15%;">50,000원</td>
						</tr>
						<tr>
							<td style="width: 15%;">AQW01</td>
							<td style="width: 10%;">100,000원</td>
							<td style="width: 10%;">2개</td>
							<td style="width: 10%;">a01</td>
							<td style="width: 30%;">ksadjpoasjdpsajpdspasdjasm[상품명]</td>
							<td style="width: 15%;">50,000원</td>
						</tr>
						<tr>
							<td style="width: 15%;">AQW01</td>
							<td style="width: 10%;">100,000원</td>
							<td style="width: 10%;">2개</td>
							<td style="width: 10%;">a01</td>
							<td style="width: 30%;">ksadjpoasjdpsajpdspasdjasm[상품명]</td>
							<td style="width: 15%;">50,000원</td>
						</tr>
					</table>
				</div>
			</div>
			<!-- 반복 -->
			
			<div class="accordionItem close">
				<div class="accordionItemHeading">
					<table>
						<tr>
							<td style="width: 10%;">A01</td>
							<td style="width: 30%;">경기도성남시중원구오리역</td>
							<td style="width: 10%;">김이름</td>
							<td style="width: 15%;">010-1234-1234</td>
							<td style="width: 10%;">12345</td>
							<td style="width: 15%;">2022-12-08</td>
						</tr>
					</table>
				</div>
				<div class="accordionItemContent">
					<br>
					<div class="orderDetail-title">[주문번호: AQW01] [주문시간:
						2022-12-08-12:00] [총금액: 1000,000원]</div>
					<br>
					<br>
					<table>
						<tr style="color: #ffffff; margin-bottom: 5px;">
							<th style="width: 15%;">주문상세번호</th>
							<th style="width: 10%;">주문금액</th>
							<th style="width: 10%;">주문수량</th>
							<th style="width: 10%;">상품코드</th>
							<th style="width: 30%;">상품명</th>
							<th style="width: 15%;">상품금액</th>
						</tr>
						<tr>
							<td style="width: 15%;">AQW01</td>
							<td style="width: 10%;">100,000원</td>
							<td style="width: 10%;">2개</td>
							<td style="width: 10%;">a01</td>
							<td style="width: 30%;">ksadjpoasjdpsajpdspasdjasm[상품명]</td>
							<td style="width: 15%;">50,000원</td>
						</tr>
					</table>
				</div>
			</div>
			
			
			
			
			
		</div>
	</div>


	<script type="text/javascript">
		var accItem = document.getElementsByClassName('accordionItem');
		var accHD = document.getElementsByClassName('accordionItemHeading');
		for (i = 0; i < accHD.length; i++) {
			accHD[i].addEventListener('click', toggleItem, false);
		}
		function toggleItem() {
			var itemClass = this.parentNode.className;
			for (i = 0; i < accItem.length; i++) {
				accItem[i].className = 'accordionItem close';
			}
			if (itemClass == 'accordionItem close') {
				this.parentNode.className = 'accordionItem open';
			}
		}
	</script>
</body>
</html>