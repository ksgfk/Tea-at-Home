<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'forward_demo.jsp' starting page</title>
    
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
		//转发，一次请求
		request.getRequestDispatcher("index.jsp").forward(request, response);
		//重定向，二次请求，客户端发送了两次信息给服务器。
		response.sendRedirect("index.jsp");
		
	 %>
	 
	 <script type="text/javascript">
		window.location.href="index.jsp";	//使用js跳转到index.jsp,两次请求。
	</script>


  </body>
</html>



