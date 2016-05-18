<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>ComputerStore home</title>
<%--import css --%>
<link rel="stylesheet" href="css/main.css" type="text/css" />
</head>

<body class="main">

	<%@include file="head.jsp"%>
	<%@include file="menu_search.jsp" %>
	<div id="divad">
		<img src="images/index_ad.jpg" />
	</div>

	<div id="divcontent">
		<table width="900px" border="0" cellspacing="0">
			<tr>
				<td width="497"><img src="images/billboard.gif" width="497"
					height="38" />
					<table cellspacing="0" class="ctl">
						<tr>
							<td>&middot;<h1>News 1:<a href="${pageContext.request.contextPath}/product?method=showProductByPage2&category=Laptop" style="color:#FF0000">Laptop sales on fire!</a></td>
						</tr>
						<tr>
							<td>&middot;<h1>News 2:<a href="${pageContext.request.contextPath}/product?method=showProductByPage2&category=Desktop" style="color:#FF0000">Desktop sales on fire!</a></td>
						</tr>
						<tr>
							<td>&middot;<h1>News 3:<a href="${pageContext.request.contextPath}/product?method=showProductByPage2&category=Server" style="color:#FF0000">Server sales on fire!</a></td>
						</tr>
						<tr>
							<td>&middot;<h1>News 4:</h1><a style="color:#00FF00">New products </a>are coming!</td>
						</tr>
						<tr>
							<td>&middot;<h1>News 4:</h1><a style="color:#0000FF">New technology </a>is on the way!</a></td>
						</tr>
					</table></td>
				<td style="padding:5px 15px 10px 40px"><table width="100%"
						border="0" cellspacing="0">
						<tr>
							<td><img src="images/hottitle.gif" width="126" height="29" />
							</td>
						</tr>
					</table>
					<table width="100%" border="0" cellspacing="0">
						<tr>
							<td style="width:50; text-align:center"><a href="${ pageContext.request.contextPath }/product?method=showProduct&pid=b7e45585-45a2-4560-9c4e-b466b32e1c4f"><img
									src="computercover/MacBookPro.jpg" width="102" height="130"
									border="0" /> </a><br /> <a href="${ pageContext.request.contextPath }/product?method=showProduct&pid=b7e45585-45a2-4560-9c4e-b466b32e1c4f">Macbook Pro<br />
									Brand:Apple</a></td>
							<td style="width:50; text-align:center"><a href="${ pageContext.request.contextPath }/product?method=showProduct&pid=549f9d19-6280-4cc8-bafb-15d734220273"><img
									src="computercover/DellInspron.jpg" width="102" height="130" border="0" />
							</a><br /> <a href="${ pageContext.request.contextPath }/product?method=showProduct&pid=549f9d19-6280-4cc8-bafb-15d734220273">Dell Inspron <br /> Brand:Dell</a>
							</td>
						</tr>
					</table></td>
			</tr>
		</table>
	</div>



	<jsp:include page="foot.jsp"/>


</body>
</html>
