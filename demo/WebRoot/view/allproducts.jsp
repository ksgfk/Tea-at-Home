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
  				<td><a href="edt.do?id=${product.id }">编辑</a></td>
  				<td><a href="del.do?id=${product.id }">删除</a></td>
  			</tr>
  		</c:forEach>
  	
  	
  	<%-- <%
  		//把list读取出来
  		List<Product> list=(List)request.getAttribute("list");
  		//循环读取列表
  		for(Product product:list){
  			%>
  			<tr>
  				<td><%=product.getId() %></td>
  				<td><%=product.getProductName() %></td>
  				<td><%=product.getProductPrice() %></td>
  				<td><%=product.getProductStore() %></td>
  				<td><%=product.getProductPdate() %></td>
  				<!-- 记得无论是编辑还是删除，都需要传递变量id，通过表达式来获取当前的id值 -->
  				<td><a href="edt.do?id=<%=product.getId()%>" class="btn btn-warning">编辑</a></td>
  				<td><a href="del.do?id=<%=product.getId()%>" class="btn btn-danger">删除</a></td>
  			</tr>
  			<%
  		}  	
  	 %> --%>
  	</table>
  	</div>
  	
    <%
    	//setAttribute->保存对象，全部当成Object类型存储
    	//ResultSet resultSet=(ResultSet)request.getAttribute("resultSet");
    	/* while(resultSet.next()){
    		int id=resultSet.getInt("id");
    		String productName=resultSet.getString("product_name"); */
    		%>
    			<%-- <%=id %>:<%=productName %><br/> --%>
    		<%
    	/* } */
    	//resultSet.getStatement().getConnection().close();
    	//service	dao entity
     %>
  </body>
</html>
