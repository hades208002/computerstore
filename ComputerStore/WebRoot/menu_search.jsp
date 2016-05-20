<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/my.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.8.3.js"></script>
<script type="text/javascript">
	// 我定义一个变量，用于记录搜索框的值所属的对象
	var $p = null;
	$(function(){
		$("#name").keyup(function(){
			// 当键盘按键弹起的时候触发，调用该方法，我们要获取用户输入的字符
			var $pname = $(this).val();
			var $data = {"pname":$pname,"method":"findBySearch"};
			// 设置url
			var $url = "${pageContext.request.contextPath}/product";
			// 调用post方法请求
			$.post($url,$data,function(data){
				//alert("Data Loaded: " + data);
				// 获取数组
				var lists = data.list;
				//alert(lists);
				// 在显示之前先清空
				$("#content").empty();
				for(var i=0;i<lists.length;i++){
					// 获取到每一个名字
					var plist = lists[i];
					//alert(plist.pname);
					// 创建一个div
					var $div = $("<div></div>");
					// 设置div的内容
					$div.text(plist.pname);
					// 设置鼠标事件
					$div.bind("mouseover",function(){
						$(this).css("background","red");
					});
					$div.bind("mouseout",function(){
						$(this).css("background","white");
					});
					// 点击事件
					$div.bind("click",function(){
						$("#name").val($(this).text());
						$p = plist;
						// 把所有的小的div清空
						$("#content").empty();
					});
					// 将该div添加到content中
					$("#content").append($div);
				}
				$("#content").show();
			},"json");
		});
	});
	// 根据输入框中的值，跳转到商品信息页面显示该商品
	function searchProduct(){
		// 获取搜索框中的值
		
		alert($p.pname);
	}
	
</script>

<div id="divmenu"><a style="color:#C0C0C0">Category：
	<a
		href="${pageContext.request.contextPath}/product?method=showProductByPage2&category=Server">Server</a>
	<a
		href="${pageContext.request.contextPath}/product?method=showProductByPage2&category=Desktop">Desktop</a>
	<a
		href="${pageContext.request.contextPath}/product?method=showProductByPage2&category=Laptop">Laptop</a> <a  href="${pageContext.request.contextPath}/product?method=showProductByPage2" style="color:#FFFF00">  All products</a>

	<div><a style="color:#C0C0C0">CPU：
	<a
		href="${pageContext.request.contextPath}/product?method=showProductByPage2&cputype=Intel32G">Intel32G</a>
	<a
		href="${pageContext.request.contextPath}/product?method=showProductByPage2&cputype=Intel64G">Intel64G</a>
	<a
		href="${pageContext.request.contextPath}/product?method=showProductByPage2&cputype=Intel128G">Intel128G</a>
	<a
		href="${pageContext.request.contextPath}/product?method=showProductByPage2&cputype=AMD32G">AMD32G</a>
	<a
		href="${pageContext.request.contextPath}/product?method=showProductByPage2&cputype=AMD64G">AMD64G</a>
	</div>
	<div><a style="color:#C0C0C0">GPU：
	<a
		href="${pageContext.request.contextPath}/product?method=showProductByPage2&gputype=500Mhz">500Mhz</a>
	<a
		href="${pageContext.request.contextPath}/product?method=showProductByPage2&gputype=550Mhz">550Mhz</a>
		<a
		href="${pageContext.request.contextPath}/product?method=showProductByPage2&gputype=600Mhz">600Mhz</a>
		<a
		href="${pageContext.request.contextPath}/product?method=showProductByPage2&gputype=650Mhz">650Mhz</a>
		<a
		href="${pageContext.request.contextPath}/product?method=showProductByPage2&gputype=700Mhz">700Mhz</a>
	</div>
	<div><a style="color:#C0C0C0">Memory：
	<a
		href="${pageContext.request.contextPath}/product?method=showProductByPage2&memtype=16G">16G</a>
	<a
		href="${pageContext.request.contextPath}/product?method=showProductByPage2&memtype=32G">32G</a>
		<a
		href="${pageContext.request.contextPath}/product?method=showProductByPage2&memtype=64G">64G</a>
		<a
		href="${pageContext.request.contextPath}/product?method=showProductByPage2&memtype=128G">128G</a>
		<a
		href="${pageContext.request.contextPath}/product?method=showProductByPage2&memtype=256G">256G</a></h4>
	</div>
</div>
<div id="divsearch">
	<form action="#" method="post">
		<table width="100%" border="0" cellspacing="0">
			<tr>
				<td style="text-align:right; padding-right:220px">Search 
					<input type="text" name="name" class="inputtable" id="name" /> 
					<input type="image" src="images/searchbutton.gif" border="0" onclick="searchProduct()" style="margin-bottom:-4px">
				</td>
			</tr>
		</table>

	</form>
</div>
<div id="content"
	style="background-color:white;width:128px; text-align:left;margin-left:945px;color:black;float:left;margin-top: -20px;display: none">
</div>