<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
@import url("https://fonts.googleapis.com/css2?family=Pacifico&display=swap");

html, body {
  background-color: #000;
  width: 100%;
  font-family: "Pacifico", cursive;
  overflow-x: hidden;
}

.cover {
  width: 100vw;
  margin-bottom: 12vw;
  display: flex;
}

.cover:nth-child(1) {
  transform: rotate(-2deg);
  background-color: #ff3796;
}

.cover:nth-child(2) {
  transform: rotate(2deg);
  background-color: #ecfe04;
  justify-content: flex-end;
}

.cover:nth-child(3) {
  transform: rotate(-2deg);
  background-color: #5800ff;
}

.cover:nth-child(4) {
  transform: rotate(2deg);
  background-color: #33ff33;
  justify-content: flex-end;
  margin-bottom: 100vh;
}

p {
  display: flex;
  font-size: clamp(2vw, 8vw, 5rem);
}
</style>

</head>
<body>
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

<script type="text/javascript">
	const pTag1 = document.querySelector('.first-parallel')
	const pTag2 = document.querySelector('.second-parallel')
	const pTag3 = document.querySelector('.third-parallel')
	const pTag4 = document.querySelector('.forth-parallel')

	const textArr1 = 'Yummy Tasty Delicious Useful Coding Yummy Yummmmy Yummmmmmmmmy yum'.split(' ')
	const textArr2 = 'Chicken Hamburger Pizza Salad Sushi Ramen Gimbab JJajangmyeon'.split(' ')
	const textArr3 = "Let's Dive Into This Tutorial Take It Easy! Don't Worry".split(' ')
	const textArr4 = 'Pure Moral Conscientious Meritorious Worthy Exemplary Upright '.split(' ')

	let count1 = 0
	let count2 = 0
	let count3 = 0
	let count4 = 0

	initTexts(pTag1, textArr1)
	initTexts(pTag2, textArr2)
	initTexts(pTag3, textArr3)
	initTexts(pTag4, textArr4)

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