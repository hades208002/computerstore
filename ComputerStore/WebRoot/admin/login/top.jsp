<%@ page language="java" pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Language" content="zh-cn">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<style type="text/css">
BODY {
	MARGIN: 0px;
	BACKGROUND-COLOR: #ffffff
}

BODY {
	FONT-SIZE: 12px;
	COLOR: #000000
}

TD {
	FONT-SIZE: 12px;
	COLOR: #000000
}

TH {
	FONT-SIZE: 12px;
	COLOR: #000000
}
</style>
<link href="${pageContext.request.contextPath}/admin/css/Style.css"
	rel="stylesheet" type="text/css">

<script type="text/javascript">
	function exitSys() {
		window.confirm("Confirm to exit the system?");
	}
</script>
</HEAD>
<body>
	<table width="100%" height="70%" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td><img width="100%"
				src="${pageContext.request.contextPath}/admin/images/top_01.jpg">
			</td>

			<td width="100%"
				background="${pageContext.request.contextPath}/admin/images/top_11.png">
			</td>
		</tr>
	</table>
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td height="30" valign="bottom"
				background="${pageContext.request.contextPath}/admin/images/mis_01.jpg">
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td width="85%" align="left">
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <font color="#000000">
								<script language="JavaScript">
								<!--
									tmpDate = new Date();
									date = tmpDate.getDate();
									month = tmpDate.getMonth() + 1;
									year = tmpDate.getFullYear();
									document.write(year);
									document.write("[Year]");
									document.write(month);
									document.write("[Month]");
									document.write(date);
									document.write("[Date]");

									myArray = new Array(6);
									myArray[0] = "Sunday"
									myArray[1] = "Monday"
									myArray[2] = "Tuesday"
									myArray[3] = "Wednesday"
									myArray[4] = "Thursday"
									myArray[5] = "Friday"
									myArray[6] = "Saturday"
									weekday = tmpDate.getDay();
									if (weekday == 0 | weekday == 6) {
										document.write(myArray[weekday])
									} else {
										document.write(myArray[weekday])
									};
								// -->
								</script> </font>
						</td>
						<td width="15%">
							<table width="100%" border="0" cellspacing="0" cellpadding="0">
								<tr>
									<td width="16" background="${pageContext.request.contextPath}/admin/images/mis_05b.jpg">
										<img src="${pageContext.request.contextPath}/admin/images/mis_05a.jpg" width="6" height="18">
									</td>
									<td width="155" valign="bottom" background="${pageContext.request.contextPath}/admin/images/mis_05b.jpg">
										<font color="blue">
											<a href="${ pageContext.request.contextPath}/user?method=quitUser" onclick="exitSys()">Exit</a> 
										</font></td>
									<td width="10" align="right" background="${pageContext.request.contextPath}/admin/images/mis_05b.jpg">
										<img src="${pageContext.request.contextPath}/admin/images/mis_05c.jpg" width="6" height="18">
									</td>
								</tr>
							</table>
						</td>
						<td align="right" width="5%"></td>
					</tr>
				</table>
			</td>
		</tr>
	</table>
</body>
</HTML>
