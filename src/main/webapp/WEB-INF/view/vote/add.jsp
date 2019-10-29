<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
request.setCharacterEncoding("UTF-8");
String htmlData = request.getParameter("content1") != null ? request.getParameter("content1") : "";
%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>投票发布</title>
<script type="text/javascript" src="/resource/js/jquery-3.2.1.js"></script>
</head>
<body>
	<form action="" id="form" method="post">
		<div class="form-group row ">
			<label for="title">投票标题</label> <input type="text"
				class="form-control" id="title" name="title" placeholder="请输入标题">
		</div>


		<div class="form-group row ">
			<textarea name="content" cols="400" rows="5" placeholder="标准格式({A:,B:,C:})"
				style="width: 600px; height: 250px; " ></textarea>
			<br />
		</div>
		
		
		<div class="form-group row" >
		<button type="button" class="btn btn-success" onclick="publish()">发布</button>
		
		</div>
	</form>






</body>


<script type="text/javascript">
//发布文章
function publish(){
	
	//alert(editor1.html())
	
		//序列化表单数据带文件
		var formData = $("#form").serialize();
		//var formData = new FormData($( "#form" )[0]);
		$.ajax({
			type:"post",
			data:formData,
			// 告诉jQuery不要去处理发送的数据
			processData : false,
			// 告诉jQuery不要去设置Content-Type请求头
			dataType : "json",
			url:"/vote/push",
			success:function(obj){
				if(obj){
					alert("发布成功")
					$('#center').load("/vote/list");
					//$('#center').load("/vote/list");
				}else{
					alert("发布失败")
				}
				
			}
			
			
			
		})


}

</script>
<%!
private String htmlspecialchars(String str) {
	str = str.replaceAll("&", "&amp;");
	str = str.replaceAll("<", "&lt;");
	str = str.replaceAll(">", "&gt;");
	str = str.replaceAll("\"", "&quot;");
	return str;
}
%>
</html>