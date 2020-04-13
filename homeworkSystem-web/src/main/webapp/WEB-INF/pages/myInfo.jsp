<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>我的信息</title>
<%
	pageContext.setAttribute("ctp", request.getContextPath());
%>
</head>
<a href="${ctp }/mainMenu/${info.type }/myCourse/${info.id}">返回主界面</a>
<h1>我的信息</h1>
<form:form action="${ctp }/change/${info.type }" modelAttribute="info" method="post">
	<form:hidden path="id"/>
	学号/工号:${info.id}<br/>
	密码:<form:input path="passWord"/><form:errors path="passWord"/><br/>
	姓名:<form:input path="userName"/><form:errors path="userName"/><br/>
	性别:<form:input path="gender"/><form:errors path="gender"/><br/>
	<button οnclick="this.form.submit()">更改</button>
</form:form>
</body>
</html>