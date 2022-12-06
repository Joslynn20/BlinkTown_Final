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
<div class="box">
 <section class="main">
    <div class="wrapper">
        <div class="left-col">
            <div class="post">
                <div class="info">
                    <div class="user">
                        <div class="profile-pic"><img src="${pageContext.request.contextPath}/img/board/profile-pic.png" alt=""></div>
                        <p class="username">modern_web_channel</p>
                    </div>
                    <img src="img/option.PNG" class="options" alt="">
                </div>
                <img src="${pageContext.request.contextPath}/img/board/cover 1.png" class="post-image" alt="">
                <div class="post-content">
                    <div class="reaction-wrapper">
                        <img src="${pageContext.request.contextPath}/img/board/like.PNG" class="icon" alt="">
                        <img src="${pageContext.request.contextPath}/img/board/comment.PNG" class="icon" alt="">
                        <img src="${pageContext.request.contextPath}/img/board/send.PNG" class="icon" alt="">
                        <img src="${pageContext.request.contextPath}/img/board/save.PNG" class="save icon" alt="">
                    </div>
                    <p class="likes">1,012 likes</p>
                    <p class="description"> 안녕하세요~~~~~제니에요!!!!!!!!~</p>
                    <p class="post-time">2022-12-06</p>
                </div>
               
            </div>
        </div>

        <div class="right-col">
            
            <p class="suggestion-text">Suggestions for you</p>
            <div class="profile-card">
                <div class="profile-pic">
                    <img src="${pageContext.request.contextPath}/img/board/cover 9.png" alt="">
                </div>
                <div>
                    <p class="username">modern_web_channel</p>
                    <p class="sub-text">followed bu user</p>
                </div>
                <button class="action-btn">follow</button>
            </div>
            <div class="profile-card">
                <div class="profile-pic">
                    <img src="${pageContext.request.contextPath}/img/board/cover 10.png" alt="">
                </div>
                <div>
                    <p class="username">modern_web_channel</p>
                    <p class="sub-text">followed bu user</p>
                </div>
                <button class="action-btn">follow</button>
            </div>
            <div class="profile-card">
                <div class="profile-pic">
                    <img src="${pageContext.request.contextPath}/img/board/cover 11.png" alt="">
                </div>
                <div>
                    <p class="username">modern_web_channel</p>
                    <p class="sub-text">followed bu user</p>
                </div>
                <button class="action-btn">follow</button>
            </div>
            <div class="profile-card">
                <div class="profile-pic">
                    <img src="${pageContext.request.contextPath}/img/board/cover 12.png" alt="">
                </div>
                <div>
                    <p class="username">modern_web_channel</p>
                    <p class="sub-text">followed bu user</p>
                </div>
                <button class="action-btn">follow</button>
            </div>
            <div class="profile-card">
                <div class="profile-pic">
                    <img src="${pageContext.request.contextPath}/img/board/cover 13.png" alt="">
                </div>
                <div>
                    <p class="username">modern_web_channel</p>
                    <p class="sub-text">followed bu user</p>
                </div>
                <button class="action-btn">follow</button>
            </div>
            <div class="comment-wrapper">
                    <img src="${pageContext.request.contextPath}/img/board/smile.PNG" class="icon" alt="">
                    <input type="text" class="comment-box" placeholder="Add a comment">
                    <button class="comment-btn">post</button>
                </div>
        </div>
</div>
</section>
</div>
</body>
</html>