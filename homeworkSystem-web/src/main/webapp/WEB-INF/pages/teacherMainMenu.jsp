<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>教师界面</title>
<%
	pageContext.setAttribute("ctp", request.getContextPath());
%>
</head>
<body>
	<h1>欢迎使用作业管理系统</h1><br/>
	<a href="${ctp }/toMyInfoPage/teacher/${requestScope.id}">我的信息</a>
	<a href="${ctp }/mainMenu/teacher/myCourse/${requestScope.id}">我的课程</a>
	<a href="${ctp }/mainMenu/teacher/createCourse/${requestScope.id}">开设课程</a>
	<a href="${ctp }/mainMenu/teacher/assignQuestion/${requestScope.id}">布置作业</a>
	<a href="${ctp }/mainMenu/teacher/correctHomework/${requestScope.id}">批改作业</a>
	<a href="${ctp }/toLogin">注销登录</a><br/>
	<!-- 以下所有的if语句，有且只有一个会在页面上展示 -->
	<c:if test="${page.equals(\"myCourse\") }">
		<h1>我的课程</h1>
		<table border="1" >
			<tr>
				<th>操作</th>
				<th>课程号</th>
				<th>课程名</th>
				<th>选课人数</th>
			</tr>
			<c:forEach items="${requestScope.allTheCourses}" var="cou">
				<tr>
					<td>
						<a  onclick="alert('关闭成功！')" href="${ctp }/closeCourse/${requestScope.id}/${cou.courseId}">
							关闭课程
						</a>
					</td>
					<td>${cou.courseId}</td>
					<td>${cou.courseName }</td>
					<td>${cou.num==null?0:cou.num }</td>
				</tr>
			</c:forEach>
		</table>
	</c:if>
	
	<c:if test="${page.equals(\"createCourse\") }">
		<h1>我的课程</h1>
		<table border="1" >
			<tr>
				<th>操作</th>
				<th>课程号</th>
				<th>课程名</th>
				<th>选课人数</th>
				<th>操作</th>
				
			</tr>
			<c:forEach items="${requestScope.allTheCourses}" var="cou">
				<tr>
					<td>
						<a  onclick="alert('关闭成功！')" href="${ctp }/closeCourse/${requestScope.id}/${cou.courseId}">
							关闭课程
						</a>
					</td>
					<td>${cou.courseId}</td>
					<td>${cou.courseName }</td>
					<td>${cou.num==null?0:cou.num }</td>
					<td>
						<a  href="${ctp }/toAssignQuestionPage/${requestScope.id}/${cou.courseId}">
							布置作业/批改作业
						</a>
					</td>
					
				</tr>
			</c:forEach>
		</table><br/><br/><br/><br/>
		<h2>开新课</h2>
		<form action="${ctp }/createCourse/${id}" method="post">
			课程名：<input name="courseName">
			<button onclick="this.form.submit()">提交</button>
		</form>
	</c:if>
	
	<c:if test="${page.equals(\"assignQuestion\") }">
		<h1>我的课程</h1>
		<table border="1" >
			<tr>
				<th>课程号</th>
				<th>课程名</th>
				<th>选课人数</th>
				<th>操作</th>
			</tr>
			<c:forEach items="${requestScope.allTheCourses}" var="cou">
				<tr>
					<td>${cou.courseId}</td>
					<td>${cou.courseName }</td>
					<td>${cou.num==null?0:cou.num }</td>
					<td>
						<a  href="${ctp }/toAssignQuestionPage/${requestScope.id}/${cou.courseId}">
							布置作业
						</a>
					</td>
				</tr>
			</c:forEach>
		</table>
	</c:if>
	
	<c:if test="${page.equals(\"correctHomework\") }">
		<h1>我的课程</h1>
		<table border="1" >
			<tr>
				<th>课程号</th>
				<th>课程名</th>
				<th>选课人数</th>
				<th>操作</th>
			</tr>
			<c:forEach items="${requestScope.allTheCourses}" var="cou">
				<tr>
					<td>${cou.courseId}</td>
					<td>${cou.courseName }</td>
					<td>${cou.num==null?0:cou.num }</td>
					<td>
						<a  href="${ctp }/toAssignQuestionPage/${requestScope.id}/${cou.courseId}">
							批改作业
						</a>
					</td>
				</tr>
			</c:forEach>
		</table>
	</c:if>
	
</body>
</html>