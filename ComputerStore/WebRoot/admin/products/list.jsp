<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<HTML>
<HEAD>
<meta http-equiv="Content-Language" content="zh-cn">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="${pageContext.request.contextPath}/admin/css/Style.css" rel="stylesheet" type="text/css" />
<script language="javascript" src="${pageContext.request.contextPath}/admin/js/public.js"></script>
<script type="text/javascript">
	function addProduct() {
		window.location.href = "${pageContext.request.contextPath}/admin/products/add.jsp";
	}
</script>
<style type="text/css">
	/*分页*/
.pagination {
	padding: 5px;
	width: 600px;
	margin: 5px;
	text-align: center;
}

.pagination ul {
	margin: 5;
	padding: 5;
	font-size: 12px;
}

.pagination li {
	list-style-type: none;
	display: inline;
	padding: 1px;
	margin: 1px;
}

.pagination a,.pagination a:visited {
	padding: 5px;
	border: 1px solid #9aafe5;
	text-decoration: none;
	color: #2e6ab1;
}

.pagination a:hover,.pagination a:active {
	border: 1px solid #2b66a5;
	color: #000;
	background-color: lightyellow;
}

.pagination li.currentpage {
	font-weight: bold;
	padding: 5px;
	border: 1px solid navy;
	background-color: #2e6ab1;
	color: #FFF;
}

.pagination li.disablepage {
	padding: 5px;
	border: 1px solid #929292;
	color: #929292;
}

.pagination li.nextpage {
	font-weight: bold;
	padding: 5px;
}
</style>
</HEAD>
<body>
	<br>
	<form id="Form1" name="Form1" action="${pageContext.request.contextPath}/product" method="post">
		<!-- 隐藏域 -->
		<input type="hidden" name="method" value="showProductByPage3"/>
		<table cellSpacing="1" cellPadding="0" width="100%" align="center" bgColor="#f5fafe" border="0">
			<TBODY>
				<tr>
					<td class="ta_01" align="center" bgColor="#afd1f3">
						<strong>Search Condition</strong>
					</td>
				</tr>
				<tr>
					<td>
						<table cellpadding="0" cellspacing="0" border="0" width="100%">
							<tr>
								<td height="22" align="center" bgColor="#f5fafe" class="ta_01">Product Number</td>
								<td class="ta_01" bgColor="#ffffff">
									<input type="text" name="pid" size="15" id="Form1_userName" class="bg" />
								</td>
								<td height="22" align="center" bgColor="#f5fafe" class="ta_01">Category：</td>
								<td class="ta_01" bgColor="#ffffff">
									<select name="category" id="category">
										<option value="" selected="selected">--choose category--</option>
										<option value="server">server</option>
										<option value="desktop">desktop</option>
										<option value="laptop">laptop</option>
									</select>
								</td>
							</tr>

							<tr>
								<td height="22" align="center" bgColor="#f5fafe" class="ta_01">Product Name：</td>
								<td class="ta_01" bgColor="#ffffff">
									<input type="text" name="pname" size="15" value="" id="pname" class="bg" />
								</td>
								<td height="22" align="center" bgColor="#f5fafe" class="ta_01">Price(€)：</td>
								<td class="ta_01" bgColor="#ffffff">
									<input type="text" name="minprice" size="10"/> 
									- 
									<input type="text" name="maxprice" size="10"/>
								</td>
							</tr>

							<tr>
								<td width="100" height="22" align="center" bgColor="#f5fafe" class="ta_01"></td>
								<td class="ta_01" bgColor="#ffffff"><font face="宋体" color="red"> &nbsp;</font>
								</td>
								<td align="right" bgColor="#ffffff" class="ta_01"><br><br></td>
								<td align="right" bgColor="#ffffff" class="ta_01">
									<button type="submit" id="search" name="search" value="Submit" class="button_view">
										Submit&nbsp;
									</button> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
									<input type="reset" name="reset" value="Reset" class="button_view" />
								</td>
							</tr>
						</table>
					</td>

				</tr>
				<tr>
					<td class="ta_01" align="center" bgColor="#afd1f3"><strong>Product List</strong>
					</TD>
				</tr>
				<tr>
				<!-- Add button！ -->
					<td class="ta_01" align="right">
						<button type="button" id="add" name="add" value="&#28155;&#21152;"
							class="button_add" onclick="addProduct()">Add
						</button>
					</td>
				</tr>
				<tr>
					<td class="ta_01" align="center" bgColor="#f5fafe">
						<font>${ap_msg }</font>
						<table cellspacing="0" cellpadding="1" rules="all"
							bordercolor="gray" border="1" id="DataGrid1"
							style="BORDER-RIGHT: gray 1px solid; BORDER-TOP: gray 1px solid; BORDER-LEFT: gray 1px solid; WIDTH: 100%; WORD-BREAK: break-all; BORDER-BOTTOM: gray 1px solid; BORDER-COLLAPSE: collapse; BACKGROUND-COLOR: #f5fafe; WORD-WRAP: break-word">
							<tr
								style="FONT-WEIGHT: bold; FONT-SIZE: 12pt; HEIGHT: 25px; BACKGROUND-COLOR: #afd1f3">
								<td align="center" width="11%">Product Number</td>
								<td align="center" width="20%">Product Name</td>
								<td align="center" width="15%">Price</td>
								<td align="center" width="15%">Number</td>
								<td width="15%" align="center">Category</td>
								<td width="12%" align="center">Edit</td>

								<td width="12%" align="center">Remove</td>
							</tr>

							<c:forEach var="p" items="${ page.pList }" varStatus="vs">
								<tr onmouseover="this.style.backgroundColor = 'white'"
									onmouseout="this.style.backgroundColor = '#F5FAFE';">
									
									<td style="CURSOR: hand; HEIGHT: 22px" align="center" >${vs.count }</td>
									<td style="CURSOR: hand; HEIGHT: 22px" align="center" >${p.pname }</td>
									<td style="CURSOR: hand; HEIGHT: 22px" align="center" >${p.price }</td>
									<td style="CURSOR: hand; HEIGHT: 22px" align="center" >${p.pnum }</td>
									<td style="CURSOR: hand; HEIGHT: 22px" align="center" >${p.category }</td>
									<td align="center" style="HEIGHT: 22px" >
										<a href="${pageContext.request.contextPath }/product?method=initUpdate&pid=${p.pid}">
											<img src="${pageContext.request.contextPath}/admin/images/i_edit.gif"
											border="0" style="CURSOR: hand"> 
										</a>
									</td>

									<td align="center" style="HEIGHT: 22px">
										<a href="${pageContext.request.contextPath }/product?method=delById&pid=${p.pid}" onclick="delOK()">
											<img src="${pageContext.request.contextPath}/admin/images/i_del.gif"
											width="16" height="16" border="0" style="CURSOR: hand">
										</a>
									</td>
								</tr>
							</c:forEach>
						</table>
						<div class="pagination">
							<ul>
								<!-- 显示分页 -->
								<c:if test="${ page.pageCode > 1 }">
									<li>
										<a href="${ page.url }&pc=${page.pageCode - 1}">Previous Page &lt;&lt;</a>
									</li>
								</c:if>
								<c:choose>
									<c:when test="${page.totalPage <= 4 }">
										<c:set var="begin" value="${ 1 }"></c:set>	
										<c:set var="end" value="${ page.totalPage }"></c:set>
									</c:when>
									<c:otherwise>
										<c:set var="begin" value="${ page.pageCode - 2 }"></c:set>
										<c:set var="end" value="${ page.pageCode + 1 }"></c:set>
										<c:if test="${ begin < 1 }">
											<c:set var="begin" value="${ 1 }"></c:set>
											<c:set var="end" value="${ 4 }"></c:set>
										</c:if>
										<c:if test="${ end > page.totalPage }">
											<c:set var="end" value="${ page.totalPage }"></c:set>
											<c:set var="begin" value="${ page.totalPage - 3 }"></c:set>
										</c:if>
									</c:otherwise>
								</c:choose>
								<c:forEach var="i" begin="${ begin }" end="${ end }" step="1">
									<c:if test="${ page.pageCode == i }">
										<li class="currentpage">${ i }</li>
									</c:if>
									<c:if test="${ page.pageCode != i }">
										<li><a href="${ page.url }&pc=${ i }">${ i }</a>
									</c:if>
								</c:forEach>
								<c:if test="${ pageCode < page.totalPage }">
									<li>
										<a href="${ page.url }&pc=${page.pageCode + 1}">Next Page&lt;&lt;</a>
									</li>
								</c:if>
							</ul>
						</div>
					</td>
				</tr>
			</TBODY>
		</table>
		<font color="red">${p_msg }</font>
		<font color="red">${del_msg }</font>
	</form>
</body>
</HTML>

