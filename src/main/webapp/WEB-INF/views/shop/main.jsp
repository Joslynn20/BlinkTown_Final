<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="${pageContext.request.contextPath}/js/jquery-3.6.1.min.js"></script>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/shop/shopMain.css">
</head>
<body>
<div class="shop-wrap">
	<div class="shop-main-wrap">
		<div class="shop-aside-container">
			<div class="shop-aside">
				<div class="shop-aside-title">SHOP</div>
				<div class="aside-menu">MEMBERSHIP</div>
				<div class="aside-menu">GOODS</div>
				<div class="aside-menu">ALBUM</div>
			</div>

		</div>
		<!-- shop-aside-container -->


		<div class="shop-main-coontainer">
			<div class="title">
				<div>MEMBERSHIP</div>
				<div>ONLY</div>
			</div>
			<div class="wrapper">
				<article class="flow">
					<div class="team">
						<ul class="auto-grid" role="list">
							<li><a href="/shop/details" target="_blank " class="profile">
									<h3 class="profile__name">[EUP23] BLACKPINK COLLECTIBLE
										FIGURE_ JISOO</h3>
									<div class="price-text">
										<p>000000</p>
										<p>원</p>
									</div> <img alt="Anita Simmons"
									src="${pageContext.request.contextPath}/img/FIGURE_JENNIE.png" />
							</a></li>

							<li><a href="#" target="_blank " class="profile">
									<h3 class="profile__name">[EUP23] BLACKPINK COLLECTIBLE
										FIGURE_ JISOO</h3>
									<div class="price-text">
										<p>000000</p>
										<p>원</p>
									</div> <img alt="Anita Simmons"
									src="${pageContext.request.contextPath}/img/FIGURE_JENNIE.png" />
							</a></li>

							<li><a href="#" target="_blank " class="profile">
									<h3 class="profile__name">[EUP23] BLACKPINK COLLECTIBLE
										FIGURE_ JISOO</h3>
									<div class="price-text">
										<p>000000</p>
										<p>원</p>
									</div> <img alt="Anita Simmons"
									src="${pageContext.request.contextPath}/img/FIGURE_JENNIE.png" />
							</a></li>

							<li><a href="#" target="_blank " class="profile">
									<h3 class="profile__name">[EUP23] BLACKPINK COLLECTIBLE
										FIGURE_ JISOO</h3>
									<div class="price-text">
										<p>000000</p>
										<p>원</p>
									</div> <img alt="Anita Simmons"
									src="${pageContext.request.contextPath}/img/FIGURE_JENNIE.png" />
							</a></li>
						</ul>
					</div>
				</article>
				<div class="goods-shop">
				<div class="title">
					<div>GOODS</div>
					<div>SHOP</div>							
				</div>
				<div class="select-sort">
					<select class="sort-option">
						<option class="sort-option"><spring:message code="Sort"/></option>
						<option class="sort-option" value="<spring:message code='Newproduct'/>"><spring:message code='Newproduct'/></option>
						<option class="sort-option" value="<spring:message code='Popularity'/>"><spring:message code='Popularity'/></option>
						<option class="sort-option" value="<spring:message code='Highprice'/>"><spring:message code='Highprice'/></option>
						<option class="sort-option" value="<spring:message code='Lowprice'/>"><spring:message code='Lowprice'/></option>
					</select>
				</div>
				<article class="flow">
					<div class="team">
						<ul class="auto-grid" role="list">
							<li><a href="#" target="_blank " class="profile">
									<h3 class="profile__name">[EUP23] BLACKPINK COLLECTIBLE
										FIGURE_ JISOO</h3>
									<div class="price-text">
										<p>000000</p>
										<p>원</p>
									</div> <img alt="Anita Simmons"
									src="${pageContext.request.contextPath}/img/FIGURE_JENNIE.png" />
							</a></li>
							<li><a href="#" target="_blank " class="profile">
									<h3 class="profile__name">[EUP23] BLACKPINK COLLECTIBLE
										FIGURE_ JISOO</h3>
									<div class="price-text">
										<p>000000</p>
										<p>원</p>
									</div> <img alt="Anita Simmons"
									src="${pageContext.request.contextPath}/img/FIGURE_JENNIE.png" />
							</a></li>
							<li><a href="#" target="_blank " class="profile">
									<h3 class="profile__name">[EUP23] BLACKPINK COLLECTIBLE
										FIGURE_ JISOO</h3>
									<div class="price-text">
										<p>000000</p>
										<p>원</p>
									</div> <img alt="Anita Simmons"
									src="${pageContext.request.contextPath}/img/FIGURE_JENNIE.png" />
							</a></li>
							<li><a href="#" target="_blank " class="profile">
									<h3 class="profile__name">[EUP23] BLACKPINK COLLECTIBLE
										FIGURE_ JISOO</h3>
									<div class="price-text">
										<p>000000</p>
										<p>원</p>
									</div> <img alt="Anita Simmons"
									src="${pageContext.request.contextPath}/img/FIGURE_JENNIE.png" />
							</a></li>
						</ul>
					</div>
				</article>
				</div><!-- goods-shop -->
			</div>
<div class="pagination">
  <a href="#">&laquo;</a>
  <a href="#">1</a>
  <a class="active" href="#">2</a>
  <a href="#">3</a>
  <a href="#">4</a>
  <a href="#">5</a>
  <a href="#">6</a>
  <a href="#">&raquo;</a>
</div>
</div>
		<!-- shop-main-container -->
	</div>
</div>
	<script type="text/javascript">
	const nonClick = document.querySelectorAll(".aside-menu");

	function handleClick(event) {
	  // div에서 모든 "click" 클래스 제거
	  nonClick.forEach((e) => {
	    e.classList.remove("click");
	  });
	  // 클릭한 div만 "click"클래스 추가
	  event.target.classList.add("click");
	}

	</script>
</body>
</html>