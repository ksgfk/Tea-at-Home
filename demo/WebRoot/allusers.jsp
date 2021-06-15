<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.DriverManager"%>
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
    
    <title>My JSP 'allusers.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link href="https://cdn.bootcdn.net/ajax/libs/twitter-bootstrap/3.4.1/css/bootstrap.css" rel="stylesheet">
  </head>
  
  <body>
  <div class="container">
  <div class="page-header">
  <h1>用户管理系统<small>管理员：19软工</small></h1>
  
</div>
<ol class="breadcrumb">
  <li><a href="#">主页</a></li>
  <li><a href="#">管理系统</a></li>
  <li class="active">用户管理</li>
</ol>
  <br/>
  <a href="adduser.jsp" class="btn btn-success">新增用户</a>
  <p></p>
  <table class="table table-hover">
  	<tr>
  		<th>#</th>
  		<th>用户名</th>
  		<th>性别</th>
  		<th>电话</th>
  		<th>生日</th>
  	</tr>
	<%
		String driver="com.mysql.jdbc.Driver";	//mysql数据库驱动的名字
		String url="jdbc:mysql://localhost:3306/demo"; 	//数据库的连接地址
		String dbUser="root";			//用户名
		String dbPwd="root";			//密码
		
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		ResultSet resultSet=null;
		try{
		Class.forName(driver);	//加载驱动，可以不写。
		//获取数据库连接
		 connection=
			DriverManager.getConnection(url,dbUser,dbPwd);
			
		//准备sql语句
		String sql="select * from tuser";
		
		//预编译sql语句
		 preparedStatement=
				connection.prepareStatement(sql);
				
		//执行查询，获得结果集
		 resultSet=
			preparedStatement.executeQuery();
			
		//遍历结果集，将数据读取出来
		while(resultSet.next()){	//如果有数据next会返回true。如果没有数据返回false。
			int id=resultSet.getInt("id");	//读取id字段存入整形变量id中
			String username=resultSet.getString("username");	//读取username
			String gendar=resultSet.getString("gendar");
			String phone=resultSet.getString("phone");
			String birthday=resultSet.getString("birthday");
			%>
			<tr>
				<td><%=id %></td>
				<td><%=username %></td>
				<td><%=gendar %></td>
				<td><%=phone %></td>
				<td><%=birthday %></td>
			</tr>
			<%
			
		}
		
		
		}catch(Exception e){
			e.printStackTrace();
		}finally{
		//关闭对象，关闭的顺序和实例化的顺序相反，最后实例的，最早关闭。
		if(resultSet!=null)resultSet.close();
		if(preparedStatement!=null)preparedStatement.close();
		if(connection!=null)connection.close();
		
		}
	 %>
	
	
	

</table>
	</div>
  </body>
</html>
