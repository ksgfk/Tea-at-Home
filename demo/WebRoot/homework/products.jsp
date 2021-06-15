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
    
    <title>My JSP 'products.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<%
  		Object username=session.getAttribute("username");
  		System.out.println(basePath);
  		if(username==null){		//没登录，session没有username的记录，所以不让访问页面
  			%>
  			<script type="text/javascript">
  				alert("请登录！");
  				window.location.href="<%=basePath%>user/login.jsp";
  			</script>
  			<%
  		}
  	 %>
  </head>
  
  <body>
  	<p>
  		<a href="homework/addproduct.jsp" >新增产品</a>
  	</p>
  	<table width="500px" border="1">
	<tr>
		<th>#</th>
		<th>产品名称</th>
		<th>产品价格</th>
		<th>产品库存</th>
		<th>发布时间</th>
		<th>&nbsp;</th>
		<th>&nbsp;</th>
	</tr>
	<%
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
			String sql="select * from tproduct order by product_pdate desc";
			preparedStatement=connection.prepareStatement(sql);
			resultSet=preparedStatement.executeQuery();
			while(resultSet.next()){
				int id=resultSet.getInt("id");
				String productName=resultSet.getString("product_name");
				double productPrice=resultSet.getDouble("product_price");
				int productStore=resultSet.getInt("product_store");
				String productPdate=resultSet.getString("product_pdate");
				%>
				
				<tr>
					<td><%=id %></td>
					<td><%=productName %></td>
					<td><%=productPrice %></td>
					<td><%=productStore%></td>
					<td><%=productPdate %></td>
					<td><a href="homework/editproduct.jsp?id=<%=id%>">编辑</a></td>
					<td><a href="homework/opdel.jsp?id=<%=id%>">删除</a></td>
				</tr>
				
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
	</table>
  </body>
</html>
