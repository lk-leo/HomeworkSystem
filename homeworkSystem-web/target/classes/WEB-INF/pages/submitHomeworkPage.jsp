<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>提交作业</title>
<%
	pageContext.setAttribute("ctp", request.getContextPath());
%>
</head>
<body>
<a href="${ctp }/mainMenu/student/chooseQuestion/${requestScope.id}">返回</a>
<p>${requestScope.questionContext}</p>
<form action="${ctp }/homework/${requestScope.questionId}/${requestScope.id}" method="post">
	<!-- <input type="text" maxlength="1000" name="context"> -->
	<textarea id="area" name="homeworkContext" rows="40" cols="60">
		${requestScope.homeworkContext}
	</textarea><br/>
	<c:if test="${isLate==false}">
		<button οnclick="this#area.submit()">提交</button>
	</c:if>
	<c:if test="${isLate==true}">
		已截止，不能提交
	</c:if>
</form>
</body>
</html>