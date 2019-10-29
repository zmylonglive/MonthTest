<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<script type="text/javascript" src="/resource/js/jquery-3.2.1.js">
<!--

//-->
</script>
<div class="container">
<form action="" id="spform">
		<label>名称</label>
		<input name="text" id="text"/>
		<br/>
		<input name="url" value="https://" id="url">
		<input type="button" value="提交" onclick="addata()"> 
		
		<script type="text/javascript">
			function addata(){
				$.post("/blogroll/add",$("#spform").serialize(),function(msg){
					if(msg.result==1){
						alert("处理成功")
						$("#content-wrapper").load("/blogroll/friendship")
					}else{
						alert(msg.errorMsg);
					}
				},"json")
			}
		</script>
</form>

</div>

