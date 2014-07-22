<%@ page language="java" import="java.util.*" pageEncoding="GB18030"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<ul>
	<li <c:if test="${page.current<=page.pageRow-1}">class="disabled"</c:if>><a href="javascript:void(0)">&laquo;</a></li>
	<c:forEach begin="${page.first}" end="${page.last}" var="idx">
		<li <c:if test="${page.current==idx}">class="active"</c:if>><a href="javascript:location.href='${page.pageUrl}?pageCurrent=${idx}&pageFirst=${page.first}&pageLast=${page.last}&pageRow=${page.pageRow}&pageRecord=${page.pageRecord}'"><c:out value="${idx}"/></a></li>
	</c:forEach>
    <li <c:if test="${page.current>=page.totalPage-page.pageRow}">class="disabled"</c:if>><a href="javascript:void(0)">&raquo;</a></li>
</ul>