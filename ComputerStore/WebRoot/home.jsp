<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Language" content="zh-cn">
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <style>
		body
		{
			SCROLLBAR-ARROW-COLOR: #ffffff;  SCROLLBAR-BASE-COLOR: #dee3f7;
		}
    </style>
  </head>
<!-- judge if admin has logged in ,if not ,redirect to login page-->
<c:if test="${ user.username != 'admin' || user.role != 'admin' }">
	<c:redirect url="/login.jsp" context="${ pageContext.request.contextPath }"></c:redirect>
</c:if>
<frameset rows="103,*,43" frameborder="no" border="0" framespacing="0">
  <frame src="${pageContext.request.contextPath}/admin/login/top.jsp" name="topFrame" scrolling="NO" noresize>
  <frameset cols="159,*" frameborder="no" border="0" framespacing="0">
		<frame src="${pageContext.request.contextPath}/admin/login/left.jsp" name="leftFrame" noresize scrolling="YES">
		<frame src="${pageContext.request.contextPath}/admin/login/welcome.jsp" name="mainFrame">
  </frameset>
  <frame src="${pageContext.request.contextPath}/admin/login/bottom.jsp" name="bottomFrame" scrolling="NO"  noresize>
</frameset>
</html>
