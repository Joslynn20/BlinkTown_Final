<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/shop/cart.css">
</head>
<body>
  <section class="cart">
  <h1>장바구니</h1>
        <div class="cart__information">
            <ul>
                <li>장바구니 상품은 session에 저장됩니다.</li>
                <li>가격, 옵션 등 정보가 변경된 경우 주문이 불가할 수 있습니다.</li>
            </ul>
        </div>
         <form>
         <div class="cart__optionbtn">
         	<button class="cart__list__optionbtn moving-grad">선택상품 삭제</button>
         	<button class="cart__list__optionbtn moving-grad">장바구니 비우기</button>
        </div>
        <table class="cart__list">   
     
                <thead>
                    <tr>
                        <td>
                        	<div class="checkbox-animate" >
							  <label> <input type="checkbox" name='cart-select' value='selectall'
									onclick='selectAll(this)'> <span class="input-check"></span>
							  </label>
							</div>
						</td>
                        <td colspan="2">상품정보</td>
                        <td>옵션</td>
                        <td>상품금액</td>
                    </tr>
                </thead>
                <tbody>
                    <tr class="cart__list__detail">
                        <td>
							<div class="checkbox-animate">
							  <label>
							    <input type="checkbox"  name='cart-select' value='제품명'>
							    <span class="input-check"></span>
							  </label>
							</div>
						</td>
                        <td><img src="${pageContext.request.contextPath}/img/FIGURE_JENNIE.png" alt="magic keyboard"></td>
                        <td><a href="#">카테고리명</a>
                            <p>제품명</p>
                          
                        <td>
                            <p>총 주문수량: <span>1</span>개</p>
                        </td>
                        <td>
							  <span class="price">11662</span><span>원</span>
                        </td>
                    </tr>
           
                </tbody>
               
                <tfoot>
                	<tr>
                	<td colspan="5"></td>
                	</tr>
                    <tr class="cart__list__detail moving-grad">
                        <td></td>
                        <td colspan="3" style="background-color: inherit;"></td>
  
                        <td><span class="price"><span>총 </span>116620<span>원</span></span><br>
                        </td>
                    </tr>
                    
                </tfoot>
            
        </table>
        <div class="cart__mainbtns">
            <button class="cart__bigorderbtn left">쇼핑 계속하기</button>
            <button class="cart__bigorderbtn right" type="submit">주문하기</button>
        </div>
        </form>
    </section>
    
    <script type="text/javascript">
    function selectAll(selectAll)  {
    	  const checkboxes 
    	       = document.getElementsByName('cart-select');
    	  
    	  checkboxes.forEach((checkbox) => {
    	    checkbox.checked = selectAll.checked;
    	  })
    	}
    
    </script>
</body>
</html>