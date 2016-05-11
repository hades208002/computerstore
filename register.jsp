<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>

<title>ComputerStore register page</title>
<%--import css --%>
<link rel="stylesheet" href="css/main.css" type="text/css" />
<script type="text/javascript">
	function changeImage() {
		
		document.getElementById("img").src = "${pageContext.request.contextPath}/checkcode?time="
				+ new Date().getTime();
	}
</script>
</head>


<body class="main">
	<%@include file="head.jsp"%>
	<%--导入头 --%>
	<%@include file="menu_search.jsp"%>

	<div id="divcontent">
		<form action="${pageContext.request.contextPath}/user" method="post">
			<input type="hidden" name="method" value="register"/>
			<table width="850px" border="0" cellspacing="0">
				<tr>
					<td style="padding:30px">
						<h1>Register</h1>
						<font color="red">${ regMsg }</font>
						<table width="70%" border="0" cellspacing="2" class="upline">
							<tr>
								<td style="text-align:right; width:20%">E-mail ：</td>
								<td style="width:40%">
									<input type="text" class="textinput" name="email" />
								</td>
								<td><font color="#999999">Please insert valid email address</font></td>
							</tr>
							<tr>
								<td style="text-align:right">UserName：</td>
								<td>
									<input type="text" class="textinput" name="username" />
								</td>
								<td><font color="#999999">At least six digits</font></td>
							</tr>
							<tr>
								<td style="text-align:right">Password：</td>
								<td>
									<input type="password" class="textinput" name="password" />
								</td>
								<td><font color="#999999">At least six digits</font></td>
							</tr>
							<tr>
								<td style="text-align:right">Confirm ：</td>
								<td>
									<input type="password" class="textinput" name="repassword" />
								</td>
								<td><font color="#999999">Confirm password</font></td>
							</tr>
							<tr>
								<td style="text-align:right">Gender：</td>
								<td colspan="2">&nbsp;&nbsp;
									<input type="radio" name="gender" value="male" checked="checked" /> Male
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<input type="radio" name="gender" value="female" /> Female</td>
							</tr>
							<tr>
								<td style="text-align:right">Telephone：</td>
								<td colspan="2">
									<input type="text" class="textinput" style="width:350px" name="telephone" />
								</td>
							</tr>
							<tr>
								<td style="text-align:right">Self--------------Introduction：</td>
								<td colspan="2">
									<textarea class="textarea" name="introduce"></textarea>
								</td>
							</tr>

						</table>



						<h1>Verification</h1>
						<table width="80%" border="0" cellspacing="2" class="upline">
							<tr>
								<td style="text-align:right; width:20%">Verification code：</td>
								<td style="width:50%">
									<input type="text" class="textinput" />
								</td>
								<td>&nbsp;</td>
							</tr>
							<tr>
								<td style="text-align:right;width:20%;">&nbsp;</td>
								<td colspan="2" style="width:50%">
								<img src="${ pageContext.request.contextPath }/checkcode" width="180" height="30" class="textinput" style="height:30px;" id="img" />&nbsp;&nbsp;
									<a href="javascript:void(0);" onclick="changeImage()">Another one</a>
								</td>
							</tr>
						</table>



						<table width="70%" border="0" cellspacing="0">
							<tr>
								<td style="padding-top:20px; text-align:center"><input
									type="image" src="images/signup.gif" name="submit" border="0">
								</td>
							</tr>
						</table></td>
				</tr>
			</table>
		</form>
	</div>



	<div id="divfoot">
		<table width="100%" border="0" cellspacing="0">
			<tr>
				<td rowspan="2" style="width:10%"><img
					src="images/bottomlogo.gif" width="195" height="50"
					style="margin-left:175px" /></td>
				<td style="padding-top:5px; padding-left:50px"><a href="#"><font
						color="#747556"><b>CONTACT US</b> </font> </a></td>
			</tr>
			<tr>
				<td style="padding-left:50px"><font color="#CCCCCC"><b>COPYRIGHT
							2008 &copy; ComputerStore All Rights RESERVED.</b> </font></td>
			</tr>
		</table>
	</div>


</body>
</html>
