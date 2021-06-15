<%@page import="java.sql.ResultSet"%>
<%@page import="com.etcxm.www.utils.DBUtil"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>编辑产品</title>
    
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
	<%
		String id=request.getParameter("id");
		//根据id读取旧的记录
		String sql="select * from tproduct where id=?";
		DBUtil dbUtil=new DBUtil();
		ResultSet resultSet=dbUtil.query(sql, id);
		while(resultSet.next()){
			String productName=resultSet.getString("product_name");
			Double prodcutPrice=resultSet.getDouble("product_price");
			Integer productStore=resultSet.getInt("product_store");
			String productPdate=resultSet.getString("product_pdate");
			%>
			<form action="product/opedit.jsp" method="post" class="form-horizontal">
				<input type="hidden" name="id" value="<%=id%>">
				<div class="form-group">
		    		<label for="productName">产品名称</label>
		    		<input type="text" class="form-control" id="productName" placeholder="请输入产品名称" name="productName" value="<%=productName %>">
		  		</div>
		  		<div class="form-group">
		    		<label for="productName">产品价格</label>
		    		<input type="text" class="form-control" id="productName" placeholder="请输入产品价格" name="productPrice" value="<%=prodcutPrice %>">
		  		</div>
		  		<div class="form-group">
		    		<label for="productName">产品库存</label>
		    		<input type="text" class="form-control" id="productName" placeholder="请输入产品库存" name="productStore" value="<%=productStore %>">
		  		</div>
				<input type="submit" value="新增产品" class="btn btn-success"/>
	</form>
			<%
		}
		dbUtil.close();
	 %>

</div>
  </body>
</html>
