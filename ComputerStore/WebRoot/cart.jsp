<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>ComputerStore</title>
<link rel="stylesheet" href="css/main.css" type="text/css" />
<script type="text/javascript">
	function changeBuyNum(pid,num){
		window.location.href="${ pageContext.request.contextPath}/cart?method=newBuyNum&bBum="+num+"&pid="+pid;		
	}
</script>

</head>

<body class="main">

	<jsp:include page="head.jsp" />

	<jsp:include page="menu_search.jsp" />


	<div id="divpagecontent">
		<table width="100%" border="0" cellspacing="0">
			<tr>

				<td><div style="text-align:right; margin:5px 10px 5px 0px">
						<a href="index.jsp">Home</a>&nbsp;&nbsp;&nbsp;&nbsp;&gt;&nbsp;&nbsp;&nbsp;&nbsp;Cart
					</div>

					<table cellspacing="0" class="infocontent">
						<tr>
							<td><img src="images/page_ad.jpg" width="666" height="89" />
								<table width="100%" border="0" cellspacing="0">
									<tr>
										<td><img src="images/buy1.gif" width="635" height="38" />
										</td>
									</tr>
									<tr>
										<td>
											<table cellspacing="1" class="carttable">
												<tr>
													<td width="10%">Sequence Number</td>
													<td width="30%">Product Name</td>
													<td width="10%">Price</td>
													<td width="20%">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Amount</td>
													<td width="10%">Storage</td>
													<td width="10%">Count</td>
													<td width="10%">Cancel</td>
												</tr>
											</table> 
											<!-- Traversal cart, display the shopping items -->
											<c:forEach var="map" items="${ cart.map }" varStatus="vs">
												<table width="100%" border="0" cellspacing="0">
													<tr>
														<td width="10%">${ vs.count }</td>
														<td width="30%">${ map.value.p.pname }</td>
														<td width="10%">${ map.value.p.price }</td>
														<td width="20%">
															<input type="button" value='-' style="width:20px" onclick="changeBuyNum('${ map.value.p.pid }','${ map.value.buyNum - 1 }')">
															<input name="text" type="text"  value="${ map.value.buyNum }" style="width:40px;text-align:center" /> 
															<!-- on click event, increase buy amount by 1一 -->
															<!-- parameter means how much the amount should change to  -->
															<input type="button" value='+' style="width:20px" onclick="changeBuyNum('${ map.value.p.pid }','${ map.value.buyNum + 1 }')">
														</td>
														<td width="10%">${ map.value.p.pnum }</td>
														<td width="10%">${ map.value.buyTotal }</td>
														<td width="10%"><a href="${ pageContext.request.contextPath }/cart?method=removeItemFromCart&pid=${ map.value.p.pid}" style="color:#FF0000; font-weight:bold">X</a></td>
													</tr>
												</table>
											</c:forEach>


											<table cellspacing="1" class="carttable">
												<tr>
													<td style="text-align:right; padding-right:40px;"><font
														style="color:#FF6600; font-weight:bold">Total：&nbsp;&nbsp;${ cart.total } €</font>
													</td>
												</tr>
											</table>
											<div style="text-align:right; margin-top:10px">
												<a href="${pageContext.request.contextPath}/product?method=showProductByPage2">
													<img src="images/gwc_jx.gif" border="0" /> 
												</a>
												&nbsp;&nbsp;&nbsp;&nbsp;
												<a href="${pageContext.request.contextPath}/order.jsp">
													<!-- pay button -->
													<img src="images/gwc_buy.gif" border="0"/> 
												</a>
											</div>
										</td>
									</tr>
								</table>
							</td>
						</tr>
					</table></td>
			</tr>
		</table>
	</div>



	<jsp:include page="foot.jsp" />


</body>
</html>