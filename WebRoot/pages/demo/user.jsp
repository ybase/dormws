<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE html>
<html>
<head></head>
<body>
	用户列表:
	<table>
		<c:forEach var="user" items="${userList}">
			<tr>
				<td>${user.id}</td>
				<td>${user.name}</td>
				<td>${user.address}</td>
				<td>${user.email}</td>
			</tr>
		</c:forEach>
	</table>
	<jsp:include page="/pages/include/page.jsp"></jsp:include>
</body>
</html>
