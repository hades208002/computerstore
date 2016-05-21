<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<HTML>
<HEAD>
<meta http-equiv="Content-Language" content="zh-cn">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="${pageContext.request.contextPath}/admin/css/Style.css"
	rel="stylesheet" type="text/css" />
<script language="javascript"
	src="${pageContext.request.contextPath}/admin/js/public.js"></script>

</HEAD>
<body>
	<br>
	<form id="Form1" name="Form1" action="${ pageContext.request.contextPath }/order" method="post">
		<input type="hidden" name="method" value="showOrderByWhere"/>
		<table cellSpacing="1" cellPadding="0" width="100%" align="center"
			bgColor="#f5fafe" border="0">
			<TBODY>
				<tr>
					<td class="ta_01" align="center" bgColor="#afd1f3"><strong>Search Condition</strong></td>
				</tr>
				<tr>
					<td>
						<table cellpadding="0" cellspacing="0" border="0" width="100%">
							<tr>
								<td height="22" align="center" bgColor="#f5fafe" class="ta_01">
									Order Number:</td>
								<td class="ta_01" bgColor="#ffffff">
									<input type="text" name="oid" size="15" value="${ oid }" id="Form1_userName" class="bg" />
								</td>
								<td height="22" align="center" bgColor="#f5fafe" class="ta_01">
									Receiver：</td>
								<td class="ta_01" bgColor="#ffffff">
									<input type="text" name="receiverName" size="15" value="${ receiverName }" id="Form1_userName" class="bg" />
								</td>
							</tr>

							<tr>
								<td width="100" height="22" align="center" bgColor="#f5fafe"
									class="ta_01"></td>
								<td class="ta_01" bgColor="#ffffff"><font face="宋体"
									color="red"> &nbsp;</font>
								</td>
								<td align="right" bgColor="#ffffff" class="ta_01"><br>
								<br>
								</td>
								<td align="right" bgColor="#ffffff" class="ta_01">
									<button type="submit" id="search" name="search" value="&#26597;&#35810;" class="button_view">
										Search
									</button> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
									<input type="reset" name="reset" value="Reset" class="button_view" />
								</td>
							</tr>
						</table></td>

				</tr>
				<tr>
					<td class="ta_01" align="center" bgColor="#afd1f3"><strong>Order List</strong></TD>
				</tr>
				<tr>
					<td class="ta_01" align="right"></td>
				</tr>
				<tr>
					<td class="ta_01" align="center" bgColor="#f5fafe">
						<table cellspacing="0" cellpadding="1" rules="all"
							bordercolor="gray" border="1" id="DataGrid1"
							style="BORDER-RIGHT: gray 1px solid; BORDER-TOP: gray 1px solid; BORDER-LEFT: gray 1px solid; WIDTH: 100%; WORD-BREAK: break-all; BORDER-BOTTOM: gray 1px solid; BORDER-COLLAPSE: collapse; BACKGROUND-COLOR: #f5fafe; WORD-WRAP: break-word">
							<tr
								style="FONT-WEIGHT: bold; FONT-SIZE: 12pt; HEIGHT: 25px; BACKGROUND-COLOR: #afd1f3">

								<td align="center" width="20%">Order Number</td>
								<td align="center" width="10%">Receiver</td>
								<td align="center" width="15%">Address</td>
								<td align="center" width="10%">Contact</td>
								<td width="11%" align="center">Total</td>
								<td width="8%" align="center">Client</td>
								<td width="10%" align="center">Status</td>
								<td width="7%" align="center">check</td>
								<td width="7%" align="center">delete</td>
							</tr>
							<c:forEach var="order" items="${ oList }" varStatus="vs">
								<tr onmouseover="this.style.backgroundColor = 'white'"
									onmouseout="this.style.backgroundColor = '#F5FAFE';">
									<td style="CURSOR: hand; HEIGHT: 22px" align="center" width="20%">
										${ order.oid }
									</td>
									<td style="CURSOR: hand; HEIGHT: 22px" align="center" width="10%">
										${ order.receiverName }
									</td>
									<td style="CURSOR: hand; HEIGHT: 22px" align="center" width="15%">
										${ order.receiverAddress }
									</td>
									<td style="CURSOR: hand; HEIGHT: 22px" align="center" width="10%">
										${ order.receiverPhone }
									</td>
									<td style="CURSOR: hand; HEIGHT: 22px" align="center">
										${ order.money }
									</td>
									<td width="8%" align="center">${ order.user.username }</td>
									<c:if test="${ order.paystate eq 0 }">
										<td width="10%" align="center">not paid</td>
									</c:if>
									<c:if test="${ order.paystate eq 1 }">
										<td width="10%" align="center">already paid</td>
									</c:if>
									<td align="center" style="HEIGHT: 22px">
										<a href="${pageContext.request.contextPath}/order?method=showOrderById&oid=${ order.oid }">
											<img src="${pageContext.request.contextPath}/admin/images/button_view.gif" border="0" style="CURSOR: hand"> 
										</a>
									</td>
									<td align="center" style="HEIGHT: 22px">
										<a href="#">
											<img src="${pageContext.request.contextPath}/admin/images/i_del.gif" width="16" height="16" border="0" style="CURSOR: hand">
										</a>
									</td>
								</tr>
							</c:forEach>
						</table></td>
				</tr>
			</TBODY>
		</table>
	</form>
</body>
</HTML>

