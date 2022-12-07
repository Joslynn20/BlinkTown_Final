<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%-- <%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %> --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%-- <meta name="_csrf_header" th:content="${_csrf.headerName}"> --%>
<%-- <meta name="_csrf" th:content="${_csrf.token}"> --%>
<meta name="_csrf" content="${_csrf.token}"/>
<!-- 403에러 대비 -->
<title>Insert title here</title>
  <!-- jQuery -->
  <script type="text/javascript" src="https://code.jquery.com/jquery-3.6.1.min.js" ></script>
  <!-- iamport.payment.js -->
  <script type="text/javascript" src="https://cdn.iamport.kr/js/iamport.payment-1.1.8.js"></script>
  <!-- 주소지API(다음주소api) -->
  <script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script>
$(function() {
// 			alert("시작"); //호출확인용
		$("#checkOutBtn").on("click", function() {
			//IMP객체 생성, 결제준비
			var IMP = window.IMP;
			IMP.init("imp75802403"); //가맹점 식별코드
			//결제 준비하기 끝
			
			//결제하기 버튼 누르면 주문 실행
			//리턴값 주문정보orders, 총합계=amount 결제할 때 사용
// 			alert("버튼클릭");//호출됨
			$.ajax({
				url: "${pageContext.request.contextPath}/orders/checkout", //${pageContext.request.contextPath}
				type: "post",
// 				contentType : "application/json",//컨트롤러로 보낼때 json사용시->작동안함
 				dataType: "json", //리턴값 Map사용
				data: $("#ordersForm").serialize(), 
				success: function(result){
// 					alert("db성공, amount="+result);//출력확인
// 					alert(result.orders.ordersNo);
// 					alert(result.amount);
					
					IMP.request_pay({ //결제창 호출
						pg : "kakaopay", //"html5_inicis" //$("#paymentMethod").val()->선택지두고 페이팔도 구현할 수 있음
						pay_method : "card",
						merchant_uid : result.orders.ordersNo ,//"${orders.ordersNo}" ,//result.orders.ordersNo , 
						name : "BLINK_TOWN_GOODS",
						amount : result.amount ,
						buyer_name : result.orders.ordersReceiverName ,
						buyer_tel : result.orders.ordersReceiverPhone ,
						buyer_addr : result.orders.ordersAddr ,
						buyer_postcode : result.orders.ordersZipcode ,
						m_redirect_url : "http://localhost:8000/"
					}, //IMP.request_pay end 
					function(rsp){//callback
						if(rsp.success){
// 							alert("검증시작");
							//결제 성공 시: 결제 승인에 성공한 경우
							//일치하는지 검증하기(검증 메소드 컨트롤러에서 호출)
							$.ajax({
							url: "${pageContext.request.contextPath}/verifyIamport",
				            type: "post",
// 				            dataType: "json", //리턴되는 값이 collection일때 사용
				            dataType: "text", //void도 text
				            data: {
				            	"${_csrf.parameterName}": "${_csrf.token}",
				                imp_uid: rsp.imp_uid
				                },
							success: function(done){
								alert("주문 및 결제가 완료되었습니다");
							    //검증이후 페이지 이동:주문내역 페이지나 주문성공 페이지로 이동~~(1-1 : Orders컨트롤러의 마이페이지-주문내역출력 mapping url 또는 주문 성공 페이지)
								location.href="${pageContext.request.contextPath}/ordersTest/ordersResult";//&${_csrf.parameterName}=${_csrf.token}";
							}, //success end
							error: function(err){
								alert("3결제에 실패하였습니다");
							}//error end
							});//ajax end
						}//rsp.success end
						else{//(request,status,err)
							alert("2결제에 실패하였습니다.");
// 							alert(rsp.error);
// 							alert("code="+request.status+"\n"+"message"+request.responseText+"\n"+"error:"+err);
							$.ajax({
								url: "${pageContext.request.contextPath}/orders/delete", //${pageContext.request.contextPath}
								type: "post", 
								dataType: "text",
								data: {merchant_uid: rsp.merchant_uid,
				            	"${_csrf.parameterName}": "${_csrf.token}"
				            	}
// 							},
// 							success: function(rs){alert("2결제실패끝");}
// 							error : function(request,status,err){
// 							alert("code="+request.status+"\n"+"message"+request.responseText+"\n"+"error:"+err);
							})//2실패 ajax끝
// 							alert("2결제실패 ajax 끝");
						}//else end
					}); //rsp,IMPrequest_pay end
				}, //success end
				error : function(request,status,err){
					$.ajax({
						url: "${pageContext.request.contextPath}/orders/delete", //${pageContext.request.contextPath}
						type: "post", //"post",
// 						contentType : "application/json",//컨트롤러로 보낼때
						dataType: "text",
						data: result.orders
					})//1실패 ajax끝
					let msg = "1주문에 실패했습니다. 다시 주문해주세요.";
					alert(msg);
					alert("code="+request.status+"\n"+"message"+request.responseText+"\n"+"error:"+err);
				}//error end
			}); //ajax end
		});//맨 위의 #~~Btn 끝
}); //button function end
// }//function
</script>

<script>
    function sample6_execDaumPostcode() {
        new daum.Postcode({
            oncomplete: function(data) {
                // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

                // 각 주소의 노출 규칙에 따라 주소를 조합한다.
                // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
                var addr = ''; // 주소 변수
                var extraAddr = ''; // 참고항목 변수

                //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
                if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
                    addr = data.roadAddress;
                } else { // 사용자가 지번 주소를 선택했을 경우(J)
                    addr = data.jibunAddress;
                }

                // 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
                if(data.userSelectedType === 'R'){
                    // 법정동명이 있을 경우 추가한다. (법정리는 제외)
                    // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
                    if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                        extraAddr += data.bname;
                    }
                    // 건물명이 있고, 공동주택일 경우 추가한다.
                    if(data.buildingName !== '' && data.apartment === 'Y'){
                        extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                    }
                    // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
                    if(extraAddr !== ''){
                        extraAddr = ' (' + extraAddr + ')';
                    }
                }

                var totalAddr=addr+' '+extraAddr+' ';
                
                // 우편번호를 해당 필드에 넣는다.
                document.getElementById('sample6_postcode').value = data.zonecode;
                document.getElementById("sample6_address").value = totalAddr;
                // 커서를 주소의 맨 뒤로 이동한다.
                document.getElementById("sample6_address").focus();
                document.getElementById("sample6_address").value = '';
                document.getElementById("sample6_address").value = totalAddr;
            }
        }).open();
    }
</script>
</head>
<body>
<form name="orders" id="ordersForm" method="post" >
<input type="text" name="ordersZipcode" id="sample6_postcode" placeholder="ZipCode">
<input type="button" onclick="sample6_execDaumPostcode()" value="Find ZipCode"><br>
<input type="text" name="ordersAddr" id="sample6_address" placeholder="Adress"><br>
<input type="text" name="ordersReceiverName" id="ordersReceiverName" placeholder="name"><br>
<input type="text" name="ordersReceiverPhone" id="ordersReceiverPhone" placeholder="phone"><br>

<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
<%-- <input type="hidden" name="cartList" value="${cartList}"> --%>
<input type="hidden" name="orderdetailsList" value="${orderdetailsList}">

<input type="button" id="checkOutBtn" value="결제하기" >
</form>
<!-- onclick="checkout()" -->
</body>
</html>