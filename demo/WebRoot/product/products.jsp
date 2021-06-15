<%@page import="java.sql.ResultSet"%>
<%@page import="com.etcxm.www.utils.DBUtil"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>My JSP 'products.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link href="https://cdn.bootcdn.net/ajax/libs/twitter-bootstrap/3.4.1/css/bootstrap.min.css" rel="stylesheet">
</head>

<body>
	<div class="container">
	<p><a href="product/addproduct.jsp" class="btn btn-success">新增产品</a></p>
	<h3>所有产品：</h3>
	<table class="table table-hover">
		<tr>
			<th>#</th>
			<th>产品名称</th>
			<th>产品价格</th>
			<th>产品库存</th>
			<th>发布日期</th>
			<th></th>
			<th></th>
		</tr>
		<%
			DBUtil dbUtil = new DBUtil();
			String sql = "select * from tproduct";
			ResultSet resultSet = dbUtil.query(sql);
			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				String productName = resultSet.getString("product_name");
				double productPrice = resultSet.getDouble("product_price");
				int productStore = resultSet.getInt("product_store");
				String productPdate = resultSet.getString("product_pdate");
		%>

		<tr>
			<td><%=id%></td>
			<td><%=productName%></td>
			<td><%=productPrice%></td>
			<td><%=productStore%></td>
			<td><%=productPdate%></td>
			<td><a href="product/editproduct.jsp?id=<%=id %>" class="btn btn-warning">编辑</a></td>
			<td><a href="product/opdel.jsp?id=<%=id %>" class="btn btn-danger">删除</a></td>
		</tr>

		<%
			}
			dbUtil.close();
		%>
	</table>
	</div>
</body>
</html>
