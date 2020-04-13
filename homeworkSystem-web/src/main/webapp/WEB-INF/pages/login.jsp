<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isErrorPage="true"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Homework System</title>
	<link rel="stylesheet" type="text/css" href="css/loginStyle.css"  >
	
	<script type="text/javascript" src="jquery/jquery-3.4.1.min.js"></script>
 	
</head>
<body>
	
	<!-- <h1>学生注册</h1><br/><br/> -->
	<!-- class对元素进行分类（css文件选用时用.类名）     id指定元素唯一id -->
	<div class="container">
		<h1 style="color: black">学生作业管理系统</h1>
		<img alt="" src="css/wall.jpg" >
		<div class="panel">
			<div class="content login">
				<div class="switch">
					<span id="login" class='active'> 登 陆 </span><span>/</span><span id="signup" class='active'> 注 册 </span>
				</div>
				<form:form action="login" modelAttribute="person" method="post">
				<!-- <input type="hidden" name="_method" value="put"> -->
				<div class="input" placeholder="学号/工号">
					<form:input path="id" maxlength="30"/><form:errors path="id"/>
				</div>
		 		<div class="input" placeholder="密码">
					<form:password path="passWord"/><form:errors path="passWord"/>
				<div>
				<div class="input" placeholder="姓名" id="name">
					<form:input  path="userName"/><form:errors path="userName"/>
				</div>
				<div class="input" placeholder="性别" id="gender">
					<form:input path="gender"/><form:errors path="gender"/>
				</div>
				<div class="input">
					<form:select id="select" path="type">
						<option value="student">学生</option>
						<option value="teacher">老师</option>
					</form:select>
				</div>
				${error }
		 		<span>忘记密码?</span>
				<button class="submit" οnclick="this.form.submit()">登陆</button>
			</form:form>
			</div>
		</div>
	</div>
</body>
<!-- <script src="jquery/jquery.min.js"></script> -->
<script type="text/javascript">
	
	$('#login').click(function(){
		$('.switch span').removeClass('active');
		$(this).addClass('active');
		
		$(this).parents('.content').removeClass('signup');
		$(this).parents('.content').addClass('login');
	})
	$('#signup').click(function(){
		$('.switch span').removeClass('active');
		$(this).addClass('active');
		
		$(this).parents('.content').removeClass('login');
		$(this).parents('.content').addClass('signup');
	})
	
	$('.input input').on('focus',function(){
		$(this).parent().addClass('focus');
	})
	$('.input input').on('blur',function(){
		if($(this).val()==='')
			$(this).parent().removeClass('focus');
	})
</script>
</html>