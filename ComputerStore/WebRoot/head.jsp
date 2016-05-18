<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div id="divhead" width=100%>
	<table cellspacing="0" class="headtable">
		<tr>
			<td><a href="index.jsp"><img src="images/logo.png"
					width="95" height="30" border="0" /> </a></td>
			<td style="text-align:right"><img src="images/cart.gif"
				    width="26" height="23" style="margin-bottom:-4px" />&nbsp;
				<a href="cart.jsp">ShoppingCart</a> | <a href="#">Help</a> | 
				<c:if test="${ empty user }">
					<a href="login.jsp">My_Account</a>
				</c:if>
				<c:if test="${ not empty user }">
					<a href="myAccount.jsp">My_Account</a>
				</c:if>
				| <a href="register.jsp">Register</a></td>
		</tr>
	</table>
</div>
