<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
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

  </head>
  
  <body>
  	<%
  		//获取编辑产品的编号。
  		String id=request.getParameter("id");
  		//通过编号获取产品信息。把信息放入到form表单中
  		//连接数据库
  		String driver="com.mysql.jdbc.Driver";
		String url="jdbc:mysql://localhost:3306/demo?useSSL=false&useUnicode=true&characterEncoding=utf8";
		String dbUser="root";
		String dbPwd="123456";
		
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		ResultSet resultSet=null;
		
		try{
			Class.forName(driver);
			connection=DriverManager.getConnection(url,dbUser,dbPwd);
			String sql="select * from tproduct where id=?";	//?占位符
			preparedStatement=connection.prepareStatement(sql);
			preparedStatement.setString(1, id);
			resultSet=preparedStatement.executeQuery();
			while(resultSet.next()){
				String productName=resultSet.getString("product_name");
				double productPrice=resultSet.getDouble("product_price");
				int productStore=resultSet.getInt("product_store");
				%>
					<h3>编辑产品</h3>
	<form action="homework/opupt.jsp" method="post">
		产品名称：<input type="text" name="productName" value="<%=productName %>" /><br/>
		产品价格：<input type="text" name="productPrice" value="<%=productPrice%>"/><br/>
		产品库存：<input type="text" name="productStore" value="<%=productStore%>"/><br/>
		<input type="hidden" value="<%=id %>" name="id" />
		<input type="submit" value="修改产品" />
	</form>
				<%
			}
		}catch(Exception e){
			e.printStackTrace();
			
		}finally{
			if(resultSet!=null)resultSet.close();
			if(preparedStatement!=null)preparedStatement.close();
			if(connection!=null)connection.close();
		}
  		
  	%>
	

  </body>
</html>
