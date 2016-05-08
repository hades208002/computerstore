
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>ComputerStore</title>

<link rel="stylesheet" href="css/main.css" type="text/css" />
<script type="text/javascript">
	// 当用户点击登陆图片按钮时，通过此方法提交表单
	function login(){
		// 获取表单DOM对象
		var loginForm = document.getElementById("userLogin");
		// 提交表单
		loginForm.submit();
	}
</script>
</head>

<body class="main">
<!-- 先判断session中的user是否存在，如果存在，直接跳转到主页，如果不存在，停留在该页面 -->
<c:if test="${ not empty sessionScope.user }">
	<c:redirect url="/index.jsp" context="${ pageContext.request.contextPath }"></c:redirect>
</c:if>
	<jsp:include page="head.jsp" />
	<jsp:include page="menu_search.jsp" />

	<div id="divcontent">
		<form action="${ pageContext.request.contextPath }/user" method="post" id="userLogin">
			<!-- 隐藏域，设置方法名 -->
			<input type="hidden" name="method" value="login"/> 
			<table width="900px" border="0" cellspacing="0">
				<tr>
					<td style="padding:30px"><div style="height:470px">
							<b>&nbsp;&nbsp;Home&nbsp;&raquo;&nbsp;Login</b>
							<div>
								<table width="85%" border="0" cellspacing="0">
									<tr>
										<td>
											<div id="logindiv">
												<table width="100%" border="0" cellspacing="0">
													<tr>
														<td style="text-align:center; padding-top:20px"><img
															src="images/logintitle.gif" width="150" height="30" />
														</td>
													</tr>
													<tr>
														<td style="text-align:center;padding-top:20px;"><font
															color="#ff0000">${msg}</font>
														</td>
													</tr>
													<tr>
														<td style="text-align:center">
															<table width="80%" border="0" cellspacing="0"
																style="margin-top:15px ;margin-left:auto; margin-right:auto">
																<tr>
																	<td style="text-align:right; padding-top:5px; width:25%">User Name ：</td>
																	<td style="text-align:left">
																		<input name="username" type="text" class="textinput" value="${ cookie.onlyname.value }"/>
																	</td>
																</tr>
																<tr>
																	<td style="text-align:right; padding-top:5px">&nbsp;&nbsp;&nbsp;&nbsp;Password：</td>
																	<td style="text-align:left">
																		<input name="password" type="password" class="textinput" />
																	</td>
																</tr>
																<tr>
																	<td colspan="2" style="text-align:center">
																		<input type="checkbox" name="remUsername" value="remUsername" />
																		remember Name&nbsp;&nbsp; 
																		<input type="checkbox"name="autoLogin" value="autoLogin" /> 
																		automatic Login
																	</td>
																</tr>
																<tr>
																	<td colspan="2" style="padding-top:10px; text-align:center">
																		<input name="image" type="image" src="images/loginbutton.gif" onclick="login()" width="83" height="22" />
																	</td>
																</tr>

																<tr>
																	<td colspan="2" style="padding-top:10px">
																		<img src="images/loginline.gif" width="241" height="10" />
																	</td>
																</tr>
																<tr>
																	<td colspan="2"
																		style="padding-top:10px; text-align:center"><a
																		href="register.jsp"><img name="image"
																			src="images/signupbutton.gif" width="135" height="33" />
																	</a></td>
																</tr>
															</table>
														</td>
													</tr>
												</table>
											</div></td>
										<td style="text-align:left; padding-top:30px; width:60%"><h1>Haven't registered ？</h1>
											<p>Register now, enjoy more preferential prices!!!</p>
											<p>A large variety of options on your click! Register now and get decent discounts!!!</p>
											<p>Super hot community!!! 100% guaranteed transaction safety!!!</p>
											<p style="text-align:right">
												<a href="register.jsp"><img
													src="images/signupbutton.gif" width="135" height="33" /> </a>
											</p>
										</td>
									</tr>
								</table>
							</div>
						</div>
					</td>
				</tr>
			</table>
		</form>
	</div>



	<jsp:include page="foot.jsp" />


</body>
</html>
