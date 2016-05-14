<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="jquery-1.8.3.js"></script>
</head>
<body>
<div>
	<form action="" method="POST"> 
		<label><input name="yooo" type="radio" value="credit card" checked/>creditCard </label> 
		<label><input name="yooo" type="radio" value="transfer" />transfer </label> 
		<label><input name="yooo" type="radio" value="cash" />cash</label>
		<div style="border:1px solid #000;width:400px;height:200px;padding-top:10px" id="showIn">
			Card No : <input type="text" name="cardNo"/><br>
			Safety Code : <input type="text" name="safetyCode"/>
		</div> 
	</form> 
</div>
<script type="text/javascript">
	$(function(){
		
		$('input:radio[name="yooo"]').click(function(){
			checkRadio();
		 });
	});
	function checkRadio(){
		var yooo=$('input:radio[name="yooo"]:checked').val();
		if(yooo==null){
            alert("什么也没选中!");
            return false;
        }
        else if(yooo == "credit card"){
            alert(yooo);
            $("#showIn").html("Card No : <input type=\"text\" name=\"cardNo\"/><br>Safety Code : <input type=\"text\" name=\"safetyCode\"/>");
        }
        
        else if(yooo== "transfer"){
        	alert(yooo);
        	$("#showIn").html("<iframe src=\"transfer.html\" id=\"content\" name=\"content\" height=\"300\" width=\"468\" scrolling=\"no\" frameborder=\"1\"></iframe>");
        }
        else if(yooo == "cash"){
        	alert(yooo);
        	location.href = "transfer.html";
        }
	}
</script>
</body>
</html>