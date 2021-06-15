<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>修改保存</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<%@ include file="include_auth" %>

  </head>
  
  <body>
    <%
    	request.setCharacterEncoding("utf-8");
  		//获取对应的参数
  		String productName = request.getParameter("productName");
  		String productPrice = request.getParameter("productPrice");
  		String productStore = request.getParameter("productStore");
  		String id=request.getParameter("id");
  		//产品日期的处理
		Date nowDate = new Date();
		//格式化当前日期
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String pDate = simpleDateFormat.format(nowDate);
		
		//连接数据库进行更新
		String driver="com.mysql.jdbc.Driver";
		String url="jdbc:mysql://localhost:3306/demo?useSSL=false&useUnicode=true&characterEncoding=utf8";
		String dbUser="root";
		String dbPwd="123456";
		
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		
		String sql="update tproduct set product_name=?,product_price=?,product_store=?,product_pdate=? where id=?";
		
		try{
			
			double dPrice=Double.parseDouble(productPrice);
			int iStore=Integer.parseInt(productStore);
			
			Class.forName(driver);
			connection=DriverManager.getConnection(url,dbUser,dbPwd);
			preparedStatement=connection.prepareStatement(sql);
			preparedStatement.setString(1, productName);
			preparedStatement.setDouble(2, dPrice);
			preparedStatement.setInt(3, iStore);
			preparedStatement.setString(4, pDate);
			preparedStatement.setString(5, id);
			int i=preparedStatement.executeUpdate();
			if(i==1){
				%>
				<script type="text/javascript">
					alert("修改成功！");
					window.location.href = "homework/products.jsp";
				</script>
				<%
			}else{
				%>
				<script type="text/javascript">
					alert("修改失败！");
					window.history.back();
				</script>
				<%
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(preparedStatement!=null)preparedStatement.close();
			if(connection!=null)connection.close();
		}
		
    	
    
    %>
  </body>
</html>
