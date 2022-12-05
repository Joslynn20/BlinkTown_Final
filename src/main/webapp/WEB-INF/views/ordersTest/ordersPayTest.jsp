<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%-- <%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %> --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
  <!-- jQuery -->
  <script type="text/javascript" src="https://code.jquery.com/jquery-3.6.1.min.js" ></script>
  <!-- iamport.payment.js -->
  <script type="text/javascript" src="https://cdn.iamport.kr/js/iamport.payment-1.1.8.js"></script>
<script>
$(function() {
		//폼양식 name에 entity기준으로 동일하게 넣기
		//폼양식 안에 히든으로 넣기 1,2,3
		//1) security: <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
		//2) productList:<input type="hidden" name="${productList}" value="${productList}">
		//3) orderdetailsList:<input type="hidden" name="${orderdetailsList}" value="${orderdetailsList}">
		
		$("#checkOutBtn").on("click", function() {
			//결제하기 버튼 누르면 주문 실행
			//리턴값 주문정보orders, 총합계=amount 결제할 때 사용
			
			$.ajax({
				url: "${pageContext.request.contextPath}/~~(4 : OrdersController의 insertOrdersOrderdetails 메소드 mapping url)",
				type: "post",
				dataType: "json",
				data: $("#폼이름 입력").serialize(),
				success: function(result){
					
					//IMP객체 생성, 결제준비
					var IMP = window.IMP;
					IMP.init("imp75802403"); //가맹점 식별코드
					//결제 준비하기 끝
					
					//IMP.request_pay(param, callback) 결제창 호출
					//회원 정보대신 주문 정보 송출
// 					//없을때는 회원정보로 전송(email)->주석처리
					IMP.request_pay({
						pg : "kakaopay", //"html5_inicis" //$("#paymentMethod").val()->선택지두고 페이팔도 구현할 수 있음
						pay_method : "card",
						merchant_uid : result.orders.ordersNo , //"${orders.ordersNo}"
						name : "BLINK_TOWN_GOODS",
						amount : result.amount , //"${amount}"
// 						buyer_email : result.orders.users.usersEmail , //"${orders.users.usersEmail}",
						buyer_name : result.orders.ordersReceiverName , //"${orders.ordersReceiverName}",
						buyer_tel : result.orders.ordersReceiverPhone , //"${orders.ordersReceiverPhone}",
						buyer_addr : result.orders.ordersAddr , //"${orders.ordersAddr}",
						buyer_postcode : result.orders.ordersZipcode , //"${orders.ordersZipcode}",
						m_redirect_url : "http://localhost:8000/"
					}, //IMP.request_pay end 
					function(rsp){//callback
						if(rsp.success){
							//결제 성공 시: 결제 승인에 성공한 경우
							//일치하는지 검증하기(검증 메소드 컨트롤러에서 호출)
							$.ajax({
							url: "${pageContext.request.contextPath}/verifyIamport/",
				            type: "post",
				            dataType: "json",
				            data: {
				            	"${_csrf.parameterName}": "${_csrf.token}",
				                imp_uid: rsp.imp_uid,
// 				                merchant_uid: rsp.merchant_uid, //imp_uid로 조회 및 사용
// 				                orders: result.orders //"${orders}" //orders 다시 담는게 진행이 안되면 위의 merchant_uid로 정보 찾아서 진행하는걸로 할 것
				            },
							success: function(done){
								alert("주문 및 결제가 완료되었습니다");
							    //검증이후 페이지 이동:주문내역 페이지나 주문성공 페이지로 이동
								location.href="${pageContext.request.contextPath}/~~(1-1 : Orders컨트롤러의 마이페이지-주문내역출력 mapping url 또는 주문 성공 페이지)&${_csrf.parameterName}=${_csrf.token}";
							}, //success end
							error: function(err){
								alert("결제에 실패하였습니다");
							}//error end
							});//ajax end
						}//rsp.success end
						else{
							alert("결제에 실패하였습니다.");
						}//else end
					}); //rsp end
				}, //success end
				error : function(err){
					let msg = "주문에 실패했습니다. 다시 주문해주세요.";
					alert(msg);
				}//error end
			}); //ajax end
		});//맨 위의 #~~Btn 끝
}); //function end
</script>
</head>
<body>

</body>
</html>