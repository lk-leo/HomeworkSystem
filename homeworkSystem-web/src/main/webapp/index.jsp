<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>HomeworkSystem</title>
	<link rel="stylesheet" type="text/css" href="css/welcomeStyle.css"  >
	<script type="text/javascript" src="jquery/jquery-3.4.1.min.js"></script>
<%
	pageContext.setAttribute("ctp", request.getContextPath());
%>
</head>
<body>
	
	
	<div class="container">
		<div class="welcome">
			<h1>Welcome!</h1>
		</div>
		<div class="box">
			<div class="imgBx">
				<img alt="" src="css/road.jpg">
			</div>
			<div class="content">
				<h3>简介</h3>
				<p>
					青春，有嬉笑声与哭泣声夹杂的年华，
					青春的少年是蓝天中翱翔的幼鹰，虽然没有完全长大，
					有些稚气， 有些懵懂，
					脱不开父母的双手却极力想去找寻属于自己的一片天空，
					为的是一时的激情，为的是一种独自翱翔的感觉!
				</p>
			</div>
		</div>
		<div class="box">
			<div class="imgBx">
				<img alt="" src="css/jungle.jpg">
			</div>
			<div class="content">
				<h3>功能</h3>
				<p>
					最为值得珍惜的是今天，因为最容易流逝的就是今天，把握今天就是把握希望，
					分分秒秒只是瞬间，而所乘载的分分秒秒就叫做一天，
					时间的流逝往往是在不经意之间，人生几回，青春更珍贵，
					对于我们这个年龄的青少年来说，青春已不足二十载，
					在学习的生活中我们必须靠自己的力量，
					驾驭着自己的小船驶向希望的彼岸。
				</p>
			</div>
		</div>
		<div class="box">
			<div class="imgBx">
				<img alt="" src="css/book.jpg">
			</div>
			<div class="content">
				<h3>分工</h3>
				<p>
					整个世界，因为有了阳光，城市有了生机;细小心灵，因为有了阳光，
					内心有了舒畅。明媚的金黄色，树丛间小影成像在叶片上泛有的点点破碎似的金灿，
					海面上直射反映留有的随波浪层层翻滚的碎片，为这大自然创造了美景，
					惹人醉的温馨之感，浓浓暖意中夹杂着的明朗与柔情，
					让雨过天晴后久违阳光的心灵重新得到了滋润!
				</p>
			</div>
		</div>
		<div class="login">
			<a href="toLogin"><h1>LOGIN</h1></a><br/>
		</div>
	</div>
</body>
</html>