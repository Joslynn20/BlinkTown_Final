<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/board/boardDetails.css">
</head>
<body>

 <section class="main">
    <div class="wrapper">
        <div class="left-col">
            <div class="post box">
                <div class="info moving-grad">
                    <div class="user">
                        <div class="profile-pic"><img src="${pageContext.request.contextPath}/img/board/cover 9.png" alt=""></div>
                        <p class="username">${board.users.usersId}</p>
                    </div>
                    <img src="img/option.PNG" class="options" alt="">
                </div>
                <img src="${pageContext.request.contextPath}/save/${board.boardImg}" class="post-image" alt="">
                <div class="post-content">
                    <div class="reaction-wrapper">
                        <img src="${pageContext.request.contextPath}/img/board/like.PNG" class="icon" alt="">
                        <img src="${pageContext.request.contextPath}/img/board/comment.PNG" class="icon" alt="">
                        <img src="${pageContext.request.contextPath}/img/board/send.PNG" class="icon" alt="">
                    </div>
                    <p class="likes" id="likeBtn">좋아요 <span>${board.boardLikeNo}</span>개</p>
                    <p class="description">${board.boardContent}</p>
                    <p class="post-time">${board.boardRegDate}</p>
                </div>            
            </div>
        </div>

        <div class="right-col box">
        <div class="profile-card-wrap">
         <!-- 댓글양식 -->
        <div id="reply"></div>
    
         <!-- 댓글양식 -->
            
          
            </div>
            <div class="comment-wrapper">
                    <img src="${pageContext.request.contextPath}/img/board/smile.PNG" class="icon" alt="">
                    <input type="text" class="comment-box" placeholder="댓글을 입력해주세요" name="replyContent" id="replyContent">
                    <button class="comment-btn" type="submit" id="submitReply">post</button>
            </div>  
		<script type="text/javascript">
		$(function(){
			
			$(document).ajaxSend(function(e, xhr, options) {
			       xhr.setRequestHeader( "${_csrf.headerName}", "${_csrf.token}" );
			  });
			 
			$('#submitReply').on("click", function(){
				var replyContent = $('#replyContent').val();
				var boardNo = ${board.boardNo};				
				$.ajax({
					url : "${pageContext.request.contextPath}/reply/details/${board.boardNo}",
					type : "post",
					dataType : "json",
					data : {
						"replyContent" : replyContent
					},						
					success : function(reply) {							
						initReply();							
					},
					error : function() {
						alert("댓글 등록 실패");	
					}		
				});
			});// 등록끝
	
			
			///////////////////////////////
			//댓글 로딩
			function initReply() {								
				$.ajax({
					url : "${pageContext.request.contextPath}/reply/select",
					type : "post",
					dataType :"json",
					data : {
						"boardNo" : ${board.boardNo}
					},					
					success : function(result) {
						let str="";
						$.each(result.replyList , function(index, item){
							//result.nicList[index]
							str+='<div class="profile-card" >';
							str+="<div class='profile-pic'>"
							str+=" <img src='${pageContext.request.contextPath}/img/board/cover 10.png' alt=''> </div>";
							str+='<div class="profile-text"><p class="username">'+result.nicList[index] +'</p>';
							str+='<p class="sub-text">'+item.replyContent+'</p></div>';
							str+='<button class="action-btn" name="'+item.replyNo +'">x</button>';
							str+='</div>';
						})														
						$("#reply").html(str);							
					},
					error : function() {
						alert("댓글가져오기를 실패했습니다.");	
					}
	
				});
			}			
			////////////////////////////////////////////////////////////
			//댓글
			$(document).on("click","button[class=action-btn]", function(){
				alert($(this).attr("name"))
				$.ajax({
					url : "${pageContext.request.contextPath}/reply/delete",
					type : "post",
					dataType :"text", // 서버에서 보내준 데이터타입
					data : {
						"replyNo" : $(this).attr("name"),
						"boardNo" : ${board.boardNo}
					},					
					success : function(result) {
						alert("댓글을 삭제했습니다.");
						initReply();
					},
					error : function() {
						alert("댓글 삭제에 실패했습니다.");
					}

				});
			})
			
			////////////////////////////////////////
			initReply();	
		});//ready끝		
	</script>


        </div>
</div>
</section>

  <div class="background-img">
				<div class="cover">
				  <p class="first-parallel"></p>
				</div>
				<div class="cover">
				  <p class="second-parallel"></p>
				</div>
				<div class="cover">
				  <p class="third-parallel"></p>
				</div>
				<div class="cover">
				  <p class="forth-parallel"></p>
				</div>
			</div>
<script type="text/javascript">
	const pTag1 = document.querySelector('.first-parallel')
	const pTag2 = document.querySelector('.second-parallel')
	const pTag3 = document.querySelector('.third-parallel')
	const pTag4 = document.querySelector('.forth-parallel')

	
	const textArr1 = ' BLACKPINK IN YOUR AREA BLACKPINK IN YOUR AREA BLACKPINK IN YOUR AREA'.split(' ')

	let count1 = 0
	let count2 = 0
	let count3 = 0
	let count4 = 0

	
	initTexts(pTag1, textArr1)
	initTexts(pTag2, textArr1)
	initTexts(pTag3, textArr1)
	initTexts(pTag4, textArr1)


	function initTexts(element, textArray) {
	  textArray.push(...textArray)
	  for (let i = 0; i < textArray.length; i++) {
	    element.innerText += `${'${textArray[i]}'}\u00A0\u00A0\u00A0\u00A0`
	  }
	}

	function marqueeText(count, element, direction) {
	  if (count > element.scrollWidth / 2) {
	    element.style.transform = `translate3d(0, 0, 0)`
	    count = 0
	  }
	  element.style.transform = `translate3d(${'${direction * count}'}px, 0, 0)`

	  return count
	}

	function animate() {
	  count1++
	  count2++
	  count3++
	  count4++


	  count1 = marqueeText(count1, pTag1, -1)
	  count2 = marqueeText(count2, pTag2, 1)
	  count3 = marqueeText(count3, pTag3, -1)
	  count4 = marqueeText(count4, pTag4, 1)


	  window.requestAnimationFrame(animate)
	}

	function scrollHandler() {
	  count1 += 15
	  count2 += 15
	  count3 += 15
	  count4 += 15

	}

	window.addEventListener('scroll', scrollHandler)
	animate()
</script>
</body>
</html>