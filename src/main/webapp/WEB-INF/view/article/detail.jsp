<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
<meta name="viewport"
	content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="/resource/css/bootstrap.min.css">
	<script src="${pageContext.request.contextPath}/resource/js/jquery-3.2.1.js"></script>
<script type="text/javascript" src="/resource/js/bootstrap.min.js"></script>
</head>
<body>  
<div class="container">
		
		<dl>
		   <dt><a href="javascript:window.close()">关闭窗口</a></dt>
			<dt>${article.title }</dt>
				<hr>
			<td>文章作者：${article.user.username}&nbsp;&nbsp;&nbsp;
				发布时间：<fmt:formatDate value="${article.created}" pattern="yyyy年MM月dd日  HH:mm:ss"/> </td>
			<dd>文章内容：${article.content }
			</dd>
			<dd>文章标签：${article.terms}</dd>
			<dd><ul class="pagination"><li class="page-item">${adjStr}</li></ul></dd>
			
			<dd><div>
				<form action="">
					<input type="text" name="content">
					<input type="button" value="评论" onclick="commnent()">
				</form>
			</div>
				<hr>
				评论数量：${article.commentCnt }
			</dd>
			<dd><div id="commentList"></div></dd>
			
		</dl>
	

</div>
<script type="text/javascript">



	
	$(function(){
		$("#commentList").load("/comment/getComList?articleId=${article.id}" );
	}); 
	
	function commnent(){
		
		var retext=$("[name='content']").val();
		//alert(retext)
		var id=${article.id}
		//alert(id)
		if(retext!=""){
		$.ajax({ 
			type:"post",
			data:{content:retext,articleId:id},
			url:"/comment/addComment",
			success:function(msg){
				if(msg.result == 1){ 
					alert("发表成功");
					$("#commentList").load("/comment/getComList?articleId=${article.id}" );
					history.go(0)
					//location.href="getDetail" 
				}else{
					alert(msg.errorMsg)
				}
			}
		})
		}else{
			alert("请输入评论内容")
		}
	}
	$("#commentList").load("/article/getclist?articleId=${article.id}" );
	//增加点击次数
	$.post("/article/addHits",{id:${article.id}},function(data){
		
	},"json");
</script>


</body>
</html>