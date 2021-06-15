<jsp:include page="include_auth.jsp"></jsp:include>

<%@page import="java.sql.DriverManager"%>
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
    
    <title>删除产品</title>
    
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
		//获取id
		String id=request.getParameter("id");
		//数据库配置参数
		String driver="com.mysql.jdbc.Driver";
		String url="jdbc:mysql://localhost:3306/demo?useSSL=false&useUnicode=true&characterEncoding=utf8";
		String dbUser="root";
		String dbPwd="123456";
		//定义数据库对象
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		
		try{
			Class.forName(driver);
			connection=DriverManager.getConnection(url,dbUser,dbPwd);
			//sql语句，其中？为占位符。
			String sql="delete from tproduct where id=?";
			preparedStatement=connection.prepareStatement(sql);
			//赋值，把值带入到语句中,其中第一个参数代表了第几个问号，第二个参数是该问号的值
			preparedStatement.setString(1,id);	//第一个问号，用字符串变量id来代入。
			//执行删除操作,返回受影响的行数。
			int i=preparedStatement.executeUpdate();	//处理成功返回1，否则返回0
			if(i==1){	//成功！
				//使用javascript
				%>
				<script type="text/javascript">
					alert("删除成功！");
					window.location.href="homework/products.jsp";
				</script>
				<%
			}else{ //失败！
			%>
				<script type="text/javascript">
					alert("删除失败！");
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
