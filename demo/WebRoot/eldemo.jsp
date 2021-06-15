<%@page import="com.etcxm.www.entity.Product"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>EL表达式</title>
    
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
  	<h3>El表达式</h3>
    ${"Hello World" }=<%="Hello Wrold" %><br/>
    ${3+2 }=<%=3+2 %><br/>
    
    <%
    	request.setAttribute("username", "zhangsan");
     %>
     
     ${username} = <%=request.getAttribute("username") %>
     
     <%
     	pageContext.setAttribute("password", "123");
     	request.setAttribute("password", "456");
     	session.setAttribute("password", "789");
     	application.setAttribute("password", "abc");
     	//读取顺序：page->request->session->application
      %>
     ${pageScope.password }
     ${requestScope.password }
     ${sessionScope.password }
     ${applicationScope.password }
     <br/>
     
     <%
     	Product product=new Product(10,"Switch",2000d,100,"2021-06-07");
     	request.setAttribute("product", product);
      %>
    
     ${product }<br/>
     
     ${product.id } ${product.productName } ${product.productPrice } ${product.productStore} ${product.productPdate }
    
  </body>
</html>
