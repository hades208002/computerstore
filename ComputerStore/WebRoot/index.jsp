<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>bookStore首页</title>
<%--导入css --%>
<link rel="stylesheet" href="css/main.css" type="text/css" />
</head>

<body class="main">

	<%@include file="head.jsp"%>
	<%@include file="menu_search.jsp" %>
	<div id="divad">
		<img src="ad/index_ad.jpg" />
	</div>

	<div id="divcontent">
		<table width="900px" border="0" cellspacing="0">
			<tr>
				<td width="497"><img src="images/billboard.gif" width="497"
					height="38" />
					<table cellspacing="0" class="ctl">
						<tr>
							<td>&middot;<a href="news.html" style="color:#000000">全场笔记本3折起，
									先看再买不后悔,任何商品免费送货</a></td>
						</tr>
						<tr>
							<td>&middot;<a href="news.html" style="color:#000000">新闻2</a></td>
						</tr>
						<tr>
							<td>&middot;<a href="news.html" style="color:#000000">新闻3</a></td>
						</tr>
						<tr>
							<td>&middot;<a href="news.html" style="color:#000000">新闻4</a></td>
						</tr>
						<tr>
							<td>&middot;<a href="news.html" style="color:#000000">新闻5</a></td>
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
							<td style="width:50; text-align:center"><a href="info.html"><img
									src="bookcover/Laptop005.jpg" width="102" height="130"
									border="0" /> </a><br /> <a href="info.html">Laptop005<br />
									品牌:外星1</a></td>
							<td style="width:50; text-align:center"><a href="info.html"><img
									src="bookcover/Desktop010.jpg" width="102" height="130" border="0" />
							</a><br /> <a href="info.html">Desktop010 <br /> 品牌：外星2</a>
							</td>
						</tr>
					</table></td>
			</tr>
		</table>
	</div>



	<jsp:include page="foot.jsp"/>


</body>
</html>
