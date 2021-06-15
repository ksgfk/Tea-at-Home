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

<title>My JSP 'calc.jsp' starting page</title>

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
		//设置request中内容，编码格式为UTF-8，为了解决post中的中文问题。
		request.setCharacterEncoding("UTF-8");
		//拿到对应参数
		String n1 = request.getParameter("num1");
		String n2 = request.getParameter("num2");
		String op = request.getParameter("op");
	%>
	<%=n1%><%=op%><%=n2%>=
	<%
		try {
			//对字符串转成数字格式
			int num1 = Integer.parseInt(n1);
			int num2 = Integer.parseInt(n2);
			//判断用户的操作
			if ("+".equals(op)) { //加法
	%>
	<%=num1 + num2%>
	<%
		} else if ("-".equals(op)) {
	%>
	<%=num1 - num2%>
	<%
		} else if ("*".equals(op)) {
	%>
	<%=num1 * num2%>
	<%
		} else if ("/".equals(op)) {
	%>
	<%=num1 / num2%>
	<%
		} else {
			}
		} catch (Exception E) {
			//out.println("参数错误，请重试！");
			//重定向到input_num.jsp
			/* out.println("1");
			response.sendRedirect("input_num.jsp");
			out.println("2");
			System.out.println("3"); */
			%>
			<script type="text/javascript">
				alert("参数错误！请重试！");
				location.href="input_num.jsp";
			</script>
			<%
		}
	%>
</body>
</html>
