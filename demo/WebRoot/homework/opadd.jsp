<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
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

<title>保存产品</title>

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

	<%-- <%@ include file="include_auth.jsp" %>	//静态引用 把代码拷贝过来以后，再 编译。 --%>
	
	<jsp:include page="include_auth.jsp"></jsp:include>

	<%
		//获取表单参数
		//设置编码为utf-8
		request.setCharacterEncoding("utf-8");
		//获取对应的参数
		String productName = request.getParameter("productName");
		String productPrice = request.getParameter("productPrice");
		String productStore = request.getParameter("productStore");

		String driver = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306/demo?useSSL=false&useUnicode=true&characterEncoding=utf8";
		String dbUser = "root";
		String dbPwd = "123456";

		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			//转换字段的格式
			double pPrice = Double.parseDouble(productPrice);
			int pStore = Integer.parseInt(productStore);
			//产品日期的处理
			Date nowDate = new Date();
			//格式化当前日期
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
			String pDate = simpleDateFormat.format(nowDate);

			Class.forName(driver);
			connection = DriverManager.getConnection(url, dbUser, dbPwd);
			String sql = "insert into tproduct(product_name,product_price,product_store,product_pdate) values(?,?,?,?)";
			preparedStatement = connection.prepareStatement(sql);
			//赋值
			preparedStatement.setString(1, productName);
			preparedStatement.setDouble(2, pPrice);
			preparedStatement.setInt(3, pStore);
			preparedStatement.setString(4, pDate);

			int i = preparedStatement.executeUpdate();
			if (i == 1) {
	%>
	<script type="text/javascript">
		alert("新增成功！");
		window.location.href = "homework/products.jsp";
	</script>
	<%
		} else {
	%>
	<script type="text/javascript">
		alert("新增失败！");
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
			if(preparedStatement!=null)preparedStatement.close();
			if(connection!=null)connection.close();
		}
	%>

</body>
</html>
