<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

	<%
  		Object username=session.getAttribute("username");
  		System.out.println(basePath);
  		if(username==null){		//没登录，session没有username的记录，所以不让访问页面
  			%>
  			<html>
  			<head>
  				<meta charset="utf-8">
  			</head>
  			<script type="text/javascript">
  				alert("请登录！");
  				window.location.href="<%=basePath%>user/login.jsp";
  			</script>
  			</html>
  			<%
  		}
  	 %>
  	 
  