<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<title>布置作业</title>
<%
	pageContext.setAttribute("ctp", request.getContextPath());
%>
</head>
<body>
<a href="${ctp }/mainMenu/teacher/myCourse/${teacherId}">返回主界面</a>
<h1>选这门课的学生</h1>
<table border="1" >
	<tr>
		<th>学号</th>
		<th>姓名</th>
		<th>性别</th>
	</tr>
	<c:forEach items="${requestScope.allTheStudents}" var="stu">
		<tr>
			<td>${stu.studentId}</td>
			<td>${stu.userName }</td>
			<td>${stu.gender }</td>
		</tr>
	</c:forEach>
</table><br/><br/>
<h1>我布置过的作业</h1>
<table border="1" >
	<tr>
		<th>问题编号</th>
		<th>题目</th>
		<th>截止日期</th>
		<th>操作</th>
		<th>已提交</th>
		<th>查重</th>
		<th>操作</th>
	</tr>
	<c:forEach items="${requestScope.allTheQuestions}" var="que">
		<tr>
			<td>${que.questionId}</td>
			<td>${que.outline }</td>
			<td>${que.deadline }</td>
			<td>
				<a  onclick="alert('成功取消本次作业！')" href="${ctp }/deleteQuestion/${requestScope.teacherId}/${courseId }/${que.questionId}">
					取消这次作业
				</a>
			</td>
			<td>${que.num }</td>
			<td>
				<c:if test="${que.dupCheck==0 }">
					<a onclick="alert('查重启动，正在后台计算重复度')" href="${ctp }/duplicateChecking/true/${requestScope.teacherId}/${courseId }/${que.questionId}">
						未启动
					</a>
				</c:if>
				
				<c:if test="${que.dupCheck==1 }">
					<a onclick="alert('查重关闭')" href="${ctp }/duplicateChecking/false/${requestScope.teacherId}/${courseId }/${que.questionId}">
						已启动
					</a>
				</c:if>
			</td>
			<td>
				<c:if test="${que.num==0}">
					还没有学生提交作业
				</c:if>
				<c:if test="${que.num!=0}">
					<a  href="${ctp }/toCorrectHomeworkPage/${requestScope.teacherId}/${courseId}/${que.questionId}">
						批改作业
					</a>
				</c:if>
			</td>
		</tr>
	</c:forEach>
</table>

<br/><br/>
<h2>布置作业</h2>
<form action="${ctp }/newQuestion/${teacherId}/${courseId}" method="post">
	作业题目：<input name="questionContext"><br/>
	截止时间：<select	 name="year">
				<c:forEach items="${year}" var="y">
					<option value="${y }">${y }</option>
				</c:forEach>
			</select>年
			<select	 name="month">
				<c:forEach items="${month}" var="m">
					<option value="${m }">${m }</option>
				</c:forEach>
			</select>月
			<select	 name="day">
				<c:forEach items="${day}" var="d">
					<option value="${d }">${d }</option>
				</c:forEach>
			</select>日
			<select	 name="hour">
				<c:forEach items="${hour}" var="h">
					<option value="${h }">${h }</option>
				</c:forEach>
			</select>时
	<button onclick="this.form.submit()">提交</button>
</form>
</body>
</html>