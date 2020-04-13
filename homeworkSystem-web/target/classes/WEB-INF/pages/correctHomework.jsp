<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>批改作业</title>
<%
	pageContext.setAttribute("ctp", request.getContextPath());
%>
<a href="${ctp }/toAssignQuestionPage/${teacherId}/${courseId}">返回</a>
</head>
<body>
<table border="1">
	<tr>
		<th>学号</th>
		<th>姓名</th>
		<th>分数</th>
		<th>重复度</th>
	</tr>
	<c:forEach items="${info.list }" var="work">
		<tr>
			<td>${work.studentId }</td>
			<td>${work.userName }</td>
			<td>${work.score }</td>
			<td>${work.repeatability}%</td>
		</tr>
		<tr>
			<!-- 自动换行 -->
			<th colspan="4" style="word-break:break-all; word-wrap:break-word;">作业</th>
		</tr>
		<tr>
			<td colspan="4" style="word-break:break-all; word-wrap:break-word;">${work.homework }</td>
		</tr>
	</c:forEach>
	<tr>
		<td colspan="4" rowspan="10">
			<!-- 前往首页 -->
			<a href="${ctp }/toCorrectHomeworkPage/${teacherId}/${courseId}/${questionId}?pageNum=1">首页</a>
			<!-- 上一页 -->
			<c:if test="${pageNum!=1}">
					<a href="${ctp }/toCorrectHomeworkPage/${teacherId}/${courseId}/${questionId}?pageNum=${info.prePage}">上一页</a>
				</c:if>
			<c:if test="${pageNum==1}">
				上一页
			</c:if>
			<!-- 点击对应数字切换到对应页面 -->
			<c:forEach items="${info.navigatepageNums}" var="num">
				<c:if test="${num==info.pageNum}">
					${num}
				</c:if>
				<c:if test="${num!=info.pageNum}">
					<a href="${ctp }/toCorrectHomeworkPage/${teacherId}/${courseId}/${questionId}?pageNum=${num}">${num }</a>
				</c:if>
			</c:forEach>
			
			<!-- 下一页 -->
			<c:if test="${pageNum!=info.pages}">
				<a href="${ctp }/toCorrectHomeworkPage/${teacherId}/${courseId}/${questionId}?pageNum=${info.nextPage}">下一页</a>
			</c:if>
			<c:if test="${pageNum==info.pages}">
				下一页
			</c:if>
			<!-- 前往末页 -->
			<a href="${ctp }/toCorrectHomeworkPage/${teacherId}/${courseId}/${questionId}?pageNum=${info.pages}">末页</a>
		</td>
	</tr>
</table>
<form action="${ctp }/score/${teacherId}/${courseId}/${questionId}/${homeworks.get(info.pageNum-1).studentId}">
	成绩:<input name="score"><br/>
	<input type="hidden" name="pageNum" value="${info.pageNum}">
	<button onclick="this.form.submit()">提交</button>
</form>
</body>
</html>