<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Menu</title>
<link href="${pageContext.request.contextPath}/admin/css/left.css" rel="stylesheet" type="text/css">
</head>
<body>
<table width="100" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td height="12"></td>
  </tr>
</table>
<table width="100%" border="0">
  <tr>
    <td>
<div class="dtree">

	<a href="javascript: d.openAll();">unfold all</a> | <a href="javascript: d.closeAll();">fold all</a>
	<link rel="StyleSheet" href="${pageContext.request.contextPath}/admin/css/dtree.css" type="text/css" />
	<script type="text/javascript" src="${pageContext.request.contextPath}/admin/js/dtree.js"></script>
	<script type="text/javascript">
		<!--
		d = new dTree('d');
		d.add(0,-1,'System menu tree');
		d.add(1,0,'Products','/computerstore/admin/login/welcome.jsp','','mainFrame');
		d.add(2,0,'Orders','/computerstore/admin/login/welcome.jsp','','mainFrame');
		
		
		//子目录添加
		d.add(11,1,'view products','${pageContext.request.contextPath }/product?method=showProductByPage1','','mainFrame');
		
		d.add(12,1,'sales list','${pageContext.request.contextPath }/admin/products/download.jsp','','mainFrame')
		
		d.add(21,2,'view orders','${pageContext.request.contextPath }/order?method=showOrder','','mainFrame');
	
		
		document.write(d);
		//-->
	</script>
</div>	</td>
  </tr>
</table>
</body>
</html>
