<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>ComputerStore</title>
<link rel="stylesheet" href="css/main.css" type="text/css" />
</head>

<body class="main">
	<jsp:include page="head.jsp" />

	<jsp:include page="menu_search.jsp" />

	<div id="divpagecontent">
		<table width="100%" border="0" cellspacing="0">
			<tr>
				<td width="25%">
					<table width="100%" border="0" cellspacing="0"
						style="margin-top:30px">
						<tr>
							<td class="listtitle">My Account</td>
						</tr>
						<tr>
							<td class="listtd"><img src="images/miniicon.gif" width="9" height="6" />&nbsp;&nbsp;&nbsp;&nbsp; 
								<a href="${ pageContext.request.contextPath }/modifyUserInfo.jsp">Modify profile</a>
							</td>
						</tr>

						<tr>
							<td class="listtd"><img src="images/miniicon.gif" width="9" height="6" />&nbsp;&nbsp;&nbsp;&nbsp; 
								<a href="${ pageContext.request.contextPath }/user?method=orderList&uid=${ user.uid }">Order list</a>
							</td>
						</tr>
						<tr>
							<td class="listtd"><img src="images/miniicon.gif" width="9" height="6" />&nbsp;&nbsp;&nbsp;&nbsp; 
								<a href="${ pageContext.request.contextPath }/user?method=quitUser&uid=${ user.uid }">Exit</a>
							</td>
						</tr>
					</table>
				</td>
				<td>
					<div style="text-align:right; margin:5px 10px 5px 0px">
						<a href="index.jsp">Home</a>&nbsp;&nbsp;&nbsp;&nbsp;&gt;&nbsp;&nbsp;&nbsp;<a
							href="myAccount.jsp">&nbsp;My Account</a>&nbsp;&nbsp;&nbsp;&nbsp;&gt;&nbsp;&nbsp;&nbsp;&nbsp;modify
					</div>
					<table cellspacing="0" class="infocontent">
						<tr>
							<td>
								<font color="red">${ modMsg }</font>
								<form action="${ pageContext.request.contextPath }/user" method="post">
									<input type="hidden" name="method" value="modifyUser"/>
									<input type="hidden" name="uid" value="${user.uid}">
									<table width="100%" border="0" cellspacing="2" class="upline">
										<tr>
											<td style="text-align:right; width:20%">E-mail：</td>
											<td style="width:40%; padding-left:20px">${user.email}</td>
											<td>&nbsp;</td>


										</tr>
										<tr>
											<td style="text-align:right">User Name：</td>
											<td style="padding-left:20px">${user.username}</td>
											<td>&nbsp;</td>
										</tr>
										<tr>
											<td style="text-align:right">Password：</td>
											<td><input type="password" name="password" class="textinput" />
											</td>
											<td><font color="#999999">at least 6 digits, case sensitive</font>
											</td>
										</tr>
										<tr>
											<td style="text-align:right">Repeat password：</td>
											<td><input type="password" class="textinput" />
											</td>
											<td>&nbsp;</td>
										</tr>
										<tr>
											<td style="text-align:right">Gender：</td>
											<td colspan="2">&nbsp;&nbsp;
												<input type="radio" name="gender" value="male" <c:if test="${ user.gender eq 'male' }">checked</c:if> /> male
												&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
												<input type="radio" name="gender" value="female" <c:if test="${ user.gender eq 'female' }">checked</c:if> /> female</td>
										</tr>
										<tr>
											<td style="text-align:right">Contact：</td>
											<td colspan="2">
												<input name="telephone" type="text" value="${ user.telephone }" class="textinput" />
											</td>
										</tr>

										<tr>
											<td style="text-align:right">&nbsp;</td>
											<td>&nbsp;</td>
											<td>&nbsp;</td>
										</tr>
									</table>
									<p style="text-align:center">
										<input type="image" src="images/botton_gif_025.gif" border="0">
									</p>
									<p style="text-align:center">&nbsp;</p>
								</form>
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
	</div>
	<jsp:include page="foot.jsp" />
</body>
</html>
