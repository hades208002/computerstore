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

				<td><div style="text-align:right; margin:5px 10px 5px 0px">
						<a href="index.jsp">Home</a>&nbsp;&nbsp;&nbsp;&nbsp;&gt;&nbsp;&nbsp;&nbsp;Order detail
					</div>



					<table cellspacing="0" class="infocontent">
						<tr>
							<td>
								<table width="100%" border="0" cellspacing="0">
									<tr>
										<td>
											<p><font color="red">${ payMsg }</font></p>
											<p>OrderNumber:${ order.oid }</p>
										</td>
									</tr>
									<tr>
										<td>
											<table cellspacing="1" class="carttable">
												<tr>
													<td width="10%">SequenceNumber</td>
													<td width="40%">Produc tName</td>
													<td width="10%">价Price</td>
													<td width="10%">Amount</td>
													<td width="10%">Count</td>

												</tr>
											</table>
											<c:forEach var="oList" items="${ order.list }" varStatus="vs">
											<table width="100%" border="0" cellspacing="0">
												<tr>
													<td width="10%">${ vs.count }</td>
													<td width="40%">${ oList.product.pname }</td>
													<td width="10%">${ oList.product.price }</td>
													<td width="10%">${ oList.buyCount }</td>
													<td width="10%">${ oList.subtotal }</td>

												</tr>
											</table>
											</c:forEach>
											<table cellspacing="1" class="carttable">
												<tr>
													<td style="text-align:right; padding-right:40px;"><font
														style="color:#FF0000">Total：&nbsp;&nbsp;${ order.money }</font></td>
												</tr>
											</table>

											<p>
												Delivery Address：${ order.receiverAddress }&nbsp;&nbsp;&nbsp;&nbsp;<br />
												Receiver：&nbsp;&nbsp;&nbsp;&nbsp;${ order.receiverName }&nbsp;&nbsp;&nbsp;&nbsp;<br />
												Contact：${ order.receiverPhone }&nbsp;&nbsp;&nbsp;&nbsp;

											</p>
											<hr>
											<p style="text-align:right">
												<a href="${ pageContext.request.contextPath }/order?method=initPay&oid=${ order.oid }&money=${order.money}">
													<img src="images/gif53_029.gif" width="204" height="51" border="0" /> 
												</a>
											</p>
										</td>
									</tr>
								</table>
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
