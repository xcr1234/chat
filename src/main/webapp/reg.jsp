<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

	<head>
		<meta charset="UTF-8">
		<title>简易聊天室系统</title>
		<script src="plugins/jquery/jquery.min.js" type="text/javascript" charset="utf-8"></script>
		<script type="text/javascript">
			function reg(){
				
				if($("#usr").val()==""){
					alert("请输入用户名！");
					return false; 
				}
				
				
				if($("#pwd").val()==""){
					alert("请输入密码!");
					return false;
				}
				if($("#nick").val()==""){
					alert("请输入昵称!");
					return;
				}
				$("#form").submit();
			}
		</script>
	</head>

	<body>
		<form action="reg.do" method="post" id="form">
			<h2>注册</h2>
			<p>用户名：<input type="text" id="usr" name="usr" /></p>
			<p>密码：<input type="password" id="pwd" name="pwd"></p>
			<p>昵称：<input type="text" id="nick" name="nick"></p>
			<p><a href="#" onclick="reg();">注册</a> <a href="index.jsp">返回</a></p>
		</form>

	</body>

</html>