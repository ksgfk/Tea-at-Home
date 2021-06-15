<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'request.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
    <%
    	request.setAttribute("username", "lisi");
     %>
     <%=request.getAttribute("username") %>
     <!-- <a href="request02.jsp" >request2</a> -->
     <%-- <%
     	//转发，对于客户端来说，是一次连接。
     	request.getRequestDispatcher("request02.jsp").forward(request, response);
      %> --%>
      动作指令，跳转到request02.jsp
      <jsp:forward page="request02.jsp"></jsp:forward>
  </body>
</html>

pageContext:当前页
request:一次请求有用
session:一次会话
application:一直保持







