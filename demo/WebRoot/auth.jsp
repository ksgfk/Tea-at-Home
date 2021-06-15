<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.PreparedStatement"%>
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

<title>My JSP 'auth.jsp' starting page</title>

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
		request.setCharacterEncoding("utf-8");
		String username = request.getParameter("username");
		String password = request.getParameter("password");

		String driver = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306/shop?useUnicode=true&characterEncoding=utf8&useSSL=false";
		String dbUser = "root";
		String dbPwd = "123456";

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			Class.forName(driver);
			connection = DriverManager.getConnection(url, dbUser, dbPwd);
			String sql = "select * from tab_user where username=? and password=?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, username);
			preparedStatement.setString(2, password);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				session.setAttribute("username", username);
	%>
	<script type="text/javascript">
		alert("欢迎你，" + username);
		window.location.href = "welcome.jsp";
	</script>
	<%
		} else {
	%>
	<script type="text/javascript">
		alert("用户名或者密码错误！");
		window.history.back();
	</script>
	<%
		}
		} catch (Exception e) {
			e.printStackTrace();
	%>
	<script type="text/javascript">
		window.history.back();
	</script>
	<%
		} finally {
			try {
				if (resultSet != null)
					resultSet.close();
				if (preparedStatement != null)
					preparedStatement.close();
				if (connection != null)
					connection.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	%>
</body>
</html>









