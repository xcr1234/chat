<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8" />
		
		<title>简易聊天室系统</title>
		<script src="plugins/jquery/jquery.min.js" type="text/javascript" charset="utf-8"></script>
		<script type="text/javascript">
			function login(){
				if($("#usr").val()==""){
					alert("请输入用户名!");
					return false; 
				}
				if($("#pwd").val()==""){
					alert("请输入密码!");
					return false;
				}
				$("#form").submit();
				
			}
		</script>
	</head>
	<body>
		<form action="login.do" method="post" id="form">
		<h2>登录</h2>
		<p>用户名：<input type="text" name="usr" id="usr"></p>
		<p>密码：<input type="password" name="pwd" id="pwd"></p>
		
		<p><a href="javascript:;" onclick="login()">登录</a> <a href="reg.jsp">注册</a></p>
		</form>
	</body>
</html>