<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="p" uri="http://www.itcast.cn./tag" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitonal//EN">
<html>
<head>
<title> ComputerStore </title>
<link rel="stylesheet" href="css/main.css" type="text/css"/>
<script type="text/javascript">
//该方法用于提交表单 this way is used to hang on a sheet
function submitOrder(){
// get the object of DOM
var orderForm=document.getElementById("orderForm");
orderForm.submit();
//this way euqal to click on submit button on a page to hang on something to
//the web but not show it 吧表单数据提交到web服务器

}
</script>
</head>
<body class="main">
<!-- we should judge if this user had login or we should jump to the login page -->
<c:if test="${empty user} ">
<c: redirect url="url=/login,jsp" context="${pageContext.request.contextPath}"></c:>
</c:if>
<jsp:include page="head.jsp"/>
<jsp:include page="menu_search.jsp"/>
<div id="divpagecontext"=100%" border="0" cellspacing="0">
<tr>
		<td><div style="text-align:right; margin:5px 10px 5px 0px">
		<a href="index.jsp">Home</a>&nbsp;&nbsp;&nbsp;&nbsp;&gt;&nbsp;&nbsp;&nbsp;<a 
		href="cart.jsp">&nbsp;Cart</a>&nbsp;&nbsp;&nbsp;&nbsp;&gt;&nbsp;&nbsp;&nbsp;&nbsp;
		Order</div>
		<form id="orderForm" action="${pageContext.request.contextPath}/order" method="post">
					
		<input type="hidden" name="method" value="addOrder"/> 
			<table cellspacing="0" class="infocontent">
			<tr> 
				<td><table width="100%" border="0" cellspacing="0" width="635" height="38"/>
				
										<tr>
											<td><img src="images/buy2.gif" width="635" height="38" />
												<p>Welcome： Mr./Ms.<font color="red">${ user.username }</font> to the settlement center!</p></td>
										</tr>
										<tr>
											<td><table cellspacing="1" class="carttable">
													<tr>
														<td width="10%">Sequence number</td>
														<td width="40%">Product Name</td>
														<td width="10%">Price</td>
														<td width="10%">Category</td>
														<td width="10%">Amount</td>
														<td width="10%">Count</td>

													</tr>
												</table>

												<table width="100%" border="0" cellspacing="0">
													<c:forEach var="map" items="${ cart.map }" varStatus="vs">
														<tr>
															<td width="10%">${ vs.count }</td>
															<td width="40%">${ map.value.p.pname }</td>
															<td width="10%">${ map.value.p.price }</td>
															<td width="10%">${ map.value.p.category }</td>
															<td width="10%"><input name="text" type="text"
																value="${ map.value.buyNum }" style="width:20px" readonly="readonly" /></td>
															<td width="10%">${ map.value.buyTotal }</td>
														</tr>
													</c:forEach>
												</table>


												<table cellspacing="1" class="carttable">
													<tr>
														<td style="text-align:right; padding-right:40px;"><font
															style="color:#FF0000">Total：&nbsp;&nbsp;${ cart.total }</font></td>
													</tr>
												</table>

												<p>
													Delivery Address：
													<input name="receiverAddress" type="text" value="xxx" style="width:350px" />&nbsp;&nbsp;&nbsp;&nbsp;<a href="#"></a>
													<br />
													 Receiver：&nbsp;&nbsp;&nbsp;&nbsp;
													<input name="receiverName" type="text" value="xxx" style="width:150px" />&nbsp;&nbsp;&nbsp;&nbsp;<a href="#"></a>
													<br /> 
													Contact：
													<input name="receiverPhone" type="text" value="xxx" style="width:150px" />&nbsp;&nbsp;&nbsp;&nbsp;

												</p>
												<hr />
												<p style="text-align:right">
													<img src="images/gif53_029.gif" width="204" height="51" border="0" onclick="submitOrder()"/>
												</p>
											</td>
										</tr>
									</table></td>
							</tr>
						</table>
					</form></td>
			</tr>
		</table>
	</div>


	<jsp:include page="foot.jsp" />


</body>
</html>


