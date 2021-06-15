<%@page import="com.etcxm.www.entity.Product"%>
<%-- <%@page import="java.sql.ResultSet"%> --%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'allproducts.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<meta charset="utf-8" >
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<link href="https://cdn.bootcdn.net/ajax/libs/twitter-bootstrap/3.4.1/css/bootstrap.min.css" rel="stylesheet">
  </head>
  
  <body>
  	<div class="container">
  	<h3>所有产品</h3>
  	<a href="view/addproduct.jsp" class="btn btn-success">新增产品</a>
  	<table class="table table-hover">
  		<c:forEach items="${list }" var="product">
  			<tr>
  				<td>${product.id }</td>
  				<td>${product.productName }</td>
  				<td>${product.productPrice }</td>
  				<td>${product.productStore }</td>
  				<td><c:if test="${product.productStore>50 }">库存正常</c:if><c:if test="${product.productStore<=50 }">
  				<span style="color:red">低库存</span></c:if></td>
  				<td>${product.productPdate }</td>
  				<td><a href="edt.do?id=${product.id }" class="btn btn-warning">编辑</a></td>
  				<td><a href="del.do?id=${product.id }" class="btn btn-success">删除</a></td>
  			</tr>
  		</c:forEach>
  	</table>
  	<a href="allpage.do?pageNum=1&pageSize=${pageSize}" class="btn btn-default">第一页 </a>
  	<c:if test="${currPage!=1 }"><a href="allpage.do?pageNum=${currPage-1 }&pageSize=${pageSize}" class="btn btn-default">上一页</a></c:if> 
  	共  ${allCount } 条，共  ${allPage } 页 ，当前在 ${currPage } 页
  	<c:if test="${currPage!=allPage }"><a href="allpage.do?pageNum=${currPage+1 }&pageSize=${pageSize}" class="btn btn-default">下一页</a></c:if>
  	<a href="allpage.do?pageNum=${allPage}&pageSize=${pageSize}" class="btn btn-default">最后一页 </a>
  	</div>
  </body>
</html>
