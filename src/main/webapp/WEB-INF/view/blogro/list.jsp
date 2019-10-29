<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>用户列表</title>
<script type="text/javascript" src="/resource/js/cms.js"></script>
<script type="text/javascript">



//查看文章详情
function toDetail(){
	$("#content-wrapper").load("/blogroll/friendship");
	
}
function add(){
	$("#content-wrapper").load("/blogroll/add");
}
/**
function addArticle(id){
	$("#content-wrapper").load("/special/detail?id="+id)
}

function modify(id){
	$("#content-wrapper").load("/special/update?id="+id)
}*/


</script>


</head>
<body>
<div class="container-fluid">

		<div>
		     链接管理/链接管理
		     <br/>
		<button type="button" class="btn btn-info" onclick="add()">添加链接</button>
		</div>

		<table class="table table-sm  table-hover table-bordered ">
			<thead class="thead-light">
				<tr align="center">
					
					<td>编号</td>
					<td>名称</td>
					<td>地址</td>
					<td>时间</td>
<!-- 					<td>操作</td> -->
				</tr>
			</thead>
			<c:forEach items="${listBlogroll}" var="special" varStatus="index">
				<tr align="center">
					<td>${index.index+1}</td>
					<td>${special.text}</td>
					<td><a>${special.url}</a></td>
					<td>${special.created}</td>
					<%--<td>
						<button type="button" class="btn btn-info" onclick="addArticle(${special.id})">追加文章</button>
					    <button type="button" class="btn btn-info" onclick="modify(${special.id})">修改专题</button>
					
					</td>--%>
				</tr>

			</c:forEach>

		</table>
		<div>
		</div>
	</div>
</body>
</html>