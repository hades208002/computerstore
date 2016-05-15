<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<HTML>
<HEAD>
<meta http-equiv="Content-Language" content="zh-cn">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<LINK href="${pageContext.request.contextPath}/admin/css/Style.css"
	type="text/css" rel="stylesheet">


</HEAD>

<body>
	<!-- enctype="multipart/form-data"用于上传文件 -->
	<form id="userAction_save_do" name="Form1" action="${pageContext.request.contextPath }/addProduct" method="post" enctype="multipart/form-data">
		&nbsp;
		<!-- 说明调用那个方法 -->
		<!-- <input type="hidden" name="method" value="addProduct"/> -->
		<table cellSpacing="1" cellPadding="5" width="100%" align="center"
			bgColor="#eeeeee" style="border: 1px solid #8ba7e3" border="0">
			<tr>
				<td class="ta_01" align="center" bgColor="#afd1f3" colSpan="4"
					height="26"><strong><STRONG>Add Product</STRONG> </strong>
				</td>
			</tr>


			<tr>
				<td align="center" bgColor="#f5fafe" class="ta_01">Name：</td>
				<td class="ta_01" bgColor="#ffffff"><input type="text"
					name="pname" class="bg"/>
				</td>
				<td align="center" bgCo.lor="#f5fafe" class="ta_01">Price：</td>
				<td class="ta_01" bgColor="#ffffff"><input type="text"
					name="price" 
					class="bg" />
				</td>
			</tr>
			<tr>
				<td align="center" bgColor="#f5fafe" class="ta_01">Amount：</td>
				<td class="ta_01" bgColor="#ffffff"><input type="text"
					name="pnum" 
					class="bg" />
				</td>
				<td align="center" bgColor="#f5fafe" class="ta_01">Category,CPU,GPU,Memory：</td>
				<td class="ta_01" bgColor="#ffffff"><select name="category"
					id="category">
						<option value="" selected="selected">--choose category--</option>
						<option value="Server">Server</option>
						<option value="Desktop">Desktop</option>
						<option value="Laptop">Laptop</option>
				</select><select name="cputype"
					id="cputype">
						<option value="" selected="selected">--choose cputype--</option>
						<option value="Intel32G">Intel32G</option>
						<option value="Intel64G">Intel64G</option>
						<option value="Intel128G">Intel128G</option>
						<option value="AMD32G">AMD32G</option>
						<option value="AMD64G">AMD64G</option>
				</select><select name="gputype"
					id="gputype">
						<option value="" selected="selected">--choose gputype--</option>
						<option value="500Mhz">500Mhz</option>
						<option value="550Mhz">550Mhz</option>
						<option value="600Mhz">600Mhz</option>
						<option value="650Mhz">650Mhz</option>
						<option value="700Mhz">700Mhz</option>
				</select><select name="memtype"
					id="memtype">
						<option value="" selected="selected">--choose memtype--</option>
						<option value="16G">16G</option>
						<option value="32G">32G</option>
						<option value="64G">64G</option>
						<option value="128G">128G</option>
						<option value="256G">256G</option>
				</select>
				</td>
			</tr>


			<tr>
				<td align="center" bgColor="#f5fafe" class="ta_01">Image：</td>
				<td class="ta_01" bgColor="#ffffff" colSpan="3">
				<input type="file" name="upload" size="30"/>
				</td>
			</tr>
			<TR>
				<TD class="ta_01" align="center" bgColor="#f5fafe">Description：</TD>
				<TD class="ta_01" bgColor="#ffffff" colSpan="3">
				<textarea
						name="description" cols="30" rows="3" 
						style="WIDTH: 96%"></textarea>
				</TD>
			</TR>
			<TR>
				<td align="center" colSpan="4" class="sep1"><img
					src="${pageContext.request.contextPath}/admin/images/shim.gif">
				</td>
			</TR>


			<tr>
				<td class="ta_01" style="WIDTH: 100%" align="center"
					bgColor="#f5fafe" colSpan="4">
					
					
						
					<input type="submit" class="button_ok" value="Confirm">	
						
					<FONT face="Arial">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</FONT>
					
					
					
					<input type="reset" value="Reset" class="button_cancel">

					<FONT face="Arial">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</FONT> <INPUT
					class="button_ok" type="button" onclick="history.go(-1)" value="Back" />
					<span id="Label1">
					
					</span>
				</td>
			</tr>
		</table>
	</form>
</body>
</HTML>